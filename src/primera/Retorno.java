package primera;

import javax.swing.JOptionPane;
import java.awt.Component;

public class Retorno
{
    public static void main( String[] args )
    {
        int MrQuestion = JOptionPane.showConfirmDialog((Component) null, "¿Seguro que desea regresar al menú principal?", "Regresar", JOptionPane.OK_CANCEL_OPTION);
        System.out.println(MrQuestion);

        JOptionPane.showMessageDialog(null, "CREDENCIALES INCORRECTAS. INTENTE NUEVAMENTE","Warning", JOptionPane.WARNING_MESSAGE);

        JOptionPane.showMessageDialog(null, "INTENTOS CONSUMIDOS. COMUNIQUESE CON EL SUPERVISOR","Error", JOptionPane.ERROR_MESSAGE);
    }
}