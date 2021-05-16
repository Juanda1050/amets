package modelo;

import controlador.PaquetesController;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import static modelo.Conexion.conectar;

public class PaquetesDAO
{
    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    public ArrayList<Paquetes> listar()
    {
        ArrayList<Paquetes> listaPaquetes = new ArrayList();
        Destinos destinos;
        String sql = "SELECT * FROM paquetes";
        try{
            con = conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                Paquetes p = new Paquetes();
                p.setID(rs.getInt(1));
                p.setName(rs.getString(2));
                p.setDescription(rs.getString(3));
                p.setPassengers(rs.getInt(4));
                p.setPrice(rs.getInt(5));
                listaPaquetes.add(p);
            }
        }catch (Exception e){

        }
        return listaPaquetes;
    }

    public int agregar(Paquetes p)
    {
        String sql = "INSERT INTO paquetes(packName, packDescription, passengers, price) VALUES (?, ?, ?, ?)";
        try{
            con = conectar();
            ps = con.prepareStatement(sql);
            ps.setString(1, p.getName());
            ps.setString(2, p.getDescription());
            ps.setInt(3, p.getPassengers());
            ps.setInt(4, p.getPrice());
            ps.executeUpdate();
        }catch (SQLException e){
            System.out.println("Error");
            e.printStackTrace();
        }

        return 1;
    }
}
