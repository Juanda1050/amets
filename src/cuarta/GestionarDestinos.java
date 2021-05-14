package cuarta;

import java.awt.EventQueue;

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class GestionarDestinos {

    private JFrame gDestinoFrame;
    private JTextField gDestino_ciudadTF;
    private JTextField gDestino_estadoTF;
    private JTextField gDestino_paisTF;
    private JScrollPane gDestinoSP;
    private JTable gDestinoTable;

    /**
     * Launch the application.
     */
    public void runFrame(){
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    GestionarDestinos window = new GestionarDestinos();
                    window.gDestinoFrame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }


    /**
     * Create the application.
     */
    public GestionarDestinos() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        gDestinoFrame = new JFrame("Amets Travels");
        gDestinoFrame.setBounds(100, 100, 1280, 720);
        gDestinoFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gDestinoFrame.setExtendedState(Frame.MAXIMIZED_BOTH);
        gDestinoFrame.getContentPane().setLayout(new BorderLayout(0, 0));
        gDestinoFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                int result = JOptionPane.showConfirmDialog(gDestinoFrame, "¿Desea cerrar el programa?", "Salir del programa", JOptionPane.YES_NO_OPTION);
                if (result == JOptionPane.YES_OPTION) {
                    gDestinoFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                } else if (result == JOptionPane.NO_OPTION) {
                    gDestinoFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                }
            }
        });

        JPanel gDestinoTop = new JPanel();
        gDestinoTop.setBorder(new EmptyBorder(20, 20, 20, 20));
        gDestinoFrame.getContentPane().add(gDestinoTop, BorderLayout.NORTH);
        gDestinoTop.setLayout(new BorderLayout(0, 0));

        JLabel gDestino_addL = new JLabel("Añadir Destino");
        gDestino_addL.setFont(new Font("Tahoma", Font.BOLD, 18));
        gDestinoTop.add(gDestino_addL, BorderLayout.WEST);

        JLabel gDestinoL = new JLabel("Destinos");
        gDestinoL.setHorizontalAlignment(SwingConstants.CENTER);
        gDestinoL.setFont(new Font("Tahoma", Font.BOLD, 18));
        gDestinoTop.add(gDestinoL, BorderLayout.CENTER);

        JPanel gDestinoLeft = new JPanel();
        gDestinoLeft.setBorder(new EmptyBorder(20, 50, 50, 50));
        gDestinoFrame.getContentPane().add(gDestinoLeft, BorderLayout.WEST);
        gDestinoLeft.setLayout(new GridLayout(0, 2, 15, 120));

        JLabel gDestino_ciudadL = new JLabel("Ciudad");
        gDestino_ciudadL.setFont(new Font("Tahoma", Font.PLAIN, 16));
        gDestinoLeft.add(gDestino_ciudadL);

        gDestino_ciudadTF = new JTextField();
        gDestino_ciudadTF.setFont(new Font("Tahoma", Font.PLAIN, 16));
        gDestinoLeft.add(gDestino_ciudadTF);
        gDestino_ciudadTF.setColumns(20);

        JLabel gDestino_estadoL = new JLabel("Estado");
        gDestino_estadoL.setFont(new Font("Tahoma", Font.PLAIN, 16));
        gDestinoLeft.add(gDestino_estadoL);

        gDestino_estadoTF = new JTextField();
        gDestino_estadoTF.setFont(new Font("Tahoma", Font.PLAIN, 16));
        gDestino_estadoTF.setHorizontalAlignment(SwingConstants.LEFT);
        gDestinoLeft.add(gDestino_estadoTF);
        gDestino_estadoTF.setColumns(20);

        JLabel gDestino_paisL = new JLabel("Pais");
        gDestino_paisL.setFont(new Font("Tahoma", Font.PLAIN, 16));
        gDestinoLeft.add(gDestino_paisL);

        gDestino_paisTF = new JTextField();
        gDestino_paisTF.setFont(new Font("Tahoma", Font.PLAIN, 16));
        gDestinoLeft.add(gDestino_paisTF);
        gDestino_paisTF.setColumns(20);

        JButton gDestino_addB = new JButton("Añadir");
        gDestino_addB.setFont(new Font("Tahoma", Font.PLAIN, 16));
        gDestinoLeft.add(gDestino_addB);

        JButton gDestino_cleanB = new JButton("Limpiar");
        gDestino_cleanB.setFont(new Font("Tahoma", Font.PLAIN, 16));
        gDestinoLeft.add(gDestino_cleanB);

        JPanel gDestinoMid = new JPanel();
        gDestinoMid.setBorder(new EmptyBorder(20, 20, 20, 20));
        gDestinoFrame.getContentPane().add(gDestinoMid, BorderLayout.CENTER);
        gDestinoMid.setLayout(new BorderLayout(0, 10));

        gDestinoSP = new JScrollPane();
        gDestinoSP.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        gDestinoMid.add(gDestinoSP, BorderLayout.CENTER);

        gDestinoTable = new JTable();
        gDestinoTable.setModel(new DefaultTableModel(
                new Object[][]{
                },
                new String[]{
                        "ID Destino", "Ciudad", "Estado", "Pais"
                }
        ));

        //Generando estilo de JTable
        JTableHeader tHeader = gDestinoTable.getTableHeader();
        tHeader.setPreferredSize(new Dimension(0, 25));
        tHeader.setBackground(Color.decode("#094293"));
        tHeader.setForeground(Color.white);
        tHeader.setFont(new Font("Tahome", Font.BOLD, 16));
        gDestinoTable.setFont(new Font("Tahome", Font.PLAIN, 14));
        gDestinoSP.setViewportView(gDestinoTable);

        JPanel gDestinoMid_B = new JPanel();
        gDestinoMid_B.setBorder(new EmptyBorder(0, 0, 20, 20));
        gDestinoMid.add(gDestinoMid_B, BorderLayout.SOUTH);
        gDestinoMid_B.setLayout(new GridLayout(0, 6, 20, 0));

        JButton gDestino_editB = new JButton("Editar");
        gDestino_editB.setVerticalAlignment(SwingConstants.TOP);
        gDestino_editB.setFont(new Font("Tahoma", Font.PLAIN, 16));
        gDestinoMid_B.add(gDestino_editB);

        JButton gDestino_deleteB = new JButton("Eliminar\\r\\n");
        gDestino_deleteB.setVerticalAlignment(SwingConstants.TOP);
        gDestino_deleteB.setFont(new Font("Tahoma", Font.PLAIN, 16));
        gDestinoMid_B.add(gDestino_deleteB);

        JPanel gDestinoBottom = new JPanel();
        gDestinoBottom.setBorder(new EmptyBorder(20, 20, 20, 20));
        gDestinoFrame.getContentPane().add(gDestinoBottom, BorderLayout.SOUTH);
        gDestinoBottom.setLayout(new BorderLayout(0, 0));

        JButton btnNewButton = new JButton("VOLVER");
        btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
        gDestinoBottom.add(btnNewButton, BorderLayout.EAST);
    }
}