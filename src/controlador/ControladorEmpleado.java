package controlador;


import modelo.Empleado;
import modelo.EmpleadoDAO;
import cuarta.GestionarEmpleados;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.List;

public class ControladorEmpleado implements ActionListener {
    EmpleadoDAO dao = new EmpleadoDAO();
    Empleado empleado = new Empleado();
    GestionarEmpleados gestionarEmpleados;
    DefaultTableModel modelo = new DefaultTableModel();



    public ControladorEmpleado(GestionarEmpleados vistaEmpleados){
        this.gestionarEmpleados = vistaEmpleados;
        listar(vistaEmpleados.gEmpleadoTable);
        this.gestionarEmpleados.gEmpleado_addB.addActionListener(this);
        this.gestionarEmpleados.gEmpleado_editB.addActionListener(this);
    }

    public void listar(JTable tabla){
        modelo = (DefaultTableModel)tabla.getModel();
        List<Empleado> lista = dao.listar();
        Object[] object = new Object[6];
        for (Empleado value : lista) {
            object[0] = value.getIDempleado();
            object[1] = value.getNombreEmpleado();
            object[2] = value.getApellidoEmpleado();
            object[3] = value.getContraseña();
            object[4] = value.getTurno();
            object[5] = value.getPuesto();
            modelo.addRow(object);
        }
        gestionarEmpleados.gEmpleadoTable.setModel(modelo);
    }

    private void agregar() {
        añadirYactualizar();
        int r =dao.agregar(empleado);
        if (r == 1){
            JOptionPane.showMessageDialog(null, "Empleado agregado con Exito");
        }
        else {
            JOptionPane.showMessageDialog(null,"Error, falta algún campo por llenar");
        }
    }

    private void actualizar(){
        añadirYactualizar();
        int r =dao.actualizar(empleado);
        if (r == 1){
            JOptionPane.showMessageDialog(null, "Empleado agregado con Exito");
        }
        else {
            JOptionPane.showMessageDialog(null,"Error, falta algún campo por llenar");
        }
    }

    private void añadirYactualizar() {
        String nombre = gestionarEmpleados.gEmpleado_nombreTF.getText();
        String apellido = gestionarEmpleados.gEmpleado_apellidoTF.getText();
        String contraseña = gestionarEmpleados.gEmpleado_contraTF.getText();
        int turno = gestionarEmpleados.gEmpleado_turnoCB.getSelectedIndex();
        int puesto = gestionarEmpleados.gEmpleado_puestoCB.getSelectedIndex();

        empleado.setNombreEmpleado(nombre);
        empleado.setApellidoEmpleado(apellido);
        empleado.setContraseña(contraseña);
        empleado.setTurno(turno);
        empleado.setPuesto(puesto);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == gestionarEmpleados.gEmpleado_addB){
            agregar();
        }

        if (e.getSource() == gestionarEmpleados.gEmpleado_saveB){

        }

        if (e.getSource() == gestionarEmpleados.gEmpleado_editB){
            int fila = gestionarEmpleados.gEmpleadoTable.getSelectedRow();
            if (fila == -1){
                JOptionPane.showMessageDialog(null, "Debe seleccionar una fila");
            }
            else{

                String nombre = (String) gestionarEmpleados.gEmpleadoTable.getValueAt(fila,1);
                String apellido = (String) gestionarEmpleados.gEmpleadoTable.getValueAt(fila,2);
                String contraseña = (String) gestionarEmpleados.gEmpleadoTable.getValueAt(fila,3);
                int turno = (int) gestionarEmpleados.gEmpleadoTable.getValueAt(fila,4);
                int puesto = (int) gestionarEmpleados.gEmpleadoTable.getValueAt(fila,5);
                gestionarEmpleados.gEmpleado_nombreTF.setText(nombre);
                gestionarEmpleados.gEmpleado_apellidoTF.setText(apellido);
                gestionarEmpleados.gEmpleado_contraTF.setText(contraseña);

            }
        }

    }
}
