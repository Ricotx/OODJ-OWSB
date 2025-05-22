/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.omega.classes;

/**
 *
 * @author userr
 */
public class Item {
    private final String itemID;
    private String itemName;
    private int stock;

    
    public Item(String itemID, String itemName, int stock) {
        this.itemID = itemID;
        this.itemName = itemName;
        this.stock = stock;
    }
    
    @Override
    public String toString() {
        
        return itemID + "," + itemName + "," + stock;
    }
    
    public static Item fromLine(String line){
        String[] parts = line.split(",");
        if (parts.length < 3) {
            System.out.println("Skipping invalid Item line: " + line);
            return null;
        }
        return new Item(
            parts[0], //PR_ID
            parts[1], //Item_ID
            Integer.parseInt(parts[2].trim()) //PR requested Item Quantity
        );
    }

    public String getItemID() {
        return itemID;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
    
    
}
