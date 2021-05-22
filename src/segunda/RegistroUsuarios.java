package segunda;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class RegistroUsuarios extends MenuPrincipal{

    protected JFrame ruFrame;
    private JTextField ruDireccionTF;
    private JTextField ruTelefonoTF;
    private JTextField ruEmailTF;
    private JTextField ruNombreTF;
    private JTextField ruApellidoTF;
    private JTextField ruNacimientoTF;

    public void initializeRU(int agentID) {
        ruFrame = new JFrame();
        ruFrame.setVisible(true);
        ruFrame.setExtendedState(Frame.MAXIMIZED_BOTH);
        ruFrame.setTitle("Amets Travel");
        ruFrame.setBounds(100, 100, 1280, 720);
        ruFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                int result = JOptionPane.showConfirmDialog(ruFrame, "¿Desea cerrar el programa?", "Salir del programa", JOptionPane.YES_NO_OPTION);
                if (result == JOptionPane.YES_OPTION)
                {
                    ruFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                }
                else if (result == JOptionPane.NO_OPTION)
                {
                    ruFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                }
            }
        });

        JPanel ruTop = new JPanel();
        ruTop.setBorder(new EmptyBorder(20, 20, 20, 20));
        ruFrame.getContentPane().add(ruTop, BorderLayout.NORTH);
        ruTop.setLayout(new BorderLayout(0, 0));

        JLabel ruLabel = new JLabel("Registro de Usuarios");
        ruLabel.setHorizontalAlignment(SwingConstants.CENTER);
        ruLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
        ruTop.add(ruLabel, BorderLayout.NORTH);

        JPanel ruMid = new JPanel();
        ruFrame.getContentPane().add(ruMid, BorderLayout.CENTER);
        ruMid.setBorder(new EmptyBorder(20, 20, 20, 20));
        ruMid.setLayout(new BorderLayout(0, 0));

        Panel ruMidEast = new Panel();
        ruMid.add(ruMidEast, BorderLayout.CENTER);
        ruMidEast.setLayout(new GridLayout(0, 2, 0, 30));

        Label ruNombreLbl = new Label("Nombre(s)");
        ruMidEast.add(ruNombreLbl);
        ruNombreLbl.setFont(new Font("Tahoma", Font.PLAIN, 16));

        ruNombreTF = new JTextField();
        ruNombreTF.setFont(new Font("Tahoma", Font.PLAIN, 20));
        ruNombreTF.setHorizontalAlignment(SwingConstants.LEFT);
        ruNombreTF.setColumns(10);
        ruNombreTF.setAlignmentX(1.0f);
        ruMidEast.add(ruNombreTF);

        Label ruApellidoLbl = new Label("Apellidos");
        ruMidEast.add(ruApellidoLbl);
        ruApellidoLbl.setFont(new Font("Tahoma", Font.PLAIN, 16));

        ruApellidoTF = new JTextField();
        ruApellidoTF.setFont(new Font("Tahoma", Font.PLAIN, 20));
        ruApellidoTF.setHorizontalAlignment(SwingConstants.LEFT);
        ruApellidoTF.setColumns(10);
        ruApellidoTF.setAlignmentX(1.0f);
        ruMidEast.add(ruApellidoTF);

        Label ruNacimientoLbl = new Label("Fecha de Nacimiento");
        ruMidEast.add(ruNacimientoLbl);
        ruNacimientoLbl.setFont(new Font("Tahoma", Font.PLAIN, 16));

        ruNacimientoTF = new JTextField();
        ruNacimientoTF.setFont(new Font("Tahoma", Font.PLAIN, 20));
        ruNacimientoTF.setHorizontalAlignment(SwingConstants.LEFT);
        ruNacimientoTF.setColumns(10);
        ruNacimientoTF.setAlignmentX(1.0f);
        ruMidEast.add(ruNacimientoTF);

        Label ruDireccionLbl = new Label("Direcci\u00F3n");
        ruMidEast.add(ruDireccionLbl);
        ruDireccionLbl.setFont(new Font("Tahoma", Font.PLAIN, 16));

        ruDireccionTF = new JTextField();
        ruDireccionTF.setFont(new Font("Tahoma", Font.PLAIN, 20));
        ruDireccionTF.setHorizontalAlignment(SwingConstants.LEFT);
        ruDireccionTF.setAlignmentX(1.0f);
        ruDireccionTF.setColumns(10);
        ruMidEast.add(ruDireccionTF);

        Label ruEmailLbl = new Label("Email");
        ruMidEast.add(ruEmailLbl);
        ruEmailLbl.setFont(new Font("Tahoma", Font.PLAIN, 16));

        ruEmailTF = new JTextField();
        ruEmailTF.setFont(new Font("Tahoma", Font.PLAIN, 20));
        ruEmailTF.setHorizontalAlignment(SwingConstants.LEFT);
        ruEmailTF.setAlignmentX(Component.RIGHT_ALIGNMENT);
        ruEmailTF.setColumns(10);
        ruMidEast.add(ruEmailTF);

        Label ruTelefonoLbl = new Label("Tel\u00E9fono");
        ruMidEast.add(ruTelefonoLbl);
        ruTelefonoLbl.setFont(new Font("Tahoma", Font.PLAIN, 16));

        ruTelefonoTF = new JTextField();
        ruTelefonoTF.setFont(new Font("Tahoma", Font.PLAIN, 20));
        ruTelefonoTF.setHorizontalAlignment(SwingConstants.LEFT);
        ruTelefonoTF.setAlignmentX(1.0f);
        ruTelefonoTF.setColumns(10);
        ruMidEast.add(ruTelefonoTF);

        JPanel ruBottom = new JPanel();
        ruBottom.setBorder(new EmptyBorder(30, 20, 20, 20));
        ruFrame.getContentPane().add(ruBottom, BorderLayout.SOUTH);
        ruBottom.setLayout(new BorderLayout(0, 0));

        JButton ruSiguienteBtn = new JButton("Siguiente");
        ruSiguienteBtn.setFont(new Font("Tahoma", Font.PLAIN, 16));
        ruBottom.add(ruSiguienteBtn, BorderLayout.EAST);
        ruSiguienteBtn.addActionListener(e -> {
            SeleccionarPaquete sp = new SeleccionarPaquete();
            sp.initializeSP(agentID);
            ruFrame.setVisible(false);
        });

        JButton ruVolverBtn = new JButton("Volver");
        ruVolverBtn.setFont(new Font("Tahoma", Font.PLAIN, 16));
        ruBottom.add(ruVolverBtn, BorderLayout.WEST);
        ruVolverBtn.addActionListener(e -> {
            mpFrame.setVisible(true);
            ruFrame.setVisible(false);
        });
    }

}