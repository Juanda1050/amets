package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import static modelo.Conexion.conectar;

public class SelecPaqDAO {

    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    ArrayList<String> listaPaquetes;

    public int listarPaquetes(int destino) {

        listaPaquetes = new ArrayList<>();
        PreparedStatement ps2;
        ResultSet rs2;

        String sql = "SELECT packID FROM detallepaquete WHERE `destinationID` = ?";
        try{
            con = conectar();
            ps = con.prepareStatement(sql);
            ps.setInt(1, destino);
            rs = ps.executeQuery();
            while(rs.next()) {
                int packID = rs.getInt("packID");
                ps2 = con.prepareStatement("SELECT packName FROM paquetes WHERE packID = ?");
                ps2.setInt(1,packID);
                rs2 = ps2.executeQuery();
                while(rs2.next()) {
                    String packName = rs2.getString("packName");
                    listaPaquetes.add(packName);
                }
            }
        }catch (Exception e){

        }
        return 0;
    }

    public ArrayList<String> getListaPaquete(){
        return listaPaquetes;
    }

    public ArrayList<String> listarDestinos() {

        ArrayList<String> arrayDestinos = new ArrayList<>();

        String sql = "SELECT city FROM destino";
        try{
            con = conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()) {
                String cityName = rs.getString("city");
                arrayDestinos.add(cityName);
            }
        }catch (Exception e){

        }
        return arrayDestinos;
    }

    public ArrayList<String> getData(int index){

        ArrayList<String> arrayTFData = new ArrayList<>();
        PreparedStatement ps2,ps3;
        ResultSet rs2, rs3;

        String sql = "SELECT * FROM paquetes WHERE `packID` = ?";
        try{
            con = conectar();
            ps = con.prepareStatement(sql);
            ps.setInt(1,index);
            rs = ps.executeQuery();
            while(rs.next()) {
                String packName = rs.getString("packName");
                arrayTFData.add(packName);
                String packDesc = rs.getString("packDescription");
                arrayTFData.add(packDesc);
                int pass = rs.getInt("passengers");
                arrayTFData.add(String.valueOf(pass));
                double price = rs.getDouble("price");
                arrayTFData.add(String.valueOf(price));

                ps2 = con.prepareStatement("SELECT destinationID FROM detallepaquete WHERE `packID` = ?");
                ps2.setInt(1,index);
                rs2 = ps2.executeQuery();
                while(rs2.next()){
                    int destinoID = rs2.getInt("destinationID");

                    ps3 =  con.prepareStatement("SELECT city FROM destino WHERE `destinationID` = ?");
                    ps3.setInt(1,destinoID);
                    rs3 = ps3.executeQuery();
                    while(rs3.next()){
                        String cityName = rs3.getString("city");
                        arrayTFData.add(cityName);
                    }
                }
            }
        }catch (Exception e){

        }
        return arrayTFData;
    }

}
