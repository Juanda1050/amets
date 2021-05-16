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
        vista.gPaquete_addB.addActionListener(this);
        listar(vista.gPaqueteTable);
    }

    public void actionPerformed(ActionEvent e) {

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
    }
}
