package controlador;

import modelo.Aerolinea;
import modelo.AerolineaDAO;
import vista.GestionarAerolinea;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class AerolineaController implements ActionListener {
    Aerolinea a = new Aerolinea();
    GestionarAerolinea vistaA;
    AerolineaDAO dao;
    DefaultTableModel modelo = new DefaultTableModel();
    private boolean AU = true;

    public AerolineaController(GestionarAerolinea vistaA, AerolineaDAO dao){
        this.dao = dao;
        this.vistaA = vistaA;
        this.vistaA.gAerolinea_addB.addActionListener(this);
        this.vistaA.gAerolinea_saveB.addActionListener(this);
        this.vistaA.gAerolinea_editB.addActionListener(this);
        this.vistaA.gAerolinea_deleteB.addActionListener(this);
        listAirline(vistaA.gAerolineaTable);
        areTextFieldEditable(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //Boton Nuevo
        if(e.getSource() == vistaA.gAerolinea_addB){
            AU = true;
            areTextFieldEditable(true);
        }
        //Boton Guardar
        if (e.getSource() == vistaA.gAerolinea_saveB){
            saveAirline();
        }
        //Boton Editar
        if (e.getSource() == vistaA.gAerolinea_editB){
            editAirline();
        }
        //Boton Eliminar
        if (e.getSource() == vistaA.gAerolinea_deleteB){
            deleteAirline();
            cleanAirline();
            listAirline(vistaA.gAerolineaTable);
        }
    }

    public void addAirline(){
        String nombre = vistaA.gAerolinea_nombreTF.getText();
        String clase = vistaA.gAerolinea_claseTF.getText();
        float precio = Float.parseFloat(vistaA.gAerolinea_precioTF.getText());
        a.setAirlineName(nombre);
        a.setFlyClass(clase);
        a.setPrice(precio);
        int r = dao.agregar(a);
        if (r == 1){
            JOptionPane.showMessageDialog(null, "Registro agregado exitosamente");
        }else{
            JOptionPane.showMessageDialog(null, "Registro fallido");
        }
    }

    public void updateAirline(){
        int id = Integer.parseInt(vistaA.gAerolinea_idTF.getText());
        String nombre = vistaA.gAerolinea_nombreTF.getText();
        String clase = vistaA.gAerolinea_claseTF.getText();
        float precio = Float.parseFloat(vistaA.gAerolinea_precioTF.getText());
        a.setAirlineID(id);
        a.setAirlineName(nombre);
        a.setFlyClass(clase);
        a.setPrice(precio);
        int r = dao.actualizar(a);
        if (r == 1){
            JOptionPane.showMessageDialog(null, "Registro actualizado exitosamente");
        }else{
            JOptionPane.showMessageDialog(null, "Registro fallido");
        }
    }

    public void deleteAirline(){
        int row = vistaA.gAerolineaTable.getSelectedRow();
        if (row == 1){
            JOptionPane.showMessageDialog(null, "Seleccione una Aerolinea");
        }else{
            int id = Integer.parseInt(vistaA.gAerolineaTable.getValueAt(row, 0).toString());
            dao.delete(id);
            JOptionPane.showMessageDialog(null, "Aerolinea eliminada exitosamente");
        }
    }

    private void saveAirline(){
        if (AU){
            addAirline();
            cleanAirline();
            listAirline(vistaA.gAerolineaTable);
            areTextFieldEditable(false);
            cleanForm();
        }else{
            updateAirline();
            cleanAirline();
            listAirline(vistaA.gAerolineaTable);
            areTextFieldEditable(false);
            cleanForm();
        }
    }

    private void editAirline(){
        AU = false;
        int row = vistaA.gAerolineaTable.getSelectedRow();
        if (row == -1){
            JOptionPane.showMessageDialog(null, "Seleccione una fila");
        }else{
            areTextFieldEditable(true);
            int id = Integer.parseInt(vistaA.gAerolineaTable.getValueAt(row, 0).toString());
            String nombre = (String) vistaA.gAerolineaTable.getValueAt(row, 1);
            String clase = (String) vistaA.gAerolineaTable.getValueAt(row, 2);
            float precio = Float.parseFloat(vistaA.gAerolineaTable.getValueAt(row, 3).toString());
            vistaA.gAerolinea_idTF.setText("" + id);
            vistaA.gAerolinea_nombreTF.setText("" + nombre);
            vistaA.gAerolinea_claseTF.setText("" + clase);
            vistaA.gAerolinea_precioTF.setText("" + precio);
        }
    }

    public void listAirline(JTable aerolineaTable){
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

    private void cleanAirline(){
        for(int i = 0; i < vistaA.gAerolineaTable.getRowCount(); i++){
            modelo.removeRow(i);
            i = i - 1;
        }
    }

    private void areTextFieldEditable(boolean flag){
        vistaA.gAerolinea_nombreTF.setEditable(flag);
        vistaA.gAerolinea_claseTF.setEditable(flag);
        vistaA.gAerolinea_precioTF.setEditable(flag);
    }

    private void cleanForm(){
        vistaA.gAerolinea_idTF.setText("");
        vistaA.gAerolinea_nombreTF.setText("");
        vistaA.gAerolinea_claseTF.setText("");
        vistaA.gAerolinea_precioTF.setText("");
    }
}
