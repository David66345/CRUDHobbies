/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vistas;

import bd.HobbieController;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import modelos.Hobbie;

/**
 *
 * @author david
 */
public class RegistrarHobbies extends JPanel {

     private JTextField txtHobbies;
    private JTextArea txtDescripcion;

    public RegistrarHobbies() {
        super();
        initComponents();
    }

    private void initComponents() {
        JPanel panel = new JPanel();
        GroupLayout layout = new GroupLayout(panel);
        panel.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        JLabel lblHobbies = new JLabel("Hobbie:");
        JLabel lblDescripcion = new JLabel("Descripción:");

        txtHobbies = new JTextField(10);
        txtDescripcion = new JTextArea(5, 20);
        JScrollPane scrollPane = new JScrollPane(txtDescripcion);

        JButton btnRegistrar = new JButton("Registrar");
        btnRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registrarHobbie();
            }
        });

        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(lblHobbies)
                        .addComponent(lblDescripcion))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(txtHobbies)
                        .addComponent(scrollPane)
                        .addComponent(btnRegistrar))
        );

        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(lblHobbies)
                        .addComponent(txtHobbies))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(lblDescripcion)
                        .addComponent(scrollPane))
                .addComponent(btnRegistrar)
        );

        add(panel);
    }
    
    private void registrarHobbie() {
        if(txtHobbies.getText().isEmpty() || txtDescripcion.getText().isEmpty()){
            JOptionPane.showMessageDialog(this, "No puede dejar espacios vacios", "Datos Invalidos", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        
        Hobbie hobbie = new Hobbie();
        HobbieController hobbieController = new HobbieController(); 
        
        hobbie.setHobbie(txtHobbies.getText());
        hobbie.setDescripcion(txtDescripcion.getText());
        
        hobbieController.add(hobbie);
        
        mostrarDatosIngresados(hobbie);
    }
    
    private void mostrarDatosIngresados(Hobbie hobbie) {

        JOptionPane.showMessageDialog(this, "Hobbie: " + hobbie.getHobbie() + "\nDescripción: " + hobbie.getDescripcion(), "Datos Ingresados", JOptionPane.INFORMATION_MESSAGE);
    }
}
