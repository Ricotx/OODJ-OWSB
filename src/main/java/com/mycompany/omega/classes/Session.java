/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.omega.classes;

/**
 *
 * @author userr
 */
public class Session {
    private static Employee currentUser;
    
    public static void setCurrentUser(Employee user){
        currentUser = user;
    }
    
    public static Employee getCurrentUser(){
        return currentUser;
    }
    
    public static boolean isLoggedIn(){
        return currentUser != null;
    }
    
    public static void clear(){
        currentUser = null;
    }
}
