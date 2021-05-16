package segunda;

import Controlador.SelecPaqController;
import modelo.Paquetes;
import modelo.SelecPaqDAO;
import primera.Retorno;
import tercera.Ticket;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class SeleccionarPaquetes {

    private JFrame spFrame;
    private JTextField spNombreTF;
    private JTextField spDestinoTF;
    private JTextField spPlazasTF;
    private JTextField spPrecioTF;
    private JTextArea spDescripcionTA;

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

        SelecPaqDAO spDAO = new SelecPaqDAO();

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

        JComboBox spPaqueteCB = new JComboBox();
        spPaqueteCB.setEnabled(false);

        JComboBox<String> spDestinoCB = new JComboBox<String>(spDAO.listarDestinos().toArray(new String[0]));
        spDestinoCB.setFont(new Font("Tahoma", Font.PLAIN, 16));
        spDestinoCB.setToolTipText("");
        spMidTop.add(spDestinoCB);
        spDestinoCB.addActionListener (e -> {
            spPaqueteCB.removeAllItems();
            spDAO.listarPaquetes(spDestinoCB.getSelectedIndex()+1);
            for(int i=0;i<spDAO.getListaPaquete().size();i++){
                spPaqueteCB.addItem(spDAO.getListaPaquete().get(i));
            }
            spPaqueteCB.setEnabled(true);
        });

        spPaqueteCB.setFont(new Font("Tahoma", Font.PLAIN, 16));
        spMidTop.add(spPaqueteCB);
        spPaqueteCB.addActionListener (e -> {
            int paqueteCBindex = spPaqueteCB.getSelectedIndex() + 1;
            spNombreTF.setText(spDAO.getData(paqueteCBindex).get(0));
            spDescripcionTA.setText(spDAO.getData(paqueteCBindex).get(1));
            spPlazasTF.setText(spDAO.getData(paqueteCBindex).get(2));
            spPrecioTF.setText(spDAO.getData(paqueteCBindex).get(3));
            spDestinoTF.setText(spDAO.getData(paqueteCBindex).get(4));
        });

        JPanel spMidCenter = new JPanel();
        spMidCenter.setBorder(new EmptyBorder(10, 10, 10, 10));
        spMid.add(spMidCenter, BorderLayout.CENTER);
        spMidCenter.setLayout(new GridLayout(0, 2, 0, 30));

        JLabel spNombreLbl = new JLabel("Nombre");
        spNombreLbl.setFont(new Font("Tahoma", Font.PLAIN, 16));
        spNombreLbl.setHorizontalAlignment(SwingConstants.CENTER);
        spMidCenter.add(spNombreLbl);

        spNombreTF = new JTextField();
        spNombreTF.setEditable(false);
        spNombreTF.setFont(new Font("Tahoma", Font.PLAIN, 20));
        spNombreTF.setHorizontalAlignment(SwingConstants.LEFT);
        spMidCenter.add(spNombreTF);
        spNombreTF.setColumns(10);

        JLabel spDestinoLbl = new JLabel("Destino");
        spDestinoLbl.setFont(new Font("Tahoma", Font.PLAIN, 16));
        spDestinoLbl.setHorizontalAlignment(SwingConstants.CENTER);
        spMidCenter.add(spDestinoLbl);

        spDestinoTF = new JTextField();
        spDestinoTF.setEditable(false);
        spDestinoTF.setFont(new Font("Tahoma", Font.PLAIN, 20));
        spDestinoTF.setHorizontalAlignment(SwingConstants.LEFT);
        spMidCenter.add(spDestinoTF);
        spDestinoTF.setColumns(10);

        JLabel spDescripcionLbl = new JLabel("Descripci\u00F3n");
        spDescripcionLbl.setFont(new Font("Tahoma", Font.PLAIN, 16));
        spDescripcionLbl.setHorizontalAlignment(SwingConstants.CENTER);
        spMidCenter.add(spDescripcionLbl);

        spDescripcionTA = new JTextArea();
        spDescripcionTA.setEditable(false);
        spDescripcionTA.setFont(new Font("Tahoma", Font.PLAIN, 16));
        spMidCenter.add(spDescripcionTA);

        JLabel spPlazasLbl = new JLabel("Plazas");
        spPlazasLbl.setFont(new Font("Tahoma", Font.PLAIN, 16));
        spPlazasLbl.setHorizontalAlignment(SwingConstants.CENTER);
        spMidCenter.add(spPlazasLbl);

        spPlazasTF = new JTextField();
        spPlazasTF.setEditable(false);
        spPlazasTF.setFont(new Font("Tahoma", Font.PLAIN, 20));
        spPlazasTF.setHorizontalAlignment(SwingConstants.LEFT);
        spMidCenter.add(spPlazasTF);
        spPlazasTF.setColumns(10);

        JLabel spPrecioLbl = new JLabel("Precio");
        spPrecioLbl.setFont(new Font("Tahoma", Font.PLAIN, 16));
        spPrecioLbl.setHorizontalAlignment(SwingConstants.CENTER);
        spMidCenter.add(spPrecioLbl);

        spPrecioTF = new JTextField();
        spPrecioTF.setEditable(false);
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
            Retorno rtn = new Retorno();
            spFrame.setVisible(rtn.runReturn());
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