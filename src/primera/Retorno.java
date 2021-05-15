package primera;

import segunda.MenuPrincipal;

import javax.swing.*;
import java.awt.*;

public class Retorno
{
    public static void main( String[] args )
    {
        int MrQuestion = JOptionPane.showConfirmDialog((Component) null, "¿Seguro que desea regresar al menú principal?", "Regresar", JOptionPane.OK_CANCEL_OPTION);
        System.out.println(MrQuestion);

        JOptionPane.showMessageDialog(null, "CREDENCIALES INCORRECTAS. INTENTE NUEVAMENTE","Warning", JOptionPane.WARNING_MESSAGE);

        JOptionPane.showMessageDialog(null, "INTENTOS CONSUMIDOS. COMUNIQUESE CON EL SUPERVISOR","Error", JOptionPane.ERROR_MESSAGE);
    }

    public void runReturn(){

        int result = JOptionPane.showConfirmDialog(null, "¿Seguro que desea regresar al menú principal?", "Regresar", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION)
        {
            MenuPrincipal mpFrame = new MenuPrincipal();
            mpFrame.runFrame();
        }
    }
}