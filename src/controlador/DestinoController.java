package controlador;

import modelo.DestinoDAO;
import modelo.Destinos;
import modelo.Empleado;
import modelo.EmpleadoDAO;
import vista.GestionarDestinos;
import vista.GestionarEmpleados;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class DestinoController implements ActionListener {
    Destinos d = new Destinos();
    GestionarDestinos vistaD;
    DestinoDAO dao;
    DefaultTableModel modelo = new DefaultTableModel();
    boolean key;

    public DestinoController(GestionarDestinos v, DestinoDAO dao)
    {
        int lista = dao.listar().size();
        if(lista>0)
        {
            this.vistaD = v;
            this.dao = dao;
            areTextFieldEditable(false);
            vistaD.gDestino_saveB.setEnabled(false);
            vistaD.gDestino_addB.addActionListener(this);
            vistaD.gDestino_saveB.addActionListener(this);
            vistaD.gDestino_editB.addActionListener(this);
            vistaD.gDestino_deleteB.addActionListener(this);
            listar(vistaD.gDestinoTable);
        }
        else
        {
            this.vistaD = v;
            this.dao = dao;
            areTextFieldEditable(false);
            vistaD.gDestino_saveB.setEnabled(false);
            vistaD.gDestino_addB.addActionListener(this);
            vistaD.gDestino_saveB.addActionListener(this);
            vistaD.gDestino_editB.addActionListener(this);
            vistaD.gDestino_deleteB.addActionListener(this);
            listar(vistaD.gDestinoTable);
            boolean[] arr = {true, false, false, false};
            estadosBotones(arr);
        }
    }

    public void actionPerformed(ActionEvent e)
    {
        //Boton Nuevo
        if(e.getSource()== vistaD.gDestino_addB)
        {
            key = true;
            areTextFieldEditable(true);
            boolean[] arr = {false, true, false, false};
            estadosBotones(arr);
        }
        //Boton Guardar
        if(e.getSource()== vistaD.gDestino_saveB)
        {
            boolean[] arr = {true, false, true, true};
            estadosBotones(arr);
            Guardar();
        }
        //Boton Editar
        if(e.getSource()== vistaD.gDestino_editB)
        {
            key = false;
            Editar();
        }
        //Boton eliminar
        if(e.getSource()== vistaD.gDestino_deleteB)
        {
            eliminar();
            limpiar();
            cleanForm();
            listar(vistaD.gDestinoTable);
        }

    }

    public void eliminar(){
        int fila = vistaD.gDestinoTable.getSelectedRow();
        if(fila==-1)
        {
            JOptionPane.showMessageDialog(null, "Debe selecionar un registro");
        }
        else
        {
            int lista = dao.listar().size();
            if(lista>1)
            {
                int id = Integer.parseInt((String) vistaD.gDestinoTable.getValueAt(fila, 0).toString());
                dao.delete(id);
                JOptionPane.showMessageDialog(null, "Registro eliminado");
            }
            else
            {
                boolean[] arr = {true, false, false, false};
                estadosBotones(arr);
                int id = Integer.parseInt((String) vistaD.gDestinoTable.getValueAt(fila, 0).toString());
                dao.delete(id);
                JOptionPane.showMessageDialog(null, "Registro eliminado");
            }

        }
    }

    public void listar(JTable tabla)
    {
        modelo = (DefaultTableModel)tabla.getModel();
        List<Destinos> lista = dao.listar();
        Object[] object = new Object[4];
        for (Destinos value : lista) {
            object[0] = value.getDestinationID();
            object[1] = value.getCity();
            object[2] = value.getState();
            object[3] = value.getCountry();
            modelo.addRow(object);
        }
    }

    public void agregar()
    {

        if(vistaD.gDestino_ciudadTF.getText().isEmpty() || vistaD.gDestino_estadoTF.getText().isEmpty() || vistaD.gDestino_paisTF.getText().isEmpty())
        {
            JOptionPane.showMessageDialog(null, "Uno o mas campos estan vacios, rellenalos para continuar");
            boolean[] arr = {false, true, true, false};
            estadosBotones(arr);
        }
        else
        {
            String ciudad = vistaD.gDestino_ciudadTF.getText();
            String estado = vistaD.gDestino_estadoTF.getText();
            String pais = vistaD.gDestino_paisTF.getText();
            d.setCity(ciudad);
            d.setState(estado);
            d.setCountry(pais);
            int r = dao.agregar(d);
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
        if(vistaD.gDestino_ciudadTF.getText().isEmpty() || vistaD.gDestino_estadoTF.getText().isEmpty() || vistaD.gDestino_paisTF.getText().isEmpty())
        {
            JOptionPane.showMessageDialog(null, "Uno o mas campos estan vacios, rellenalos para continuar");
            boolean[] arr = {false, true, false, false};
            estadosBotones(arr);
        }
        else
        {
            int id = Integer.parseInt(vistaD.gDestino_idTF.getText());
            String ciudad = vistaD.gDestino_ciudadTF.getText();
            String estado = vistaD.gDestino_estadoTF.getText();
            String pais = vistaD.gDestino_paisTF.getText();

            d.setDestinationID(id);
            d.setCity(ciudad);
            d.setState(estado);
            d.setCountry(pais);

            int r = dao.actualizar(d);
            if (r == 1) {
                JOptionPane.showMessageDialog(null, "Registro actualizado exitosamente");
            } else {
                JOptionPane.showMessageDialog(null, "Registro fallido");
            }
            areTextFieldEditable(false);
            limpiar();
            cleanForm();
            listar(vistaD.gDestinoTable);
        }
    }

    public void Editar()
    {
        int fila = vistaD.gDestinoTable.getSelectedRow();
        if(fila==-1)
        {
            JOptionPane.showMessageDialog(null, "Seleccione una fila");
        }
        else
        {
            areTextFieldEditable(true);
            boolean[] arr = {false, true, true, false};
            estadosBotones(arr);
            int id = Integer.parseInt(vistaD.gDestinoTable.getValueAt(fila, 0).toString());
            String ciudad = (String) vistaD.gDestinoTable.getValueAt(fila, 1);
            String estado = (String) vistaD.gDestinoTable.getValueAt(fila, 2);
            String pais = (String) vistaD.gDestinoTable.getValueAt(fila, 3);
            vistaD.gDestino_idTF.setText(""+id);
            vistaD.gDestino_ciudadTF.setText(ciudad);
            vistaD.gDestino_estadoTF.setText(estado);
            vistaD.gDestino_paisTF.setText(pais);
        }
    }
    //Guardar
    public void Guardar()
    {
        if(key)
        {
            int lista = dao.listar().size();
            if (lista>0)
            {
                agregar();
                limpiar();
                listar(vistaD.gDestinoTable);
            }
            else
            {
                agregar();
                listar(vistaD.gDestinoTable);
            }
        }
        else
        {
            Actualizar();
        }
    }


    //Limpiar la Tabla
    private void limpiar(){
        for (int i = 0; i < vistaD.gDestinoTable.getRowCount(); i++){
            modelo.removeRow(i);
            i = i - 1;
        }
    }

    //Hacer editable o no editable los TextField
    private void areTextFieldEditable(boolean flag)
    {
        vistaD.gDestino_ciudadTF.setEditable(flag);
        vistaD.gDestino_estadoTF.setEditable(flag);
        vistaD.gDestino_paisTF.setEditable(flag);
    }

    //Limpiar los TextField
    private void cleanForm(){
        vistaD.gDestino_idTF.setText("");
        vistaD.gDestino_ciudadTF.setText("");
        vistaD.gDestino_estadoTF.setText("");
        vistaD.gDestino_paisTF.setText("");
    }

    //Activar o desactivar botones
    private void estadosBotones(boolean[] a)
    {
        vistaD.gDestino_addB.setEnabled(a[0]);
        vistaD.gDestino_saveB.setEnabled(a[1]);
        vistaD.gDestino_editB.setEnabled(a[2]);
        vistaD.gDestino_deleteB.setEnabled(a[3]);
    }
}