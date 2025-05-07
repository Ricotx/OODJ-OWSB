/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.omega.classes;
import java.util.List;
/**
 *
 * @author userr
 * @param <T>
 */
public interface Viewable<T> {
    List<T> viewAll();
    T viewById (String id);
}
