package vista;

import java.awt.*;

import javax.swing.*;

import javax.swing.border.EmptyBorder;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Date;

import tercera.VistaMA;

import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class GestionarVuelos {

    private JFrame gVuelosFrame;
    public JTextField gVuelos_idTF, gVuelos_origenTF, gVuelos_aerolineaTF, gVuelos_claseTF, gVuelos_precioTF;
    public JButton gVuelos_addB, gVuelos_saveB, gVuelos_editB, gVuelos_updateB, gVuelos_deleteB;
    private JTable gVuelosTable;

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

    public GestionarVuelos() {
        initialize();
    }

    private void initialize() {
        gVuelosFrame = new JFrame();
        gVuelosFrame.setTitle("Gestionar Vuelos");
        gVuelosFrame.setExtendedState(Frame.MAXIMIZED_BOTH);
        gVuelosFrame.setIconImage(Toolkit.getDefaultToolkit().getImage("resources/amets.jpg"));
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

        JLabel gVuelos_addL = new JLabel("Agregar Vuelo");
        gVuelos_addL.setFont(new Font("Tahoma", Font.BOLD, 18));
        gVuelos_addL.setHorizontalAlignment(SwingConstants.LEFT);
        gVuelosTop.add(gVuelos_addL, BorderLayout.WEST);

        JLabel gVuelosL = new JLabel("Vuelos");
        gVuelosL.setHorizontalAlignment(SwingConstants.CENTER);
        gVuelosL.setFont(new Font("Tahoma", Font.BOLD, 18));
        gVuelosTop.add(gVuelosL, BorderLayout.CENTER);

        JPanel gVuelosLeft = new JPanel();
        gVuelosLeft.setBorder(new EmptyBorder(20, 20, 20, 20));
        gVuelosFrame.getContentPane().add(gVuelosLeft, BorderLayout.WEST);
        gVuelosLeft.setLayout(new GridLayout(0, 2, 15, 30));

        JLabel gVuelos_idL = new JLabel("ID Vuelo");
        gVuelos_idL.setFont(new Font("Tahoma", Font.PLAIN, 16));
        gVuelosLeft.add(gVuelos_idL);

        gVuelos_idTF = new JTextField();
        gVuelos_idTF.setFont(new Font("Tahoma", Font.PLAIN, 16));
        gVuelos_idTF.setEditable(false);
        gVuelosLeft.add(gVuelos_idTF);
        gVuelos_idTF.setColumns(20);

        JLabel gVuelos_origenL = new JLabel("Origen");
        gVuelos_origenL.setFont(new Font("Tahoma", Font.PLAIN, 16));
        gVuelosLeft.add(gVuelos_origenL);

        gVuelos_origenTF = new JTextField();
        gVuelos_origenTF.setFont(new Font("Tahoma", Font.PLAIN, 16));
        gVuelos_origenTF.setColumns(10);
        gVuelosLeft.add(gVuelos_origenTF);

        JLabel gVuelos_destinoL = new JLabel("Destino");
        gVuelos_destinoL.setFont(new Font("Tahoma", Font.PLAIN, 16));
        gVuelosLeft.add(gVuelos_destinoL);

        JComboBox gVuelos_destinoCB = new JComboBox();
        gVuelos_destinoCB.setFont(new Font("Tahoma", Font.PLAIN, 16));
        gVuelosLeft.add(gVuelos_destinoCB);

        JLabel gVuelos_aerolineaL = new JLabel("Aerolinea");
        gVuelos_aerolineaL.setFont(new Font("Tahoma", Font.PLAIN, 16));
        gVuelosLeft.add(gVuelos_aerolineaL);

        gVuelos_aerolineaTF = new JTextField();
        gVuelos_aerolineaTF.setFont(new Font("Tahoma", Font.PLAIN, 16));
        gVuelosLeft.add(gVuelos_aerolineaTF);
        gVuelos_aerolineaTF.setColumns(10);

        JLabel gVuelos_claseL = new JLabel("Clase");
        gVuelos_claseL.setFont(new Font("Tahoma", Font.PLAIN, 16));
        gVuelosLeft.add(gVuelos_claseL);

        gVuelos_claseTF = new JTextField();
        gVuelos_claseTF.setFont(new Font("Tahoma", Font.PLAIN, 20));
        gVuelosLeft.add(gVuelos_claseTF);
        gVuelos_claseTF.setColumns(10);

        JLabel gVuelos_salidaL = new JLabel("Fecha de salida");
        gVuelos_salidaL.setFont(new Font("Tahoma", Font.PLAIN, 16));
        gVuelosLeft.add(gVuelos_salidaL);

        Date date = new Date();
        JSpinner gVuelos_salidaJS = new JSpinner( new SpinnerDateModel());
        JSpinner.DateEditor gVuelos_salidaE = new JSpinner.DateEditor(gVuelos_salidaJS, "yyyy-MM-dd HH:mm:ss");
        gVuelos_salidaJS.setEditor(gVuelos_salidaE);
        gVuelos_salidaJS.setValue(date);
        gVuelosLeft.add(gVuelos_salidaJS);

        JLabel gVuelos_llegadaL = new JLabel("Fecha de llegada");
        gVuelos_llegadaL.setFont(new Font("Tahoma", Font.PLAIN, 16));
        gVuelosLeft.add(gVuelos_llegadaL);

        JSpinner gVuelos_llegadaJS = new JSpinner( new SpinnerDateModel());
        JSpinner.DateEditor gVuelos_llegadaE = new JSpinner.DateEditor(gVuelos_llegadaJS, "yyyy-MM-dd HH:mm:ss");
        gVuelos_llegadaJS.setEditor(gVuelos_llegadaE);
        gVuelos_llegadaJS.setValue(date);
        gVuelosLeft.add(gVuelos_llegadaJS);

        JLabel gVuelos_precioL = new JLabel("Precio");
        gVuelos_precioL.setFont(new Font("Tahoma", Font.PLAIN, 16));
        gVuelosLeft.add(gVuelos_precioL);

        gVuelos_precioTF = new JTextField();
        gVuelos_precioTF.setFont(new Font("Tahoma", Font.PLAIN, 20));
        gVuelosLeft.add(gVuelos_precioTF);
        gVuelos_precioTF.setColumns(10);

        gVuelos_addB = new JButton("Nuevo");
        gVuelos_addB.setFont(new Font("Tahoma", Font.PLAIN, 16));
        gVuelosLeft.add(gVuelos_addB);

        gVuelos_saveB = new JButton("Guardar");
        gVuelos_saveB.setFont(new Font("Tahoma", Font.PLAIN, 16));
        gVuelosLeft.add(gVuelos_saveB);

        JPanel gVuelosMid = new JPanel();
        gVuelosMid.setBorder(new EmptyBorder(20, 20, 40, 20));
        gVuelosFrame.getContentPane().add(gVuelosMid, BorderLayout.CENTER);
        gVuelosMid.setLayout(new BorderLayout(0, 10));

        JScrollPane gVuelosSP = new JScrollPane();
        gVuelosSP.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        gVuelosMid.add(gVuelosSP, BorderLayout.CENTER);

        gVuelosTable = new JTable();
        gVuelosTable = new JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                return false;
            }
        };
        DefaultTableModel tModel = new DefaultTableModel();
        gVuelosTable.setModel(tModel);
        tModel.addColumn("ID Vuelo");
        tModel.addColumn("Origen");
        tModel.addColumn("Destino");
        tModel.addColumn("Aerolinea");
        tModel.addColumn("Clase");
        tModel.addColumn("Salida");
        tModel.addColumn("Llegada");
        tModel.addColumn("Precio");
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );
        gVuelosTable.getColumnModel().getColumn(0).setCellRenderer( centerRenderer );
        gVuelosTable.setRowHeight(50);

        //Generando estilo de JTable
        JTableHeader tHeader = gVuelosTable.getTableHeader();
        tHeader.setPreferredSize(new Dimension(0, 25));
        tHeader.setBackground(Color.decode("#094293"));
        tHeader.setForeground(Color.white);
        tHeader.setFont(new Font("Tahome", Font.BOLD, 16));
        gVuelosTable.setFont(new Font("Tahome", Font.PLAIN, 14));
        gVuelosSP.setViewportView(gVuelosTable);

        JPanel gVuelosMid_B = new JPanel();
        gVuelosMid_B.setBorder(new EmptyBorder(0, 0, 20, 20));
        gVuelosMid.add(gVuelosMid_B, BorderLayout.SOUTH);
        gVuelosMid_B.setLayout(new GridLayout(0, 6, 20, 0));

        gVuelos_editB = new JButton("Editar");
        gVuelos_editB.setVerticalAlignment(SwingConstants.TOP);
        gVuelos_editB.setFont(new Font("Tahoma", Font.PLAIN, 16));
        gVuelosMid_B.add(gVuelos_editB);

        gVuelos_updateB = new JButton("Actualizar");
        gVuelos_updateB.setVerticalAlignment(SwingConstants.TOP);
        gVuelos_updateB.setFont(new Font("Tahoma", Font.PLAIN, 16));
        gVuelosMid_B.add(gVuelos_updateB);

        gVuelos_deleteB = new JButton("Eliminar");
        gVuelos_deleteB.setVerticalAlignment(SwingConstants.TOP);
        gVuelos_deleteB.setFont(new Font("Tahoma", Font.PLAIN, 16));
        gVuelosMid_B.add(gVuelos_deleteB);

        JButton gVuelos_airlineB = new JButton("Aerolinea");
        gVuelos_airlineB.setVerticalAlignment(SwingConstants.TOP);
        gVuelos_airlineB.setFont(new Font("Tahoma", Font.PLAIN, 16));
        gVuelosMid_B.add(gVuelos_airlineB);
        gVuelos_airlineB.addActionListener(e -> {
            GestionarAerolinea gaFrame = new GestionarAerolinea();
            gaFrame.runFrame();
            gVuelosFrame.setVisible(false);
        });

        JPanel gVuelosBottom = new JPanel();
        gVuelosBottom.setBorder(new EmptyBorder(20, 20, 20, 20));
        gVuelosFrame.getContentPane().add(gVuelosBottom, BorderLayout.SOUTH);
        gVuelosBottom.setLayout(new BorderLayout(0, 0));

        JButton gVuelos_backB = new JButton("VOLVER");
        gVuelos_backB.setIcon(new ImageIcon("resources/left.png"));
        gVuelos_backB.setFont(new Font("Tahoma", Font.PLAIN, 16));
        gVuelosBottom.add(gVuelos_backB, BorderLayout.EAST);
        gVuelos_backB.addActionListener(e -> {
            VistaMA maFrame = new VistaMA();
            maFrame.runFrame();
            gVuelosFrame.setVisible(false);
        });
    }

}