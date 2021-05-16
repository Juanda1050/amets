package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion
{
    private static final String CONTROLADOR = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/poo";
    private static final String USUARIO = "root";
    private static final String CLAVE = "admin";

<<<<<<< HEAD:src/modelo/Conexion.java
    public static Connection conectar()
=======
    static
>>>>>>> Tercera:src/Conexion.java
    {
        try
        {
            Class.forName(CONTROLADOR);
<<<<<<< HEAD:src/modelo/Conexion.java
            conexion = DriverManager.getConnection(URL, USUARIO, CLAVE);
            System.out.println("Modelo.Conexion OK");
=======
>>>>>>> Tercera:src/Conexion.java
        }
        catch(ClassNotFoundException e)
        {
            System.out.println("Error al cargar el controlador");
            e.printStackTrace();
        }
    }

    public static Connection conectar()
    {
        Connection conexion = null;
        try
        {
            conexion = DriverManager.getConnection(URL, USUARIO, CLAVE);
            System.out.println("Conexion OK");
        }
        catch(SQLException e)
        {
            System.out.println("Error en la conexion");
            e.printStackTrace();
        }

        return conexion;
    }

}
