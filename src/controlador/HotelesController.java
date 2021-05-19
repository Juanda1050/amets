package controlador;

import modelo.Hoteles;
import modelo.HotelesDAO;
import quinta.GestionarHoteles;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class HotelesController implements ActionListener {
    Hoteles h = new Hoteles();
    GestionarHoteles vistaH;
    HotelesDAO dao;
    DefaultTableModel modelo = new DefaultTableModel();
    private boolean AU = true;

    public HotelesController(GestionarHoteles vistaH, HotelesDAO dao){

        this.dao = dao;
        this.vistaH = vistaH;
        this.vistaH.gHotel_addB.addActionListener(this);
        this.vistaH.gHotel_saveB.addActionListener(this);
        this.vistaH.gHotel_editB.addActionListener(this);
        this.vistaH.gHotel_deleteB.addActionListener(this);
        listFly(vistaH.gHotelTable);
        areTextFieldEditable(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == vistaH.gHotel_addB){
            AU = true;
            areTextFieldEditable(true);
            boolean[] arr = {false, true, false, false};
            areButtonEnable(arr);
        }
        if(e.getSource() == vistaH.gHotel_saveB){
            saveFly();
        }
        if(e.getSource() == vistaH.gHotel_editB){
            editFly();
        }
        if(e.getSource() == vistaH.gHotel_deleteB){
            deleteFly();
            cleanFly();
            listFly(vistaH.gHotelTable);
        }
    }

    public void addFly(){
        if(vistaH.gHotel_nombreTF.getText().isEmpty() || vistaH.gHotel_destinoCB.getSelectedIndex() == 0 || vistaH.gHotel_regimenCB.getSelectedIndex() == 0 || vistaH.gHotel_clasifTF.getText().isEmpty() || vistaH.gHotel_genteTF.getText().isEmpty() || vistaH.gHotel_entradaDC.getText().isEmpty() || vistaH.gHotel_salidaDC.getText().isEmpty() || vistaH.gHotel_dispoCB.getSelectedIndex() == 0){
            JOptionPane.showMessageDialog(null, "Uno de los campos están vacios o no cumplen con los valores requeridos para continuar");
            boolean[] arr = {true, false, true, true};
            areButtonEnable(arr);
        }
        else{
            String nombre = vistaH.gHotel_nombreTF.getText();
            int destino = vistaH.gHotel_destinoCB.getSelectedIndex();
            int regimen = vistaH.gHotel_regimenCB.getSelectedIndex();
            String availability = vistaH.gHotel_dispoCB.getSelectedItem().toString();
            String localizacion = (vistaH.gHotel_ubicacionTF.getText());
            String rating = (vistaH.gHotel_clasifTF.getText());
            int guests = Integer.parseInt(vistaH.gHotel_genteTF.getText());
            String salida = vistaH.gHotel_salidaDC.getText();
            String llegada = vistaH.gHotel_entradaDC.getText();
            h.setHotelName(nombre);
            h.setDestinationID(destino);
            h.setRegime(regimen);
            h.setLocation(localizacion);
            h.setExitDate(salida);
            h.setEntryDate(llegada);
            h.setRating(rating);
            h.setGuests(guests);
            h.setAvailability(availability);
            int r = dao.agregar(h);
            if (r == 1){
                JOptionPane.showMessageDialog(null, "Registro agregado exitosamente");
            }
            else{
                JOptionPane.showMessageDialog(null, "Registro fallido");
            }
        }
    }

    public void updateFly(){
        if(vistaH.gHotel_nombreTF.getText().isEmpty() || vistaH.gHotel_destinoCB.getSelectedIndex() == 0 || vistaH.gHotel_regimenCB.getSelectedIndex() == 0 || vistaH.gHotel_clasifTF.getText().isEmpty() || vistaH.gHotel_genteTF.getText().isEmpty() || vistaH.gHotel_entradaDC.getText().isEmpty() || vistaH.gHotel_salidaDC.getText().isEmpty() || vistaH.gHotel_dispoCB.getSelectedIndex() == 0){
            JOptionPane.showMessageDialog(null, "Uno de los campos están vacios o no cumplen con los valores requeridos para continuar");
            boolean[] arr = {true, false, true, true, true};
            areButtonEnable(arr);
            if(!validarNombre(vistaH.gHotel_nombreTF.getText().trim())){
                JOptionPane.showMessageDialog(null, "Nombre solo contiene caracteres");
            }
        }
        else{
            int id = Integer.parseInt(vistaH.gHotel_idTF.getText());
            String nombre = vistaH.gHotel_nombreTF.getText();
            int destino = vistaH.gHotel_destinoCB.getSelectedIndex();
            int regimen = vistaH.gHotel_regimenCB.getSelectedIndex();
            String disponibilidad = vistaH.gHotel_dispoCB.getSelectedItem().toString();
            int gente = Integer.parseInt(vistaH.gHotel_genteTF.getText());
            String rating = (vistaH.gHotel_clasifTF.getText());
            String localizacion = (vistaH.gHotel_ubicacionTF.getText());
            String salida = vistaH.gHotel_salidaDC.getText();
            String llegada = vistaH.gHotel_entradaDC.getText();
            h.setHotelID(id);
            h.setHotelName(nombre);
            h.setDestinationID(destino);
            h.setRegime(regimen);
            h.setAvailability(disponibilidad);
            h.setGuests(gente);
            h.setRating(rating);
            h.setLocation(localizacion);
            h.setExitDate(salida);
            h.setEntryDate(llegada);
            int r = dao.actualizar(h);
            if (r == 1){
                JOptionPane.showMessageDialog(null, "Registro actualizado exitosamente");
            }
            else{
                JOptionPane.showMessageDialog(null, "Registro fallido");
            }
        }
    }

    public void deleteFly(){
        int row = vistaH.gHotelTable.getSelectedRow();
        if (row == 1){
            JOptionPane.showMessageDialog(null, "Seleccione un vuelo");
        }else{
            int id = Integer.parseInt(vistaH.gHotelTable.getValueAt(row, 0).toString());
            dao.eliminar(id);
            JOptionPane.showMessageDialog(null, "Vuelo eliminado exitosamente");
        }
    }

    private void saveFly(){
        if (AU){
            JOptionPane.showMessageDialog(null, "ADDING");
            addFly();
            cleanFly();
            listFly(vistaH.gHotelTable);
            areTextFieldEditable(false);
            cleanForm();

        }else{
            JOptionPane.showMessageDialog(null, "UPDATING");
            updateFly();
            cleanFly();
            listFly(vistaH.gHotelTable);
            areTextFieldEditable(false);
            cleanForm();
        }
    }

    private void editFly(){
        AU = false;
        int row = vistaH.gHotelTable.getSelectedRow();
        if (row == -1){
            JOptionPane.showMessageDialog(null, "Seleccione un Hotel");
        }else{
            areTextFieldEditable(true);
            int id = Integer.parseInt(vistaH.gHotelTable.getValueAt(row, 0).toString());
            int destino = Integer.parseInt(vistaH.gHotelTable.getValueAt(row, 1).toString());
            String nombre = (String) vistaH.gHotelTable.getValueAt(row,2);
            String localizacion = (String) vistaH.gHotelTable.getValueAt(row, 3);
            String rating = (String) vistaH.gHotelTable.getValueAt(row, 4);
            int gente = Integer.parseInt(vistaH.gHotelTable.getValueAt(row, 5).toString());
            int regimen = Integer.parseInt(vistaH.gHotelTable.getValueAt(row, 6).toString());
            int disponibilidad = Integer.parseInt(vistaH.gHotelTable.getValueAt(row, 7).toString());
            String salida = (String) vistaH.gHotelTable.getValueAt(row, 8);
            String llegada = (String) vistaH.gHotelTable.getValueAt(row, 9);

            vistaH.gHotel_idTF.setText("" + id);
            vistaH.gHotel_destinoCB.setSelectedIndex(0);
            vistaH.gHotel_nombreTF.setText("" + nombre);
            vistaH.gHotel_genteTF.setText("" + gente);
            vistaH.gHotel_ubicacionTF.setText("" + localizacion);
            vistaH.gHotel_clasifTF.setText("" + rating);
            vistaH.gHotel_regimenCB.setSelectedIndex(0);
            vistaH.gHotel_dispoCB.setSelectedIndex(0);
            vistaH.gHotel_entradaDC.getText();
            vistaH.gHotel_salidaDC.getText();

        }
    }

    public void listFly(JTable hotelTable){
        modelo = (DefaultTableModel) hotelTable.getModel();
        List<Hoteles> lista = dao.listar();
        Object[] object = new Object[10];
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );
        for (Hoteles hoteles : lista){
            object[0] = hoteles.getHotelID();
            object[1] = hoteles.getDestinationID();
            object[2] = hoteles.getHotelName();
            object[3] = hoteles.getLocation();
            object[4] = hoteles.getRating();
            object[5] = hoteles.getGuests();
            object[6] = hoteles.getRegime();
            object[7] = hoteles.getAvailability();
            object[8] = hoteles.getEntryDate();
            object[9] = hoteles.getExitDate();
            modelo.addRow(object);
        }
        vistaH.gHotelTable.setModel(modelo);
    }

    private void cleanFly(){
        for(int i = 0; i < vistaH.gHotelTable.getRowCount(); i++){
            modelo.removeRow(i);
            i = i - 1;
        }
    }

    private void areTextFieldEditable(boolean flag){
        vistaH.gHotel_destinoCB.setEditable(flag);
        vistaH.gHotel_nombreTF.setEditable(flag);
        vistaH.gHotel_ubicacionTF.setEditable(flag);
        vistaH.gHotel_clasifTF.setEditable(flag);
        vistaH.gHotel_genteTF.setEditable(flag);
        vistaH.gHotel_regimenCB.setEditable(flag);
        vistaH.gHotel_dispoCB.setEditable(flag);
        vistaH.gHotel_entradaDC.setEnabled(flag);
        vistaH.gHotel_salidaDC.setEnabled(flag);
    }
    private void areButtonEnable(boolean[] a){
        vistaH.gHotel_addB.setEnabled(a[0]);
        vistaH.gHotel_saveB.setEnabled(a[1]);
        vistaH.gHotel_editB.setEnabled(a[2]);
        vistaH.gHotel_deleteB.setEnabled(a[3]);
    }

    private boolean validarNombre(String datos){
        return datos.matches("[a-zA-z]{1,45}");
    }

    private void cleanForm(){
        boolean[] arr = {true, true, true, true};
        areButtonEnable(arr);
        vistaH.gHotel_destinoCB.setSelectedIndex(0);
        vistaH.gHotel_nombreTF.setText("");
        vistaH.gHotel_ubicacionTF.setText("");
        vistaH.gHotel_clasifTF.setText("");
        vistaH.gHotel_genteTF.setText("");
        vistaH.gHotel_regimenCB.setSelectedIndex(0);
        vistaH.gHotel_dispoCB.setSelectedIndex(0);
    }
}
