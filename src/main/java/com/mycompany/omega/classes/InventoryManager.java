/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.omega.classes;

import com.mycompany.omega.InventoryFrame;
import java.util.List;
import com.mycompany.omega.classes.*;
import java.util.stream.Collectors;
import java.util.Map;


/**
 *
 * @author fikri
 */
public class InventoryManager extends Employee {
    private List<Item> itemList;
    private List<PR> prList;
    private List<Supplier> supplierList;
    private List<PO> poList;

    public InventoryManager(String employeeID, String name, Role role, String email, String password) {
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
        // Note: PR.fromLine expects itemList, so ensure items loaded first
        this.prList = FileHandler.loadFromFile("data/PR.txt", line -> PR.fromLine(line, itemList));
    }

    private void loadSuppliers() {
        this.supplierList = FileHandler.loadFromFile("data/Supplier.txt", line -> Supplier.fromLine(line, itemList));
    }

    private void loadPOs() {
        // PO.fromLine expects prList, itemList, supplierList, so load them first
        this.poList = FileHandler.loadFromFile("data/PO.txt", line -> PO.fromLine(line, prList, itemList, supplierList));
    }

    // Getter methods for GUI access
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
    

    // Existing launchDashboard, now passing 'this'
    @Override
    public void launchDashboard() {
        new InventoryFrame().setVisible(true);
    }

    public List<String> getPOIDs() {
        return poList.stream()
                .filter(po -> "APPROVED".equalsIgnoreCase(po.getApproval()))
                .filter(po -> po.getReceivedQuantity() < po.getQuantity()) // only show if quantity ordered > 0
                .map(PO::getPoID)
                .distinct()
                .collect(Collectors.toList());
    }
    
    public boolean isPOFullyReceived(String poID) {
        List<PO> details = getPODetailsByID(poID);

    // Make sure every PO detail's received quantity >= ordered quantity
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

                    int maxReceivable = po.getQuantity() - po.getReceivedQuantity();
                    if (received > maxReceivable) {
                        received = maxReceivable;
                    }

                    po.setReceivedQuantity(po.getReceivedQuantity() + received);

                    // Update item stock
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



