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
import modelos.RelacionEH;

/**
 *
 * @author david
 */
public class RelacionEHController extends Hobbies{
    
    public void add(RelacionEH dato){
        try {
            // Introducirlos en la base de datos
            PreparedStatement ps;
            ps = getCon().prepareStatement("Insert into RelacionEH values (?,?,?,?)");
            ps.setInt(1,dato.getCodigo());
            ps.setInt(2, dato.getRegistro());
            ps.setInt(3, dato.getIdHobbie());
            ps.setInt(4, dato.getTiempo());
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public ArrayList<RelacionEH> getAll(){
        try {
            ArrayList<RelacionEH> lista = new ArrayList<RelacionEH>();
            ResultSet rs;
            PreparedStatement ps;
            ps=getCon().prepareStatement("Select * from RelacionEH"); // Se pone con interrogación lo que se quiere cambiar
            rs = ps.executeQuery(); // rs es un iterador
            while(rs.next()){ // Verifica que haya algo
                RelacionEH x = new RelacionEH();
                x.setCodigo(rs.getInt("codigo"));
                x.setRegistro(rs.getInt("registro"));
                x.setIdHobbie(rs.getInt("idHobbie"));
                x.setTiempo(rs.getInt("tiempo"));
                lista.add(x);
            }
            return lista;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }
    
    public ArrayList<RelacionEH> getAllWithDetails() {
        try {
            ArrayList<RelacionEH> lista = new ArrayList<>();
            PreparedStatement ps;
            ps = getCon().prepareStatement("SELECT r.codigo, r.registro, r.idHobbie, r.tiempo, e.nombre, e.apellidoPaterno, e.apellidoMaterno, e.edad, h.hobbie "
                                       + "FROM RelacionEH r "
                                       + "INNER JOIN Estudiante e ON r.registro = e.registro "
                                       + "INNER JOIN Hobbie h ON r.idHobbie = h.id "
                                       + "ORDER BY r.codigo");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                RelacionEH relacionEH = new RelacionEH();
                relacionEH.setCodigo(rs.getInt("codigo"));
                relacionEH.setRegistro(rs.getInt("registro"));
                relacionEH.setIdHobbie(rs.getInt("idHobbie"));
                relacionEH.setTiempo(rs.getInt("tiempo"));
                relacionEH.setEstudiante(new Estudiante(rs.getString("nombre"), rs.getString("apellidoPaterno"), rs.getString("apellidoMaterno"), rs.getInt("edad")));
                relacionEH.setHobbie(new Hobbie(rs.getString("hobbie")));
                lista.add(relacionEH);
            }
            return lista;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }
    
    public void delete(int codigo) {
        try {
            
            PreparedStatement psRelacionEH;
            psRelacionEH = getCon().prepareStatement("DELETE FROM RelacionEH WHERE Codigo = ?");
            psRelacionEH.setInt(1, codigo);
            psRelacionEH.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public void update(RelacionEH relacion) {
        try {
            PreparedStatement ps;
            ps = getCon().prepareStatement("UPDATE RelacionEH SET Registro = ?, IdHobbie = ?, Tiempo = ? WHERE Codigo = ?");
            ps.setInt(1, relacion.getRegistro());
            ps.setInt(2, relacion.getIdHobbie());
            ps.setInt(3, relacion.getTiempo());
            ps.setInt(4, relacion.getCodigo());
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
