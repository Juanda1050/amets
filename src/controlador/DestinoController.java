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
    GestionarDestinos vista;
    DestinoDAO dao;
    DefaultTableModel modelo = new DefaultTableModel();
    boolean AU;

    //Constructor del controlador
    public DestinoController(GestionarDestinos v, DestinoDAO dao)
    {
        int lista = dao.listar().size();
        if(lista>0)
        {
            this.vista = v;
            this.dao = dao;
            areTextFieldEditable(false);
            vista.gDestino_saveB.setEnabled(false);
            vista.gDestino_addB.addActionListener(this);
            vista.gDestino_saveB.addActionListener(this);
            vista.gDestino_editB.addActionListener(this);
            vista.gDestino_deleteB.addActionListener(this);
            listDestination(vista.gDestinoTable);
            boolean[] arr = {true, false, true, true};
            areButtonsEnabled(arr);
        }
        else
        {
            this.vista = v;
            this.dao = dao;
            areTextFieldEditable(false);
            vista.gDestino_saveB.setEnabled(false);
            vista.gDestino_addB.addActionListener(this);
            vista.gDestino_saveB.addActionListener(this);
            vista.gDestino_editB.addActionListener(this);
            vista.gDestino_deleteB.addActionListener(this);
            listDestination(vista.gDestinoTable);
            boolean[] arr = {true, false, false, false};
            areButtonsEnabled(arr);
        }
    }

    //ActionListener de cada boton de la vista
    public void actionPerformed(ActionEvent e)
    {
        //Boton Nuevo
        if(e.getSource()== vista.gDestino_addB)
        {
            AU = true;
            areTextFieldEditable(true);
            boolean[] arr = {false, true, false, false};
            areButtonsEnabled(arr);
        }
        //Boton Guardar
        if(e.getSource()== vista.gDestino_saveB)
        {
            saveDestination();
        }
        //Boton Editar
        if(e.getSource()== vista.gDestino_editB)
        {
            editDestination();
        }
        //Boton Eliminar
        if(e.getSource()== vista.gDestino_deleteB)
        {
            deleteDestination();
            cleanDestination();
            cleanForm();
            listDestination(vista.gDestinoTable);
        }

    }

    //Metodo agregar
    public void addDestination()
    {
        if(vista.gDestino_ciudadTF.getText().isEmpty() || vista.gDestino_estadoTF.getText().isEmpty() || vista.gDestino_paisTF.getText().isEmpty())
        {
            JOptionPane.showMessageDialog(null, "Uno o mas campos estan vacios, rellenalos para continuar");
            boolean[] arr = {false, true, false, false};
            areButtonsEnabled(arr);
        }
        else
        {
            String ciudad = vista.gDestino_ciudadTF.getText();
            String estado = vista.gDestino_estadoTF.getText();
            String pais = vista.gDestino_paisTF.getText();
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
            areButtonsEnabled(arr);
        }
    }

    //Metodo actualizar
    public void updateDestination()
    {
        if(vista.gDestino_ciudadTF.getText().isEmpty() || vista.gDestino_estadoTF.getText().isEmpty() || vista.gDestino_paisTF.getText().isEmpty())
        {
            JOptionPane.showMessageDialog(null, "Uno o mas campos estan vacios, rellenalos para continuar");
            boolean[] arr = {false, true, false, false};
            areButtonsEnabled(arr);
        }
        else
        {
            int id = Integer.parseInt(vista.gDestino_idTF.getText());
            String ciudad = vista.gDestino_ciudadTF.getText();
            String estado = vista.gDestino_estadoTF.getText();
            String pais = vista.gDestino_paisTF.getText();

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
            cleanForm();
            cleanDestination();
            listDestination(vista.gDestinoTable);
            boolean[] arr = {true, false, true, true};
            areButtonsEnabled(arr);
        }
    }

    //Metodo eliminar
    public void deleteDestination(){
        int fila = vista.gDestinoTable.getSelectedRow();
        if(fila==-1)
        {
            JOptionPane.showMessageDialog(null, "Debe selecionar un destino");
        }
        else
        {
            boolean k=true;
            int lista = dao.listar().size();
            if(lista>1)
            {
                int id = Integer.parseInt(vista.gDestinoTable.getValueAt(fila, 0).toString());
                for (int i=0; i<dao.DP().size(); i++)
                {
                    if(dao.DP().get(i) == id)
                    {
                        k=false;
                        break;
                    }
                }
                for (int i=0; i<dao.H().size(); i++)
                {
                    if (dao.H().get(i) == id)
                    {
                        k=false;
                        break;
                    }
                }
                for (int i=0; i<dao.V().size(); i++)
                {
                    if (dao.V().get(i) == id)
                    {
                        k=false;
                        break;
                    }
                }
                if (k)
                {
                    dao.delete(id);
                    JOptionPane.showMessageDialog(null, "Registro eliminado");
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "El destino esta en uso");
                }
            }
            else
            {
                int id = Integer.parseInt(vista.gDestinoTable.getValueAt(fila, 0).toString());
                for (int i=0; i<dao.DP().size(); i++)
                {
                    if(dao.DP().get(i) == id)
                    {
                        k=false;
                        break;
                    }
                }
                for (int i=0; i<dao.H().size(); i++)
                {
                    if (dao.H().get(i) == id)
                    {
                        k=false;
                        break;
                    }
                }
                for (int i=0; i<dao.V().size(); i++)
                {
                    if (dao.V().get(i) == id)
                    {
                        k=false;
                        break;
                    }
                }
                if (k)
                {
                    boolean[] arr = {true, false, false, false};
                    areButtonsEnabled(arr);
                    dao.delete(id);
                    JOptionPane.showMessageDialog(null, "Paquete eliminado");
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "El paquete esta en uso");
                }
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
                listDestination(vista.gDestinoTable);
            }
            else
            {
                addDestination();
                listDestination(vista.gDestinoTable);
            }
        }
        else
        {
            updateDestination();
        }
    }

    //Metodo editar
    public void editDestination()
    {
        AU = false;
        int row = vista.gDestinoTable.getSelectedRow();
        if(row==-1)
        {
            JOptionPane.showMessageDialog(null, "Seleccione una fila");
        }
        else
        {
            areTextFieldEditable(true);
            boolean[] arr = {false, true, false, false};
            areButtonsEnabled(arr);
            int id = Integer.parseInt(vista.gDestinoTable.getValueAt(row, 0).toString());
            String ciudad = (String) vista.gDestinoTable.getValueAt(row, 1);
            String estado = (String) vista.gDestinoTable.getValueAt(row, 2);
            String pais = (String) vista.gDestinoTable.getValueAt(row, 3);
            vista.gDestino_idTF.setText(""+id);
            vista.gDestino_ciudadTF.setText(ciudad);
            vista.gDestino_estadoTF.setText(estado);
            vista.gDestino_paisTF.setText(pais);
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
        for (int i = 0; i < vista.gDestinoTable.getRowCount(); i++){
            modelo.removeRow(i);
            i = i - 1;
        }
    }

    //Hacer editable o no editable los TextField
    private void areTextFieldEditable(boolean flag)
    {
        vista.gDestino_ciudadTF.setEditable(flag);
        vista.gDestino_estadoTF.setEditable(flag);
        vista.gDestino_paisTF.setEditable(flag);
    }

    //Limpiar los componentes
    private void cleanForm(){
        vista.gDestino_idTF.setText("");
        vista.gDestino_ciudadTF.setText("");
        vista.gDestino_estadoTF.setText("");
        vista.gDestino_paisTF.setText("");
    }

    //Activar o desactivar botones
    private void areButtonsEnabled(boolean[] a)
    {
        vista.gDestino_addB.setEnabled(a[0]);
        vista.gDestino_saveB.setEnabled(a[1]);
        vista.gDestino_editB.setEnabled(a[2]);
        vista.gDestino_deleteB.setEnabled(a[3]);
    }
}
