package Controlador;

import Modelo.DestinoDAO;
import Modelo.Destinos;
import cuarta.GestionarDestinos;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class DestinoController implements ActionListener {
    Destinos d = new Destinos();
    GestionarDestinos vistaD = new GestionarDestinos();
    DestinoDAO dao = new DestinoDAO();
    DefaultTableModel modelo = new DefaultTableModel();

    public DestinoController(GestionarDestinos vistaD, DestinoDAO dao){
        this.dao = dao;
        this.vistaD = vistaD;
        this.vistaD.gDestino_listB.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == vistaD.gDestino_listB){
            listar(vistaD.gDestinoTable);
        }
    }

    public void listar(JTable destinosTable){
        modelo = (DefaultTableModel) destinosTable.getModel();
        DefaultTableCellRenderer tRender = new DefaultTableCellRenderer();
        tRender.setHorizontalAlignment(JLabel.CENTER);
        destinosTable.setModel(modelo);
        modelo.addColumn("ID Destino");
        modelo.addColumn("Ciudad");
        modelo.addColumn("Estado");
        modelo.addColumn("Pais");

        Object[]object = new Object[4];
        int registro = dao.listar().size();
        for (int i = 0; i < registro; i++){
            object[0] = dao.listar().get(i).getDestinationID();
            object[1] = dao.listar().get(i).getCity();
            object[2] = dao.listar().get(i).getState();
            object[3] = dao.listar().get(i).getCountry();
            modelo.addRow(object);
            destinosTable.getColumnModel().getColumn(i).setCellRenderer(tRender);
        }
    }
}
