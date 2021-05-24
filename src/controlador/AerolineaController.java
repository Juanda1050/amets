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
    GestionarAerolinea vistaA;
    AerolineaDAO dao;
    DefaultTableModel modelo = new DefaultTableModel();
    private boolean AU = true;

    public AerolineaController(GestionarAerolinea vistaA, AerolineaDAO dao){
        this.dao = dao;
        int lista = dao.listar().size();
        if(lista>0)
        {
            this.vistaA = vistaA;
            this.dao = dao;
            areTextFieldEditable(false);
            vistaA.gAerolinea_saveB.setEnabled(false);

            vistaA.gAerolinea_addB.addActionListener(this);
            vistaA.gAerolinea_saveB.addActionListener(this);
            vistaA.gAerolinea_editB.addActionListener(this);
            vistaA.gAerolinea_deleteB.addActionListener(this);
            listar(vistaA.gAerolineaTable);
        }
        else
        {
            this.vistaA = vistaA;
            this.dao = dao;
            areTextFieldEditable(false);

            vistaA.gAerolinea_saveB.setEnabled(false);

            vistaA.gAerolinea_addB.addActionListener(this);
            vistaA.gAerolinea_saveB.addActionListener(this);
            vistaA.gAerolinea_editB.addActionListener(this);
            vistaA.gAerolinea_deleteB.addActionListener(this);
            listar(vistaA.gAerolineaTable);
            boolean[] arr = {true, false, false, false};
            estadosBotones(arr);
        }
    }

    public void actionPerformed(ActionEvent e)
    {
        //Boton Nuevo
        if(e.getSource()== vistaA.gAerolinea_addB)
        {
            AU = true;
            areTextFieldEditable(true);
            boolean[] arr = {false, true, true, false};
            estadosBotones(arr);
        }
        //Boton Guardar
        if(e.getSource()== vistaA.gAerolinea_saveB)
        {
            boolean[] arr = {true, false, true, true};
            estadosBotones(arr);
            Guardar();

        }
        //Boton Editar
        if(e.getSource()== vistaA.gAerolinea_editB)
        {
            AU = false;
            Editar();
        }
        //Boton eliminar
        if(e.getSource()== vistaA.gAerolinea_deleteB)
        {
            eliminar();
            limpiar();
            cleanForm();
            listar(vistaA.gAerolineaTable);
        }

    }

    public void eliminar(){
        int fila = vistaA.gAerolineaTable.getSelectedRow();
        if(fila==-1)
        {
            JOptionPane.showMessageDialog(null, "Debe selecionar un registro");
        }
        else
        {
            int lista = dao.listar().size();
            if(lista>1)
            {
                int id = Integer.parseInt((String) vistaA.gAerolineaTable.getValueAt(fila, 0).toString());
                dao.delete(id);
                JOptionPane.showMessageDialog(null, "Registro eliminado");
            }
            else
            {
                boolean[] arr = {true, false, false, false};
                estadosBotones(arr);
                int id = Integer.parseInt((String) vistaA.gAerolineaTable.getValueAt(fila, 0).toString());
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

        if(vistaA.gAerolinea_nombreTF.getText().isEmpty() || vistaA.gAerolinea_claseTF.getText().isEmpty() || vistaA.gAerolinea_precioTF.getText().isEmpty())
        {
            JOptionPane.showMessageDialog(null, "Uno o mas campos estan vacios, rellenalos para continuar");
            boolean[] arr = {false, true, false, false};
            estadosBotones(arr);
        }
        else
        {
            String name = vistaA.gAerolinea_nombreTF.getText();
            String clase = vistaA.gAerolinea_claseTF.getText();
            float precio = Float.parseFloat(vistaA.gAerolinea_precioTF.getText());
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
        if(vistaA.gAerolinea_nombreTF.getText().isEmpty() || vistaA.gAerolinea_claseTF.getText().isEmpty() || vistaA.gAerolinea_precioTF.getText().isEmpty())
        {
            JOptionPane.showMessageDialog(null, "Uno o mas campos estan vacios, rellenalos para continuar");
            boolean[] arr = {false, true, false, false};
            estadosBotones(arr);
        }
        else
        {
            int id = Integer.parseInt(vistaA.gAerolinea_idTF.getText());
            String name = vistaA.gAerolinea_nombreTF.getText();
            String clase = vistaA.gAerolinea_claseTF.getText();
            float precio = Float.parseFloat(vistaA.gAerolinea_precioTF.getText());
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
            listar(vistaA.gAerolineaTable);
        }
    }

    public void Editar()
    {


        int fila = vistaA.gAerolineaTable.getSelectedRow();
        if(fila==-1)
        {
            JOptionPane.showMessageDialog(null, "Seleccione una fila");
        }
        else
        {

            areTextFieldEditable(true);
            boolean[] arr = {false, true, true, false};
            estadosBotones(arr);
            int id = Integer.parseInt(vistaA.gAerolineaTable.getValueAt(fila, 0).toString());
            String nombre = (String) vistaA.gAerolineaTable.getValueAt(fila, 1);
            String clase = (String) vistaA.gAerolineaTable.getValueAt(fila, 2);
            float precio = Float.parseFloat(vistaA.gAerolineaTable.getValueAt(fila, 3).toString());
            vistaA.gAerolinea_idTF.setText(""+id);
            vistaA.gAerolinea_nombreTF.setText(nombre);
            vistaA.gAerolinea_claseTF.setText(clase);
            vistaA.gAerolinea_precioTF.setText(""+precio);
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
                listar(vistaA.gAerolineaTable);
            }
            else
            {
                agregar();
                listar(vistaA.gAerolineaTable);
            }
        }
        else
        {
            Actualizar();
        }

    }


    //Limpiar la Tabla
    private void limpiar(){
        for (int i = 0; i < vistaA.gAerolineaTable.getRowCount(); i++){
            modelo.removeRow(i);
            i = i - 1;
        }
    }

    //Hacer editable o no editable los TextField
    private void areTextFieldEditable(boolean flag)
    {
        vistaA.gAerolinea_nombreTF.setEditable(flag);
        vistaA.gAerolinea_claseTF.setEditable(flag);
        vistaA.gAerolinea_precioTF.setEditable(flag);
    }

    //Limpiar los TextField
    private void cleanForm(){
        vistaA.gAerolinea_idTF.setText("");
        vistaA.gAerolinea_nombreTF.setText("");
        vistaA.gAerolinea_claseTF.setText("");
        vistaA.gAerolinea_precioTF.setText("");
    }

    //Activar o desactivar botones
    private void estadosBotones(boolean[] a)
    {
        vistaA.gAerolinea_addB.setEnabled(a[0]);
        vistaA.gAerolinea_saveB.setEnabled(a[1]);
        vistaA.gAerolinea_editB.setEnabled(a[2]);
        vistaA.gAerolinea_deleteB.setEnabled(a[3]);
    }
}
