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
    GestionarVuelos vistaV;
    VuelosDAO dao;
    DefaultTableModel modelo = new DefaultTableModel();
    private boolean AU = true;
    Date currentDate = new Date();

    //Constructor del controlador
    public VuelosController(GestionarVuelos vistaV, VuelosDAO dao){
        int lista = dao.listar().size();
        if (lista > 0){
            this.dao = dao;
            this.vistaV = vistaV;
            this.vistaV.gVuelos_addB.addActionListener(this);
            this.vistaV.gVuelos_saveB.addActionListener(this);
            this.vistaV.gVuelos_editB.addActionListener(this);
            this.vistaV.gVuelos_deleteB.addActionListener(this);
            listFly(vistaV.gVuelosTable);
            boolean[] arr = {true, false, true, true, true};
            areButtonsEnabled(arr);
            mostrarDestinos();
            mostrarAero();
        }else{
            this.dao = dao;
            this.vistaV = vistaV;
            this.vistaV.gVuelos_addB.addActionListener(this);
            this.vistaV.gVuelos_saveB.addActionListener(this);
            this.vistaV.gVuelos_editB.addActionListener(this);
            this.vistaV.gVuelos_deleteB.addActionListener(this);
            listFly(vistaV.gVuelosTable);
            boolean[] arr = {true, false, false, false, true};
            areButtonsEnabled(arr);
            mostrarDestinos();
            mostrarAero();
        }
        areTextFieldEditable(false);
    }

    //Metodo para mostrar el contenido de la tabla destinos en el CombBox
    public void mostrarDestinos()
    {
        for (int i =0; i<dao.listarDestinos().size(); i++)
        {
            vistaV.gVuelos_destinoCB.addItem(dao.listarDestinos().get(i));
        }
    }

    //Metodo para mostrar el contenido de la tabla aerolinea en el CombBox
    public void mostrarAero(){
        for (int i =0; i<dao.listarAerolinea().size(); i++)
        {
            vistaV.gVuelos_aerolineaCB.addItem(dao.listarAerolinea().get(i));
        }
    }

    //ActionListener de cada boton de la vista
    @Override
    public void actionPerformed(ActionEvent e) {
        //Boton Nuevo
        if(e.getSource() == vistaV.gVuelos_addB){
            AU = true;
            areTextFieldEditable(true);
            vistaV.gVuelos_llegadaDC.setDate(currentDate);
            vistaV.gVuelos_salidaDC.setDate(currentDate);
            boolean[] arr = {false, true, false, false, false};
            areButtonsEnabled(arr);
        }
        //Boton Guardar
        if(e.getSource() == vistaV.gVuelos_saveB){
            saveFly();
        }
        //Boton Editar
        if(e.getSource() == vistaV.gVuelos_editB){
            editFly();
        }
        //Boton Eliminar
        if(e.getSource() == vistaV.gVuelos_deleteB){
            deleteFly();
            cleanFly();
            listFly(vistaV.gVuelosTable);
        }
    }

    //Metodo agregar
    public void addFly(){
        if(vistaV.gVuelos_origenTF.getText().isEmpty() || vistaV.gVuelos_destinoCB.getSelectedIndex() == 0 || vistaV.gVuelos_aerolineaCB.getSelectedIndex() == 0 || vistaV.gVuelos_genteTF.getText().isEmpty() || vistaV.gVuelos_salidaDC.getText().isEmpty() || vistaV.gVuelos_llegadaDC.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Uno de los campos están vacios o no cumplen con los valores requeridos para continuar");
            boolean[] arr = {false, true, false, false, false};
            areButtonsEnabled(arr);
        }
        else if (currentDate.after(vistaV.gVuelos_llegadaDC.getDate()) || currentDate.after(vistaV.gVuelos_salidaDC.getDate())){
            JOptionPane.showMessageDialog(null, "Ingrese una fecha mayor a la actual");
            boolean[] arr = {true, false, true, true, true};
            areButtonsEnabled(arr);
        }
        else{
            String origen = vistaV.gVuelos_origenTF.getText();
            int destino = dao.destinoID((String) vistaV.gVuelos_destinoCB.getSelectedItem());
            int aero = dao.aeroID((String) vistaV.gVuelos_aerolineaCB.getSelectedItem());
            int gente = Integer.parseInt(vistaV.gVuelos_genteTF.getText());
            String salida = vistaV.gVuelos_salidaDC.getText();
            String llegada = vistaV.gVuelos_llegadaDC.getText();
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
            boolean[] arr = {true, false, true, true, true};
            areButtonsEnabled(arr);
        }
    }

    //Metodo actualizar
    public void updateFly(){
        if(vistaV.gVuelos_origenTF.getText().isEmpty() || vistaV.gVuelos_genteTF.getText().isEmpty() || vistaV.gVuelos_salidaDC.getText().isEmpty() || vistaV.gVuelos_llegadaDC.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Uno de los campos están vacios o no cumplen con los valores requeridos para continuar");
            boolean[] arr = {false, true, false, false, false};
            areButtonsEnabled(arr);
        }else if (currentDate.after(vistaV.gVuelos_llegadaDC.getDate()) || currentDate.after(vistaV.gVuelos_salidaDC.getDate())){
            JOptionPane.showMessageDialog(null, "Ingrese una fecha mayor a la actual");
            boolean[] arr = {false, true, false, false, false};
            areButtonsEnabled(arr);
        }
        else{
            int id = Integer.parseInt(vistaV.gVuelos_idTF.getText());
            String origen = vistaV.gVuelos_origenTF.getText();
            int destino = dao.destinoID((String) vistaV.gVuelos_destinoCB.getSelectedItem());
            int aero = vistaV.gVuelos_aerolineaCB.getSelectedIndex();
            int gente = Integer.parseInt(vistaV.gVuelos_genteTF.getText());
            String salida = vistaV.gVuelos_salidaDC.getText();
            String llegada = vistaV.gVuelos_llegadaDC.getText();
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
            boolean[] arr = {true, false, true, true, true};
            areButtonsEnabled(arr);
        }
    }

    //Metodo eliminar
    public void deleteFly(){
        int row = vistaV.gVuelosTable.getSelectedRow();
        if (row == 1){
            JOptionPane.showMessageDialog(null, "Seleccione un vuelo");
        }else{
            int lista = dao.listar().size();
            if (lista > 1){
                int id = Integer.parseInt(vistaV.gVuelosTable.getValueAt(row, 0).toString());
                dao.eliminar(id);
                JOptionPane.showMessageDialog(null, "Vuelo eliminado exitosamente");
            }else{
                boolean[] arr = {true, false, false, false, true};
                areButtonsEnabled(arr);
                int id = Integer.parseInt(vistaV.gVuelosTable.getValueAt(row, 0).toString());
                dao.eliminar(id);
                JOptionPane.showMessageDialog(null, "Vuelo eliminado exitosamente");
            }

        }
    }

    //Metodo guardar
    private void saveFly(){
        if (AU){
            if(dao.listar().size() > 0){
                addFly();
                cleanFly();
                listFly(vistaV.gVuelosTable);
                areTextFieldEditable(false);
                cleanForm();
                cleanCB();
            }else{
                addFly();
                listFly(vistaV.gVuelosTable);
                areTextFieldEditable(false);
                cleanForm();
                cleanCB();
            }

        }else{
            updateFly();
            cleanFly();
            listFly(vistaV.gVuelosTable);
            areTextFieldEditable(false);
            cleanForm();
            cleanCB();
        }
        boolean[] arr = {true, false, true, true, true};
        areButtonsEnabled(arr);
    }

    //Metodo para editar
    private void editFly(){
        AU = false;
        int row = vistaV.gVuelosTable.getSelectedRow();
        if (row == -1){
            JOptionPane.showMessageDialog(null, "Seleccione un vuelo");
        }else{
            areTextFieldEditable(true);
            boolean[] arr = {false, true, false, false, false};
            areButtonsEnabled(arr);
            int id = Integer.parseInt(vistaV.gVuelosTable.getValueAt(row, 0).toString());
            String origen = (String) vistaV.gVuelosTable.getValueAt(row, 1);
            String destino = (String) vistaV.gVuelosTable.getValueAt(row, 2);
            String aero = (String) vistaV.gVuelosTable.getValueAt(row, 3);
            int gente = Integer.parseInt(vistaV.gVuelosTable.getValueAt(row, 4).toString());
            String salida = (String) vistaV.gVuelosTable.getValueAt(row, 5);
            String llegada = (String) vistaV.gVuelosTable.getValueAt(row, 6);
            vistaV.gVuelos_idTF.setText("" + id);
            vistaV.gVuelos_origenTF.setText("" + origen);
            vistaV.gVuelos_genteTF.setText("" + gente);
            vistaV.gVuelos_salidaDC.setText(salida);
            vistaV.gVuelos_llegadaDC.setText(llegada);
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
        vistaV.gVuelosTable.setModel(modelo);
    }

    //Actualizar la Tabla cada que se hace un cambio
    private void cleanFly(){
        for(int i = 0; i < vistaV.gVuelosTable.getRowCount(); i++){
            modelo.removeRow(i);
            i = i - 1;
        }
    }

    //Hacer editable o no editable los TextField
    private void areTextFieldEditable(boolean flag){
        vistaV.gVuelos_origenTF.setEditable(flag);
        vistaV.gVuelos_destinoCB.setEnabled(flag);
        vistaV.gVuelos_aerolineaCB.setEnabled(flag);
        vistaV.gVuelos_genteTF.setEditable(flag);
        vistaV.gVuelos_salidaDC.setEnabled(flag);
        vistaV.gVuelos_llegadaDC.setEnabled(flag);
    }

    //Limpiar los componentes
    private void cleanForm(){
        vistaV.gVuelos_idTF.setText("");
        vistaV.gVuelos_origenTF.setText("");
        vistaV.gVuelos_destinoCB.setSelectedIndex(0);
        vistaV.gVuelos_aerolineaCB.setSelectedIndex(0);
        vistaV.gVuelos_genteTF.setText("");
        vistaV.gVuelos_salidaDC.setText("");
        vistaV.gVuelos_llegadaDC.setText("");
    }

    //Activar o desactivar botones
    private void areButtonsEnabled(boolean[] a) {
        vistaV.gVuelos_addB.setEnabled(a[0]);
        vistaV.gVuelos_saveB.setEnabled(a[1]);
        vistaV.gVuelos_editB.setEnabled(a[2]);
        vistaV.gVuelos_deleteB.setEnabled(a[3]);
        vistaV.gVuelos_airlineB.setEnabled(a[4]);
    }

    //Limpiar los ComboBox
    private void cleanCB(){
        vistaV.gVuelos_destinoCB.removeAllItems();
        vistaV.gVuelos_destinoCB.addItem("Seleccione destino");
        mostrarDestinos();
        vistaV.gVuelos_aerolineaCB.removeAllItems();
        vistaV.gVuelos_aerolineaCB.addItem("Seleccione aerolinea");
        mostrarAero();
    }

    //Metodo para llenar los ComboBox con lo que esta dentro de la tabla correspondiente en la BD
    private void rellenarCB(String destino, String aero){
        vistaV.gVuelos_destinoCB.removeAllItems();
        vistaV.gVuelos_destinoCB.addItem(destino);
        for (int i = 0; i < dao.listarDestinos().size(); i++){
            if(dao.listarDestinos().get(i).compareTo(destino) != 0){
                vistaV.gVuelos_destinoCB.addItem(dao.listarDestinos().get(i));
            }
        }
        vistaV.gVuelos_aerolineaCB.removeAllItems();
        vistaV.gVuelos_aerolineaCB.addItem(aero);
        for (int i = 0; i < dao.listarAerolinea().size(); i++){
            if(dao.listarAerolinea().get(i).compareTo(aero) != 0){
                vistaV.gVuelos_aerolineaCB.addItem(dao.listarAerolinea().get(i));
            }
        }
    }
}
