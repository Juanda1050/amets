package quinta;

import java.awt.*;

import javax.swing.*;

import javax.swing.border.EmptyBorder;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import com.toedter.calendar.JDateChooser;
import tercera.VistaMA;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class GestionarVuelos {

    private JFrame gVuelosFrame;
    private JTextField textField;
    private JTextField textField_4;
    private JTable gVuelosTable;
    private JTextField textField_1;
    private JTextField textField_2;
    private JTextField textField_3;

    /**
     * Launch the application.
     */
    public void runFrame(){
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    GestionarVuelos window = new GestionarVuelos();
                    window.gVuelosFrame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public GestionarVuelos() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        gVuelosFrame = new JFrame();
        gVuelosFrame.setTitle("Gestionar Vuelos");
        gVuelosFrame.setExtendedState(Frame.MAXIMIZED_BOTH);
        gVuelosFrame.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\DELL\\OneDrive\\Documentos\\Tercer Semestre\\POO\\agenciaAmets\\resources\\amets.jpg"));
        gVuelosFrame.setBounds(100, 100, 1280, 720);
        gVuelosFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                int result = JOptionPane.showConfirmDialog(gVuelosFrame, "¿Desea cerrar el programa?", "Salir del programa", JOptionPane.YES_NO_OPTION);
                if (result == JOptionPane.YES_OPTION)
                {
                    gVuelosFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                }
                else if (result == JOptionPane.NO_OPTION)
                {
                    gVuelosFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                }
            }
        });

        JPanel gVuelosTop = new JPanel();
        gVuelosTop.setBorder(new EmptyBorder(20, 20, 20, 20));
        gVuelosFrame.getContentPane().add(gVuelosTop, BorderLayout.NORTH);
        gVuelosTop.setLayout(new BorderLayout(0, 0));

        JLabel lblAadirVuelo = new JLabel("A\u00F1adir Vuelo");
        lblAadirVuelo.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblAadirVuelo.setHorizontalAlignment(SwingConstants.LEFT);
        gVuelosTop.add(lblAadirVuelo, BorderLayout.WEST);

        JLabel lblNewLabel_1 = new JLabel("Vuelos");
        lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 18));
        gVuelosTop.add(lblNewLabel_1, BorderLayout.CENTER);

        JPanel gVuelosTop_left = new JPanel();
        gVuelosTop_left.setBorder(new EmptyBorder(20, 20, 20, 20));
        gVuelosFrame.getContentPane().add(gVuelosTop_left, BorderLayout.WEST);
        gVuelosTop_left.setLayout(new GridLayout(0, 2, 15, 30));

        JLabel lblNewLabel_2 = new JLabel("Origen");
        lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
        gVuelosTop_left.add(lblNewLabel_2);

        textField = new JTextField();
        textField.setFont(new Font("Tahoma", Font.PLAIN, 20));
        textField.setColumns(10);
        gVuelosTop_left.add(textField);

        JLabel lblNewLabel_4 = new JLabel("Destino");
        lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 16));
        gVuelosTop_left.add(lblNewLabel_4);

        JComboBox comboBox = new JComboBox();
        comboBox.setFont(new Font("Tahoma", Font.PLAIN, 16));
        gVuelosTop_left.add(comboBox);

        JLabel lblNewLabel_5 = new JLabel("Aerolinea");
        lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 16));
        gVuelosTop_left.add(lblNewLabel_5);

        textField_1 = new JTextField();
        textField_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
        gVuelosTop_left.add(textField_1);
        textField_1.setColumns(10);

        JLabel lblNewLabel_6 = new JLabel("Pasajeros");
        lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 16));
        gVuelosTop_left.add(lblNewLabel_6);

        textField_4 = new JTextField();
        textField_4.setFont(new Font("Tahoma", Font.PLAIN, 20));
        textField_4.setColumns(10);
        gVuelosTop_left.add(textField_4);

        JLabel lblNewLabel_7 = new JLabel("Clase");
        lblNewLabel_7.setFont(new Font("Tahoma", Font.PLAIN, 16));
        gVuelosTop_left.add(lblNewLabel_7);

        textField_2 = new JTextField();
        textField_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
        gVuelosTop_left.add(textField_2);
        textField_2.setColumns(10);

        JLabel lblNewLabel = new JLabel("Fecha de salida");
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
        gVuelosTop_left.add(lblNewLabel);

        JDateChooser dateChooser = new JDateChooser();
        gVuelosTop_left.add(dateChooser);

        JLabel lblNewLabel_3 = new JLabel("Fecha de llegada");
        lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 16));
        gVuelosTop_left.add(lblNewLabel_3);

        JDateChooser dateChooser_1 = new JDateChooser();
        gVuelosTop_left.add(dateChooser_1);

        JLabel lblNewLabel_8 = new JLabel("Precio");
        lblNewLabel_8.setFont(new Font("Tahoma", Font.PLAIN, 16));
        gVuelosTop_left.add(lblNewLabel_8);

        textField_3 = new JTextField();
        textField_3.setFont(new Font("Tahoma", Font.PLAIN, 20));
        gVuelosTop_left.add(textField_3);
        textField_3.setColumns(10);

        JButton gHoteles_añadirButton = new JButton("A\u00F1adir");
        gHoteles_añadirButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
        gVuelosTop_left.add(gHoteles_añadirButton);

        JButton gHoteles_limpiarButton = new JButton("Limpiar");
        gHoteles_limpiarButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
        gVuelosTop_left.add(gHoteles_limpiarButton);

        JPanel gVuelosMid = new JPanel();
        gVuelosMid.setBorder(new EmptyBorder(20, 20, 40, 20));
        gVuelosFrame.getContentPane().add(gVuelosMid, BorderLayout.CENTER);
        gVuelosMid.setLayout(new BorderLayout(0, 10));

        JScrollPane gVuelosSP = new JScrollPane();
        gVuelosSP.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        gVuelosMid.add(gVuelosSP, BorderLayout.CENTER);

        gVuelosTable = new JTable();
        gVuelosTable.setModel(new DefaultTableModel(
                new Object[][] {
                },
                new String[] {
                        "ID Vuelo", "Origen", "Destino", "Aerolinea", "Pasajeros", "Clase", "Salida", "Llegada", "Precio"
                }
        ));

        //Generando estilo de JTable
        JTableHeader tHeader = gVuelosTable.getTableHeader();
        tHeader.setPreferredSize(new Dimension(0, 25));
        tHeader.setBackground(Color.decode("#094293"));
        tHeader.setForeground(Color.white);
        tHeader.setFont(new Font("Tahome", Font.BOLD, 16));
        gVuelosTable.setFont(new Font("Tahome", Font.PLAIN, 14));
        gVuelosSP.setViewportView(gVuelosTable);

        JPanel gHotelesMid_bottom = new JPanel();
        gHotelesMid_bottom.setBorder(new EmptyBorder(0, 0, 20, 20));
        gVuelosMid.add(gHotelesMid_bottom, BorderLayout.SOUTH);
        gHotelesMid_bottom.setLayout(new GridLayout(0, 6, 20, 0));

        JButton gVuelos_editarButton = new JButton("Editar");
        gVuelos_editarButton.setVerticalAlignment(SwingConstants.TOP);
        gVuelos_editarButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
        gHotelesMid_bottom.add(gVuelos_editarButton);

        JButton gVuelos_eliminarButton = new JButton("Eliminar\r\n");
        gVuelos_eliminarButton.setVerticalAlignment(SwingConstants.TOP);
        gVuelos_eliminarButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
        gHotelesMid_bottom.add(gVuelos_eliminarButton);

        JPanel gHotelesBottom = new JPanel();
        gHotelesBottom.setBorder(new EmptyBorder(20, 20, 20, 20));
        gVuelosFrame.getContentPane().add(gHotelesBottom, BorderLayout.SOUTH);
        gHotelesBottom.setLayout(new BorderLayout(0, 0));

        JButton btnNewButton = new JButton("VOLVER");
        btnNewButton.setIcon(new ImageIcon("C:\\Users\\DELL\\OneDrive\\Documentos\\Tercer Semestre\\POO\\AmetsTravels\\resources\\left.png"));
        btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
        gHotelesBottom.add(btnNewButton, BorderLayout.EAST);
        btnNewButton.addActionListener(e -> {
            VistaMA maFrame = new VistaMA();
            maFrame.runFrame();
            gVuelosFrame.setVisible(false);
        });
    }

}