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
            listar(vista.gAerolineaTable);
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
            listar(vista.gAerolineaTable);
            boolean[] arr = {true, false, false, false};
            estadosBotones(arr);
        }
    }

    public void actionPerformed(ActionEvent e)
    {
        //Boton Nuevo
        if(e.getSource()== vista.gAerolinea_addB)
        {
            AU = true;
            areTextFieldEditable(true);
            boolean[] arr = {false, true, true, false};
            estadosBotones(arr);
        }
        //Boton Guardar
        if(e.getSource()== vista.gAerolinea_saveB)
        {
            boolean[] arr = {true, false, true, true};
            estadosBotones(arr);
            Guardar();
        }
        //Boton Editar
        if(e.getSource()== vista.gAerolinea_editB)
        {
            AU = false;
            Editar();
        }
        //Boton eliminar
        if(e.getSource()== vista.gAerolinea_deleteB)
        {
            eliminar();
            limpiar();
            cleanForm();
            listar(vista.gAerolineaTable);
        }

    }

    public void eliminar(){
        int fila = vista.gAerolineaTable.getSelectedRow();
        if(fila==-1)
        {
            JOptionPane.showMessageDialog(null, "Debe selecionar un registro");
        }
        else
        {
            int lista = dao.listar().size();
            if(lista>1)
            {
                int id = Integer.parseInt((String) vista.gAerolineaTable.getValueAt(fila, 0).toString());
                dao.delete(id);
                JOptionPane.showMessageDialog(null, "Registro eliminado");
            }
            else
            {
                boolean[] arr = {true, false, false, false};
                estadosBotones(arr);
                int id = Integer.parseInt((String) vista.gAerolineaTable.getValueAt(fila, 0).toString());
                dao.delete(id);
                JOptionPane.showMessageDialog(null, "Registro eliminado");
            }

        }
    }

    public void listar(JTable tabla)
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

    public void agregar()
    {
        if(vista.gAerolinea_nombreTF.getText().isEmpty() || vista.gAerolinea_claseTF.getText().isEmpty() || vista.gAerolinea_precioTF.getText().isEmpty())
        {
            JOptionPane.showMessageDialog(null, "Uno o mas campos estan vacios, rellenalos para continuar");
            boolean[] arr = {false, true, false, false};
            estadosBotones(arr);
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
            areTextFieldEditable(false);
            cleanForm();
            boolean[] arr = {true, false, true, true};
            estadosBotones(arr);
        }
    }


    public void Actualizar()
    {
        if(vista.gAerolinea_nombreTF.getText().isEmpty() || vista.gAerolinea_claseTF.getText().isEmpty() || vista.gAerolinea_precioTF.getText().isEmpty())
        {
            JOptionPane.showMessageDialog(null, "Uno o mas campos estan vacios, rellenalos para continuar");
            boolean[] arr = {false, true, false, false};
            estadosBotones(arr);
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
            areTextFieldEditable(false);
            limpiar();
            cleanForm();
            listar(vista.gAerolineaTable);
        }
    }

    public void Editar()
    {
        int fila = vista.gAerolineaTable.getSelectedRow();
        if(fila==-1)
        {
            JOptionPane.showMessageDialog(null, "Seleccione una fila");
        }
        else
        {

            areTextFieldEditable(true);
            boolean[] arr = {false, true, true, false};
            estadosBotones(arr);
            int id = Integer.parseInt(vista.gAerolineaTable.getValueAt(fila, 0).toString());
            String nombre = (String) vista.gAerolineaTable.getValueAt(fila, 1);
            String clase = (String) vista.gAerolineaTable.getValueAt(fila, 2);
            float precio = Float.parseFloat(vista.gAerolineaTable.getValueAt(fila, 3).toString());
            vista.gAerolinea_idTF.setText(""+id);
            vista.gAerolinea_nombreTF.setText(nombre);
            vista.gAerolinea_claseTF.setText(clase);
            vista.gAerolinea_precioTF.setText(""+precio);
        }
    }

    //Guardar
    public void Guardar()
    {
        if(AU)
        {
            int lista = dao.listar().size();
            if (lista>0)
            {
                agregar();
                limpiar();
                listar(vista.gAerolineaTable);
            }
            else
            {
                agregar();
                listar(vista.gAerolineaTable);
            }
        }
        else
        {
            Actualizar();
        }

    }

    //Actualizar la Tabla cada que se hace un cambio
    private void limpiar(){
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

    //Limpiar los TextField
    private void cleanForm(){
        vista.gAerolinea_idTF.setText("");
        vista.gAerolinea_nombreTF.setText("");
        vista.gAerolinea_claseTF.setText("");
        vista.gAerolinea_precioTF.setText("");
    }

    //Activar o desactivar botones
    private void estadosBotones(boolean[] a)
    {
        vista.gAerolinea_addB.setEnabled(a[0]);
        vista.gAerolinea_saveB.setEnabled(a[1]);
        vista.gAerolinea_editB.setEnabled(a[2]);
        vista.gAerolinea_deleteB.setEnabled(a[3]);
    }
}
