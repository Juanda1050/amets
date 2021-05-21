package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

import static modelo.Conexion.conectar;

public class PagoDAO {

    Connection con;
    PreparedStatement ps;

    public void guardarVenta(int agentID, String description, String tpago, double price, int userID){

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();

        String sql = "INSERT INTO ventas(userID, agentID, quantity, saleDescription, saleDate, payment, subtotal, iva, total) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try{
            con = conectar();
            ps = con.prepareStatement(sql);
            ps.setInt(1,userID);
            ps.setInt(2, agentID);
            ps.setInt(3,1);
            ps.setString(4, description);
            ps.setString(5, dtf.format(now));
            ps.setString(6, tpago);
            ps.setDouble(7, price);
            ps.setDouble(8, (price*0.16));
            ps.setDouble(9, (price)+(price*0.16));
            ps.executeUpdate();
        }catch (Exception e){
        }

    }
}
