package tercera;

import Controlador.CorteController;
import modelo.CorteDAO;
import modelo.VentaDAO;
import primera.Retorno;
import segunda.MenuPrincipal;

import java.awt.*;

import javax.swing.*;

import javax.swing.border.EmptyBorder;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class VistaCC {

    private JFrame frmAmetsTravels;
    public JTextField ccHorarioTF;
    public JTextField ccTotalTF;
    public JTextField ccFechaTF;
    public JTable table;
    public JButton ccCorteButton;
    CorteDAO corteDAO = new CorteDAO();
    /**
     * Launch the application.
     */

    public void runFrame(int agentID){
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    VistaCC window = new VistaCC();
                    VentaDAO dao = new VentaDAO();
                    CorteController c = new CorteController(window, dao, agentID);
                    window.frmAmetsTravels.setVisible(true);
                    window.ccHorarioTF.setText(corteDAO.getHorario(agentID));
                    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                    LocalDateTime now = LocalDateTime.now();
                    window.ccFechaTF.setText(dtf.format(now));
                    window.ccTotalTF.setText(String.valueOf(c.getTotal()));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public VistaCC() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    public void initialize() {

        frmAmetsTravels = new JFrame();
        frmAmetsTravels.setTitle("Amets Travels");
        frmAmetsTravels.setExtendedState(Frame.MAXIMIZED_BOTH);
        frmAmetsTravels.setBounds(100, 100, 1280, 720);
        frmAmetsTravels.getContentPane().setLayout(new BorderLayout(0, 0));
        frmAmetsTravels.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                int result = JOptionPane.showConfirmDialog(frmAmetsTravels, "¿Desea cerrar el programa?", "Salir del programa", JOptionPane.YES_NO_OPTION);
                if (result == JOptionPane.YES_OPTION)
                {
                    frmAmetsTravels.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                }
                else if (result == JOptionPane.NO_OPTION)
                {
                    frmAmetsTravels.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                }
            }
        });

        JPanel Top = new JPanel();
        Top.setBorder(new EmptyBorder(20, 20, 20, 20));
        frmAmetsTravels.getContentPane().add(Top, BorderLayout.NORTH);
        Top.setLayout(new BorderLayout(0, 0));

        JLabel ccLabel = new JLabel("Corte de caja");
        ccLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
        ccLabel.setHorizontalAlignment(SwingConstants.CENTER);
        Top.add(ccLabel, BorderLayout.CENTER);

        JPanel Mid = new JPanel();
        Mid.setBorder(new EmptyBorder(20, 20, 20, 20));
        frmAmetsTravels.getContentPane().add(Mid, BorderLayout.CENTER);
        Mid.setLayout(new BorderLayout(0, 0));

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        Mid.add(scrollPane, BorderLayout.CENTER);

        table = new JTable();
        table.setEnabled(false);
        table.setBorder(new EmptyBorder(20, 20, 20, 20));
        table.setFont(new Font("Tahoma", Font.PLAIN, 16));
        table.setModel(new DefaultTableModel(
                new Object[][] {
                },
                new String[] {
                        "ID Venta", "Descripcion", "Vendedor", "Forma de pago", "Importe"
                }
        ));

        JTableHeader tHeader = table.getTableHeader();
        tHeader.setPreferredSize(new Dimension(0, 25));
        tHeader.setBackground(Color.decode("#094293"));
        tHeader.setForeground(Color.white);
        tHeader.setFont(new Font("Tahome", Font.BOLD, 16));
        table.setFont(new Font("Tahome", Font.PLAIN, 14));
        scrollPane.setViewportView(table);

        JPanel Buttom = new JPanel();
        Buttom.setBorder(new EmptyBorder(20, 20, 20, 20));
        frmAmetsTravels.getContentPane().add(Buttom, BorderLayout.SOUTH);
        Buttom.setLayout(new BorderLayout(0, 0));

        JPanel ButtomMid = new JPanel();
        ButtomMid.setBorder(new EmptyBorder(40, 20, 20, 20));
        Buttom.add(ButtomMid, BorderLayout.CENTER);
        ButtomMid.setLayout(new GridLayout(0, 6, 25, 0));

        JLabel ccHorarioLabel = new JLabel("Horario de corte");
        ccHorarioLabel.setHorizontalAlignment(SwingConstants.CENTER);
        ccHorarioLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
        ButtomMid.add(ccHorarioLabel);

        ccHorarioTF = new JTextField();
        ccHorarioTF.setHorizontalAlignment(SwingConstants.CENTER);
        ccHorarioTF.setFont(new Font("Tahoma", Font.PLAIN, 20));
        ccHorarioTF.setEditable(false);
        ButtomMid.add(ccHorarioTF);

        JLabel ccFechaLbl = new JLabel("Fecha de corte");
        ccFechaLbl.setHorizontalAlignment(SwingConstants.CENTER);
        ccFechaLbl.setFont(new Font("Tahoma", Font.PLAIN, 16));
        ButtomMid.add(ccFechaLbl);

        ccFechaTF = new JTextField();
        ccFechaTF.setHorizontalAlignment(SwingConstants.CENTER);
        ccFechaTF.setFont(new Font("Tahoma", Font.PLAIN, 20));
        ccFechaTF.setEditable(false);
        ButtomMid.add(ccFechaTF);

        JLabel ccTotalLabel = new JLabel("Total");
        ccTotalLabel.setHorizontalAlignment(SwingConstants.CENTER);
        ccTotalLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
        ButtomMid.add(ccTotalLabel);

        ccTotalTF = new JTextField();
        ccTotalTF.setHorizontalAlignment(SwingConstants.CENTER);
        ccTotalTF.setFont(new Font("Tahoma", Font.PLAIN, 20));
        ccTotalTF.setEditable(false);
        ButtomMid.add(ccTotalTF);

        JPanel ButtomSouth = new JPanel();
        ButtomSouth.setBorder(new EmptyBorder(20, 20, 20, 20));
        Buttom.add(ButtomSouth, BorderLayout.SOUTH);
        ButtomSouth.setLayout(new GridLayout(0, 2, 600, 0));

        JButton ccVolverButton = new JButton("Volver");
        ccVolverButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
        ButtomSouth.add(ccVolverButton);
        ccVolverButton.addActionListener(e -> {
            Retorno rtn = new Retorno();
            frmAmetsTravels.setVisible(rtn.runReturn());
        });

        ccCorteButton = new JButton("Hacer corte");
        ccCorteButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
        ButtomSouth.add(ccCorteButton);
    }

}