package Modelo;

import Controlador.Conexion;

import java.sql.*;
import java.util.ArrayList;

public class DestinoDAO extends Conexion{
    Connection con;
    PreparedStatement ps;
    ResultSet rs;

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
