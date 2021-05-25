package vista;

import controlador.PagoController;
import com.toedter.calendar.JTextFieldDateEditor;
import modelo.PagoDAO;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class VistaPP{

    public JFrame pPagoFrame;
    public JTextField pPago_numTF;
    public JTextFieldDateEditor pPago_expTF;
    public JTextField pPago_titularTF;
    public JTextField pPago_ccvTF;
    public JComboBox pPago_tipoCB, pPago_tarjetaCB;
    public JButton pPago_backB, pPago_menuB, pPago_nextB;

    public void runFrame(String desc, float precio, int agentID, Ticket ticket){
        EventQueue.invokeLater(() -> {
            try {
                VistaPP window = new VistaPP();
                PagoDAO pagoDAO = new PagoDAO();
                PagoController pc = new PagoController(window, pagoDAO, agentID, desc, precio, ticket);
                window.pPagoFrame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public VistaPP() {
        initialize();
    }

    public void initialize() {

        pPagoFrame = new JFrame("Proceso de Pago");
        pPagoFrame.setExtendedState(Frame.MAXIMIZED_BOTH);
        pPagoFrame.setIconImage(Toolkit.getDefaultToolkit().getImage("resources/amets.jpg"));
        pPagoFrame.setBounds(100, 100, 1280, 720);
        pPagoFrame.getContentPane().setLayout(new BorderLayout(0, 0));

        JPanel pPagoTop = new JPanel();
        pPagoTop.setBorder(new EmptyBorder(20, 20, 20, 20));
        pPagoFrame.getContentPane().add(pPagoTop, BorderLayout.NORTH);
        pPagoTop.setLayout(new BorderLayout(0, 0));

        JLabel pPagoL = new JLabel("Proceso de pago");
        pPagoL.setHorizontalAlignment(SwingConstants.CENTER);
        pPagoL.setFont(new Font("Tahoma", Font.BOLD, 18));
        pPagoTop.add(pPagoL, BorderLayout.CENTER);

        JPanel pPagoTop_B = new JPanel();
        pPagoTop_B.setBorder(new EmptyBorder(10, 10, 10, 10));
        pPagoTop.add(pPagoTop_B, BorderLayout.SOUTH);
        pPagoTop_B.setLayout(new GridLayout(0, 2, 0, 0));

        JLabel pPago_tipoL = new JLabel("Seleccione una forma de pago");
        pPago_tipoL.setHorizontalAlignment(SwingConstants.CENTER);
        pPago_tipoL.setFont(new Font("Tahoma", Font.PLAIN, 16));
        pPagoTop_B.add(pPago_tipoL);

        JPanel pPagoMid = new JPanel();
        pPagoMid.setBorder(new EmptyBorder(0, 20, 0, 20));
        pPagoFrame.getContentPane().add(pPagoMid, BorderLayout.CENTER);
        pPagoMid.setLayout(new BorderLayout(0, 0));

        JPanel pPagopPagoMid_N = new JPanel();
        pPagopPagoMid_N.setBorder(new EmptyBorder(0, 20, 20, 0));
        pPagoMid.add(pPagopPagoMid_N, BorderLayout.NORTH);
        pPagopPagoMid_N.setLayout(new GridLayout(0, 2, 0, 0));

        pPago_tipoCB = new JComboBox();
        pPago_tipoCB.setFont(new Font("Tahoma", Font.PLAIN, 16));
        pPago_tipoCB.setModel(new DefaultComboBoxModel(new String[] {"Tarjeta", "Efectivo"}));
        pPago_tipoCB.setSelectedIndex(-1);
        pPagopPagoMid_N.add(pPago_tipoCB);

        //JLabel pPago_tipoL_1 = new JLabel("");
        //pPagopPagoMid_N.add(pPago_tipoL_1);

        JPanel pPagopPagoMid_M = new JPanel();
        pPagopPagoMid_M.setBorder(new EmptyBorder(20, 20, 20, 20));
        pPagoMid.add(pPagopPagoMid_M, BorderLayout.CENTER);
        pPagopPagoMid_M.setLayout(new GridLayout(0, 2, 10, 20));

        JLabel pPago_tarjetaL = new JLabel("Tipo de tarjeta");
        pPago_tarjetaL.setHorizontalAlignment(SwingConstants.CENTER);
        pPago_tarjetaL.setFont(new Font("Tahoma", Font.PLAIN, 18));
        pPagopPagoMid_M.add(pPago_tarjetaL);

        pPago_tarjetaCB = new JComboBox();
        pPago_tarjetaCB.setEnabled(false);
        pPago_tarjetaCB.setModel(new DefaultComboBoxModel(new String[] {"Visa","Mastercard"}));
        pPago_tarjetaCB.setFont(new Font("Tahoma", Font.PLAIN, 18));
        pPago_tarjetaCB.setSelectedIndex(-1);
        pPagopPagoMid_M.add(pPago_tarjetaCB);

        JLabel pPago_numL = new JLabel("Numero de tarjeta");
        pPago_numL.setHorizontalAlignment(SwingConstants.CENTER);
        pPago_numL.setFont(new Font("Tahoma", Font.PLAIN, 18));
        pPagopPagoMid_M.add(pPago_numL);

        pPago_numTF = new JTextField();
        pPago_numTF.setEditable(false);
        pPago_numTF.setFont(new Font("Tahoma", Font.PLAIN, 20));
        pPagopPagoMid_M.add(pPago_numTF);
        pPago_numTF.setColumns(10);
        pPago_numTF.addKeyListener(new KeyAdapter()
        {
            public void keyTyped(KeyEvent e)
            {
                char caracter = e.getKeyChar();
                if(((caracter < '0') || (caracter > '9')) && (caracter != '\b') || pPago_numTF.getText().length()== 16)
                {
                    e.consume();
                }
            }
        });

        JLabel pPago_expL = new JLabel("Fecha de expiracion");
        pPago_expL.setFont(new Font("Tahoma", Font.PLAIN, 18));
        pPago_expL.setHorizontalAlignment(SwingConstants.CENTER);
        pPagopPagoMid_M.add(pPago_expL);

        pPago_expTF = new JTextFieldDateEditor("MM/yy","##/##",'_');
        pPago_expTF.setEnabled(false);
        pPago_expTF.setFont(new Font("Tahoma", Font.PLAIN, 20));
        pPagopPagoMid_M.add(pPago_expTF);

        JLabel pPago_titularL = new JLabel("Nombre del titular");
        pPago_titularL.setHorizontalAlignment(SwingConstants.CENTER);
        pPago_titularL.setFont(new Font("Tahoma", Font.PLAIN, 18));
        pPagopPagoMid_M.add(pPago_titularL);

        pPago_titularTF = new JTextField();
        pPago_titularTF.setEditable(false);
        pPago_titularTF.setFont(new Font("Tahoma", Font.PLAIN, 20));
        pPagopPagoMid_M.add(pPago_titularTF);
        pPago_titularTF.addKeyListener(new KeyAdapter()
        {
            public void keyTyped(KeyEvent e)
            {
                char caracter = e.getKeyChar();
                if(!(Character.isLetter(caracter)||Character.isWhitespace(caracter)||Character.isISOControl(caracter)))
                {
                    e.consume();
                }
            }
        });

        JLabel pPago_ccvL = new JLabel("Codigo de control");
        pPago_ccvL.setHorizontalAlignment(SwingConstants.CENTER);
        pPago_ccvL.setFont(new Font("Tahoma", Font.PLAIN, 18));
        pPagopPagoMid_M.add(pPago_ccvL);

        pPago_ccvTF = new JTextField();
        pPago_ccvTF.setEditable(false);
        pPago_ccvTF.setFont(new Font("Tahoma", Font.PLAIN, 20));
        pPagopPagoMid_M.add(pPago_ccvTF);
        pPago_ccvTF.addKeyListener(new KeyAdapter()
        {
            public void keyTyped(KeyEvent e)
            {
                char caracter = e.getKeyChar();
                if(((caracter < '0') || (caracter > '9')) && (caracter != '\b') || pPago_ccvTF.getText().length()==3)
                {
                    e.consume();
                }
            }
        });

        JPanel pPagoBottom = new JPanel();
        pPagoBottom.setBorder(new EmptyBorder(20, 20, 20, 20));
        pPagoFrame.getContentPane().add(pPagoBottom, BorderLayout.SOUTH);
        pPagoBottom.setLayout(new GridLayout(0, 3, 25, 0));

        pPago_backB = new JButton("Volver");
        pPago_backB.setFont(new Font("Tahoma", Font.PLAIN, 16));
        pPago_backB.setIcon(new ImageIcon("resources/left.png"));
        pPagoBottom.add(pPago_backB);

        pPago_menuB = new JButton("Menu");
        pPago_menuB.setFont(new Font("Tahoma", Font.PLAIN, 16));
        pPago_menuB.setIcon(new ImageIcon("resources/home.png"));
        pPagoBottom.add(pPago_menuB);

        pPago_nextB = new JButton("Siguiente");
        pPago_nextB.setFont(new Font("Tahoma", Font.PLAIN, 16));
        pPago_nextB.setIcon(new ImageIcon("resources/siguiente.png"));
        pPagoBottom.add(pPago_nextB);
    }
}