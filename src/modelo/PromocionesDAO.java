package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static modelo.Conexion.conectar;

public class PromocionesDAO {
    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    public int agregar(Promociones p){
        String sql = "INSERT INTO promociones(packID, promotionName, discount, promoDescription) VALUES (?, ?, ?, ?)";
        try{
            con = conectar();
            ps = con.prepareStatement(sql);
            ps.setInt(1, p.getPackID());
            ps.setString(2, p.getPromotionName());
            ps.setFloat(3, p.getDiscount());
            ps.setString(4, p.getDescription());
            ps.executeUpdate();
        }catch (SQLException e){
            System.out.println("Error en agregar un registro");
            e.printStackTrace();
        }
        return 1;
    }

    public int actualizar(Promociones p){
        String sql = "UPDATE promociones SET packID = ?, promotionName = ?, discount = ?, promoDescription = ? WHERE promotionID = ?";
        int r = 0;
        try{
            con = conectar();
            ps = con.prepareStatement(sql);
            ps.setInt(1, p.getPackID());
            ps.setString(2, p.getPromotionName());
            ps.setFloat(3, p.getDiscount());
            ps.setString(4, p.getDescription());
            ps.setInt(5, p.getPromotionID());
            r = ps.executeUpdate();
            if (r == 1){
                return 1;
            }
            else{
                return 0;
            }
        }catch (SQLException e){
            System.out.println("Error en actualziar un registro");
            e.printStackTrace();
        }
        return r;
    }

    public void eliminar(int id){
        String sql = "DELETE FROM promociones WHERE promotionID = "+id;
        try{
            con = conectar();
            ps = con.prepareStatement(sql);
            ps.executeUpdate();
        }catch (SQLException e){
            System.out.println("Error en eliminar un registro");
            e.printStackTrace();
        }
    }

    public List listar(){
        List<Promociones> promo = new ArrayList<>();
        String sql = "SELECT * FROM promociones";
        PreparedStatement ps2;
        ResultSet rs2;
        try{
            con = conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                Promociones p = new Promociones();
                p.setPromotionID(rs.getInt(1));
                //Seleccionar el nombre del paquete de la tabla paquetes
                ps2 = con.prepareStatement("SELECT packName FROM paquetes WHERE packID = ?");
                ps2.setInt(1, rs.getInt(2));
                rs2 = ps2.executeQuery();
                while (rs2.next()){
                    String paquete = rs2.getString("packName");
                    p.setPackName(paquete);
                }
                p.setPromotionName(rs.getString(3));
                p.setDiscount(rs.getFloat(4));
                p.setDescription(rs.getString(5));
                promo.add(p);
            }
        }catch (SQLException e){
            System.out.println("Error al listar los registros");
            e.printStackTrace();
        }
        return promo;
    }

    public int packID(String pack) {
        int id = 0;
        String sql = "SELECT packID FROM paquetes WHERE packName = ?";
        try{
            con = conectar();
            ps = con.prepareStatement(sql);
            ps.setString(1, pack);
            rs = ps.executeQuery();
            while(rs.next()){
                id = rs.getInt("packID");
            }
            return id;
        }catch (Exception e){
        }
        return 0;
    }

    public ArrayList<String> listarPaquete(){
        ArrayList<String> pack = new ArrayList<>();
        String sql = "SELECT * FROM paquetes";
        try{
            con = conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                String paquete = rs.getString("packName");
                pack.add(paquete);
            }
        }catch (SQLException e){
            System.out.println("Error en listar paquetes");
            e.printStackTrace();
        }
        return pack;
    }
}
