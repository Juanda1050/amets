package controlador;

import modelo.VentaDAO;
import vista.GestionarVentas;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class VentaController
{
    GestionarVentas vista;
    VentaDAO dao;
    DefaultTableModel modelo = new DefaultTableModel();

    public VentaController(GestionarVentas vista, VentaDAO dao)
    {
        this.vista = vista;
        this.dao = dao;
        listar(this.vista.gVentasTable);
    }
    public void listar(JTable destinosTable){
        modelo = (DefaultTableModel) destinosTable.getModel();
        destinosTable.setModel(modelo);

        Object[]object = new Object[10];
        int registro = dao.listar().size();
        for (int i = 0; i < registro; i++){
            object[0] = dao.listar().get(i).getSaleID();
            object[1] = dao.listar().get(i).getUserID();
            object[2] = dao.listar().get(i).getAgentID();
            object[3] = dao.listar().get(i).getQuantity();
            object[4] = dao.listar().get(i).getSaleDescription();
            object[5] = dao.listar().get(i).getSaleData();
            object[6] = dao.listar().get(i).getPayment();
            object[7] = dao.listar().get(i).getSubtotal();
            object[8] = dao.listar().get(i).getIva();
            object[9] = dao.listar().get(i).getTotal();
            modelo.addRow(object);
        }
    }

}
