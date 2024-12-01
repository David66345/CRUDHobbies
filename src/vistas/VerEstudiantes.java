/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vistas;

import bd.EstudianteController;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.GroupLayout;
import javax.swing.JButton;
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

/**
 *
 * @author david
 */
public class VerEstudiantes extends JPanel {
    
    private JTable tblEstudiantes;
    private DefaultTableModel tableModel;
    private JButton btnEliminar;
    private JButton btnActualizar;

    public VerEstudiantes() {
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

        JLabel lblTitulo = new JLabel("Lista de Estudiantes");

        tableModel = new DefaultTableModel();
        tableModel.addColumn("Registro");
        tableModel.addColumn("Nombre");
        tableModel.addColumn("Apellido Paterno");
        tableModel.addColumn("Apellido Materno");
        tableModel.addColumn("Edad");

        tblEstudiantes = new JTable(tableModel);
        tblEstudiantes.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        JScrollPane scrollPane = new JScrollPane(tblEstudiantes);

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
                actualizarEstudiante();
            }
        });
        
        tblEstudiantes.getModel().addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                if (e.getType() == TableModelEvent.UPDATE) { // Verifica si el evento es de actualización
                    int fila = e.getFirstRow();
                    int columna = e.getColumn();
                    TableModel model = (TableModel) e.getSource();
                    Object nuevoValor = model.getValueAt(fila, columna);
                    
                    actualizarEstudiante();
                }
            }
        });

        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addComponent(lblTitulo)
                .addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE) // Permitir expansión horizontal
                .addGroup(layout.createSequentialGroup()
                        .addComponent(btnEliminar)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
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
    }

    private void cargarDatos() {
        EstudianteController estudianteController = new EstudianteController();
        
        ArrayList<Estudiante> estudiantes = estudianteController.getAll();

        for ( Estudiante estudiante : estudiantes) {
            Object[] rowData = {
                estudiante.getRegistro(),
                estudiante.getNombre(),
                estudiante.getApellidoPaterno(),
                estudiante.getApellidoMaterno(),
                estudiante.getEdad()
            };
            tableModel.addRow(rowData);
        }
    }
    
    private void eliminarRegistro() {
        int selectedRow = tblEstudiantes.getSelectedRow();
        if (selectedRow != -1) {
            int registro = (int) tblEstudiantes.getValueAt(selectedRow, 0); // Obtener el registro del estudiante seleccionado
            EstudianteController estudianteController = new EstudianteController();
            estudianteController.delete(registro); // Eliminar el estudiante de la base de datos

            tableModel.removeRow(selectedRow); // Eliminar el estudiante de la tabla
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione un estudiante para eliminar.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void actualizarEstudiante() {
        int selectedRow = tblEstudiantes.getSelectedRow();
        if (selectedRow != -1) {
            // Recopilar los datos del estudiante desde la tabla
            
            Object registroObject = tableModel.getValueAt(selectedRow, 0);
            Object nombreObject = tableModel.getValueAt(selectedRow, 1);
            Object apellidoPaternoObject = tableModel.getValueAt(selectedRow, 2);
            Object apellidoMaternoObject = tableModel.getValueAt(selectedRow, 3);
            Object edadObject = tableModel.getValueAt(selectedRow, 4);
            
            if(registroObject == null || nombreObject== null || apellidoPaternoObject== null || apellidoMaternoObject== null
                    || edadObject== null){
                JOptionPane.showMessageDialog(this, "No puede dejar espacios vacios", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            String registroString = tableModel.getValueAt(selectedRow, 0).toString();
            String nombre = tableModel.getValueAt(selectedRow, 1).toString();
            String apellidoPaterno = tableModel.getValueAt(selectedRow, 2).toString();
            String apellidoMaterno = tableModel.getValueAt(selectedRow, 3).toString();
            String edadString = tableModel.getValueAt(selectedRow, 4).toString();
            
            int registro = Integer.parseInt(registroString);
            int edad = Integer.parseInt(edadString);

            // Crear un nuevo objeto Estudiante con los nuevos datos
            Estudiante estudiante = new Estudiante(registro, nombre, apellidoPaterno, apellidoMaterno, edad);

            // Llamar al método update en el controlador EstudianteController
            EstudianteController estudianteController = new EstudianteController();
            estudianteController.update(estudiante);
            JOptionPane.showMessageDialog(this, "Datos Actualizados con exito", "Datos Actualizados", JOptionPane.INFORMATION_MESSAGE);

            // Actualizar la tabla después de la actualización
            refreshScreen();
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione un estudiante para actualizar.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void refreshScreen() {
        removeAll(); // Elimina todos los componentes de la pantalla actual
        initComponents(); // Vuelve a inicializar los componentes
        cargarDatos(); // Vuelve a cargar los datos en la tabla
        revalidate(); // Vuelve a validar la disposición de los componentes
        repaint(); // Vuelve a pintar la pantalla
    }
}
