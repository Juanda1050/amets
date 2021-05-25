package controlador;

import vista.Credenciales;
import vista.MenuPrincipal;
import vista.RegistroUsuarios;
import vista.VistaCC;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class MenuController implements ActionListener, WindowListener {

    MenuPrincipal vista = new MenuPrincipal();
    int agentID;

    public MenuController(MenuPrincipal v, int agentID){
        this.vista = v;
        this.agentID = agentID;
        vista.mPrincipal_corteB.addActionListener(this);
        vista.mPrincipal_ventaB.addActionListener(this);
        vista.mPrincipal_exitB.addActionListener(this);
        vista.mPrincipalFrame.addWindowListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == vista.mPrincipal_corteB) {
            VistaCC ccFrame = new VistaCC();
            ccFrame.runFrame(agentID);
            vista.mPrincipalFrame.setVisible(false);
        }

        if (e.getSource() == vista.mPrincipal_ventaB) {
            RegistroUsuarios ru = new RegistroUsuarios();
            ru.runFrame(agentID);
            vista.mPrincipalFrame.setVisible(false);
        }

        if (e.getSource() == vista.mPrincipal_exitB) {
            Credenciales credentials = new Credenciales();
            credentials.initialize();
            vista.mPrincipalFrame.setVisible(false);
        }
    }

    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {

        if (e.getSource() == vista.mPrincipalFrame){
            int result = JOptionPane.showConfirmDialog(vista.mPrincipalFrame, "¿Desea cerrar el programa?", "Salir del programa", JOptionPane.YES_NO_OPTION);
            if (result == JOptionPane.YES_OPTION)
            {
                vista.mPrincipalFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            }
            else if (result == JOptionPane.NO_OPTION)
            {
                vista.mPrincipalFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
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
