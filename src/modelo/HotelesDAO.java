package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HotelesDAO {
    Conexion conectar = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    public int agregar(Hoteles h){
        String sql = "INSERT INTO hotel(destinationID, hotelName, location, rating, guests, regime, availability, entryDate, exitDate) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try{
            con = conectar.conectar();
            ps = con.prepareStatement(sql);
            ps.setInt(1, h.getDestinationID());
            ps.setString(2, h.getHotelName());
            ps.setString(3, h.getLocation());
            ps.setString(4, h.getRating());
            ps.setInt(5, h.getGuests());
            ps.setInt(6, h.getRegime());
            ps.setObject(7, h.getAvailability());
            ps.setString(8, h.getEntryDate());
            ps.setString(9, h.getExitDate());
            ps.executeUpdate();
        }catch (SQLException e){
            System.out.println("Error en agregar un registro");
            e.printStackTrace();
        }
        return 1;
    }

    public int actualizar(Hoteles h){
        String sql = "UPDATE hotel SET  destinationID = ?, hotelName = ?, location = ?, rating = ?, guests = ?, regime = ?, availability = ?, entryDate = ?, exitDate = ? WHERE hotelID = ?";
        int r = 0;
        try{
            con = conectar.conectar();
            ps = con.prepareStatement(sql);
            ps.setInt(1, h.getDestinationID());
            ps.setString(2, h.getHotelName());
            ps.setString(3, h.getLocation());
            ps.setString(4, h.getRating());
            ps.setInt(5, h.getGuests());
            ps.setInt(6, h.getRegime());
            ps.setObject(7, h.getAvailability());
            ps.setString(8, h.getEntryDate());
            ps.setString(9, h.getExitDate());
            ps.setInt(10, h.getHotelID());
            ps.executeUpdate();
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
        String sql = "DELETE FROM hotel WHERE hotelID = "+id;
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
        List<Hoteles> hoteles = new ArrayList<>();
        String sql = "SELECT * FROM hotel";
        try{
            con = conectar.conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                Hoteles h = new Hoteles();
                h.setHotelID(rs.getInt(1));
                h.setDestinationID(rs.getInt(2));
                h.setHotelName(rs.getString(3));
                h.setLocation(rs.getString(4));
                h.setRating(rs.getString(5));
                h.setGuests(rs.getInt(6));
                h.setRegime(rs.getInt(7));
                h.setAvailability(rs.getString(8));
                h.setEntryDate(rs.getString(9));
                h.setExitDate(rs.getString(10));
                hoteles.add(h);
            }
        }catch (Exception e){
        }
        return hoteles;
    }

    public ArrayList<String> listarDestinos(){
        ArrayList<String> locations = new ArrayList<>();
        String sql = "SELECT * FROM destino";
        try{
            con = conectar.conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            locations.add("Seleccione un destino");
            while(rs.next()){
                String location = rs.getInt("destinationID") + (" - ") + rs.getString("city");
                locations.add(location);
            }
        }catch (Exception e){
        }
        return locations;
    }

}
