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
                p.setPrice(rs.getFloat(5));
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
            ps.setFloat(4, p.getPrice());
            ps.executeUpdate();
        }catch (SQLException e){
            System.out.println("Error");
            e.printStackTrace();
        }

        return 1;
    }

    public int Actualizar(Paquetes p)
    {
        String sql = "UPDATE paquetes SET packName=?, packDescription=?, passengers=?, price=? WHERE packID=?";
        int r=0;
        try
        {
            con = conectar();
            ps = con.prepareStatement(sql);
            ps.setString(1, p.getName());
            ps.setString(2, p.getDescription());
            ps.setInt(3, p.getPassengers());
            ps.setFloat(4, p.getPrice());
            ps.setInt(5, p.getID());
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

    public void eliminar(int id)
    {
        String sql = "DELETE FROM paquetes WHERE packID="+id;
        try
        {
            con = conectar();
            ps = con.prepareStatement(sql);
            ps.executeUpdate();
        }
        catch (Exception e)
        {

        }
    }
}
