/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos;

/**
 *
 * @author david
 */
public class Hobbie {
    
    // Campos de la tabla Hobbie
    private int id;
    private String hobbie;
    private String descripcion;
    
    public Hobbie(){
        
    }

    public Hobbie(int id, String hobbie, String descripcion) {
        this.id = id;
        this.hobbie = hobbie;
        this.descripcion = descripcion;
    }

    public Hobbie(String hobbie) {
        this.hobbie = hobbie;
    }
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHobbie() {
        return hobbie;
    }

    public void setHobbie(String hobbie) {
        this.hobbie = hobbie;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
