package controlador;

import modelo.DP;
import modelo.DetallePaqueteDAO;
import vista.DetallePaquete;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

        listDPaquete(vista.dPaqueteTable);
        areComboBoxEnabled(false);
        this.vista.dPaquete_destinoCB.addActionListener(this);
        this.vista.dPaquete_addB.addActionListener(this);
        this.vista.dPaquete_saveB.addActionListener(this);
        this.vista.dPaquete_editB.addActionListener(this);
        this.vista.dPaquete_deleteB.addActionListener(this);
        mostrarPaquetes();
        mostrarDestinos();
    }

    public void mostrarPaquetes()
    {
        boolean k;
        for (int i =0; i<dao.listarPaquetes().size(); i++)
        {
            k=true;
            int paqueteID = dao.paqueteID(dao.listarPaquetes().get(i).getName());
            for(int j=0; j<dao.sizeDP().size(); j++)
            {
                if(paqueteID == dao.sizeDP().get(j).getIdpaquete())
                {
                    k=false;
                }
            }
            if (k)
            {
                vista.dPaquete_paqueteCB.addItem(dao.listarPaquetes().get(i).getName());
            }
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
            areButtonsEnabled(arr);
            areComboBoxEnabled(true);
        }
        if (e.getSource() == vista.dPaquete_saveB)
        {
            boolean[] arr = {true, false, true, true};
            areButtonsEnabled(arr);
            saveDPaquete();
        }
        if (e.getSource() == vista.dPaquete_editB)
        {
            key = false;
            editDPaquete();
        }
        if(e.getSource() == vista.dPaquete_deleteB)
        {
            deleteDPaquete();
            cleanDPaquete();
            cleanCB();
            listDPaquete(vista.dPaqueteTable);
        }
        if(e.getSource() == vista.dPaquete_destinoCB)
        {
            mostrarVuelos();
            mostrarHoteles();
        }
    }

    public void addDPaquete()
    {

        if(vista.dPaquete_paqueteCB.getSelectedIndex() == 0 || vista.dPaquete_destinoCB.getSelectedIndex() == 0 || vista.dPaquete_vueloCB.getSelectedIndex() == 0 || vista.dPaquete_hotelCB.getSelectedIndex() == 0)
        {
            JOptionPane.showMessageDialog(null, "Uno o mas campos estan vacios, rellenalos para continuar");
            boolean[] arr = {false, true, false, false};
            areButtonsEnabled(arr);
        }
        else
        {
            int paqueteID = dao.paqueteID((String) vista.dPaquete_paqueteCB.getSelectedItem());
            int destinoID = dao.destinoID((String) vista.dPaquete_destinoCB.getSelectedItem());
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
            areButtonsEnabled(arr);
        }
    }

    public void updateDPaquete()
    {
        String p = (String) vista.dPaquete_paqueteCB.getSelectedItem();
        String d = (String) vista.dPaquete_destinoCB.getSelectedItem();
        String v = vista.dPaquete_vueloCB.getSelectedItem().toString();
        String h = (String) vista.dPaquete_hotelCB.getSelectedItem();
        if(p.compareTo("Seleccione paquete")==0 || d.compareTo("Seleccione destino")==0 || v.compareTo("Seleccione vuelo")==0 || h.compareTo("Seleccione hotel")==0)
        {
            JOptionPane.showMessageDialog(null, "Uno o mas campos estan vacios, rellenalos para continuar");
            boolean[] arr = {false, true, false, false};
            areButtonsEnabled(arr);
        }
        else
        {
            int paqueteID = dao.paqueteID((String) vista.dPaquete_paqueteCB.getSelectedItem());
            int destinoID = dao.destinoID((String) vista.dPaquete_destinoCB.getSelectedItem());
            int vueloID = (int) vista.dPaquete_vueloCB.getSelectedItem();
            int hotelID = dao.hotelID((String) vista.dPaquete_hotelCB.getSelectedItem());
            dp.setIdpaquete(paqueteID);
            dp.setIddestino(destinoID);
            dp.setIdVuelo(vueloID);
            dp.setIdhotel(hotelID);
            int r = dao.actualizar(dp);
            if (r == 1) {
                JOptionPane.showMessageDialog(null, "Registro actualizado exitosamente");
            } else {
                JOptionPane.showMessageDialog(null, "Registro fallido");
            }
            areComboBoxEnabled(false);
            cleanDPaquete();
            cleanCB();
            listDPaquete(vista.dPaqueteTable);
        }
    }

    public void deleteDPaquete(){
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
                areButtonsEnabled(arr);
                int packID = dao.paqueteID((String) vista.dPaqueteTable.getValueAt(fila, 0).toString());
                int flightID = Integer.parseInt((String) vista.dPaqueteTable.getValueAt(fila, 1).toString());
                int hotelID = dao.hotelID((String) vista.dPaqueteTable.getValueAt(fila, 2).toString());
                int destID = dao.destinoID((String) vista.dPaqueteTable.getValueAt(fila, 3).toString());
                dao.eliminar(packID, flightID, hotelID, destID);
                JOptionPane.showMessageDialog(null, "Usuario eliminado");
            }

        }
    }

    private void areComboBoxEnabled(boolean flag)
    {
        vista.dPaquete_paqueteCB.setEnabled(flag);
        vista.dPaquete_destinoCB.setEnabled(flag);
        vista.dPaquete_vueloCB.setEnabled(flag);
        vista.dPaquete_hotelCB.setEnabled(flag);
    }

    public void saveDPaquete()
    {
        if(key)
        {
            int lista = dao.sizeDP().size();
            if (lista>0)
            {
                addDPaquete();
                cleanDPaquete();
                listDPaquete(vista.dPaqueteTable);
            }
            else
            {
                addDPaquete();
                listDPaquete(vista.dPaqueteTable);
            }
        }
        else
        {
            updateDPaquete();
        }
    }

    public void listDPaquete(JTable tabla)
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
    
    private void cleanDPaquete(){
        for (int i = 0; i < vista.dPaqueteTable.getRowCount(); i++){
            modelo.removeRow(i);
            i = i - 1;
        }
    }

    public void editDPaquete()
    {
        int fila = vista.dPaqueteTable.getSelectedRow();
        if(fila==-1)
        {
            JOptionPane.showMessageDialog(null, "Seleccione una fila");
        }
        else
        {
            areComboBoxEnabled(true);
            vista.dPaquete_paqueteCB.setEnabled(false);
            boolean[] arr = {false, true, false, false};
            areButtonsEnabled(arr);
            String paqueteEd = (String) vista.dPaqueteTable.getValueAt(fila, 0);
            int flightIdEd = Integer.parseInt(vista.dPaqueteTable.getValueAt(fila, 1).toString());
            String hotelNameEd = (String) vista.dPaqueteTable.getValueAt(fila, 2);
            String destNameEd = (String) vista.dPaqueteTable.getValueAt(fila, 3);
            rellenarCB(paqueteEd, flightIdEd, hotelNameEd, destNameEd);
        }
    }

    //Controla el estado de los botones
    private void areButtonsEnabled(boolean[] a)
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
        vista.dPaquete_paqueteCB.addItem("Seleccione paquete");
        mostrarPaquetes();
        vista.dPaquete_destinoCB.removeAllItems();
        vista.dPaquete_destinoCB.addItem("Seleccione destino");
        mostrarDestinos();
        vista.dPaquete_vueloCB.removeAllItems();
        vista.dPaquete_vueloCB.addItem("Seleccione vuelo");
        vista.dPaquete_hotelCB.removeAllItems();
        vista.dPaquete_hotelCB.addItem("Seleccione hotel");
    }

    private void rellenarCB(String paqueteEd, int flightIdEd, String hotelNameEd , String destNameEd)
    {
        vista.dPaquete_paqueteCB.removeAllItems();
        vista.dPaquete_paqueteCB.addItem(paqueteEd);
        for (int i =0; i<dao.listarPaquetes().size(); i++)
        {
            if(dao.listarPaquetes().get(i).getName().compareTo(paqueteEd) != 0)
            {
                vista.dPaquete_paqueteCB.addItem(dao.listarPaquetes().get(i).getName());
            }
        }
        vista.dPaquete_destinoCB.removeAllItems();
        vista.dPaquete_destinoCB.addItem(destNameEd);
        for (int i=0; i<dao.listarDestinos().size(); i++)
        {
            if (dao.listarDestinos().get(i).getCity().compareTo(destNameEd) != 0)
            {
                vista.dPaquete_destinoCB.addItem(dao.listarDestinos().get(i).getCity());
            }
        }
        String city = (String) vista.dPaquete_destinoCB.getSelectedItem();
        vista.dPaquete_vueloCB.removeAllItems();
        vista.dPaquete_vueloCB.addItem(flightIdEd);
        for (int i =0; i<dao.vuelosId(city).size(); i++)
        {
            if (dao.vuelosId(city).get(i) != flightIdEd)
            {
                vista.dPaquete_vueloCB.addItem(dao.vuelosId(city).get(i));
            }
        }
        vista.dPaquete_hotelCB.removeAllItems();
        vista.dPaquete_hotelCB.addItem(hotelNameEd);
        for(int i =0; i<dao.nombresHoteles(city).size(); i++)
        {
            if (dao.nombresHoteles(city).get(i).compareTo(hotelNameEd) != 0)
            {
                vista.dPaquete_hotelCB.addItem(dao.nombresHoteles(city).get(i));
            }
        }
    }
}
