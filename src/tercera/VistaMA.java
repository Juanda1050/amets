package tercera;

import quinta.GestionarHoteles;
import quinta.GestionarReportes;
import quinta.GestionarVentas;
import vista.GestionarVuelos;
import cuarta.GestionarDestinos;
import cuarta.GestionarEmpleados;
import cuarta.GestionarPaquetes;
import cuarta.GestionarPromociones;
import primera.Credenciales;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class VistaMA {

    private JFrame frame;

    /**
     * Launch the application.
     */
    public void runFrame(){
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    VistaMA window = new VistaMA();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public VistaMA() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 1280, 720);
        frame.setExtendedState(Frame.MAXIMIZED_BOTH);
        frame.getContentPane().setLayout(new BorderLayout(0, 0));
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                int result = JOptionPane.showConfirmDialog(frame, "¿Desea cerrar el programa?", "Salir del programa", JOptionPane.YES_NO_OPTION);
                if (result == JOptionPane.YES_OPTION)
                {
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                }
                else if (result == JOptionPane.NO_OPTION)
                {
                    frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                }
            }
        });

        JPanel Top = new JPanel();
        Top.setBorder(new EmptyBorder(20, 20, 20, 20));
        frame.getContentPane().add(Top, BorderLayout.NORTH);
        Top.setLayout(new BorderLayout(0, 0));

        JLabel maTituloLabel = new JLabel("Menu de Administrador");
        maTituloLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
        maTituloLabel.setHorizontalAlignment(SwingConstants.CENTER);
        Top.add(maTituloLabel, BorderLayout.CENTER);

        JLabel maSeleccionLabel = new JLabel("Seleccione lo que desea gestionar");
        maSeleccionLabel.setFont(new Font("Tahoma", Font.PLAIN, 19));
        Top.add(maSeleccionLabel, BorderLayout.SOUTH);

        JPanel Mid = new JPanel();
        Mid.setBorder(new EmptyBorder(20, 20, 20, 20));
        frame.getContentPane().add(Mid, BorderLayout.CENTER);
        Mid.setLayout(new GridLayout(0, 2, 20, 40));

        JButton maEmpleadosButton = new JButton("Empleados");
        maEmpleadosButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
        Mid.add(maEmpleadosButton);
        maEmpleadosButton.addActionListener(e -> {
            GestionarEmpleados geFrame = new GestionarEmpleados();
            geFrame.runFrame();
            frame.setVisible(false);
        });

        JButton maPromocionesButton = new JButton("Promociones");
        maPromocionesButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
        Mid.add(maPromocionesButton);
        maPromocionesButton.addActionListener(e -> {
            GestionarPromociones gpFrame = new GestionarPromociones();
            gpFrame.runFrame();
            frame.setVisible(false);
        });

        JButton maDestinosButton = new JButton("Destinos");
        maDestinosButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
        Mid.add(maDestinosButton);
        maDestinosButton.addActionListener(e -> {
            GestionarDestinos gdFrame = new GestionarDestinos();
            gdFrame.runFrame();
            frame.setVisible(false);
        });

        JButton maPaquetesButton = new JButton("Paquetes");
        maPaquetesButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
        Mid.add(maPaquetesButton);
        maPaquetesButton.addActionListener(e -> {
            GestionarPaquetes gpFrame = new GestionarPaquetes();
            gpFrame.runFrame();
            frame.setVisible(false);
        });

        JButton maHotelesButton = new JButton("Hoteles");
        maHotelesButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
        Mid.add(maHotelesButton);
        maHotelesButton.addActionListener(e -> {
            GestionarHoteles ghFrame = new GestionarHoteles();
            ghFrame.runFrame();
            frame.setVisible(false);
        });

        JButton maVuelosButton = new JButton("Vuelos");
        maVuelosButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
        Mid.add(maVuelosButton);
        maVuelosButton.addActionListener(e -> {
            GestionarVuelos gvFrame = new GestionarVuelos();
            gvFrame.runFrame();
            frame.setVisible(false);
        });

        JButton maVentasButton = new JButton("Ventas");
        maVentasButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
        Mid.add(maVentasButton);
        maVentasButton.addActionListener(e -> {
            GestionarVentas gvtFrame = new GestionarVentas();
            gvtFrame.runFrame();
            frame.setVisible(false);
        });

        JButton maReportesButton = new JButton("Reportes");
        maReportesButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
        Mid.add(maReportesButton);
        maReportesButton.addActionListener(e -> {
            GestionarReportes grFrame = new GestionarReportes();
            grFrame.runFrame();
            frame.setVisible(false);
        });

        JPanel East = new JPanel();
        East.setBorder(new EmptyBorder(20, 20, 20, 20));
        frame.getContentPane().add(East, BorderLayout.EAST);
        East.setLayout(new BorderLayout(0, 0));

        JLabel lblNewLabel = new JLabel("");
        lblNewLabel.setIcon(new ImageIcon("resources\\amets.jpg"));
        East.add(lblNewLabel, BorderLayout.NORTH);

        JPanel Bottom = new JPanel();
        Bottom.setBorder(new EmptyBorder(20, 1000, 20, 20));
        frame.getContentPane().add(Bottom, BorderLayout.SOUTH);
        Bottom.setLayout(new GridLayout(0, 1, 0, 0));

        JButton maVolverButton = new JButton("Salir");
        maVolverButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
        Bottom.add(maVolverButton);
        maVolverButton.addActionListener(e -> {
            Credenciales credentials = new Credenciales();
            credentials.runFrame();
            frame.setVisible(false);
        });
    }

}
