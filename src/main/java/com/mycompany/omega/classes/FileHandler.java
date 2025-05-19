package com.mycompany.omega.classes;

import java.io.*;
import static java.nio.file.Files.lines;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author userr
 */
public class FileHandler {
    public static <T> List<T> loadFromFile(String filePath, Function<String, T> parser){
        List<T> list = new ArrayList<>();
        File file = new File(filePath);
            
        if (!file.exists()){
            try{
                file.getParentFile().mkdirs();
                file.createNewFile();
            }catch(IOException e){
                System.out.println("Error creating file: " + e.getMessage());
            }
        }
        
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                T obj = parser.apply(line);
                if (obj != null) {
                    list.add(obj);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
            e.printStackTrace();
        }
        
        return list;      
    }
    
    public static void appendLine(String filePath, String line){
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))){
            writer.write(line);
            writer.newLine();
        }catch(IOException e){
            System.out.println("Error appending to file: " + e.getMessage());
        }
    }
    
    public static <T> void writeAllToFile(String filePath,List<T> list){
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))){
            for (T obj : list){
                writer.write(obj.toString());
                writer.newLine();
            }
        }catch (IOException e){
            System.out.println("Error editing file: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    public static String getNextID(String filePath, String prefix){
        int max = 0;
        File file = new File(filePath);
        
        if(!file.exists()){
            return prefix + "001";
        }
        try(BufferedReader reader = new BufferedReader(new FileReader(file))){
            Iterator<String> iterator = reader.lines().iterator();
            
            while (iterator.hasNext()){
                String line = iterator.next();
                if (line.startsWith(prefix)){
                    String[] parts = line.split(",");
                    String idPart = parts[0].substring(prefix.length());
                    int num = Integer.parseInt(idPart);
                    if(num>max){
                        max = num;
                    }
                }
            }
            
        }catch (IOException e){
            System.out.println("Error reading file for ID: " + e.getMessage());
            e.printStackTrace();
        }
        return prefix + String.format("%03d", max + 1);
    }
}
