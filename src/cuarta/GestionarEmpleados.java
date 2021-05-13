package cuarta;

import tercera.VistaMA;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.SwingConstants;
import javax.swing.DefaultComboBoxModel;
import javax.swing.SwingConstants;
import javax.swing.DefaultComboBoxModel;

public class GestionarEmpleados {

    private JFrame gestEmp_frame;
    private JTextField gestEmp_nombreTextField;
    private JTextField gestEmp_apellidoTextField;
    private JTextField gestEmp_contraTextField;
    private JTable gestEmp_table;

    /**
     * Launch the application.
     */
    public void runFrame(){
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    GestionarEmpleados window = new GestionarEmpleados();
                    window.gestEmp_frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public GestionarEmpleados() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        gestEmp_frame = new JFrame("Amets Travels");
        gestEmp_frame.setExtendedState(Frame.MAXIMIZED_BOTH);
        gestEmp_frame.setBounds(100, 100, 1280, 720);
        gestEmp_frame.getContentPane().setLayout(new BorderLayout(0, 0));
        gestEmp_frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                int result = JOptionPane.showConfirmDialog(gestEmp_frame, "¿Desea cerrar el programa?", "Salir del programa", JOptionPane.YES_NO_OPTION);
                if (result == JOptionPane.YES_OPTION)
                {
                    gestEmp_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                }
                else if (result == JOptionPane.NO_OPTION)
                {
                    gestEmp_frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                }
            }
        });

        JPanel gestEmp_centerPanel = new JPanel();
        gestEmp_centerPanel.setBorder(new EmptyBorder(0, 20, 20, 20));
        gestEmp_centerPanel.setBackground(Color.WHITE);
        gestEmp_frame.getContentPane().add(gestEmp_centerPanel, BorderLayout.CENTER);
        GridBagLayout gbl_gestEmp_centerPanel = new GridBagLayout();
        gbl_gestEmp_centerPanel.columnWidths = new int[]{33, 230, 328, 149, 117, 166, 94, 150, 62, 0};
        gbl_gestEmp_centerPanel.rowHeights = new int[]{0, 0, 125, 0, 0, 53, 48, 0, 0};
        gbl_gestEmp_centerPanel.columnWeights = new double[]{0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
        gbl_gestEmp_centerPanel.rowWeights = new double[]{0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
        gestEmp_centerPanel.setLayout(gbl_gestEmp_centerPanel);

        JPanel gestEmp_panel = new JPanel();
        gestEmp_panel.setBackground(Color.WHITE);
        gestEmp_panel.setBorder(new EmptyBorder(10, 10, 10, 10));
        GridBagConstraints gbc_gestEmp_panel = new GridBagConstraints();
        gbc_gestEmp_panel.gridwidth = 2;
        gbc_gestEmp_panel.gridheight = 4;
        gbc_gestEmp_panel.insets = new Insets(0, 0, 5, 5);
        gbc_gestEmp_panel.fill = GridBagConstraints.BOTH;
        gbc_gestEmp_panel.gridx = 1;
        gbc_gestEmp_panel.gridy = 2;
        gestEmp_centerPanel.add(gestEmp_panel, gbc_gestEmp_panel);
        GridBagLayout gbl_gestEmp_panel = new GridBagLayout();
        gbl_gestEmp_panel.columnWidths = new int[]{0, 0, 0, 0};
        gbl_gestEmp_panel.rowHeights = new int[]{0, 0, 0, 0, 22, 0, 0, 0, 0, 29, 0};
        gbl_gestEmp_panel.columnWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
        gbl_gestEmp_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
        gestEmp_panel.setLayout(gbl_gestEmp_panel);

        JLabel gestEmp_nombreLabel = new JLabel("Nombre(s):");
        gestEmp_nombreLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
        GridBagConstraints gbc_gestEmp_nombreLabel = new GridBagConstraints();
        gbc_gestEmp_nombreLabel.insets = new Insets(0, 0, 5, 5);
        gbc_gestEmp_nombreLabel.gridx = 0;
        gbc_gestEmp_nombreLabel.gridy = 1;
        gestEmp_panel.add(gestEmp_nombreLabel, gbc_gestEmp_nombreLabel);

        gestEmp_nombreTextField = new JTextField();
        GridBagConstraints gbc_gestEmp_nombreTextField = new GridBagConstraints();
        gbc_gestEmp_nombreTextField.insets = new Insets(0, 0, 5, 0);
        gbc_gestEmp_nombreTextField.fill = GridBagConstraints.HORIZONTAL;
        gbc_gestEmp_nombreTextField.gridx = 2;
        gbc_gestEmp_nombreTextField.gridy = 1;
        gestEmp_panel.add(gestEmp_nombreTextField, gbc_gestEmp_nombreTextField);
        gestEmp_nombreTextField.setColumns(10);

        JLabel gestEmp_apellidoLabel = new JLabel("Apellido(s):");
        gestEmp_apellidoLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
        GridBagConstraints gbc_gestEmp_apellidoLabel = new GridBagConstraints();
        gbc_gestEmp_apellidoLabel.insets = new Insets(0, 0, 5, 5);
        gbc_gestEmp_apellidoLabel.gridx = 0;
        gbc_gestEmp_apellidoLabel.gridy = 3;
        gestEmp_panel.add(gestEmp_apellidoLabel, gbc_gestEmp_apellidoLabel);

        gestEmp_apellidoTextField = new JTextField();
        gestEmp_apellidoTextField.setColumns(10);
        GridBagConstraints gbc_gestEmp_apellidoTextField = new GridBagConstraints();
        gbc_gestEmp_apellidoTextField.insets = new Insets(0, 0, 5, 0);
        gbc_gestEmp_apellidoTextField.fill = GridBagConstraints.HORIZONTAL;
        gbc_gestEmp_apellidoTextField.gridx = 2;
        gbc_gestEmp_apellidoTextField.gridy = 3;
        gestEmp_panel.add(gestEmp_apellidoTextField, gbc_gestEmp_apellidoTextField);

        JLabel gestEmp_contraLabel = new JLabel("Contrase\u00F1a:");
        gestEmp_contraLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
        GridBagConstraints gbc_gestEmp_contraLabel = new GridBagConstraints();
        gbc_gestEmp_contraLabel.insets = new Insets(0, 0, 5, 5);
        gbc_gestEmp_contraLabel.gridx = 0;
        gbc_gestEmp_contraLabel.gridy = 5;
        gestEmp_panel.add(gestEmp_contraLabel, gbc_gestEmp_contraLabel);

        gestEmp_contraTextField = new JTextField();
        gestEmp_contraTextField.setColumns(10);
        GridBagConstraints gbc_gestEmp_contraTextField = new GridBagConstraints();
        gbc_gestEmp_contraTextField.insets = new Insets(0, 0, 5, 0);
        gbc_gestEmp_contraTextField.fill = GridBagConstraints.HORIZONTAL;
        gbc_gestEmp_contraTextField.gridx = 2;
        gbc_gestEmp_contraTextField.gridy = 5;
        gestEmp_panel.add(gestEmp_contraTextField, gbc_gestEmp_contraTextField);

        JLabel gestEmp_turnoLabel = new JLabel("Turno:");
        gestEmp_turnoLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
        GridBagConstraints gbc_gestEmp_turnoLabel = new GridBagConstraints();
        gbc_gestEmp_turnoLabel.insets = new Insets(0, 0, 5, 5);
        gbc_gestEmp_turnoLabel.gridx = 0;
        gbc_gestEmp_turnoLabel.gridy = 7;
        gestEmp_panel.add(gestEmp_turnoLabel, gbc_gestEmp_turnoLabel);
        //a
        JComboBox gestEmp_turnoComboBox = new JComboBox();
        gestEmp_turnoComboBox.setFont(new Font("Tahoma", Font.PLAIN, 16));
        gestEmp_turnoComboBox.setModel(new DefaultComboBoxModel(new String[] {"Matutino", "Vespertino", "Completo"}));
        GridBagConstraints gbc_gestEmp_turnoComboBox = new GridBagConstraints();
        gbc_gestEmp_turnoComboBox.insets = new Insets(0, 0, 5, 0);
        gbc_gestEmp_turnoComboBox.fill = GridBagConstraints.HORIZONTAL;
        gbc_gestEmp_turnoComboBox.gridx = 2;
        gbc_gestEmp_turnoComboBox.gridy = 7;
        gestEmp_panel.add(gestEmp_turnoComboBox, gbc_gestEmp_turnoComboBox);

        JLabel gestEmp_puestoLabel = new JLabel("Puesto:");
        gestEmp_puestoLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
        GridBagConstraints gbc_gestEmp_puestoLabel = new GridBagConstraints();
        gbc_gestEmp_puestoLabel.insets = new Insets(0, 0, 0, 5);
        gbc_gestEmp_puestoLabel.gridx = 0;
        gbc_gestEmp_puestoLabel.gridy = 9;
        gestEmp_panel.add(gestEmp_puestoLabel, gbc_gestEmp_puestoLabel);

        JComboBox gestEmp_puestoComboBox = new JComboBox();
        gestEmp_puestoComboBox.setModel(new DefaultComboBoxModel(new String[] {"Empleado", "Supervisor"}));
        gestEmp_puestoComboBox.setFont(new Font("Tahoma", Font.PLAIN, 16));
        GridBagConstraints gbc_gestEmp_puestoComboBox = new GridBagConstraints();
        gbc_gestEmp_puestoComboBox.fill = GridBagConstraints.HORIZONTAL;
        gbc_gestEmp_puestoComboBox.gridx = 2;
        gbc_gestEmp_puestoComboBox.gridy = 9;
        gestEmp_panel.add(gestEmp_puestoComboBox, gbc_gestEmp_puestoComboBox);

        JScrollPane gestEmp_ScrollPane = new JScrollPane();
        gestEmp_ScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        GridBagConstraints gbc_gestEmp_ScrollPane = new GridBagConstraints();
        gbc_gestEmp_ScrollPane.gridwidth = 5;
        gbc_gestEmp_ScrollPane.gridheight = 4;
        gbc_gestEmp_ScrollPane.insets = new Insets(0, 0, 5, 5);
        gbc_gestEmp_ScrollPane.fill = GridBagConstraints.BOTH;
        gbc_gestEmp_ScrollPane.gridx = 3;
        gbc_gestEmp_ScrollPane.gridy = 2;
        gestEmp_centerPanel.add(gestEmp_ScrollPane, gbc_gestEmp_ScrollPane);

        gestEmp_table = new JTable();
        gestEmp_table.setModel(new DefaultTableModel(
                new Object[][] {
                        {null, null, null},
                        {null, null, null},
                },
                new String[] {
                        "ID empleado", "Nombre(s)", "Apellido(s)"
                }
        ));
        gestEmp_ScrollPane.setViewportView(gestEmp_table);

        JButton gestEmp_anadirButton = new JButton("A\u00F1adir");
        gestEmp_anadirButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
        GridBagConstraints gbc_gestEmp_anadirButton = new GridBagConstraints();
        gbc_gestEmp_anadirButton.anchor = GridBagConstraints.WEST;
        gbc_gestEmp_anadirButton.insets = new Insets(0, 0, 5, 5);
        gbc_gestEmp_anadirButton.gridx = 1;
        gbc_gestEmp_anadirButton.gridy = 6;
        gestEmp_centerPanel.add(gestEmp_anadirButton, gbc_gestEmp_anadirButton);

        JButton gestEmp_limpiarButton = new JButton("Limpiar");
        gestEmp_limpiarButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
        GridBagConstraints gbc_gestEmp_limpiarButton = new GridBagConstraints();
        gbc_gestEmp_limpiarButton.anchor = GridBagConstraints.EAST;
        gbc_gestEmp_limpiarButton.insets = new Insets(0, 0, 5, 5);
        gbc_gestEmp_limpiarButton.gridx = 2;
        gbc_gestEmp_limpiarButton.gridy = 6;
        gestEmp_centerPanel.add(gestEmp_limpiarButton, gbc_gestEmp_limpiarButton);

        JButton gestEmp_editarButton = new JButton("Editar");
        gestEmp_editarButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
        GridBagConstraints gbc_gestEmp_editarButton = new GridBagConstraints();
        gbc_gestEmp_editarButton.insets = new Insets(0, 0, 5, 5);
        gbc_gestEmp_editarButton.gridx = 6;
        gbc_gestEmp_editarButton.gridy = 6;
        gestEmp_centerPanel.add(gestEmp_editarButton, gbc_gestEmp_editarButton);

        JButton gestEmp_eliminarButton = new JButton("Eliminar");
        gestEmp_eliminarButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
        GridBagConstraints gbc_gestEmp_eliminarButton = new GridBagConstraints();
        gbc_gestEmp_eliminarButton.anchor = GridBagConstraints.EAST;
        gbc_gestEmp_eliminarButton.insets = new Insets(0, 0, 5, 5);
        gbc_gestEmp_eliminarButton.gridx = 7;
        gbc_gestEmp_eliminarButton.gridy = 6;
        gestEmp_centerPanel.add(gestEmp_eliminarButton, gbc_gestEmp_eliminarButton);

        JButton gestEmp_volverButton = new JButton("VOLVER");
        GridBagConstraints gbc_gestEmp_volverButton = new GridBagConstraints();
        gbc_gestEmp_volverButton.anchor = GridBagConstraints.NORTH;
        gbc_gestEmp_volverButton.gridwidth = 2;
        gbc_gestEmp_volverButton.insets = new Insets(0, 0, 0, 5);
        gbc_gestEmp_volverButton.gridx = 6;
        gbc_gestEmp_volverButton.gridy = 7;
        gestEmp_centerPanel.add(gestEmp_volverButton, gbc_gestEmp_volverButton);
        gestEmp_volverButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
        gestEmp_volverButton.addActionListener(e -> {
            VistaMA maFrame = new VistaMA();
            maFrame.runFrame();
            gestEmp_frame.setVisible(false);
        });

        JPanel gestEmp_topPanel = new JPanel();
        gestEmp_topPanel.setBackground(Color.WHITE);
        gestEmp_frame.getContentPane().add(gestEmp_topPanel, BorderLayout.NORTH);
        GridBagLayout gbl_gestEmp_topPanel = new GridBagLayout();
        gbl_gestEmp_topPanel.columnWidths = new int[]{56, 506, 177, 0, 0};
        gbl_gestEmp_topPanel.rowHeights = new int[]{22, 0};
        gbl_gestEmp_topPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
        gbl_gestEmp_topPanel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
        gestEmp_topPanel.setLayout(gbl_gestEmp_topPanel);

        JLabel gestEmp_anadirLabel = new JLabel("A\u00F1adir Empleado");
        gestEmp_anadirLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
        GridBagConstraints gbc_gestEmp_anadirLabel = new GridBagConstraints();
        gbc_gestEmp_anadirLabel.anchor = GridBagConstraints.NORTHWEST;
        gbc_gestEmp_anadirLabel.insets = new Insets(0, 0, 0, 5);
        gbc_gestEmp_anadirLabel.gridx = 1;
        gbc_gestEmp_anadirLabel.gridy = 0;
        gestEmp_topPanel.add(gestEmp_anadirLabel, gbc_gestEmp_anadirLabel);

        JLabel gestEmp_empleadosLabel = new JLabel("Empleados");
        gestEmp_empleadosLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
        GridBagConstraints gbc_gestEmp_empleadosLabel = new GridBagConstraints();
        gbc_gestEmp_empleadosLabel.insets = new Insets(0, 0, 0, 5);
        gbc_gestEmp_empleadosLabel.anchor = GridBagConstraints.WEST;
        gbc_gestEmp_empleadosLabel.gridx = 2;
        gbc_gestEmp_empleadosLabel.gridy = 0;
        gestEmp_topPanel.add(gestEmp_empleadosLabel, gbc_gestEmp_empleadosLabel);
    }

}