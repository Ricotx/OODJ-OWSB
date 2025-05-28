/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.omega.classes;

/**
 *
 * @author userr
 */
public abstract class Employee {
   public enum Role{
       ADMINISTRATOR,
    SALES_MANAGER,
    PURCHASE_MANAGER,
    FINANCE_MANAGER,
    INVENTORY_MANAGER
   } 
   
   protected final String employeeID;
   protected String name;
   protected Role role;
   protected String email;
   protected String password;

    public Employee(String employeeID, String name, Role role, String email, String password) {
        this.employeeID = employeeID;
        this.name = name;
        this.role = role;
        this.email = email;
        this.password = password;
    }

   @Override
    public String toString(){
        return employeeID + "," + name + "," + role + "," + email + "," + password;
    }
    
    public static Employee fromLine(String line){
        String[] parts = line.split(",");
        String id = parts[0];
        String name = parts[1];
        Role role = Role.valueOf(parts[2]);
        String email = parts[3];
        String password = parts[4];
        
        return switch (role){
            case SALES_MANAGER -> new SalesManager(id, name, role, email, password);
            case PURCHASE_MANAGER -> new PurchaseManager(id, name, role, email, password);
            //case FINANCE_MANAGER -> new FinanceManager(id, name, role, email, password);
            //case INVENTORY_MANAGER -> new InventoryManager(id, name, role, email, password);
            //case ADMINISTRATOR -> new Administrator(id, name, role, email, password);
            default -> throw new IllegalArgumentException("Unsupported: " + role);
        };
    }
    
    public String getEmployeeID() {
        return employeeID;
    }

    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public abstract void launchDashboard(); 
}
