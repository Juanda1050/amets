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

    private JFrame vCrendecialFrame;
    private JTextField vCredencial_userTF;
    private JPasswordField vCredencial_passwordTF;
    PreparedStatement st;
    ResultSet rs;
    Connection con;

    public static void main(String[] args) {
        Credenciales credenciales = new Credenciales();
        credenciales.initialize();
    }

    public void initialize() {
        vCrendecialFrame = new JFrame("Amets Travels");
        vCrendecialFrame.setBounds(100, 100, 500, 300);
        vCrendecialFrame.setResizable(false);
        vCrendecialFrame.setLocationRelativeTo(null);
        vCrendecialFrame.setIconImage(Toolkit.getDefaultToolkit().getImage("resources/amets.jpg"));
        vCrendecialFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                int result = JOptionPane.showConfirmDialog(vCrendecialFrame, "¿Desea cerrar el programa?", "Salir del programa", JOptionPane.YES_NO_OPTION);
                if (result == JOptionPane.YES_OPTION)
                {
                    vCrendecialFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                }
                else if (result == JOptionPane.NO_OPTION)
                {
                    vCrendecialFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                }
            }
        });

        JPanel vCredencialLeft = new JPanel();
        vCrendecialFrame.getContentPane().add(vCredencialLeft, BorderLayout.WEST);
        vCredencialLeft.setLayout(new BorderLayout(10, 10));

        JLabel vCredencial_logoL = new JLabel(new ImageIcon("resources/logo.png"));
        vCredencialLeft.add(vCredencial_logoL, BorderLayout.WEST);

        JPanel vCredencialRight = new JPanel();
        vCrendecialFrame.getContentPane().add(vCredencialRight);
        vCredencialRight.setLayout(null);

        JLabel vCredencial_userL = new JLabel("Usuario");
        vCredencialRight.add(vCredencial_userL);
        vCredencial_userL.setBounds(0, 100, 60, 24);

        vCredencial_userTF = new JTextField();
        vCredencialRight.add(vCredencial_userTF);
        vCredencial_userTF.setColumns(10);
        vCredencial_userTF.setBounds(80, 100, 119, 24);

        JLabel vCredencial_passwordL = new JLabel("Contraseña");
        vCredencialRight.add(vCredencial_passwordL);
        vCredencial_passwordL.setBounds(0, 137, 76, 24);

        vCredencial_passwordTF = new JPasswordField();
        vCredencialRight.add(vCredencial_passwordTF);
        vCredencial_passwordTF.setColumns(10);
        vCredencial_passwordTF.setBounds(80, 137, 119, 24);

        JButton vCredencial_loginB = new JButton("Ingresar");
        vCredencialRight.add(vCredencial_loginB);
        vCredencial_loginB.setBounds(99, 174, 81, 24);
        vCredencial_loginB.addActionListener(e -> {
            Login();
        });

        vCrendecialFrame.setVisible(true);
    }

    public void Login(){
        String user = vCredencial_userTF.getText();
        String password = String.valueOf(vCredencial_passwordTF.getPassword());

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
                        vCrendecialFrame.setVisible(false);
                        break;

                    case 2:
                        MenuAdministrador maFrame = new MenuAdministrador();
                        maFrame.runFrame();
                        vCrendecialFrame.setVisible(false);
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