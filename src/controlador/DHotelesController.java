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
    DetalleHotel vistaDH;
    DHotelesDAO dao;
    DefaultTableModel modelo = new DefaultTableModel();
    private boolean AU = true;

    public DHotelesController(DetalleHotel vistaDH, DHotelesDAO dao){

        int lista = dao.listar().size();
        if (lista > 0){
            this.dao = dao;
            this.vistaDH = vistaDH;
            this.vistaDH.dHotel_addB.addActionListener(this);
            this.vistaDH.dHotel_saveB.addActionListener(this);
            this.vistaDH.dHotel_editB.addActionListener(this);
            this.vistaDH.dHotel_deleteB.addActionListener(this);
            listHotel(vistaDH.dHotelTable);
            boolean[] arr = {true, false, true, true};
            areButtonEnable(arr);
        }else{
            this.dao = dao;
            this.vistaDH = vistaDH;
            this.vistaDH.dHotel_addB.addActionListener(this);
            this.vistaDH.dHotel_saveB.addActionListener(this);
            this.vistaDH.dHotel_editB.addActionListener(this);
            this.vistaDH.dHotel_deleteB.addActionListener(this);
            listHotel(vistaDH.dHotelTable);
            boolean[] arr = {true, false, false, false};
            areButtonEnable(arr);
        }
        areTextFieldEditable(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == vistaDH.dHotel_addB){
            AU = true;
            areTextFieldEditable(true);
            boolean[] arr = {false, true, false, false};
            areButtonEnable(arr);
        }
        if(e.getSource() == vistaDH.dHotel_saveB){
            saveHotel();
        }
        if(e.getSource() == vistaDH.dHotel_editB){
            editHotel();
        }
        if(e.getSource() == vistaDH.dHotel_deleteB){
            deleteHotel();
            cleanHotel();
            listHotel(vistaDH.dHotelTable);
        }
    }

    public void addFly(){
        if(vistaDH.dHotel_precioTF.getText().isEmpty() || vistaDH.dHotel_hotelCB.getSelectedIndex() == 0 || vistaDH.dHotel_habitacionCB.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(null, "Uno de los campos están vacios o no cumplen con los valores requeridos para continuar");
            boolean[] arr = {true, false, true, true};
            areButtonEnable(arr);
        }
        else{
            float price = Float.parseFloat(vistaDH.dHotel_precioTF.getText());
            int hotel = vistaDH.dHotel_hotelCB.getSelectedIndex();
            int room = vistaDH.dHotel_habitacionCB.getSelectedIndex();
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
            boolean[] arr = {true, false, true, true};
            areButtonEnable(arr);
        }
    }

    public void updateHotel(){
        String h = (String) vistaDH.dHotel_hotelCB.getSelectedItem();
        String rn = (String) vistaDH.dHotel_habitacionCB.getSelectedItem();
        if(vistaDH.dHotel_precioTF.getText().isEmpty() || rn.compareTo("Seleccione una habitacion")==0 || h.compareTo("Seleccione un hotel")==0){
            JOptionPane.showMessageDialog(null, "Uno de los campos están vacios o no cumplen con los valores requeridos para continuar");
            boolean[] arr = {true, false, true, true, true};
            areButtonEnable(arr);
        }
        else{
            int hotel = dao.hotelID((String) vistaDH.dHotel_hotelCB.getSelectedItem());
            int room = dao.roomID((String)vistaDH.dHotel_habitacionCB.getSelectedItem());
            float price = Float.parseFloat(vistaDH.dHotel_precioTF.getText());
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
        }
    }

    public void deleteHotel(){
        int row = vistaDH.dHotelTable.getSelectedRow();
        if (row == 1){
            JOptionPane.showMessageDialog(null, "Seleccione un hotel");
        }else{
            int id = dao.hotelID((String) vistaDH.dHotelTable.getValueAt(row, 0).toString());
            dao.eliminar(id);
            JOptionPane.showMessageDialog(null, "Vuelo eliminado exitosamente");
        }
    }

    private void saveHotel(){
        if (AU){
            JOptionPane.showMessageDialog(null, "ADDING");
            addFly();
            cleanHotel();
            listHotel(vistaDH.dHotelTable);
            areTextFieldEditable(false);
            cleanForm();

        }else{
            updateHotel();
            JOptionPane.showMessageDialog(null, "UPDATING");
            cleanHotel();
            listHotel(vistaDH.dHotelTable);
            areTextFieldEditable(false);
            cleanForm();
        }
    }

    private void editHotel(){
        AU = false;
        int row = vistaDH.dHotelTable.getSelectedRow();
        if (row == -1){
            JOptionPane.showMessageDialog(null, "Seleccione un Hotel");
        }else{
            areTextFieldEditable(true);
            vistaDH.dHotel_hotelCB.setEnabled(false);
            boolean[] arr = {false, true, false, false};
            areButtonEnable(arr);
            String Hotel = (String) vistaDH.dHotelTable.getValueAt(row, 0);
            String Room = (String) vistaDH.dHotelTable.getValueAt(row, 1);
            float price = Float.parseFloat(vistaDH.dHotelTable.getValueAt(row, 2).toString());
            vistaDH.dHotel_precioTF.setText("" + price);
            rellenarCB(Hotel, Room);
        }
    }

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
            System.out.println(object);
            modelo.addRow(object);
        }
        vistaDH.dHotelTable.setModel(modelo);
    }

    private void cleanHotel(){
        for(int i = 0; i < vistaDH.dHotelTable.getRowCount(); i++){
            modelo.removeRow(i);
            i = i - 1;
        }
    }

    private void areTextFieldEditable(boolean flag){
        vistaDH.dHotel_hotelCB.setEnabled(flag);
        vistaDH.dHotel_habitacionCB.setEnabled(flag);
        vistaDH.dHotel_precioTF.setEditable(flag);
    }
    private void areButtonEnable(boolean[] a){
        vistaDH.dHotel_addB.setEnabled(a[0]);
        vistaDH.dHotel_saveB.setEnabled(a[1]);
        vistaDH.dHotel_editB.setEnabled(a[2]);
        vistaDH.dHotel_deleteB.setEnabled(a[3]);
    }

    private void cleanForm(){
        vistaDH.dHotel_habitacionCB.setSelectedIndex(0);
        vistaDH.dHotel_precioTF.setText("");
    }

    private void rellenarCB(String Hotel, String room){
        vistaDH.dHotel_hotelCB.removeAllItems();
        vistaDH.dHotel_hotelCB.addItem(Hotel);
        for (int i = 0; i<dao.listarhoteles().size();i++){
            if (dao.listarhoteles().get(i).getHotelName().compareTo(Hotel) != 0){
                vistaDH.dHotel_hotelCB.addItem(dao.listarhoteles().get(i).getHotelName());
            }
        }
        vistaDH.dHotel_habitacionCB.removeAllItems();
        vistaDH.dHotel_habitacionCB.addItem(room);
        for (int i = 0; i<dao.listarRoom().size();i++){
            if (dao.listarRoom().get(i).compareTo(room) != 0){
                vistaDH.dHotel_habitacionCB.addItem(dao.listarRoom().get(i));
            }
        }
    }
}