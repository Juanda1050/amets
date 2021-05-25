package controlador;

import modelo.CorteDAO;
import modelo.VentaDAO;
import vista.Retorno;
import vista.VistaCC;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class CorteController implements ActionListener, WindowListener
{
    VistaCC vista;
    VentaDAO vDao;
    int agentID;
    int lastSaleID;
    DefaultTableModel modelo = new DefaultTableModel();
    float total;

    //Constructor del controlador
    public CorteController(VistaCC v, VentaDAO dao, int agentID){
        this.vista = v;
        this.vDao = dao;
        this.agentID = agentID;
        listar(this.vista.cCorteTable);
        vista.cCorte_corteB.addActionListener(this);
        vista.cCorte_backB.addActionListener(this);
        vista.cCorteFrame.addWindowListener(this);
    }

    //Listar la tabla ventas en el JTable
    public void listar(JTable destinosTable){
        modelo = (DefaultTableModel) destinosTable.getModel();
        destinosTable.setModel(modelo);

        CorteDAO cDao = new CorteDAO();

        Object[]object = new Object[10];
        int registro = vDao.listar().size();
        for (int i = 0; i < registro; i++){

            if(vDao.listar().get(i).getSaleID()>cDao.getLastCorte()){
                object[0] = vDao.listar().get(i).getSaleID();
                if(i==(registro-1)){
                    lastSaleID = vDao.listar().get(i).getSaleID();
                }
                object[1] = vDao.listar().get(i).getSaleDescription();
                object[2] = vDao.listar().get(i).getAgentID();
                object[3] = vDao.listar().get(i).getPayment();
                object[4] = vDao.listar().get(i).getTotal();
                total = total + vDao.listar().get(i).getTotal();
                modelo.addRow(object);
            }
        }
    }

    public float getTotal(){
        return total;
    }

    //ActionListener de cada boton de la vista
    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == vista.cCorte_backB) {
            Retorno rtn = new Retorno();
            vista.cCorteFrame.setVisible(rtn.runReturn(agentID));
        }

        if(e.getSource() == vista.cCorte_corteB){
            // Se realiza el corte
            CorteDAO corteDAO = new CorteDAO();
            int turno = 0;
            if(vista.cCorte_horarioTF.getText().equals("Matutino")){
                turno = 1;
            }else if(vista.cCorte_horarioTF.getText().equals("Vespertino")){
                turno = 2;
            }

            if(corteDAO.saveCorte(lastSaleID, agentID, turno, vista.cCorte_totalTF.getText())){
                DefaultTableModel model = (DefaultTableModel) vista.cCorteTable.getModel();
                model.setRowCount(0);
                total = 0;
                vista.cCorte_totalTF.setText(String.valueOf(total));
            }else{
                JOptionPane.showMessageDialog(null, "ERROR AL HACER EL CORTE DE CAJA","Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {

        if (e.getSource() == vista.cCorteFrame) {
            int result = JOptionPane.showConfirmDialog(vista.cCorteFrame, "¿Desea cerrar el programa?", "Salir del programa", JOptionPane.YES_NO_OPTION);
            if (result == JOptionPane.YES_OPTION)
            {
                vista.cCorteFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            }
            else if (result == JOptionPane.NO_OPTION)
            {
                vista.cCorteFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            }
        }
    }

    @Override
    public void windowClosed(WindowEvent e) {

    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }
}