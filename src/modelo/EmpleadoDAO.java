package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
public class EmpleadoDAO {
    Conexion conectar = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    //listar los registros de la DB en la tabla
    public List listar(){
        List<Empleado> datos = new ArrayList<>();
        String sql = "SELECT * FROM empleado";
        try{
            con = conectar.conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()){
                Empleado empleado = new Empleado();
                empleado.setIDempleado(rs.getInt(1));
                empleado.setNombreEmpleado(rs.getString(2));
                empleado.setApellidoEmpleado(rs.getString(3));
                empleado.setContraseña(rs.getString(4));
                empleado.setTurno(rs.getInt(5));
                empleado.setPuesto(rs.getInt(6));
                datos.add(empleado);
            }
        }catch (Exception e){

        }
        return datos;
    }

    public int agregar(Empleado empleado){
        if(empleado.getNombreEmpleado().equals("") || empleado.getApellidoEmpleado().equals("") || empleado.getContraseña().equals("") || empleado.getTurno() == 0 || empleado.getPuesto() == 0){
            return 2;
        }
        else{
            String sql = "INSERT INTO empleado(agentName,agentLastName,password,workShift,jobTitle) VALUES(?,?,?,?,?)";
            try{
                con = conectar.conectar();
                ps = con.prepareStatement(sql);
                ps.setString(1, empleado.getNombreEmpleado());
                ps.setString(2, empleado.getApellidoEmpleado());
                ps.setString(3, empleado.getContraseña());
                ps.setInt(4,empleado.getTurno());
                ps.setInt(5,empleado.getPuesto());
                ps.executeUpdate();
            }catch (Exception e){

            }
            return 1;
        }
    }

    public int actualizar(Empleado empleado){
        String sql = "UPDATE empleado SET agentName = ?, agentLastName = ?, password = ?, workShift = ?, jobTitle = ? WHERE ID = ?";
        try{
            con = conectar.conectar();
            ps = con.prepareStatement(sql);
            ps.setString(1, empleado.getNombreEmpleado());
            ps.setString(2, empleado.getApellidoEmpleado());
            ps.setString(3, empleado.getContraseña());
            ps.setInt(4,empleado.getTurno());
            ps.setInt(5,empleado.getPuesto());
            ps.executeUpdate();
        }catch (Exception e){

        }
        return 1;
    }
}
