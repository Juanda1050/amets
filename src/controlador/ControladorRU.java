package controlador;

import modelo.Usuario;
import modelo.UsuarioDAO;
import vista.MenuPrincipal;
import vista.RegistroUsuarios;
import vista.SeleccionarPaquete;

import javax.swing.*;
import java.awt.event.*;

public class ControladorRU implements ActionListener, WindowListener
{
    UsuarioDAO dao;
    Usuario u = new Usuario();
    RegistroUsuarios vista = new RegistroUsuarios();
    int agentID;

    public ControladorRU(RegistroUsuarios v, UsuarioDAO dao, int agentID)
    {
        this.vista = v;
        this.dao = dao;
        this.agentID = agentID;
        vista.ruSiguienteBtn.addActionListener(this);
        vista.ruVolverBtn.addActionListener(this);
        vista.ruFrame.addWindowListener(this);
    }

    public void agregar()
    {
        String Nom = vista.ruNombreTF.getText();
        String Ap = vista.ruApellidoTF.getText();
        String Nacimiento = vista.ruNacimientoTF.getText();
        String Direccion = vista.ruDireccionTF.getText();
        String Email = vista.ruEmailTF.getText();
        String Tel = vista.ruTelefonoTF.getText();
        u.setNombre(Nom);
        u.setApellido(Ap);
        u.setNacimiento(Nacimiento);
        u.setDireccion(Direccion);
        u.setEmail(Email);
        u.setTelefono(Tel);
        int r = dao.agregar(u);
        if(r==1)
        {
            System.out.println("Exito");
            SeleccionarPaquete spFrame = new SeleccionarPaquete();
            vista.ruFrame.setVisible(false);
            spFrame.runFrame(agentID);

        }
        else
        {
            System.out.println("Fail");
        }
    }

    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource()== vista.ruSiguienteBtn)
        {
            if (vista.ruNombreTF.getText().length()!=0 && vista.ruApellidoTF.getText().length()!=0 && vista.ruDireccionTF.getText().length()!=0 && vista.ruNacimientoTF.getDate()!=null && vista.ruTelefonoTF.getText().length()==10 && vista.ruEmailTF.getText().length()!=0){
                if (vista.ruEmailTF.getText().contains("@")) {
                    agregar();
                }else{
                    JOptionPane.showMessageDialog(null, "INTRODUZCA UN CORREO VÁLIDO","DATOS INCORRECTOS", JOptionPane.ERROR_MESSAGE);
                }
            }else{
                JOptionPane.showMessageDialog(null, "LLENE TODOS LOS ESPACIOS CORRECTAMENTE","DATOS INCOMPLETOS", JOptionPane.ERROR_MESSAGE);
            }

        }

        if (e.getSource()== vista.ruVolverBtn) {
            MenuPrincipal mpFrame = new MenuPrincipal();
            mpFrame.runFrame(agentID);
            vista.ruFrame.setVisible(false);
        }
    }

    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {

        if (e.getSource() == vista.ruFrame) {
            int result = JOptionPane.showConfirmDialog(vista.ruFrame, "¿Desea cerrar el programa?", "Salir del programa", JOptionPane.YES_NO_OPTION);
            if (result == JOptionPane.YES_OPTION)
            {
                vista.ruFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            }
            else if (result == JOptionPane.NO_OPTION)
            {
                vista.ruFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            }
        }
    }

    @Override
    public void windowClosed(WindowEvent e) {

    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }
}