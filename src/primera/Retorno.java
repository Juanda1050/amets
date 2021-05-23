package primera;

import segunda.MenuPrincipal;
import javax.swing.*;

public class Retorno
{
    public boolean runReturn(int agentID){

        int result = JOptionPane.showConfirmDialog(null, "¿Seguro que desea regresar al menú principal?", "Regresar", JOptionPane.YES_NO_OPTION);
        if (result == JOptionPane.OK_OPTION)
        {
            MenuPrincipal mp = new MenuPrincipal();
            mp.runFrame(agentID);
            return false;
        }
        else
        {
            return true;
        }
    }
}