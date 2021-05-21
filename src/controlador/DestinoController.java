package controlador;

import modelo.DestinoDAO;
import modelo.Destinos;
import cuarta.GestionarDestinos;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DestinoController implements ActionListener {
    Destinos d = new Destinos();
    GestionarDestinos vistaD;
    DestinoDAO dao;
    DefaultTableModel modelo = new DefaultTableModel();

    public DestinoController(GestionarDestinos vistaD, DestinoDAO dao){
        this.dao = dao;
        this.vistaD = vistaD;
        this.vistaD.gDestino_addB.addActionListener(this);
        this.vistaD.gDestino_saveB.addActionListener(this);
        this.vistaD.gDestino_editB.addActionListener(this);
        this.vistaD.gDestino_updateB.addActionListener(this);
        this.vistaD.gDestino_deleteB.addActionListener(this);
        listar(vistaD.gDestinoTable);
        areTextFieldEditable(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //Boton Agregar
        if(e.getSource() == vistaD.gDestino_addB){
            areTextFieldEditable(true);
        }
        //Boton Guardar
        if(e.getSource() == vistaD.gDestino_saveB){
            agregar();
            limpiar();
            listar(vistaD.gDestinoTable);
            areTextFieldEditable(false);
            cleanForm();
        }
        //Boton Editar
        if(e.getSource() == vistaD.gDestino_editB){
            int row = vistaD.gDestinoTable.getSelectedRow();
            if(row == -1){
                JOptionPane.showMessageDialog(null, "Seleccione una fila");
            }
            else{
                areTextFieldEditable(true);
                int id = Integer.parseInt(vistaD.gDestinoTable.getValueAt(row, 0).toString());
                String ciudad = (String) vistaD.gDestinoTable.getValueAt(row, 1);
                String estado = (String) vistaD.gDestinoTable.getValueAt(row, 2);
                String pais = (String) vistaD.gDestinoTable.getValueAt(row, 3);
                vistaD.gDestino_idTF.setText("" + id);
                vistaD.gDestino_ciudadTF.setText("" + ciudad);
                vistaD.gDestino_estadoTF.setText("" + estado);
                vistaD.gDestino_paisTF.setText("" + pais);
            }
        }
        //Boton Actualizar
        if (e.getSource() == vistaD.gDestino_updateB){
            actualizar();
            limpiar();
            listar(vistaD.gDestinoTable);
            areTextFieldEditable(false);
            cleanForm();

        }
        //Boton Eliminar
        if(e.getSource() == vistaD.gDestino_deleteB){
            eliminar();
            limpiar();
            listar(vistaD.gDestinoTable);
        }
    }

    public void agregar(){
        String ciudad = vistaD.gDestino_ciudadTF.getText();
        String estado = vistaD.gDestino_estadoTF.getText();
        String pais = vistaD.gDestino_paisTF.getText();
        d.setCity(ciudad);
        d.setState(estado);
        d.setCountry(pais);
        int r = dao.agregar(d);
        if(r == 1){
            JOptionPane.showMessageDialog(null, "Registro agregado exitosamente");
        }else{
            JOptionPane.showMessageDialog(null, "Registro fallido");
        }
    }

    public void actualizar(){
        int id = Integer.parseInt(vistaD.gDestino_idTF.getText());
        String ciudad = vistaD.gDestino_ciudadTF.getText();
        String estado = vistaD.gDestino_estadoTF.getText();
        String pais = vistaD.gDestino_paisTF.getText();
        d.setDestinationID(id);
        d.setCity(ciudad);
        d.setState(estado);
        d.setCountry(pais);
        int r = dao.actualizar(d);
        if (r == 1){
            JOptionPane.showMessageDialog(null, "Registro actualizado exitosamente");
        }else{
            JOptionPane.showMessageDialog(null, "Registro fallido");
        }
    }

    public void eliminar(){
        int row = vistaD.gDestinoTable.getSelectedRow();
        if(row == 1){
            JOptionPane.showMessageDialog(null, "Seleccione un Destino");
        }else{
            int id = Integer.parseInt(vistaD.gDestinoTable.getValueAt(row, 0).toString());
            dao.delete(id);
            JOptionPane.showMessageDialog(null, "Destino eliminado exitosamente");
        }
    }

    public void listar(JTable destinosTable){
        modelo = (DefaultTableModel) destinosTable.getModel();
        Object[]object = new Object[4];
        int registro = dao.listar().size();
        for (int i = 0; i < registro; i++){
            object[0] = dao.listar().get(i).getDestinationID();
            object[1] = dao.listar().get(i).getCity();
            object[2] = dao.listar().get(i).getState();
            object[3] = dao.listar().get(i).getCountry();
            modelo.addRow(object);
        }
        vistaD.gDestinoTable.setModel(modelo);
    }

    private void limpiar(){
        for (int i = 0; i < vistaD.gDestinoTable.getRowCount(); i++){
            modelo.removeRow(i);
            i = i - 1;
        }
    }

    private void areTextFieldEditable(boolean flag){
        vistaD.gDestino_ciudadTF.setEditable(flag);
        vistaD.gDestino_estadoTF.setEditable(flag);
        vistaD.gDestino_paisTF.setEditable(flag);
    }

    private void cleanForm(){
        vistaD.gDestino_idTF.setText("");
        vistaD.gDestino_ciudadTF.setText("");
        vistaD.gDestino_estadoTF.setText("");
        vistaD.gDestino_paisTF.setText("");
    }
}