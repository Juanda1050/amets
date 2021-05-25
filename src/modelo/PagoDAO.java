package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static modelo.Conexion.conectar;

public class PagoDAO {

    Connection con;
    PreparedStatement ps;

    public void guardarVenta(int userID, int agentID, String description, String tpago, float price){

        float iva = 0.16f;

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();

        String sql = "INSERT INTO ventas(userID, agentID, quantity, saleDescription, saleDate, payment, subtotal, iva, total) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try{
            con = conectar();
            ps = con.prepareStatement(sql);
            ps.setInt(1, userID);
            ps.setInt(2, agentID);
            ps.setInt(3,1);
            ps.setString(4, description);
            ps.setString(5, dtf.format(now));
            ps.setString(6, tpago);
            ps.setFloat(7, price);
            ps.setFloat(8, (price * iva));
            ps.setFloat(9, (price)+(price * iva));
            ps.executeUpdate();
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