/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.omega.classes;

import com.mycompany.omega.classes.Employee;
import java.io.*;
import java.util.List;
import java.util.ArrayList;

public class UserAuth {

    public static Employee authenticate(String inputUsername, String inputPassword) {
    try { 
            BufferedReader br = new BufferedReader(new FileReader("data/Employee.txt"));
            String line;

            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 5) {
                    String EmployeeID = parts[0].trim();
                    String name = parts[1].trim();
                    Employee.Role role = Employee.Role.valueOf(parts[2].trim());
                    String email = parts[3].trim();
                    String password = parts[4].trim();

                    if (email.equals(inputUsername) && password.equals(inputPassword)) {
                        return switch (role) {
                            case PURCHASE_MANAGER -> new PurchaseManager(EmployeeID, name, role, email, password);
                            case SALES_MANAGER -> new SalesManager(EmployeeID, name, role, email, password);
                            case INVENTORY_MANAGER -> new InventoryManager(EmployeeID, name, role, email, password);
                            case FINANCE_MANAGER -> new FinanceManager(EmployeeID, name, role, email, password);
                            case ADMINISTRATOR ->  new Admin(EmployeeID, name, role, email, password);
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
    public static boolean forgotPassword(String email, String newPassword) {
    File file = new File("data/Employee.txt");
    List<String> updatedUsers = new ArrayList<>();
    boolean found = false;

    try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
        String line;

        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(",");
            if (parts.length == 5 && parts[3].equalsIgnoreCase(email)) {
                parts[4] = newPassword; // Update password
                line = String.join(",", parts);
                found = true;
            }
            updatedUsers.add(line);
        }

    } catch (IOException e) {
        e.printStackTrace();
        return false;
    }

    if (found) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (String user : updatedUsers) {
                writer.write(user);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    return found;
}
}
