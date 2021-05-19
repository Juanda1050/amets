package modelo;

import java.net.Inet4Address;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DHotelesDAO {
    Conexion conectar = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    public int agregar(DHoteles dh){
        String sql = "INSERT INTO detallehotel(hotelID, roomID, price) VALUES (?, ?, ?)";
        try{
            con = conectar.conectar();
            ps = con.prepareStatement(sql);
            ps.setInt(1, dh.getHotelID());
            ps.setInt(2, dh.getRoomID());
            ps.setFloat(3, dh.getPrice());
            ps.executeUpdate();
        }catch (SQLException e){
            System.out.println("Error en agregar un registro");
            e.printStackTrace();
        }
        return 1;
    }

    public int actualizar(DHoteles dh){
        String sql = "UPDATE detallehotel SET  hotelID = ?, roomID = ?, price = ? ";
        int r = 0;
        try{
            con = conectar.conectar();
            ps = con.prepareStatement(sql);
            ps.setInt(1, dh.getHotelID());
            ps.setInt(2, dh.getRoomID());
            ps.setFloat(3, dh.getPrice());
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
        String sql = "DELETE FROM detallehotel WHERE hotelID = "+id;
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
        List<DHoteles> dHoteles = new ArrayList<>();
        String sql = "SELECT * FROM detallehotel";
        PreparedStatement ps2, ps3;
        ResultSet rs2, rs3;
        try{
            con = conectar.conectar();
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
        }
        return dHoteles;
    }
    public ArrayList<String> listarhotel(){
        ArrayList<String> hotel = new ArrayList<>();
        String sql = "SELECT * FROM hotel";
        try{
            con = conectar.conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            hotel.add("Seleccione un hotel");
            while(rs.next()){
                String hotelid = rs.getString("hotelName");
                hotel.add(hotelid);
            }
        }catch (Exception e){
        }
        return hotel;
    }
    public ArrayList<String> listarRoom(){
        ArrayList<String> room = new ArrayList<>();
        String sql = "SELECT * FROM categoriahotel";
        try{
            con = conectar.conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            room.add("Seleccione una habitacion");
            while(rs.next()){
                String roomid = rs.getInt("roomID") + (" - ") + rs.getString("roomName");
                room.add(roomid);
            }
        }catch (Exception e){
        }
        return room;
    }
}

