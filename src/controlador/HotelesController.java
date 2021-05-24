package controlador;
import modelo.Hoteles;
import modelo.HotelesDAO;
import vista.GestionarHoteles;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.List;

public class HotelesController implements ActionListener {
    Hoteles h = new Hoteles();
    GestionarHoteles vistaH;
    HotelesDAO dao;
    DefaultTableModel modelo = new DefaultTableModel();
    private boolean AU = true;
    Date currentDate = new Date();

    public HotelesController(GestionarHoteles vistaH, HotelesDAO dao){
        int lista = dao.listar().size();
        if(lista > 0){
            this.dao = dao;
            this.vistaH = vistaH;
            this.vistaH.gHotel_addB.addActionListener(this);
            this.vistaH.gHotel_saveB.addActionListener(this);
            this.vistaH.gHotel_editB.addActionListener(this);
            this.vistaH.gHotel_deleteB.addActionListener(this);
            listHoteles(vistaH.gHotelTable);
            boolean[] arr = {true, false, true, true, true};
            areButtonEnable(arr);
            mostrarDestinos();
        }
        else{
            this.dao = dao;
            this.vistaH = vistaH;
            this.vistaH.gHotel_addB.addActionListener(this);
            this.vistaH.gHotel_saveB.addActionListener(this);
            this.vistaH.gHotel_editB.addActionListener(this);
            this.vistaH.gHotel_deleteB.addActionListener(this);
            listHoteles(vistaH.gHotelTable);
            boolean[] arr = {true, false, false, false, true};
            areButtonEnable(arr);
            mostrarDestinos();
        }
        areTextFieldEditable(false);
    }

    public void mostrarDestinos(){
        for (int i =0; i<dao.listarDestinos().size(); i++)
        {
            vistaH.gHotel_destinoCB.addItem(dao.listarDestinos().get(i));
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == vistaH.gHotel_addB){
            AU = true;
            areTextFieldEditable(true);
            vistaH.gHotel_entradaDC.setDate(currentDate);
            vistaH.gHotel_salidaDC.setDate(currentDate);
            boolean[] arr = {false, true, false, false, false};
            areButtonEnable(arr);
        }
        if(e.getSource() == vistaH.gHotel_saveB){
            saveHoteles();
        }
        if(e.getSource() == vistaH.gHotel_editB){
            editHoteles();
        }
        if(e.getSource() == vistaH.gHotel_deleteB){
            deleteHoteles();
            cleanHoteles();
            listHoteles(vistaH.gHotelTable);
        }
    }

    public void addHoteles(){
        if(vistaH.gHotel_nombreTF.getText().isEmpty() || vistaH.gHotel_destinoCB.getSelectedIndex() == 0 || vistaH.gHotel_regimenCB.getSelectedIndex() == 0 || vistaH.gHotel_clasifTF.getText().isEmpty() || vistaH.gHotel_genteTF.getText().isEmpty() || vistaH.gHotel_entradaDC.getText().isEmpty() || vistaH.gHotel_salidaDC.getText().isEmpty() || vistaH.gHotel_dispoCB.getSelectedIndex() == 0){
            JOptionPane.showMessageDialog(null, "Uno de los campos están vacios o no cumplen con los valores requeridos para continuar");
            boolean[] arr = {true, false, true, true, true};
            areButtonEnable(arr);
        }
        else if (currentDate.after(vistaH.gHotel_entradaDC.getDate()) || currentDate.after(vistaH.gHotel_salidaDC.getDate())){
            JOptionPane.showMessageDialog(null, "Ingrese una fecha mayor a la actual");
            boolean[] arr = {true, false, true, true, true};
            areButtonEnable(arr);
        }
        else{
            String nombre = vistaH.gHotel_nombreTF.getText();
            int destino = vistaH.gHotel_destinoCB.getSelectedIndex();
            int regimen = vistaH.gHotel_regimenCB.getSelectedIndex();
            int availability = vistaH.gHotel_dispoCB.getSelectedIndex();
            String localizacion = (vistaH.gHotel_ubicacionTF.getText());
            String rating = (vistaH.gHotel_clasifTF.getText());
            int guests = Integer.parseInt(vistaH.gHotel_genteTF.getText());
            String salida = vistaH.gHotel_salidaDC.getText();
            String llegada = vistaH.gHotel_entradaDC.getText();
            h.setHotelName(nombre);
            h.setDestinationID(destino);
            h.setRegime(regimen);
            h.setLocation(localizacion);
            h.setRating(rating);
            h.setGuests(guests);
            h.setAvailability(availability);
            h.setEntryDate(llegada);
            h.setExitDate(salida);
            int r = dao.agregar(h);
            if (r == 1){
                JOptionPane.showMessageDialog(null, "Registro agregado exitosamente");
            }
            else{
                JOptionPane.showMessageDialog(null, "Registro fallido");
            }
            boolean[] arr = {true, false, true, true, true};
            areButtonEnable(arr);
        }
    }

    public void updateHoteles(){
        if(vistaH.gHotel_nombreTF.getText().isEmpty() || vistaH.gHotel_regimenCB.getSelectedIndex() == 0 || vistaH.gHotel_clasifTF.getText().isEmpty() || vistaH.gHotel_genteTF.getText().isEmpty() || vistaH.gHotel_entradaDC.getText().isEmpty() || vistaH.gHotel_salidaDC.getText().isEmpty() || vistaH.gHotel_dispoCB.getSelectedIndex() == 0){
            JOptionPane.showMessageDialog(null, "Uno de los campos están vacios o no cumplen con los valores requeridos para continuar");
            boolean[] arr = {true, false, true, true, true};
            areButtonEnable(arr);
        }else if (currentDate.after(vistaH.gHotel_entradaDC.getDate()) || currentDate.after(vistaH.gHotel_salidaDC.getDate())){
            JOptionPane.showMessageDialog(null, "Ingrese una fecha mayor a la actual");
            boolean[] arr = {true, false, true, true, true};
            areButtonEnable(arr);
        }
        else{
            int id = Integer.parseInt(vistaH.gHotel_idTF.getText());
            String nombre = vistaH.gHotel_nombreTF.getText();
            int destino = dao.destinoID((String) vistaH.gHotel_destinoCB.getSelectedItem());
            int regimen = vistaH.gHotel_regimenCB.getSelectedIndex();
            int disponibilidad = Integer.parseInt(vistaH.gHotel_dispoCB.getSelectedItem().toString());
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

    public void deleteHoteles(){
        int row = vistaH.gHotelTable.getSelectedRow();
        if (row == 1){
            JOptionPane.showMessageDialog(null, "Seleccione un hotel");
        }else{
            int lista = dao.listar().size();
            if (lista > 1) {
                int id = Integer.parseInt(vistaH.gHotelTable.getValueAt(row, 0).toString());
                dao.eliminar(id);
                JOptionPane.showMessageDialog(null, "Hotel eliminado exitosamente");
            }else{
                boolean[] arr = {true, false, false, false, true};
                areButtonEnable(arr);
                int id = Integer.parseInt(vistaH.gHotelTable.getValueAt(row, 0).toString());
                dao.eliminar(id);
                JOptionPane.showMessageDialog(null, "Hotel eliminado exitosamente");
            }
        }
    }

    private void saveHoteles(){
        if (AU){
            if(dao.listar().size() > 0 ){
                addHoteles();
                cleanHoteles();
                listHoteles(vistaH.gHotelTable);
                areTextFieldEditable(false);
                cleanForm();
                cleanCB();
            }
            else{
                addHoteles();
                listHoteles(vistaH.gHotelTable);
                areTextFieldEditable(false);
                cleanForm();
                cleanCB();
            }
        }else{
            updateHoteles();
            cleanHoteles();
            listHoteles(vistaH.gHotelTable);
            areTextFieldEditable(false);
            cleanForm();
            cleanCB();
        }
        boolean[] arr = {true, false, true, true, true};
        areButtonEnable(arr);
    }

    private void editHoteles(){
        AU = false;
        int row = vistaH.gHotelTable.getSelectedRow();
        if (row == -1){
            JOptionPane.showMessageDialog(null, "Seleccione un hotel");
        }else{
            areTextFieldEditable(true);
            boolean[] arr = {false, true, false, false, false};
            areButtonEnable(arr);
            int id = Integer.parseInt(vistaH.gHotelTable.getValueAt(row, 0).toString());
            String Destino = (String) vistaH.gHotelTable.getValueAt(row, 1);
            String nombre = (String) vistaH.gHotelTable.getValueAt(row,2);
            String localizacion = (String) vistaH.gHotelTable.getValueAt(row, 3);
            String rating = (String) vistaH.gHotelTable.getValueAt(row, 4);
            int gente = Integer.parseInt(vistaH.gHotelTable.getValueAt(row, 5).toString());
            int regimen = Integer.parseInt(vistaH.gHotelTable.getValueAt(row, 6).toString());
            int disponibilidad = Integer.parseInt(vistaH.gHotelTable.getValueAt(row, 7).toString());
            String salida = (String) vistaH.gHotelTable.getValueAt(row, 8);
            String llegada = (String) vistaH.gHotelTable.getValueAt(row, 9);

            vistaH.gHotel_idTF.setText("" + id);
            vistaH.gHotel_nombreTF.setText("" + nombre);
            vistaH.gHotel_genteTF.setText("" + gente);
            vistaH.gHotel_ubicacionTF.setText("" + localizacion);
            vistaH.gHotel_clasifTF.setText("" + rating);
            vistaH.gHotel_regimenCB.setSelectedIndex(regimen);
            System.out.println(disponibilidad);
            vistaH.gHotel_dispoCB.setSelectedIndex(disponibilidad + 1);
            vistaH.gHotel_entradaDC.setText(salida);
            vistaH.gHotel_salidaDC.setText(llegada);
            rellenarCB(Destino);
        }
    }

    public void listHoteles(JTable hotelTable){
        modelo = (DefaultTableModel) hotelTable.getModel();
        List<Hoteles> lista = dao.listar();
        Object[] object = new Object[10];
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );
        for (Hoteles hoteles : lista){
            object[0] = hoteles.getHotelID();
            object[1] = hoteles.getDestinationName();
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

    private void cleanHoteles(){
        for(int i = 0; i < vistaH.gHotelTable.getRowCount(); i++){
            modelo.removeRow(i);
            i = i - 1;
        }
    }

    private void areTextFieldEditable(boolean flag){
        vistaH.gHotel_destinoCB.setEnabled(flag);
        vistaH.gHotel_nombreTF.setEditable(flag);
        vistaH.gHotel_ubicacionTF.setEditable(flag);
        vistaH.gHotel_clasifTF.setEditable(flag);
        vistaH.gHotel_genteTF.setEditable(flag);
        vistaH.gHotel_regimenCB.setEnabled(flag);
        vistaH.gHotel_dispoCB.setEnabled(flag);
        vistaH.gHotel_entradaDC.setEnabled(flag);
        vistaH.gHotel_salidaDC.setEnabled(flag);
    }
    private void areButtonEnable(boolean[] a){
        vistaH.gHotel_addB.setEnabled(a[0]);
        vistaH.gHotel_saveB.setEnabled(a[1]);
        vistaH.gHotel_editB.setEnabled(a[2]);
        vistaH.gHotel_deleteB.setEnabled(a[3]);
        vistaH.gHotel_hotelB.setEnabled(a[4]);
    }

    private void cleanForm(){
        vistaH.gHotel_idTF.setText("");
        vistaH.gHotel_nombreTF.setText("");
        vistaH.gHotel_destinoCB.setSelectedIndex(0);
        vistaH.gHotel_ubicacionTF.setText("");
        vistaH.gHotel_clasifTF.setText("");
        vistaH.gHotel_genteTF.setText("");
        vistaH.gHotel_regimenCB.setSelectedIndex(0);
        vistaH.gHotel_dispoCB.setSelectedIndex(0);
        vistaH.gHotel_salidaDC.setText("");
        vistaH.gHotel_entradaDC.setText("");
    }
    private void rellenarCB(String Destino){
        vistaH.gHotel_destinoCB.removeAllItems();
        vistaH.gHotel_destinoCB.addItem(Destino);
        for (int i = 0; i<dao.listarDestinos().size();i++){
            if (dao.listarDestinos().get(i).compareTo(Destino) != 0){
                vistaH.gHotel_destinoCB.addItem(dao.listarDestinos().get(i));
            }
        }
    }

    private void cleanCB(){
        vistaH.gHotel_destinoCB.removeAllItems();
        vistaH.gHotel_destinoCB.addItem("Seleccione un destino");
        mostrarDestinos();
    }
}
