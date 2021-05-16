package modelo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static modelo.Conexion.conectar;

public class AerolineaDAO {
    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    public ArrayList<Aerolinea> listar(){
        ArrayList<Aerolinea> aero = new ArrayList<>();
        String sql = "SELECT * FROM aerolinea";
        try{
            con = conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
        }catch (SQLException e){
            System.out.println("Error en listar Aerolinea");
            e.printStackTrace();
        }
        return aero;
    }
}
