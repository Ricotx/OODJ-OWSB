/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.omega.classes;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.io.*;
/**
 *
 * @author userr
 */
public class PO {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    private final String poID;
    private PR pr;
    private Supplier sup;
    private Item item;
    private int quantity;
    private final LocalDate Date;
    private final String orderedBy;
    private String approval;
    private String approvalBy;
    private int receivedQuantity;

    public PO(String poID, PR pr, Supplier sup, Item item, int quantity, LocalDate Date, String orderedBy, String approval, String approvalBy, int receivedQuantity) {
        this.poID = poID;
        this.pr = pr;
        this.sup = sup;
        this.item = item;
        this.quantity = quantity;
        this.Date = Date;
        this.orderedBy = orderedBy;
        this.approval = approval;
        this.approvalBy = approvalBy;
        this.receivedQuantity = receivedQuantity;
    }

    @Override
    public String toString(){
        return poID + "," + pr.getPR_ID() + "," + item.getItemID() + "," + sup.getSupplierID()+ ","+ quantity + "," + Date.format(formatter) + "," + orderedBy + "," + approval + "," + approvalBy + "," + receivedQuantity;
    }
    
    public static PO fromLine(String line, List<PR> prList, List<Item> itemList, List<Supplier> supplierList) {
        try {
        String[] parts = line.split(",");
        String prID = parts[1];
        String itemID = parts[2];
        String supplierID = parts[3];

        PR matchedPR = prList.stream()
                             .filter(pr -> pr.getPR_ID().equals(prID))
                             .findFirst().orElse(null);

        Item matchedItem = itemList.stream()
                                   .filter(i -> i.getItemID().equals(itemID))
                                   .findFirst().orElse(null);

        Supplier matchedSupplier = supplierList.stream()
                                               .filter(s -> s.getSupplierID().equals(supplierID))
                                               .findFirst().orElse(null);

        if (matchedPR == null || matchedItem == null || matchedSupplier == null) {
            System.out.println("PO skipped due to unresolved references: " + line);
            return null;
        }

        return new PO(
            parts[0], // PO_ID
            matchedPR,
            matchedSupplier,
            matchedItem,
            Integer.parseInt(parts[4]),
            LocalDate.parse(parts[5], formatter),
            parts[6],
            parts[7],
            parts[8],
            Integer.parseInt(parts[9])
        );

    } catch (Exception e) {
        System.out.println("Error parsing PO from line: " + line);
        e.printStackTrace();
        return null;
    }
}
    
    public String getPoID() {
        return poID;
    }

    public PR getPr() {
        return pr;
    }

    public void setPr(PR pr) {
        this.pr = pr;
    }

    public Supplier getSup() {
        return sup;
    }

    public void setSup(Supplier sup) {
        this.sup = sup;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public LocalDate getDate() {
        return Date;
    }


    public String getRequestedBy() {
        return orderedBy;
    }

    public String getApproval() {
        return approval;
    }

    public void setApproval(String approval) {
        this.approval = approval;
    }

    public String getApprovalBy() {
        return approvalBy;
    }

    public void setApprovalBy(String approvalBy) {
        this.approvalBy = approvalBy;
    }
    
    public int getReceivedQuantity(){
        return receivedQuantity;
    }
    
    public void setReceivedQuantity(int receivedQuantity){
        this.receivedQuantity = receivedQuantity;
    }
    
    
}
