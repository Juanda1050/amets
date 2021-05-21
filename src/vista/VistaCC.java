package vista;

import java.awt.*;

import javax.swing.*;

import javax.swing.border.EmptyBorder;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class VistaCC {

    private JFrame frmAmetsTravels;
    private JTextField ccHorarioTF;
    private JTextField ccTotalTF;
    private JTable table;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        VistaCC cc = new VistaCC();
        cc.runFrame();
    }
    public void runFrame(){
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    VistaCC window = new VistaCC();
                    window.frmAmetsTravels.setVisible(true);
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
    private void initialize() {
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
        ButtomMid.setLayout(new GridLayout(0, 4, 25, 0));

        JLabel ccHorarioLabel = new JLabel("Horario de corte");
        ccHorarioLabel.setHorizontalAlignment(SwingConstants.CENTER);
        ccHorarioLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
        ButtomMid.add(ccHorarioLabel);

        ccHorarioTF = new JTextField();
        ccHorarioTF.setHorizontalAlignment(SwingConstants.CENTER);
        ccHorarioTF.setFont(new Font("Tahoma", Font.PLAIN, 20));
        ButtomMid.add(ccHorarioTF);
        ccHorarioTF.setColumns(10);

        JLabel ccTotalLabel = new JLabel("Total");
        ccTotalLabel.setHorizontalAlignment(SwingConstants.CENTER);
        ccTotalLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
        ButtomMid.add(ccTotalLabel);

        ccTotalTF = new JTextField();
        ccTotalTF.setHorizontalAlignment(SwingConstants.CENTER);
        ccTotalTF.setFont(new Font("Tahoma", Font.PLAIN, 20));
        ButtomMid.add(ccTotalTF);
        ccTotalTF.setColumns(10);

        JPanel ButtomSouth = new JPanel();
        ButtomSouth.setBorder(new EmptyBorder(20, 20, 20, 20));
        Buttom.add(ButtomSouth, BorderLayout.SOUTH);
        ButtomSouth.setLayout(new GridLayout(0, 2, 600, 0));

        JButton ccVolverButton = new JButton("Volver");
        ccVolverButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
        ButtomSouth.add(ccVolverButton);
        ccVolverButton.addActionListener(e -> {
            Retorno rtn = new Retorno();
            rtn.runReturn();
            frmAmetsTravels.setVisible(false);
        });

        JButton ccCorteButton = new JButton("Hacer corte");
        ccCorteButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
        ButtomSouth.add(ccCorteButton);
        ccCorteButton.addActionListener(e -> {
            /* Se realiza el corte */
            frmAmetsTravels.setVisible(false);
        });
    }

}