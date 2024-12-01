/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package crudhobbies;

import bd.Hobbies;
import vistas.Ventana;

/**
 *
 * @author david
 */
public class CRUDHobbies {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Hobbies hobbies = new Hobbies();
        new Ventana().setVisible(true);
    }
    
}
