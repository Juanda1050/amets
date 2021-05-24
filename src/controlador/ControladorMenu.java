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

public class ControladorMenu implements ActionListener, WindowListener {

    MenuPrincipal vista = new MenuPrincipal();
    int agentID;

    public ControladorMenu(MenuPrincipal v, int agentID){
        this.vista = v;
        this.agentID = agentID;
        vista.mpCajaBtn.addActionListener(this);
        vista.mpVentaBtn.addActionListener(this);
        vista.mpSalirBtn.addActionListener(this);
        vista.mpFrame.addWindowListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == vista.mpCajaBtn) {
            VistaCC ccFrame = new VistaCC();
            ccFrame.runFrame(agentID);
            vista.mpFrame.setVisible(false);
        }

        if (e.getSource() == vista.mpVentaBtn) {
            RegistroUsuarios ru = new RegistroUsuarios();
            ru.runFrame(agentID);
            vista.mpFrame.setVisible(false);
        }

        if (e.getSource() == vista.mpSalirBtn) {
            Credenciales credentials = new Credenciales();
            credentials.initialize();
            vista.mpFrame.setVisible(false);
        }
    }

    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {

        if (e.getSource() == vista.mpFrame){
            int result = JOptionPane.showConfirmDialog(vista.mpFrame, "¿Desea cerrar el programa?", "Salir del programa", JOptionPane.YES_NO_OPTION);
            if (result == JOptionPane.YES_OPTION)
            {
                vista.mpFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            }
            else if (result == JOptionPane.NO_OPTION)
            {
                vista.mpFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
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
