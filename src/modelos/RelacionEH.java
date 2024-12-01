/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos;

import bd.EstudianteController;
import bd.HobbieController;
import bd.RelacionEHController;
import java.util.ArrayList;

/**
 *
 * @author david
 */
public class RelacionEH {
    
    // Campos de la tabla RelacionEH
    private int codigo;
    private int registro;
    private int idHobbie;
    private int tiempo;
    private Estudiante estudiante;
    private Hobbie hobbie;
    
    public RelacionEH(){
        
    }

    public RelacionEH(int codigo, int registro, int idHobbie, int tiempo) {
        this.codigo = codigo;
        this.registro = registro;
        this.idHobbie = idHobbie;
        this.tiempo = tiempo;
    }

    public RelacionEH(int codigo, int registro, int idHobbie, int tiempo, Estudiante estudiante, Hobbie hobbie) {
        this.codigo = codigo;
        this.registro = registro;
        this.idHobbie = idHobbie;
        this.tiempo = tiempo;
        this.estudiante = estudiante;
        this.hobbie = hobbie;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public int getRegistro() {
        return registro;
    }

    public void setRegistro(int registro) {
        this.registro = registro;
    }

    public int getIdHobbie() {
        return idHobbie;
    }

    public void setIdHobbie(int idHobbie) {
        this.idHobbie = idHobbie;
    }

    public int getTiempo() {
        return tiempo;
    }

    public void setTiempo(int tiempo) {
        this.tiempo = tiempo;
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }

    public Hobbie getHobbie() {
        return hobbie;
    }

    public void setHobbie(Hobbie hobbie) {
        this.hobbie = hobbie;
    }
    
    
    
}
