package controlador;


import modelo.Reportes;
import modelo.ReportesDAO;

import vista.GestionarReportes;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.List;

public class ReportesController implements ActionListener {

    ReportesDAO dao = new ReportesDAO();
    GestionarReportes vista ;
    DefaultTableModel modelo = new DefaultTableModel();
    Reportes reportes = new Reportes();

    public ReportesController(GestionarReportes window, ReportesDAO dao){
        this.vista = window;
        listar(vista.getgReportesTable());
        window.getgReportes_searchB().addActionListener(this);
    }

    public void listar(JTable tabla)
    {
        modelo = (DefaultTableModel)tabla.getModel();
        List<Reportes> lista = dao.listar();
        Object[] object = new Object[6];
        for (Reportes value : lista) {
            object[0] = value.getIdCorte();
            object[1] = value.getIdVenta();
            object[2] = value.getIdVendedor();
            object[3] = value.getFecha();
            object[4] = value.getTurno();
            object[5] = value.getTotal();
            modelo.addRow(object);
        }
    }

    private void limpiar(){
        for (int i = 0; i < vista.getgReportesTable().getRowCount(); i++){
            modelo.removeRow(i);
            i = i - 1;
        }
    }

    private void listarPorFechas(){
        if(vista.gReportes_inicioDC.getDateEditor().toString().isEmpty() || vista.gReportes_finalDC.getDateEditor().toString().isEmpty())
        {
            JOptionPane.showMessageDialog(null, "Uno o mas campos estan vacios, rellenalos para continuar");
        }
        else
        {
            System.out.println("Listar");
            int dia, mes, año;
            int dia2, mes2, año2;
            String fecha1, fecha2;

            dia = vista.gReportes_inicioDC.getCalendar().get(Calendar.DAY_OF_MONTH);
            mes = vista.gReportes_inicioDC.getCalendar().get(Calendar.MONTH) + 1;
            año = vista.gReportes_inicioDC.getCalendar().get(Calendar.YEAR);
            if(mes < 10){
                fecha1 = año + "-0" + mes + "-" + dia;
            }
            else{
                fecha1 = año + "-" + mes + "-" + dia;
            }

            dia2 = vista.gReportes_finalDC.getCalendar().get(Calendar.DAY_OF_MONTH);
            mes2 = vista.gReportes_finalDC.getCalendar().get(Calendar.MONTH) + 1;
            año2 = vista.gReportes_finalDC.getCalendar().get(Calendar.YEAR);
            if(mes < 10){
                fecha2 = año2 + "-0" + mes2 + "-" + dia2;
            }
            else{
                fecha2 = año2 + "-" + mes2 + "-" + dia2;
            }

            ReportesDAO obj = new ReportesDAO();
            List<Reportes> list = obj.fechas(fecha1, fecha2);
            modelo.setRowCount(0);
            for (Reportes reportes: list) {
                modelo.addRow(new Object[]{reportes.getIdCorte(), reportes.getIdVenta(), reportes.getIdVendedor(), reportes.getFecha(),reportes.getTurno(), reportes.getTotal()});
            }
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.getgReportes_searchB()){
            listarPorFechas();
        }
    }
}
