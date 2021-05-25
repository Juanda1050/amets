package vista;

import controlador.PaquetesController;
import modelo.PaquetesDAO;

import java.awt.*;

import javax.swing.*;

import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class GestionarPaquetes{

    private JFrame gPaqueteFrame;
    public JTextField gPaquete_idTF, gPaquete_nombreTF, gPaquete_genteTF, gPaquete_precioTF, gPaquete_descripcionTF;
    public JButton gPaquete_addB, gPaquete_saveB, gPaquete_editB, gPaquete_deleteB, gPaquete_packB;
    public JTable gPaqueteTable;
    private int limiteNombre = 25, limiteDesc = 144, limiteGente = 1, limitePrecio = 10;

    public void runFrame(){
        EventQueue.invokeLater(() -> {
            try {
                PaquetesDAO dao = new PaquetesDAO();
                GestionarPaquetes window = new GestionarPaquetes();
                PaquetesController c = new PaquetesController(window, dao);
                window.gPaqueteFrame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public GestionarPaquetes() {
        initialize();
    }

    private void initialize() {
        gPaqueteFrame = new JFrame("Gestionar Paquetes");
        gPaqueteFrame.setBounds(100, 100, 1280, 720);
        gPaqueteFrame.setIconImage(Toolkit.getDefaultToolkit().getImage("resources/amets.jpg"));
        gPaqueteFrame.setExtendedState(Frame.MAXIMIZED_BOTH);
        gPaqueteFrame.getContentPane().setLayout(new BorderLayout(0, 0));
        gPaqueteFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                int result = JOptionPane.showConfirmDialog(gPaqueteFrame, "¿Desea cerrar el programa?", "Salir del programa", JOptionPane.YES_NO_OPTION);
                if (result == JOptionPane.YES_OPTION) {
                    gPaqueteFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                } else if (result == JOptionPane.NO_OPTION) {
                    gPaqueteFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                }
            }
        });

        JPanel gPaqueteTop = new JPanel();
        gPaqueteTop.setBorder(new EmptyBorder(20, 20, 20, 20));
        gPaqueteFrame.getContentPane().add(gPaqueteTop, BorderLayout.NORTH);
        gPaqueteTop.setLayout(new BorderLayout(0, 0));

        JLabel gPaquete_addL = new JLabel("Nuevo Paquete");
        gPaquete_addL.setFont(new Font("Tahoma", Font.BOLD, 18));
        gPaqueteTop.add(gPaquete_addL, BorderLayout.WEST);

        JLabel gPaqueteL = new JLabel("Paquetes");
        gPaqueteL.setHorizontalAlignment(SwingConstants.CENTER);
        gPaqueteL.setFont(new Font("Tahoma", Font.BOLD, 18));
        gPaqueteTop.add(gPaqueteL, BorderLayout.CENTER);

        JPanel gPaqueteLeft = new JPanel();
        gPaqueteLeft.setBorder(new EmptyBorder(20, 20, 2, 20));
        gPaqueteFrame.getContentPane().add(gPaqueteLeft, BorderLayout.WEST);
        gPaqueteLeft.setLayout(new GridLayout(0, 2, 20, 60));

        JLabel gPaquete_idL = new JLabel("ID");
        gPaquete_idL.setFont(new Font("Tahoma", Font.PLAIN, 16));
        gPaqueteLeft.add(gPaquete_idL);

        gPaquete_idTF = new JTextField();
        gPaquete_idTF.setFont(new Font("Tahoma", Font.PLAIN, 16));
        gPaqueteLeft.add(gPaquete_idTF);
        gPaquete_idTF.setColumns(10);
        gPaquete_idTF.setEditable(false);

        JLabel gPaquete_nombreL = new JLabel("Nombre");
        gPaquete_nombreL.setFont(new Font("Tahoma", Font.PLAIN, 16));
        gPaqueteLeft.add(gPaquete_nombreL);

        gPaquete_nombreTF = new JTextField();
        gPaquete_nombreTF.setFont(new Font("Tahoma", Font.PLAIN, 16));
        gPaqueteLeft.add(gPaquete_nombreTF);
        gPaquete_nombreTF.setColumns(10);
        gPaquete_nombreTF.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char ch = e.getKeyChar();
                if (Character.isLetter(ch) || Character.isISOControl(ch) || Character.isSpaceChar(ch)){
                }else{
                    e.consume();
                    JOptionPane.showMessageDialog(null, "Solo admite letras");
                }
                if(gPaquete_nombreTF.getText().length() >= limiteNombre){
                    e.consume();
                    Toolkit.getDefaultToolkit().beep();
                }
            }
        });

        JLabel gPaquete_genteL = new JLabel("Personas");
        gPaquete_genteL.setFont(new Font("Tahoma", Font.PLAIN, 16));
        gPaqueteLeft.add(gPaquete_genteL);

        gPaquete_genteTF = new JTextField();
        gPaquete_genteTF.setFont(new Font("Tahoma", Font.PLAIN, 16));
        gPaqueteLeft.add(gPaquete_genteTF);
        gPaquete_genteTF.setColumns(10);
        gPaquete_genteTF.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char ch = e.getKeyChar();
                if(Character.isDigit(ch) || Character.isISOControl(ch)){
                }
                else {
                    e.consume();
                    JOptionPane.showMessageDialog(null, "Solo admite números");
                }
                if (gPaquete_genteTF.getText().length() >= limiteGente){
                    e.consume();
                    Toolkit.getDefaultToolkit().beep();
                }
            }
        });

        JLabel gPaquete_descripcionL = new JLabel("Descripcion");
        gPaquete_descripcionL.setFont(new Font("Tahoma", Font.PLAIN, 16));
        gPaqueteLeft.add(gPaquete_descripcionL);

        gPaquete_descripcionTF = new JTextField();
        gPaquete_descripcionTF.setFont(new Font("Tahoma", Font.PLAIN, 16));
        gPaquete_descripcionTF.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if(gPaquete_descripcionTF.getText().length() >= limiteDesc){
                    e.consume();
                    Toolkit.getDefaultToolkit().beep();
                }
            }
        });
        gPaqueteLeft.add(gPaquete_descripcionTF);

        JLabel gPaquete_precioL = new JLabel("Precio");
        gPaquete_precioL.setFont(new Font("Tahoma", Font.PLAIN, 16));
        gPaqueteLeft.add(gPaquete_precioL);

        gPaquete_precioTF = new JTextField();
        gPaquete_precioTF.setFont(new Font("Tahoma", Font.PLAIN, 16));
        gPaqueteLeft.add(gPaquete_precioTF);
        gPaquete_precioTF.setColumns(10);
        gPaquete_precioTF.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char ch = e.getKeyChar();
                if((ch<'0' || ch>'9') && (ch<',' || ch>'.') && (ch != '\b')) {
                    e.consume();
                    JOptionPane.showMessageDialog(null, "Solo admite números");
                }
                if (gPaquete_precioTF.getText().length() >= limitePrecio){
                    e.consume();
                    Toolkit.getDefaultToolkit().beep();
                }
            }
        });

        gPaquete_addB = new JButton("Nuevo");
        gPaquete_addB.setFont(new Font("Tahoma", Font.PLAIN, 16));
        gPaquete_addB.setIcon(new ImageIcon("resources/add.png"));
        gPaqueteLeft.add(gPaquete_addB);

        gPaquete_saveB = new JButton("Guardar");
        gPaquete_saveB.setFont(new Font("Tahoma", Font.PLAIN, 16));
        gPaquete_saveB.setIcon(new ImageIcon("resources/save.png"));
        gPaqueteLeft.add(gPaquete_saveB);

        JPanel gPaqueteMid = new JPanel();
        gPaqueteMid.setBorder(new EmptyBorder(20, 20, 40, 20));
        gPaqueteFrame.getContentPane().add(gPaqueteMid, BorderLayout.CENTER);
        gPaqueteMid.setLayout(new BorderLayout(0, 10));

        JScrollPane gPaqueteSP = new JScrollPane();
        gPaqueteMid.add(gPaqueteSP, BorderLayout.CENTER);

        gPaqueteTable = new JTable();
        gPaqueteTable = new JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                return false;
            }
        };
        DefaultTableModel tModel = new DefaultTableModel();
        gPaqueteTable.setModel(tModel);
        tModel.addColumn("ID Paquete");
        tModel.addColumn("Nombre");
        tModel.addColumn("Descripcion");
        tModel.addColumn("Personas");
        tModel.addColumn("Precio");
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );
        gPaqueteTable.getColumnModel().getColumn(0).setCellRenderer( centerRenderer );
        gPaqueteTable.setRowHeight(50);
        for(int i = 0; i < gPaqueteTable.getModel().getColumnCount(); i++){
            gPaqueteTable.getColumnModel().getColumn(i).setCellRenderer( centerRenderer );
        }

        //Generando estilo de JTable
        JTableHeader tHeader = gPaqueteTable.getTableHeader();
        tHeader.setPreferredSize(new Dimension(0, 25));
        tHeader.setBackground(Color.decode("#094293"));
        tHeader.setForeground(Color.white);
        tHeader.setReorderingAllowed(false);
        tHeader.setFont(new Font("Tahome", Font.BOLD, 16));
        gPaqueteTable.setFont(new Font("Tahome", Font.PLAIN, 16));
        gPaqueteSP.setViewportView(gPaqueteTable);

        JPanel gPaqueteMid_B = new JPanel();
        gPaqueteMid_B.setBorder(new EmptyBorder(0, 0, 20, 20));
        gPaqueteMid.add(gPaqueteMid_B, BorderLayout.SOUTH);
        gPaqueteMid_B.setLayout(new GridLayout(0, 6, 20, 0));

        gPaquete_editB = new JButton("Editar");
        gPaquete_editB.setFont(new Font("Tahoma", Font.PLAIN, 16));
        gPaquete_editB.setIcon(new ImageIcon("resources/edit.png"));
        gPaqueteMid_B.add(gPaquete_editB);

        gPaquete_deleteB = new JButton("Eliminar");
        gPaquete_deleteB.setFont(new Font("Tahoma", Font.PLAIN, 16));
        gPaquete_deleteB.setIcon(new ImageIcon("resources/delete.png"));
        gPaqueteMid_B.add(gPaquete_deleteB);

        gPaquete_packB = new JButton("Detalles");
        gPaquete_packB.setFont(new Font("Tahoma", Font.PLAIN, 16));
        gPaquete_packB.setIcon(new ImageIcon("resources/details.png"));
        gPaqueteMid_B.add(gPaquete_packB);
        gPaquete_packB.addActionListener(e -> {
            DetallePaquete dpFrame = new DetallePaquete();
            dpFrame.runFrame();
            gPaqueteFrame.setVisible(false);
        });

        JPanel gPaqueteBottom = new JPanel();
        gPaqueteBottom.setBorder(new EmptyBorder(20, 20, 20, 20));
        gPaqueteFrame.getContentPane().add(gPaqueteBottom, BorderLayout.SOUTH);
        gPaqueteBottom.setLayout(new BorderLayout(0, 0));

        JButton gPaquete_backB = new JButton("VOLVER");
        gPaquete_backB.setFont(new Font("Tahoma", Font.PLAIN, 16));
        gPaquete_backB.setIcon(new ImageIcon("resources/left.png"));
        gPaqueteBottom.add(gPaquete_backB, BorderLayout.EAST);
        gPaquete_backB.addActionListener(e -> {
            MenuAdministrador maFrame = new MenuAdministrador();
            maFrame.runFrame();
            gPaqueteFrame.setVisible(false);
        });
    }

}