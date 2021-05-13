package cuarta;

import tercera.VistaMA;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class GestionarDestinos{

    private JFrame gestDest_frame;
    private JTextField gestDest_ciudadTextField;
    private JTextField gestDest_estadoTextField;
    private JTextField gestDest_paisTextField;
    private JTable gestDest_table;

    /**
     * Launch the application.
     */
    public void runFrame(){
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    GestionarDestinos window = new GestionarDestinos();
                    window.gestDest_frame.setVisible(true);
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
        gestDest_frame = new JFrame("Amets Travels");
        gestDest_frame.setBounds(100, 100, 1280, 720);
        gestDest_frame.setExtendedState(Frame.MAXIMIZED_BOTH);
        gestDest_frame.getContentPane().setLayout(new BorderLayout(0, 0));
        gestDest_frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                int result = JOptionPane.showConfirmDialog(gestDest_frame, "¿Desea cerrar el programa?", "Salir del programa", JOptionPane.YES_NO_OPTION);
                if (result == JOptionPane.YES_OPTION)
                {
                    gestDest_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                }
                else if (result == JOptionPane.NO_OPTION)
                {
                    gestDest_frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                }
            }
        });

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
                        {null, null, null},
                        {null, null, null},
                },
                new String[] {
                        "Ciudad", "Estado", "Pa\u00EDs"
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
            VistaMA maFrame = new VistaMA();
            maFrame.runFrame();
            gestDest_frame.setVisible(false);
        });

        JPanel gestDest_topPanel = new JPanel();
        gestDest_topPanel.setBackground(Color.WHITE);
        gestDest_frame.getContentPane().add(gestDest_topPanel, BorderLayout.NORTH);
        GridBagLayout gbl_gestDest_topPanel = new GridBagLayout();
        gbl_gestDest_topPanel.columnWidths = new int[]{56, 506, 177, 0, 0};
        gbl_gestDest_topPanel.rowHeights = new int[]{22, 0};
        gbl_gestDest_topPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
        gbl_gestDest_topPanel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
        gestDest_topPanel.setLayout(gbl_gestDest_topPanel);

        JLabel gestDest_anadirLabel = new JLabel("A\u00F1adir Destino");
        gestDest_anadirLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
        GridBagConstraints gbc_gestDest_anadirLabel = new GridBagConstraints();
        gbc_gestDest_anadirLabel.anchor = GridBagConstraints.NORTHWEST;
        gbc_gestDest_anadirLabel.insets = new Insets(0, 0, 0, 5);
        gbc_gestDest_anadirLabel.gridx = 1;
        gbc_gestDest_anadirLabel.gridy = 0;
        gestDest_topPanel.add(gestDest_anadirLabel, gbc_gestDest_anadirLabel);

        JLabel gestDest_destinosLabel = new JLabel("Destinos");
        gestDest_destinosLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
        GridBagConstraints gbc_gestDest_destinosLabel = new GridBagConstraints();
        gbc_gestDest_destinosLabel.insets = new Insets(0, 0, 0, 5);
        gbc_gestDest_destinosLabel.anchor = GridBagConstraints.WEST;
        gbc_gestDest_destinosLabel.gridx = 2;
        gbc_gestDest_destinosLabel.gridy = 0;
        gestDest_topPanel.add(gestDest_destinosLabel, gbc_gestDest_destinosLabel);
    }

}