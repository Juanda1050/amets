package vista;

import controlador.CorteController;
import modelo.CorteDAO;
import modelo.VentaDAO;

import java.awt.*;

import javax.swing.*;

import javax.swing.border.EmptyBorder;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class VistaCC {

    public JFrame cCorteFrame;
    public JTextField cCorte_horarioTF, cCorte_totalTF, cCorte_fechaTF;
    public JTable cCorteTable;
    public JButton cCorte_corteB, cCorte_backB;

    public void runFrame(int agentID){
        EventQueue.invokeLater(() -> {
            try {
                VistaCC window = new VistaCC();
                VentaDAO dao = new VentaDAO();
                CorteController c = new CorteController(window, dao, agentID);
                window.cCorteFrame.setVisible(true);
                CorteDAO corteDAO = new CorteDAO();
                window.cCorte_horarioTF.setText(corteDAO.getHorario(agentID));
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDateTime now = LocalDateTime.now();
                window.cCorte_fechaTF.setText(dtf.format(now));
                window.cCorte_totalTF.setText(String.valueOf(c.getTotal()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public VistaCC() {
        initialize();
    }

    public void initialize() {

        cCorteFrame = new JFrame();
        cCorteFrame.setTitle("Corte de Caja");
        cCorteFrame.setExtendedState(Frame.MAXIMIZED_BOTH);
        cCorteFrame.setIconImage(Toolkit.getDefaultToolkit().getImage("resources/amets.jpg"));
        cCorteFrame.setBounds(100, 100, 1280, 720);
        cCorteFrame.getContentPane().setLayout(new BorderLayout(0, 0));

        JPanel cCorteTop = new JPanel();
        cCorteTop.setBorder(new EmptyBorder(20, 20, 20, 20));
        cCorteFrame.getContentPane().add(cCorteTop, BorderLayout.NORTH);
        cCorteTop.setLayout(new BorderLayout(0, 0));

        JLabel cCorteL = new JLabel("Corte de caja");
        cCorteL.setFont(new Font("Tahoma", Font.BOLD, 18));
        cCorteL.setHorizontalAlignment(SwingConstants.CENTER);
        cCorteTop.add(cCorteL, BorderLayout.CENTER);

        JPanel Mid = new JPanel();
        Mid.setBorder(new EmptyBorder(20, 20, 20, 20));
        cCorteFrame.getContentPane().add(Mid, BorderLayout.CENTER);
        Mid.setLayout(new BorderLayout(0, 0));

        JScrollPane cCorteSP = new JScrollPane();
        cCorteSP.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        Mid.add(cCorteSP, BorderLayout.CENTER);

        cCorteTable = new JTable();
        cCorteTable = new JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                return false;
            }
        };
        cCorteTable.setEnabled(false);
        cCorteTable.setBorder(new EmptyBorder(20, 20, 20, 20));
        cCorteTable.setModel(new DefaultTableModel(
                new Object[][] {
                },
                new String[] {
                        "ID Venta", "Descripcion", "Vendedor", "Forma de pago", "Importe"
                }
        ));
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );
        cCorteTable.getColumnModel().getColumn(0).setCellRenderer( centerRenderer );
        cCorteTable.setRowHeight(50);
        for(int i = 0; i < cCorteTable.getModel().getColumnCount(); i++){
            cCorteTable.getColumnModel().getColumn(i).setCellRenderer( centerRenderer );
        }

        JTableHeader tHeader = cCorteTable.getTableHeader();
        tHeader.setPreferredSize(new Dimension(0, 25));
        tHeader.setBackground(Color.decode("#094293"));
        tHeader.setForeground(Color.white);
        tHeader.setFont(new Font("Tahome", Font.BOLD, 16));
        cCorteTable.setFont(new Font("Tahome", Font.PLAIN, 14));
        cCorteSP.setViewportView(cCorteTable);

        JPanel cCorteBottom = new JPanel();
        cCorteBottom.setBorder(new EmptyBorder(20, 20, 20, 20));
        cCorteFrame.getContentPane().add(cCorteBottom, BorderLayout.SOUTH);
        cCorteBottom.setLayout(new BorderLayout(0, 0));

        JPanel cCorteBottom_M = new JPanel();
        cCorteBottom_M.setBorder(new EmptyBorder(40, 20, 20, 20));
        cCorteBottom.add(cCorteBottom_M, BorderLayout.CENTER);
        cCorteBottom_M.setLayout(new GridLayout(0, 6, 25, 0));

        JLabel cCorte_horarioL = new JLabel("Horario de corte");
        cCorte_horarioL.setHorizontalAlignment(SwingConstants.CENTER);
        cCorte_horarioL.setFont(new Font("Tahoma", Font.PLAIN, 16));
        cCorteBottom_M.add(cCorte_horarioL);

        cCorte_horarioTF = new JTextField();
        cCorte_horarioTF.setHorizontalAlignment(SwingConstants.CENTER);
        cCorte_horarioTF.setFont(new Font("Tahoma", Font.PLAIN, 20));
        cCorte_horarioTF.setEditable(false);
        cCorteBottom_M.add(cCorte_horarioTF);

        JLabel cCorte_fechaL = new JLabel("Fecha de corte");
        cCorte_fechaL.setHorizontalAlignment(SwingConstants.CENTER);
        cCorte_fechaL.setFont(new Font("Tahoma", Font.PLAIN, 16));
        cCorteBottom_M.add(cCorte_fechaL);

        cCorte_fechaTF = new JTextField();
        cCorte_fechaTF.setHorizontalAlignment(SwingConstants.CENTER);
        cCorte_fechaTF.setFont(new Font("Tahoma", Font.PLAIN, 20));
        cCorte_fechaTF.setEditable(false);
        cCorteBottom_M.add(cCorte_fechaTF);

        JLabel cCorte_totalL = new JLabel("Total");
        cCorte_totalL.setHorizontalAlignment(SwingConstants.CENTER);
        cCorte_totalL.setFont(new Font("Tahoma", Font.PLAIN, 16));
        cCorteBottom_M.add(cCorte_totalL);

        cCorte_totalTF = new JTextField();
        cCorte_totalTF.setHorizontalAlignment(SwingConstants.CENTER);
        cCorte_totalTF.setFont(new Font("Tahoma", Font.PLAIN, 20));
        cCorte_totalTF.setEditable(false);
        cCorteBottom_M.add(cCorte_totalTF);

        JPanel cCorteBottom_B = new JPanel();
        cCorteBottom_B.setBorder(new EmptyBorder(20, 20, 20, 20));
        cCorteBottom.add(cCorteBottom_B, BorderLayout.SOUTH);
        cCorteBottom_B.setLayout(new GridLayout(0, 2, 600, 0));

        cCorte_backB = new JButton("Volver");
        cCorte_backB.setFont(new Font("Tahoma", Font.PLAIN, 16));
        cCorte_backB.setIcon(new ImageIcon("resources/left.png"));
        cCorteBottom_B.add(cCorte_backB);

        cCorte_corteB = new JButton("Hacer corte");
        cCorte_corteB.setFont(new Font("Tahoma", Font.PLAIN, 16));
        cCorte_corteB.setIcon(new ImageIcon("resources/caja.png"));
        cCorteBottom_B.add(cCorte_corteB);
    }

}