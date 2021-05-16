package vista;

import controlador.AerolineaController;
import modelo.AerolineaDAO;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class GestionarAerolinea {

    private JFrame gAerolineaFrame;
    public JTextField gAerolinea_nombreTF, gAerolinea_precioTF;
    public JComboBox gAerolinea_claseCB;
    public JButton gAerolinea_addB, gAerolinea_saveB, gAerolinea_editB, gAerolinea_deleteB;
    public JTable gAerolineaTable;

    /**
     * Launch the application.
     */

    public void runFrame(){
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    GestionarAerolinea window = new GestionarAerolinea();
                    AerolineaDAO dao = new AerolineaDAO();
                    AerolineaController controller = new AerolineaController(window, dao);
                    window.gAerolineaFrame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public GestionarAerolinea() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        gAerolineaFrame = new JFrame();
        gAerolineaFrame.setTitle("Gestionar Aerolineas");
        gAerolineaFrame.setExtendedState(Frame.MAXIMIZED_BOTH);
        gAerolineaFrame.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\DELL\\OneDrive\\Documentos\\Tercer Semestre\\POO\\agenciaAmets\\resources\\amets.jpg"));
        gAerolineaFrame.setBounds(100, 100, 1280, 720);
        gAerolineaFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                int result = JOptionPane.showConfirmDialog(gAerolineaFrame, "¿Desea cerrar el programa?", "Salir del programa", JOptionPane.YES_NO_OPTION);
                if (result == JOptionPane.YES_OPTION)
                {
                    gAerolineaFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                }
                else if (result == JOptionPane.NO_OPTION)
                {
                    gAerolineaFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                }
            }
        });

        JPanel gAerolineaTop = new JPanel();
        gAerolineaTop.setBorder(new EmptyBorder(20, 20, 20, 20));
        gAerolineaFrame.getContentPane().add(gAerolineaTop, BorderLayout.NORTH);
        gAerolineaTop.setLayout(new BorderLayout(0, 0));

        JLabel lblAadirVuelo = new JLabel("Agregar Aerolinea");
        lblAadirVuelo.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblAadirVuelo.setHorizontalAlignment(SwingConstants.LEFT);
        gAerolineaTop.add(lblAadirVuelo, BorderLayout.WEST);

        JLabel lblNewLabel_1 = new JLabel("Aerolineas");
        lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 18));
        gAerolineaTop.add(lblNewLabel_1, BorderLayout.CENTER);

        JPanel gAerolineaLeft = new JPanel();
        gAerolineaLeft.setBorder(new EmptyBorder(20, 20, 20, 20));
        gAerolineaFrame.getContentPane().add(gAerolineaLeft, BorderLayout.WEST);
        gAerolineaLeft.setLayout(new GridLayout(0, 2, 15, 30));

        JLabel gAerolinea_nombreL = new JLabel("Nombre");
        gAerolinea_nombreL.setFont(new Font("Tahoma", Font.PLAIN, 16));
        gAerolineaLeft.add(gAerolinea_nombreL);

        gAerolinea_nombreTF = new JTextField();
        gAerolinea_nombreTF.setFont(new Font("Tahoma", Font.PLAIN, 20));
        gAerolinea_nombreTF.setColumns(10);
        gAerolineaLeft.add(gAerolinea_nombreTF);

        JLabel gAerolinea_claseL= new JLabel("Clase");
        gAerolinea_claseL.setFont(new Font("Tahoma", Font.PLAIN, 16));
        gAerolineaLeft.add(gAerolinea_claseL);

        gAerolinea_claseCB = new JComboBox();
        gAerolinea_claseCB.setFont(new Font("Tahoma", Font.PLAIN, 16));
        gAerolineaLeft.add(gAerolinea_claseCB);

        JLabel gAerolinea_precioL = new JLabel("Precio");
        gAerolinea_precioL.setFont(new Font("Tahoma", Font.PLAIN, 16));
        gAerolineaLeft.add(gAerolinea_precioL);

        gAerolinea_precioTF = new JTextField();
        gAerolinea_precioTF.setFont(new Font("Tahoma", Font.PLAIN, 16));
        gAerolineaLeft.add(gAerolinea_precioTF);
        gAerolinea_precioTF.setColumns(10);

        gAerolinea_addB = new JButton("Nuevo");
        gAerolinea_addB.setFont(new Font("Tahoma", Font.PLAIN, 16));
        gAerolineaLeft.add(gAerolinea_addB);

        gAerolinea_saveB = new JButton("Guardar");
        gAerolinea_saveB.setFont(new Font("Tahoma", Font.PLAIN, 16));
        gAerolineaLeft.add(gAerolinea_saveB);

        JPanel gAerolineaMid = new JPanel();
        gAerolineaMid.setBorder(new EmptyBorder(20, 20, 40, 16));
        gAerolineaFrame.getContentPane().add(gAerolineaMid, BorderLayout.CENTER);
        gAerolineaMid.setLayout(new BorderLayout(0, 10));

        JScrollPane gVuelosSP = new JScrollPane();
        gVuelosSP.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        gAerolineaMid.add(gVuelosSP, BorderLayout.CENTER);

        gAerolineaTable = new JTable();
        gAerolineaTable = new JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                return false;
            }
        };
        DefaultTableModel tModel = new DefaultTableModel();
        gAerolineaTable.setModel(tModel);
        tModel.addColumn("ID Aerolinea");
        tModel.addColumn("Nombre");
        tModel.addColumn("Clase");
        tModel.addColumn("Precio");
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );
        gAerolineaTable.getColumnModel().getColumn(0).setCellRenderer( centerRenderer );
        gAerolineaTable.setRowHeight(50);
        
        //Generando estilo de JTable
        JTableHeader tHeader = gAerolineaTable.getTableHeader();
        tHeader.setPreferredSize(new Dimension(0, 25));
        tHeader.setBackground(Color.decode("#094293"));
        tHeader.setForeground(Color.white);
        tHeader.setFont(new Font("Tahome", Font.BOLD, 16));
        gAerolineaTable.setFont(new Font("Tahome", Font.PLAIN, 14));
        gVuelosSP.setViewportView(gAerolineaTable);

        JPanel gAerolineaMid_B = new JPanel();
        gAerolineaMid_B.setBorder(new EmptyBorder(0, 0, 20, 20));
        gAerolineaMid.add(gAerolineaMid_B, BorderLayout.SOUTH);
        gAerolineaMid_B.setLayout(new GridLayout(0, 6, 20, 0));

        gAerolinea_editB = new JButton("Editar");
        gAerolinea_editB.setVerticalAlignment(SwingConstants.TOP);
        gAerolinea_editB.setFont(new Font("Tahoma", Font.PLAIN, 16));
        gAerolineaMid_B.add(gAerolinea_editB);

        gAerolinea_deleteB = new JButton("Eliminar");
        gAerolinea_deleteB.setVerticalAlignment(SwingConstants.TOP);
        gAerolinea_deleteB.setFont(new Font("Tahoma", Font.PLAIN, 16));
        gAerolineaMid_B.add(gAerolinea_deleteB);

        JPanel gAerolineaBottom = new JPanel();
        gAerolineaBottom.setBorder(new EmptyBorder(20, 20, 20, 20));
        gAerolineaFrame.getContentPane().add(gAerolineaBottom, BorderLayout.SOUTH);
        gAerolineaBottom.setLayout(new BorderLayout(0, 0));

        JButton gAerolinea_backB = new JButton("VOLVER");
        gAerolinea_backB.setIcon(new ImageIcon("resources/left.png"));
        gAerolinea_backB.setFont(new Font("Tahoma", Font.PLAIN, 16));
        gAerolineaBottom.add(gAerolinea_backB, BorderLayout.EAST);
        gAerolinea_backB.addActionListener(e -> {
            GestionarVuelos gvFrame = new GestionarVuelos();
            gvFrame.runFrame();
            gAerolineaFrame.setVisible(false);
        });
    }

}