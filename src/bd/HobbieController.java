/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bd;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import modelos.Estudiante;
import modelos.Hobbie;

/**
 *
 * @author david
 */
public class HobbieController extends Hobbies{
    
    public void add(Hobbie dato){
        try {
            // Introducirlos en la base de datos
            PreparedStatement ps;
            ps = getCon().prepareStatement("Insert into Hobbie values (?,?,?)");
            ps.setInt(1,dato.getId());
            ps.setString(2, dato.getHobbie());
            ps.setString(3, dato.getDescripcion());
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public ArrayList<Hobbie> getAll(){
        try {
            ArrayList<Hobbie> lista = new ArrayList<Hobbie>();
            ResultSet rs;
            PreparedStatement ps;
            ps=getCon().prepareStatement("Select * from Hobbie"); // Se pone con interrogación lo que se quiere cambiar
            rs = ps.executeQuery(); // rs es un iterador
            while(rs.next()){ // Verifica que haya algo
                Hobbie x = new Hobbie();
                x.setId(rs.getInt("id"));
                x.setHobbie(rs.getString("hobbie"));
                x.setDescripcion(rs.getString("descripcion"));
                lista.add(x);
            }
            return lista;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }
    
    public void delete(int id) {
        try {
            // Eliminar los registros relacionados en la tabla RelacionEH
            PreparedStatement psRelacionEH;
            psRelacionEH = getCon().prepareStatement("DELETE FROM RelacionEH WHERE idHobbie = ?");
            psRelacionEH.setInt(1, id);
            psRelacionEH.executeUpdate();

            // Luego eliminar al hobbie
            PreparedStatement psHobbie;
            psHobbie = getCon().prepareStatement("DELETE FROM Hobbie WHERE id = ?");
            psHobbie.setInt(1, id);
            psHobbie.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public void update(Hobbie hobbie) {
        try {
            // Preparar la sentencia SQL UPDATE
            PreparedStatement ps;
            ps = getCon().prepareStatement("UPDATE Hobbie SET Hobbie = ?, Descripcion = ? WHERE Id = ?");

            // Establecer los parámetros en la sentencia SQL
            ps.setString(1, hobbie.getHobbie());
            ps.setString(2, hobbie.getDescripcion());
            ps.setInt(3, hobbie.getId());

            // Ejecutar la sentencia SQL
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
