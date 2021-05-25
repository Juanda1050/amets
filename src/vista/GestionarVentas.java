package vista;

import controlador.VentaController;
import modelo.VentaDAO;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.awt.event.ActionEvent;
import java.net.URLEncoder;

public class GestionarVentas {

    private JFrame gVentasFrame;
    public JTable gVentasTable;

    public void runFrame(){
        EventQueue.invokeLater(() -> {
            try {
                GestionarVentas window = new GestionarVentas();
                VentaDAO dao = new VentaDAO();
                VentaController c = new VentaController(window, dao);
                window.gVentasFrame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public GestionarVentas() {
        initialize();
    }


    private void initialize() {
        //Iniciando JFrame
        gVentasFrame = new JFrame("Gestionar Ventas");
        gVentasFrame.setMinimumSize(new Dimension(1280, 720));
        gVentasFrame.setExtendedState(Frame.MAXIMIZED_BOTH);
        gVentasFrame.setIconImage(Toolkit.getDefaultToolkit().getImage("resources/amets.jpg"));
        gVentasFrame.setBounds(100, 100, 1280, 720);
        gVentasFrame.getContentPane().setLayout(new BorderLayout(0, 0));
        gVentasFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                int result = JOptionPane.showConfirmDialog(gVentasFrame, "¿Desea cerrar el programa?", "Salir del programa", JOptionPane.YES_NO_OPTION);
                if (result == JOptionPane.YES_OPTION)
                {
                    gVentasFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                }
                else if (result == JOptionPane.NO_OPTION)
                {
                    gVentasFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                }
            }
        });

        //JPanel North
        JPanel gVentasTop = new JPanel();
        gVentasTop.setBorder(new EmptyBorder(20, 20, 20, 20));
        gVentasFrame.getContentPane().add(gVentasTop, BorderLayout.NORTH);
        gVentasTop.setLayout(new BorderLayout(0, 0));

        //JLabel Principal "Ventas"
        JLabel gVentasL = new JLabel("Ventas");
        gVentasL.setFont(new Font("Tahoma", Font.BOLD, 18));
        gVentasL.setHorizontalAlignment(SwingConstants.CENTER);
        gVentasL.setVerticalTextPosition(SwingConstants.CENTER);
        gVentasTop.add(gVentasL);

        //JPanel Center
        JPanel gVentasMid = new JPanel();
        gVentasMid.setBorder(new EmptyBorder(10, 5, 10, 5));
        gVentasFrame.getContentPane().add(gVentasMid, BorderLayout.CENTER);
        gVentasMid.setLayout(new BorderLayout(0, 0));

        //JScrollPane con una JTable dentro
        JScrollPane gVentasSP = new JScrollPane();
        gVentasSP.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        gVentasMid.add(gVentasSP, BorderLayout.CENTER);

        gVentasTable = new JTable();
        gVentasTable = new JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                return false;
            }
        };
        gVentasTable.setModel(new DefaultTableModel(
                new Object[][] {
                },
                new String[] {
                        "ID Venta", "Usuario","Vendedor", "Cantidad", "Descripcion", "Fecha", "Tipo de Pago", "Subtotal", "IVA", "Total"
                }
        ));
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );
        gVentasTable.getColumnModel().getColumn(0).setCellRenderer( centerRenderer );
        gVentasTable.setRowHeight(50);
        for(int i = 0; i < gVentasTable.getModel().getColumnCount(); i++){
            gVentasTable.getColumnModel().getColumn(i).setCellRenderer( centerRenderer );
        }

        //Generando estilo de JTable
        JTableHeader tHeader = gVentasTable.getTableHeader();
        tHeader.setPreferredSize(new Dimension(0, 25));
        tHeader.setBackground(Color.decode("#094293"));
        tHeader.setForeground(Color.white);
        tHeader.setReorderingAllowed(false);
        tHeader.setFont(new Font("Tahome", Font.BOLD, 16));
        gVentasTable.setFont(new Font("Tahome", Font.PLAIN, 14));
        gVentasSP.setViewportView(gVentasTable);

        //JPanel South
        JPanel gVentasBottom = new JPanel();
        gVentasBottom.setBorder(new EmptyBorder(20, 20, 20, 20));
        gVentasFrame.getContentPane().add(gVentasBottom, BorderLayout.SOUTH);
        gVentasBottom.setLayout(new BorderLayout(0, 0));

        //JButton de "Volver"
        JButton gVentas_backB = new JButton("VOLVER");
        gVentas_backB.setIcon(new ImageIcon("resources/left.png"));
        gVentas_backB.setFont(new Font("Tahoma", Font.PLAIN, 16));
        gVentasBottom.add(gVentas_backB, BorderLayout.EAST);
        gVentas_backB.addActionListener(e -> {
            MenuAdministrador maFrame = new MenuAdministrador();
            maFrame.runFrame();
            gVentasFrame.setVisible(false);
        });
    }
}
