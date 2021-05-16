package modelo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AerolineaDAO {
    Conexion conectar = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    public int agregar(Aerolinea a){
        if(a.getFlyClass().equals("") || a.getFlyClass().equals("") || a.getPrice() == 0){
            return 2;
        }
        String sql = "INSERT INTO aerolinea(airlineName, flyClass, price) VALUES (?, ?, ?)";
        try{
            con = conectar.conectar();
            ps = con.prepareStatement(sql);
            ps.setString(1, a.getAirlineName());
            ps.setString(2, a.getFlyClass());
            ps.setFloat(3, a.getPrice());
            ps.executeUpdate();
        }catch(SQLException e){
            System.out.println("Error en agregar un registro");
            e.printStackTrace();
        }
        return 1;
    }

    public int actualizar(Aerolinea a){
        int r = 0;
        String sql = "UPDATE aerolinea SET airlineName = ?, flyClass = ?, price = ? WHERE airlineID = ?";
        try{
            con = conectar.conectar();
            ps = con.prepareStatement(sql);
            ps.setString(1, a.getAirlineName());
            ps.setString(2, a.getFlyClass());
            ps.setFloat(3, a.getPrice());
            ps.setInt(4, a.getAirlineID());
            r = ps.executeUpdate();
            if(r == 1){
                return 1;
            }else{
                return 0;
            }
        }catch (SQLException e){
            System.out.println("Error en editar un registro");
            e.printStackTrace();
        }
        return r;
    }

    public void delete(int id){
        String sql = "DELETE FROM aerolinea WHERE airlineID = "+id;
        try{
            con = conectar.conectar();
            ps = con.prepareStatement(sql);
            ps.executeUpdate();
        }catch (SQLException e){
            System.out.println("Error en eliminar un registro");
            e.printStackTrace();
        }
    }

    public List<Aerolinea> listar(){
        List<Aerolinea> aero = new ArrayList<>();
        String sql = "SELECT * FROM aerolinea";
        try{
            con = conectar.conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                Aerolinea a = new Aerolinea();
                a.setAirlineID(rs.getInt(1));
                a.setAirlineName(rs.getString(2));
                a.setFlyClass(rs.getString(3));
                a.setPrice(rs.getFloat(4));
                aero.add(a);
            }
        }catch (SQLException e){
            System.out.println("Error en listar Aerolinea");
            e.printStackTrace();
        }
        return aero;
    }
}
