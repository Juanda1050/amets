package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static modelo.Conexion.conectar;

public class CorteDAO {

    Connection con;
    PreparedStatement ps,ps2;
    ResultSet rs,rs2;
    String horario;

    public String getHorario(int agentID) {

        String sql = "SELECT * FROM empleado";
        try{
            con = conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                int ws = rs.getInt("workShift");
                if(ws==1){
                    horario = "Matutino";
                }else if(ws==2){
                    horario = "Vespertino";
                }
            }
        }catch (Exception e){

        }
        return horario;
    }

    public boolean saveCorte(int lastSale, int agentID, int turno, String total){

        double granTotal = Double.parseDouble(total);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();

        String sql = "INSERT INTO cortecaja(saleID, agentID, auditDate, schedule, total) VALUES (?, ?, ?, ?, ?)";
        try{
            con = conectar();
            ps = con.prepareStatement(sql);
            ps.setInt(1,lastSale);
            ps.setInt(2, agentID);
            ps.setString(3, dtf.format(now));
            ps.setInt(4, turno);
            ps.setDouble(5, granTotal);
            ps.executeUpdate();
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public int getLastCorte(){

        int lastSaleID = 0;

        String sql = "SELECT * FROM cortecaja";
        try{
            con = conectar();
            ps2 = con.prepareStatement(sql);
            rs2 = ps2.executeQuery();
            while(rs2.next()) {
                if(rs2.isLast()) {
                    lastSaleID = rs2.getInt("saleID");
                }
            }
        }catch (Exception e){

        }
        return lastSaleID;
    }
}
