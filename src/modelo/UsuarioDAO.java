package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
<<<<<<< HEAD
<<<<<<< HEAD
=======
import java.util.List;
>>>>>>> main
=======
import java.util.List;
>>>>>>> main

import static modelo.Conexion.conectar;

public class UsuarioDAO
{
    Conexion conectar = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    public int agregar(Usuario p)
    {
<<<<<<< HEAD
<<<<<<< HEAD

=======
>>>>>>> main
=======
>>>>>>> main
        String sql = "INSERT INTO usuarios(userName, userLastName, birthDate, email, address, phone) VALUES (?,?,?,?,?,?)";
        try
        {
            con = conectar();
            ps = con.prepareStatement(sql);
            ps.setString(1, p.getNombre());
            ps.setString(2, p.getApellido());
            ps.setString(3, p.getApellido());
            ps.setString(4, p.getEmail());
            ps.setString(5, p.getDireccion());
            ps.setString(6, p.getTelefono());
            ps.executeUpdate();
        }
        catch(Exception e)
        {
<<<<<<< HEAD
<<<<<<< HEAD

        }
        return 1;
    }
}
=======
        }
        return 1;
    }
}
>>>>>>> main
=======
        }
        return 1;
    }
}
>>>>>>> main
