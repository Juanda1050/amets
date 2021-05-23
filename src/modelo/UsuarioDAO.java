package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import static modelo.Conexion.conectar;

public class UsuarioDAO
{
    Conexion conectar = new Conexion();
    Connection con;
    PreparedStatement ps,ps2,ps3;
    ResultSet rs;

    public int agregar(Usuario p)
    {
        String sql = "INSERT INTO usuarios(userName, userLastName, birthDate, email, address, phone) VALUES (?,?,?,?,?,?)";
        try
        {
            con = conectar();
            ps = con.prepareStatement(sql);
            ps.setString(1, p.getNombre());
            ps.setString(2, p.getApellido());
            ps.setString(3, p.getNacimiento());
            ps.setString(4, p.getEmail());
            ps.setString(5, p.getDireccion());
            ps.setString(6, p.getTelefono());
            ps.executeUpdate();
            return 1;
        }
        catch(Exception e)
        {
            return 0;
        }
    }

    public ArrayList<String> getLastUser(){

        ArrayList<String> lastUserData = new ArrayList<>();

        String sql = "SELECT * FROM usuarios";
        try{
            con = conectar();
            ps2 = con.prepareStatement(sql);
            rs = ps2.executeQuery();
            while(rs.next()) {
                if(rs.isLast()) {
                    lastUserData.add(rs.getString("userName"));
                    lastUserData.add(rs.getString("userLastName"));
                    lastUserData.add(String.valueOf(rs.getDate("birthDate")));
                    lastUserData.add(rs.getString("email"));
                    lastUserData.add(rs.getString("address"));
                    lastUserData.add(rs.getString("phone"));
                }
            }
        }catch (Exception e){

        }
        return lastUserData;
    }

    public void deleteLastUser(){

        String sql = "DELETE FROM usuarios ORDER BY userID DESC LIMIT 1";
        try{
            con = conectar();
            ps3 = con.prepareStatement(sql);
            ps3.executeUpdate();
        }catch (Exception e){

        }
    }
}