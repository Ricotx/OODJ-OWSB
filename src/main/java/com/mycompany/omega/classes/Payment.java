/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.omega.classes;

/**
 *
 * @author ahaiq
 */
public class Payment {
    private String paymentID;
    private String poID;
    private String supplierID;
    private String itemID;
    private int quantity;
    private double unitPrice;
    private double totalAmount;
    private String paymentDate;
    private String paidBy;
    
        public Payment(String paymentID, String poID, String supplierID, String itemID,int quantity, double unitPrice, double totalAmount,String paymentDate, String paidBy) {
        
            this.paymentID = paymentID;
            this.poID = poID;
            this.supplierID = supplierID;
            this.itemID = itemID;
            this.quantity = quantity;
            this.unitPrice = unitPrice;
            this.totalAmount = totalAmount;
            this.paymentDate = paymentDate;
            this.paidBy = paidBy;
    }
      public static Payment fromLine(String line) {
            String[] parts = line.split(",");
            if (parts.length != 9) return null;

            return new Payment(
                parts[0], // PaymentID
                parts[1], // PO_ID
                parts[2], // SupplierID
                parts[3], // ItemID
                Integer.parseInt(parts[4]),  // Quantity
                Double.parseDouble(parts[5]), // UnitPrice
                Double.parseDouble(parts[6]), // Total
                parts[7], // PaymentDate
                parts[8]  // PaidBy
            );
    }  
        
        
        
    @Override
    public String toString() {
        return String.join(",",
            paymentID, poID, supplierID, itemID,
            String.valueOf(quantity),
            String.format("%.2f", unitPrice),
            String.format("%.2f", totalAmount),
            paymentDate, paidBy
        );
    }

    public String getPaymentID() {
        return paymentID;
    }

    public String getPoID() {
        return poID;
    }

    public String getSupplierID() {
        return supplierID;
    }

    public String getItemID() {
        return itemID;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public String getPaymentDate() {
        return paymentDate;
    }

    public String getPaidBy() {
        return paidBy;
    }
    
}
