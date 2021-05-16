package cuarta;

import controlador.DestinoController;
import modelo.DestinoDAO;
import tercera.VistaMA;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
<<<<<<< HEAD
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
=======
<<<<<<< HEAD
import javax.swing.table.JTableHeader;
=======
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
>>>>>>> Cuarta
>>>>>>> main
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

<<<<<<< HEAD
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
=======
        JPanel gestDest_centerPanel = new JPanel();
        gestDest_centerPanel.setBorder(new EmptyBorder(0, 20, 20, 20));
        gestDest_centerPanel.setBackground(Color.WHITE);
        gestDest_frame.getContentPane().add(gestDest_centerPanel, BorderLayout.CENTER);
        GridBagLayout gbl_gestDest_centerPanel = new GridBagLayout();
        gbl_gestDest_centerPanel.columnWidths = new int[]{33, 230, 328, 149, 117, 166, 94, 150, 62, 0};
        gbl_gestDest_centerPanel.rowHeights = new int[]{0, 0, 125, 0, 0, 53, 48, 0, 0};
        gbl_gestDest_centerPanel.columnWeights = new double[]{0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
        gbl_gestDest_centerPanel.rowWeights = new double[]{0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
        gestDest_centerPanel.setLayout(gbl_gestDest_centerPanel);

        JPanel gestDest_panel = new JPanel();
        gestDest_panel.setBackground(Color.WHITE);
        gestDest_panel.setBorder(new EmptyBorder(10, 10, 10, 10));
        GridBagConstraints gbc_gestDest_panel = new GridBagConstraints();
        gbc_gestDest_panel.gridwidth = 2;
        gbc_gestDest_panel.gridheight = 4;
        gbc_gestDest_panel.insets = new Insets(0, 0, 5, 5);
        gbc_gestDest_panel.fill = GridBagConstraints.BOTH;
        gbc_gestDest_panel.gridx = 1;
        gbc_gestDest_panel.gridy = 2;
        gestDest_centerPanel.add(gestDest_panel, gbc_gestDest_panel);
        GridBagLayout gbl_gestDest_panel = new GridBagLayout();
        gbl_gestDest_panel.columnWidths = new int[]{0, 0, 0, 0};
        gbl_gestDest_panel.rowHeights = new int[]{0, 0, 0, 0, 22, 0, 0, 0, 0, 29, 0};
        gbl_gestDest_panel.columnWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
        gbl_gestDest_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
        gestDest_panel.setLayout(gbl_gestDest_panel);

        JLabel gestDest_ciudadLabel = new JLabel("Ciudad:");
        gestDest_ciudadLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
        GridBagConstraints gbc_gestDest_ciudadLabel = new GridBagConstraints();
        gbc_gestDest_ciudadLabel.insets = new Insets(0, 0, 5, 5);
        gbc_gestDest_ciudadLabel.gridx = 0;
        gbc_gestDest_ciudadLabel.gridy = 1;
        gestDest_panel.add(gestDest_ciudadLabel, gbc_gestDest_ciudadLabel);

        gestDest_ciudadTextField = new JTextField();
        GridBagConstraints gbc_gestDest_ciudadTextField = new GridBagConstraints();
        gbc_gestDest_ciudadTextField.insets = new Insets(0, 0, 5, 0);
        gbc_gestDest_ciudadTextField.fill = GridBagConstraints.HORIZONTAL;
        gbc_gestDest_ciudadTextField.gridx = 2;
        gbc_gestDest_ciudadTextField.gridy = 1;
        gestDest_panel.add(gestDest_ciudadTextField, gbc_gestDest_ciudadTextField);
        gestDest_ciudadTextField.setColumns(10);

        JLabel gestDest_estadoLabel = new JLabel("Estado:");
        gestDest_estadoLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
        GridBagConstraints gbc_gestDest_estadoLabel = new GridBagConstraints();
        gbc_gestDest_estadoLabel.insets = new Insets(0, 0, 5, 5);
        gbc_gestDest_estadoLabel.gridx = 0;
        gbc_gestDest_estadoLabel.gridy = 3;
        gestDest_panel.add(gestDest_estadoLabel, gbc_gestDest_estadoLabel);

        gestDest_estadoTextField = new JTextField();
        gestDest_estadoTextField.setColumns(10);
        GridBagConstraints gbc_gestDest_estadoTextField = new GridBagConstraints();
        gbc_gestDest_estadoTextField.insets = new Insets(0, 0, 5, 0);
        gbc_gestDest_estadoTextField.fill = GridBagConstraints.HORIZONTAL;
        gbc_gestDest_estadoTextField.gridx = 2;
        gbc_gestDest_estadoTextField.gridy = 3;
        gestDest_panel.add(gestDest_estadoTextField, gbc_gestDest_estadoTextField);

        JLabel gestDest_paisLabel = new JLabel("Pa\u00EDs:");
        gestDest_paisLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
        GridBagConstraints gbc_gestDest_paisLabel = new GridBagConstraints();
        gbc_gestDest_paisLabel.insets = new Insets(0, 0, 5, 5);
        gbc_gestDest_paisLabel.gridx = 0;
        gbc_gestDest_paisLabel.gridy = 5;
        gestDest_panel.add(gestDest_paisLabel, gbc_gestDest_paisLabel);

        gestDest_paisTextField = new JTextField();
        gestDest_paisTextField.setColumns(10);
        GridBagConstraints gbc_gestDest_paisTextField = new GridBagConstraints();
        gbc_gestDest_paisTextField.insets = new Insets(0, 0, 5, 0);
        gbc_gestDest_paisTextField.fill = GridBagConstraints.HORIZONTAL;
        gbc_gestDest_paisTextField.gridx = 2;
        gbc_gestDest_paisTextField.gridy = 5;
        gestDest_panel.add(gestDest_paisTextField, gbc_gestDest_paisTextField);

        JScrollPane gestDest_ScrollPane = new JScrollPane();
        gestDest_ScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        GridBagConstraints gbc_gestDest_ScrollPane = new GridBagConstraints();
        gbc_gestDest_ScrollPane.gridwidth = 5;
        gbc_gestDest_ScrollPane.gridheight = 4;
        gbc_gestDest_ScrollPane.insets = new Insets(0, 0, 5, 5);
        gbc_gestDest_ScrollPane.fill = GridBagConstraints.BOTH;
        gbc_gestDest_ScrollPane.gridx = 3;
        gbc_gestDest_ScrollPane.gridy = 2;
        gestDest_centerPanel.add(gestDest_ScrollPane, gbc_gestDest_ScrollPane);

        gestDest_table = new JTable();
        gestDest_table.setModel(new DefaultTableModel(
                new Object[][] {

                },
                new String[] {
                        "ID de Destino", "Ciudad", "Estado", "Pa\u00EDs"
                }
        ));
        gestDest_ScrollPane.setViewportView(gestDest_table);

        JButton gestDest_anadirButton = new JButton("A\u00F1adir");
        gestDest_anadirButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
        GridBagConstraints gbc_gestDest_anadirButton = new GridBagConstraints();
        gbc_gestDest_anadirButton.anchor = GridBagConstraints.WEST;
        gbc_gestDest_anadirButton.insets = new Insets(0, 0, 5, 5);
        gbc_gestDest_anadirButton.gridx = 1;
        gbc_gestDest_anadirButton.gridy = 6;
        gestDest_centerPanel.add(gestDest_anadirButton, gbc_gestDest_anadirButton);

        JButton gestDest_limpiarButton = new JButton("Limpiar");
        gestDest_limpiarButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
        GridBagConstraints gbc_gestDest_limpiarButton = new GridBagConstraints();
        gbc_gestDest_limpiarButton.anchor = GridBagConstraints.EAST;
        gbc_gestDest_limpiarButton.insets = new Insets(0, 0, 5, 5);
        gbc_gestDest_limpiarButton.gridx = 2;
        gbc_gestDest_limpiarButton.gridy = 6;
        gestDest_centerPanel.add(gestDest_limpiarButton, gbc_gestDest_limpiarButton);


        JButton gestDest_editarButton = new JButton("Editar");
        gestDest_editarButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
        GridBagConstraints gbc_gestDest_editarButton = new GridBagConstraints();
        gbc_gestDest_editarButton.insets = new Insets(0, 0, 5, 5);
        gbc_gestDest_editarButton.gridx = 6;
        gbc_gestDest_editarButton.gridy = 6;
        gestDest_centerPanel.add(gestDest_editarButton, gbc_gestDest_editarButton);

        JButton gestDest_eliminarButton = new JButton("Eliminar");
        gestDest_eliminarButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
        GridBagConstraints gbc_gestDest_eliminarButton = new GridBagConstraints();
        gbc_gestDest_eliminarButton.anchor = GridBagConstraints.EAST;
        gbc_gestDest_eliminarButton.insets = new Insets(0, 0, 5, 5);
        gbc_gestDest_eliminarButton.gridx = 7;
        gbc_gestDest_eliminarButton.gridy = 6;
        gestDest_centerPanel.add(gestDest_eliminarButton, gbc_gestDest_eliminarButton);

        JButton gestDest_volverButton = new JButton("VOLVER");
        GridBagConstraints gbc_gestDest_volverButton = new GridBagConstraints();
        gbc_gestDest_volverButton.anchor = GridBagConstraints.NORTH;
        gbc_gestDest_volverButton.gridwidth = 2;
        gbc_gestDest_volverButton.insets = new Insets(0, 0, 0, 5);
        gbc_gestDest_volverButton.gridx = 6;
        gbc_gestDest_volverButton.gridy = 7;
        gestDest_centerPanel.add(gestDest_volverButton, gbc_gestDest_volverButton);
        gestDest_volverButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
        gestDest_volverButton.addActionListener(e -> {
>>>>>>> Cuarta
            VistaMA maFrame = new VistaMA();
            maFrame.runFrame();
            gDestinoFrame.setVisible(false);
        });
    }
}