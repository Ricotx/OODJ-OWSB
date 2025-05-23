/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.omega.classes;

import com.mycompany.omega.classes.Employee;
import java.io.*;

public class UserAuth {

    public static Employee authenticate(String inputUsername, String inputPassword) {
    try {
            BufferedReader br = new BufferedReader(new FileReader("data/Employee.txt"));
            String line;

            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 5) {
                    String employeeID = parts[0].trim();
                    String name = parts[1].trim();
                    Employee.Role role = Employee.Role.valueOf(parts[2].trim());
                    String email = parts[3].trim();
                    String password = parts[4].trim();

                    if (email.equals(inputUsername) && password.equals(inputPassword)) {
                        return switch (role) {
                            case PURCHASE_MANAGER -> new PurchaseManager(employeeID, name, role, email, password);
                            case INVENTORY_MANAGER -> new InventoryManager(employeeID, name, role, email, password);
                            
                            case FINANCE_MANAGER -> new FinanceManager(employeeID, name, role, email, password);
                            case ADMINISTRATOR -> new Admin(employeeID, name, role, email, password);
                            case SALES_MANAGER -> new SalesManager(employeeID, name, role, email, password);
                                
                            // Add others like:
                            // case ADMINISTRATOR -> new Administrator(...);
                            default -> null;
                        };
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null; // Login failed
    }
}
