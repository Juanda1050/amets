package Modelo;

import Controlador.Conexion;

import java.sql.*;
import java.util.ArrayList;

public class DestinoDAO extends Conexion{
    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    public int agregar(Destinos d){
        String sql = "INSERT INTO destino(city, state, country) VALUES (?, ?, ?)";
        try{
            con = conectar();
            ps = con.prepareStatement(sql);
            ps.setString(1, d.getCity());
            ps.setString(2, d.getState());
            ps.setString(3, d.getCountry());
            ps.executeUpdate();
        }catch (Exception e){
        }
        return 1;
    }

    public int actualizar(Destinos d){
        int r = 0;
        String sql = "UPDATE destino SET city=?, state=?, country=? WHERE destinationID=?";
        try {
            con = conectar();
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
        }
        return r;
    }

    public void delete(int id){
        String sql = "DELETE FROM destino WHERE destinationID="+id;
        try{
            con = conectar();
            ps = con.prepareStatement(sql);
            ps.executeUpdate();
        }catch (Exception e){
        }
    }

    public ArrayList<Destinos> listar()
    {
        ArrayList<Destinos> listaDestino = new ArrayList();
        Destinos destinos;
        String sql = "SELECT * FROM destino";
        try{
            con = conectar();
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

        }
        return listaDestino;
    }
}
