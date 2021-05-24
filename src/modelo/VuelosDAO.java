package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VuelosDAO {
    Conexion conectar = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    public int agregar(Vuelos v){
        String sql = "INSERT INTO vuelo(origin, destinationID, airlineID, passengers, departureDate, landingDate) VALUES (?, ?, ?, ?, ?, ?)";
        try{
            con = conectar.conectar();
            ps = con.prepareStatement(sql);
            ps.setString(1, v.getOrigin());
            ps.setInt(2, v.getDestinationID());
            ps.setInt(3, v.getAirlineID());
            ps.setInt(4, v.getPassengers());
            ps.setString(5, v.getDeparture());
            ps.setString(6, v.getLanding());
            ps.executeUpdate();
        }catch (SQLException e){
            System.out.println("Error en agregar un registro");
            e.printStackTrace();
        }
        return 1;
    }

    public int actualizar(Vuelos v){
        String sql = "UPDATE vuelo SET origin = ?, destinationID = ?, airlineID = ?, passengers = ?, departureDate = ?, landingDate = ? WHERE flightID = ?";
        int r = 0;
        try{
            con = conectar.conectar();
            ps = con.prepareStatement(sql);
            ps.setString(1, v.getOrigin());
            ps.setInt(2, v.getDestinationID());
            ps.setInt(3, v.getAirlineID());
            ps.setInt(4, v.getPassengers());
            ps.setString(5, v.getDeparture());
            ps.setString(6, v.getLanding());
            ps.setInt(7, v.getFlightID());
            r = ps.executeUpdate();
            if(r == 1){
                return 1;
            }else{
                return 0;
            }
        }catch (SQLException e){
            System.out.println("Error en actualizar un registro");
            e.printStackTrace();
        }
        return r;
    }

    public void eliminar(int id){
        String sql = "DELETE FROM vuelo WHERE flightID = "+id;
        try{
            con = conectar.conectar();
            ps = con.prepareStatement(sql);
            ps.executeUpdate();
        }catch (SQLException e){
            System.out.println("Error en eliminar un registro");
            e.printStackTrace();
        }
    }

    public List listar(){
        List<Vuelos> vuelos = new ArrayList<>();
        String sql = "SELECT * FROM vuelo";
        PreparedStatement ps2, ps3;
        ResultSet rs2, rs3;
        try{
            con = conectar.conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                Vuelos v = new Vuelos();
                v.setFlightID(rs.getInt(1));
                v.setOrigin(rs.getString(2));
                //Seleccionar el nombre de la ciudad de la tabla DESTINOS
                ps2 = con.prepareStatement("SELECT city FROM destino WHERE  destinationID = ?");
                ps2.setInt(1, rs.getInt(3));
                rs2 = ps2.executeQuery();
                while(rs2.next()){
                    String ciudad = rs2.getString("city");
                    v.setDestinationName(ciudad);
                }
                //Seleccionar el nombre de la aerolinea de la tabla AEROLINEA
                ps3 = con.prepareStatement("SELECT airlineName FROM aerolinea WHERE airlineID = ?");
                ps3.setInt(1, rs.getInt(4));
                rs3 = ps3.executeQuery();
                while(rs3.next()){
                    String aero = rs3.getString("airlineName");
                    v.setAirlineName(aero);
                }
                v.setPassengers(rs.getInt(5));
                v.setDeparture(rs.getString(6));
                v.setLanding(rs.getString(7));
                vuelos.add(v);
            }
        }catch (Exception e){
        }
        return vuelos;
    }

    public ArrayList<String> listarDestinos(){
        ArrayList<String> destinos = new ArrayList<>();
        String sql = "SELECT * FROM destino";
        try{
            con = conectar.conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            destinos.add("Seleccione un destino");
            while(rs.next()){
                String ciudad = rs.getInt("destinationID") + (" - ") + rs.getString("city");
                destinos.add(ciudad);
            }
        }catch (Exception e){
        }
        return destinos;
    }

    public ArrayList<String> listarAerolinea(){
        ArrayList<String> aero = new ArrayList<>();
        String sql = "SELECT * FROM aerolinea";
        try{
            con = conectar.conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            aero.add("Seleccione una aerolinea");
            while(rs.next()){
                String aerolinea = rs.getString("airlineName") + (" - ") + rs.getString("flyClass");
                aero.add(aerolinea);
            }
        }catch (Exception e){
        }
        return aero;
    }
}
