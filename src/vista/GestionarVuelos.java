package vista;

import java.awt.*;

import javax.swing.*;

import javax.swing.border.EmptyBorder;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JTextFieldDateEditor;
import controlador.VuelosController;
import modelo.Vuelos;
import modelo.VuelosDAO;
import tercera.VistaMA;

import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class GestionarVuelos {

    public JTextFieldDateEditor gVuelos_salidaDC, gVuelos_llegadaDC;
    private JFrame gVuelosFrame;
    public JTextField gVuelos_idTF, gVuelos_origenTF, gVuelos_genteTF;
    public JComboBox<String> gVuelos_destinoCB, gVuelos_aerolineaCB;
    public JButton gVuelos_addB, gVuelos_saveB, gVuelos_editB, gVuelos_deleteB;
    public JTable gVuelosTable;

    public void runFrame(){
        EventQueue.invokeLater(() -> {
            try {
                GestionarVuelos window = new GestionarVuelos();
                VuelosDAO dao = new VuelosDAO();
                VuelosController controller = new VuelosController(window, dao);
                window.gVuelosFrame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public GestionarVuelos() {
        initialize();
    }

    private void initialize() {
        VuelosDAO gvDAO = new VuelosDAO();
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
        gVuelos_origenTF.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char ch = e.getKeyChar();
                if(Character.isLetter(ch) || Character.isISOControl(ch)){
                    String txtOrigen = String.valueOf(ch);
                    gVuelos_origenTF.setText("" + txtOrigen);
                }else{
                    e.consume();
                    JOptionPane.showMessageDialog(null, "Escriba solo letras");
                }
            }
        });
        gVuelosLeft.add(gVuelos_origenTF);

        JLabel gVuelos_destinoL = new JLabel("Destino");
        gVuelos_destinoL.setFont(new Font("Tahoma", Font.PLAIN, 16));
        gVuelosLeft.add(gVuelos_destinoL);

        gVuelos_destinoCB = new JComboBox<>(gvDAO.listarDestinos().toArray(new String[0]));
        gVuelos_destinoCB.setFont(new Font("Tahoma", Font.PLAIN, 16));
        gVuelos_destinoCB.setToolTipText("");
        gVuelosLeft.add(gVuelos_destinoCB);

        JLabel gVuelos_aerolineaL = new JLabel("Aerolinea");
        gVuelos_aerolineaL.setFont(new Font("Tahoma", Font.PLAIN, 16));
        gVuelosLeft.add(gVuelos_aerolineaL);

        gVuelos_aerolineaCB = new JComboBox<>(gvDAO.listarAerolinea().toArray(new String[0]));
        gVuelos_aerolineaCB.setFont(new Font("Tahoma", Font.PLAIN, 16));
        gVuelos_aerolineaCB.setToolTipText("");
        gVuelosLeft.add(gVuelos_aerolineaCB);

        JLabel gVuelos_genteL = new JLabel("Pasajeros");
        gVuelos_genteL.setFont(new Font("Tahoma", Font.PLAIN, 16));
        gVuelosLeft.add(gVuelos_genteL);

        gVuelos_genteTF = new JTextField();
        gVuelos_genteTF.setFont(new Font("Tahoma", Font.PLAIN, 20));
        gVuelos_genteTF.setColumns(10);
        gVuelos_genteTF.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char ch = e.getKeyChar();
                if(Character.isDigit(ch) || Character.isISOControl(ch)){
                    String txtGente = String.valueOf(ch);
                    gVuelos_origenTF.setText("" + txtGente);
                }else{
                    e.consume();
                    JOptionPane.showMessageDialog(null, "Solo admite numeros");
                }
            }
        });
        gVuelosLeft.add(gVuelos_genteTF);

        JLabel gVuelos_salidaL = new JLabel("Fecha de salida");
        gVuelos_salidaL.setFont(new Font("Tahoma", Font.PLAIN, 16));
        gVuelosLeft.add(gVuelos_salidaL);

        gVuelos_salidaDC = new JTextFieldDateEditor("yyyy-MM-dd HH:mm", "####-##-## ##:##", '_');
        gVuelosLeft.add(gVuelos_salidaDC);

        JLabel gVuelos_llegadaL = new JLabel("Fecha de llegada");
        gVuelos_llegadaL.setFont(new Font("Tahoma", Font.PLAIN, 16));
        gVuelosLeft.add(gVuelos_llegadaL);

        gVuelos_llegadaDC = new JTextFieldDateEditor("yyyy-MM-dd HH:mm", "####-##-## ##:##", '_');
        gVuelos_llegadaDC.setDate(new Date());
        gVuelosLeft.add(gVuelos_llegadaDC);

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
        gVuelosSP.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        gVuelosMid.add(gVuelosSP, BorderLayout.CENTER);

        gVuelosTable = new JTable();
        gVuelosTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
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
        tModel.addColumn("Pasajeros");
        tModel.addColumn("Salida");
        tModel.addColumn("Llegada");
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );
        for(int i = 0; i < gVuelosTable.getModel().getColumnCount(); i++){
            gVuelosTable.getColumnModel().getColumn(i).setCellRenderer( centerRenderer );
        }
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