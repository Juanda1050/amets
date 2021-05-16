package controlador;

import modelo.Aerolinea;
import modelo.AerolineaDAO;
import vista.GestionarAerolinea;

import javax.print.attribute.standard.JobKOctets;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class AerolineaController{
    Aerolinea a = new Aerolinea();
    GestionarAerolinea vistaA;
    AerolineaDAO dao;
    DefaultTableModel modelo = new DefaultTableModel();

    public AerolineaController(GestionarAerolinea vistaA, AerolineaDAO dao){
        this.dao = dao;
        this.vistaA = vistaA;
        listar(vistaA.gAerolineaTable);
    }

    public void listar(JTable aerolineaTable){
        modelo = (DefaultTableModel) aerolineaTable.getModel();
        List<Aerolinea> lista = dao.listar();
        Object[] object = new Object[4];
        for (Aerolinea aero : lista){
            object[0] = aero.getAirlineID();
            object[1] = aero.getAirlineName();
            object[2] = aero.getFlyClass();
            object[3] = aero.getPrice();
            modelo.addRow(object);
        }
        vistaA.gAerolineaTable.setModel(modelo);
    }
}
