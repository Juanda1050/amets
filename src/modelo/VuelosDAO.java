package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class VuelosDAO {
    Conexion conectar = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    public List listar(){
        List<Vuelos> vuelos = new ArrayList<>();
        String sql = "SELECT * FROM vuelo";
        try{
            con = conectar.conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                Vuelos v = new Vuelos();
                v.setFlightID(rs.getInt(1));
                v.setOrigin(rs.getString(2));
                v.setDestinationID(rs.getInt(3));
                v.setAirlineID(rs.getInt(4));
                v.setPassengers(rs.getInt(5));
                v.setDeparture(rs.getString(6));
                v.setLanding(rs.getString(7));
                vuelos.add(v);
            }
        }catch (Exception e){
        }
        return vuelos;
    }
}
