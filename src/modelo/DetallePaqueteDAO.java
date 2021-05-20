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

    public ArrayList<DP> sizeDP()
    {
        ArrayList<DP> listaDP = new ArrayList();
        String sql = "SELECT * FROM detallepaquete";
        try{
            con = conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next())
            {
                DP dp = new DP();
                dp.setIdpaquete(rs.getInt(1));
                dp.setIdVuelo(rs.getInt(2));
                dp.setIdhotel(rs.getInt(3));
                dp.setIddestino(rs.getInt(4));
                listaDP.add(dp);
            }
        }catch (Exception e){

        }
        return listaDP;
    }

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

    public void eliminar(int packID, int flightID, int hotelID, int destID)
    {
        String sql = "DELETE FROM detallepaquete WHERE packID = ? AND flightID= ? AND hotelID= ? AND destinationID= ?; ";
        try
        {
            con = conectar();
            ps = con.prepareStatement(sql);
            ps.setInt(1, packID);
            ps.setInt(2, flightID);
            ps.setInt(3, hotelID);
            ps.setInt(4, destID);
            ps.executeUpdate();
        }
        catch (Exception e)
        {

        }
    }

    public int Actualizar(DP dp)
    {
        String sql2 = "UPDATE detallepaquete SET flightID=?, hotelID=?, destinationID=? WHERE packID = ?";
        int r=0;
        try
        {
            con = conectar();
            ps = con.prepareStatement(sql2);
            ps.setInt(1, dp.getIdVuelo());
            ps.setInt(2, dp.getIdhotel());
            ps.setInt(3, dp.getIddestino());
            ps.setInt(4, dp.getIdpaquete());
            r = ps.executeUpdate();
            if(r==1)
            {
                return 1;
            }
            else
            {
                return 0;
            }
        }
        catch (Exception e)
        {
        }
        return r;
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

    public int paqueteID(String name)
    {
        int id=0;
        String sql = "SELECT packID FROM paquetes WHERE packName= ?";
        try{
            con = conectar();
            ps = con.prepareStatement(sql);
            ps.setString(1, name);
            rs = ps.executeQuery();
            while(rs.next()){
                id = rs.getInt("packID");
            }
            return id;
        }catch (Exception e){
            return 0;
        }
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

    //Obtengo el nombre del hotel mediante su id
    public String packName(int idPack)
    {
        String name=null;
        ArrayList<String> names = new ArrayList<>();
        String sql = "SELECT packName FROM paquetes WHERE packID=?";
        try{
            con = conectar();
            ps = con.prepareStatement(sql);
            ps.setInt(1, idPack);
            rs = ps.executeQuery();
            while(rs.next()){
                name = rs.getString("packName");
            }
            return name;
        }
        catch (Exception e)
        {
            return "";
        }
    }

    //Obtengo el nombre del hotel mediante su id
    public String hotelName(int idHotel)
    {
        String name=null;
        ArrayList<String> names = new ArrayList<>();
        String sql = "SELECT hotelName FROM hotel WHERE hotelID=?";
        try{
            con = conectar();
            ps = con.prepareStatement(sql);
            ps.setInt(1, idHotel);
            rs = ps.executeQuery();
            while(rs.next()){
                name = rs.getString("hotelName");
            }
            return name;
        }
        catch (Exception e)
        {
            return "";
        }
    }

    //Obtengo el nombre del destino mediante su id
    public String destName(int idDestino)
    {
        String name=null;
        ArrayList<String> names = new ArrayList<>();
        String sql = "SELECT city FROM destino WHERE destinationID=?";
        try{
            con = conectar();
            ps = con.prepareStatement(sql);
            ps.setInt(1, idDestino);
            rs = ps.executeQuery();
            while(rs.next()){
                name = rs.getString("city");
            }
            return name;
        }
        catch (Exception e)
        {
            return "";
        }
    }





}
