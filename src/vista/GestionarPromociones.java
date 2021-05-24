package vista;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class GestionarPromociones{

    private JFrame gestPromo_frame;
    private JTable gestPromo_table;
    private JTextField gestPromo_nombreTextField;
    private JTextField gestPromo_descuentoTextField;

    /**
     * Launch the application.
     */
    public void runFrame(){
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    GestionarPromociones window = new GestionarPromociones();
                    window.gestPromo_frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public GestionarPromociones() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        gestPromo_frame = new JFrame("Amets Travels");
        gestPromo_frame.setBounds(100, 100, 1280, 720);
        gestPromo_frame.setExtendedState(Frame.MAXIMIZED_BOTH);
        gestPromo_frame.getContentPane().setLayout(new BorderLayout(0, 0));
        gestPromo_frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                int result = JOptionPane.showConfirmDialog(gestPromo_frame, "¿Desea cerrar el programa?", "Salir del programa", JOptionPane.YES_NO_OPTION);
                if (result == JOptionPane.YES_OPTION)
                {
                    gestPromo_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                }
                else if (result == JOptionPane.NO_OPTION)
                {
                    gestPromo_frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                }
            }
        });

        JPanel gestPromo_centerPanel = new JPanel();
        gestPromo_centerPanel.setBorder(new EmptyBorder(0, 20, 20, 20));
        gestPromo_centerPanel.setBackground(Color.WHITE);
        gestPromo_frame.getContentPane().add(gestPromo_centerPanel, BorderLayout.CENTER);
        GridBagLayout gbl_gestPromo_centerPanel = new GridBagLayout();
        gbl_gestPromo_centerPanel.columnWidths = new int[]{33, 230, 328, 149, 117, 166, 94, 150, 62, 0};
        gbl_gestPromo_centerPanel.rowHeights = new int[]{0, 0, 125, 0, 0, 53, 48, 0, 0};
        gbl_gestPromo_centerPanel.columnWeights = new double[]{0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
        gbl_gestPromo_centerPanel.rowWeights = new double[]{0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
        gestPromo_centerPanel.setLayout(gbl_gestPromo_centerPanel);

        JPanel gestEmp_panel = new JPanel();
        gestEmp_panel.setBorder(new EmptyBorder(10, 10, 10, 10));
        gestEmp_panel.setBackground(Color.WHITE);
        GridBagConstraints gbc_gestEmp_panel = new GridBagConstraints();
        gbc_gestEmp_panel.gridheight = 4;
        gbc_gestEmp_panel.gridwidth = 2;
        gbc_gestEmp_panel.insets = new Insets(0, 0, 5, 5);
        gbc_gestEmp_panel.fill = GridBagConstraints.BOTH;
        gbc_gestEmp_panel.gridx = 1;
        gbc_gestEmp_panel.gridy = 2;
        gestPromo_centerPanel.add(gestEmp_panel, gbc_gestEmp_panel);
        GridBagLayout gbl_gestEmp_panel = new GridBagLayout();
        gbl_gestEmp_panel.columnWidths = new int[]{0, 0, 0, 0};
        gbl_gestEmp_panel.rowHeights = new int[]{0, 0, 0, 0, 22, 0, 0, 0, 0, 29, 0, 0, 0};
        gbl_gestEmp_panel.columnWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
        gbl_gestEmp_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
        gestEmp_panel.setLayout(gbl_gestEmp_panel);

        JLabel gestPromo_nombreLabel = new JLabel("Nombre:");
        gestPromo_nombreLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
        GridBagConstraints gbc_gestPromo_nombreLabel = new GridBagConstraints();
        gbc_gestPromo_nombreLabel.insets = new Insets(0, 0, 5, 5);
        gbc_gestPromo_nombreLabel.gridx = 0;
        gbc_gestPromo_nombreLabel.gridy = 1;
        gestEmp_panel.add(gestPromo_nombreLabel, gbc_gestPromo_nombreLabel);

        gestPromo_nombreTextField = new JTextField();
        gestPromo_nombreTextField.setColumns(10);
        GridBagConstraints gbc_gestPromo_nombreTextField = new GridBagConstraints();
        gbc_gestPromo_nombreTextField.fill = GridBagConstraints.HORIZONTAL;
        gbc_gestPromo_nombreTextField.insets = new Insets(0, 0, 5, 0);
        gbc_gestPromo_nombreTextField.gridx = 2;
        gbc_gestPromo_nombreTextField.gridy = 1;
        gestEmp_panel.add(gestPromo_nombreTextField, gbc_gestPromo_nombreTextField);

        JLabel gestPromo_hotelLabel = new JLabel("Hotel:");
        gestPromo_hotelLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
        GridBagConstraints gbc_gestPromo_hotelLabel = new GridBagConstraints();
        gbc_gestPromo_hotelLabel.insets = new Insets(0, 0, 5, 5);
        gbc_gestPromo_hotelLabel.gridx = 0;
        gbc_gestPromo_hotelLabel.gridy = 3;
        gestEmp_panel.add(gestPromo_hotelLabel, gbc_gestPromo_hotelLabel);

        JComboBox gestPromo_hotelComboBox = new JComboBox();
        GridBagConstraints gbc_gestPromo_hotelComboBox = new GridBagConstraints();
        gbc_gestPromo_hotelComboBox.insets = new Insets(0, 0, 5, 0);
        gbc_gestPromo_hotelComboBox.fill = GridBagConstraints.HORIZONTAL;
        gbc_gestPromo_hotelComboBox.gridx = 2;
        gbc_gestPromo_hotelComboBox.gridy = 3;
        gestEmp_panel.add(gestPromo_hotelComboBox, gbc_gestPromo_hotelComboBox);

        JLabel gestPromo_vueloLabel = new JLabel("Vuelo:");
        gestPromo_vueloLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
        GridBagConstraints gbc_gestPromo_vueloLabel = new GridBagConstraints();
        gbc_gestPromo_vueloLabel.insets = new Insets(0, 0, 5, 5);
        gbc_gestPromo_vueloLabel.gridx = 0;
        gbc_gestPromo_vueloLabel.gridy = 5;
        gestEmp_panel.add(gestPromo_vueloLabel, gbc_gestPromo_vueloLabel);

        JComboBox gestPromo_vueloComboBox = new JComboBox();
        GridBagConstraints gbc_gestPromo_vueloComboBox = new GridBagConstraints();
        gbc_gestPromo_vueloComboBox.insets = new Insets(0, 0, 5, 0);
        gbc_gestPromo_vueloComboBox.fill = GridBagConstraints.HORIZONTAL;
        gbc_gestPromo_vueloComboBox.gridx = 2;
        gbc_gestPromo_vueloComboBox.gridy = 5;
        gestEmp_panel.add(gestPromo_vueloComboBox, gbc_gestPromo_vueloComboBox);

        JLabel gestPromo_paqueteLabel = new JLabel("Paquete:");
        gestPromo_paqueteLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
        GridBagConstraints gbc_gestPromo_paqueteLabel = new GridBagConstraints();
        gbc_gestPromo_paqueteLabel.insets = new Insets(0, 0, 5, 5);
        gbc_gestPromo_paqueteLabel.gridx = 0;
        gbc_gestPromo_paqueteLabel.gridy = 7;
        gestEmp_panel.add(gestPromo_paqueteLabel, gbc_gestPromo_paqueteLabel);

        JComboBox gestPromo_paqueteComboBox = new JComboBox();
        GridBagConstraints gbc_gestPromo_paqueteComboBox = new GridBagConstraints();
        gbc_gestPromo_paqueteComboBox.fill = GridBagConstraints.HORIZONTAL;
        gbc_gestPromo_paqueteComboBox.insets = new Insets(0, 0, 5, 0);
        gbc_gestPromo_paqueteComboBox.gridx = 2;
        gbc_gestPromo_paqueteComboBox.gridy = 7;
        gestEmp_panel.add(gestPromo_paqueteComboBox, gbc_gestPromo_paqueteComboBox);

        JLabel gestPromo_descuentoLabel = new JLabel("Descuento:");
        gestPromo_descuentoLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
        GridBagConstraints gbc_gestPromo_descuentoLabel = new GridBagConstraints();
        gbc_gestPromo_descuentoLabel.insets = new Insets(0, 0, 5, 5);
        gbc_gestPromo_descuentoLabel.gridx = 0;
        gbc_gestPromo_descuentoLabel.gridy = 9;
        gestEmp_panel.add(gestPromo_descuentoLabel, gbc_gestPromo_descuentoLabel);

        gestPromo_descuentoTextField = new JTextField();
        gestPromo_descuentoTextField.setColumns(10);
        GridBagConstraints gbc_gestPromo_descuentoTextField = new GridBagConstraints();
        gbc_gestPromo_descuentoTextField.insets = new Insets(0, 0, 5, 0);
        gbc_gestPromo_descuentoTextField.fill = GridBagConstraints.HORIZONTAL;
        gbc_gestPromo_descuentoTextField.gridx = 2;
        gbc_gestPromo_descuentoTextField.gridy = 9;
        gestEmp_panel.add(gestPromo_descuentoTextField, gbc_gestPromo_descuentoTextField);

        JLabel gestPromo_descripcionLabel = new JLabel("Descripci\u00F3n:");
        gestPromo_descripcionLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
        GridBagConstraints gbc_gestPromo_descripcionLabel = new GridBagConstraints();
        gbc_gestPromo_descripcionLabel.insets = new Insets(0, 0, 0, 5);
        gbc_gestPromo_descripcionLabel.gridx = 0;
        gbc_gestPromo_descripcionLabel.gridy = 11;
        gestEmp_panel.add(gestPromo_descripcionLabel, gbc_gestPromo_descripcionLabel);

        JTextArea gestPromo_descripcionTextArea = new JTextArea();
        gestPromo_descripcionTextArea.setBackground(SystemColor.controlHighlight);
        GridBagConstraints gbc_gestPromo_descripcionTextArea = new GridBagConstraints();
        gbc_gestPromo_descripcionTextArea.fill = GridBagConstraints.BOTH;
        gbc_gestPromo_descripcionTextArea.gridx = 2;
        gbc_gestPromo_descripcionTextArea.gridy = 11;
        gestEmp_panel.add(gestPromo_descripcionTextArea, gbc_gestPromo_descripcionTextArea);

        JScrollPane gestPromo_ScrollPane = new JScrollPane();
        gestPromo_ScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        GridBagConstraints gbc_gestPromo_ScrollPane = new GridBagConstraints();
        gbc_gestPromo_ScrollPane.gridwidth = 5;
        gbc_gestPromo_ScrollPane.gridheight = 4;
        gbc_gestPromo_ScrollPane.insets = new Insets(0, 0, 5, 5);
        gbc_gestPromo_ScrollPane.fill = GridBagConstraints.BOTH;
        gbc_gestPromo_ScrollPane.gridx = 3;
        gbc_gestPromo_ScrollPane.gridy = 2;
        gestPromo_centerPanel.add(gestPromo_ScrollPane, gbc_gestPromo_ScrollPane);

        gestPromo_table = new JTable();
        gestPromo_table.setModel(new DefaultTableModel(
                new Object[][] {
                        {null, null, null, null, null, null},
                        {null, null, null, null, null, null},
                },
                new String[] {
                        "Nombre", "Hotel", "Vuelo", "Paquete", "Descuento", "Descripci\u00F3n"
                }
        ));
        gestPromo_ScrollPane.setViewportView(gestPromo_table);

        JButton gestPromo_anadirButton = new JButton("A\u00F1adir");
        gestPromo_anadirButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
        GridBagConstraints gbc_gestPromo_anadirButton = new GridBagConstraints();
        gbc_gestPromo_anadirButton.anchor = GridBagConstraints.WEST;
        gbc_gestPromo_anadirButton.insets = new Insets(0, 0, 5, 5);
        gbc_gestPromo_anadirButton.gridx = 1;
        gbc_gestPromo_anadirButton.gridy = 6;
        gestPromo_centerPanel.add(gestPromo_anadirButton, gbc_gestPromo_anadirButton);

        JButton gestPromo_limpiarButton = new JButton("Limpiar");
        gestPromo_limpiarButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
        GridBagConstraints gbc_gestPromo_limpiarButton = new GridBagConstraints();
        gbc_gestPromo_limpiarButton.anchor = GridBagConstraints.EAST;
        gbc_gestPromo_limpiarButton.insets = new Insets(0, 0, 5, 5);
        gbc_gestPromo_limpiarButton.gridx = 2;
        gbc_gestPromo_limpiarButton.gridy = 6;
        gestPromo_centerPanel.add(gestPromo_limpiarButton, gbc_gestPromo_limpiarButton);

        JButton gestPromo_editarButton = new JButton("Editar");
        gestPromo_editarButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
        GridBagConstraints gbc_gestPromo_editarButton = new GridBagConstraints();
        gbc_gestPromo_editarButton.insets = new Insets(0, 0, 5, 5);
        gbc_gestPromo_editarButton.gridx = 6;
        gbc_gestPromo_editarButton.gridy = 6;
        gestPromo_centerPanel.add(gestPromo_editarButton, gbc_gestPromo_editarButton);

        JButton gestPromo_eliminarButton = new JButton("Eliminar");
        gestPromo_eliminarButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
        GridBagConstraints gbc_gestPromo_eliminarButton = new GridBagConstraints();
        gbc_gestPromo_eliminarButton.anchor = GridBagConstraints.EAST;
        gbc_gestPromo_eliminarButton.insets = new Insets(0, 0, 5, 5);
        gbc_gestPromo_eliminarButton.gridx = 7;
        gbc_gestPromo_eliminarButton.gridy = 6;
        gestPromo_centerPanel.add(gestPromo_eliminarButton, gbc_gestPromo_eliminarButton);

        JButton gestPromo_volverButton = new JButton("VOLVER");
        GridBagConstraints gbc_gestPromo_volverButton = new GridBagConstraints();
        gbc_gestPromo_volverButton.anchor = GridBagConstraints.NORTH;
        gbc_gestPromo_volverButton.gridwidth = 2;
        gbc_gestPromo_volverButton.insets = new Insets(0, 0, 0, 5);
        gbc_gestPromo_volverButton.gridx = 6;
        gbc_gestPromo_volverButton.gridy = 7;
        gestPromo_centerPanel.add(gestPromo_volverButton, gbc_gestPromo_volverButton);
        gestPromo_volverButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
        gestPromo_volverButton.addActionListener(e -> {
            VistaMA maFrame = new VistaMA();
            maFrame.runFrame();
            gestPromo_frame.setVisible(false);
        });

        JPanel gestPromo_topPanel = new JPanel();
        gestPromo_topPanel.setBackground(Color.WHITE);
        gestPromo_frame.getContentPane().add(gestPromo_topPanel, BorderLayout.NORTH);
        GridBagLayout gbl_gestPromo_topPanel = new GridBagLayout();
        gbl_gestPromo_topPanel.columnWidths = new int[]{56, 506, 177, 0, 0};
        gbl_gestPromo_topPanel.rowHeights = new int[]{22, 0};
        gbl_gestPromo_topPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
        gbl_gestPromo_topPanel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
        gestPromo_topPanel.setLayout(gbl_gestPromo_topPanel);

        JLabel gestPromo_anadirLabel = new JLabel("A\u00F1adir Promoci\u00F3n");
        gestPromo_anadirLabel.setHorizontalAlignment(SwingConstants.LEFT);
        gestPromo_anadirLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
        GridBagConstraints gbc_gestPromo_anadirLabel = new GridBagConstraints();
        gbc_gestPromo_anadirLabel.anchor = GridBagConstraints.NORTHWEST;
        gbc_gestPromo_anadirLabel.insets = new Insets(0, 0, 0, 5);
        gbc_gestPromo_anadirLabel.gridx = 1;
        gbc_gestPromo_anadirLabel.gridy = 0;
        gestPromo_topPanel.add(gestPromo_anadirLabel, gbc_gestPromo_anadirLabel);

        JLabel gestPromo_promocionesLabel = new JLabel("Promociones");
        gestPromo_promocionesLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
        GridBagConstraints gbc_gestPromo_promocionesLabel = new GridBagConstraints();
        gbc_gestPromo_promocionesLabel.insets = new Insets(0, 0, 0, 5);
        gbc_gestPromo_promocionesLabel.anchor = GridBagConstraints.WEST;
        gbc_gestPromo_promocionesLabel.gridx = 2;
        gbc_gestPromo_promocionesLabel.gridy = 0;
        gestPromo_topPanel.add(gestPromo_promocionesLabel, gbc_gestPromo_promocionesLabel);
    }

}
