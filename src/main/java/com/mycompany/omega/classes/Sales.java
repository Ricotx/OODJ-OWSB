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
public class Sales {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    private final String saleID;
    private Item item;
    private int quantity;
    private String recordedBy;
    private LocalDate date;

    public Sales (String saleID, Item item, int quantity, String recordedBy, LocalDate date) {
        this.saleID = saleID;
        this.item = item;
        this.quantity = quantity;
        this.recordedBy = recordedBy;
        this.date = date;
    }

    @Override
    public String toString() {
        return saleID + "," +
               item.getItemID() + "," +
               quantity + "," +
               recordedBy + "," +
               date.format(formatter);
    }

    public static Sales fromLine(String line, List<Item> items) {
        String[] parts = line.split(",");
        Item matchedItem = items.stream()
            .filter(i -> i.getItemID().equals(parts[1]))
            .findFirst().orElse(null);

        if (matchedItem == null) return null;

        return new Sales(
            parts[0],
            matchedItem,
            Integer.parseInt(parts[2]),
            parts[3],
            LocalDate.parse(parts[4], formatter)
        );
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

    public String getRecordedBy() {
        return recordedBy;
    }

    public void setRecordedBy(String recordedBy) {
        this.recordedBy = recordedBy;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
    
    
}
