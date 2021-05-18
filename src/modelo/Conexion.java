package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion
{
    private static final String CONTROLADOR = "com.mysql.cj.jdbc.Driver";
<<<<<<< HEAD:src/modelo/Conexion.java
    private static final String URL = "jdbc:mysql://localhost:3306/poo";
=======

    private static final String URL = "jdbc:mysql://localhost:3306/amets_travels";
>>>>>>> Cuarta:src/Conexion.java
    private static final String USUARIO = "root";
    private static final String CLAVE = "!Pa2131xamL";

    public static Connection conectar()
    {
        Connection conexion = null;
        try
        {
            Class.forName(CONTROLADOR);
            conexion = DriverManager.getConnection(URL, USUARIO, CLAVE);
            System.out.println("Modelo.Conexion OK");
        }
        catch(ClassNotFoundException e)
        {
            System.out.println("Error al cargar el controlador");
            e.printStackTrace();
        }
        catch(SQLException e)
        {
            System.out.println("Error en la conexion");
            e.printStackTrace();
        }

        return conexion;
    }

    public static void main(String[] args)
    {
        Conexion conecxion = new Conexion();
        conecxion.conectar();
    }
}