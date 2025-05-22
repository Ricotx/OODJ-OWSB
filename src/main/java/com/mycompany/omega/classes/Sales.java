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

    public static Sales fromLine(String line, List<Item> items, List<Employee> employees) {
        String[] parts = line.split(",");
        Item matchedItem = items.stream()
            .filter(i -> i.getItemID().equals(parts[1]))
            .findFirst().orElse(null);

        //Employee matchedEmp = employees.stream()
            //.filter(e -> e.getEmployeeID().equals(parts[3]))
            //.findFirst().orElse(null);

        //if (matchedItem == null || matchedEmp == null) return null;
        if (matchedItem == null) return null;

        return new Sales(
            parts[0],
            matchedItem,
            Integer.parseInt(parts[2]),
            parts[3],
            LocalDate.parse(parts[4], formatter)
        );
    }
}
