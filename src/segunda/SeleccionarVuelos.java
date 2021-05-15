package segunda;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import com.toedter.calendar.JDateChooser;
import primera.Retorno;
import tercera.VistaSH;

public class SeleccionarVuelos {

    private JFrame svFrame;
    private JTextField svNombreTF;
    private JTextField svPasajerosTF;
    private JTextField svPrecioTF;
    private JTextField svTipodeVueloTF;

    /**
     * Launch the application.
     */
    public void runFrame(){
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    SeleccionarVuelos window = new SeleccionarVuelos();
                    window.svFrame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public SeleccionarVuelos() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        svFrame = new JFrame();
        svFrame.setTitle("Amets Travels");
        svFrame.setExtendedState(Frame.MAXIMIZED_BOTH);
        svFrame.setBounds(100, 100, 1280, 720);
        svFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                int result = JOptionPane.showConfirmDialog(svFrame, "¿Desea cerrar el programa?", "Salir del programa", JOptionPane.YES_NO_OPTION);
                if (result == JOptionPane.YES_OPTION)
                {
                    svFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                }
                else if (result == JOptionPane.NO_OPTION)
                {
                    svFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                }
            }
        });

        JPanel svTop = new JPanel();
        svTop.setBorder(new EmptyBorder(20, 20, 20, 20));
        svFrame.getContentPane().add(svTop, BorderLayout.NORTH);
        svTop.setLayout(new BorderLayout(0, 0));

        JLabel svLabel = new JLabel("Seleccione un vuelo\r\n");
        svLabel.setHorizontalAlignment(SwingConstants.CENTER);
        svLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
        svTop.add(svLabel, BorderLayout.NORTH);

        JPanel svMid = new JPanel();
        svMid.setBorder(new EmptyBorder(20, 20, 20, 20));
        svFrame.getContentPane().add(svMid, BorderLayout.CENTER);
        svMid.setLayout(new BorderLayout(0, 0));

        JPanel svMidTop = new JPanel();
        svMidTop.setBorder(new EmptyBorder(10, 10, 10, 10));
        svMid.add(svMidTop, BorderLayout.NORTH);
        svMidTop.setLayout(new GridLayout(0, 4, 25, 0));

        JComboBox svOrigenCB = new JComboBox();
        svOrigenCB.setModel(new DefaultComboBoxModel(new String[] {"Origen"}));
        svOrigenCB.setToolTipText("");
        svOrigenCB.setFont(new Font("Tahoma", Font.PLAIN, 16));
        svMidTop.add(svOrigenCB);

        JComboBox svAerolinaCB = new JComboBox();
        svAerolinaCB.setModel(new DefaultComboBoxModel(new String[] {"Aerolinea"}));
        svAerolinaCB.setFont(new Font("Tahoma", Font.PLAIN, 16));
        svMidTop.add(svAerolinaCB);

        JPanel svMidCenter = new JPanel();
        svMidCenter.setBorder(new EmptyBorder(20, 20, 20, 20));
        svMid.add(svMidCenter, BorderLayout.CENTER);
        svMidCenter.setLayout(new GridLayout(0, 2, 0, 30));

        JLabel svNombreLbl = new JLabel("Nombre");
        svNombreLbl.setHorizontalAlignment(SwingConstants.CENTER);
        svNombreLbl.setFont(new Font("Tahoma", Font.PLAIN, 16));
        svMidCenter.add(svNombreLbl);

        svNombreTF = new JTextField();
        svNombreTF.setHorizontalAlignment(SwingConstants.LEFT);
        svNombreTF.setFont(new Font("Tahoma", Font.PLAIN, 20));
        svNombreTF.setColumns(10);
        svMidCenter.add(svNombreTF);

        JLabel svPasajerosLbl = new JLabel("Pasajeros");
        svPasajerosLbl.setHorizontalAlignment(SwingConstants.CENTER);
        svPasajerosLbl.setFont(new Font("Tahoma", Font.PLAIN, 16));
        svMidCenter.add(svPasajerosLbl);

        svPasajerosTF = new JTextField();
        svPasajerosTF.setHorizontalAlignment(SwingConstants.LEFT);
        svPasajerosTF.setFont(new Font("Tahoma", Font.PLAIN, 20));
        svPasajerosTF.setColumns(10);
        svMidCenter.add(svPasajerosTF);

        JLabel svTipodeVueloLbL = new JLabel("Tipo de Vuelo");
        svTipodeVueloLbL.setHorizontalAlignment(SwingConstants.CENTER);
        svTipodeVueloLbL.setFont(new Font("Tahoma", Font.PLAIN, 16));
        svMidCenter.add(svTipodeVueloLbL);

        svTipodeVueloTF = new JTextField();
        svTipodeVueloTF.setHorizontalAlignment(SwingConstants.LEFT);
        svTipodeVueloTF.setFont(new Font("Tahoma", Font.PLAIN, 20));
        svTipodeVueloTF.setColumns(10);
        svMidCenter.add(svTipodeVueloTF);

        JLabel svPrecioLbl = new JLabel("Precio");
        svPrecioLbl.setHorizontalAlignment(SwingConstants.CENTER);
        svPrecioLbl.setFont(new Font("Tahoma", Font.PLAIN, 16));
        svMidCenter.add(svPrecioLbl);

        svPrecioTF = new JTextField();
        svPrecioTF.setHorizontalAlignment(SwingConstants.LEFT);
        svPrecioTF.setFont(new Font("Tahoma", Font.PLAIN, 20));
        svPrecioTF.setColumns(10);
        svMidCenter.add(svPrecioTF);

        JPanel svMidSouth = new JPanel();
        svMidSouth.setBorder(new EmptyBorder(20, 20, 20, 20));
        svMid.add(svMidSouth, BorderLayout.SOUTH);
        svMidSouth.setLayout(new GridLayout(0, 4, 0, 0));

        JLabel svSalidaLbl = new JLabel("Hora de Salida");
        svSalidaLbl.setHorizontalAlignment(SwingConstants.CENTER);
        svSalidaLbl.setFont(new Font("Tahoma", Font.PLAIN, 16));
        svMidSouth.add(svSalidaLbl);

        JDateChooser svSalidaDC = new JDateChooser();
        svMidSouth.add(svSalidaDC);

        JLabel svLlegadaLbl = new JLabel("Hora de Llegada");
        svLlegadaLbl.setHorizontalAlignment(SwingConstants.CENTER);
        svLlegadaLbl.setFont(new Font("Tahoma", Font.PLAIN, 16));
        svMidSouth.add(svLlegadaLbl);

        JDateChooser svLlegadaDC = new JDateChooser();
        svMidSouth.add(svLlegadaDC);

        JPanel svBottom = new JPanel();
        svBottom.setBorder(new EmptyBorder(20, 20, 20, 20));
        svFrame.getContentPane().add(svBottom, BorderLayout.SOUTH);
        svBottom.setLayout(new GridLayout(0, 3, 20, 0));

        JButton svVolverBtn = new JButton("Volver");
        svVolverBtn.setFont(new Font("Tahoma", Font.PLAIN, 16));
        svBottom.add(svVolverBtn);
        svVolverBtn.addActionListener(e -> {
            SeleccionarPaquetes spFrame = new SeleccionarPaquetes();
            spFrame.runFrame();
            svFrame.setVisible(false);
        });

        JButton svMenuBtn = new JButton("Men\u00FA");
        svMenuBtn.setFont(new Font("Tahoma", Font.PLAIN, 16));
        svBottom.add(svMenuBtn);
        svMenuBtn.addActionListener(e -> {
            Retorno rtn = new Retorno();
            svFrame.setVisible(rtn.runReturn());
        });

        JButton svSiguienteBtn = new JButton("Siguiente");
        svSiguienteBtn.setFont(new Font("Tahoma", Font.PLAIN, 16));
        svSiguienteBtn.setActionCommand("");
        svBottom.add(svSiguienteBtn);
        svSiguienteBtn.addActionListener(e -> {
            VistaSH shFrame = new VistaSH();
            shFrame.runFrame();
            svFrame.setVisible(false);
        });
    }

}