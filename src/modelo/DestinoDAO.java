package modelo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static modelo.Conexion.conectar;

public class DestinoDAO{
    Conexion conectar = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    public int agregar(Destinos d){
        String sql = "INSERT INTO destino(city, state, country) VALUES (?, ?, ?)";
        try{
            con = conectar.conectar();
            ps = con.prepareStatement(sql);
            ps.setString(1, d.getCity());
            ps.setString(2, d.getState());
            ps.setString(3, d.getCountry());
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

    public int actualizar(Destinos d){
        int r = 0;
        String sql = "UPDATE destino SET city=?, state=?, country=? WHERE destinationID=?";
        try {
            con = conectar.conectar();
            ps = con.prepareStatement(sql);
            ps.setString(1, d.getCity());
            ps.setString(2, d.getState());
            ps.setString(3, d.getCountry());
            ps.setInt(4, d.getDestinationID());
            r = ps.executeUpdate();
            if(r == 1){
                return 1;
            }else{
                return 0;
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
        return r;
    }

    public void delete(int id){
        String sql = "DELETE FROM destino WHERE destinationID="+id;
        try{
            con = conectar.conectar();
            ps = con.prepareStatement(sql);
            ps.executeUpdate();
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
    }

    public List<Destinos> listar()
    {
        List<Destinos> listaDestino = new ArrayList();
        String sql = "SELECT * FROM destino";
        try{
            con = conectar.conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                Destinos d = new Destinos();
                d.setDestinationID(rs.getInt(1));
                d.setCity(rs.getString(2));
                d.setState(rs.getString(3));
                d.setCountry(rs.getString(4));
                listaDestino.add(d);
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
        return listaDestino;
    }

    public ArrayList<Integer> DP()
    {
        ArrayList<Integer> listaDP = new ArrayList();
        String sql = "SELECT destinationID FROM detallepaquete";
        try{
            con = conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                listaDP.add(rs.getInt("destinationID"));
            }
        }catch (SQLException e){
            System.out.println("Error al listar los registros");
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
        return listaDP;
    }

    public ArrayList<Integer> H(){
        ArrayList<Integer> hotel = new ArrayList<>();
        String sql = "SELECT destinationID FROM hotel";
        try{
            con = conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                hotel.add(rs.getInt("destinationID"));
            }
        }catch (SQLException e){
            System.out.println("Error al listar los registros");
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
        return hotel;
    }

    public ArrayList<Integer> V(){
        ArrayList<Integer> vuelo = new ArrayList<>();
        String sql = "SELECT destinationID FROM vuelo";
        try{
            con = conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                vuelo.add(rs.getInt("destinationID"));
            }
        }catch (SQLException e){
            System.out.println("Error al listar los registros");
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
        return vuelo;
    }
}