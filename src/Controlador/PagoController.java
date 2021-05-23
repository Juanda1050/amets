package Controlador;

import modelo.PagoDAO;
import primera.Retorno;
import segunda.SeleccionarPaquete;
import segunda.Ticket;
import segunda.VistaPP;
import javax.swing.*;
import java.awt.event.*;
import java.util.Date;

public class PagoController implements ActionListener, WindowListener {

    PagoDAO dao;
    VistaPP vista = new VistaPP();
    int agentID;
    String description;
    double price;
    Ticket ticket = new Ticket();

    public PagoController(VistaPP v, PagoDAO dao, int agentID, String desc, double precio, Ticket ticket){
        this.vista = v;
        this.dao = dao;
        this.agentID = agentID;
        this.description = desc;
        this.price = precio;
        this.ticket = ticket;
        vista.ppVolverButton.addActionListener(this);
        vista.ppSiguienteButton.addActionListener(this);
        vista.ppMenuButton.addActionListener(this);
        vista.comboBox.addActionListener(this);
        vista.frmAmetsTravel.addWindowListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == vista.ppMenuButton) {
            Retorno rtn = new Retorno();
            vista.frmAmetsTravel.setVisible(rtn.runReturn(agentID));
        }

        if (e.getSource() == vista.ppVolverButton){
            SeleccionarPaquete sp = new SeleccionarPaquete();
            sp.runFrame(agentID);
            vista.frmAmetsTravel.setVisible(false);
        }

        if (e.getSource() == vista.comboBox) {
            if(vista.comboBox.getSelectedItem()=="Tarjeta"){
                vista.ppTarjetaBox.setEnabled(true);
                vista.ppNumTarjetaTF.setEditable(true);
                vista.ppExpiracionTf.setEnabled(true);
                vista.ppTitularTF.setEditable(true);
                vista.ppCcvTF.setEditable(true);
            }else{
                vista.ppTarjetaBox.setEnabled(false);
                vista.ppNumTarjetaTF.setEditable(false);
                vista.ppExpiracionTf.setEnabled(false);
                vista.ppTitularTF.setEditable(false);
                vista.ppCcvTF.setEditable(false);
            }
        }

        if (e.getSource() == vista.ppSiguienteButton) {
            PagoDAO pagoDAO = new PagoDAO();
            Date currentDate = new Date();
            SeleccionarPaquete sp = new SeleccionarPaquete();
            if(vista.comboBox.getSelectedItem()=="Tarjeta" && vista.comboBox.getSelectedIndex()!=-1 && vista.ppTarjetaBox.getSelectedIndex()!=-1 && vista.ppNumTarjetaTF.getText().length()==16 && vista.ppExpiracionTf.getText().length()==5 && vista.ppTitularTF.getText().length()!=0 && vista.ppCcvTF.getText().length()==3){
                if(vista.ppExpiracionTf.getDate()==null || currentDate.after(vista.ppExpiracionTf.getDate())){
                    JOptionPane.showMessageDialog(null, "TARJETA EXPIRADA", "MÉTODO DE PAGO NO ACEPTADO", JOptionPane.WARNING_MESSAGE);
                }else {
                    ticket.txtrCompraRealizada.setText("Compra Realizada\nTipo de pago: Tarjeta");
                    char last1digit = vista.ppNumTarjetaTF.getText().charAt(12);
                    char last2digit = vista.ppNumTarjetaTF.getText().charAt(13);
                    char last3digit = vista.ppNumTarjetaTF.getText().charAt(14);
                    char last4digit = vista.ppNumTarjetaTF.getText().charAt(15);
                    ticket.txtrNumDeTarjeta.setText("Tipo de Tarjeta: " + vista.ppTarjetaBox.getSelectedItem() + "\nNum. de Tarjeta: ****-****-****-" + last1digit+last2digit+last3digit+last4digit + "\nTitular: " + vista.ppTitularTF.getText());
                    ticket.txtrAsfdas.setText("Descripcion: " + description);
                    pagoDAO.guardarVenta(agentID, description, String.valueOf(vista.comboBox.getSelectedItem()), price);
                    vista.frmAmetsTravel.setVisible(false);
                    ticket.frame.setVisible(true);
                }
                //agregar opción para efectivo, se llena solo el campo de tipo de pago y descripcion
            }else if(vista.comboBox.getSelectedItem()=="Efectivo"){
                ticket.txtrCompraRealizada.setText("Compra Realizada\nTipo de pago: Efectivo");
                ticket.txtrAsfdas.setText("Descripcion: " + description);
                pagoDAO.guardarVenta(agentID, description, String.valueOf(vista.comboBox.getSelectedItem()), price);
                vista.frmAmetsTravel.setVisible(false);
                ticket.frame.setVisible(true);
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

        if (e.getSource() == vista.frmAmetsTravel) {
            int result = JOptionPane.showConfirmDialog(vista.frmAmetsTravel, "¿Desea cerrar el programa?", "Salir del programa", JOptionPane.YES_NO_OPTION);
            if (result == JOptionPane.YES_OPTION)
            {
                vista.frmAmetsTravel.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            }
            else if (result == JOptionPane.NO_OPTION)
            {
                vista.frmAmetsTravel.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
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
