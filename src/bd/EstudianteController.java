/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bd;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelos.Estudiante;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.util.Scanner;

/**
 *
 * @author david
 */
public class EstudianteController extends Hobbies{
    
    public void add(Estudiante dato){
        try {
            // Introducirlos en la base de datos
            PreparedStatement ps;
            ps = getCon().prepareStatement("Insert into Estudiante values (?,?,?,?,?)");
            ps.setInt(1,dato.getRegistro());
            ps.setString(2, dato.getNombre());
            ps.setString(3, dato.getApellidoPaterno());
            ps.setString(4, dato.getApellidoMaterno());
            ps.setInt(5,dato.getEdad());
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public ArrayList<Estudiante> getAll(){
        try {
            ArrayList<Estudiante> lista = new ArrayList<Estudiante>();
            ResultSet rs;
            PreparedStatement ps;
            ps=getCon().prepareStatement("Select * from Estudiante"); // Se pone con interrogación lo que se quiere cambiar
            rs = ps.executeQuery(); // rs es un iterador
            while(rs.next()){ // Verifica que haya algo
                Estudiante x = new Estudiante();
                x.setRegistro(rs.getInt("registro"));
                x.setNombre(rs.getString("nombre"));
                x.setApellidoPaterno(rs.getString("apellidoPaterno"));
                x.setApellidoMaterno(rs.getString("apellidoMaterno"));
                x.setEdad(rs.getInt("edad"));
                lista.add(x);
            }
            return lista;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }
    
    public void delete(int registro) {
        try {
            // Eliminar los registros relacionados en la tabla RelacionEH
            PreparedStatement psRelacionEH;
            psRelacionEH = getCon().prepareStatement("DELETE FROM RelacionEH WHERE Registro = ?");
            psRelacionEH.setInt(1, registro);
            psRelacionEH.executeUpdate();

            // Luego eliminar al estudiante
            PreparedStatement psEstudiante;
            psEstudiante = getCon().prepareStatement("DELETE FROM Estudiante WHERE Registro = ?");
            psEstudiante.setInt(1, registro);
            psEstudiante.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public void update(Estudiante estudiante) {
        try {
            // Preparar la sentencia SQL UPDATE
            PreparedStatement ps;
            ps = getCon().prepareStatement("UPDATE Estudiante SET Nombre = ?, ApellidoPaterno = ?, ApellidoMaterno = ?, Edad = ? WHERE Registro = ?");

            // Establecer los parámetros en la sentencia SQL
            ps.setString(1, estudiante.getNombre());
            ps.setString(2, estudiante.getApellidoPaterno());
            ps.setString(3, estudiante.getApellidoMaterno());
            ps.setInt(4, estudiante.getEdad());
            ps.setInt(5, estudiante.getRegistro());

            // Ejecutar la sentencia SQL
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
