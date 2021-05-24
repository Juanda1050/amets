package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import static modelo.Conexion.conectar;

public class VentaDAO
{
    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    public ArrayList<Venta> listar()
    {
        ArrayList<Venta> listaVentas = new ArrayList();
        String sql = "SELECT * FROM ventas";
        try{
            con = conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                Venta v = new Venta();
                v.setSaleID(rs.getInt(1));
                v.setUserID(rs.getInt(2));
                v.setAgentID(rs.getInt(3));
                v.setQuantity(rs.getInt(4));
                v.setSaleDescription(rs.getString(5));
                v.setSaleData(rs.getString(6));
                v.setPayment(rs.getString(7));
                v.setSubtotal(rs.getFloat(8));
                v.setIva(rs.getFloat(9));
                v.setTotal(rs.getFloat(10));
                listaVentas.add(v);
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
        return listaVentas;
    }
}
