package controlador;

import modelo.Aerolinea;
import modelo.AerolineaDAO;
import modelo.Empleado;
import vista.GestionarAerolinea;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class AerolineaController implements ActionListener {
    Aerolinea a = new Aerolinea();
    GestionarAerolinea vista;
    AerolineaDAO dao;
    DefaultTableModel modelo = new DefaultTableModel();
    private boolean AU = true;

    public AerolineaController(GestionarAerolinea vista, AerolineaDAO dao){
        this.dao = dao;
        int lista = dao.listar().size();
        if(lista > 0)
        {
            this.vista = vista;
            this.dao = dao;
            areTextFieldEditable(false);
            vista.gAerolinea_saveB.setEnabled(false);
            vista.gAerolinea_addB.addActionListener(this);
            vista.gAerolinea_saveB.addActionListener(this);
            vista.gAerolinea_editB.addActionListener(this);
            vista.gAerolinea_deleteB.addActionListener(this);
            listAirline(vista.gAerolineaTable);
            boolean[] arr = {true, false, true, true};
            areButtonsEnabled(arr);
        }
        else
        {
            this.vista = vista;
            this.dao = dao;
            areTextFieldEditable(false);
            vista.gAerolinea_saveB.setEnabled(false);
            vista.gAerolinea_addB.addActionListener(this);
            vista.gAerolinea_saveB.addActionListener(this);
            vista.gAerolinea_editB.addActionListener(this);
            vista.gAerolinea_deleteB.addActionListener(this);
            listAirline(vista.gAerolineaTable);
            boolean[] arr = {true, false, false, false};
            areButtonsEnabled(arr);
        }
        areTextFieldEditable(false);
    }

    public void actionPerformed(ActionEvent e)
    {
        //Boton Nuevo
        if(e.getSource()== vista.gAerolinea_addB)
        {
            AU = true;
            areTextFieldEditable(true);
            boolean[] arr = {false, true, true, false};
            areButtonsEnabled(arr);
        }
        //Boton Guardar
        if(e.getSource()== vista.gAerolinea_saveB)
        {
            saveAirline();
        }
        //Boton Editar
        if(e.getSource()== vista.gAerolinea_editB)
        {
            editAirline();
        }
        //Boton Eliminar
        if(e.getSource()== vista.gAerolinea_deleteB)
        {
            deleteAirline();
            cleanAirline();
            cleanForm();
            listAirline(vista.gAerolineaTable);
        }

    }

    //Metodo agregar
    public void addAirline()
    {
        if(vista.gAerolinea_nombreTF.getText().isEmpty() || vista.gAerolinea_claseTF.getText().isEmpty() || vista.gAerolinea_precioTF.getText().isEmpty())
        {
            JOptionPane.showMessageDialog(null, "Uno o mas campos estan vacios, rellenalos para continuar");
            boolean[] arr = {true, false, true, true};
            areButtonsEnabled(arr);
        }
        else
        {
            String name = vista.gAerolinea_nombreTF.getText();
            String clase = vista.gAerolinea_claseTF.getText();
            float precio = Float.parseFloat(vista.gAerolinea_precioTF.getText());
            a.setAirlineName(name);
            a.setFlyClass(clase);
            a.setPrice(precio);
            int r = dao.agregar(a);

            if (r == 1) {
                JOptionPane.showMessageDialog(null, "Registro agregado exitosamente");
            } else {
                JOptionPane.showMessageDialog(null, "Registro fallido");
            }
            boolean[] arr = {true, false, true, true};
            areButtonsEnabled(arr);
        }
    }

    //Metodo actualizar
    public void updateAirline()
    {
        if(vista.gAerolinea_nombreTF.getText().isEmpty() || vista.gAerolinea_claseTF.getText().isEmpty() || vista.gAerolinea_precioTF.getText().isEmpty())
        {
            JOptionPane.showMessageDialog(null, "Uno o mas campos estan vacios, rellenalos para continuar");
            boolean[] arr = {true, false, true, true};
            areButtonsEnabled(arr);
        }
        else
        {
            int id = Integer.parseInt(vista.gAerolinea_idTF.getText());
            String name = vista.gAerolinea_nombreTF.getText();
            String clase = vista.gAerolinea_claseTF.getText();
            float precio = Float.parseFloat(vista.gAerolinea_precioTF.getText());
            a.setAirlineID(id);
            a.setAirlineName(name);
            a.setFlyClass(clase);
            a.setPrice(precio);

            int r = dao.actualizar(a);

            if (r == 1) {
                JOptionPane.showMessageDialog(null, "Registro actualizado exitosamente");
            } else {
                JOptionPane.showMessageDialog(null, "Registro fallido");
            }
            boolean[] arr = {true, false, true, true};
            areButtonsEnabled(arr);
        }
    }

    //Metodo eliminar
    public void deleteAirline(){
        int row = vista.gAerolineaTable.getSelectedRow();
        if(row == -1)
        {
            JOptionPane.showMessageDialog(null, "Debe selecionar una aerolinea");
        }
        else
        {
            int lista = dao.listar().size();
            if(lista>1)
            {
                int id = Integer.parseInt((String) vista.gAerolineaTable.getValueAt(row, 0).toString());
                dao.delete(id);
                JOptionPane.showMessageDialog(null, "Registro eliminado");
            }
            else
            {
                boolean[] arr = {true, false, false, false};
                areButtonsEnabled(arr);
                int id = Integer.parseInt((String) vista.gAerolineaTable.getValueAt(row, 0).toString());
                dao.delete(id);
                JOptionPane.showMessageDialog(null, "Registro eliminado");
            }

        }
    }

    //Metodo guardar
    public void saveAirline()
    {
        if(AU)
        {
            int lista = dao.listar().size();
            if (lista>0)
            {
                addAirline();
                cleanAirline();
                listAirline(vista.gAerolineaTable);
                areTextFieldEditable(false);
                cleanForm();
            }
            else
            {
                addAirline();
                listAirline(vista.gAerolineaTable);
                areTextFieldEditable(false);
                cleanForm();
            }
        }
        else
        {
            updateAirline();
            cleanAirline();
            listAirline(vista.gAerolineaTable);
            areTextFieldEditable(false);
            cleanForm();
        }
        boolean[] arr = {true, false, true, true};
        areButtonsEnabled(arr);
    }

    public void editAirline()
    {
        AU = false;
        int row = vista.gAerolineaTable.getSelectedRow();
        if(row==-1) {
            JOptionPane.showMessageDialog(null, "Seleccione una fila");
        }
        else {
            areTextFieldEditable(true);
            boolean[] arr = {false, true, false, false};
            areButtonsEnabled(arr);
            int id = Integer.parseInt(vista.gAerolineaTable.getValueAt(row, 0).toString());
            String nombre = (String) vista.gAerolineaTable.getValueAt(row, 1);
            String clase = (String) vista.gAerolineaTable.getValueAt(row, 2);
            float precio = Float.parseFloat(vista.gAerolineaTable.getValueAt(row, 3).toString());
            vista.gAerolinea_idTF.setText(""+id);
            vista.gAerolinea_nombreTF.setText(nombre);
            vista.gAerolinea_claseTF.setText(clase);
            vista.gAerolinea_precioTF.setText(""+precio);
        }
    }

    //Listar la tabla aerolinea en el JTable
    public void listAirline(JTable tabla)
    {
        modelo = (DefaultTableModel)tabla.getModel();
        List<Aerolinea> lista = dao.listar();
        Object[] object = new Object[6];
        for (Aerolinea value : lista) {
            object[0] = value.getAirlineID();
            object[1] = value.getAirlineName();
            object[2] = value.getFlyClass();
            object[3] = value.getPrice();
            modelo.addRow(object);
        }
    }

    //Actualizar la Tabla cada que se hace un cambio
    private void cleanAirline(){
        for (int i = 0; i < vista.gAerolineaTable.getRowCount(); i++){
            modelo.removeRow(i);
            i = i - 1;
        }
    }

    //Hacer editable o no editable los TextField
    private void areTextFieldEditable(boolean flag)
    {
        vista.gAerolinea_nombreTF.setEditable(flag);
        vista.gAerolinea_claseTF.setEditable(flag);
        vista.gAerolinea_precioTF.setEditable(flag);
    }

    //Limpiar los componentes
    private void cleanForm(){
        vista.gAerolinea_idTF.setText("");
        vista.gAerolinea_nombreTF.setText("");
        vista.gAerolinea_claseTF.setText("");
        vista.gAerolinea_precioTF.setText("");
    }

    //Activar o desactivar botones
    private void areButtonsEnabled(boolean[] a)
    {
        vista.gAerolinea_addB.setEnabled(a[0]);
        vista.gAerolinea_saveB.setEnabled(a[1]);
        vista.gAerolinea_editB.setEnabled(a[2]);
        vista.gAerolinea_deleteB.setEnabled(a[3]);
    }
}
