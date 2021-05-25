package controlador;

import modelo.SelecPaqDAO;
import modelo.UsuarioDAO;
import vista.Retorno;
import vista.RegistroUsuarios;
import vista.SeleccionarPaquete;
import vista.Ticket;
import vista.VistaPP;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class SelecPaqController implements ActionListener, WindowListener {

    SelecPaqDAO dao;
    SeleccionarPaquete vista;
    Ticket ticket = new Ticket();
    int agentID;

    public SelecPaqController(SeleccionarPaquete v, SelecPaqDAO dao, int agentID){
        this.vista = v;
        this.dao = dao;
        this.agentID = agentID;
        vista.sPaquete_backB.addActionListener(this);
        vista.sPaquete_menuB.addActionListener(this);
        vista.sPaquete_nextB.addActionListener(this);
        vista.sPaquete_paqueteCB.addActionListener(this);
        vista.sPaquete_destinoCB.addActionListener(this);
        vista.sPaqueteFrame.addWindowListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == vista.sPaquete_paqueteCB) {
            String paqueteCBindex = vista.sPaquete_paqueteCB.getSelectedItem().toString();
            vista.sPaquete_descTP.setText(dao.getData(paqueteCBindex).get(0));
            vista.sPaquete_precioTF.setText(dao.getData(paqueteCBindex).get(1));
            vista.sPaquete_genteTF.setText(dao.getData(paqueteCBindex).get(2));
            vista.sPaquete_hotelTF.setText(dao.getData(paqueteCBindex).get(3));
            vista.sPaquete_direccionTP.setText(dao.getData(paqueteCBindex).get(4));
            vista.sPaquete_regimenTF.setText(dao.getData(paqueteCBindex).get(5));
            vista.sPaquete_llegadaTF.setText(dao.getData(paqueteCBindex).get(6));
            vista.sPaquete_salidaTF.setText(dao.getData(paqueteCBindex).get(7));
            vista.sPaquete_origenTF.setText(dao.getData(paqueteCBindex).get(8));
            vista.sPaquete_despegueTF.setText(dao.getData(paqueteCBindex).get(9));
            vista.sPaquete_aterrizajeTF.setText(dao.getData(paqueteCBindex).get(10));
            vista.sPaquete_aerolineaTF.setText(dao.getData(paqueteCBindex).get(11));
            vista.sPaquete_claseTF.setText(dao.getData(paqueteCBindex).get(12));
        }

        if (e.getSource() == vista.sPaquete_destinoCB) {
            if(vista.sPaquete_destinoCB.getSelectedIndex()!=-1){
                vista.sPaquete_paqueteCB.setEnabled(true);
                vista.sPaquete_nextB.setEnabled(true);
            }
            vista.sPaquete_paqueteCB.removeAllItems();
            dao.listarPaquetes(vista.sPaquete_destinoCB.getSelectedIndex()+1);
            for(int i=0;i<dao.getListaPaquete().size();i++){
                vista.sPaquete_paqueteCB.addItem(dao.getListaPaquete().get(i));
            }
        }

        if (e.getSource() == vista.sPaquete_menuB) {
            Retorno rtn = new Retorno();
            vista.sPaqueteFrame.setVisible(rtn.runReturn(agentID));
        }

        if (e.getSource() == vista.sPaquete_backB) {
            UsuarioDAO usuarioDAO = new UsuarioDAO();
            RegistroUsuarios ru = new RegistroUsuarios();
            usuarioDAO.deleteLastUser();
            ru.runFrame(agentID);
            vista.sPaqueteFrame.setVisible(false);
        }

        if (e.getSource() == vista.sPaquete_nextB) {
            ticket.runFrame(ticket, agentID);
            VistaPP pp = new VistaPP();
            pp.runFrame(vista.sPaquete_descTP.getText(),Float.parseFloat(vista.sPaquete_precioTF.getText()),agentID, ticket);
            vista.sPaqueteFrame.setVisible(false);
        }
    }

    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {

        if (e.getSource() == vista.sPaqueteFrame){
            int result = JOptionPane.showConfirmDialog(vista.sPaqueteFrame, "¿Desea cerrar el programa?", "Salir del programa", JOptionPane.YES_NO_OPTION);
            if (result == JOptionPane.YES_OPTION)
            {
                vista.sPaqueteFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            }
            else if (result == JOptionPane.NO_OPTION)
            {
                vista.sPaqueteFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
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
