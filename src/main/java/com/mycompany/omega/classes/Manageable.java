/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.omega.classes;


/**
 *
 * @author userr
 * @param <T>
 */
public interface Manageable<T> {
    
    void add(T obj);
    void edit(T obj);
    void delete(T obj);
}
