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
    SeleccionarPaquete vista = new SeleccionarPaquete();
    Ticket ticket = new Ticket();
    int agentID;
    
    public SelecPaqController(SeleccionarPaquete v, SelecPaqDAO dao, int agentID){
        this.vista = v;
        this.dao = dao;
        this.agentID = agentID;
        vista.spVolverBtn.addActionListener(this);
        vista.spMenuBtn.addActionListener(this);
        vista.btnSiguiente.addActionListener(this);
        vista.spPaqueteCB.addActionListener(this);
        vista.spDestinoCB.addActionListener(this);
        vista.spFrame.addWindowListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == vista.spPaqueteCB) {
            String paqueteCBindex = vista.spPaqueteCB.getSelectedItem().toString();
            vista.spDescripcionTF.setText(dao.getData(paqueteCBindex).get(0));
            vista.spPrecioTF.setText(dao.getData(paqueteCBindex).get(1));
            vista.spPlazasTF.setText(dao.getData(paqueteCBindex).get(2));
            vista.spHotelTF.setText(dao.getData(paqueteCBindex).get(3));
            vista.spDireccionTF.setText(dao.getData(paqueteCBindex).get(4));
            vista.spRegimenTF.setText(dao.getData(paqueteCBindex).get(5));
            vista.spHoraLlegadaSpn.setText(dao.getData(paqueteCBindex).get(6));
            vista.spHoraSalidaSpn.setText(dao.getData(paqueteCBindex).get(7));
            vista.spOrigenFT.setText(dao.getData(paqueteCBindex).get(8));
            vista.spHoraDespegueSpn.setText(dao.getData(paqueteCBindex).get(9));
            vista.spHoradeAterrizajeSpn.setText(dao.getData(paqueteCBindex).get(10));
            vista.spAerolineaTF.setText(dao.getData(paqueteCBindex).get(11));
            vista.spTipodeVueloTF.setText(dao.getData(paqueteCBindex).get(12));
        }

        if (e.getSource() == vista.spDestinoCB) {
            if(vista.spDestinoCB.getSelectedIndex()!=-1){
                vista.spPaqueteCB.setEnabled(true);
                vista.btnSiguiente.setEnabled(true);
            }
            vista.spPaqueteCB.removeAllItems();
            dao.listarPaquetes(vista.spDestinoCB.getSelectedIndex()+1);
            for(int i=0;i<dao.getListaPaquete().size();i++){
                vista.spPaqueteCB.addItem(dao.getListaPaquete().get(i));
            }
        }

        if (e.getSource() == vista.spMenuBtn) {
            Retorno rtn = new Retorno();
            vista.spFrame.setVisible(rtn.runReturn(agentID));
        }

        if (e.getSource() == vista.spVolverBtn) {
            UsuarioDAO usuarioDAO = new UsuarioDAO();
            RegistroUsuarios ru = new RegistroUsuarios();
            usuarioDAO.deleteLastUser();
            ru.runFrame(agentID);
            vista.spFrame.setVisible(false);
        }

        if (e.getSource() == vista.btnSiguiente) {
            ticket.runFrame(ticket, agentID);
            VistaPP pp = new VistaPP();
            pp.runFrame(vista.spDescripcionTF.getText(),Double.parseDouble(vista.spPrecioTF.getText()),agentID, ticket);
            vista.spFrame.setVisible(false);
        }
    }

    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {

        if (e.getSource() == vista.spFrame){
            int result = JOptionPane.showConfirmDialog(vista.spFrame, "¿Desea cerrar el programa?", "Salir del programa", JOptionPane.YES_NO_OPTION);
            if (result == JOptionPane.YES_OPTION)
            {
                vista.spFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            }
            else if (result == JOptionPane.NO_OPTION)
            {
                vista.spFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
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
