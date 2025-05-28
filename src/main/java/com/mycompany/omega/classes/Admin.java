/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.omega.classes;

import com.mycompany.omega.AdminFrame;
import com.mycompany.omega.classes.Employee;
import static com.mycompany.omega.classes.Employee.Role.FINANCE_MANAGER;
import static com.mycompany.omega.classes.Employee.Role.INVENTORY_MANAGER;
import static com.mycompany.omega.classes.Employee.Role.SALES_MANAGER;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JOptionPane;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;


public class Admin extends Employee{

     public Admin(String EmployeeID, String name, Role role, String email, String password) {
        super(EmployeeID, name, role, email, password);
    }


   public void registerUser(String id, String name, String roleStr, String email, String password) {
    Role role;
    try {
        role = Role.valueOf(roleStr.toUpperCase());
    } catch (IllegalArgumentException e) {
        JOptionPane.showMessageDialog(null, "Invalid role: " + roleStr);
        return;
    }

    Employee user;

    switch (role) {
        case ADMINISTRATOR -> user = new Admin(id, name, role, email, password);
        case PURCHASE_MANAGER -> user = new PurchaseManager(id, name, role, email, password);
        case SALES_MANAGER -> user = new SalesManager (id, name, role, email, password);
        case INVENTORY_MANAGER -> user = new InventoryManager(id, name, role, email, password);
        case FINANCE_MANAGER -> user = new FinanceManager(id, name, role, email, password);
        default -> {
            JOptionPane.showMessageDialog(null, "Unsupported role: " + role);
            return;
        }
    }

    // Check for duplicates and write to file
    File file = new File("data/Employee.txt");

    try {
        if (file.exists()) {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String[] parts = scanner.nextLine().split(",");
                if (parts.length >= 5 && (parts[0].equals(user.getEmployeeID()) || parts[3].equalsIgnoreCase(user.getEmail()))) {
                    JOptionPane.showMessageDialog(null, "Employee ID or Email already exists.");
                    scanner.close();
                    return;
                }
            }
            scanner.close();
        }

        FileWriter writer = new FileWriter(file, true);
        writer.write("\n" + user.getEmployeeID() + "," + user.getName() + "," + user.getRole() + "," + user.getEmail() + "," + user.getPassword());
        writer.close();

        JOptionPane.showMessageDialog(null, "User registered successfully!");

    } catch (IOException e) {
        JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        e.printStackTrace();
    }
}

    public List<String[]> getAllUserData() {
    List<String[]> rowData = new ArrayList<>();
    File file = new File("data/Employee.txt");

    try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
        String line;
        while ((line = reader.readLine()) != null) {
            String[] data = line.split(",");
            if (data.length == 5) {
                rowData.add(data);
            }
        }
    } catch (IOException e) {
        JOptionPane.showMessageDialog(null, "Error reading user data file: " + e.getMessage());
        e.printStackTrace();
    }

    return rowData;
}
public boolean deleteUserById(String employeeId) {
    File file = new File("data/Employee.txt");
    List<String> updatedUsers = new ArrayList<>();
    boolean found = false;

    try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
        String line;
        while ((line = reader.readLine()) != null) {
            String[] data = line.split(",");
            if (data.length > 0 && data[0].equals(employeeId)) {
                found = true; // skip this user
            } else {
                updatedUsers.add(line);
            }
        }
    } catch (IOException e) {
        JOptionPane.showMessageDialog(null, "Error reading the file.");
        return false;
    }

    if (!found) {
        return false;
    }

    try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
        for (String user : updatedUsers) {
            writer.write(user);
            writer.newLine();
        }
    } catch (IOException e) {
        JOptionPane.showMessageDialog(null, "Error writing to the file.");
        return false;
    }

    return true;
}

    

     @Override
    public void launchDashboard() {
        new AdminFrame().setVisible(true);
        
    }
}
