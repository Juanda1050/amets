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

    public JFrame frmAmetsTravel;
    public JTextField ppNumTarjetaTF;
    public JTextFieldDateEditor ppExpiracionTf;
    public JTextField ppTitularTF;
    public JTextField ppCcvTF;
    public JComboBox comboBox, ppTarjetaBox;
    public JButton ppVolverButton, ppMenuButton, ppSiguienteButton;

    /**
     * Launch the application.
     */
    public void runFrame(String desc, double precio, int agentID, Ticket ticket){
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    VistaPP window = new VistaPP();
                    PagoDAO pagoDAO = new PagoDAO();
                    PagoController pc = new PagoController(window, pagoDAO, agentID, desc, precio, ticket);
                    window.frmAmetsTravel.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public VistaPP() {
        initialize();
    }

    public void initialize() {

        frmAmetsTravel = new JFrame();
        frmAmetsTravel.setTitle("Amets Travels");
        frmAmetsTravel.setExtendedState(Frame.MAXIMIZED_BOTH);
        frmAmetsTravel.setBounds(100, 100, 1280, 720);
        frmAmetsTravel.getContentPane().setLayout(new BorderLayout(0, 0));

        JPanel Top = new JPanel();
        Top.setBorder(new EmptyBorder(20, 20, 20, 20));
        frmAmetsTravel.getContentPane().add(Top, BorderLayout.NORTH);
        Top.setLayout(new BorderLayout(0, 0));

        JLabel ppLabel = new JLabel("Proceso de pago");
        ppLabel.setHorizontalAlignment(SwingConstants.CENTER);
        ppLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
        Top.add(ppLabel, BorderLayout.CENTER);

        JPanel TopSouth = new JPanel();
        TopSouth.setBorder(new EmptyBorder(10, 10, 10, 10));
        Top.add(TopSouth, BorderLayout.SOUTH);
        TopSouth.setLayout(new GridLayout(0, 2, 0, 0));

        JLabel lblNewLabel = new JLabel("Seleccione una forma de pago");
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
        TopSouth.add(lblNewLabel);

        JPanel Mid = new JPanel();
        Mid.setBorder(new EmptyBorder(0, 20, 0, 20));
        frmAmetsTravel.getContentPane().add(Mid, BorderLayout.CENTER);
        Mid.setLayout(new BorderLayout(0, 0));

        JPanel MidTop = new JPanel();
        MidTop.setBorder(new EmptyBorder(0, 20, 20, 0));
        Mid.add(MidTop, BorderLayout.NORTH);
        MidTop.setLayout(new GridLayout(0, 2, 0, 0));

        comboBox = new JComboBox();
        comboBox.setFont(new Font("Tahoma", Font.PLAIN, 16));
        comboBox.setModel(new DefaultComboBoxModel(new String[] {"Tarjeta", "Efectivo"}));
        comboBox.setSelectedIndex(-1);
        MidTop.add(comboBox);

        JLabel lblNewLabel_1 = new JLabel("");
        MidTop.add(lblNewLabel_1);

        JPanel MidMid = new JPanel();
        MidMid.setBorder(new EmptyBorder(20, 20, 20, 20));
        Mid.add(MidMid, BorderLayout.CENTER);
        MidMid.setLayout(new GridLayout(0, 2, 10, 20));

        JLabel ppTarjetaLabel = new JLabel("Tipo de tarjeta");
        ppTarjetaLabel.setHorizontalAlignment(SwingConstants.CENTER);
        ppTarjetaLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
        MidMid.add(ppTarjetaLabel);

        ppTarjetaBox = new JComboBox();
        ppTarjetaBox.setEnabled(false);
        ppTarjetaBox.setModel(new DefaultComboBoxModel(new String[] {"Visa","Mastercard"}));
        ppTarjetaBox.setFont(new Font("Tahoma", Font.PLAIN, 18));
        ppTarjetaBox.setSelectedIndex(-1);
        MidMid.add(ppTarjetaBox);

        JLabel ppnumTarjetaLabel = new JLabel("Numero de tarjeta");
        ppnumTarjetaLabel.setHorizontalAlignment(SwingConstants.CENTER);
        ppnumTarjetaLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
        MidMid.add(ppnumTarjetaLabel);

        ppNumTarjetaTF = new JTextField();
        ppNumTarjetaTF.setEditable(false);
        ppNumTarjetaTF.setFont(new Font("Tahoma", Font.PLAIN, 20));
        MidMid.add(ppNumTarjetaTF);
        ppNumTarjetaTF.setColumns(10);
        ppNumTarjetaTF.addKeyListener(new KeyAdapter()
        {
            public void keyTyped(KeyEvent e)
            {
                char caracter = e.getKeyChar();
                if(((caracter < '0') || (caracter > '9')) && (caracter != '\b' /*corresponde a BACK_SPACE*/) || ppNumTarjetaTF.getText().length()== 16)
                {
                    e.consume();
                }
            }
        });

        JLabel ppExpiracionLabel = new JLabel("Fecha de expiracion");
        ppExpiracionLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
        ppExpiracionLabel.setHorizontalAlignment(SwingConstants.CENTER);
        MidMid.add(ppExpiracionLabel);

        ppExpiracionTf = new JTextFieldDateEditor("MM/yy","##/##",'_');
        ppExpiracionTf.setEnabled(false);
        ppExpiracionTf.setFont(new Font("Tahoma", Font.PLAIN, 20));
        MidMid.add(ppExpiracionTf);

        JLabel ppTitularLabel = new JLabel("Nombre del titular");
        ppTitularLabel.setHorizontalAlignment(SwingConstants.CENTER);
        ppTitularLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
        MidMid.add(ppTitularLabel);

        ppTitularTF = new JTextField();
        ppTitularTF.setEditable(false);
        ppTitularTF.setFont(new Font("Tahoma", Font.PLAIN, 20));
        MidMid.add(ppTitularTF);
        ppTitularTF.addKeyListener(new KeyAdapter()
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

        JLabel ppCcvLabel = new JLabel("Codigo de control");
        ppCcvLabel.setHorizontalAlignment(SwingConstants.CENTER);
        ppCcvLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
        MidMid.add(ppCcvLabel);

        ppCcvTF = new JTextField();
        ppCcvTF.setEditable(false);
        ppCcvTF.setFont(new Font("Tahoma", Font.PLAIN, 20));
        MidMid.add(ppCcvTF);
        ppCcvTF.addKeyListener(new KeyAdapter()
        {
            public void keyTyped(KeyEvent e)
            {
                char caracter = e.getKeyChar();
                if(((caracter < '0') || (caracter > '9')) && (caracter != '\b') || ppCcvTF.getText().length()==3)
                {
                    e.consume();
                }
            }
        });

        JPanel Bottom = new JPanel();
        Bottom.setBorder(new EmptyBorder(20, 20, 20, 20));
        frmAmetsTravel.getContentPane().add(Bottom, BorderLayout.SOUTH);
        Bottom.setLayout(new GridLayout(0, 3, 25, 0));

        ppVolverButton = new JButton("Volver");
        ppVolverButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
        Bottom.add(ppVolverButton);

        ppMenuButton = new JButton("Menu");
        ppMenuButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
        Bottom.add(ppMenuButton);

        ppSiguienteButton = new JButton("Siguiente");
        ppSiguienteButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
        Bottom.add(ppSiguienteButton);
    }
}