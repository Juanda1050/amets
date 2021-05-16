package controlador;

import modelo.Paquetes;
import modelo.PaquetesDAO;
import vista.GestionarPaquetes;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PaquetesController implements ActionListener
{
    PaquetesDAO dao = new PaquetesDAO();
    Paquetes p = new Paquetes();
    GestionarPaquetes vista = new GestionarPaquetes();
    DefaultTableModel modelo = new DefaultTableModel();

    public PaquetesController(GestionarPaquetes v, PaquetesDAO dao)
    {
        this.vista = v;
        this.dao = dao;
        areTextFieldEditable(false);
        vista.gPaquete_addB.addActionListener(this);
        vista.gPaquete_saveB.addActionListener(this);
        listar(vista.gPaqueteTable);
    }

    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource()==vista.gPaquete_addB)
        {
            areTextFieldEditable(true);
            vista.gPaquete_addB.setEnabled(false);
            vista.gPaquete_editB.setEnabled(false);
            vista.gPaquete_deleteB.setEnabled(false);
        }
        if(e.getSource()==vista.gPaquete_saveB)
        {
            agregar();
            cleanForm();
            limpiar();
            listar(vista.gPaqueteTable);
        }
    }


    public void listar(JTable tabla)
    {
        modelo = (DefaultTableModel)tabla.getModel();
        tabla.setModel(modelo);
        int lista = dao.listar().size();
        Object[] object = new Object[5];
        for (int i =0; i<lista; i++)
        {
           object[0] = dao.listar().get(i).getID();
           object[1] = dao.listar().get(i).getName();
           object[2] = dao.listar().get(i).getDescription();
           object[3] = dao.listar().get(i).getPassengers();
            object[4] = dao.listar().get(i).getPrice();
            modelo.addRow(object);
        }
    }

    public void agregar()
    {
        String name = vista.gPaquete_nombreTF.getText();
        String description = vista.gPaquete_descripcionTF.getText();
        int passengers = Integer.parseInt(vista.gPaquete_genteTF.getText());
        float price = Float.parseFloat(vista.gPaquete_precioTF.getText());

        p.setName(name);
        p.setDescription(description);
        p.setPassengers(passengers);
        p.setPrice(price);
        int r = dao.agregar(p);
        if(r == 1){
            JOptionPane.showMessageDialog(null, "Paquete agregado exitosamente");
        }else{
            JOptionPane.showMessageDialog(null, "Paquete fallido");
        }
    }

    private void limpiar(){
        for (int i = 0; i < vista.gPaqueteTable.getRowCount(); i++){
            modelo.removeRow(i);
            i = i - 1;
        }
    }

    private void areTextFieldEditable(boolean flag)
    {
        vista.gPaquete_nombreTF.setEditable(flag);
        vista.gPaquete_descripcionTF.setEditable(flag);
        vista.gPaquete_genteTF.setEditable(flag);
        vista.gPaquete_precioTF.setEditable(flag);
    }

    private void cleanForm(){
        vista.gPaquete_nombreTF.setText("");
        vista.gPaquete_descripcionTF.setText("");
        vista.gPaquete_genteTF.setText("");
        vista.gPaquete_precioTF.setText("");
    }
}

