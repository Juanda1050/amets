package quinta;

import tercera.VistaMA;

import java.awt.*;

import javax.swing.*;

import javax.swing.border.EmptyBorder;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class GestionarHoteles {

    private JFrame gHotelesFrame;
    private JTextField textField;
    private JTextField textField_1;
    private JTextField textField_2;
    private JTextField textField_3;
    private JTextField textField_4;
    private JTable gHotelesTable;

    /**
     * Launch the application.
     */
    public void runFrame(){
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    GestionarHoteles window = new GestionarHoteles();
                    window.gHotelesFrame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public GestionarHoteles() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        gHotelesFrame = new JFrame();
        gHotelesFrame.setExtendedState(Frame.MAXIMIZED_BOTH);
        gHotelesFrame.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\DELL\\OneDrive\\Documentos\\Tercer Semestre\\POO\\AmetsTravels\\resources\\amets.jpg"));
        gHotelesFrame.setTitle("Gestionar Hoteles");
        gHotelesFrame.setBounds(100, 100, 1280, 720);
        gHotelesFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                int result = JOptionPane.showConfirmDialog(gHotelesFrame, "¿Desea cerrar el programa?", "Salir del programa", JOptionPane.YES_NO_OPTION);
                if (result == JOptionPane.YES_OPTION)
                {
                    gHotelesFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                }
                else if (result == JOptionPane.NO_OPTION)
                {
                    gHotelesFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                }
            }
        });

        JPanel gHotelesTop = new JPanel();
        gHotelesTop.setBorder(new EmptyBorder(20, 20, 20, 20));
        gHotelesFrame.getContentPane().add(gHotelesTop, BorderLayout.NORTH);
        gHotelesTop.setLayout(new BorderLayout(0, 0));

        JLabel lblNewLabel = new JLabel("A\u00F1adir Hotel");
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
        gHotelesTop.add(lblNewLabel, BorderLayout.WEST);

        JLabel lblNewLabel_1 = new JLabel("Hoteles");
        lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 18));
        gHotelesTop.add(lblNewLabel_1, BorderLayout.CENTER);

        JPanel gHotelesTop_left = new JPanel();
        gHotelesTop_left.setBorder(new EmptyBorder(20, 20, 20, 20));
        gHotelesFrame.getContentPane().add(gHotelesTop_left, BorderLayout.WEST);

        JLabel lblNewLabel_2 = new JLabel("Nombre");
        lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 16));

        textField = new JTextField();
        textField.setFont(new Font("Tahoma", Font.PLAIN, 20));
        textField.setColumns(10);

        JLabel lblNewLabel_7 = new JLabel("Destino");
        lblNewLabel_7.setFont(new Font("Tahoma", Font.PLAIN, 16));

        JComboBox comboBox2 = new JComboBox();
        comboBox2.setModel(new DefaultComboBoxModel(new String[] {"Seleccione"}));
        comboBox2.setFont(new Font("Tahoma", Font.PLAIN, 16));

        JLabel lblNewLabel_3 = new JLabel("Ubicacion");
        lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 16));

        textField_1 = new JTextField();
        textField_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
        textField_1.setColumns(10);

        JLabel lblNewLabel_2_1 = new JLabel("Clasificacion");
        lblNewLabel_2_1.setFont(new Font("Tahoma", Font.PLAIN, 16));

        textField_2 = new JTextField();
        textField_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
        textField_2.setColumns(10);

        JLabel lblNewLabel_3_1 = new JLabel("Huespedes");
        lblNewLabel_3_1.setFont(new Font("Tahoma", Font.PLAIN, 16));

        textField_3 = new JTextField();
        textField_3.setFont(new Font("Tahoma", Font.PLAIN, 20));
        textField_3.setColumns(10);

        JLabel lblNewLabel_4 = new JLabel("Regimen");
        lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 16));

        JComboBox comboBox = new JComboBox();
        comboBox.setModel(new DefaultComboBoxModel(new String[] {"Seleccione", "Media Pension", "Pension Completa"}));
        comboBox.setFont(new Font("Tahoma", Font.PLAIN, 16));

        JLabel lblNewLabel_5 = new JLabel("Tipo de habitacion");
        lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 16));

        JComboBox comboBox_1 = new JComboBox();
        comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"Seleccione", "Individual", "Doble", "Suite Ejecutiva", "Suite Presidencial"}));
        comboBox_1.setFont(new Font("Tahoma", Font.PLAIN, 16));

        JLabel lblNewLabel_6 = new JLabel("Precio");
        lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 16));

        textField_4 = new JTextField();
        textField_4.setFont(new Font("Tahoma", Font.PLAIN, 20));
        textField_4.setColumns(10);
        gHotelesTop_left.setLayout(new GridLayout(0, 2, 15, 50));
        gHotelesTop_left.add(lblNewLabel_2);
        gHotelesTop_left.add(textField);
        gHotelesTop_left.add(lblNewLabel_7);
        gHotelesTop_left.add(comboBox2);
        gHotelesTop_left.add(lblNewLabel_3);
        gHotelesTop_left.add(textField_1);
        gHotelesTop_left.add(lblNewLabel_2_1);
        gHotelesTop_left.add(textField_2);
        gHotelesTop_left.add(lblNewLabel_3_1);
        gHotelesTop_left.add(textField_3);
        gHotelesTop_left.add(lblNewLabel_4);
        gHotelesTop_left.add(comboBox);
        gHotelesTop_left.add(lblNewLabel_5);
        gHotelesTop_left.add(comboBox_1);
        gHotelesTop_left.add(lblNewLabel_6);
        gHotelesTop_left.add(textField_4);

        JButton gHoteles_añadirButton = new JButton("A\u00F1adir");
        gHoteles_añadirButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
        gHotelesTop_left.add(gHoteles_añadirButton);

        JButton gHoteles_limpiarButton = new JButton("Limpiar");
        gHoteles_limpiarButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
        gHotelesTop_left.add(gHoteles_limpiarButton);

        JPanel gHotelesMid = new JPanel();
        gHotelesMid.setBorder(new EmptyBorder(20, 20, 40, 20));
        gHotelesFrame.getContentPane().add(gHotelesMid, BorderLayout.CENTER);
        gHotelesMid.setLayout(new BorderLayout(0, 10));

        JScrollPane gHotelesSP = new JScrollPane();
        gHotelesSP.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        gHotelesMid.add(gHotelesSP, BorderLayout.CENTER);

        gHotelesTable = new JTable();
        gHotelesTable.setModel(new DefaultTableModel(
                new Object[][] {
                },
                new String[] {
                        "ID Hotel", "Nombre", "Ubicacion", "Clasificacion", "Huespedes", "Regimen", "Disponibilidad", "Habitacion", "Precio"
                }
        ));

        //Generando estilo de JTable
        JTableHeader tHeader = gHotelesTable.getTableHeader();
        tHeader.setPreferredSize(new Dimension(0, 25));
        tHeader.setBackground(Color.decode("#094293"));
        tHeader.setForeground(Color.white);
        tHeader.setFont(new Font("Tahome", Font.BOLD, 16));
        gHotelesTable.setFont(new Font("Tahome", Font.PLAIN, 14));
        gHotelesSP.setViewportView(gHotelesTable);

        JPanel gHotelesMid_bottom = new JPanel();
        gHotelesMid_bottom.setBorder(new EmptyBorder(0, 0, 20, 20));
        gHotelesMid.add(gHotelesMid_bottom, BorderLayout.SOUTH);
        gHotelesMid_bottom.setLayout(new GridLayout(0, 6, 20, 0));

        JButton gHoteles_editarButton = new JButton("Editar");
        gHoteles_editarButton.setVerticalAlignment(SwingConstants.TOP);
        gHoteles_editarButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
        gHotelesMid_bottom.add(gHoteles_editarButton);

        JButton btnNewButton_2 = new JButton("Eliminar\r\n");
        btnNewButton_2.setVerticalAlignment(SwingConstants.TOP);
        btnNewButton_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
        gHotelesMid_bottom.add(btnNewButton_2);

        JPanel gHotelesBottom = new JPanel();
        gHotelesBottom.setBorder(new EmptyBorder(20, 20, 20, 20));
        gHotelesFrame.getContentPane().add(gHotelesBottom, BorderLayout.SOUTH);
        gHotelesBottom.setLayout(new BorderLayout(0, 0));

        JButton btnNewButton = new JButton("VOLVER");
        btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
        btnNewButton.setIcon(new ImageIcon("C:\\Users\\DELL\\OneDrive\\Documentos\\Tercer Semestre\\POO\\AmetsTravels\\resources\\left.png"));
        gHotelesBottom.add(btnNewButton, BorderLayout.EAST);
        btnNewButton.addActionListener(e -> {
            VistaMA maFrame = new VistaMA();
            maFrame.runFrame();
            gHotelesFrame.setVisible(false);
        });
    }

}
