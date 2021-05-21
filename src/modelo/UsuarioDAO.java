package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import static modelo.Conexion.conectar;

public class UsuarioDAO
{
    Conexion conectar = new Conexion();
    Connection con;
    PreparedStatement ps;
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
            ps.setString(3, p.getApellido());
            ps.setString(4, p.getEmail());
            ps.setString(5, p.getDireccion());
            ps.setString(6, p.getTelefono());
            ps.executeUpdate();
        }
        catch(Exception e)
        {
        }
        return 1;
    }
}