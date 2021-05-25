package controlador;

import modelo.Promociones;
import modelo.PromocionesDAO;
import vista.GestionarPromociones;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class PromocionesController implements ActionListener {
    Promociones p = new Promociones();
    GestionarPromociones vista;
    PromocionesDAO dao;
    DefaultTableModel modelo = new DefaultTableModel();
    private boolean AU = true;

    public PromocionesController(GestionarPromociones vista, PromocionesDAO dao){
        int lista = dao.listar().size();
        if (lista > 0){
            this.dao = dao;
            this.vista = vista;
            this.vista.gPromo_addB.addActionListener(this);
            this.vista.gPromo_saveB.addActionListener(this);
            this.vista.gPromo_editB.addActionListener(this);
            this.vista.gPromo_deleteB.addActionListener(this);
            listPromo(vista.gPromoTable);
            boolean[] arr = {true, false, true, true};
            areButtonEnable(arr);
            mostrarPaquetes();
        }else{
            this.dao = dao;
            this.vista = vista;
            this.vista.gPromo_addB.addActionListener(this);
            this.vista.gPromo_saveB.addActionListener(this);
            this.vista.gPromo_editB.addActionListener(this);
            this.vista.gPromo_deleteB.addActionListener(this);
            listPromo(vista.gPromoTable);
            boolean[] arr = {true, false, false, false};
            areButtonEnable(arr);
            mostrarPaquetes();
        }
        areTextFieldEditable(false);
    }

    public void mostrarPaquetes(){
        for(int i = 0; i < dao.listarPaquete().size(); i++){
            vista.gPromo_paqueteCB.addItem(dao.listarPaquete().get(i));
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == vista.gPromo_addB){
            AU = true;
            areTextFieldEditable(true);
            boolean[] arr = {false, true, false, false};
            areButtonEnable(arr);
        }
        if(e.getSource() == vista.gPromo_saveB){
            savePromo();
        }
        if(e.getSource() == vista.gPromo_editB){
            editPromo();
        }
        if(e.getSource() == vista.gPromo_deleteB){
            deletePromo();
            cleanPromo();
            listPromo(vista.gPromoTable);
        }
    }

    public void addPromo(){
        if(vista.gPromo_nombreTF.getText().isEmpty() || vista.gPromo_paqueteCB.getSelectedIndex() == 0 || vista.gPromo_descuentoTF.getText().isEmpty() || vista.gPromo_descripcionTF.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Uno de los campos están vacios o no cumplen con los valores requeridos para continuar");
            boolean[] arr = {false, true, false, false};
            areButtonEnable(arr);
        }else{
            String nombre = vista.gPromo_nombreTF.getText();
            int paquete = dao.packID((String) vista.gPromo_paqueteCB.getSelectedItem());
            float descuento = Float.parseFloat(vista.gPromo_descuentoTF.getText());
            String desc = vista.gPromo_descripcionTF.getText();
            p.setPromotionName(nombre);
            p.setPackID(paquete);
            p.setDiscount(descuento);
            p.setDescription(desc);
            int r = dao.agregar(p);
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

    public void updatePromo(){
        if(vista.gPromo_nombreTF.getText().isEmpty()|| vista.gPromo_descuentoTF.getText().isEmpty() || vista.gPromo_descripcionTF.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Uno de los campos están vacios o no cumplen con los valores requeridos para continuar");
            boolean[] arr = {false, true, false, false};
            areButtonEnable(arr);
        }
        else
        {
            int id = Integer.parseInt(vista.gPromo_idTF.getText());
            String nombre = vista.gPromo_nombreTF.getText();
            int paquete = dao.packID((String) vista.gPromo_paqueteCB.getSelectedItem());
            float descuento = Float.parseFloat(vista.gPromo_descuentoTF.getText());
            String desc = vista.gPromo_descripcionTF.getText();
            p.setPromotionID(id);
            p.setPromotionName(nombre);
            p.setPackID(paquete);
            p.setDiscount(descuento);
            p.setDescription(desc);
            int r = dao.actualizar(p);
            if (r == 1){
                JOptionPane.showMessageDialog(null, "Registro actualizado exitosamente");
            }
            else{
                JOptionPane.showMessageDialog(null, "Registro fallido");
            }
            boolean[] arr = {true, false, true, true};
            areButtonEnable(arr);
        }
    }

    public void deletePromo(){
        int row = vista.gPromoTable.getSelectedRow();
        if (row == 1){
            JOptionPane.showMessageDialog(null, "Seleccione un paquete");
        }else{
            int lista = dao.listar().size();
            if (lista > 1){
                int id = Integer.parseInt(vista.gPromoTable.getValueAt(row, 0).toString());
                dao.eliminar(id);
                JOptionPane.showMessageDialog(null, "Paquete eliminado exitosamente");
            }else{
                boolean[] arr = {true, false, false, false};
                areButtonEnable(arr);
                int id = Integer.parseInt(vista.gPromoTable.getValueAt(row, 0).toString());
                dao.eliminar(id);
                JOptionPane.showMessageDialog(null, "Paquete eliminado exitosamente");
            }
        }
    }

    private void savePromo(){
        if(AU){
            if(dao.listar().size() > 0){
                addPromo();
                cleanPromo();
                listPromo(vista.gPromoTable);
                areTextFieldEditable(false);
                cleanForm();
                cleanCB();
            }
            else{
                addPromo();
                listPromo(vista.gPromoTable);
                areTextFieldEditable(false);
                cleanForm();
                cleanCB();
            }
        }else{
            updatePromo();
            cleanPromo();
            listPromo(vista.gPromoTable);
            areTextFieldEditable(false);
            cleanForm();
            cleanCB();
        }
    }

    private void editPromo(){
        AU = false;
        int row = vista.gPromoTable.getSelectedRow();
        if( row == -1){
            JOptionPane.showMessageDialog(null, "Seleccione un paquete");
        }else{
            areTextFieldEditable(true);
            boolean[] arr = {false, true, false, false};
            areButtonEnable(arr);
            int id = Integer.parseInt(vista.gPromoTable.getValueAt(row, 0).toString());
            String nombre = (String) vista.gPromoTable.getValueAt(row, 1);
            String paquete = (String) vista.gPromoTable.getValueAt(row, 2);
            float descuento = Float.parseFloat(vista.gPromoTable.getValueAt(row, 3).toString());
            String desc = (String) vista.gPromoTable.getValueAt(row, 4);
            vista.gPromo_idTF.setText("" + id);
            vista.gPromo_nombreTF.setText("" + nombre);
            vista.gPromo_paqueteCB.setSelectedIndex(0);
            vista.gPromo_descuentoTF.setText("" + descuento);
            vista.gPromo_descripcionTF.setText("" + desc);
            rellenarCB(paquete);
        }
    }

    public void listPromo(JTable promoTable){
        modelo = (DefaultTableModel) promoTable.getModel();
        List<Promociones> lista = dao.listar();
        Object[] object = new Object[5];
        DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
        cellRenderer.setHorizontalAlignment(JLabel.CENTER);
        for(Promociones promo : lista){
            object[0] = promo.getPromotionID();
            object[1] = promo.getPromotionName();
            object[2] = promo.getPackName();
            object[3] = promo.getDiscount();
            object[4] = promo.getDescription();
            modelo.addRow(object);
        }
        vista.gPromoTable.setModel(modelo);
    }

    private void cleanPromo(){
        for(int i = 0; i < vista.gPromoTable.getRowCount(); i++){
            modelo.removeRow(i);
            i = i -1;
        }
    }

    private void areTextFieldEditable(boolean flag){
        vista.gPromo_nombreTF.setEditable(flag);
        vista.gPromo_paqueteCB.setEnabled(flag);
        vista.gPromo_descuentoTF.setEditable(flag);
        vista.gPromo_descripcionTF.setEditable(flag);
    }

    private void cleanForm(){
        vista.gPromo_idTF.setText("");
        vista.gPromo_nombreTF.setText("");
        vista.gPromo_paqueteCB.setSelectedIndex(0);
        vista.gPromo_descuentoTF.setText("");
        vista.gPromo_descripcionTF.setText("");
    }

    private void areButtonEnable(boolean[] a){
        vista.gPromo_addB.setEnabled(a[0]);
        vista.gPromo_saveB.setEnabled(a[1]);
        vista.gPromo_editB.setEnabled(a[2]);
        vista.gPromo_deleteB.setEnabled(a[3]);
    }

    private void cleanCB(){
        vista.gPromo_paqueteCB.removeAllItems();
        vista.gPromo_paqueteCB.addItem("Seleccione paquete");
        mostrarPaquetes();
    }

    private void rellenarCB(String pack){
        vista.gPromo_paqueteCB.removeAllItems();
        vista.gPromo_paqueteCB.addItem(pack);
        for(int i = 0; i < dao.listarPaquete().size(); i++){
            if(dao.listarPaquete().get(i).compareTo(pack) != 0){
                vista.gPromo_paqueteCB.addItem(dao.listarPaquete().get(i));
            }
        }
    }
}
