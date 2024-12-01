/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vistas;

import bd.EstudianteController;
import bd.HobbieController;
import bd.RelacionEHController;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import modelos.Estudiante;
import modelos.Hobbie;
import modelos.RelacionEH;

/**
 *
 * @author david
 */
public class RegistrarEstudiante extends JPanel {

    private JTextField txtRegistro;
    private JTextField txtNombre;
    private JTextField txtApellidoPaterno;
    private JTextField txtApellidoMaterno;
    private JTextField txtEdad;
    private JTextField txtTiempoDedicado;
    private JList<String> lstHobbies;
    private DefaultListModel<String> hobbiesListModel;
    private ArrayList<Hobbie> hobbies;

    public RegistrarEstudiante() {
        super();
        initComponentes();
    }

    private void initComponentes() {
        
        JPanel panel = new JPanel();
        GroupLayout layout = new GroupLayout(panel);
        panel.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        JLabel lblRegistro = new JLabel("Registro:");
        JLabel lblNombre = new JLabel("Nombre:");
        JLabel lblApellidoPaterno = new JLabel("Apellido Paterno:");
        JLabel lblApellidoMaterno = new JLabel("Apellido Materno:");
        JLabel lblEdad = new JLabel("Edad:");
        JLabel lblTiempoDedicado = new JLabel("Tiempo Dedicado:");
        JLabel lblHobbies = new JLabel("Hobbies:");

        txtRegistro = new JTextField(10);
        txtNombre = new JTextField(10);
        txtApellidoPaterno = new JTextField(10);
        txtApellidoMaterno = new JTextField(10);
        txtEdad = new JTextField(10);
        txtTiempoDedicado = new JTextField(10);
       
        HobbieController hobbieController = new HobbieController();
        
        hobbies = hobbieController.getAll();
        hobbiesListModel = new DefaultListModel<>();
        for (Hobbie hobby : hobbies) {
            hobbiesListModel.addElement(hobby.getHobbie());
        }
        lstHobbies = new JList<>(hobbiesListModel);
        lstHobbies.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

        JScrollPane scrollPane = new JScrollPane(lstHobbies);

        JButton btnRegistrar = new JButton("Registrar");
        btnRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registrarEstudiante();
            }
        });

        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(lblRegistro)
                        .addComponent(lblNombre)
                        .addComponent(lblApellidoPaterno)
                        .addComponent(lblApellidoMaterno)
                        .addComponent(lblEdad)
                        .addComponent(lblTiempoDedicado)
                        .addComponent(lblHobbies))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(txtRegistro)
                        .addComponent(txtNombre)
                        .addComponent(txtApellidoPaterno)
                        .addComponent(txtApellidoMaterno)
                        .addComponent(txtEdad)
                        .addComponent(txtTiempoDedicado)
                        .addComponent(scrollPane)
                        .addComponent(btnRegistrar))
        );

        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(lblRegistro)
                        .addComponent(txtRegistro))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(lblNombre)
                        .addComponent(txtNombre))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(lblApellidoPaterno)
                        .addComponent(txtApellidoPaterno))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(lblApellidoMaterno)
                        .addComponent(txtApellidoMaterno))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(lblEdad)
                        .addComponent(txtEdad))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(lblTiempoDedicado)
                        .addComponent(txtTiempoDedicado))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(lblHobbies)
                        .addComponent(scrollPane))
                .addComponent(btnRegistrar)
        );

        add(panel);
    }

    private void registrarEstudiante() {
        if(txtRegistro.getText().isEmpty() || txtNombre.getText().isEmpty() || txtApellidoPaterno.getText().isEmpty()
                || txtApellidoMaterno.getText().isEmpty() || txtEdad.getText().isEmpty()){
            JOptionPane.showMessageDialog(this, "No puede dejar espacios vacios", "Datos Invalidos", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        
        Estudiante estudiante = new Estudiante();
        RelacionEH relacionEH = new RelacionEH();
        EstudianteController estudianteController = new EstudianteController(); 
        RelacionEHController relacionEHController = new RelacionEHController();  
        
        // Inicializar los datos del estudiante
        estudiante.setRegistro(Integer.parseInt(txtRegistro.getText()));
        estudiante.setNombre(txtNombre.getText());
        estudiante.setApellidoPaterno(txtApellidoPaterno.getText());
        estudiante.setApellidoMaterno(txtApellidoMaterno.getText());
        estudiante.setEdad(Integer.parseInt(txtEdad.getText()));
        
        estudianteController.add(estudiante);
        
        // Obtener los hobbies seleccionados
        ArrayList<Hobbie> selectedHobbies = new ArrayList<>();
        ArrayList<Integer> selectedHobbiesIds = new ArrayList<>();
        java.util.List<String> selectedHobbiesNames = lstHobbies.getSelectedValuesList();
        for (String hobbyName : selectedHobbiesNames) {
            for (Hobbie hobby : hobbies) {
                if (hobby.getHobbie().equals(hobbyName)) {
                    selectedHobbies.add(hobby);
                    selectedHobbiesIds.add(hobby.getId());
                    break;
                }
            }
        }
        
        for (Hobbie hobby : selectedHobbies) {
            relacionEH.setRegistro(estudiante.getRegistro());
            relacionEH.setIdHobbie(hobby.getId());
            relacionEH.setTiempo(Integer.parseInt(txtTiempoDedicado.getText()));
            relacionEHController.add(relacionEH);
        }

        mostrarDatosIngresados(estudiante, relacionEH, selectedHobbiesNames);
    }

    private void mostrarDatosIngresados(Estudiante estudiante, RelacionEH relacionEH, java.util.List<String> hobbies) {
        StringBuilder hobbiesBuilder = new StringBuilder();
        for (String hobby : hobbies) {
            hobbiesBuilder.append(hobby).append(", ");
        }

        JOptionPane.showMessageDialog(this, "Registro: " + estudiante.getRegistro() + "\nNombre: " + estudiante.getNombre() +
                "\nApellido Paterno: " + estudiante.getApellidoPaterno() + "\nApellido Materno: " + estudiante.getApellidoMaterno() +
                "\nEdad: " + estudiante.getEdad() + "\nTiempo Dedicado: " + relacionEH.getTiempo() +
                "\nHobbies: " + hobbiesBuilder.toString(), "Datos Ingresados", JOptionPane.INFORMATION_MESSAGE);
    }
}
