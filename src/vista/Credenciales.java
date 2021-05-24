package vista;

import modelo.Conexion;

import java.awt.*;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static modelo.Conexion.conectar;

public class Credenciales {

    private JFrame VcFrame;
    private JTextField VcUsuarioTF;
    private JPasswordField VcContraTF;
    PreparedStatement st;
    ResultSet rs;
    Connection con;

    public static void main(String[] args) {
        Credenciales credenciales = new Credenciales();
        credenciales.initialize();
    }

    public void initialize() {
        VcFrame = new JFrame("Amets Travels");
        VcFrame.setBounds(100, 100, 500, 300);
        VcFrame.setResizable(false);
        VcFrame.setLocationRelativeTo(null);
        VcFrame.setIconImage(Toolkit.getDefaultToolkit().getImage("resources/amets.jpg"));
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

        JButton VcButton = new JButton("Ingresar");
        panel_1.add(VcButton);
        VcButton.setBounds(99, 174, 81, 24);
        VcButton.addActionListener(e -> {
            Login();
        });

        VcFrame.setVisible(true);
    }

    public void Login(){
        String user = VcUsuarioTF.getText();
        String password = String.valueOf(VcContraTF.getPassword());

        String userSearch = "SELECT * FROM `empleado` WHERE `agentName` = ? AND `password` = ?";
        try {
            con = conectar();
            st = con.prepareStatement(userSearch);
            st.setString(1, user);
            st.setString(2, password);
            rs = st.executeQuery();
            if(rs.next()){
                int type = rs.getInt("jobTitle");
                switch(type)
                {
                    case 1:
                        MenuPrincipal mpFrame = new MenuPrincipal();
                        mpFrame.runFrame(rs.getInt("agentID"));
                        VcFrame.setVisible(false);
                        break;

                    case 2:
                        MenuAdministrador maFrame = new MenuAdministrador();
                        maFrame.runFrame();
                        VcFrame.setVisible(false);
                        break;

                }
            }else{
                JOptionPane.showMessageDialog(null, "Revisa tus credenciales y vuelve a intentarlo", "Credenciales Inválidas", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            if(con != null){
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}