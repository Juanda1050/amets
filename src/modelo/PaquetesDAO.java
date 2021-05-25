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

        }finally {
            if(con != null){
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
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

    public int actualizar(Paquetes p)
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

    public ArrayList<Integer> listarP(){
        ArrayList<Integer> promo = new ArrayList<>();
        String sql = "SELECT packID FROM promociones";
        try{
            con = conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                promo.add(rs.getInt("packID"));
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
        return promo;
    }
}
