package vista;

import controlador.PromocionesController;
import modelo.PromocionesDAO;

import java.awt.*;

import javax.swing.JFrame;
import javax.swing.JPanel;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class GestionarPromociones{

    private JFrame gPromoFrame;
    public JTextField gPromo_idTF, gPromo_nombreTF, gPromo_descuentoTF, gPromo_descripcionTF;
    public JButton gPromo_addB, gPromo_saveB, gPromo_editB, gPromo_deleteB;
    public JComboBox<String> gPromo_paqueteCB;
    public JTable gPromoTable;

    public void runFrame(){
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    GestionarPromociones window = new GestionarPromociones();
                    PromocionesDAO dao = new PromocionesDAO();
                    PromocionesController controller = new PromocionesController(window, dao);
                    window.gPromoFrame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public GestionarPromociones() {
        initialize();
    }

    private void initialize() {
        PromocionesDAO gpDAO = new PromocionesDAO();
        gPromoFrame = new JFrame("Gestionar Promociones");
        gPromoFrame.setBounds(100, 100, 1280, 720);
        gPromoFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gPromoFrame.setExtendedState(Frame.MAXIMIZED_BOTH);
        gPromoFrame.setIconImage(Toolkit.getDefaultToolkit().getImage("resources/amets.jpg"));
        gPromoFrame.getContentPane().setLayout(new BorderLayout(0, 0));
        gPromoFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                int result = JOptionPane.showConfirmDialog(gPromoFrame, "¿Desea cerrar el programa?", "Salir del programa", JOptionPane.YES_NO_OPTION);
                if (result == JOptionPane.YES_OPTION) {
                    gPromoFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                } else if (result == JOptionPane.NO_OPTION) {
                    gPromoFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                }
            }
        });

        JPanel gPromoTop = new JPanel();
        gPromoTop.setBorder(new EmptyBorder(20, 20, 20, 20));
        gPromoFrame.getContentPane().add(gPromoTop, BorderLayout.NORTH);
        gPromoTop.setLayout(new BorderLayout(0, 0));

        JLabel gPromo_addL = new JLabel("Nueva Promocion");
        gPromo_addL.setFont(new Font("Tahoma", Font.BOLD, 18));
        gPromoTop.add(gPromo_addL, BorderLayout.WEST);

        JLabel gPromoL = new JLabel("Promociones");
        gPromoL.setHorizontalAlignment(SwingConstants.CENTER);
        gPromoL.setFont(new Font("Tahoma", Font.BOLD, 18));
        gPromoTop.add(gPromoL, BorderLayout.CENTER);

        JPanel gPromoLeft = new JPanel();
        gPromoLeft.setBorder(new EmptyBorder(20, 20, 20, 20));
        gPromoFrame.getContentPane().add(gPromoLeft, BorderLayout.WEST);
        gPromoLeft.setLayout(new GridLayout(0, 2, 15, 50));

        JLabel gPromo_idL = new JLabel("Nombre");
        gPromo_idL.setFont(new Font("Tahoma", Font.PLAIN, 16));
        gPromoLeft.add(gPromo_idL);

        gPromo_idTF = new JTextField();
        gPromo_idTF.setFont(new Font("Tahoma", Font.PLAIN, 16));
        gPromoLeft.add(gPromo_idTF);
        gPromo_idTF.setColumns(10);
        gPromo_idTF.setEditable(false);

        JLabel gPromo_nombreL = new JLabel("Nombre");
        gPromo_nombreL.setFont(new Font("Tahoma", Font.PLAIN, 16));
        gPromoLeft.add(gPromo_nombreL);

        gPromo_nombreTF = new JTextField();
        gPromo_nombreTF.setFont(new Font("Tahoma", Font.PLAIN, 16));
        gPromo_nombreTF.setColumns(10);
        gPromo_nombreTF.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char ch = e.getKeyChar();
                if (Character.isLetter(ch) || Character.isISOControl(ch) || Character.isSpaceChar(ch)){
                }else{
                    e.consume();
                    JOptionPane.showMessageDialog(null, "Solo admite letras");
                }
                if(gPromo_nombreTF.getText().length() >= 20){
                    e.consume();
                    Toolkit.getDefaultToolkit().beep();
                }
            }
        });
        gPromoLeft.add(gPromo_nombreTF);

        JLabel gPromo_paqueteL = new JLabel("Paquete");
        gPromo_paqueteL.setFont(new Font("Tahoma", Font.PLAIN, 16));
        gPromoLeft.add(gPromo_paqueteL);

        gPromo_paqueteCB = new JComboBox<>(gpDAO.listarPaquete().toArray(new String[0]));
        gPromo_paqueteCB.setFont(new Font("Tahoma", Font.PLAIN, 16));
        gPromoLeft.add(gPromo_paqueteCB);

        JLabel gPromo_descuentoL = new JLabel("Descuento");
        gPromo_descuentoL.setFont(new Font("Tahoma", Font.PLAIN, 16));
        gPromoLeft.add(gPromo_descuentoL);

        gPromo_descuentoTF = new JTextField();
        gPromo_descuentoTF.setFont(new Font("Tahoma", Font.PLAIN, 16));
        gPromo_descuentoTF.setColumns(10);
        gPromo_descuentoTF.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char ch = e.getKeyChar();
                if (Character.isDigit(ch) || Character.isISOControl(ch)){
                }else{
                    e.consume();
                    JOptionPane.showMessageDialog(null, "Solo admite números");
                }
                if(gPromo_descuentoTF.getText().length() >= 6){
                    e.consume();
                    Toolkit.getDefaultToolkit().beep();
                }
            }
        });
        gPromoLeft.add(gPromo_descuentoTF);

        JLabel gPromo_descripcionL = new JLabel("Descripcion");
        gPromo_descripcionL.setFont(new Font("Tahoma", Font.PLAIN, 16));
        gPromoLeft.add(gPromo_descripcionL);

        gPromo_descripcionTF = new JTextField();
        gPromo_descripcionTF.setFont(new Font("Tahoma", Font.PLAIN, 16));
        gPromo_descripcionTF.setColumns(10);
        gPromo_descripcionTF.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if(gPromo_descuentoTF.getText().length() >= 144){
                    e.consume();
                    Toolkit.getDefaultToolkit().beep();
                }
            }
        });
        gPromoLeft.add(gPromo_descripcionTF);

        gPromo_addB = new JButton("Nuevo");
        gPromo_addB.setFont(new Font("Tahoma", Font.PLAIN, 16));
        gPromoLeft.add(gPromo_addB);

        gPromo_saveB = new JButton("Guardar");
        gPromo_saveB.setFont(new Font("Tahoma", Font.PLAIN, 16));
        gPromoLeft.add(gPromo_saveB);

        JPanel gPromoMid = new JPanel();
        gPromoMid.setBorder(new EmptyBorder(20, 20, 40, 20));
        gPromoFrame.getContentPane().add(gPromoMid, BorderLayout.CENTER);
        gPromoMid.setLayout(new BorderLayout(0, 10));

        JScrollPane gPromoSP = new JScrollPane();
        gPromoMid.add(gPromoSP, BorderLayout.CENTER);

        gPromoTable = new JTable();
        gPromoTable = new JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                return false;
            }
        };
        DefaultTableModel tModel = new DefaultTableModel();
        gPromoTable.setModel(tModel);
        tModel.addColumn("ID Promocion");
        tModel.addColumn("Nombre");
        tModel.addColumn("Paquete");
        tModel.addColumn("Descuento");
        tModel.addColumn("Descripcion");
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );
        for(int i = 0; i < gPromoTable.getModel().getColumnCount(); i++){
            gPromoTable.getColumnModel().getColumn(i).setCellRenderer( centerRenderer );
        }
        gPromoTable.setRowHeight(50);
        gPromoTable.setFont(new Font("Tahoma", Font.PLAIN, 16));

        //Generando estilo de JTable
        JTableHeader tHeader = gPromoTable.getTableHeader();
        tHeader.setPreferredSize(new Dimension(0, 25));
        tHeader.setBackground(Color.decode("#094293"));
        tHeader.setForeground(Color.white);
        tHeader.setReorderingAllowed(false);
        tHeader.setFont(new Font("Tahome", Font.BOLD, 16));
        gPromoTable.setFont(new Font("Tahome", Font.PLAIN, 16));
        gPromoSP.setViewportView(gPromoTable);

        JPanel gPromoMid_B = new JPanel();
        gPromoMid_B.setBorder(new EmptyBorder(0, 0, 20, 20));
        gPromoMid.add(gPromoMid_B, BorderLayout.SOUTH);
        gPromoMid_B.setLayout(new GridLayout(0, 6, 20, 0));

        gPromo_editB = new JButton("Editar");
        gPromo_editB.setFont(new Font("Tahoma", Font.PLAIN, 16));
        gPromoMid_B.add(gPromo_editB);

        gPromo_deleteB = new JButton("Eliminar");
        gPromo_deleteB.setFont(new Font("Tahoma", Font.PLAIN, 16));
        gPromoMid_B.add(gPromo_deleteB);

        JPanel gPromoBottom = new JPanel();
        gPromoBottom.setBorder(new EmptyBorder(20, 20, 20, 20));
        gPromoFrame.getContentPane().add(gPromoBottom, BorderLayout.SOUTH);
        gPromoBottom.setLayout(new BorderLayout(0, 0));

        JButton gPromo_backB = new JButton("VOLVER");
        gPromo_backB.setFont(new Font("Tahoma", Font.PLAIN, 16));
        gPromoBottom.add(gPromo_backB, BorderLayout.EAST);
        gPromo_backB.addActionListener(e -> {
            VistaMA maFrame = new VistaMA();
            maFrame.runFrame();
            gPromoFrame.setVisible(false);
        });
    }

}
