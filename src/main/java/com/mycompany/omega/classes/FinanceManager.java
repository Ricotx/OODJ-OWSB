/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.omega.classes;
import com.mycompany.omega.FinanceFrame;
import com.mycompany.omega.LoginFrame;
import java.time.LocalDate;
import java.util.List;
import java.util.ListIterator;
import java.util.stream.Collectors;
import javax.swing.JOptionPane;

import java.text.SimpleDateFormat;
import java.util.Calendar;
/**
 *
 * @author ahaiq
 */
public class FinanceManager extends Employee implements Viewable<PO> {
    private List<PO> poList;
    private List<PR> prList;
    private List<Supplier> supplierList;
    private List<Item> itemList;
    private List<Payment> paymentList;
    
    

    public FinanceManager(String employeeID, String name, Role role, String email, String password) {
        super(employeeID, name, role, email, password);
        List<Item> itemList = FileHandler.loadFromFile("data/Items.txt", Item::fromLine);
        this.itemList = FileHandler.loadFromFile("data/Items.txt", Item::fromLine);
        this.prList = FileHandler.loadFromFile("data/PR.txt",line -> PR.fromLine(line, itemList));  
        this.supplierList = FileHandler.loadFromFile("data/Supplier.txt",line -> Supplier.fromLine(line, itemList));
        this.poList = FileHandler.loadFromFile("data/PO.txt",line -> PO.fromLine(line, prList, itemList, supplierList));
        this.paymentList = FileHandler.loadFromFile("data/Payments.txt", Payment::fromLine);
    }

    
    @Override
    public void launchDashboard() {
        new FinanceFrame(Session.getCurrentUser()).setVisible(true);
    }
    
    public void launchLogin(){
        new LoginFrame().setVisible(true);
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
    
    // ========= Logic for Finance Manager ========== //
        
   
    //View all tables
    public List<PO> filterPO_byDate(LocalDate date){
        return poList.stream()
                .filter(po -> po.getDate().equals(date))
                .collect(Collectors.toList());
    }
    
    public PO getPOById(String id){
        return poList.stream()
                .filter(po -> po.getPoID().equals(id))
                .findFirst()
                .orElse(null);
    }
    
    public List<PR> filterPR_byDate(LocalDate date){
    return prList.stream()
            .filter(pr -> pr.getRequestdDate().equals(date))
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
    
    //update PO status
    public void updatePOStatus(String poID, String status, String UserId){
        for(PO po : poList){
            if(po.getPoID().equals(poID)){
                po.setApproval(status);
                po.setApprovalBy(UserId);
                break;
            }
        }
        FileHandler.writeAllToFile("data/PO.txt", poList);
    }
    
    public boolean isSupplierValid(String supplierID, String itemID) {
    return getSupplierForItem(itemID).stream()
           .anyMatch(s -> s.getSupplierID().equals(supplierID));
    }
    
    public Double getPriceByPOID(String poID) {
    // Get the PO object by its ID
    PO po = getPOById(poID);
    if (po == null) {
        System.out.println("PO not found: " + poID);
        return null;
    }

    // Extract SupplierID and ItemID from the PO
    String supplierID = po.getSup().getSupplierID();
    String itemID = po.getItem().getItemID();

    // Get the list of suppliers for the item
    List<Supplier> suppliers = getSupplierForItem(itemID);
    
    if (!isSupplierValid(po.getSup().getSupplierID(), po.getItem().getItemID())) {
    System.out.println("Invalid supplier/item combo in PO: " + po.getPoID());
    }
    
    if (suppliers == null || suppliers.isEmpty()) {
        System.out.println("No suppliers found for ItemID: " + itemID);
        return null;
    }

    // Find the supplier matching the SupplierID and return its price
    for (Supplier sup : suppliers) {
        if (sup.getSupplierID().equals(supplierID)) {
            return sup.getPrice();
        }
    }

    System.out.println("Supplier " + supplierID + " not found for ItemID: " + itemID);
        return null;  
    }

     public void saveAllPaymentsToFile() {
        FileHandler.writeAllToFile("data/payments.txt", paymentList);
    }
    
    public void addPayment(Payment payment) {
        this.paymentList.add(payment);
        saveAllPaymentsToFile();
    }
    
    public boolean isPOAlreadyPaid(String poID) {
        return paymentList.stream().anyMatch(p -> p.getPoID().equals(poID));
    }   
    
    public String generateNextPaymentID() {
        int nextId = paymentList.size() + 1;
        return "PY" + String.format("%03d", nextId);
    }
    
    public String generatePaymentReport() {
        StringBuilder sb = new StringBuilder();
        double grandTotal = 0;

        sb.append("==== Finance Payment Report ====\n");
        sb.append(String.format("%-10s %-10s %-10s %-10s %-10s %-12s %-10s %-10s\n",
            "PayID", "PO_ID", "Supplier", "Item", "Qty", "Unit Price", "Total", "Date"));

        for (Payment p : paymentList) {
            sb.append(String.format("%-10s %-10s %-10s %-10s %-10d %-12.2f %-10.2f %-10s\n",
                p.getPaymentID(), p.getPoID(), p.getSupplierID(), p.getItemID(),
                p.getQuantity(), p.getUnitPrice(), p.getTotalAmount(), p.getPaymentDate()));

            grandTotal += p.getTotalAmount();
        }

        sb.append("\nTotal Payments Made: RM ").append(String.format("%.2f", grandTotal));
        return sb.toString();
    }
    
    public String generatePaymentReportByDate(Calendar dateFilter) {
        StringBuilder sb = new StringBuilder();
        double total = 0;
        boolean found = false;

        // Format Calendar to String in "yyyy-MM-dd" format
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String filterDateStr = sdf.format(dateFilter.getTime());

        sb.append("==== Payment Report for ").append(filterDateStr).append(" ====\n\n");
        sb.append(String.format("%-10s %-10s %-10s %-10s %-10s %-12s %-10s %-10s\n",
            "PayID", "PO_ID", "Supplier", "Item", "Qty", "UnitPrice", "Total", "Date"));

        for (Payment p : paymentList) {
            if (p.getPaymentDate().equals(filterDateStr)) {
                found = true;
                sb.append(String.format("%-10s %-10s %-10s %-10s %-10d %-12.2f %-10.2f %-10s\n",
                    p.getPaymentID(), p.getPoID(), p.getSupplierID(), p.getItemID(),
                    p.getQuantity(), p.getUnitPrice(), p.getTotalAmount(), p.getPaymentDate()));

                total += p.getTotalAmount();
            }
        }

        if (!found) {
            sb.append("No payments found on this date.");
        } else {
            sb.append("\nTotal for ").append(filterDateStr).append(": RM ").append(String.format("%.2f", total));
        }

        return sb.toString();
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
    
    public List<Payment> getAllPayments() {
        return paymentList;
    }
    
   
}

