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

    public CorteController(VistaCC v, VentaDAO dao, int agentID){
        this.vista = v;
        this.vDao = dao;
        this.agentID = agentID;
        listar(this.vista.table);
        vista.ccCorteButton.addActionListener(this);
        vista.ccVolverBtn.addActionListener(this);
        vista.frmAmetsTravels.addWindowListener(this);
    }

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

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == vista.ccVolverBtn) {
            Retorno rtn = new Retorno();
            vista.frmAmetsTravels.setVisible(rtn.runReturn(agentID));
        }

        if(e.getSource() == vista.ccCorteButton){
            /* Se realiza el corte */
            CorteDAO corteDAO = new CorteDAO();
            int turno = 0;
            if(vista.ccHorarioTF.getText().equals("Matutino")){
                turno = 1;
            }else if(vista.ccHorarioTF.getText().equals("Vespertino")){
                turno = 2;
            }

            if(corteDAO.saveCorte(lastSaleID, agentID, turno, vista.ccTotalTF.getText())){
                DefaultTableModel model = (DefaultTableModel) vista.table.getModel();
                model.setRowCount(0);
                total = 0;
                vista.ccTotalTF.setText(String.valueOf(total));
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

        if (e.getSource() == vista.frmAmetsTravels) {
            int result = JOptionPane.showConfirmDialog(vista.frmAmetsTravels, "¿Desea cerrar el programa?", "Salir del programa", JOptionPane.YES_NO_OPTION);
            if (result == JOptionPane.YES_OPTION)
            {
                vista.frmAmetsTravels.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            }
            else if (result == JOptionPane.NO_OPTION)
            {
                vista.frmAmetsTravels.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
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