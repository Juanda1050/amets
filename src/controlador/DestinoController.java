package controlador;

import modelo.DestinoDAO;
import modelo.Destinos;
import vista.GestionarDestinos;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class DestinoController implements ActionListener {
    Destinos d = new Destinos();
    GestionarDestinos vistaD;
    DestinoDAO dao;
    DefaultTableModel modelo = new DefaultTableModel();
    boolean AU;

    //Constructor del controlador
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
            listDestination(vistaD.gDestinoTable);
            boolean[] arr = {true, false, true, true};
            areButtonsEnabled(arr);
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
            listDestination(vistaD.gDestinoTable);
            boolean[] arr = {true, false, false, false};
            areButtonsEnabled(arr);
        }
        areTextFieldEditable(false);
    }

    //ActionListener de cada boton de la vista
    public void actionPerformed(ActionEvent e)
    {
        //Boton Nuevo
        if(e.getSource()== vistaD.gDestino_addB)
        {
            AU = true;
            areTextFieldEditable(true);
            boolean[] arr = {false, true, false, false};
            areButtonsEnabled(arr);
        }
        //Boton Guardar
        if(e.getSource()== vistaD.gDestino_saveB)
        {
            saveDestination();
        }
        //Boton Editar
        if(e.getSource()== vistaD.gDestino_editB)
        {
            editDestination();
        }
        //Boton Eliminar
        if(e.getSource()== vistaD.gDestino_deleteB)
        {
            deleteDestination();
            cleanDestination();
            cleanForm();
            listDestination(vistaD.gDestinoTable);
        }

    }

    //Metodo agregar
    public void addDestination()
    {
        if(vistaD.gDestino_ciudadTF.getText().isEmpty() || vistaD.gDestino_estadoTF.getText().isEmpty() || vistaD.gDestino_paisTF.getText().isEmpty())
        {
            JOptionPane.showMessageDialog(null, "Uno o mas campos estan vacios, rellenalos para continuar");
            boolean[] arr = {false, true, false, false};
            areButtonsEnabled(arr);
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
            boolean[] arr = {true, false, true, true};
            areButtonsEnabled(arr);
        }
    }

    //Metodo actualizar
    public void updateDestination()
    {
        if(vistaD.gDestino_ciudadTF.getText().isEmpty() || vistaD.gDestino_estadoTF.getText().isEmpty() || vistaD.gDestino_paisTF.getText().isEmpty())
        {
            JOptionPane.showMessageDialog(null, "Uno o mas campos estan vacios, rellenalos para continuar");
            boolean[] arr = {false, true, false, false};
            areButtonsEnabled(arr);
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
            boolean[] arr = {true, false, true, true};
            areButtonsEnabled(arr);
        }
    }

    //Metodo eliminar
    public void deleteDestination(){
        int row = vistaD.gDestinoTable.getSelectedRow();
        if(row==-1)
        {
            JOptionPane.showMessageDialog(null, "Debe selecionar un destino");
        }
        else
        {
            int lista = dao.listar().size();
            if(lista>1)
            {
                int id = Integer.parseInt(vistaD.gDestinoTable.getValueAt(row, 0).toString());
                dao.delete(id);
                JOptionPane.showMessageDialog(null, "Registro eliminado");
            }
            else
            {
                boolean[] arr = {true, false, false, false};
                areButtonsEnabled(arr);
                int id = Integer.parseInt(vistaD.gDestinoTable.getValueAt(row, 0).toString());
                dao.delete(id);
                JOptionPane.showMessageDialog(null, "Registro eliminado");
            }

        }
    }

    //Metodo guardar
    public void saveDestination()
    {
        if(AU)
        {
            int lista = dao.listar().size();
            if (lista>0)
            {
                addDestination();
                cleanDestination();
                listDestination(vistaD.gDestinoTable);
                areTextFieldEditable(false);
                cleanForm();
            }
            else
            {
                addDestination();
                listDestination(vistaD.gDestinoTable);
                areTextFieldEditable(false);
                cleanForm();
            }
        }
        else
        {
            updateDestination();
            cleanDestination();
            listDestination(vistaD.gDestinoTable);
            areTextFieldEditable(false);
            cleanForm();
        }
    }

    //Metodo editar
    public void editDestination()
    {
        AU = false;
        int row = vistaD.gDestinoTable.getSelectedRow();
        if(row==-1)
        {
            JOptionPane.showMessageDialog(null, "Seleccione una fila");
        }
        else
        {
            areTextFieldEditable(true);
            boolean[] arr = {false, true, false, false};
            areButtonsEnabled(arr);
            int id = Integer.parseInt(vistaD.gDestinoTable.getValueAt(row, 0).toString());
            String ciudad = (String) vistaD.gDestinoTable.getValueAt(row, 1);
            String estado = (String) vistaD.gDestinoTable.getValueAt(row, 2);
            String pais = (String) vistaD.gDestinoTable.getValueAt(row, 3);
            vistaD.gDestino_idTF.setText(""+id);
            vistaD.gDestino_ciudadTF.setText(ciudad);
            vistaD.gDestino_estadoTF.setText(estado);
            vistaD.gDestino_paisTF.setText(pais);
        }
    }

    //Listar la tabla destinos en el JTable
    public void listDestination(JTable tabla)
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

    //Actualizar la Tabla cada que se hace un cambio
    private void cleanDestination(){
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

    //Limpiar los componentes
    private void cleanForm(){
        vistaD.gDestino_idTF.setText("");
        vistaD.gDestino_ciudadTF.setText("");
        vistaD.gDestino_estadoTF.setText("");
        vistaD.gDestino_paisTF.setText("");
    }

    //Activar o desactivar botones
    private void areButtonsEnabled(boolean[] a)
    {
        vistaD.gDestino_addB.setEnabled(a[0]);
        vistaD.gDestino_saveB.setEnabled(a[1]);
        vistaD.gDestino_editB.setEnabled(a[2]);
        vistaD.gDestino_deleteB.setEnabled(a[3]);
    }
}
