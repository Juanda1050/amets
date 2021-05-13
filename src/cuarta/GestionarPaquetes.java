package cuarta;

import tercera.VistaMA;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class GestionarPaquetes{

    private JFrame gestPaq_frame;
    private JTable gestPaq_table;
    private JTextField gestPaq_nombreTextField;
    private JTextField gestPaq_pasajerosTextField;
    private JTextField gestPaq_precioTextField;

    /**
     * Launch the application.
     */
    public void runFrame(){
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    GestionarPaquetes window = new GestionarPaquetes();
                    window.gestPaq_frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public GestionarPaquetes() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        gestPaq_frame = new JFrame("Amets Travels");
        gestPaq_frame.setBounds(100, 100, 1280, 720);
        gestPaq_frame.setExtendedState(Frame.MAXIMIZED_BOTH);
        gestPaq_frame.getContentPane().setLayout(new BorderLayout(0, 0));
        gestPaq_frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                int result = JOptionPane.showConfirmDialog(gestPaq_frame, "¿Desea cerrar el programa?", "Salir del programa", JOptionPane.YES_NO_OPTION);
                if (result == JOptionPane.YES_OPTION)
                {
                    gestPaq_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                }
                else if (result == JOptionPane.NO_OPTION)
                {
                    gestPaq_frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                }
            }
        });

        JPanel gestPaq_centerPanel = new JPanel();
        gestPaq_centerPanel.setBorder(new EmptyBorder(0, 20, 20, 20));
        gestPaq_centerPanel.setBackground(Color.WHITE);
        gestPaq_frame.getContentPane().add(gestPaq_centerPanel, BorderLayout.CENTER);
        GridBagLayout gbl_gestPaq_centerPanel = new GridBagLayout();
        gbl_gestPaq_centerPanel.columnWidths = new int[]{33, 230, 328, 149, 117, 166, 94, 150, 62, 0};
        gbl_gestPaq_centerPanel.rowHeights = new int[]{0, 0, 125, 0, 0, 53, 48, 0, 0};
        gbl_gestPaq_centerPanel.columnWeights = new double[]{0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
        gbl_gestPaq_centerPanel.rowWeights = new double[]{0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
        gestPaq_centerPanel.setLayout(gbl_gestPaq_centerPanel);

        JPanel gestPaq_panel = new JPanel();
        gestPaq_panel.setBorder(new EmptyBorder(10, 10, 10, 10));
        gestPaq_panel.setBackground(Color.WHITE);
        GridBagConstraints gbc_gestPaq_panel = new GridBagConstraints();
        gbc_gestPaq_panel.gridheight = 4;
        gbc_gestPaq_panel.gridwidth = 2;
        gbc_gestPaq_panel.insets = new Insets(0, 0, 5, 5);
        gbc_gestPaq_panel.fill = GridBagConstraints.BOTH;
        gbc_gestPaq_panel.gridx = 1;
        gbc_gestPaq_panel.gridy = 2;
        gestPaq_centerPanel.add(gestPaq_panel, gbc_gestPaq_panel);
        GridBagLayout gbl_gestPaq_panel = new GridBagLayout();
        gbl_gestPaq_panel.columnWidths = new int[]{0, 0, 0, 0};
        gbl_gestPaq_panel.rowHeights = new int[]{0, 0, 0, 0, 22, 0, 0, 0, 0, 29, 0, 0, 0};
        gbl_gestPaq_panel.columnWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
        gbl_gestPaq_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
        gestPaq_panel.setLayout(gbl_gestPaq_panel);

        JLabel gestPaq_nombreLabel = new JLabel("Nombre:");
        gestPaq_nombreLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
        GridBagConstraints gbc_gestPaq_nombreLabel = new GridBagConstraints();
        gbc_gestPaq_nombreLabel.insets = new Insets(0, 0, 5, 5);
        gbc_gestPaq_nombreLabel.gridx = 0;
        gbc_gestPaq_nombreLabel.gridy = 1;
        gestPaq_panel.add(gestPaq_nombreLabel, gbc_gestPaq_nombreLabel);

        gestPaq_nombreTextField = new JTextField();
        gestPaq_nombreTextField.setColumns(10);
        GridBagConstraints gbc_gestPaq_nombreTextField = new GridBagConstraints();
        gbc_gestPaq_nombreTextField.fill = GridBagConstraints.HORIZONTAL;
        gbc_gestPaq_nombreTextField.insets = new Insets(0, 0, 5, 0);
        gbc_gestPaq_nombreTextField.gridx = 2;
        gbc_gestPaq_nombreTextField.gridy = 1;
        gestPaq_panel.add(gestPaq_nombreTextField, gbc_gestPaq_nombreTextField);

        JLabel gestPaq_pasajerosLabel = new JLabel("Pasajeros:");
        gestPaq_pasajerosLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
        GridBagConstraints gbc_gestPaq_pasajerosLabel = new GridBagConstraints();
        gbc_gestPaq_pasajerosLabel.insets = new Insets(0, 0, 5, 5);
        gbc_gestPaq_pasajerosLabel.gridx = 0;
        gbc_gestPaq_pasajerosLabel.gridy = 3;
        gestPaq_panel.add(gestPaq_pasajerosLabel, gbc_gestPaq_pasajerosLabel);

        gestPaq_pasajerosTextField = new JTextField();
        gestPaq_pasajerosTextField.setColumns(10);
        GridBagConstraints gbc_gestPaq_pasajerosTextField = new GridBagConstraints();
        gbc_gestPaq_pasajerosTextField.insets = new Insets(0, 0, 5, 0);
        gbc_gestPaq_pasajerosTextField.fill = GridBagConstraints.HORIZONTAL;
        gbc_gestPaq_pasajerosTextField.gridx = 2;
        gbc_gestPaq_pasajerosTextField.gridy = 3;
        gestPaq_panel.add(gestPaq_pasajerosTextField, gbc_gestPaq_pasajerosTextField);

        JLabel gestPaq_precioLabel = new JLabel("Precio:");
        gestPaq_precioLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
        GridBagConstraints gbc_gestPaq_precioLabel = new GridBagConstraints();
        gbc_gestPaq_precioLabel.insets = new Insets(0, 0, 5, 5);
        gbc_gestPaq_precioLabel.gridx = 0;
        gbc_gestPaq_precioLabel.gridy = 5;
        gestPaq_panel.add(gestPaq_precioLabel, gbc_gestPaq_precioLabel);

        gestPaq_precioTextField = new JTextField();
        gestPaq_precioTextField.setColumns(10);
        GridBagConstraints gbc_gestPaq_precioTextField = new GridBagConstraints();
        gbc_gestPaq_precioTextField.insets = new Insets(0, 0, 5, 0);
        gbc_gestPaq_precioTextField.fill = GridBagConstraints.HORIZONTAL;
        gbc_gestPaq_precioTextField.gridx = 2;
        gbc_gestPaq_precioTextField.gridy = 5;
        gestPaq_panel.add(gestPaq_precioTextField, gbc_gestPaq_precioTextField);

        JLabel gestPaq_descripcionLabel = new JLabel("Descripci\u00F3n:");
        gestPaq_descripcionLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
        GridBagConstraints gbc_gestPaq_descripcionLabel = new GridBagConstraints();
        gbc_gestPaq_descripcionLabel.insets = new Insets(0, 0, 5, 5);
        gbc_gestPaq_descripcionLabel.gridx = 0;
        gbc_gestPaq_descripcionLabel.gridy = 7;
        gestPaq_panel.add(gestPaq_descripcionLabel, gbc_gestPaq_descripcionLabel);

        JTextArea gestPaq_descripcionTextArea = new JTextArea();
        gestPaq_descripcionTextArea.setBackground(SystemColor.controlHighlight);
        GridBagConstraints gbc_gestPaq_descripcionTextArea = new GridBagConstraints();
        gbc_gestPaq_descripcionTextArea.gridheight = 3;
        gbc_gestPaq_descripcionTextArea.insets = new Insets(0, 0, 5, 0);
        gbc_gestPaq_descripcionTextArea.fill = GridBagConstraints.BOTH;
        gbc_gestPaq_descripcionTextArea.gridx = 2;
        gbc_gestPaq_descripcionTextArea.gridy = 7;
        gestPaq_panel.add(gestPaq_descripcionTextArea, gbc_gestPaq_descripcionTextArea);

        JScrollPane gestPaq_ScrollPane = new JScrollPane();
        gestPaq_ScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        GridBagConstraints gbc_gestPaq_ScrollPane = new GridBagConstraints();
        gbc_gestPaq_ScrollPane.gridwidth = 5;
        gbc_gestPaq_ScrollPane.gridheight = 4;
        gbc_gestPaq_ScrollPane.insets = new Insets(0, 0, 5, 5);
        gbc_gestPaq_ScrollPane.fill = GridBagConstraints.BOTH;
        gbc_gestPaq_ScrollPane.gridx = 3;
        gbc_gestPaq_ScrollPane.gridy = 2;
        gestPaq_centerPanel.add(gestPaq_ScrollPane, gbc_gestPaq_ScrollPane);

        gestPaq_table = new JTable();
        gestPaq_table.setModel(new DefaultTableModel(
                new Object[][] {
                        {null, null, null},
                        {null, null, null},
                },
                new String[] {
                        "Nombre", "Personas", "Precio"
                }
        ));
        gestPaq_ScrollPane.setViewportView(gestPaq_table);

        JButton gestPaq_anadirButton = new JButton("A\u00F1adir");
        gestPaq_anadirButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
        GridBagConstraints gbc_gestPaq_anadirButton = new GridBagConstraints();
        gbc_gestPaq_anadirButton.anchor = GridBagConstraints.WEST;
        gbc_gestPaq_anadirButton.insets = new Insets(0, 0, 5, 5);
        gbc_gestPaq_anadirButton.gridx = 1;
        gbc_gestPaq_anadirButton.gridy = 6;
        gestPaq_centerPanel.add(gestPaq_anadirButton, gbc_gestPaq_anadirButton);

        JButton gestPaq_limpiarButton = new JButton("Limpiar");
        gestPaq_limpiarButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
        GridBagConstraints gbc_gestPaq_limpiarButton = new GridBagConstraints();
        gbc_gestPaq_limpiarButton.anchor = GridBagConstraints.EAST;
        gbc_gestPaq_limpiarButton.insets = new Insets(0, 0, 5, 5);
        gbc_gestPaq_limpiarButton.gridx = 2;
        gbc_gestPaq_limpiarButton.gridy = 6;
        gestPaq_centerPanel.add(gestPaq_limpiarButton, gbc_gestPaq_limpiarButton);

        JButton gestPaq_editarButton = new JButton("Editar");
        gestPaq_editarButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
        GridBagConstraints gbc_gestPaq_editarButton = new GridBagConstraints();
        gbc_gestPaq_editarButton.insets = new Insets(0, 0, 5, 5);
        gbc_gestPaq_editarButton.gridx = 6;
        gbc_gestPaq_editarButton.gridy = 6;
        gestPaq_centerPanel.add(gestPaq_editarButton, gbc_gestPaq_editarButton);

        JButton gestPaq_eliminarButton = new JButton("Eliminar");
        gestPaq_eliminarButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
        GridBagConstraints gbc_gestPaq_eliminarButton = new GridBagConstraints();
        gbc_gestPaq_eliminarButton.anchor = GridBagConstraints.EAST;
        gbc_gestPaq_eliminarButton.insets = new Insets(0, 0, 5, 5);
        gbc_gestPaq_eliminarButton.gridx = 7;
        gbc_gestPaq_eliminarButton.gridy = 6;
        gestPaq_centerPanel.add(gestPaq_eliminarButton, gbc_gestPaq_eliminarButton);

        JButton gestPaq_volverButton = new JButton("VOLVER");
        GridBagConstraints gbc_gestPaq_volverButton = new GridBagConstraints();
        gbc_gestPaq_volverButton.anchor = GridBagConstraints.NORTH;
        gbc_gestPaq_volverButton.gridwidth = 2;
        gbc_gestPaq_volverButton.insets = new Insets(0, 0, 0, 5);
        gbc_gestPaq_volverButton.gridx = 6;
        gbc_gestPaq_volverButton.gridy = 7;
        gestPaq_centerPanel.add(gestPaq_volverButton, gbc_gestPaq_volverButton);
        gestPaq_volverButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
        gestPaq_volverButton.addActionListener(e -> {
            VistaMA maFrame = new VistaMA();
            maFrame.runFrame();
            gestPaq_frame.setVisible(false);
        });

        JPanel gestPaq_topPanel = new JPanel();
        gestPaq_topPanel.setBackground(Color.WHITE);
        gestPaq_frame.getContentPane().add(gestPaq_topPanel, BorderLayout.NORTH);
        GridBagLayout gbl_gestPaq_topPanel = new GridBagLayout();
        gbl_gestPaq_topPanel.columnWidths = new int[]{56, 506, 177, 0, 0};
        gbl_gestPaq_topPanel.rowHeights = new int[]{22, 0};
        gbl_gestPaq_topPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
        gbl_gestPaq_topPanel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
        gestPaq_topPanel.setLayout(gbl_gestPaq_topPanel);

        JLabel gestPaq_anadirLabel = new JLabel("A\u00F1adir Paquete");
        gestPaq_anadirLabel.setHorizontalAlignment(SwingConstants.LEFT);
        gestPaq_anadirLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
        GridBagConstraints gbc_gestPaq_anadirLabel = new GridBagConstraints();
        gbc_gestPaq_anadirLabel.anchor = GridBagConstraints.NORTHWEST;
        gbc_gestPaq_anadirLabel.insets = new Insets(0, 0, 0, 5);
        gbc_gestPaq_anadirLabel.gridx = 1;
        gbc_gestPaq_anadirLabel.gridy = 0;
        gestPaq_topPanel.add(gestPaq_anadirLabel, gbc_gestPaq_anadirLabel);

        JLabel gestPaq_paquetesLabel = new JLabel("Paquetes");
        gestPaq_paquetesLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
        GridBagConstraints gbc_gestPaq_paquetesLabel = new GridBagConstraints();
        gbc_gestPaq_paquetesLabel.insets = new Insets(0, 0, 0, 5);
        gbc_gestPaq_paquetesLabel.anchor = GridBagConstraints.WEST;
        gbc_gestPaq_paquetesLabel.gridx = 2;
        gbc_gestPaq_paquetesLabel.gridy = 0;
        gestPaq_topPanel.add(gestPaq_paquetesLabel, gbc_gestPaq_paquetesLabel);
    }

}
