package controlador;

import modelo.Paquetes;
import modelo.PaquetesDAO;
import vista.GestionarPaquetes;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PaquetesController implements ActionListener
{
    PaquetesDAO dao = new PaquetesDAO();
    Paquetes p = new Paquetes();
    GestionarPaquetes vista = new GestionarPaquetes();
    DefaultTableModel modelo = new DefaultTableModel();
    boolean key;

    public PaquetesController(GestionarPaquetes v, PaquetesDAO dao)
    {
        int lista = dao.listar().size();
        if(lista>0)
        {
            this.vista = v;
            this.dao = dao;
            areTextFieldEditable(false);
            vista.gPaquete_saveB.setEnabled(false);
            vista.gPaquete_addB.addActionListener(this);
            vista.gPaquete_saveB.addActionListener(this);
            vista.gPaquete_editB.addActionListener(this);
            vista.gPaquete_deleteB.addActionListener(this);
            listar(vista.gPaqueteTable);
        }
        else
        {
            this.vista = v;
            this.dao = dao;
            areTextFieldEditable(false);
            vista.gPaquete_saveB.setEnabled(false);
            vista.gPaquete_addB.addActionListener(this);
            vista.gPaquete_saveB.addActionListener(this);
            vista.gPaquete_editB.addActionListener(this);
            vista.gPaquete_deleteB.addActionListener(this);
            listar(vista.gPaqueteTable);
            boolean[] arr = {true, false, false, false};
            estadosBotones(arr);
        }
    }

    public void actionPerformed(ActionEvent e)
    {
        //Boton Nuevo
        if(e.getSource()==vista.gPaquete_addB)
        {
            key = true;
            areTextFieldEditable(true);
            boolean[] arr = {false, true, false, false};
            estadosBotones(arr);
        }
        //Boton Guardar
        if(e.getSource()==vista.gPaquete_saveB)
        {
            boolean[] arr = {true, false, true, true};
            estadosBotones(arr);
            Guardar();
        }
        //Boton Editar
        if(e.getSource()==vista.gPaquete_editB)
        {
            key = false;
            Editar();
        }
        //Boton eliminar
        if(e.getSource()==vista.gPaquete_deleteB)
        {
            eliminar();
            limpiar();
            cleanForm();
            listar(vista.gPaqueteTable);
        }

    }

    public void eliminar(){
        int fila = vista.gPaqueteTable.getSelectedRow();
        if(fila==-1)
        {
            JOptionPane.showMessageDialog(null, "Debe selecionar un usuario");
        }
        else
        {
            int lista = dao.listar().size();
            if(lista>1)
            {
                int id = Integer.parseInt((String) vista.gPaqueteTable.getValueAt(fila, 0).toString());
                dao.eliminar(id);
                JOptionPane.showMessageDialog(null, "Usuario eliminado");
            }
            else
            {
                boolean[] arr = {true, false, false, false};
                estadosBotones(arr);
                int id = Integer.parseInt((String) vista.gPaqueteTable.getValueAt(fila, 0).toString());
                dao.eliminar(id);
                JOptionPane.showMessageDialog(null, "Usuario eliminado");
            }

        }
    }

    public void listar(JTable tabla)
    {
        modelo = (DefaultTableModel)tabla.getModel();
        tabla.setModel(modelo);
        int lista = dao.listar().size();
        Object[] object = new Object[5];
        for (int i =0; i<lista; i++)
        {
           object[0] = dao.listar().get(i).getID();
           object[1] = dao.listar().get(i).getName();
           object[2] = dao.listar().get(i).getDescription();
           object[3] = dao.listar().get(i).getPassengers();
            object[4] = dao.listar().get(i).getPrice();
            modelo.addRow(object);
        }
    }

    public void agregar()
    {

        if(vista.gPaquete_nombreTF.getText().isEmpty() || vista.gPaquete_descripcionTF.getText().isEmpty() || vista.gPaquete_genteTF.getText().isEmpty() || vista.gPaquete_precioTF.getText().isEmpty())
        {
            JOptionPane.showMessageDialog(null, "Uno o mas campos estan vacios, rellenalos para continuar");
            boolean[] arr = {false, true, false, false};
            estadosBotones(arr);
        }
        else
        {

            boolean i = validacionI(vista.gPaquete_genteTF.getText());
            boolean f = validacionF(vista.gPaquete_precioTF.getText());
            if(!i || !f)
            {
                JOptionPane.showMessageDialog(null, "Los campos pasajeros y precio no pueden contener letras, verificalos de nuevo");
                boolean[] arr = {false, true, false, false};
                estadosBotones(arr);
            }
            else {
                String name = vista.gPaquete_nombreTF.getText();
                String description = vista.gPaquete_descripcionTF.getText();
                int passengers = Integer.parseInt(vista.gPaquete_genteTF.getText());
                float price = Float.parseFloat(vista.gPaquete_precioTF.getText());
                p.setName(name);
                p.setDescription(description);
                p.setPassengers(passengers);
                p.setPrice(price);
                int r = dao.agregar(p);
                if (r == 1) {
                    JOptionPane.showMessageDialog(null, "Paquete agregado exitosamente");
                } else {
                    JOptionPane.showMessageDialog(null, "Paquete fallido");
                }
                areTextFieldEditable(false);
                cleanForm();
                boolean[] arr = {true, false, true, true};
                estadosBotones(arr);
            }
        }
    }


    public void Actualizar()
    {
        if(vista.gPaquete_nombreTF.getText().isEmpty() || vista.gPaquete_descripcionTF.getText().isEmpty() || vista.gPaquete_genteTF.getText().isEmpty() || vista.gPaquete_precioTF.getText().isEmpty())
        {
            JOptionPane.showMessageDialog(null, "Uno o mas campos estan vacios, rellenalos para continuar");
            boolean[] arr = {false, true, false, false};
            estadosBotones(arr);
        }
        else
        {
            boolean i = validacionI(vista.gPaquete_genteTF.getText());
            boolean f = validacionF(vista.gPaquete_precioTF.getText());
            if(!i || !f)
            {
                JOptionPane.showMessageDialog(null, "Los campos Personas y Precio no pueden contener letras, verificalos de nuevo");
                boolean[] arr = {false, true, false, false};
                estadosBotones(arr);
            }
            else
            {
                int id = Integer.parseInt(vista.gPaquete_idTF.getText());
                String name = vista.gPaquete_nombreTF.getText();
                String description = vista.gPaquete_descripcionTF.getText();
                int passengers = Integer.parseInt(vista.gPaquete_genteTF.getText());
                float price = Float.parseFloat(vista.gPaquete_precioTF.getText());
                p.setID(id);
                p.setName(name);
                p.setDescription(description);
                p.setPassengers(passengers);
                p.setPrice(price);
                int r = dao.Actualizar(p);
                if (r == 1) {
                    JOptionPane.showMessageDialog(null, "Registro actualizado exitosamente");
                } else {
                    JOptionPane.showMessageDialog(null, "Registro fallido");
                }
                areTextFieldEditable(false);
                limpiar();
                cleanForm();
                listar(vista.gPaqueteTable);
            }
        }
    }

    public void Editar()
    {
        int fila = vista.gPaqueteTable.getSelectedRow();
        if(fila==-1)
        {
            JOptionPane.showMessageDialog(null, "Seleccione una fila");
        }
        else
        {
            areTextFieldEditable(true);
            boolean[] arr = {false, true, false, false};
            estadosBotones(arr);
            int id = Integer.parseInt(vista.gPaqueteTable.getValueAt(fila, 0).toString());
            String name = (String) vista.gPaqueteTable.getValueAt(fila, 1);
            String description = (String) vista.gPaqueteTable.getValueAt(fila, 2);
            int passengers = Integer.parseInt(vista.gPaqueteTable.getValueAt(fila, 3).toString());
            float price = Float.parseFloat(vista.gPaqueteTable.getValueAt(fila, 4).toString());
            vista.gPaquete_idTF.setText(""+id);
            vista.gPaquete_nombreTF.setText(""+name);
            vista.gPaquete_descripcionTF.setText(""+description);
            vista.gPaquete_genteTF.setText(""+passengers);
            vista.gPaquete_precioTF.setText(""+price);
        }
    }

    public void Guardar()
    {
        if(key)
        {
            int lista = dao.listar().size();
            if (lista>0)
            {
                agregar();
                limpiar();
                listar(vista.gPaqueteTable);
            }
            else
            {
                agregar();
                listar(vista.gPaqueteTable);
            }
        }
        else
        {
            Actualizar();
        }
    }


    //Limpiar la Tabla
    private void limpiar(){
        for (int i = 0; i < vista.gPaqueteTable.getRowCount(); i++){
            modelo.removeRow(i);
            i = i - 1;
        }
    }

    //Hacer editable o no editable los TextField
    private void areTextFieldEditable(boolean flag)
    {
        vista.gPaquete_nombreTF.setEditable(flag);
        vista.gPaquete_descripcionTF.setEditable(flag);
        vista.gPaquete_genteTF.setEditable(flag);
        vista.gPaquete_precioTF.setEditable(flag);
    }

    //Limpiar los TextField
    private void cleanForm(){
        vista.gPaquete_idTF.setText("");
        vista.gPaquete_nombreTF.setText("");
        vista.gPaquete_descripcionTF.setText("");
        vista.gPaquete_genteTF.setText("");
        vista.gPaquete_precioTF.setText("");
    }

    //Activar o desactivar botones
    private void estadosBotones(boolean[] a)
    {
        vista.gPaquete_addB.setEnabled(a[0]);
        vista.gPaquete_saveB.setEnabled(a[1]);
        vista.gPaquete_editB.setEnabled(a[2]);
        vista.gPaquete_deleteB.setEnabled(a[3]);
    }

    //Validar si pasajeros es un numero
    private boolean validacionI(String cadena)
    {
        int num;
        try
        {
            num = Integer.parseInt(cadena);
            return true;
        }
        catch (Exception e)
        {
            return false;
        }
    }

    //Validar si el precio es un numero
    private boolean validacionF(String cadena)
    {
        float num;
        try
        {
            num = Float.parseFloat(cadena);
            return true;
        }
        catch (Exception e)
        {
            return false;
        }
    }
}