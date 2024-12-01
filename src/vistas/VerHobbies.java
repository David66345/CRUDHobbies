/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vistas;

import bd.EstudianteController;
import bd.HobbieController;
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
import modelos.Hobbie;

/**
 *
 * @author david
 */
public class VerHobbies extends JPanel {
    
    private JTable tblHobbies;
    private DefaultTableModel tableModel;
    private JButton btnEliminar;
    private JButton btnActualizar;

    public VerHobbies() {
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

        JLabel lblTitulo = new JLabel("Lista de Hobbies");

        tableModel = new DefaultTableModel();
        tableModel.addColumn("Identificador");
        tableModel.addColumn("Nombre");
        tableModel.addColumn("Descripción");

        tblHobbies = new JTable(tableModel);
        tblHobbies.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        JScrollPane scrollPane = new JScrollPane(tblHobbies);

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
        
        tblHobbies.getModel().addTableModelListener(new TableModelListener() {
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
        
        HobbieController hobbieController = new HobbieController();
        
        ArrayList<Hobbie> hobbies = hobbieController.getAll();

        for ( Hobbie hobbie : hobbies) {
            Object[] rowData = {
                hobbie.getId(),
                hobbie.getHobbie(),
                hobbie.getDescripcion(),
            };
            tableModel.addRow(rowData);
        }
    }
    
    private void eliminarRegistro() {
        int selectedRow = tblHobbies.getSelectedRow();
        if (selectedRow != -1) {
            int id = (int) tblHobbies.getValueAt(selectedRow, 0); 
            HobbieController hobbieController = new HobbieController();
            hobbieController.delete(id); 

            tableModel.removeRow(selectedRow); 
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione un hobbie para eliminar.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void actualizarTabla() {
        int selectedRow = tblHobbies.getSelectedRow();
        if (selectedRow != -1) {
            // Recopilar los datos del estudiante desde la tabla
            
            Object idObject = tableModel.getValueAt(selectedRow, 0);
            Object hobbieObject = tableModel.getValueAt(selectedRow, 1);
            Object descripcionObject = tableModel.getValueAt(selectedRow, 2);
            
            if(idObject == null || hobbieObject== null || descripcionObject== null){
                JOptionPane.showMessageDialog(this, "No puede dejar espacios vacios", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            String idString = tableModel.getValueAt(selectedRow, 0).toString();
            String hobbieString = tableModel.getValueAt(selectedRow, 1).toString();
            String descripcion = tableModel.getValueAt(selectedRow, 2).toString();
            
            int id = Integer.parseInt(idString);

            // Crear un nuevo objeto Estudiante con los nuevos datos
            Hobbie hobbie = new Hobbie (id, hobbieString, descripcion);

            HobbieController hobbieController = new HobbieController();
            hobbieController.update(hobbie);
            JOptionPane.showMessageDialog(this, "Datos Actualizados con exito", "Datos Actualizados", JOptionPane.INFORMATION_MESSAGE);

            // Actualizar la tabla después de la actualización
            refreshScreen();
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione un hobbie para actualizar.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void refreshScreen() {
        removeAll(); 
        initComponents(); 
        cargarDatos(); 
        revalidate(); 
        repaint(); 
    }
}
