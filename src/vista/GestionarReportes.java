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
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class GestionarReportes {

    private JFrame gReportesFrame;
    private JTable gReportesTable;
    private JButton gReportes_buscarButton;

    public JDateChooser gReportes_inicioDate;
    public JDateChooser gReportes_finalDate;

    public JButton getgReportes_buscarButton() {
        return gReportes_buscarButton;
    }

    public JTable getgReportesTable() {
        return gReportesTable;
    }

    /**
     * Launch the application.
     */
    public void runFrame(){
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    GestionarReportes window = new GestionarReportes();
                    ReportesDAO reportesDAO = new ReportesDAO();
                    ReportesController reportesController = new ReportesController(window, reportesDAO);
                    window.gReportesFrame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public GestionarReportes() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        gReportesFrame = new JFrame();
        gReportesFrame.setMinimumSize(new Dimension(1280, 720));
        gReportesFrame.setExtendedState(Frame.MAXIMIZED_BOTH);
        gReportesFrame.setTitle("Gestionar Reportes");
        gReportesFrame.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\DELL\\OneDrive\\Documentos\\Tercer Semestre\\POO\\AmetsTravels\\resources\\amets.jpg"));
        gReportesFrame.setBounds(100, 100, 1280, 720);
        gReportesFrame.setLocationRelativeTo(null);
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

        JLabel gReportesLabel = new JLabel("GESTION DE REPORTES");
        gReportesLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
        gReportesLabel.setHorizontalAlignment(SwingConstants.CENTER);
        gReportesTop.add(gReportesLabel, BorderLayout.NORTH);

        JPanel gReportes_topBottom = new JPanel();
        gReportes_topBottom.setBorder(new EmptyBorder(20, 20, 20, 20));
        gReportesTop.add(gReportes_topBottom, BorderLayout.SOUTH);
        gReportes_topBottom.setLayout(new GridLayout(0, 9, 15, 15));

        JLabel gReportes_inicioLabel = new JLabel("Fecha de inicio");
        gReportes_inicioLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
        gReportes_inicioLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        gReportes_topBottom.add(gReportes_inicioLabel);


        gReportes_inicioDate = new JDateChooser();
        gReportes_inicioDate.setDateFormatString("yyyy-MM-dd");
        JTextFieldDateEditor inicioEditor = (JTextFieldDateEditor) gReportes_inicioDate.getDateEditor();
        inicioEditor.setEditable(false);
        gReportes_topBottom.add(gReportes_inicioDate);

        JLabel gReportes_finalLabel = new JLabel("Fecha final");
        gReportes_finalLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
        gReportes_finalLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        gReportes_topBottom.add(gReportes_finalLabel);


        gReportes_finalDate = new JDateChooser();
        gReportes_finalDate.setDateFormatString("yyyy-MM-dd");
        JTextFieldDateEditor finalEditor = (JTextFieldDateEditor) gReportes_finalDate.getDateEditor();
        finalEditor.setEditable(false);
        gReportes_topBottom.add(gReportes_finalDate);


        gReportes_buscarButton = new JButton("BUSCAR");
        gReportes_buscarButton.setBackground(Color.WHITE);
        gReportes_buscarButton.setFocusable(false);
        gReportes_buscarButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
        gReportes_topBottom.add(gReportes_buscarButton);

        JPanel gReportesMid = new JPanel();
        gReportesMid.setBorder(new EmptyBorder(20, 20, 20, 20));
        gReportesFrame.getContentPane().add(gReportesMid, BorderLayout.CENTER);
        gReportesMid.setLayout(new BorderLayout(0, 0));

        JScrollPane gReportesSP = new JScrollPane();
        gReportesSP.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        gReportesMid.add(gReportesSP, BorderLayout.CENTER);

        gReportesTable = new JTable();
        gReportesTable.setModel(new DefaultTableModel(
                new Object[][] {
                },
                new String[] {
                        "ID Corte", "ID Venta", "ID Vendedor", "Fecha", "Turno", "Total"
                }
        ));
        gReportesTable.setFont(new Font("Dialog", Font.PLAIN, 14));
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

        JButton gReportes_volverButton = new JButton("VOLVER");
        gReportes_volverButton.setBackground(Color.WHITE);
        //gReportes_volverButton.setIcon(new ImageIcon("C:\\Users\\DELL\\OneDrive\\Documentos\\Tercer Semestre\\POO\\AmetsTravels\\resources\\left.png"));
        gReportes_volverButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
        gReportesBottom.add(gReportes_volverButton, BorderLayout.EAST);
        gReportes_volverButton.addActionListener(e -> {
            VistaMA maFrame = new VistaMA();
            maFrame.runFrame();
            gReportesFrame.setVisible(false);
        });
    }

}
