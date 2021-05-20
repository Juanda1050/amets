package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static modelo.Conexion.conectar;

public class DetallePaqueteDAO
{
    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    public int agregar(DP dp)
    {
        String sql = "INSERT INTO detallepaquete(packID, flightID, hotelID, destinationID) VALUES (?, ?, ?, ?)";
        try{
            con = conectar();
            ps = con.prepareStatement(sql);
            ps.setInt(1, dp.getIdpaquete());
            ps.setInt(2, dp.getIdVuelo());
            ps.setInt(3, dp.getIdhotel());
            ps.setInt(4, dp.getIddestino());
            ps.executeUpdate();
        }catch (SQLException e){
            System.out.println("Error");
            e.printStackTrace();
        }

        return 1;
    }

    public ArrayList<Paquetes> listarPaquetes()
    {
        ArrayList<Paquetes> listaPaquetes = new ArrayList();
        String sql = "SELECT * FROM paquetes";
        try{
            con = conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                Paquetes p = new Paquetes();
                p.setID(rs.getInt("packID"));
                p.setName(rs.getString("packName"));
                p.setDescription(rs.getString("packDescription"));
                p.setPassengers(rs.getInt("passengers"));
                p.setPrice(rs.getFloat("price"));
                listaPaquetes.add(p);
            }
        }catch (Exception e){

        }
        return listaPaquetes;
    }

    public ArrayList<Destinos> listarDestinos()
    {
        ArrayList<Destinos> listaDestinos = new ArrayList();
        String sql = "SELECT * FROM destino";
        try{
            con = conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                Destinos d = new Destinos();
                d.setDestinationID(rs.getInt("destinationID"));
                d.setCity(rs.getString("city"));
                d.setState(rs.getString("state"));
                d.setCountry(rs.getString("country"));
                listaDestinos.add(d);
            }
        }catch (Exception e){

        }
        return listaDestinos;
    }

    public int destinoID(String city)
    {
        int id=0;
        String sql = "SELECT destinationID FROM destino WHERE city= ?";
        try{
            con = conectar();
            ps = con.prepareStatement(sql);
            ps.setString(1, city);
            rs = ps.executeQuery();
            while(rs.next()){
                id = rs.getInt("destinationID");
            }
            return id;
        }catch (Exception e){
            return 0;
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
        }
    }

    public ArrayList<Integer> vuelosId(String city)
    {
        int dID = destinoID(city);
        System.out.println(dID);
        ArrayList<Integer> listaVuelosId = new ArrayList();
        String sql = "SELECT flightID FROM vuelo WHERE destinationID= ?";
        try{
            con = conectar();
            ps = con.prepareStatement(sql);
            ps.setInt(1, dID);
            rs = ps.executeQuery();
            while(rs.next()){
                listaVuelosId.add(rs.getInt("flightID"));
            }

        }catch (Exception e){

        }
        return listaVuelosId;
    }

    public ArrayList<String> nombresHoteles(String city)
    {
        int dID = destinoID(city);
        ArrayList<String> listaHoteles = new ArrayList<>();
        String sql = "SELECT hotelName FROM hotel WHERE destinationID= ?";
        try
        {
            con = conectar();
            ps = con.prepareStatement(sql);
            ps.setInt(1, dID);
            rs = ps.executeQuery();
            while(rs.next())
            {
                listaHoteles.add(rs.getString("hotelName"));
            }
        }
        catch (Exception e)
        {

        }
        return listaHoteles;
    }




}
