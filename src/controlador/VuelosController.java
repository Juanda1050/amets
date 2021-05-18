package controlador;

import modelo.Vuelos;
import modelo.VuelosDAO;
import vista.GestionarVuelos;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
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
        areTextFieldEditable(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == vistaV.gVuelos_addB){
            AU = true;
            areTextFieldEditable(true);
        }
        if(e.getSource() == vistaV.gVuelos_saveB){
            saveFly();
        }
        if(e.getSource() == vistaV.gVuelos_editB){
            editFly();
        }
        if(e.getSource() == vistaV.gVuelos_saveB){
            saveFly();
        }
        if(e.getSource() == vistaV.gVuelos_deleteB){
            deleteFly();
            cleanFly();
            listFly(vistaV.gVuelosTable);
        }
    }

    public void addFly(){
        if(vistaV.gVuelos_origenTF.getText().isEmpty() || vistaV.gVuelos_destinoCB.getSelectedIndex() == 0 || vistaV.gVuelos_aerolineaCB.getSelectedIndex() == 0 || vistaV.gVuelos_genteTF.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Uno de los campos están vacios o no cumplen con los valores requeridos para continuar");
        }
        else{
            String origen = vistaV.gVuelos_origenTF.getText();
            int destino = vistaV.gVuelos_destinoCB.getSelectedIndex();
            int aero = vistaV.gVuelos_aerolineaCB.getSelectedIndex();
            int gente = Integer.parseInt(vistaV.gVuelos_genteTF.getText());
            String salida = vistaV.gVuelos_salidaDC.getDateFormatString();
            String llegada = vistaV.gVuelos_llegadaDC.getDateFormatString();
            v.setOrigin(origen);
            v.setDestinationID(destino);
            v.setAirlineID(aero);
            v.setPassengers(gente);
            v.setDeparture(salida);
            v.setLanding(llegada);
            int r = dao.agregar(v);
            if (r == 1){
                JOptionPane.showMessageDialog(null, "Registro agregado exitosamente");
            }
            else{
                JOptionPane.showMessageDialog(null, "Registro fallido");
            }
        }
    }

    public void updateFly(){
        if(vistaV.gVuelos_origenTF.getText().isEmpty() || vistaV.gVuelos_destinoCB.getSelectedIndex() == 0 || vistaV.gVuelos_aerolineaCB.getSelectedIndex() == 0 || vistaV.gVuelos_genteTF.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Uno de los campos están vacios o no cumplen con los valores requeridos para continuar");
        }
        else{
            int id = Integer.parseInt(vistaV.gVuelos_idTF.getText());
            String origen = vistaV.gVuelos_origenTF.getText();
            int destino = vistaV.gVuelos_destinoCB.getSelectedIndex();
            int aero = vistaV.gVuelos_aerolineaCB.getSelectedIndex();
            int gente = Integer.parseInt(vistaV.gVuelos_genteTF.getText());
            String salida = vistaV.gVuelos_salidaDC.getDateFormatString();
            String llegada = vistaV.gVuelos_llegadaDC.getDateFormatString();
            v.setFlightID(id);
            v.setOrigin(origen);
            v.setDestinationID(destino);
            v.setAirlineID(aero);
            v.setPassengers(gente);
            v.setDeparture(salida);
            v.setLanding(llegada);
            int r = dao.actualizar(v);
            if (r == 1){
                JOptionPane.showMessageDialog(null, "Registro actualizado exitosamente");
            }
            else{
                JOptionPane.showMessageDialog(null, "Registro fallido");
            }
        }
    }

    public void deleteFly(){
        int row = vistaV.gVuelosTable.getSelectedRow();
        if (row == 1){
            JOptionPane.showMessageDialog(null, "Seleccione un vuelo");
        }else{
            int id = Integer.parseInt(vistaV.gVuelosTable.getValueAt(row, 0).toString());
            dao.eliminar(id);
            JOptionPane.showMessageDialog(null, "Vuelo eliminado exitosamente");
        }
    }

    private void saveFly(){
        if (AU){
            addFly();
            cleanFly();
            listFly(vistaV.gVuelosTable);
            areTextFieldEditable(false);
            cleanForm();
        }else{
            updateFly();
            cleanFly();
            listFly(vistaV.gVuelosTable);
            areTextFieldEditable(false);
            cleanForm();
        }
    }

    private void editFly(){
        AU = false;
        int row = vistaV.gVuelosTable.getSelectedRow();
        if (row == -1){
            JOptionPane.showMessageDialog(null, "Seleccione un vuelo");
        }else{
            areTextFieldEditable(true);
            int id = Integer.parseInt(vistaV.gVuelosTable.getValueAt(row, 0).toString());
            String origen = (String) vistaV.gVuelosTable.getValueAt(row, 1);
            int gente = Integer.parseInt(vistaV.gVuelosTable.getValueAt(row, 4).toString());
            String salida = (String) vistaV.gVuelosTable.getValueAt(row, 5);
            String llegada = (String) vistaV.gVuelosTable.getValueAt(row, 6);
            vistaV.gVuelos_idTF.setText("" + id);
            vistaV.gVuelos_origenTF.setText("" + origen);
            vistaV.gVuelos_destinoCB.setSelectedIndex(0);
            vistaV.gVuelos_aerolineaCB.setSelectedIndex(0);
            vistaV.gVuelos_genteTF.setText("" + gente);
            vistaV.gVuelos_salidaJS.setValue(salida);
            vistaV.gVuelos_llegadaJS.setValue(llegada);
        }
    }

    public void listFly(JTable vuelosTable){
        modelo = (DefaultTableModel) vuelosTable.getModel();
        List<Vuelos> lista = dao.listar();
        Object[] object = new Object[7];
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );
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

    private void cleanFly(){
        for(int i = 0; i < vistaV.gVuelosTable.getRowCount(); i++){
            modelo.removeRow(i);
            i = i - 1;
        }
    }

    private void areTextFieldEditable(boolean flag){
        vistaV.gVuelos_origenTF.setEditable(flag);
        vistaV.gVuelos_destinoCB.setEnabled(flag);
        vistaV.gVuelos_aerolineaCB.setEnabled(flag);
        vistaV.gVuelos_genteTF.setEditable(flag);
        vistaV.gVuelos_salidaDC.setEnabled(flag);
        vistaV.gVuelos_llegadaDC.setEnabled(flag);
    }

    private void cleanForm(){
        vistaV.gVuelos_origenTF.setText("");
        vistaV.gVuelos_destinoCB.setSelectedIndex(0);
        vistaV.gVuelos_aerolineaCB.setSelectedIndex(0);
        vistaV.gVuelos_genteTF.setText("");
    }
}
