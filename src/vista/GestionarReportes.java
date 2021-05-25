package vista;

import java.awt.*;

import javax.swing.*;

import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JTextFieldDateEditor;
import controlador.ReportesController;
import modelo.ReportesDAO;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class GestionarReportes {

    private JFrame gReportesFrame;
    private JTable gReportesTable;
    private JButton gReportes_searchB;

    public JDateChooser gReportes_inicioDC;
    public JDateChooser gReportes_finalDC;

    public JButton getgReportes_searchB() {
        return gReportes_searchB;
    }

    public JTable getgReportesTable() {
        return gReportesTable;
    }
    
    public void runFrame(){
        EventQueue.invokeLater(() -> {
            try {
                GestionarReportes window = new GestionarReportes();
                ReportesDAO reportesDAO = new ReportesDAO();
                ReportesController reportesController = new ReportesController(window, reportesDAO);
                window.gReportesFrame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
    
    public GestionarReportes() {
        initialize();
    }
    
    private void initialize() {
        gReportesFrame = new JFrame("Gestionar Reportes");
        gReportesFrame.setMinimumSize(new Dimension(1280, 720));
        gReportesFrame.setExtendedState(Frame.MAXIMIZED_BOTH);
        gReportesFrame.setIconImage(Toolkit.getDefaultToolkit().getImage("resources/amets.jpg"));
        gReportesFrame.setBounds(100, 100, 1280, 720);
        gReportesFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                int result = JOptionPane.showConfirmDialog(gReportesFrame, "¿Desea cerrar el programa?", "Salir del programa", JOptionPane.YES_NO_OPTION);
                if (result == JOptionPane.YES_OPTION)
                {
                    gReportesFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                }
                else if (result == JOptionPane.NO_OPTION)
                {
                    gReportesFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                }
            }
        });

        JPanel gReportesTop = new JPanel();
        gReportesTop.setBorder(new EmptyBorder(20, 20, 20, 20));
        gReportesFrame.getContentPane().add(gReportesTop, BorderLayout.NORTH);
        gReportesTop.setLayout(new BorderLayout(0, 0));

        JLabel gReportesL = new JLabel("GESTION DE REPORTES");
        gReportesL.setFont(new Font("Tahoma", Font.BOLD, 18));
        gReportesL.setHorizontalAlignment(SwingConstants.CENTER);
        gReportesTop.add(gReportesL, BorderLayout.NORTH);

        JPanel gReportesTop_B = new JPanel();
        gReportesTop_B.setBorder(new EmptyBorder(20, 20, 20, 20));
        gReportesTop.add(gReportesTop_B, BorderLayout.SOUTH);
        gReportesTop_B.setLayout(new GridLayout(0, 9, 15, 15));

        JLabel gReportes_inicioL = new JLabel("Fecha de inicio");
        gReportes_inicioL.setFont(new Font("Tahoma", Font.PLAIN, 16));
        gReportes_inicioL.setHorizontalAlignment(SwingConstants.RIGHT);
        gReportesTop_B.add(gReportes_inicioL);

        gReportes_inicioDC = new JDateChooser();
        gReportes_inicioDC.setDateFormatString("yyyy-MM-dd");
        JTextFieldDateEditor inicioEditor = (JTextFieldDateEditor) gReportes_inicioDC.getDateEditor();
        inicioEditor.setEditable(false);
        gReportesTop_B.add(gReportes_inicioDC);

        JLabel gReportes_finalL= new JLabel("Fecha final");
        gReportes_finalL.setFont(new Font("Tahoma", Font.PLAIN, 16));
        gReportes_finalL.setHorizontalAlignment(SwingConstants.RIGHT);
        gReportesTop_B.add(gReportes_finalL);
        
        gReportes_finalDC = new JDateChooser();
        gReportes_finalDC.setDateFormatString("yyyy-MM-dd");
        JTextFieldDateEditor finalEditor = (JTextFieldDateEditor) gReportes_finalDC.getDateEditor();
        finalEditor.setEditable(false);
        gReportesTop_B.add(gReportes_finalDC);

        gReportes_searchB = new JButton("BUSCAR");
        gReportes_searchB.setIcon(new ImageIcon("resources/search.png"));
        gReportes_searchB.setFont(new Font("Tahoma", Font.PLAIN, 16));
        gReportesTop_B.add(gReportes_searchB);

        JPanel gReportesMid = new JPanel();
        gReportesMid.setBorder(new EmptyBorder(20, 20, 20, 20));
        gReportesFrame.getContentPane().add(gReportesMid, BorderLayout.CENTER);
        gReportesMid.setLayout(new BorderLayout(0, 0));

        JScrollPane gReportesSP = new JScrollPane();
        gReportesSP.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        gReportesMid.add(gReportesSP, BorderLayout.CENTER);

        gReportesTable = new JTable();
        gReportesTable = new JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                return false;
            }
        };
        gReportesTable.setModel(new DefaultTableModel(
                new Object[][] {
                },
                new String[] {
                        "ID Corte", "ID Venta", "ID Vendedor", "Fecha", "Turno", "Total"
                }
        ));
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );
        gReportesTable.getColumnModel().getColumn(0).setCellRenderer( centerRenderer );
        gReportesTable.setRowHeight(50);
        for(int i = 0; i < gReportesTable.getModel().getColumnCount(); i++){
            gReportesTable.getColumnModel().getColumn(i).setCellRenderer( centerRenderer );
        }
        gReportesSP.setViewportView(gReportesTable);

        //Generando estilo de JTable
        JTableHeader tHeader = gReportesTable.getTableHeader();
        tHeader.setPreferredSize(new Dimension(0, 25));
        tHeader.setBackground(Color.decode("#094293"));
        tHeader.setForeground(Color.white);
        tHeader.setReorderingAllowed(false);
        tHeader.setFont(new Font("Tahome", Font.BOLD, 16));
        gReportesTable.setFont(new Font("Tahome", Font.PLAIN, 14));
        gReportesSP.setViewportView(gReportesTable);

        JPanel gReportesBottom = new JPanel();
        gReportesBottom.setBorder(new EmptyBorder(20, 20, 20, 20));
        gReportesBottom.setFocusable(false);
        gReportesFrame.getContentPane().add(gReportesBottom, BorderLayout.SOUTH);
        gReportesBottom.setLayout(new BorderLayout(0, 0));

        JButton gReportes_backB = new JButton("VOLVER");
        gReportes_backB.setFont(new Font("Tahoma", Font.PLAIN, 16));
        gReportes_backB.setIcon(new ImageIcon("resources/left.png"));        
        gReportesBottom.add(gReportes_backB, BorderLayout.EAST);
        gReportes_backB.addActionListener(e -> {
            MenuAdministrador maFrame = new MenuAdministrador();
            maFrame.runFrame();
            gReportesFrame.setVisible(false);
        });
    }
}
