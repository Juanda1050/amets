package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import static modelo.Conexion.conectar;

public class SelecPaqDAO {

    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    ArrayList<String> listaPaquetes;

    public void listarPaquetes(int destino) {

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

        }finally {
            if(con != null){
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return arrayDestinos;
    }

    public ArrayList<String> getData(String name){

        ArrayList<String> arrayTFData = new ArrayList<>();
        PreparedStatement ps2,ps3,ps4,ps5;
        ResultSet rs2,rs3,rs4,rs5;
        int packID = 0, flightID = 0, hotelID = 0, airlineID = 0;

        try{
            con = conectar();
            //Get pack data
            ps = con.prepareStatement("SELECT * FROM paquetes WHERE packName = ?");
            ps.setString(1,name);
            rs = ps.executeQuery();
            while(rs.next()){
                packID = rs.getInt("packID");
                arrayTFData.add(rs.getString("packDescription"));
                arrayTFData.add(String.valueOf(rs.getInt("price")));
                arrayTFData.add(String.valueOf(rs.getInt("passengers")));
            }

            //Get IDs
            ps2 = con.prepareStatement("SELECT * FROM detallepaquete WHERE packID = ?");
            ps2.setInt(1,packID);
            rs2 = ps2.executeQuery();
            while(rs2.next()){
                flightID = rs2.getInt("flightID");
                hotelID = rs2.getInt("hotelID");
            }

            //Get hotel data
            ps3 = con.prepareStatement("SELECT * FROM hotel WHERE hotelID = ?");
            ps3.setInt(1,hotelID);
            rs3 = ps3.executeQuery();
            while(rs3.next()){
                arrayTFData.add(rs3.getString("hotelName"));
                arrayTFData.add(rs3.getString("location"));
                int regimen = rs3.getInt("regime");
                if(regimen==1){
                    arrayTFData.add("Media Pensión");
                }else if(regimen==2){
                    arrayTFData.add("Pensión Completa");
                }
                arrayTFData.add(String.valueOf(rs3.getTime("entryDate")));
                arrayTFData.add(String.valueOf(rs3.getTime("exitDate")));
            }

            //Get vuelo data
            ps4 = con.prepareStatement("SELECT * FROM vuelo WHERE flightID = ?");
            ps4.setInt(1,flightID);
            rs4 = ps4.executeQuery();
            while(rs4.next()){
                arrayTFData.add(rs4.getString("origin"));
                airlineID = rs4.getInt("airlineID");
                arrayTFData.add(rs4.getDate("departureDate")+" - "+rs4.getTime("departureDate"));
                arrayTFData.add(rs4.getDate("landingDate")+" - "+rs4.getTime("landingDate"));
            }

            //Get aerolinea data
            ps5 = con.prepareStatement("SELECT * FROM aerolinea WHERE airlineID = ?");
            ps5.setInt(1,airlineID);
            rs5 = ps5.executeQuery();
            while(rs5.next()){
                arrayTFData.add(rs5.getString("airlineName"));
                arrayTFData.add(rs5.getString("flyClass"));
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
        return arrayTFData;
    }

}
