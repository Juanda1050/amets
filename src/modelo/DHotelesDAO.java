package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static modelo.Conexion.conectar;

public class DHotelesDAO {
    Conexion conectar = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    public int agregar(DHoteles dh){
        String sql = "INSERT INTO detallehotel(hotelID, roomID, price) VALUES (?, ?, ?)";
        try{
            con = conectar();
            ps = con.prepareStatement(sql);
            ps.setInt(1, dh.getHotelID());
            ps.setInt(2, dh.getRoomID());
            ps.setFloat(3, dh.getPrice());
            ps.executeUpdate();
        }catch (SQLException e){
            System.out.println("Error en agregar un registro");
            e.printStackTrace();
        }finally {
            if(con != null){
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return 1;
    }

    public int actualizar(DHoteles dh){
        int r = 0;
        String sql = "UPDATE detallehotel SET roomID = ?, price = ? WHERE hotelID = ?";
        try{
            con = conectar();
            ps = con.prepareStatement(sql);
            ps.setInt(1, dh.getRoomID());
            ps.setFloat(2, dh.getPrice());
            ps.setInt(3, dh.getHotelID());
            r = ps.executeUpdate();
            if(r == 1){
                return 1;
            }else{
                return 0;
            }
        }catch (SQLException e){
            System.out.println("Error en actualizar un registro");
            e.printStackTrace();
        }finally {
            if(con != null){
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return r;
    }

    public void eliminar(int id){
        String sql = "DELETE FROM detallehotel WHERE hotelID = ?";
        try{
            con = conectar();
            ps = con.prepareStatement(sql);
            ps.setInt(1,id);
            ps.executeUpdate();
        }catch (SQLException e){
            System.out.println("Error en eliminar un registro");
            e.printStackTrace();
        }finally {
            if(con != null){
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public int hotelID(String hotel)
    {
        int id=0;
        String sql = "SELECT hotelID FROM hotel WHERE hotelName= ?";
        try
        {
            con = conectar();
            ps = con.prepareStatement(sql);
            ps.setString(1, hotel);
            rs = ps.executeQuery();
            while(rs.next()){
                id = rs.getInt("hotelID");
            }
            return id;
        }
        catch (Exception e)
        {
            return 0;
        }finally {
            if(con != null){
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public int roomID(String room)
    {
        int id=0;
        String sql = "SELECT roomID FROM categoriahotel WHERE roomName= ?";
        try
        {
            con = conectar();
            ps = con.prepareStatement(sql);
            ps.setString(1, room);
            rs = ps.executeQuery();
            while(rs.next()){
                id = rs.getInt("roomID");
            }
            return id;
        }
        catch (Exception e)
        {
            return 0;
        }finally {
            if(con != null){
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public ArrayList<Hoteles> listarhoteles(){
        ArrayList<Hoteles> hoteles = new ArrayList<>();
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
                h.setAvailability(rs.getInt(8));
                h.setEntryDate(rs.getString(9));
                h.setExitDate(rs.getString(10));
                hoteles.add(h);
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
        return hoteles;
    }

    public List listar(){
        List<DHoteles> dHoteles = new ArrayList<>();
        String sql = "SELECT * FROM detallehotel";
        PreparedStatement ps2, ps3;
        ResultSet rs2, rs3;
        try{
            con = conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()) {
                DHoteles dh = new DHoteles();
                ps2 = con.prepareStatement("SELECT hotelName FROM hotel WHERE hotelID = ?");
                ps2.setInt(1, rs.getInt(1));
                rs2 = ps2.executeQuery();
                while (rs2.next()){
                    String hotel = rs2.getString("hotelName");
                    dh.setHotelName(hotel);
                }
                ps3 = con.prepareStatement("SELECT roomName FROM categoriahotel WHERE roomID = ?");
                ps3.setInt(1, rs.getInt(2));
                rs3 = ps3.executeQuery();
                while (rs3.next()){
                    String room = rs3.getString("roomName");
                    dh.setRoomName(room);
                }
                dh.setPrice(rs.getFloat(3));
                dHoteles.add(dh);
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
        return dHoteles;
    }
    public ArrayList<String> listarhotel(){
        ArrayList<String> hotel = new ArrayList<>();
        String sql = "SELECT * FROM hotel";
        try{
            con = conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                String hotelid = rs.getString("hotelName");
                hotel.add(hotelid);
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
        return hotel;
    }
    public ArrayList<String> listarRoom(){
        ArrayList<String> room = new ArrayList<>();
        String sql = "SELECT * FROM categoriahotel";
        try{
            con = conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                String roomid = rs.getString("roomName");
                room.add(roomid);
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
        return room;
    }
}
