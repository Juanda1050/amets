package vista;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MenuAdministrador {

    private JFrame mAdminFrame;

    public void runFrame(){
        EventQueue.invokeLater(() -> {
            try {
                MenuAdministrador window = new MenuAdministrador();
                window.mAdminFrame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public MenuAdministrador() {
        initialize();
    }

    private void initialize() {
        mAdminFrame = new JFrame("Menu Administrador");
        mAdminFrame.setBounds(100, 100, 1280, 720);
        mAdminFrame.setExtendedState(Frame.MAXIMIZED_BOTH);
        mAdminFrame.setIconImage(Toolkit.getDefaultToolkit().getImage("resources/amets.jpg"));
        mAdminFrame.getContentPane().setLayout(new BorderLayout(0, 0));
        mAdminFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                int result = JOptionPane.showConfirmDialog(mAdminFrame, "¿Desea cerrar el programa?", "Salir del programa", JOptionPane.YES_NO_OPTION);
                if (result == JOptionPane.YES_OPTION)
                {
                    mAdminFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                }
                else if (result == JOptionPane.NO_OPTION)
                {
                    mAdminFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                }
            }
        });

        JPanel mAdminTop = new JPanel();
        mAdminTop.setBorder(new EmptyBorder(20, 20, 20, 20));
        mAdminFrame.getContentPane().add(mAdminTop, BorderLayout.NORTH);
        mAdminTop.setLayout(new BorderLayout(0, 0));

        JLabel mAdminL = new JLabel("MENU DE ADMINISTRADOR");
        mAdminL.setFont(new Font("Tahoma", Font.BOLD, 18));
        mAdminL.setHorizontalAlignment(SwingConstants.CENTER);
        mAdminTop.add(mAdminL, BorderLayout.CENTER);

        JLabel mAdmin_selectL = new JLabel("Seleccione lo que desea gestionar");
        mAdmin_selectL.setFont(new Font("Tahoma", Font.PLAIN, 19));
        mAdminTop.add(mAdmin_selectL, BorderLayout.SOUTH);

        JPanel mAdminMid = new JPanel();
        mAdminMid.setBorder(new EmptyBorder(20, 20, 20, 20));
        mAdminFrame.getContentPane().add(mAdminMid, BorderLayout.CENTER);
        mAdminMid.setLayout(new GridLayout(0, 2, 20, 40));

        JButton mAdmin_empleadoB = new JButton("Empleados");
        mAdmin_empleadoB.setFont(new Font("Tahoma", Font.PLAIN, 20));
        mAdmin_empleadoB.setIcon(new ImageIcon("resources/team.png"));
        mAdminMid.add(mAdmin_empleadoB);
        mAdmin_empleadoB.addActionListener(e -> {
            GestionarEmpleados geFrame = new GestionarEmpleados();
            geFrame.runFrame();
            mAdminFrame.setVisible(false);
        });

        JButton mAdmin_promoB = new JButton("Promociones");
        mAdmin_promoB.setFont(new Font("Tahoma", Font.PLAIN, 20));
        mAdmin_promoB.setIcon(new ImageIcon("resources/tag.png"));
        mAdminMid.add(mAdmin_promoB);
        mAdmin_promoB.addActionListener(e -> {
            GestionarPromociones gpFrame = new GestionarPromociones();
            gpFrame.runFrame();
            mAdminFrame.setVisible(false);
        });

        JButton mAdmin_destinoB = new JButton("Destinos");
        mAdmin_destinoB.setFont(new Font("Tahoma", Font.PLAIN, 20));
        mAdmin_destinoB.setIcon(new ImageIcon("resources/destino.png"));
        mAdminMid.add(mAdmin_destinoB);
        mAdmin_destinoB.addActionListener(e -> {
            GestionarDestinos gdFrame = new GestionarDestinos();
            gdFrame.runFrame();
            mAdminFrame.setVisible(false);
        });

        JButton mAdmin_paqueteB = new JButton("Paquetes");
        mAdmin_paqueteB.setFont(new Font("Tahoma", Font.PLAIN, 20));
        mAdmin_paqueteB.setIcon(new ImageIcon("resources/mundo.png"));
        mAdminMid.add(mAdmin_paqueteB);
        mAdmin_paqueteB.addActionListener(e -> {
            GestionarPaquetes gpFrame = new GestionarPaquetes();
            gpFrame.runFrame();
            mAdminFrame.setVisible(false);
        });

        JButton mAdmin_hotelB = new JButton("Hoteles");
        mAdmin_hotelB.setFont(new Font("Tahoma", Font.PLAIN, 20));
        mAdmin_hotelB.setIcon(new ImageIcon("resources/hotel.png"));
        mAdminMid.add(mAdmin_hotelB);
        mAdmin_hotelB.addActionListener(e -> {
            GestionarHoteles ghFrame = new GestionarHoteles();
            ghFrame.runFrame();
            mAdminFrame.setVisible(false);
        });

        JButton mAdmin_vueloB = new JButton("Vuelos");
        mAdmin_vueloB.setFont(new Font("Tahoma", Font.PLAIN, 20));
        mAdmin_vueloB.setIcon(new ImageIcon("resources/vuelo.png"));
        mAdminMid.add(mAdmin_vueloB);
        mAdmin_vueloB.addActionListener(e -> {
            GestionarVuelos gvFrame = new GestionarVuelos();
            gvFrame.runFrame();
            mAdminFrame.setVisible(false);
        });

        JButton mAdmin_ventasB = new JButton("Ventas");
        mAdmin_ventasB.setFont(new Font("Tahoma", Font.PLAIN, 20));
        mAdmin_ventasB.setIcon(new ImageIcon("resources/ventas.png"));
        mAdminMid.add(mAdmin_ventasB);
        mAdmin_ventasB.addActionListener(e -> {
            GestionarVentas gvtFrame = new GestionarVentas();
            gvtFrame.runFrame();
            mAdminFrame.setVisible(false);
        });

        JButton mAdmin_reporteB = new JButton("Reportes");
        mAdmin_reporteB.setFont(new Font("Tahoma", Font.PLAIN, 20));
        mAdmin_reporteB.setIcon(new ImageIcon("resources/reportes.png"));
        mAdminMid.add(mAdmin_reporteB);
        mAdmin_reporteB.addActionListener(e -> {
            GestionarReportes grFrame = new GestionarReportes();
            grFrame.runFrame();
            mAdminFrame.setVisible(false);
        });

        JPanel mAdminRight = new JPanel();
        mAdminRight.setBorder(new EmptyBorder(20, 20, 20, 20));
        mAdminFrame.getContentPane().add(mAdminRight, BorderLayout.EAST);
        mAdminRight.setLayout(new BorderLayout(0, 0));

        JLabel mAdmin_logoL = new JLabel("");
        mAdmin_logoL.setIcon(new ImageIcon("resources/amets.jpg"));
        mAdminRight.add(mAdmin_logoL, BorderLayout.NORTH);

        JPanel mAdminBottom = new JPanel();
        mAdminBottom.setBorder(new EmptyBorder(20, 1000, 20, 20));
        mAdminFrame.getContentPane().add(mAdminBottom, BorderLayout.SOUTH);
        mAdminBottom.setLayout(new GridLayout(0, 1, 0, 0));

        JButton mAdmin_backB = new JButton("Salir");
        mAdmin_backB.setFont(new Font("Tahoma", Font.PLAIN, 16));
        mAdmin_backB.setIcon(new ImageIcon("resources/salida.png"));
        mAdminBottom.add(mAdmin_backB);
        mAdmin_backB.addActionListener(e -> {
            Credenciales credentials = new Credenciales();
            credentials.initialize();
            mAdminFrame.setVisible(false);
        });
    }

}
