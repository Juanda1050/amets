package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion
{
    private static final String CONTROLADOR = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/amets_travels";
    private static final String USUARIO = "root";
    private static final String CLAVE = "!Pa2131xamL";

    public static Connection conectar()
    {
        Connection conexion = null;
        try
        {
            Class.forName(CONTROLADOR);
            conexion = DriverManager.getConnection(URL, USUARIO, CLAVE);
<<<<<<< HEAD:src/modelo/Conexion.java
            System.out.println("Modelo.Conexion OK");
=======
            System.out.println("modelo.Conexion OK");
>>>>>>> main:src/Conexion.java
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