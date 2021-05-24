package modelo;
//21:33:39	INSERT INTO cortecaja (auditID,saleID,agentID,schedule,total) values (1,2,3,"1977-12-12",21.1)	Error Code: 1452. Cannot add or update a child row: a foreign key constraint fails (`amets_travels`.`cortecaja`, CONSTRAINT `venta_corte` FOREIGN KEY (`saleID`) REFERENCES `ventas` (`saleID`))	0.062 sec
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReportesDAO {
    Conexion conectar = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    //listar los registros de la DB en la tabla
    public List listar(){
        List<Reportes> datos = new ArrayList<>();
        String sql = "SELECT * FROM cortecaja";
        try{
            con = conectar.conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()){
                Reportes reportes = new Reportes();
                reportes.setIdCorte(rs.getInt(1));
                reportes.setIdVenta(rs.getInt(2));
                reportes.setIdVendedor(rs.getInt(3));
                reportes.setFecha(rs.getString(4));
                reportes.setTurno(rs.getString(5));
                reportes.setTotal(rs.getFloat(6));
                datos.add(reportes);
            }
        }catch (Exception e){

        }finally {
            if(con != null){
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return datos;
    }
    public List<Reportes> fechas(String fecha1, String fecha2){
        List<Reportes> listaDeReportes = new ArrayList<>();
        try {
            CallableStatement cmd = conectar.conectar().prepareCall("CALL FECHAS(?,?)");
            cmd.setString(1, fecha1);
            cmd.setString(2, fecha2);
            ResultSet rs = cmd.executeQuery();
            while (rs.next()){
                Reportes reportes = new Reportes();
                reportes.setIdCorte(rs.getInt(1));
                reportes.setIdVenta(rs.getInt(2));
                reportes.setIdVendedor(rs.getInt(3));
                reportes.setFecha(rs.getString(4));
                reportes.setTurno(rs.getString(5));
                reportes.setTotal(rs.getFloat(6));
                listaDeReportes.add(reportes);
            }
        }catch (Exception e){
        }finally {
            if(con != null){
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return listaDeReportes;
    }
}
