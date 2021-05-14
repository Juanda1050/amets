package primera;

import Controlador.Conexion;
import segunda.MenuPrincipal;
import tercera.VistaMA;

import java.awt.*;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Credenciales {

    private JFrame VcFrame;
    private JTextField VcUsuarioTF;
    private JPasswordField VcContraTF;
    private JComboBox VcComboBox;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        Credenciales cFrame = new Credenciales();
        cFrame.runFrame();
    }

    public void runFrame(){
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Credenciales window = new Credenciales();
                    window.VcFrame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public Credenciales() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        VcFrame = new JFrame();
        VcFrame.setExtendedState(Frame.MAXIMIZED_BOTH);
        VcFrame.setBounds(100, 100, 500, 300);
        VcFrame.setTitle("Amets Travels");
        VcFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                int result = JOptionPane.showConfirmDialog(VcFrame, "¿Desea cerrar el programa?", "Salir del programa", JOptionPane.YES_NO_OPTION);
                if (result == JOptionPane.YES_OPTION)
                {
                    VcFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                }
                else if (result == JOptionPane.NO_OPTION)
                {
                    VcFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                }
            }
        });

        JPanel panel = new JPanel();
        VcFrame.getContentPane().add(panel, BorderLayout.WEST);
        panel.setLayout(new BorderLayout(10, 10));

        JLabel VcLogoL = new JLabel(new ImageIcon("resources/logo.png"));
        panel.add(VcLogoL, BorderLayout.WEST);

        JPanel panel_1 = new JPanel();
        VcFrame.getContentPane().add(panel_1);
        panel_1.setLayout(null);

        JLabel VcUsuarioL = new JLabel("Usuario");
        panel_1.add(VcUsuarioL);
        VcUsuarioL.setBounds(0, 100, 60, 24);

        VcUsuarioTF = new JTextField();
        panel_1.add(VcUsuarioTF);
        VcUsuarioTF.setColumns(10);
        VcUsuarioTF.setBounds(80, 100, 119, 24);

        JLabel VcContraL = new JLabel("Contraseña");
        panel_1.add(VcContraL);
        VcContraL.setBounds(0, 137, 76, 24);

        VcContraTF = new JPasswordField();
        panel_1.add(VcContraTF);
        VcContraTF.setColumns(10);
        VcContraTF.setBounds(80, 137, 119, 24);

        Object[] items = new Object[] {"Tipo de usuario 1", "Tipo de usuario 2"};
        VcComboBox = new JComboBox(items);
        VcComboBox.setFont(new Font("Tahoma", Font.PLAIN, 12));
        panel_1.add(VcComboBox);
        VcComboBox.setBounds(80, 57, 119, 24);

        JButton VcButton = new JButton("Ingresar");
        panel_1.add(VcButton);
        VcButton.setBounds(99, 174, 81, 24);
        VcButton.addActionListener(e -> {
            Login();
        });
    }

    public void Login(){

        PreparedStatement st;
        ResultSet rs;

        String user = VcUsuarioTF.getText();
        String password = String.valueOf(VcContraTF.getPassword());
        int userType = VcComboBox.getSelectedIndex()+1;

        String userSearch = "SELECT * FROM `empleado` WHERE `agentName` = ? AND `password` = ? AND `jobTitle` = ?";
        Conexion con = new Conexion();
        try {
            st = con.conectar().prepareStatement(userSearch);
            st.setString(1, user);
            st.setString(2, password);
            st.setString(3, String.valueOf(userType));
            rs = st.executeQuery();

            if(rs.next()){
                int type = rs.getInt("jobTitle");
                System.out.println(type);
                switch(type)
                {
                    case 1:
                        MenuPrincipal mpFrame = new MenuPrincipal();
                        mpFrame.runFrame();
                        VcFrame.setVisible(false);
                        break;

                    case 2:
                        VistaMA maFrame = new VistaMA();
                        maFrame.runFrame();
                        VcFrame.setVisible(false);
                        break;

                }
            }else{
                JOptionPane.showMessageDialog(null, "Revisa tus credenciales y vuelve a intentarlo", "Credenciales Inválidas", JOptionPane.ERROR_MESSAGE);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
}