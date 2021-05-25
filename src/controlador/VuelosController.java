package controlador;

import modelo.Vuelos;
import modelo.VuelosDAO;
import vista.GestionarVuelos;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.List;

public class VuelosController implements ActionListener {
    Vuelos v = new Vuelos();
    GestionarVuelos vista;
    VuelosDAO dao;
    DefaultTableModel modelo = new DefaultTableModel();
    private boolean AU = true;
    Date currentDate = new Date();

    //Constructor del controlador
    public VuelosController(GestionarVuelos vista, VuelosDAO dao){
        int lista = dao.listar().size();
        if (lista > 0){
            this.dao = dao;
            this.vista = vista;
            this.vista.gVuelos_addB.addActionListener(this);
            this.vista.gVuelos_saveB.addActionListener(this);
            this.vista.gVuelos_editB.addActionListener(this);
            this.vista.gVuelos_deleteB.addActionListener(this);
            areTextFieldEditable(false);
            listFly(vista.gVuelosTable);
            boolean[] arr = {true, false, true, true, true};
            areButtonsEnabled(arr);
            mostrarDestinos();
            mostrarAero();
        }else{
            this.dao = dao;
            this.vista = vista;
            this.vista.gVuelos_addB.addActionListener(this);
            this.vista.gVuelos_saveB.addActionListener(this);
            this.vista.gVuelos_editB.addActionListener(this);
            this.vista.gVuelos_deleteB.addActionListener(this);
            areTextFieldEditable(false);
            listFly(vista.gVuelosTable);
            boolean[] arr = {true, false, false, false, true};
            areButtonsEnabled(arr);
            mostrarDestinos();
            mostrarAero();
        }
    }

    //Metodo para mostrar el contenido de la tabla destinos en el CombBox
    public void mostrarDestinos()
    {
        for (int i =0; i<dao.listarDestinos().size(); i++)
        {
            vista.gVuelos_destinoCB.addItem(dao.listarDestinos().get(i));
        }
    }

    //Metodo para mostrar el contenido de la tabla aerolinea en el CombBox
    public void mostrarAero(){
        for (int i =0; i<dao.listarAerolinea().size(); i++)
        {
            vista.gVuelos_aerolineaCB.addItem(dao.listarAerolinea().get(i));
        }
    }

    //ActionListener de cada boton de la vista
    @Override
    public void actionPerformed(ActionEvent e) {
        //Boton Nuevo
        if(e.getSource() == vista.gVuelos_addB){
            AU = true;
            areTextFieldEditable(true);
            vista.gVuelos_llegadaDC.setDate(currentDate);
            vista.gVuelos_salidaDC.setDate(currentDate);
            boolean[] arr = {false, true, false, false, false};
            areButtonsEnabled(arr);
        }
        //Boton Guardar
        if(e.getSource() == vista.gVuelos_saveB){
            saveFly();
        }
        //Boton Editar
        if(e.getSource() == vista.gVuelos_editB){
            editFly();
        }
        //Boton Eliminar
        if(e.getSource() == vista.gVuelos_deleteB){
            deleteFly();
            cleanFly();
            listFly(vista.gVuelosTable);
        }
    }

    //Metodo agregar
    public void addFly(){
        if(vista.gVuelos_origenTF.getText().isEmpty() || vista.gVuelos_destinoCB.getSelectedIndex() == 0 || vista.gVuelos_aerolineaCB.getSelectedIndex() == 0 || vista.gVuelos_genteTF.getText().isEmpty() || vista.gVuelos_salidaDC.getText().isEmpty() || vista.gVuelos_llegadaDC.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Uno de los campos están vacios o no cumplen con los valores requeridos para continuar");
            boolean[] arr = {false, true, false, false, false};
            areButtonsEnabled(arr);
        }
        else if (currentDate.after(vista.gVuelos_llegadaDC.getDate()) || currentDate.after(vista.gVuelos_salidaDC.getDate())){
            JOptionPane.showMessageDialog(null, "Ingrese una fecha mayor a la actual");
            boolean[] arr = {false, true, false, false, false};
            areButtonsEnabled(arr);
        }
        else{
            String origen = vista.gVuelos_origenTF.getText();

            String a = String.valueOf(vista.gVuelos_aerolineaCB.getSelectedItem());
            String an = null;
            if(a.contains(" - Turista")){
                an = a.replaceAll(" - Turista", "");
            }
            else if(a.contains(" - Primera Clase")){
                an = a.replaceAll(" - Primera Clase", "");
            }
            else if(a.contains(" - VIP")){
                an = a.replaceAll(" - VIP", "");
            }

            int destino = dao.destinoID((String) vista.gVuelos_destinoCB.getSelectedItem());
            int aero = dao.aeroID(an);
            int gente = Integer.parseInt(vista.gVuelos_genteTF.getText());
            String salida = vista.gVuelos_salidaDC.getText();
            String llegada = vista.gVuelos_llegadaDC.getText();
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
            areTextFieldEditable(false);
            cleanForm();
            cleanCB();
            boolean[] arr = {true, false, true, true, true};
            areButtonsEnabled(arr);
        }
    }

    //Metodo actualizar
    public void updateFly(){
        String d = (String) vista.gVuelos_destinoCB.getSelectedItem();
        if(vista.gVuelos_origenTF.getText().isEmpty() || d.compareTo("Seleccione destino")==0 || vista.gVuelos_genteTF.getText().isEmpty() || vista.gVuelos_salidaDC.getText().isEmpty() || vista.gVuelos_llegadaDC.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Uno de los campos están vacios o no cumplen con los valores requeridos para continuar");
            boolean[] arr = {false, true, false, false, false};
            areButtonsEnabled(arr);
        }else if (currentDate.after(vista.gVuelos_llegadaDC.getDate()) || currentDate.after(vista.gVuelos_salidaDC.getDate())){
            JOptionPane.showMessageDialog(null, "Ingrese una fecha mayor a la actual");
            boolean[] arr = {false, true, false, false, false};
            areButtonsEnabled(arr);
        }
        else{
            int id = Integer.parseInt(vista.gVuelos_idTF.getText());
            String origen = vista.gVuelos_origenTF.getText();

            String a = String.valueOf(vista.gVuelos_aerolineaCB.getSelectedItem());
            String an = null;
            if(a.contains(" - Turista")){
                an = a.replaceAll(" - Turista", "");
            }
            else if(a.contains(" - Primera Clase")){
                an = a.replaceAll(" - Primera Clase", "");
            }
            else if(a.contains(" - VIP")){
                an = a.replaceAll(" - VIP", "");
            }

            int destino = dao.destinoID((String) vista.gVuelos_destinoCB.getSelectedItem());
            int aero = dao.aeroID(an);
            int gente = Integer.parseInt(vista.gVuelos_genteTF.getText());
            String salida = vista.gVuelos_salidaDC.getText();
            String llegada = vista.gVuelos_llegadaDC.getText();
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
            areTextFieldEditable(false);
            cleanCB();
            cleanFly();
            cleanForm();
            listFly(vista.gVuelosTable);
            boolean[] arr = {true, false, true, true, true};
            areButtonsEnabled(arr);
        }
    }

    //Metodo eliminar
    public void deleteFly(){
        int row = vista.gVuelosTable.getSelectedRow();
        if (row == 1){
            JOptionPane.showMessageDialog(null, "Seleccione un vuelo");
        }else{
            int lista = dao.listar().size();
            if (lista > 1){
                int id = Integer.parseInt(vista.gVuelosTable.getValueAt(row, 0).toString());
                dao.eliminar(id);
                JOptionPane.showMessageDialog(null, "Registro eliminado exitosamente");
            }else{
                boolean[] arr = {true, false, false, false, true};
                areButtonsEnabled(arr);
                int id = Integer.parseInt(vista.gVuelosTable.getValueAt(row, 0).toString());
                dao.eliminar(id);
                JOptionPane.showMessageDialog(null, "Registro eliminado exitosamente");
            }

        }
    }

    //Metodo guardar
    private void saveFly(){
        if (AU){
            if(dao.listar().size() > 0){
                addFly();
                cleanFly();
                listFly(vista.gVuelosTable);
            }else{
                addFly();
                listFly(vista.gVuelosTable);
            }

        }else{
            updateFly();
        }
    }

    //Metodo para editar
    private void editFly(){
        AU = false;
        int row = vista.gVuelosTable.getSelectedRow();
        if (row == -1){
            JOptionPane.showMessageDialog(null, "Seleccione un vuelo");
        }else{
            areTextFieldEditable(true);
            boolean[] arr = {false, true, false, false, false};
            areButtonsEnabled(arr);
            int id = Integer.parseInt(vista.gVuelosTable.getValueAt(row, 0).toString());
            String origen = (String) vista.gVuelosTable.getValueAt(row, 1);
            String destino = (String) vista.gVuelosTable.getValueAt(row, 2);
            String aero = (String) vista.gVuelosTable.getValueAt(row, 3);
            int gente = Integer.parseInt(vista.gVuelosTable.getValueAt(row, 4).toString());
            String salida = (String) vista.gVuelosTable.getValueAt(row, 5);
            String llegada = (String) vista.gVuelosTable.getValueAt(row, 6);
            vista.gVuelos_idTF.setText("" + id);
            vista.gVuelos_origenTF.setText("" + origen);
            vista.gVuelos_genteTF.setText("" + gente);
            vista.gVuelos_salidaDC.setText(salida);
            vista.gVuelos_llegadaDC.setText(llegada);
            rellenarCB(destino, aero);
        }
    }

    //Listar la tabla vuelos en el JTable
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
        vista.gVuelosTable.setModel(modelo);
    }

    //Actualizar la Tabla cada que se hace un cambio
    private void cleanFly(){
        for(int i = 0; i < vista.gVuelosTable.getRowCount(); i++){
            modelo.removeRow(i);
            i = i - 1;
        }
    }

    //Hacer editable o no editable los TextField
    private void areTextFieldEditable(boolean flag){
        vista.gVuelos_origenTF.setEditable(flag);
        vista.gVuelos_destinoCB.setEnabled(flag);
        vista.gVuelos_aerolineaCB.setEnabled(flag);
        vista.gVuelos_genteTF.setEditable(flag);
        vista.gVuelos_salidaDC.setEnabled(flag);
        vista.gVuelos_llegadaDC.setEnabled(flag);
    }

    //Limpiar los componentes
    private void cleanForm(){
        vista.gVuelos_idTF.setText("");
        vista.gVuelos_origenTF.setText("");
        vista.gVuelos_destinoCB.setSelectedIndex(0);
        vista.gVuelos_aerolineaCB.setSelectedIndex(0);
        vista.gVuelos_genteTF.setText("");
        vista.gVuelos_salidaDC.setText("");
        vista.gVuelos_llegadaDC.setText("");
    }

    //Activar o desactivar botones
    private void areButtonsEnabled(boolean[] a) {
        vista.gVuelos_addB.setEnabled(a[0]);
        vista.gVuelos_saveB.setEnabled(a[1]);
        vista.gVuelos_editB.setEnabled(a[2]);
        vista.gVuelos_deleteB.setEnabled(a[3]);
        vista.gVuelos_airlineB.setEnabled(a[4]);
    }

    //Limpiar los ComboBox
    private void cleanCB(){
        vista.gVuelos_destinoCB.removeAllItems();
        vista.gVuelos_destinoCB.addItem("Seleccione destino");
        mostrarDestinos();
        vista.gVuelos_aerolineaCB.removeAllItems();
        vista.gVuelos_aerolineaCB.addItem("Seleccione aerolinea");
        mostrarAero();
    }

    //Metodo para llenar los ComboBox con lo que esta dentro de la tabla correspondiente en la BD
    private void rellenarCB(String destino, String aero){
        vista.gVuelos_destinoCB.removeAllItems();
        vista.gVuelos_destinoCB.addItem(destino);
        for (int i = 0; i < dao.listarDestinos().size(); i++){
            if(dao.listarDestinos().get(i).compareTo(destino) != 0){
                vista.gVuelos_destinoCB.addItem(dao.listarDestinos().get(i));
            }
        }
        vista.gVuelos_aerolineaCB.removeAllItems();
        vista.gVuelos_aerolineaCB.addItem(aero);
        for (int i = 0; i < dao.listarAerolinea().size(); i++){
            if(dao.listarAerolinea().get(i).compareTo(aero) != 0){
                vista.gVuelos_aerolineaCB.addItem(dao.listarAerolinea().get(i));
            }
        }
    }
}
