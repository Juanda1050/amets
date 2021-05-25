package controlador;

import modelo.Usuario;
import modelo.UsuarioDAO;
import vista.MenuPrincipal;
import vista.RegistroUsuarios;
import vista.SeleccionarPaquete;

import javax.swing.*;
import java.awt.event.*;

public class UsuarioController implements ActionListener, WindowListener
{
    UsuarioDAO dao;
    Usuario u = new Usuario();
    RegistroUsuarios vista = new RegistroUsuarios();
    int agentID;

    public UsuarioController(RegistroUsuarios v, UsuarioDAO dao, int agentID)
    {
        this.vista = v;
        this.dao = dao;
        this.agentID = agentID;
        vista.rUsuarios_nextB.addActionListener(this);
        vista.rUsuarios_backB.addActionListener(this);
        vista.rUsuariosFrame.addWindowListener(this);
    }

    public void agregar()
    {
        String Nom = vista.rUsuarios_nombreTF.getText();
        String Ap = vista.rUsuarios_apellidoTF.getText();
        String Nacimiento = vista.rUsuarios_cumpleTF.getText();
        String Direccion = vista.rUsuarios_direccionTF.getText();
        String Email = vista.rUsuarios_emailTF.getText();
        String Tel = vista.rUsuarios_telefonoTF.getText();
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
            vista.rUsuariosFrame.setVisible(false);
            spFrame.runFrame(agentID);

        }
        else
        {
            System.out.println("Fail");
        }
    }

    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource()== vista.rUsuarios_nextB)
        {
            if (vista.rUsuarios_nombreTF.getText().length()!=0 && vista.rUsuarios_apellidoTF.getText().length()!=0 && vista.rUsuarios_direccionTF.getText().length()!=0 && vista.rUsuarios_cumpleTF.getDate()!=null && vista.rUsuarios_telefonoTF.getText().length()==10 && vista.rUsuarios_emailTF.getText().length()!=0){
                if (vista.rUsuarios_emailTF.getText().contains("@")) {
                    agregar();
                }else{
                    JOptionPane.showMessageDialog(null, "INTRODUZCA UN CORREO VÁLIDO","DATOS INCORRECTOS", JOptionPane.ERROR_MESSAGE);
                }
            }else{
                JOptionPane.showMessageDialog(null, "LLENE TODOS LOS ESPACIOS CORRECTAMENTE","DATOS INCOMPLETOS", JOptionPane.ERROR_MESSAGE);
            }

        }

        if (e.getSource()== vista.rUsuarios_backB) {
            MenuPrincipal mpFrame = new MenuPrincipal();
            mpFrame.runFrame(agentID);
            vista.rUsuariosFrame.setVisible(false);
        }
    }

    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {

        if (e.getSource() == vista.rUsuariosFrame) {
            int result = JOptionPane.showConfirmDialog(vista.rUsuariosFrame, "¿Desea cerrar el programa?", "Salir del programa", JOptionPane.YES_NO_OPTION);
            if (result == JOptionPane.YES_OPTION)
            {
                vista.rUsuariosFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            }
            else if (result == JOptionPane.NO_OPTION)
            {
                vista.rUsuariosFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
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