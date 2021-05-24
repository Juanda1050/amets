package controlador;


import vista.GestionarEmpleados;
import modelo.Empleado;
import modelo.EmpleadoDAO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class EmpleadoController implements ActionListener {
    EmpleadoDAO dao;
    Empleado empleado = new Empleado();
    GestionarEmpleados vista;
    DefaultTableModel modelo = new DefaultTableModel();
    Boolean key;

    public EmpleadoController(GestionarEmpleados v, EmpleadoDAO dao)
    {
        int lista = dao.listar().size();
        if(lista>0)
        {
            this.vista = v;
            this.dao = dao;
            areTextFieldEditable(false);
            vista.gEmpleado_saveB.setEnabled(false);
            vista.gEmpleado_puestoCB.setEnabled(false);
            vista.gEmpleado_turnoCB.setEnabled(false);
            vista.gEmpleado_addB.addActionListener(this);
            vista.gEmpleado_saveB.addActionListener(this);
            vista.gEmpleado_editB.addActionListener(this);
            vista.gEmpleado_deleteB.addActionListener(this);
            listEmployee(vista.gEmpleadoTable);
        }
        else
        {
            this.vista = v;
            this.dao = dao;
            areTextFieldEditable(false);
            vista.gEmpleado_saveB.setEnabled(false);
            vista.gEmpleado_addB.addActionListener(this);
            vista.gEmpleado_saveB.addActionListener(this);
            vista.gEmpleado_editB.addActionListener(this);
            vista.gEmpleado_deleteB.addActionListener(this);
            listEmployee(vista.gEmpleadoTable);
            boolean[] arr = {true, false, false, false};
            areButtonsEnabled(arr);
        }
    }

    public void actionPerformed(ActionEvent e)
    {
        //Boton Nuevo
        if(e.getSource()==vista.gEmpleado_addB)
        {
            key = true;
            areTextFieldEditable(true);
            boolean[] arr = {false, true, true, false};
            areButtonsEnabled(arr);
            vista.gEmpleado_turnoCB.setEnabled(true);
            vista.gEmpleado_puestoCB.setEnabled(true);
        }
        //Boton saveEmployee
        if(e.getSource()==vista.gEmpleado_saveB)
        {
            boolean[] arr = {true, false, true, true};
            areButtonsEnabled(arr);
            saveEmployee();

        }
        //Boton Editar
        if(e.getSource()==vista.gEmpleado_editB)
        {
            key = false;
            Editar();
        }
        //Boton deleteEmployee
        if(e.getSource()==vista.gEmpleado_deleteB)
        {
            deleteEmployee();
            cleanEmployee();
            cleanForm();
            listEmployee(vista.gEmpleadoTable);
        }
    }

    public void addEmployee()
    {
        if(vista.gEmpleado_nombreTF.getText().isEmpty() || vista.gEmpleado_apellidoTF.getText().isEmpty() || vista.gEmpleado_contraTF.getText().isEmpty() || vista.gEmpleado_turnoCB.getSelectedIndex() == 0 || vista.gEmpleado_puestoCB.getSelectedIndex() == 0)
        {
            JOptionPane.showMessageDialog(null, "Uno o mas campos estan vacios, rellenalos para continuar");
            boolean[] arr = {false, true, false, false};
            areButtonsEnabled(arr);
        }
        else
        {
            String name = vista.gEmpleado_nombreTF.getText();
            String apellido = vista.gEmpleado_apellidoTF.getText();
            String contraseña = vista.gEmpleado_contraTF.getText();
            int turno = vista.gEmpleado_turnoCB.getSelectedIndex();
            int puesto = vista.gEmpleado_puestoCB.getSelectedIndex();
            empleado.setNombreEmpleado(name);
            empleado.setApellidoEmpleado(apellido);
            empleado.setContraseña(contraseña);
            empleado.setTurno(turno);
            empleado.setPuesto(puesto);
            int r = dao.agregar(empleado);

            if (r == 1) {
                JOptionPane.showMessageDialog(null, "Empleado agregado exitosamente");
            } else {
                JOptionPane.showMessageDialog(null, "Empleado fallido");
            }
            areTextFieldEditable(false);
            cleanForm();
            boolean[] arr = {true, false, true, true};
            areButtonsEnabled(arr);
            vista.gEmpleado_turnoCB.setEnabled(false);
            vista.gEmpleado_puestoCB.setEnabled(false);
        }
    }


    public void updateEmployee()
    {
        if(vista.gEmpleado_nombreTF.getText().isEmpty() || vista.gEmpleado_apellidoTF.getText().isEmpty() || vista.gEmpleado_contraTF.getText().isEmpty() || vista.gEmpleado_turnoCB.getSelectedIndex() == 0 || vista.gEmpleado_puestoCB.getSelectedIndex() == 0)
        {
            JOptionPane.showMessageDialog(null, "Uno o mas campos estan vacios, rellenalos para continuar");
            boolean[] arr = {false, true, false, false};
            areButtonsEnabled(arr);
        }
        else
        {
            int id = Integer.parseInt(vista.gEmpleado_idTF.getText());
            String name = vista.gEmpleado_nombreTF.getText();
            String apellido = vista.gEmpleado_apellidoTF.getText();
            String contraseña = vista.gEmpleado_contraTF.getText();
            int turno = vista.gEmpleado_turnoCB.getSelectedIndex();
            int puesto = vista.gEmpleado_puestoCB.getSelectedIndex();
            empleado.setIDempleado(id);
            empleado.setNombreEmpleado(name);
            empleado.setApellidoEmpleado(apellido);
            empleado.setContraseña(contraseña);;
            empleado.setTurno(turno);
            empleado.setPuesto(puesto);

            int r = dao.actualizar(empleado);

            if (r == 1) {
                JOptionPane.showMessageDialog(null, "Registro actualizado exitosamente");
            } else {
                JOptionPane.showMessageDialog(null, "Registro fallido");
            }
            areTextFieldEditable(false);
            cleanEmployee();
            cleanForm();
            listEmployee(vista.gEmpleadoTable);
        }
    }


    public void deleteEmployee(){
        int row = vista.gEmpleadoTable.getSelectedRow();
        if(row==-1)
        {
            JOptionPane.showMessageDialog(null, "Debe selecionar un registro");
        }
        else
        {
            int lista = dao.listar().size();
            if(lista>1)
            {
                int id = Integer.parseInt((String) vista.gEmpleadoTable.getValueAt(row, 0).toString());
                dao.eliminar(id);
                JOptionPane.showMessageDialog(null, "Registro eliminado");
            }
            else
            {
                boolean[] arr = {true, false, false, false};
                areButtonsEnabled(arr);
                int id = Integer.parseInt((String) vista.gEmpleadoTable.getValueAt(row, 0).toString());
                dao.eliminar(id);
                JOptionPane.showMessageDialog(null, "Registro eliminado");
            }

        }
    }

    public void listEmployee(JTable tabla)
    {
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
    }
    
    public void Editar()
    {
        int row = vista.gEmpleadoTable.getSelectedRow();
        if(row==-1)
        {
            JOptionPane.showMessageDialog(null, "Seleccione un empleado");
        }
        else
        {
            areTextFieldEditable(true);
            boolean[] arr = {false, true, true, false};
            areButtonsEnabled(arr);
            int id = Integer.parseInt(vista.gEmpleadoTable.getValueAt(row, 0).toString());
            String nombre = (String) vista.gEmpleadoTable.getValueAt(row, 1);
            String apellido = (String) vista.gEmpleadoTable.getValueAt(row, 2);
            String contraseña = (String) vista.gEmpleadoTable.getValueAt(row, 3);
            int turno = Integer.parseInt(vista.gEmpleadoTable.getValueAt(row, 4).toString());
            int puesto = Integer.parseInt(vista.gEmpleadoTable.getValueAt(row, 5).toString());
            vista.gEmpleado_idTF.setText(""+id);
            vista.gEmpleado_nombreTF.setText(nombre);
            vista.gEmpleado_apellidoTF.setText(apellido);
            vista.gEmpleado_contraTF.setText(contraseña);
            vista.gEmpleado_turnoCB.setSelectedIndex(turno);
            vista.gEmpleado_puestoCB.setSelectedIndex(puesto);
        }
    }
    
    //saveEmployee
    public void saveEmployee()
    {
        if(key)
        {
            int lista = dao.listar().size();
            if (lista>0)
            {
                addEmployee();
                cleanEmployee();
                listEmployee(vista.gEmpleadoTable);
            }
            else
            {
                addEmployee();
                listEmployee(vista.gEmpleadoTable);
            }
        }
        else
        {
            updateEmployee();
        }

    }
    
    //cleanEmployee la Tabla
    private void cleanEmployee(){
        for (int i = 0; i < vista.gEmpleadoTable.getRowCount(); i++){
            modelo.removeRow(i);
            i = i - 1;
        }
    }

    //Hacer editable o no editable los TextField
    private void areTextFieldEditable(boolean flag)
    {
        vista.gEmpleado_nombreTF.setEditable(flag);
        vista.gEmpleado_apellidoTF.setEditable(flag);
        vista.gEmpleado_contraTF.setEditable(flag);
        vista.gEmpleado_turnoCB.setEnabled(flag);
        vista.gEmpleado_puestoCB.setEnabled(flag);
    }

    //cleanEmployee los TextField
    private void cleanForm(){
        vista.gEmpleado_idTF.setText("");
        vista.gEmpleado_nombreTF.setText("");
        vista.gEmpleado_apellidoTF.setText("");
        vista.gEmpleado_contraTF.setText("");
        vista.gEmpleado_turnoCB.setSelectedIndex(0);
        vista.gEmpleado_puestoCB.setSelectedIndex(0);
    }

    //Activar o desactivar botones
    private void areButtonsEnabled(boolean[] a)
    {
        vista.gEmpleado_addB.setEnabled(a[0]);
        vista.gEmpleado_saveB.setEnabled(a[1]);
        vista.gEmpleado_editB.setEnabled(a[2]);
        vista.gEmpleado_deleteB.setEnabled(a[3]);
    }
}
