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
    GestionarPromociones vistaP;
    PromocionesDAO dao;
    DefaultTableModel modelo = new DefaultTableModel();
    private boolean AU = true;

    public PromocionesController(GestionarPromociones vistaP, PromocionesDAO dao){
        int lista = dao.listar().size();
        if (lista > 0){
            this.dao = dao;
            this.vistaP = vistaP;
            this.vistaP.gPromo_addB.addActionListener(this);
            this.vistaP.gPromo_saveB.addActionListener(this);
            this.vistaP.gPromo_editB.addActionListener(this);
            this.vistaP.gPromo_deleteB.addActionListener(this);
            listPromo(vistaP.gPromoTable);
            boolean[] arr = {true, false, true, true};
            areButtonEnable(arr);
            mostrarPaquetes();
        }else{
            this.dao = dao;
            this.vistaP = vistaP;
            this.vistaP.gPromo_addB.addActionListener(this);
            this.vistaP.gPromo_saveB.addActionListener(this);
            this.vistaP.gPromo_editB.addActionListener(this);
            this.vistaP.gPromo_deleteB.addActionListener(this);
            listPromo(vistaP.gPromoTable);
            boolean[] arr = {true, false, false, false};
            areButtonEnable(arr);
            mostrarPaquetes();
        }
        areTextFieldEditable(false);
    }

    public void mostrarPaquetes(){
        for(int i = 0; i < dao.listarPaquete().size(); i++){
            vistaP.gPromo_paqueteCB.addItem(dao.listarPaquete().get(i));
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == vistaP.gPromo_addB){
            AU = true;
            areTextFieldEditable(true);
            boolean[] arr = {false, true, false, false};
            areButtonEnable(arr);
        }
        if(e.getSource() == vistaP.gPromo_saveB){
            savePromo();
        }
        if(e.getSource() == vistaP.gPromo_editB){
            editPromo();
        }
        if(e.getSource() == vistaP.gPromo_deleteB){
            deletePromo();
            cleanPromo();
            listPromo(vistaP.gPromoTable);
        }
    }

    public void addPromo(){
        if(vistaP.gPromo_nombreTF.getText().isEmpty() || vistaP.gPromo_paqueteCB.getSelectedIndex() == 0 || vistaP.gPromo_descuentoTF.getText().isEmpty() || vistaP.gPromo_descripcionTF.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Uno de los campos están vacios o no cumplen con los valores requeridos para continuar");
            boolean[] arr = {true, false, true, true};
            areButtonEnable(arr);
        }else{
            String nombre = vistaP.gPromo_nombreTF.getText();
            int paquete = dao.packID((String) vistaP.gPromo_paqueteCB.getSelectedItem());
            float descuento = Float.parseFloat(vistaP.gPromo_descuentoTF.getText());
            String desc = vistaP.gPromo_descripcionTF.getText();
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
        if(vistaP.gPromo_nombreTF.getText().isEmpty()|| vistaP.gPromo_descuentoTF.getText().isEmpty() || vistaP.gPromo_descripcionTF.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Uno de los campos están vacios o no cumplen con los valores requeridos para continuar");
            boolean[] arr = {true, false, true, true};
            areButtonEnable(arr);
        }
        else
        {
            int id = Integer.parseInt(vistaP.gPromo_idTF.getText());
            String nombre = vistaP.gPromo_nombreTF.getText();
            int paquete = dao.packID((String) vistaP.gPromo_paqueteCB.getSelectedItem());
            float descuento = Float.parseFloat(vistaP.gPromo_descuentoTF.getText());
            String desc = vistaP.gPromo_descripcionTF.getText();
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
        int row = vistaP.gPromoTable.getSelectedRow();
        if (row == 1){
            JOptionPane.showMessageDialog(null, "Seleccione un paquete");
        }else{
            int lista = dao.listar().size();
            if (lista > 1){
                int id = Integer.parseInt(vistaP.gPromoTable.getValueAt(row, 0).toString());
                dao.eliminar(id);
                JOptionPane.showMessageDialog(null, "Paquete eliminado exitosamente");
            }else{
                boolean[] arr = {true, false, false, false};
                areButtonEnable(arr);
                int id = Integer.parseInt(vistaP.gPromoTable.getValueAt(row, 0).toString());
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
                listPromo(vistaP.gPromoTable);
                areTextFieldEditable(false);
                cleanForm();
                cleanCB();
            }
            else{
                addPromo();
                listPromo(vistaP.gPromoTable);
                areTextFieldEditable(false);
                cleanForm();
                cleanCB();
            }
        }else{
            updatePromo();
            cleanPromo();
            listPromo(vistaP.gPromoTable);
            areTextFieldEditable(false);
            cleanForm();
            cleanCB();
        }
    }

    private void editPromo(){
        AU = false;
        int row = vistaP.gPromoTable.getSelectedRow();
        if( row == -1){
            JOptionPane.showMessageDialog(null, "Seleccione un paquete");
        }else{
            areTextFieldEditable(true);
            boolean[] arr = {false, true, false, false};
            areButtonEnable(arr);
            int id = Integer.parseInt(vistaP.gPromoTable.getValueAt(row, 0).toString());
            String nombre = (String) vistaP.gPromoTable.getValueAt(row, 1);
            String paquete = (String) vistaP.gPromoTable.getValueAt(row, 2);
            float descuento = Float.parseFloat(vistaP.gPromoTable.getValueAt(row, 3).toString());
            String desc = (String) vistaP.gPromoTable.getValueAt(row, 4);
            vistaP.gPromo_idTF.setText("" + id);
            vistaP.gPromo_nombreTF.setText("" + nombre);
            vistaP.gPromo_paqueteCB.setSelectedIndex(0);
            vistaP.gPromo_descuentoTF.setText("" + descuento);
            vistaP.gPromo_descripcionTF.setText("" + desc);
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
        vistaP.gPromoTable.setModel(modelo);
    }

    private void cleanPromo(){
        for(int i = 0; i < vistaP.gPromoTable.getRowCount(); i++){
            modelo.removeRow(i);
            i = i -1;
        }
    }

    private void areTextFieldEditable(boolean flag){
        vistaP.gPromo_nombreTF.setEditable(flag);
        vistaP.gPromo_paqueteCB.setEnabled(flag);
        vistaP.gPromo_descuentoTF.setEditable(flag);
        vistaP.gPromo_descripcionTF.setEditable(flag);
    }

    private void cleanForm(){
        vistaP.gPromo_idTF.setText("");
        vistaP.gPromo_nombreTF.setText("");
        vistaP.gPromo_paqueteCB.setSelectedIndex(0);
        vistaP.gPromo_descuentoTF.setText("");
        vistaP.gPromo_descripcionTF.setText("");
    }

    private void areButtonEnable(boolean[] a){
        vistaP.gPromo_addB.setEnabled(a[0]);
        vistaP.gPromo_saveB.setEnabled(a[1]);
        vistaP.gPromo_editB.setEnabled(a[2]);
        vistaP.gPromo_deleteB.setEnabled(a[3]);
    }

    private void cleanCB(){
        vistaP.gPromo_paqueteCB.removeAllItems();
        vistaP.gPromo_paqueteCB.addItem("Seleccione paquete");
        mostrarPaquetes();
    }

    private void rellenarCB(String pack){
        vistaP.gPromo_paqueteCB.removeAllItems();
        vistaP.gPromo_paqueteCB.addItem(pack);
        for(int i = 0; i < dao.listarPaquete().size(); i++){
            if(dao.listarPaquete().get(i).compareTo(pack) != 0){
                vistaP.gPromo_paqueteCB.addItem(dao.listarPaquete().get(i));
            }
        }
    }
}
