package controlador;

import modelo.Vuelos;
import modelo.VuelosDAO;
import vista.GestionarVuelos;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class VuelosController implements ActionListener {
    Vuelos v = new Vuelos();
    GestionarVuelos vistaV;
    VuelosDAO dao;
    DefaultTableModel modelo = new DefaultTableModel();
    private boolean AU = true;

    public VuelosController(GestionarVuelos vistaV, VuelosDAO dao){
        this.dao = dao;
        this.vistaV = vistaV;
        this.vistaV.gVuelos_addB.addActionListener(this);
        this.vistaV.gVuelos_saveB.addActionListener(this);
        this.vistaV.gVuelos_editB.addActionListener(this);
        this.vistaV.gVuelos_deleteB.addActionListener(this);
        listFly(vistaV.gVuelosTable);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    public void listFly(JTable vuelosTable){
        modelo = (DefaultTableModel) vuelosTable.getModel();
        List<Vuelos> lista = dao.listar();
        Object[] object = new Object[7];
        for (Vuelos vuelo : lista){
            object[0] = vuelo.getFlightID();
            object[1] = vuelo.getOrigin();
            object[2] = vuelo.getDestinationName();
            object[3] = vuelo.getAirlineName();
            object[4] = vuelo.getPassengers();
            object[5] = vuelo.getDeparture();
            object[6] = vuelo.getLanding();
            modelo.addRow(object);
        }
        vistaV.gVuelosTable.setModel(modelo);
    }
}
