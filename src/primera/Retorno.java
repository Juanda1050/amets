package primera;

import segunda.Credenciales;
import segunda.MenuPrincipal;

import javax.swing.*;
import java.awt.*;

public class Retorno extends MenuPrincipal
{
    public boolean runReturn(){

        int result = JOptionPane.showConfirmDialog(null, "¿Seguro que desea regresar al menú principal?", "Regresar", JOptionPane.YES_NO_OPTION);
        if (result == JOptionPane.OK_OPTION)
        {
            mpFrame.setVisible(true);
            return false;
        }
        else
        {
            return true;
        }
    }
}