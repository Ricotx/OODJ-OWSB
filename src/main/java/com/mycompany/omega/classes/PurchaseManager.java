/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.omega.classes;
import com.mycompany.omega.PMFrame;
import java.time.LocalDate;
import java.util.List;
import java.util.ListIterator;
import java.util.stream.Collectors;

/**
 *
 * @author userr
 */
public class PurchaseManager extends Employee implements Manageable<PO>, Viewable<PO> {
    private List<PO> poList;
    private List<PR> prList;
    private List<Supplier> supplierList;
    private List<Item> itemList;
    
    

    public PurchaseManager(String employeeID, String name, Role role, String email, String password) {
        super(employeeID, name, role, email, password);
        List<Item> itemList = FileHandler.loadFromFile("data/Items.txt", Item::fromLine);
        this.itemList = FileHandler.loadFromFile("data/Items.txt", Item::fromLine);
        this.prList = FileHandler.loadFromFile("data/PR.txt",line -> PR.fromLine(line, itemList));  
        this.supplierList = FileHandler.loadFromFile("data/Supplier.txt",line -> Supplier.fromLine(line, itemList));
        this.poList = FileHandler.loadFromFile("data/PO.txt",line -> PO.fromLine(line, prList, itemList, supplierList));
    }

    
    @Override
    public void launchDashboard() {
        new PMFrame(Session.getCurrentUser()).setVisible(true);
    }
    
    // Interface: Manageable 
    @Override
    public void add(PO po){
        poList.add(po);
        FileHandler.appendLine("data/PO.txt",po.toString());
    }
    
    @Override
    public void edit(PO po){
        ListIterator<PO> iterator = poList.listIterator();
        while(iterator.hasNext()){
            PO current = iterator.next();
            if (current.getPoID().equals(po.getPoID())){
                iterator.set(po);
                break;
            }
        }
    }
    
    @Override
    public void delete(PO po){
        poList.removeIf(p -> p.getPoID().equals(po.getPoID()));
        FileHandler.writeAllToFile("data/PO.txt", poList);
    }
    
        // Interface: Viewable 
    @Override
    public List<PO> viewAll(){
        return poList;
    }
    
    @Override
    public PO viewById(String id){
        return poList.stream()
                .filter(po -> po.getPoID().equals(id))
                .findFirst()
                .orElse(null);
    }
    
    // Logic for Purchase Manager
    
    //generateNextPOID()
    public String generateNextPO_ID(){
        return FileHandler.getNextID("data/PO.txt", "PO");
    }
    
    
   
    //View all tables
    public List<PR> filterPR_byDate(LocalDate date){
        return prList.stream()
                .filter(pr -> pr.getRequestdDate().equals(date))
                .collect(Collectors.toList());
    }
    
    public List<PO> filterPO_byDate(LocalDate date){
        return poList.stream()
                .filter(po -> po.getDate().equals(date))
                .collect(Collectors.toList());
    }
    
    public List<PR> getPendingPRs(){
        return prList.stream()
                .filter(pr -> "PENDING".equalsIgnoreCase(pr.getStatus()))
                .collect(Collectors.toList());
    }
    
    public PR getPRById(String id){
        return prList.stream()
                .filter(pr -> pr.getPR_ID().equals(id))
                .findFirst()
                .orElse(null);
    }
    
    
    
    //search by filtering date or id
    //Filter out suppplier with the same itemID as the selected PR_ID
    public List<Supplier> getSupplierForItem(String itemID){
        return supplierList.stream()
                .filter(s -> s.getItem().getItemID().equals(itemID))
                .collect(Collectors.toList());
    }
    
    //add a new Purchase Order
    //update PR status
    public void updatePRStatus(String prID, String status){
        for(PR pr : prList){
            if(pr.getPR_ID().equals(prID)){
                pr.setStatus(status);
                break;
            }
        }
        FileHandler.writeAllToFile("data/PR.txt", prList);
    }
    
    //List all tables
    public List<PR> getAllPRs(){
        return prList;
    }
    
    public List<Supplier> getAllSuppliers(){
        return supplierList;
    }
    
    public List<Item> getAllItems(){
        return itemList;
    }

    
}

//List all tabes
    
