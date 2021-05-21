package controlador;

import modelo.Usuario;
import modelo.UsuarioDAO;
import segunda.RegistroUsuarios;
import segunda.SeleccionarPaquete;

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
            SeleccionarPaquete spFrame = new SeleccionarPaquete();
            spFrame.initialize();

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
}
