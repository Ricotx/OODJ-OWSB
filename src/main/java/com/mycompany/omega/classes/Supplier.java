/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.omega.classes;

import java.util.List;

/**
 *
 * @author userr
 */
public class Supplier {
    private final String supplierID;
    private String supplierName;
    private String contact;
    private Item item;
    private double price;

    public Supplier(String supplierID, String supplierName, String contact, Item item, float price) {
        this.supplierID = supplierID;
        this.supplierName = supplierName;
        this.contact = contact;
        this.item = item;
        this.price = price;
    }

    @Override
    public String toString(){
        return supplierID + "," + supplierName + "," + contact + "," + item + "," + price;
    }
    
    public static Supplier fromLine(String line, List<Item> itemList){
        String[] parts = line.split(",");
        String itemID = parts[3];
        
        Item matchedItem = null;
        for (Item i : itemList){
            if(i.getItemID().equals(itemID)){
                matchedItem = i;
                break;
            }
        } 
        
        if(matchedItem == null){
            System.out.println("Item not found: " + itemID);
            return null;
        }
        
        
        return new Supplier (parts[0], //Supplier_ID
            parts[1], //Supplier Name
            parts[2], //Supplier contact
            matchedItem, (float) Double.parseDouble(parts[4]));
            
    }
            
            
    public String getSupplierID() {
        return supplierID;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
    
    
}
