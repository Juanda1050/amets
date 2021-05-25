package controlador;

import modelo.PagoDAO;
import vista.Retorno;
import vista.SeleccionarPaquete;
import vista.Ticket;
import vista.VistaPP;
import javax.swing.*;
import java.awt.event.*;
import java.util.Date;

public class PagoController implements ActionListener, WindowListener {

    PagoDAO dao;
    VistaPP vista;
    int agentID;
    String description;
    float price;
    Ticket ticket;

    public PagoController(VistaPP v, PagoDAO dao, int agentID, String desc, float precio, Ticket ticket){
        this.vista = v;
        this.dao = dao;
        this.agentID = agentID;
        this.description = desc;
        this.price = precio;
        this.ticket = ticket;
        vista.pPago_backB.addActionListener(this);
        vista.pPago_nextB.addActionListener(this);
        vista.pPago_menuB.addActionListener(this);
        vista.pPago_tipoCB.addActionListener(this);
        vista.pPagoFrame.addWindowListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == vista.pPago_menuB) {
            Retorno rtn = new Retorno();
            vista.pPagoFrame.setVisible(rtn.runReturn(agentID));
        }

        if (e.getSource() == vista.pPago_backB){
            SeleccionarPaquete sp = new SeleccionarPaquete();
            sp.runFrame(agentID);
            vista.pPagoFrame.setVisible(false);
        }

        if (e.getSource() == vista.pPago_tipoCB) {
            if(vista.pPago_tipoCB.getSelectedItem()=="Tarjeta"){
                vista.pPago_tarjetaCB.setEnabled(true);
                vista.pPago_numTF.setEditable(true);
                vista.pPago_expTF.setEnabled(true);
                vista.pPago_titularTF.setEditable(true);
                vista.pPago_ccvTF.setEditable(true);
            }else{
                vista.pPago_tarjetaCB.setEnabled(false);
                vista.pPago_numTF.setEditable(false);
                vista.pPago_expTF.setEnabled(false);
                vista.pPago_titularTF.setEditable(false);
                vista.pPago_ccvTF.setEditable(false);
            }
        }

        if (e.getSource() == vista.pPago_nextB) {
            PagoDAO pagoDAO = new PagoDAO();
            Date currentDate = new Date();
            SeleccionarPaquete sp = new SeleccionarPaquete();
            if(vista.pPago_tipoCB.getSelectedItem()=="Tarjeta" && vista.pPago_tipoCB.getSelectedIndex()!=-1 && vista.pPago_tarjetaCB.getSelectedIndex()!=-1 && vista.pPago_numTF.getText().length()==16 && vista.pPago_expTF.getText().length()==5 && vista.pPago_titularTF.getText().length()!=0 && vista.pPago_ccvTF.getText().length()==3){
                if(vista.pPago_expTF.getDate()==null || currentDate.after(vista.pPago_expTF.getDate())){
                    JOptionPane.showMessageDialog(null, "TARJETA EXPIRADA", "MÉTODO DE PAGO NO ACEPTADO", JOptionPane.WARNING_MESSAGE);
                }else {
                    ticket.ticket_compraTA.setText("Compra Realizada\nTipo de pago: Tarjeta");
                    char last1digit = vista.pPago_numTF.getText().charAt(12);
                    char last2digit = vista.pPago_numTF.getText().charAt(13);
                    char last3digit = vista.pPago_numTF.getText().charAt(14);
                    char last4digit = vista.pPago_numTF.getText().charAt(15);
                    ticket.ticket_pagoTA.setText("Tipo de Tarjeta: " + vista.pPago_tarjetaCB.getSelectedItem() + "\nNum. de Tarjeta: ****-****-****-" + last1digit+last2digit+last3digit+last4digit + "\nTitular: " + vista.pPago_titularTF.getText());
                    ticket.ticket_descTP.setText("Descripcion: " + description);
                    pagoDAO.guardarVenta(agentID, description, String.valueOf(vista.pPago_tipoCB.getSelectedItem()), price);
                    vista.pPagoFrame.setVisible(false);
                    ticket.ticketFrame.setVisible(true);
                }
                //agregar opción para efectivo, se llena solo el campo de tipo de pago y descripcion
            }else if(vista.pPago_tipoCB.getSelectedItem()=="Efectivo"){
                ticket.ticket_compraTA.setText("Compra Realizada\nTipo de pago: Efectivo");
                ticket.ticket_descTP.setText("Descripcion: " + description);
                pagoDAO.guardarVenta(agentID, description, String.valueOf(vista.pPago_tipoCB.getSelectedItem()), price);
                vista.pPagoFrame.setVisible(false);
                ticket.ticketFrame.setVisible(true);
            }else{
                JOptionPane.showMessageDialog(null, "LLENE TODOS LOS ESPACIOS CORRECTAMENTE","DATOS INCOMPLETOS", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {

        if (e.getSource() == vista.pPagoFrame) {
            int result = JOptionPane.showConfirmDialog(vista.pPagoFrame, "¿Desea cerrar el programa?", "Salir del programa", JOptionPane.YES_NO_OPTION);
            if (result == JOptionPane.YES_OPTION)
            {
                vista.pPagoFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            }
            else if (result == JOptionPane.NO_OPTION)
            {
                vista.pPagoFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
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
