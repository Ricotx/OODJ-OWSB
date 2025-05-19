/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.omega.classes;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 *
 * @author userr
 */
public class PR {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    private final String prID;
    private Item item;
    private int quantity;
    private final LocalDate requestDate;
    private final String requestedBy;
    private String status;

    public PR(String prID, Item item, int quantity, LocalDate requestDate, String requestedBy, String status) {
        this.prID = prID;
        this.item = item;
        this.quantity = quantity;
        this.requestDate = requestDate;
        this.requestedBy = requestedBy;
        this.status = status;
    }
    

    // this to string method is to write into the PR.txt file
    @Override
    public String toString() {
        return prID + "," + item.getItemID() + "," + quantity + "," + requestDate.format(formatter) + "," + requestedBy + "," + status;
    }
    

    public static PR fromLine(String line, List<Item> itemList){
        try{
            String[] parts = line.split(",");
        
        String itemID = parts[1];
        Item matchedItem = null;
        for (Item i : itemList){
            if(i.getItemID().equals(itemID)){
                matchedItem = i;
                break;
            }
        }
        if(matchedItem == null){
            System.out.println("Item not found for PR: " + itemID);
            return null;
        }
        
        return new PR(
            parts[0], //PR_ID
            matchedItem, //Item_ID
            Integer.parseInt(parts[2]), //PR requested Item Quantity
            LocalDate.parse(parts[3], formatter), //PR requested Date in format dd-MM-yyy
            parts[4], //PR requested by which user/sales manager
            parts[5] //PR status, is it approved or not
        );
        } catch (Exception e){
            System.out.println("Invalid PR Line: " + line);
            e.printStackTrace();
            return null;
        }
        
    }

    
    
    public String getPR_ID() {
        return prID;
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

    public LocalDate getRequestdDate() {
        return requestDate;
    }

    public String getRequestedBy() {
        return requestedBy;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    
}
