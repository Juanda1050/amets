package controlador;

import modelo.DHoteles;
import modelo.DHotelesDAO;
import vista.DetalleHotel;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class DHotelesController implements ActionListener {
    DHoteles dh = new DHoteles();
    DetalleHotel vista;
    DHotelesDAO dao;
    DefaultTableModel modelo = new DefaultTableModel();
    private boolean AU = true;

    //Constructor del controlador
    public DHotelesController(DetalleHotel vista, DHotelesDAO dao){
        int lista = dao.listar().size();
        if (lista > 0){
            this.dao = dao;
            this.vista = vista;
            this.vista.dHotel_addB.addActionListener(this);
            this.vista.dHotel_saveB.addActionListener(this);
            this.vista.dHotel_editB.addActionListener(this);
            this.vista.dHotel_deleteB.addActionListener(this);
            areTextFieldEditable(false);
            listHotel(vista.dHotelTable);
            boolean[] arr = {true, false, true, true};
            areButtonsEnabled(arr);
            mostrarRoom();
            mostrarHoteles();
        }else{
            this.dao = dao;
            this.vista = vista;
            this.vista.dHotel_addB.addActionListener(this);
            this.vista.dHotel_saveB.addActionListener(this);
            this.vista.dHotel_editB.addActionListener(this);
            this.vista.dHotel_deleteB.addActionListener(this);
            areTextFieldEditable(false);
            listHotel(vista.dHotelTable);
            boolean[] arr = {true, false, false, false};
            areButtonsEnabled(arr);
            mostrarHoteles();
            mostrarRoom();
        }
    }

    public void mostrarHoteles()
    {
        for (int i =0; i<dao.listarhotel().size(); i++)
        {
            vista.dHotel_hotelCB.addItem(dao.listarhotel().get(i));
        }
    }

    public void mostrarRoom(){
        for (int i = 0; i < dao.listarRoom().size(); i++){
            vista.dHotel_habitacionCB.addItem(dao.listarRoom().get(i));
        }
    }

    //ActionListener de cada boton de la vista
    @Override
    public void actionPerformed(ActionEvent e) {
        //Boton Nuevo
        if(e.getSource() == vista.dHotel_addB){
            AU = true;
            areTextFieldEditable(true);
            boolean[] arr = {false, true, false, false};
            areButtonsEnabled(arr);
        }
        //Boton Guardar
        if(e.getSource() == vista.dHotel_saveB){
            saveHotel();
        }
        //Boton Editar
        if(e.getSource() == vista.dHotel_editB){
            editHotel();
        }
        //Boton Eliminar
        if(e.getSource() == vista.dHotel_deleteB){
            deleteHotel();
            cleanHotel();
            listHotel(vista.dHotelTable);
        }
    }

    //Metodo agregar
    public void addFly(){
        if(vista.dHotel_precioTF.getText().isEmpty() || vista.dHotel_hotelCB.getSelectedIndex() == 0 || vista.dHotel_habitacionCB.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(null, "Uno de los campos están vacios o no cumplen con los valores requeridos para continuar");
            boolean[] arr = {false, true, false, false};
            areButtonsEnabled(arr);
        }
        else{
            float price = Float.parseFloat(vista.dHotel_precioTF.getText());
            int hotel = vista.dHotel_hotelCB.getSelectedIndex();
            int room = vista.dHotel_habitacionCB.getSelectedIndex();
            dh.setHotelID(hotel);
            dh.setRoomID(room);
            dh.setPrice(price);
            int r = dao.agregar(dh);
            if (r == 1){
                JOptionPane.showMessageDialog(null, "Registro agregado exitosamente");
            }
            else{
                JOptionPane.showMessageDialog(null, "Registro fallido");
            }
            areTextFieldEditable(false);
            cleanCB();
            boolean[] arr = {true, false, true, true};
            areButtonsEnabled(arr);
        }
    }

    //Metodo actualizar
    public void updateHotel(){
        String h = (String) vista.dHotel_hotelCB.getSelectedItem();
        String rn = (String) vista.dHotel_habitacionCB.getSelectedItem();
        if(vista.dHotel_precioTF.getText().isEmpty() || rn.compareTo("Seleccione una habitacion")==0 || h.compareTo("Seleccione un hotel")==0){
            JOptionPane.showMessageDialog(null, "Uno de los campos están vacios o no cumplen con los valores requeridos para continuar");
            boolean[] arr = {false, true, false, false};
            areButtonsEnabled(arr);
        }
        else{
            int hotel = dao.hotelID((String) vista.dHotel_hotelCB.getSelectedItem());
            int room = dao.roomID((String)vista.dHotel_habitacionCB.getSelectedItem());
            float price = Float.parseFloat(vista.dHotel_precioTF.getText());
            dh.setHotelID(hotel);
            dh.setRoomID(room);
            dh.setPrice(price);
            int r = dao.actualizar(dh);
            if (r == 1){
                JOptionPane.showMessageDialog(null, "Registro actualizado exitosamente");
            }
            else{
                JOptionPane.showMessageDialog(null, "Registro fallido");
            }
            areTextFieldEditable(false);
            cleanHotel();
            cleanCB();
            listHotel(vista.dHotelTable);
            boolean[] arr = {true, false, true, true};
            areButtonsEnabled(arr);
        }
    }

    //Metodo eliminar
    public void deleteHotel(){
        int row = vista.dHotelTable.getSelectedRow();
        if (row == -1){
            JOptionPane.showMessageDialog(null, "Seleccione un hotel");
        }else{
            int id = dao.hotelID(vista.dHotelTable.getValueAt(row, 0).toString());
            dao.eliminar(id);
            JOptionPane.showMessageDialog(null, "Registro eliminado exitosamente");
        }
    }

    //Metodo guardar
    private void saveHotel(){
        if (AU){
            int lista = dao.listar().size();
            if(lista > 0){
                addFly();
                cleanHotel();
                listHotel(vista.dHotelTable);
            }
            else{
                addFly();
                listHotel(vista.dHotelTable);
            }
        }else{
            updateHotel();
        }
    }

    private void editHotel(){
        AU = false;
        int row = vista.dHotelTable.getSelectedRow();
        if (row == -1){
            JOptionPane.showMessageDialog(null, "Seleccione un Hotel");
        }else{
            areTextFieldEditable(true);
            vista.dHotel_hotelCB.setEnabled(false);
            boolean[] arr = {false, true, false, false};
            areButtonsEnabled(arr);
            String Hotel = (String) vista.dHotelTable.getValueAt(row, 0);
            String Room = (String) vista.dHotelTable.getValueAt(row, 1);
            float price = Float.parseFloat(vista.dHotelTable.getValueAt(row, 2).toString());
            vista.dHotel_precioTF.setText("" + price);
            rellenarCB(Hotel, Room);
        }
    }

    //Listar la tabla detallehotel en el JTable
    public void listHotel(JTable dhotelTable){
        modelo = (DefaultTableModel) dhotelTable.getModel();
        List<DHoteles> lista = dao.listar();
        Object[] object = new Object[3];
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );
        for (DHoteles dhhotell : lista){
            object[0] = dhhotell.getHotelName();
            object[1] = dhhotell.getRoomName();
            object[2] = dhhotell.getPrice();
            modelo.addRow(object);
        }
        vista.dHotelTable.setModel(modelo);
    }

    //Actualizar la Tabla cada que se hace un cambio
    private void cleanHotel(){
        for(int i = 0; i < vista.dHotelTable.getRowCount(); i++){
            modelo.removeRow(i);
            i = i - 1;
        }
    }

    //Habilitar o no los componentes
    private void areTextFieldEditable(boolean flag){
        vista.dHotel_hotelCB.setEnabled(flag);
        vista.dHotel_habitacionCB.setEnabled(flag);
        vista.dHotel_precioTF.setEditable(flag);
    }

    //Controla el estado de los botones
    private void areButtonsEnabled(boolean[] a){
        vista.dHotel_addB.setEnabled(a[0]);
        vista.dHotel_saveB.setEnabled(a[1]);
        vista.dHotel_editB.setEnabled(a[2]);
        vista.dHotel_deleteB.setEnabled(a[3]);
    }

    //Limpiar los componentes
    private void cleanCB(){
        vista.dHotel_hotelCB.removeAllItems();
        vista.dHotel_hotelCB.addItem("Seleccione un hotel");
        mostrarHoteles();
        vista.dHotel_habitacionCB.removeAllItems();
        vista.dHotel_habitacionCB.addItem("Seleccione una habitacion");
        mostrarRoom();
        vista.dHotel_precioTF.setText("");
    }

    //Metodo para llenar los ComboBox con lo que esta dentro de la tabla correspondiente en la BD
    private void rellenarCB(String Hotel, String room){
        vista.dHotel_hotelCB.removeAllItems();
        vista.dHotel_hotelCB.addItem(Hotel);
        for (int i = 0; i<dao.listarhoteles().size();i++){
            if (dao.listarhoteles().get(i).getHotelName().compareTo(Hotel) != 0){
                vista.dHotel_hotelCB.addItem(dao.listarhoteles().get(i).getHotelName());
            }
        }
        vista.dHotel_habitacionCB.removeAllItems();
        vista.dHotel_habitacionCB.addItem(room);
        for (int i = 0; i<dao.listarRoom().size();i++){
            if (dao.listarRoom().get(i).compareTo(room) != 0){
                vista.dHotel_habitacionCB.addItem(dao.listarRoom().get(i));
            }
        }
    }
}