package modelo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AerolineaDAO {
    Conexion conectar = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;

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
                a.setPrice(rs.getDouble(4));
                aero.add(a);
            }
        }catch (SQLException e){
            System.out.println("Error en listar Aerolinea");
            e.printStackTrace();
        }
        return aero;
    }
}
