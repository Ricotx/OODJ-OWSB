/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.omega.classes;


import com.mycompany.omega.InventoryFrame;
import java.util.List;
import java.util.ListIterator;
import java.util.stream.Collectors;
import java.util.Map;

/**
 * InventoryManager manages inventory items, purchase requisitions, suppliers, and purchase orders.
 * Implements Manageable and Viewable interfaces for Purchase Orders.
 */
public class InventoryManager extends Employee implements Manageable<PO>, Viewable<PO> {
    private List<Item> itemList;
    private List<PR> prList;
    private List<Supplier> supplierList;
    private List<PO> poList;

    public InventoryManager(String employeeID, String name, Employee.Role role, String email, String password) {
        super(employeeID, name, role, email, password);
        loadItems();
        loadPRs();
        loadSuppliers();
        loadPOs();
    }

    private void loadItems() {
        this.itemList = FileHandler.loadFromFile("data/Items.txt", Item::fromLine);
    }

    private void loadPRs() {
        this.prList = FileHandler.loadFromFile("data/PR.txt", line -> PR.fromLine(line, itemList));
    }

    private void loadSuppliers() {
        this.supplierList = FileHandler.loadFromFile("data/Supplier.txt", line -> Supplier.fromLine(line, itemList));
    }

    private void loadPOs() {
        this.poList = FileHandler.loadFromFile("data/PO.txt", line -> PO.fromLine(line, prList, itemList, supplierList));
    }

    public List<Item> getItemList() {
        return itemList;
    }

    public List<PR> getPRList() {
        return prList;
    }

    public List<Supplier> getSupplierList() {
        return supplierList;
    }

    public List<PO> getPOList() {
        return poList;
    }

    @Override
    public void launchDashboard() {
        new InventoryFrame(Session.getCurrentUser()).setVisible(true);
    }

    // Manageable interface methods

    @Override
    public void add(PO po) {
        poList.add(po);
        FileHandler.appendLine("data/PO.txt", po.toString());
    }

    @Override
    public void edit(PO po) {
        ListIterator<PO> iterator = poList.listIterator();
        while (iterator.hasNext()) {
            PO current = iterator.next();
            if (current.getPoID().equals(po.getPoID())) {
                iterator.set(po);
                FileHandler.writeAllToFile("data/PO.txt", poList);
                break;
            }
        }
    }

    @Override
    public void delete(PO po) {
        poList.removeIf(p -> p.getPoID().equals(po.getPoID()));
        FileHandler.writeAllToFile("data/PO.txt", poList);
    }

    // Viewable interface methods

    @Override
    public List<PO> viewAll() {
        return poList;
    }

    @Override
    public PO viewById(String id) {
        return poList.stream()
                .filter(po -> po.getPoID().equals(id))
                .findFirst()
                .orElse(null);
    }

    // Existing business logic methods

    public List<String> getPOIDs() {
        return poList.stream()
                .filter(po -> "APPROVED".equalsIgnoreCase(po.getApproval()))
                .filter(po -> po.getReceivedQuantity() < po.getQuantity())  // only show PO with remaining quantity
                .map(PO::getPoID)
                .distinct()
                .collect(Collectors.toList());
    }

    public boolean isPOFullyReceived(String poID) {
        List<PO> details = getPODetailsByID(poID);
        return details.stream().allMatch(po -> po.getReceivedQuantity() >= po.getQuantity());
    }

    public List<PO> getPODetailsByID(String poID) {
        return poList.stream()
                .filter(po -> po.getPoID().equals(poID))
                .collect(Collectors.toList());
    }

    public void updateStock(Map<String, Map<String, Integer>> poItemMap) {
        for (Map.Entry<String, Map<String, Integer>> entry : poItemMap.entrySet()) {
            String poID = entry.getKey();
            Map<String, Integer> itemMap = entry.getValue();

            for (PO po : poList) {
                if (po.getPoID().equals(poID)) {
                    String itemID = po.getItem().getItemID();
                    if (itemMap.containsKey(itemID)) {
                        int received = itemMap.get(itemID);

                        // Ensure received quantity does not exceed remaining quantity
                        int maxReceivable = po.getQuantity() - po.getReceivedQuantity();
                        if (received > maxReceivable) {
                            received = maxReceivable;
                        }

                        po.setReceivedQuantity(po.getReceivedQuantity() + received);

                        // Update stock in Item
                        for (Item item : itemList) {
                            if (item.getItemID().equals(itemID)) {
                                item.setStock(item.getStock() + received);
                                break;
                            }
                        }
                    }
                }
            }
        }

        FileHandler.writeAllToFile("data/Items.txt", itemList);
        FileHandler.writeAllToFile("data/PO.txt", poList);
    }
}



