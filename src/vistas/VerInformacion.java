/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vistas;

import bd.EstudianteController;
import bd.HobbieController;
import bd.RelacionEHController;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.DefaultCellEditor;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.LayoutStyle;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import modelos.Estudiante;
import modelos.Hobbie;
import modelos.RelacionEH;

/**
 *
 * @author david
 */
public class VerInformacion extends JPanel {
    
    private JTable tblInformacion;
    private DefaultTableModel tableModel;
    private JButton btnEliminar;
    private JButton btnActualizar;

    public VerInformacion() {
        super();
        initComponents();
        cargarDatos(); 
    }
    
    private void initComponents() {
        JPanel panel = new JPanel();
        GroupLayout layout = new GroupLayout(panel);
        panel.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        JLabel lblTitulo = new JLabel("Información de Estudiantes y sus Hobbies");

        tableModel = new DefaultTableModel();
        tableModel.addColumn("Código");
        tableModel.addColumn("Registro");
        tableModel.addColumn("Nombre");
        tableModel.addColumn("Apellido Paterno");
        tableModel.addColumn("Apellido Materno");
        tableModel.addColumn("Edad");
        tableModel.addColumn("Hobbies");
        tableModel.addColumn("Tiempo dedicado");

        tblInformacion = new JTable(tableModel);
        tblInformacion.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        JScrollPane scrollPane = new JScrollPane(tblInformacion);

        btnEliminar = new JButton("Eliminar");
        btnEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarRegistro();
            }
        });

        btnActualizar = new JButton("Actualizar");
        btnActualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarTabla();
            }
        });
        
        tblInformacion.getModel().addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                if (e.getType() == TableModelEvent.UPDATE) { // Verifica si el evento es de actualización
                    int fila = e.getFirstRow();
                    int columna = e.getColumn();
                    TableModel model = (TableModel) e.getSource();
                    Object nuevoValor = model.getValueAt(fila, columna);
                    
                    actualizarTabla();
                }
            }
        });

        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addComponent(lblTitulo)
                .addComponent(scrollPane)
                .addGroup(layout.createSequentialGroup()
                        .addComponent(btnEliminar)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnActualizar))
        );

        layout.setVerticalGroup(layout.createSequentialGroup()
                .addComponent(lblTitulo)
                .addComponent(scrollPane)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(btnEliminar)
                        .addComponent(btnActualizar))
        );

        add(panel);
        
        // Configuración del editor de celdas para la columna de Hobbies
        JComboBox<String> comboBox = new JComboBox<>();
        HobbieController hobbieController = new HobbieController();
        ArrayList<Hobbie> hobbies = hobbieController.getAll();
        for (Hobbie hobbie : hobbies) {
            comboBox.addItem(hobbie.getHobbie());
        }
        tblInformacion.getColumnModel().getColumn(6).setCellEditor(new DefaultCellEditor(comboBox));
    }

    private void cargarDatos() {
        RelacionEHController relacionEHController = new RelacionEHController();
        
        ArrayList<RelacionEH> relaciones = relacionEHController.getAllWithDetails();
        
        for (RelacionEH relacion : relaciones) {
                Object[] rowData = {
                        relacion.getCodigo(),
                        relacion.getRegistro(),
                        relacion.getEstudiante().getNombre(),
                        relacion.getEstudiante().getApellidoPaterno(),
                        relacion.getEstudiante().getApellidoMaterno(),
                        relacion.getEstudiante().getEdad(),
                        relacion.getHobbie().getHobbie(),
                        relacion.getTiempo()
                    };
                    tableModel.addRow(rowData);
            }
        
        //EstudianteController estudianteController = new EstudianteController();
        //HobbieController hobbieController = new HobbieController();
        
        //ArrayList<Estudiante> estudiantes = estudianteController.getAll();
        //ArrayList<Hobbie> hobbies = hobbieController.getAll();

        /*for (Estudiante estudiante : estudiantes) {
            for (Hobbie hobbie : hobbies){
                for (RelacionEH relacion : relaciones) {
                if (estudiante.getRegistro() == relacion.getRegistro() && hobbie.getId() == relacion.getIdHobbie()) {
                    Object[] rowData = {
                        estudiante.getRegistro(),
                        estudiante.getNombre(),
                        estudiante.getApellidoPaterno(),
                        estudiante.getApellidoMaterno(),
                        estudiante.getEdad(),
                        hobbie.getHobbie(),
                        relacion.getTiempo()
                    };
                    tableModel.addRow(rowData);
                }
            }
            }
        }*/
    }
    
    private void eliminarRegistro() {
        int selectedRow = tblInformacion.getSelectedRow();
        if (selectedRow != -1) {
            int codigo = (int) tblInformacion.getValueAt(selectedRow, 0); 
            RelacionEHController relacionEHController = new RelacionEHController();
            relacionEHController.delete(codigo); 

            tableModel.removeRow(selectedRow); 
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione una relacion para eliminar.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void actualizarTabla() {
        int selectedRow = tblInformacion.getSelectedRow();
        if (selectedRow != -1) {
            
            Object codigoObject = tableModel.getValueAt(selectedRow, 0);
            
            Object registroObject = tableModel.getValueAt(selectedRow, 1);
            Object nombreObject = tableModel.getValueAt(selectedRow, 2);
            Object apellidoPaternoObject = tableModel.getValueAt(selectedRow, 3);
            Object apellidoMaternoObject = tableModel.getValueAt(selectedRow, 4);
            Object edadObject = tableModel.getValueAt(selectedRow, 5);
           
            Object hobbieObject = tableModel.getValueAt(selectedRow, 6);
            
            Object tiempoObject = tableModel.getValueAt(selectedRow, 7);
            
            if(codigoObject == null || registroObject == null || nombreObject== null || apellidoPaternoObject== null || apellidoMaternoObject== null
                    || edadObject== null || hobbieObject== null || tiempoObject== null){
                JOptionPane.showMessageDialog(this, "No puede dejar espacios vacios", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            String codigoString = tableModel.getValueAt(selectedRow, 0).toString();
            String registroString = tableModel.getValueAt(selectedRow, 1).toString();
            String nombre = tableModel.getValueAt(selectedRow, 2).toString();
            String apellidoPaterno = tableModel.getValueAt(selectedRow, 3).toString();
            String apellidoMaterno = tableModel.getValueAt(selectedRow, 4).toString();
            String edadString = tableModel.getValueAt(selectedRow, 5).toString();
            
            String hobbieSeleccionado  = tableModel.getValueAt(selectedRow, 6).toString();
            
            String tiempoString = tableModel.getValueAt(selectedRow, 7).toString();
            
            int codigo = Integer.parseInt(codigoString);
            int registro = Integer.parseInt(registroString);
            int edad = Integer.parseInt(edadString);
            int tiempo = Integer.parseInt(tiempoString);
            
            int idHobbieSeleccionado = obtenerIdHobbie(hobbieSeleccionado);
            System.out.println(idHobbieSeleccionado);
            
            // Crear un nuevo objeto Estudiante con los nuevos datos
            Estudiante estudiante = new Estudiante(registro, nombre, apellidoPaterno, apellidoMaterno, edad);
            RelacionEH relacion = new RelacionEH(codigo, registro,idHobbieSeleccionado,tiempo);
            
            // Llamar al método update en el controlador EstudianteController
            EstudianteController estudianteController = new EstudianteController();
            estudianteController.update(estudiante);

            RelacionEHController relacionEHController = new RelacionEHController();
            relacionEHController.update(relacion);
            
            JOptionPane.showMessageDialog(this, "Datos Actualizados con exito", "Datos Actualizados", JOptionPane.INFORMATION_MESSAGE);

            // Actualizar la tabla después de la actualización
            refreshScreen();
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione un hobbie para actualizar.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private int obtenerIdHobbie(String nombreHobbie) {
        HobbieController hobbieController = new HobbieController();
        ArrayList<Hobbie> hobbies = hobbieController.getAll();

        for (Hobbie hobbie : hobbies) {
            if (hobbie.getHobbie().equals(nombreHobbie)) {
                return hobbie.getId();
            }
        }

        return -1; 
    }
    
    private void refreshScreen() {
        removeAll(); 
        initComponents(); 
        cargarDatos(); 
        revalidate(); 
        repaint(); 
    }
}
