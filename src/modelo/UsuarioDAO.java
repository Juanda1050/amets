package modelo;

import jdk.swing.interop.SwingInterOpUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static modelo.Conexion.conectar;

public class UsuarioDAO
{
    Connection con;
    PreparedStatement ps,ps2, ps3;
    ResultSet rs;
    int userID;

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

    public int getUserID(Usuario p){
        try
        {
            con = conectar();
            ps2 = con.prepareStatement("SELECT * FROM usuarios WHERE userName = ? AND userLastName = ? AND birthDate = ? AND email = ? AND address = ? AND phone = ?");
            ps2.setString(1, p.getNombre());
            ps2.setString(2, p.getApellido());
            ps2.setString(3, p.getNacimiento());
            ps2.setString(4, p.getEmail());
            ps2.setString(5, p.getDireccion());
            ps2.setString(6, p.getTelefono());
            rs = ps2.executeQuery();
            while(rs.next()){
                userID = rs.getInt("userID");
            }
        }
        catch(Exception e)
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
        return userID;
    }

    public void deleteLastUser(){

        String sql = "DELETE FROM usuarios ORDER BY userID DESC LIMIT 1";
        try{
            con = conectar();
            ps3 = con.prepareStatement(sql);
            ps3.executeUpdate();
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
}
