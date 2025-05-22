/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.omega.classes;

import com.mycompany.omega.AdminFrame;
import com.mycompany.omega.classes.Employee;

/**
 *
 * @author Lee Anwen
 */
public class Admin extends Employee{

     public Admin(String employeeID, String name, Role role, String email, String password) {
        super(employeeID, name, role, email, password);
    }
     

     @Override
    public void launchDashboard() {
        new AdminFrame().setVisible(true);
        
    }
}
