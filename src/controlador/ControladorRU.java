package controlador;

import modelo.Usuario;
import modelo.UsuarioDAO;
<<<<<<< HEAD
<<<<<<< HEAD
import vista.RegistroUsuarios;
import vista.SeleccionarPaquetes;
=======
import segunda.RegistroUsuarios;
import segunda.SeleccionarPaquete;
>>>>>>> main
=======
import segunda.RegistroUsuarios;
import segunda.SeleccionarPaquete;
>>>>>>> main

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControladorRU implements ActionListener
{
    UsuarioDAO dao;
    Usuario u = new Usuario();
    RegistroUsuarios vista = new RegistroUsuarios();

    public ControladorRU(RegistroUsuarios v, UsuarioDAO dao)
    {
        this.vista = v;
        this.dao = dao;
        this.vista.ruSiguienteBtn.addActionListener(this);

    }

    public void agregar()
    {
        String Nom = vista.ruNombreTF.getText();
        String Ap = vista.ruApellidoTF.getText();
        String Nacimiento = vista.ruNacimientoTF.getText();
        String Direccion = vista.ruDireccionTF.getText();
        String Email = vista.ruEmailTF.getText();
        String Tel = vista.ruTelefonoTF.getText();
        u.setNombre(Nom);
        u.setApellido(Ap);
        u.setNacimiento(Nacimiento);
        u.setDireccion(Direccion);
        u.setEmail(Email);
        u.setTelefono(Tel);
        int r = dao.agregar(u);
        if(r==1)
        {
            System.out.println("Exito");
<<<<<<< HEAD
<<<<<<< HEAD
            SeleccionarPaquetes spFrame = new SeleccionarPaquetes();
            spFrame.runFrame();
=======
            SeleccionarPaquete spFrame = new SeleccionarPaquete();
            spFrame.initialize();
>>>>>>> main
=======
            SeleccionarPaquete spFrame = new SeleccionarPaquete();
            spFrame.initialize();
>>>>>>> main

        }
        else
        {
            System.out.println("Fail");
        }
    }

    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource()== vista.ruSiguienteBtn)
        {
            agregar();
        }
    }
<<<<<<< HEAD
<<<<<<< HEAD
}
=======
}
>>>>>>> main
=======
}
>>>>>>> main
