package vista;

import controlador.DHotelesController;
import modelo.DHotelesDAO;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class DetalleHotel {

    private JFrame dHotelFrame;
    public JTextField dHotel_precioTF;
    public JButton dHotel_addB, dHotel_saveB, dHotel_editB, dHotel_deleteB;
    public JTable dHotelTable;
    public JComboBox dHotel_hotelCB;
    public JComboBox dHotel_habitacionCB;
    private int limitePrecio = 10;

    public void runFrame() {
        EventQueue.invokeLater(() -> {
            try {
                DetalleHotel window = new DetalleHotel();
                DHotelesDAO dao = new DHotelesDAO();
                DHotelesController controller = new DHotelesController(window, dao);
                window.dHotelFrame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public DetalleHotel() {
        initialize();
    }

    private void initialize() {
        DHotelesDAO hotelDAO = new DHotelesDAO();
        dHotelFrame = new JFrame("Detalle de Hotel");
        dHotelFrame.setExtendedState(Frame.MAXIMIZED_BOTH);
        dHotelFrame.setIconImage(Toolkit.getDefaultToolkit().getImage("resources/amets.jpg"));
        dHotelFrame.setBounds(100, 100, 1280, 720);
        dHotelFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                int result = JOptionPane.showConfirmDialog(dHotelFrame, "¿Desea cerrar el programa?", "Salir del programa", JOptionPane.YES_NO_OPTION);
                if (result == JOptionPane.YES_OPTION)
                {
                    dHotelFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                }
                else if (result == JOptionPane.NO_OPTION)
                {
                    dHotelFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                }
            }
        });

        JPanel dHotelTop = new JPanel();
        dHotelTop.setBorder(new EmptyBorder(20, 20, 20, 20));
        dHotelFrame.getContentPane().add(dHotelTop, BorderLayout.NORTH);
        dHotelTop.setLayout(new BorderLayout(0, 0));

        JLabel dHotel_addL = new JLabel("Nuevo Hotel");
        dHotel_addL.setFont(new Font("Tahoma", Font.BOLD, 18));
        dHotelTop.add(dHotel_addL, BorderLayout.WEST);

        JLabel dHotelL = new JLabel("Detalle de Hotel");
        dHotelL.setHorizontalAlignment(SwingConstants.CENTER);
        dHotelL.setFont(new Font("Tahoma", Font.BOLD, 18));
        dHotelTop.add(dHotelL, BorderLayout.CENTER);

        JPanel dHotelLeft = new JPanel();
        dHotelLeft.setBorder(new EmptyBorder(20, 20, 20, 20));
        dHotelFrame.getContentPane().add(dHotelLeft, BorderLayout.WEST);
        dHotelLeft.setLayout(new GridLayout(0, 2, 15, 50));

        JLabel dHotel_hotelL = new JLabel("Hotel");
        dHotel_hotelL.setFont(new Font("Tahoma", Font.PLAIN, 16));
        dHotelLeft.add(dHotel_hotelL);

        dHotel_hotelCB = new JComboBox();
        dHotel_hotelCB.setModel(new DefaultComboBoxModel(new String[] {"Seleccione un hotel"}));
        dHotel_hotelCB.setFont(new Font("Tahoma", Font.PLAIN, 16));
        dHotelLeft.add(dHotel_hotelCB);

        JLabel dHotel_habitacionL = new JLabel("Tipo de habitacion");
        dHotel_habitacionL.setFont(new Font("Tahoma", Font.PLAIN, 16));
        dHotelLeft.add(dHotel_habitacionL);

        dHotel_habitacionCB = new JComboBox();
        dHotel_habitacionCB.setModel(new DefaultComboBoxModel(new String[] {"Seleccione una habitacion"}));
        dHotel_habitacionCB.setFont(new Font("Tahoma", Font.PLAIN, 16));
        dHotelLeft.add(dHotel_habitacionCB);

        JLabel dHotel_precioL = new JLabel("Precio");
        dHotel_precioL.setFont(new Font("Tahoma", Font.PLAIN, 16));
        dHotelLeft.add(dHotel_precioL);

        dHotel_precioTF = new JTextField();
        dHotel_precioTF.setFont(new Font("Tahoma", Font.PLAIN, 16));
        dHotelLeft.add(dHotel_precioTF);
        dHotel_precioTF.setColumns(10);
        dHotel_precioTF.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char ch = e.getKeyChar();
                if((ch<'0' || ch>'9') && (ch<',' || ch>'.') && (ch != '\b')) {
                    e.consume();
                    JOptionPane.showMessageDialog(null, "Solo admite números");
                }
                if (dHotel_precioTF.getText().length() >= limitePrecio){
                    e.consume();
                    Toolkit.getDefaultToolkit().beep();
                }
            }
        });

        dHotel_addB = new JButton("Nuevo");
        dHotel_addB.setFont(new Font("Tahoma", Font.PLAIN, 16));
        dHotel_addB.setIcon(new ImageIcon("resources/add.png"));
        dHotelLeft.add(dHotel_addB);

        dHotel_saveB = new JButton("Guardar");
        dHotel_saveB.setFont(new Font("Tahoma", Font.PLAIN, 16));
        dHotel_saveB.setIcon(new ImageIcon("resources/save.png"));
        dHotelLeft.add(dHotel_saveB);

        JPanel dHotelMid = new JPanel();
        dHotelMid.setBorder(new EmptyBorder(20, 20, 40, 20));
        dHotelFrame.getContentPane().add(dHotelMid, BorderLayout.CENTER);
        dHotelMid.setLayout(new BorderLayout(0, 10));

        JScrollPane dHotelSP = new JScrollPane();
        dHotelMid.add(dHotelSP, BorderLayout.CENTER);

        dHotelTable = new JTable();
        dHotelTable = new JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                return false;
            }
        };
        DefaultTableModel tModel = new DefaultTableModel();
        dHotelTable.setModel(tModel);
        tModel.addColumn("Hotel");
        tModel.addColumn("Habitacion");
        tModel.addColumn("Precio");
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );
        dHotelTable.getColumnModel().getColumn(0).setCellRenderer( centerRenderer );
        dHotelTable.setRowHeight(50);
        for(int i = 0; i < dHotelTable.getModel().getColumnCount(); i++){
            dHotelTable.getColumnModel().getColumn(i).setCellRenderer( centerRenderer );
        }

        //Generando estilo de JTable
        JTableHeader tHeader = dHotelTable.getTableHeader();
        tHeader.setPreferredSize(new Dimension(0, 25));
        tHeader.setBackground(Color.decode("#094293"));
        tHeader.setForeground(Color.white);
        tHeader.setReorderingAllowed(false);
        tHeader.setFont(new Font("Tahome", Font.BOLD, 16));
        dHotelTable.setFont(new Font("Tahome", Font.PLAIN, 14));
        dHotelSP.setViewportView(dHotelTable);

        JPanel dHotelMid_B = new JPanel();
        dHotelMid_B.setBorder(new EmptyBorder(0, 0, 20, 20));
        dHotelMid.add(dHotelMid_B, BorderLayout.SOUTH);
        dHotelMid_B.setLayout(new GridLayout(0, 6, 20, 0));

        dHotel_editB = new JButton("Editar");
        dHotel_editB.setFont(new Font("Tahoma", Font.PLAIN, 16));
        dHotel_editB.setIcon(new ImageIcon("resources/edit.png"));
        dHotelMid_B.add(dHotel_editB);

        dHotel_deleteB = new JButton("Eliminar");
        dHotel_deleteB.setFont(new Font("Tahoma", Font.PLAIN, 16));
        dHotel_deleteB.setIcon(new ImageIcon("resources/delete.png"));
        dHotelMid_B.add(dHotel_deleteB);

        JPanel dHotelBottom = new JPanel();
        dHotelBottom.setBorder(new EmptyBorder(20, 20, 20, 20));
        dHotelFrame.getContentPane().add(dHotelBottom, BorderLayout.SOUTH);
        dHotelBottom.setLayout(new BorderLayout(0, 0));

        JButton dHotel_backB = new JButton("VOLVER");
        dHotel_backB.setFont(new Font("Tahoma", Font.PLAIN, 16));
        dHotel_backB.setIcon(new ImageIcon("resources/left.png"));
        dHotelBottom.add(dHotel_backB, BorderLayout.EAST);
        dHotel_backB.addActionListener(e -> {
            GestionarHoteles ghFrame = new GestionarHoteles();
            ghFrame.runFrame();
            dHotelFrame.setVisible(false);
        });
    }
}