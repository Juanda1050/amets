package modelo;

import vista.DetallePaquete;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DetallePaqueteController implements ActionListener
{
    DP dp = new DP();
    DetallePaquete vista;
    DetallePaqueteDAO dao;

    public DetallePaqueteController(DetallePaquete vista, DetallePaqueteDAO dao) {
        this.vista = vista;
        this.dao = dao;

        areComboBoxEnabled(false);
        this.vista.dPaquete_destinoCB.addActionListener(this);
        this.vista.dPaquete_addB.addActionListener(this);
        this.vista.dPaquete_saveB.addActionListener(this);
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
        vista.dPaquete_vueloCB.addItem("Seleccionar vuelo");
        for (int i =0; i<dao.vuelosId(city).size(); i++)
        {
            vista.dPaquete_vueloCB.addItem(dao.vuelosId(city).get(i));
        }
    }
    public void mostrarHoteles()
    {
        String city = (String) vista.dPaquete_destinoCB.getSelectedItem();
        vista.dPaquete_hotelCB.removeAllItems();
        vista.dPaquete_hotelCB.addItem("Seleccionar hotel");
        for(int i =0; i<dao.nombresHoteles(city).size(); i++)
        {
            vista.dPaquete_hotelCB.addItem(dao.nombresHoteles(city).get(i));
        }
    }


    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == vista.dPaquete_destinoCB)
        {
            mostrarVuelos();
            mostrarHoteles();
        }
        if(e.getSource() == vista.dPaquete_addB)
        {
            areComboBoxEnabled(true);
        }
        if (e.getSource() == vista.dPaquete_saveB)
        {
            agregar();
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
            //Aqui
            int r = dao.agregar(dp);
            if (r == 1) {
                JOptionPane.showMessageDialog(null, "Paquete agregado exitosamente");
            } else {
                JOptionPane.showMessageDialog(null, "Paquete fallido");
            }
            areComboBoxEnabled(false);
            //Hacer metodo para limpiar combobox
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

    //Controla el estado de los botones
    private void estadosBotones(boolean[] a)
    {
        vista.dPaquete_addB.setEnabled(a[0]);
        vista.dPaquete_saveB.setEnabled(a[1]);
        vista.dPaquete_editB.setEnabled(a[2]);
        vista.dPaquete_deleteB.setEnabled(a[3]);
    }
}
