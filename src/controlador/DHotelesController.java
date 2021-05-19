package controlador;

import modelo.DHoteles;
import modelo.DHotelesDAO;
import quinta.DetalleHotel;

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
        if(vistaDH.dHotel_precioTF.getText().isEmpty() || vistaDH.dHotel_habitacionCB.getSelectedIndex() == 0 || vistaDH.dHotel_hotelCB.getSelectedIndex() == 0 ){
            JOptionPane.showMessageDialog(null, "Uno de los campos están vacios o no cumplen con los valores requeridos para continuar");
            boolean[] arr = {true, false, true, true, true};
            areButtonEnable(arr);
            if(!validarprecio(vistaDH.dHotel_precioTF.getText().trim())){
                JOptionPane.showMessageDialog(null, "Origen solo debe contener números");
            }
        }
        else{
            float price = Float.parseFloat(vistaDH.dHotel_precioTF.getText());
            int room = vistaDH.dHotel_habitacionCB.getSelectedIndex();
            int hotel = vistaDH.dHotel_hotelCB.getSelectedIndex();
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
            int id = Integer.parseInt(vistaDH.dHotelTable.getValueAt(row, 0).toString());
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
            boolean[] arr = {false, true, false, false};
            areButtonEnable(arr);
            float price = Float.parseFloat(vistaDH.dHotelTable.getValueAt(row, 2).toString());
            vistaDH.dHotel_precioTF.setText("" + price);
        }
    }

    public void listHotel(JTable dhotelTable){
        modelo = (DefaultTableModel) dhotelTable.getModel();
        List<DHoteles> lista = dao.listar();
        Object[] object = new Object[3];
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );
        for (DHoteles dHoteles : lista){
            object[0] = dHoteles.getHotelName();
            object[1] = dHoteles.getRoomName();
            object[2] = dHoteles.getPrice();
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
        vistaDH.dHotel_precioTF.setEditable(flag);

    }
    private void areButtonEnable(boolean[] a){
        vistaDH.dHotel_addB.setEnabled(a[0]);
        vistaDH.dHotel_saveB.setEnabled(a[1]);
        vistaDH.dHotel_editB.setEnabled(a[2]);
        vistaDH.dHotel_deleteB.setEnabled(a[3]);
    }

    private boolean validarprecio(String datos){
        return datos.matches("[a-zA-z]{1,45}");
    }

    private void cleanForm(){
        boolean[] arr = {true, true, true, true};
        areButtonEnable(arr);
        vistaDH.dHotel_habitacionCB.setSelectedIndex(0);
        vistaDH.dHotel_precioTF.setText("");
    }
}
