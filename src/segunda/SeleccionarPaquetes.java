package segunda;

import vista.RegistroUsuarios;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class SeleccionarPaquetes {

    private JFrame spFrame;
    private JTextField spNombreTF;
    private JTextField spDestinoTF;
    private JTextField spPlazasTF;
    private JTextField spPrecioTF;

    /**
     * Launch the application.
     */
    public void runFrame(){
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    SeleccionarPaquetes window = new SeleccionarPaquetes();
                    window.spFrame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public SeleccionarPaquetes() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        spFrame = new JFrame();
        spFrame.setTitle("Amets Travels");
        spFrame.setExtendedState(Frame.MAXIMIZED_BOTH);
        spFrame.setBounds(100, 100, 1280, 720);
        spFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                int result = JOptionPane.showConfirmDialog(spFrame, "¿Desea cerrar el programa?", "Salir del programa", JOptionPane.YES_NO_OPTION);
                if (result == JOptionPane.YES_OPTION)
                {
                    spFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                }
                else if (result == JOptionPane.NO_OPTION)
                {
                    spFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                }
            }
        });

        JPanel spTop = new JPanel();
        spTop.setBorder(new EmptyBorder(20, 20, 20, 20));
        spFrame.getContentPane().add(spTop, BorderLayout.NORTH);
        spTop.setLayout(new BorderLayout(0, 0));

        JLabel spLabel = new JLabel("Seleccione un paquete");
        spLabel.setHorizontalAlignment(SwingConstants.CENTER);
        spLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
        spTop.add(spLabel, BorderLayout.NORTH);

        JPanel spMid = new JPanel();
        spMid.setBorder(new EmptyBorder(20, 20, 20, 20));
        spFrame.getContentPane().add(spMid, BorderLayout.CENTER);
        spMid.setLayout(new BorderLayout(0, 0));

        JPanel spMidTop = new JPanel();
        spMidTop.setBorder(new EmptyBorder(10, 10, 10, 10));
        spMid.add(spMidTop, BorderLayout.NORTH);
        spMidTop.setLayout(new GridLayout(0, 4, 25, 0));

        JComboBox spDestinoCB = new JComboBox();
        spDestinoCB.setFont(new Font("Tahoma", Font.PLAIN, 16));
        spDestinoCB.setModel(new DefaultComboBoxModel(new String[] {"Destino"}));
        spDestinoCB.setToolTipText("");
        spMidTop.add(spDestinoCB);

        JComboBox spPaqueteCB = new JComboBox();
        spPaqueteCB.setFont(new Font("Tahoma", Font.PLAIN, 16));
        spPaqueteCB.setModel(new DefaultComboBoxModel(new String[] {"Paquete"}));
        spMidTop.add(spPaqueteCB);

        JPanel spMidCenter = new JPanel();
        spMidCenter.setBorder(new EmptyBorder(10, 10, 10, 10));
        spMid.add(spMidCenter, BorderLayout.CENTER);
        spMidCenter.setLayout(new GridLayout(0, 2, 0, 30));

        JLabel spNombreLbl = new JLabel("Nombre");
        spNombreLbl.setFont(new Font("Tahoma", Font.PLAIN, 16));
        spNombreLbl.setHorizontalAlignment(SwingConstants.CENTER);
        spMidCenter.add(spNombreLbl);

        spNombreTF = new JTextField();
        spNombreTF.setFont(new Font("Tahoma", Font.PLAIN, 20));
        spNombreTF.setHorizontalAlignment(SwingConstants.LEFT);
        spMidCenter.add(spNombreTF);
        spNombreTF.setColumns(10);

        JLabel spDestinoLbl = new JLabel("Destino");
        spDestinoLbl.setFont(new Font("Tahoma", Font.PLAIN, 16));
        spDestinoLbl.setHorizontalAlignment(SwingConstants.CENTER);
        spMidCenter.add(spDestinoLbl);

        spDestinoTF = new JTextField();
        spDestinoTF.setFont(new Font("Tahoma", Font.PLAIN, 20));
        spDestinoTF.setHorizontalAlignment(SwingConstants.LEFT);
        spMidCenter.add(spDestinoTF);
        spDestinoTF.setColumns(10);

        JLabel spDescripcionLbl = new JLabel("Descripci\u00F3n");
        spDescripcionLbl.setFont(new Font("Tahoma", Font.PLAIN, 16));
        spDescripcionLbl.setHorizontalAlignment(SwingConstants.CENTER);
        spMidCenter.add(spDescripcionLbl);

        JTextArea spDescripcionTA = new JTextArea();
        spDescripcionTA.setFont(new Font("Tahoma", Font.PLAIN, 16));
        spMidCenter.add(spDescripcionTA);

        JLabel spPlazasLbl = new JLabel("Plazas");
        spPlazasLbl.setFont(new Font("Tahoma", Font.PLAIN, 16));
        spPlazasLbl.setHorizontalAlignment(SwingConstants.CENTER);
        spMidCenter.add(spPlazasLbl);

        spPlazasTF = new JTextField();
        spPlazasTF.setFont(new Font("Tahoma", Font.PLAIN, 20));
        spPlazasTF.setHorizontalAlignment(SwingConstants.LEFT);
        spMidCenter.add(spPlazasTF);
        spPlazasTF.setColumns(10);

        JLabel spPrecioLbl = new JLabel("Precio");
        spPrecioLbl.setFont(new Font("Tahoma", Font.PLAIN, 16));
        spPrecioLbl.setHorizontalAlignment(SwingConstants.CENTER);
        spMidCenter.add(spPrecioLbl);

        spPrecioTF = new JTextField();
        spPrecioTF.setFont(new Font("Tahoma", Font.PLAIN, 16));
        spPrecioTF.setHorizontalAlignment(SwingConstants.LEFT);
        spMidCenter.add(spPrecioTF);
        spPrecioTF.setColumns(10);

        JPanel spBottom = new JPanel();
        spBottom.setBorder(new EmptyBorder(20, 20, 20, 20));
        spFrame.getContentPane().add(spBottom, BorderLayout.SOUTH);
        spBottom.setLayout(new GridLayout(0, 3, 20, 0));

        JButton spVolverBtn = new JButton("Volver");
        spVolverBtn.setFont(new Font("Tahoma", Font.PLAIN, 16));
        spBottom.add(spVolverBtn);
        spVolverBtn.addActionListener(e -> {
            RegistroUsuarios ruFrame = new RegistroUsuarios();
            ruFrame.runFrame();
            spFrame.setVisible(false);
        });

        JButton spMenuBtn = new JButton("Men\u00FA");
        spMenuBtn.setFont(new Font("Tahoma", Font.PLAIN, 16));
        spBottom.add(spMenuBtn);
        spMenuBtn.addActionListener(e -> {
            MenuPrincipal mpFrame = new MenuPrincipal();
            mpFrame.runFrame();
            spFrame.setVisible(false);
        });

        JButton spSiguienteBtn = new JButton("Siguiente");
        spSiguienteBtn.setFont(new Font("Tahoma", Font.PLAIN, 16));
        spBottom.add(spSiguienteBtn);
        spSiguienteBtn.addActionListener(e -> {
            SeleccionarVuelos svFrame = new SeleccionarVuelos();
            svFrame.runFrame();
            spFrame.setVisible(false);
        });
    }

}