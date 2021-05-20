package vista;

import modelo.DetallePaqueteController;
import modelo.DetallePaqueteDAO;
import vista.GestionarVuelos;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class DetallePaquete {

    private JFrame dPaqueteFrame;
    public JTable dPaqueteTable;
    public JButton dPaquete_addB, dPaquete_saveB, dPaquete_editB, dPaquete_deleteB;
    public JComboBox dPaquete_paqueteCB, dPaquete_vueloCB, dPaquete_hotelCB, dPaquete_destinoCB;

    public void runFrame() {
        EventQueue.invokeLater(() -> {
            try {
                DetallePaquete window = new DetallePaquete();
                DetallePaqueteDAO dao = new DetallePaqueteDAO();
                DetallePaqueteController c = new DetallePaqueteController(window, dao);
                window.dPaqueteFrame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public DetallePaquete() {
        initialize();
    }

    private void initialize() {
        dPaqueteFrame = new JFrame("Detalle de Paquete");
        dPaqueteFrame.setExtendedState(Frame.MAXIMIZED_BOTH);
        dPaqueteFrame.setBounds(100, 100, 1280, 720);
        dPaqueteFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                int result = JOptionPane.showConfirmDialog(dPaqueteFrame, "¿Desea cerrar el programa?", "Salir del programa", JOptionPane.YES_NO_OPTION);
                if (result == JOptionPane.YES_OPTION)
                {
                    dPaqueteFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                }
                else if (result == JOptionPane.NO_OPTION)
                {
                    dPaqueteFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                }
            }
        });

        JPanel dPaqueteTop = new JPanel();
        dPaqueteTop.setBorder(new EmptyBorder(20, 20, 20, 20));
        dPaqueteFrame.getContentPane().add(dPaqueteTop, BorderLayout.NORTH);
        dPaqueteTop.setLayout(new BorderLayout(0, 0));

        JLabel dPaquete_addL = new JLabel("Nuevo Paquete");
        dPaquete_addL.setFont(new Font("Tahoma", Font.BOLD, 18));
        dPaqueteTop.add(dPaquete_addL, BorderLayout.WEST);

        JLabel dPaqueteL = new JLabel("Detalle de Paquete");
        dPaqueteL.setFont(new Font("Tahoma", Font.BOLD, 18));
        dPaqueteL.setHorizontalAlignment(SwingConstants.CENTER);
        dPaqueteTop.add(dPaqueteL, BorderLayout.CENTER);

        JPanel dPaqueteLeft = new JPanel();
        dPaqueteLeft.setBorder(new EmptyBorder(20, 20, 20, 20));
        dPaqueteFrame.getContentPane().add(dPaqueteLeft, BorderLayout.WEST);
        dPaqueteLeft.setLayout(new GridLayout(0, 2, 15, 50));

        JLabel dPaquete_paqueteL = new JLabel("Paquete");
        dPaquete_paqueteL.setFont(new Font("Tahoma", Font.PLAIN, 16));
        dPaqueteLeft.add(dPaquete_paqueteL);

        dPaquete_paqueteCB = new JComboBox();
        dPaquete_paqueteCB.setModel(new DefaultComboBoxModel(new String[] {"Seleccione un paquete"}));
        dPaquete_paqueteCB.setFont(new Font("Tahoma", Font.PLAIN, 16));
        dPaqueteLeft.add(dPaquete_paqueteCB);

        JLabel dPaquete_destinoL = new JLabel("Destino");
        dPaquete_destinoL.setFont(new Font("Tahoma", Font.PLAIN, 16));
        dPaqueteLeft.add(dPaquete_destinoL);

        dPaquete_destinoCB = new JComboBox();
        dPaquete_destinoCB.setModel(new DefaultComboBoxModel(new String[] {"Seleccione un destino"}));
        dPaquete_destinoCB.setFont(new Font("Tahoma", Font.PLAIN, 16));
        dPaqueteLeft.add(dPaquete_destinoCB);

        JLabel dPaquete_vueloL = new JLabel("Vuelo");
        dPaquete_vueloL.setFont(new Font("Tahoma", Font.PLAIN, 16));
        dPaqueteLeft.add(dPaquete_vueloL);

        dPaquete_vueloCB = new JComboBox();
        dPaquete_vueloCB.setModel(new DefaultComboBoxModel(new String[] {"Seleccione un vuelo"}));
        dPaquete_vueloCB.setFont(new Font("Tahoma", Font.PLAIN, 16));
        dPaqueteLeft.add(dPaquete_vueloCB);

        JLabel dPaquete_hotelL = new JLabel("Hotel");
        dPaquete_hotelL.setFont(new Font("Tahoma", Font.PLAIN, 16));
        dPaqueteLeft.add(dPaquete_hotelL);

        dPaquete_hotelCB = new JComboBox();
        dPaquete_hotelCB.setModel(new DefaultComboBoxModel(new String[] {"Seleccione un hotel"}));
        dPaquete_hotelCB.setFont(new Font("Tahoma", Font.PLAIN, 16));
        dPaqueteLeft.add(dPaquete_hotelCB);

        dPaquete_addB = new JButton("Nuevo");
        dPaquete_addB.setFont(new Font("Tahoma", Font.PLAIN, 16));
        dPaqueteLeft.add(dPaquete_addB);

        dPaquete_saveB = new JButton("Guardar");
        dPaquete_saveB.setFont(new Font("Tahoma", Font.PLAIN, 16));
        dPaqueteLeft.add(dPaquete_saveB);

        JPanel dPaqueteMid = new JPanel();
        dPaqueteMid.setBorder(new EmptyBorder(20, 20, 40, 20));
        dPaqueteFrame.getContentPane().add(dPaqueteMid, BorderLayout.CENTER);
        dPaqueteMid.setLayout(new BorderLayout(0, 10));

        JScrollPane dPaqueteSP = new JScrollPane();
        dPaqueteMid.add(dPaqueteSP, BorderLayout.CENTER);

        dPaqueteTable = new JTable();
        dPaqueteTable = new JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                return false;
            }
        };
        DefaultTableModel tModel = new DefaultTableModel();
        dPaqueteTable.setModel(tModel);
        tModel.addColumn("Paquete");
        tModel.addColumn("Vuelo");
        tModel.addColumn("Hotel");
        tModel.addColumn("Destino");
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );
        dPaqueteTable.getColumnModel().getColumn(0).setCellRenderer( centerRenderer );
        dPaqueteTable.setRowHeight(50);

        //Generando estilo de JTable
        JTableHeader tHeader = dPaqueteTable.getTableHeader();
        tHeader.setPreferredSize(new Dimension(0, 25));
        tHeader.setBackground(Color.decode("#094293"));
        tHeader.setForeground(Color.white);
        tHeader.setFont(new Font("Tahome", Font.BOLD, 16));
        dPaqueteTable.setFont(new Font("Tahome", Font.PLAIN, 14));
        dPaqueteSP.setViewportView(dPaqueteTable);

        JPanel dPaqueteMid_B = new JPanel();
        dPaqueteMid_B.setBorder(new EmptyBorder(0, 0, 20, 20));
        dPaqueteMid.add(dPaqueteMid_B, BorderLayout.SOUTH);
        dPaqueteMid_B.setLayout(new GridLayout(0, 6, 20, 0));

        dPaquete_editB = new JButton("Editar");
        dPaquete_editB.setFont(new Font("Tahoma", Font.PLAIN, 16));
        dPaqueteMid_B.add(dPaquete_editB);

        dPaquete_deleteB = new JButton("Eliminar");
        dPaquete_deleteB.setFont(new Font("Tahoma", Font.PLAIN, 16));
        dPaqueteMid_B.add(dPaquete_deleteB);

        JPanel dPaqueteBottom = new JPanel();
        dPaqueteBottom.setBorder(new EmptyBorder(20, 20, 20, 20));
        dPaqueteFrame.getContentPane().add(dPaqueteBottom, BorderLayout.SOUTH);
        dPaqueteBottom.setLayout(new BorderLayout(0, 0));

        JButton dPaquete_backB = new JButton("VOLVER");
        dPaquete_backB.setFont(new Font("Tahoma", Font.PLAIN, 16));
        dPaqueteBottom.add(dPaquete_backB, BorderLayout.EAST);
        dPaquete_backB.addActionListener(e -> {
            GestionarPaquetes gpFrame = new GestionarPaquetes();
            gpFrame.runFrame();
            dPaqueteFrame.setVisible(false);
        });
    }

}