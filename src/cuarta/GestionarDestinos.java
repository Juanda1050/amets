package cuarta;

import controlador.DestinoController;
import modelo.DestinoDAO;
import tercera.VistaMA;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class GestionarDestinos {

    public JButton gDestino_addB, gDestino_editB, gDestino_updateB, gDestino_deleteB, gDestino_saveB;
    private JFrame gDestinoFrame;
    public JTextField gDestino_idTF, gDestino_ciudadTF, gDestino_estadoTF, gDestino_paisTF;
    private JScrollPane gDestinoSP;
    public JTable gDestinoTable;

    public void runFrame(){
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    GestionarDestinos window = new GestionarDestinos();
                    DestinoDAO destinoDAO = new DestinoDAO();
                    DestinoController controller = new DestinoController(window, destinoDAO);
                    window.gDestinoFrame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public GestionarDestinos() {
        initialize();
    }

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
        gDestinoLeft.setLayout(new GridLayout(0, 2, 15, 100));

        JLabel gDestino_idL = new JLabel("ID Destino");
        gDestino_idL.setFont(new Font("Tahoma", Font.PLAIN, 16));
        gDestinoLeft.add(gDestino_idL);

        gDestino_idTF = new JTextField();
        gDestino_idTF.setFont(new Font("Tahoma", Font.PLAIN, 16));
        gDestino_idTF.setEditable(false);
        gDestinoLeft.add(gDestino_idTF);
        gDestino_idTF.setColumns(20);

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

        gDestino_addB = new JButton("Nuevo");
        gDestino_addB.setFont(new Font("Tahoma", Font.PLAIN, 16));
        gDestinoLeft.add(gDestino_addB);

        gDestino_saveB = new JButton("Guardar");
        gDestino_saveB.setFont(new Font("Tahoma", Font.PLAIN, 16));
        gDestinoLeft.add(gDestino_saveB);

        JPanel gDestinoMid = new JPanel();
        gDestinoMid.setBorder(new EmptyBorder(20, 20, 20, 20));
        gDestinoFrame.getContentPane().add(gDestinoMid, BorderLayout.CENTER);
        gDestinoMid.setLayout(new BorderLayout(0, 10));

        gDestinoSP = new JScrollPane();
        gDestinoSP.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        gDestinoMid.add(gDestinoSP, BorderLayout.CENTER);

        gDestinoTable = new JTable();
        gDestinoTable = new JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                return false;
            }
        };
        DefaultTableModel tModel = new DefaultTableModel();
        gDestinoTable.setModel(tModel);
        tModel.addColumn("ID Destino");
        tModel.addColumn("Ciudad");
        tModel.addColumn("Estado");
        tModel.addColumn("Pais");
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );
        gDestinoTable.getColumnModel().getColumn(0).setCellRenderer( centerRenderer );
        gDestinoTable.setRowHeight(50);

        //Generando estilo de JTable
        JTableHeader tHeader = gDestinoTable.getTableHeader();
        tHeader.setPreferredSize(new Dimension(0, 25));
        tHeader.setBackground(Color.decode("#094293"));
        tHeader.setForeground(Color.white);
        tHeader.setReorderingAllowed(false);
        tHeader.setFont(new Font("Tahome", Font.BOLD, 16));
        gDestinoTable.setFont(new Font("Tahome", Font.PLAIN, 16));
        gDestinoSP.setViewportView(gDestinoTable);

        //PANEL PARA BOTONES DEBAJO DE JTABLE
        JPanel gDestinoMid_B = new JPanel();
        gDestinoMid_B.setBorder(new EmptyBorder(0, 0, 20, 20));
        gDestinoMid.add(gDestinoMid_B, BorderLayout.SOUTH);
        gDestinoMid_B.setLayout(new GridLayout(0, 6, 20, 0));

        gDestino_editB = new JButton("Editar");
        gDestino_editB.setVerticalAlignment(SwingConstants.TOP);
        gDestino_editB.setFont(new Font("Tahoma", Font.PLAIN, 16));
        gDestinoMid_B.add(gDestino_editB);

        gDestino_updateB = new JButton("Actualizar");
        gDestino_updateB.setVerticalAlignment(SwingConstants.TOP);
        gDestino_updateB.setFont(new Font("Tahoma", Font.PLAIN, 16));
        gDestinoMid_B.add(gDestino_updateB);

        gDestino_deleteB = new JButton("Eliminar");
        gDestino_deleteB.setVerticalAlignment(SwingConstants.TOP);
        gDestino_deleteB.setFont(new Font("Tahoma", Font.PLAIN, 16));
        gDestinoMid_B.add(gDestino_deleteB);

        JPanel gDestinoBottom = new JPanel();
        gDestinoBottom.setBorder(new EmptyBorder(20, 20, 20, 20));
        gDestinoFrame.getContentPane().add(gDestinoBottom, BorderLayout.SOUTH);
        gDestinoBottom.setLayout(new BorderLayout(0, 0));

        JButton gDestinos_backB = new JButton("VOLVER");
        gDestinos_backB.setFont(new Font("Tahoma", Font.PLAIN, 16));
        gDestinoBottom.add(gDestinos_backB, BorderLayout.EAST);
        gDestinos_backB.addActionListener(e -> {
            VistaMA maFrame = new VistaMA();
            maFrame.runFrame();
            gDestinoFrame.setVisible(false);
        });
    }
}