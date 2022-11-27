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
    GestionarHoteles vista;
    HotelesDAO dao;
    DefaultTableModel modelo = new DefaultTableModel();
    private boolean AU = true;
    Date currentDate = new Date();

    //Constructor del controlador
    public HotelesController(GestionarHoteles vista, HotelesDAO dao){
        int lista = dao.listar().size();
        if(lista > 0){
            this.dao = dao;
            this.vista = vista;
            this.vista.gHotel_addB.addActionListener(this);
            this.vista.gHotel_saveB.addActionListener(this);
            this.vista.gHotel_editB.addActionListener(this);
            this.vista.gHotel_deleteB.addActionListener(this);
            areTextFieldEditable(false);
            listHoteles(vista.gHotelTable);
            boolean[] arr = {true, false, true, true, true};
            areButtonsEnabled(arr);
            mostrarDestinos();
        }
        else{
            this.dao = dao;
            this.vista = vista;
            this.vista.gHotel_addB.addActionListener(this);
            this.vista.gHotel_saveB.addActionListener(this);
            this.vista.gHotel_editB.addActionListener(this);
            this.vista.gHotel_deleteB.addActionListener(this);
            areTextFieldEditable(false);
            listHoteles(vista.gHotelTable);
            boolean[] arr = {true, false, false, false, true};
            areButtonsEnabled(arr);
            mostrarDestinos();
        }
    }

    //Metodo para mostrar el contenido de la tabla destinos en el CombBox
    public void mostrarDestinos(){
        for (int i =0; i<dao.listarDestinos().size(); i++)
        {
            vista.gHotel_destinoCB.addItem(dao.listarDestinos().get(i));
        }
    }

    //ActionListener de cada boton de la vista
    @Override
    public void actionPerformed(ActionEvent e) {
        //Boton Nuevo
        if(e.getSource() == vista.gHotel_addB){
            AU = true;
            areTextFieldEditable(true);
            vista.gHotel_entradaDC.setDate(currentDate);
            vista.gHotel_salidaDC.setDate(currentDate);
            boolean[] arr = {false, true, false, false, false};
            areButtonsEnabled(arr);
        }
        //Boton Guardar
        if(e.getSource() == vista.gHotel_saveB){
            saveHoteles();
        }
        //Boton Editar
        if(e.getSource() == vista.gHotel_editB){
            editHoteles();
        }
        //Boton Eliminar
        if(e.getSource() == vista.gHotel_deleteB){
            deleteHoteles();
            cleanHoteles();
            listHoteles(vista.gHotelTable);
        }
    }

    //Metodo agregar
    public void addHoteles(){
        if(vista.gHotel_nombreTF.getText().isEmpty() || vista.gHotel_destinoCB.getSelectedIndex() == 0 || vista.gHotel_regimenCB.getSelectedIndex() == 0 || vista.gHotel_clasifTF.getText().isEmpty() || vista.gHotel_genteTF.getText().isEmpty() || vista.gHotel_entradaDC.getText().isEmpty() || vista.gHotel_salidaDC.getText().isEmpty() || vista.gHotel_dispoCB.getSelectedIndex() == 0){
            JOptionPane.showMessageDialog(null, "Uno de los campos están vacios o no cumplen con los valores requeridos para continuar");
            boolean[] arr = {false, true, false, false, false};
            areButtonsEnabled(arr);
        }
        else if (currentDate.after(vista.gHotel_entradaDC.getDate()) || currentDate.after(vista.gHotel_salidaDC.getDate())){
            JOptionPane.showMessageDialog(null, "Ingrese una fecha mayor a la actual");
            boolean[] arr = {false, true, false, false, false};
            areButtonsEnabled(arr);
        }
        else{
            String nombre = vista.gHotel_nombreTF.getText();
            int destino = dao.destinoID((String) vista.gHotel_destinoCB.getSelectedItem());
            int regimen = vista.gHotel_regimenCB.getSelectedIndex();
            int availability = vista.gHotel_dispoCB.getSelectedIndex();
            String localizacion = (vista.gHotel_ubicacionTF.getText());
            String rating = (vista.gHotel_clasifTF.getText());
            int guests = Integer.parseInt(vista.gHotel_genteTF.getText());
            String salida = vista.gHotel_salidaDC.getText();
            String llegada = vista.gHotel_entradaDC.getText();
            h.setHotelName(nombre);
            h.setDestinationID(destino);
            h.setRegime(regimen);
            h.setLocation(localizacion);
            h.setRating(rating);
            h.setGuests(guests);
            h.setAvailability(availability - 1);
            h.setEntryDate(llegada);
            h.setExitDate(salida);
            int r = dao.agregar(h);
            if (r == 1){
                JOptionPane.showMessageDialog(null, "Registro agregado exitosamente");
            }
            else{
                JOptionPane.showMessageDialog(null, "Registro fallido");
            }
            areTextFieldEditable(false);
            cleanForm();
            cleanCB();
            boolean[] arr = {true, false, true, true, true};
            areButtonsEnabled(arr);
        }
    }

    //Metodo actualizar
    public void updateHoteles(){
        if(vista.gHotel_nombreTF.getText().isEmpty() || vista.gHotel_regimenCB.getSelectedIndex() == 0 || vista.gHotel_clasifTF.getText().isEmpty() || vista.gHotel_genteTF.getText().isEmpty() || vista.gHotel_entradaDC.getText().isEmpty() || vista.gHotel_salidaDC.getText().isEmpty() || vista.gHotel_dispoCB.getSelectedIndex() == 0){
            JOptionPane.showMessageDialog(null, "Uno de los campos están vacios o no cumplen con los valores requeridos para continuar");
            boolean[] arr = {false, true, false, false, false};
            areButtonsEnabled(arr);
        }else if (currentDate.after(vista.gHotel_entradaDC.getDate()) || currentDate.after(vista.gHotel_salidaDC.getDate())){
            JOptionPane.showMessageDialog(null, "Ingrese una fecha mayor a la actual");
            boolean[] arr = {false, true, false, false, false};
            areButtonsEnabled(arr);
        }
        else{
            int id = Integer.parseInt(vista.gHotel_idTF.getText());
            String nombre = vista.gHotel_nombreTF.getText();
            int destino = dao.destinoID((String) vista.gHotel_destinoCB.getSelectedItem());
            int regimen = vista.gHotel_regimenCB.getSelectedIndex();
            int disponibilidad = Integer.parseInt(vista.gHotel_dispoCB.getSelectedItem().toString());
            int gente = Integer.parseInt(vista.gHotel_genteTF.getText());
            String rating = (vista.gHotel_clasifTF.getText());
            String localizacion = (vista.gHotel_ubicacionTF.getText());
            String salida = vista.gHotel_salidaDC.getText();
            String llegada = vista.gHotel_entradaDC.getText();
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
            areTextFieldEditable(false);
            cleanHoteles();
            cleanForm();
            cleanCB();
            listHoteles(vista.gHotelTable);
            boolean[] arr = {true, false, true, true, true};
            areButtonsEnabled(arr);
        }
    }

    //Metodo eliminar
    public void deleteHoteles(){
        int row = vista.gHotelTable.getSelectedRow();
        if (row == 1){
            JOptionPane.showMessageDialog(null, "Seleccione un hotel");
        }else{
            int lista = dao.listar().size();
            if (lista > 1) {
                int id = Integer.parseInt(vista.gHotelTable.getValueAt(row, 0).toString());
                dao.eliminar(id);
                JOptionPane.showMessageDialog(null, "Registro eliminado exitosamente");
            }else{
                boolean[] arr = {true, false, false, false, true};
                areButtonsEnabled(arr);
                int id = Integer.parseInt(vista.gHotelTable.getValueAt(row, 0).toString());
                dao.eliminar(id);
                JOptionPane.showMessageDialog(null, "Registro eliminado exitosamente");
            }
        }
    }

    //Metodo guardar
    private void saveHoteles(){
        if (AU){
            if(dao.listar().size() > 0 ){
                addHoteles();
                cleanHoteles();
                listHoteles(vista.gHotelTable);
            }
            else{
                addHoteles();
                listHoteles(vista.gHotelTable);
            }
        }else{
            updateHoteles();
        }
    }

    //Metodo editar
    private void editHoteles(){
        AU = false;
        int row = vista.gHotelTable.getSelectedRow();
        if (row == -1){
            JOptionPane.showMessageDialog(null, "Seleccione un hotel");
        }else{
            areTextFieldEditable(true);
            boolean[] arr = {false, true, false, false, false};
            areButtonsEnabled(arr);
            int id = Integer.parseInt(vista.gHotelTable.getValueAt(row, 0).toString());
            String Destino = (String) vista.gHotelTable.getValueAt(row, 1);
            String nombre = (String) vista.gHotelTable.getValueAt(row,2);
            String localizacion = (String) vista.gHotelTable.getValueAt(row, 3);
            String rating = (String) vista.gHotelTable.getValueAt(row, 4);
            int gente = Integer.parseInt(vista.gHotelTable.getValueAt(row, 5).toString());
            int regimen = Integer.parseInt(vista.gHotelTable.getValueAt(row, 6).toString());
            int disponibilidad = Integer.parseInt(vista.gHotelTable.getValueAt(row, 7).toString());
            String salida = (String) vista.gHotelTable.getValueAt(row, 8);
            String llegada = (String) vista.gHotelTable.getValueAt(row, 9);
            vista.gHotel_idTF.setText("" + id);
            vista.gHotel_nombreTF.setText("" + nombre);
            vista.gHotel_genteTF.setText("" + gente);
            vista.gHotel_ubicacionTF.setText("" + localizacion);
            vista.gHotel_clasifTF.setText("" + rating);
            vista.gHotel_regimenCB.setSelectedIndex(regimen);
            vista.gHotel_dispoCB.setSelectedIndex(disponibilidad + 1);
            vista.gHotel_entradaDC.setText(salida);
            vista.gHotel_salidaDC.setText(llegada);
            rellenarCB(Destino);
        }
    }

    //Listar la tabla hotel en el JTable
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
        vista.gHotelTable.setModel(modelo);
    }

    //Actualizar la Tabla cada que se hace un cambio
    private void cleanHoteles(){
        for(int i = 0; i < vista.gHotelTable.getRowCount(); i++){
            modelo.removeRow(i);
            i = i - 1;
        }
    }

    //Hacer editable o no editable los TextField
    private void areTextFieldEditable(boolean flag){
        vista.gHotel_destinoCB.setEnabled(flag);
        vista.gHotel_nombreTF.setEditable(flag);
        vista.gHotel_ubicacionTF.setEditable(flag);
        vista.gHotel_clasifTF.setEditable(flag);
        vista.gHotel_genteTF.setEditable(flag);
        vista.gHotel_regimenCB.setEnabled(flag);
        vista.gHotel_dispoCB.setEnabled(flag);
        vista.gHotel_entradaDC.setEnabled(flag);
        vista.gHotel_salidaDC.setEnabled(flag);
    }

    //Activar o desactivar botones
    private void areButtonsEnabled(boolean[] a){
        vista.gHotel_addB.setEnabled(a[0]);
        vista.gHotel_saveB.setEnabled(a[1]);
        vista.gHotel_editB.setEnabled(a[2]);
        vista.gHotel_deleteB.setEnabled(a[3]);
        vista.gHotel_hotelB.setEnabled(a[4]);
    }

    //Limpiar los componentes
    private void cleanForm(){
        vista.gHotel_idTF.setText("");
        vista.gHotel_nombreTF.setText("");
        vista.gHotel_destinoCB.setSelectedIndex(0);
        vista.gHotel_ubicacionTF.setText("");
        vista.gHotel_clasifTF.setText("");
        vista.gHotel_genteTF.setText("");
        vista.gHotel_regimenCB.setSelectedIndex(0);
        vista.gHotel_dispoCB.setSelectedIndex(0);
        vista.gHotel_salidaDC.setText("");
        vista.gHotel_entradaDC.setText("");
    }

    //Metodo para llenar los ComboBox con lo que esta dentro de la tabla correspondiente en la BD
    private void rellenarCB(String Destino){
        vista.gHotel_destinoCB.removeAllItems();
        vista.gHotel_destinoCB.addItem(Destino);
        for (int i = 0; i<dao.listarDestinos().size();i++){
            if (dao.listarDestinos().get(i).compareTo(Destino) != 0){
                vista.gHotel_destinoCB.addItem(dao.listarDestinos().get(i));
            }
        }
    }

    //Reinicia los ComboBox
    private void cleanCB(){
        vista.gHotel_destinoCB.removeAllItems();
        vista.gHotel_destinoCB.addItem("Seleccione un destino");
        mostrarDestinos();
    }
}