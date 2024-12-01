/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vistas;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

/**
 *
 * @author david
 */
public class Ventana extends JFrame implements ActionListener{
    
    public JMenuBar barraDeMenu;
    public JMenu estudiantes, hobbies, informacion;
    public JMenuItem [] opcionesDelMenuEstudiante;
    public JMenuItem [] opcionesDelMenuHobbies;
    public JMenuItem [] opcionesDelMenuInformacion;
    
    public Ventana()
    {
       super();
       configura();
       mostrarVerInformacion();
    }
    
    public void configura(){
        setTitle("Hobbies de Estudiantes");
        setSize(600,600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);                             
        
        // Creación de la barra de navegación
        barraDeMenu = new JMenuBar();
        
        // Creación de los menú
        estudiantes = new JMenu("Estudiantes"); 
        hobbies = new JMenu("Hobbies"); 
        informacion = new JMenu("Información");
        
        // Creación de las opciones de cada menú
        
        // Estudiante
        opcionesDelMenuEstudiante = new JMenuItem[2];
        opcionesDelMenuEstudiante[0] = new JMenuItem("Registrar Estudiante",KeyEvent.VK_C);
        opcionesDelMenuEstudiante[0].setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_2,ActionEvent.ALT_MASK));
        opcionesDelMenuEstudiante[0].addActionListener(this);
        
        opcionesDelMenuEstudiante[1] = new JMenuItem("Ver Estudiantes",KeyEvent.VK_R);
        opcionesDelMenuEstudiante[1].setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_3,ActionEvent.ALT_MASK));
        opcionesDelMenuEstudiante[1].addActionListener(this);
        
        // Hobbies
        
        opcionesDelMenuHobbies = new JMenuItem[2];
        
        opcionesDelMenuHobbies[0] = new JMenuItem("Registrar Hobbies" ,KeyEvent.VK_B);
        opcionesDelMenuHobbies[0].setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1,ActionEvent.ALT_MASK));
        opcionesDelMenuHobbies[0].addActionListener(this);
        
        opcionesDelMenuHobbies[1] = new JMenuItem("Ver Hobbies",KeyEvent.VK_C);
        opcionesDelMenuHobbies[1].setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_2,ActionEvent.ALT_MASK));
        opcionesDelMenuHobbies[1].addActionListener(this);
        
        // Información
        
        opcionesDelMenuInformacion = new JMenuItem[1];
        opcionesDelMenuInformacion[0] = new JMenuItem("Ver Información");
        opcionesDelMenuInformacion[0].setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1,0));
        opcionesDelMenuInformacion[0].addActionListener(this);
        
        // Estudiantes
        for (int i = 0; i < opcionesDelMenuEstudiante.length; i++)
            estudiantes.add(opcionesDelMenuEstudiante[i]);
        // Hobbies
        for (int i = 0; i < opcionesDelMenuHobbies.length; i++)
            hobbies.add(opcionesDelMenuHobbies[i]);
        // Información
        for (int i = 0; i < opcionesDelMenuInformacion.length; i++)
            informacion.add(opcionesDelMenuInformacion[i]);
        
        // Se agregan las opciones del menu a la barra de menu
        barraDeMenu.add(estudiantes);
        barraDeMenu.add(hobbies);
        barraDeMenu.add(informacion);
        
        // Se agrega la barra de menu al formulario        
        this.setJMenuBar(barraDeMenu);      
        
    }
    
    
    public void actionPerformed(ActionEvent ae)
    {
        JMenuItem opcion = (JMenuItem) ae.getSource();
        
        JPanel panel = null;      
        this.getContentPane().removeAll();
        
        switch(opcion.getText())
        {
            case "Registrar Estudiante": 
                panel = new RegistrarEstudiante();                                             
                break;
            case "Ver Estudiantes": 
                panel = new VerEstudiantes(); 
                break;
            case "Registrar Hobbies": 
                panel = new RegistrarHobbies(); 
                break;
            case "Ver Hobbies": 
                panel = new VerHobbies(); 
                break;
            case "Ver Información": 
                panel = new VerInformacion(); 
                break;
            default: 
                panel = null; 
                break;
        }
        
        if  (panel == null)
        {
            if (opcion.getText().equals("Ayuda"))    
            {}
            else if (opcion.getText().equals("Acerca de"))
            {}        
        }
        else
        {       
            add(panel);
            this.getContentPane().revalidate();
            this.getContentPane().repaint();        
        }
    }
    
    private void mostrarVerInformacion() {
        VerInformacion verInformacion = new VerInformacion(); 
        add(verInformacion); 
        getContentPane().revalidate(); 
        getContentPane().repaint(); 
    }
}
