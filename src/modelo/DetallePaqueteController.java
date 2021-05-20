package modelo;

import vista.DetallePaquete;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class DetallePaqueteController implements ActionListener
{
    DP dp = new DP();
    DetallePaquete vista;
    DetallePaqueteDAO dao;
    DefaultTableModel modelo = new DefaultTableModel();
    boolean key;

    public DetallePaqueteController(DetallePaquete vista, DetallePaqueteDAO dao) {
        this.vista = vista;
        this.dao = dao;

        listar(vista.dPaqueteTable);
        areComboBoxEnabled(false);
        this.vista.dPaquete_destinoCB.addActionListener(this);
        this.vista.dPaquete_addB.addActionListener(this);
        this.vista.dPaquete_saveB.addActionListener(this);
        this.vista.dPaquete_deleteB.addActionListener(this);
        mostrarPaquetes();
        mostrarDestinos();
    }

    public void mostrarPaquetes()
    {
        for (int i =0; i<dao.listarPaquetes().size(); i++)
        {
            vista.dPaquete_paqueteCB.addItem(dao.listarPaquetes().get(i).getName());
        }
    }
    public void mostrarDestinos()
    {
        for (int i =0; i<dao.listarDestinos().size(); i++)
        {
            vista.dPaquete_destinoCB.addItem(dao.listarDestinos().get(i).getCity());
        }
    }

    public void mostrarVuelos()
    {
        String city = (String) vista.dPaquete_destinoCB.getSelectedItem();

        vista.dPaquete_vueloCB.removeAllItems();
        vista.dPaquete_vueloCB.addItem("Seleccione vuelo");
        for (int i =0; i<dao.vuelosId(city).size(); i++)
        {
            vista.dPaquete_vueloCB.addItem(dao.vuelosId(city).get(i));
        }
    }
    public void mostrarHoteles()
    {
        String city = (String) vista.dPaquete_destinoCB.getSelectedItem();
        vista.dPaquete_hotelCB.removeAllItems();
        vista.dPaquete_hotelCB.addItem("Seleccione hotel");
        for(int i =0; i<dao.nombresHoteles(city).size(); i++)
        {
            vista.dPaquete_hotelCB.addItem(dao.nombresHoteles(city).get(i));
        }
    }


    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == vista.dPaquete_addB)
        {
            key = true;
            boolean[] arr = {false, true, false, false};
            estadosBotones(arr);
            areComboBoxEnabled(true);
        }
        if (e.getSource() == vista.dPaquete_saveB)
        {
            guardar();
        }
        if(e.getSource() == vista.dPaquete_deleteB)
        {
            eliminar();
            limpiar();
            cleanCB();
            listar(vista.dPaqueteTable);
        }
        if(e.getSource() == vista.dPaquete_destinoCB)
        {
            mostrarVuelos();
            mostrarHoteles();
        }
    }

    public void listar(JTable tabla)
    {
        modelo = (DefaultTableModel)tabla.getModel();
        tabla.setModel(modelo);
        Object[] object = new Object[4];
        for (int i =0; i<dao.sizeDP().size(); i++)
        {
            object[0] = dao.packName(dao.sizeDP().get(i).getIdpaquete());
            object[1] = dao.sizeDP().get(i).getIdVuelo();
            object[2] = dao.hotelName(dao.sizeDP().get(i).getIdhotel());
            object[3] = dao.destName(dao.sizeDP().get(i).getIddestino());
            modelo.addRow(object);
        }
    }

    public void agregar()
    {

        if(vista.dPaquete_paqueteCB.getSelectedIndex() == 0 || vista.dPaquete_destinoCB.getSelectedIndex() == 0 || vista.dPaquete_vueloCB.getSelectedIndex() == 0 || vista.dPaquete_hotelCB.getSelectedIndex() == 0)
        {
            JOptionPane.showMessageDialog(null, "Uno o mas campos estan vacios, rellenalos para continuar");
            boolean[] arr = {false, true, false, false};
            estadosBotones(arr);
        }
        else
        {
            int Ip = vista.dPaquete_paqueteCB.getSelectedIndex() - 1;
            int Id = vista.dPaquete_destinoCB.getSelectedIndex() -1 ;

            int paqueteID = dao.listarPaquetes().get(Ip).getID();
            int destinoID = dao.listarDestinos().get(Id).getDestinationID();
            int vueloID = (int) vista.dPaquete_vueloCB.getSelectedItem();
            int hotelID = dao.hotelID((String) vista.dPaquete_hotelCB.getSelectedItem());
            System.out.println(hotelID);
            dp.setIdpaquete(paqueteID);
            dp.setIddestino(destinoID);
            dp.setIdVuelo(vueloID);
            dp.setIdhotel(hotelID);
            int r = dao.agregar(dp);
            if (r == 1) {
                JOptionPane.showMessageDialog(null, "Paquete agregado exitosamente");
            } else {
                JOptionPane.showMessageDialog(null, "Paquete fallido");
            }
            areComboBoxEnabled(false);
            cleanCB();
            boolean[] arr = {true, false, true, true};
            estadosBotones(arr);
        }
    }

    private void areComboBoxEnabled(boolean flag)
    {
        vista.dPaquete_paqueteCB.setEnabled(flag);
        vista.dPaquete_destinoCB.setEnabled(flag);
        vista.dPaquete_vueloCB.setEnabled(flag);
        vista.dPaquete_hotelCB.setEnabled(flag);
    }

    public void guardar()
    {
        if(key)
        {
            int lista = dao.sizeDP().size();
            if (lista>0)
            {
                agregar();
                limpiar();
                listar(vista.dPaqueteTable);
            }
            else
            {
                agregar();
                listar(vista.dPaqueteTable);
            }
        }
        else
        {
            //Actualizar();
        }
    }

    private void limpiar(){
        for (int i = 0; i < vista.dPaqueteTable.getRowCount(); i++){
            modelo.removeRow(i);
            i = i - 1;
        }
    }

    public void Editar()
    {
        int fila = vista.dPaqueteTable.getSelectedRow();
        if(fila==-1)
        {
            JOptionPane.showMessageDialog(null, "Seleccione una fila");
        }
        else
        {
            areComboBoxEnabled(true);
            boolean[] arr = {false, true, false, false};
            estadosBotones(arr);
            String paquete = (String) vista.dPaqueteTable.getValueAt(fila, 0);
            int flightID = Integer.parseInt((String) vista.dPaqueteTable.getValueAt(fila, 1).toString());
            String hotelName = (String) vista.dPaqueteTable.getValueAt(fila, 2);
            String destName = (String) vista.dPaqueteTable.getValueAt(fila, 3);

            /*vista.gPaquete_idTF.setText(""+id);
            vista.gPaquete_nombreTF.setText(""+name);
            vista.gPaquete_descripcionTF.setText(""+description);
            vista.gPaquete_genteTF.setText(""+passengers);
            vista.gPaquete_precioTF.setText(""+price);*/
        }
    }

    public void eliminar(){
        int fila = vista.dPaqueteTable.getSelectedRow();
        if(fila==-1)
        {
            JOptionPane.showMessageDialog(null, "Debe selecionar una fila de datos de paquete");
        }
        else
        {
            int lista = dao.sizeDP().size();
            if(lista>1)
            {
                int packID = dao.paqueteID((String) vista.dPaqueteTable.getValueAt(fila, 0).toString());
                int flightID = Integer.parseInt((String) vista.dPaqueteTable.getValueAt(fila, 1).toString());
                int hotelID = dao.hotelID((String) vista.dPaqueteTable.getValueAt(fila, 2).toString());
                int destID = dao.destinoID((String) vista.dPaqueteTable.getValueAt(fila, 3).toString());
                dao.eliminar(packID, flightID, hotelID, destID);
                JOptionPane.showMessageDialog(null, "Datos de paquete eliminado");
            }
            else
            {
                boolean[] arr = {true, false, false, false};
                estadosBotones(arr);
                int packID = dao.paqueteID((String) vista.dPaqueteTable.getValueAt(fila, 0).toString());
                int flightID = Integer.parseInt((String) vista.dPaqueteTable.getValueAt(fila, 1).toString());
                int hotelID = dao.hotelID((String) vista.dPaqueteTable.getValueAt(fila, 2).toString());
                int destID = dao.destinoID((String) vista.dPaqueteTable.getValueAt(fila, 3).toString());
                dao.eliminar(packID, flightID, hotelID, destID);
                JOptionPane.showMessageDialog(null, "Usuario eliminado");
            }

        }
    }

    //Controla el estado de los botones
    private void estadosBotones(boolean[] a)
    {
        vista.dPaquete_addB.setEnabled(a[0]);
        vista.dPaquete_saveB.setEnabled(a[1]);
        vista.dPaquete_editB.setEnabled(a[2]);
        vista.dPaquete_deleteB.setEnabled(a[3]);
    }

    //Reinicia los ComboBox
    private void cleanCB()
    {
        vista.dPaquete_paqueteCB.removeAllItems();
        vista.dPaquete_paqueteCB.addItem("Seleccione un paquete");
        mostrarPaquetes();
        vista.dPaquete_destinoCB.removeAllItems();
        vista.dPaquete_destinoCB.addItem("Seleccione un destino");
        mostrarDestinos();
        vista.dPaquete_vueloCB.removeAllItems();
        vista.dPaquete_vueloCB.addItem("Seleccione un vuelo");
        vista.dPaquete_hotelCB.removeAllItems();
        vista.dPaquete_hotelCB.addItem("Seleccione un hotel");
    }
}
