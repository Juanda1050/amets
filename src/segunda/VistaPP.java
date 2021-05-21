package segunda;

import com.toedter.calendar.JTextFieldDateEditor;
import modelo.PagoDAO;
import modelo.SelecPaqDAO;
import primera.Retorno;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Date;

public class VistaPP extends SeleccionarPaquete{

    protected JFrame frmAmetsTravel;
    private JTextField ppNumTarjetaTF;
    private JTextFieldDateEditor ppExpiracionTf;
    private JTextField ppTitularTF;
    private JTextField ppCcvTF;
    private JComboBox comboBox, ppTarjetaBox;

    /**
     * Launch the application.
     */

    public void initialize(String desc, double precio, int agentID) {

        frmAmetsTravel = new JFrame();
        frmAmetsTravel.setVisible(true);
        frmAmetsTravel.setTitle("Amets Travels");
        frmAmetsTravel.setExtendedState(Frame.MAXIMIZED_BOTH);
        frmAmetsTravel.setBounds(100, 100, 1280, 720);
        frmAmetsTravel.getContentPane().setLayout(new BorderLayout(0, 0));
        frmAmetsTravel.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                int result = JOptionPane.showConfirmDialog(frmAmetsTravel, "¿Desea cerrar el programa?", "Salir del programa", JOptionPane.YES_NO_OPTION);
                if (result == JOptionPane.YES_OPTION)
                {
                    frmAmetsTravel.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                }
                else if (result == JOptionPane.NO_OPTION)
                {
                    frmAmetsTravel.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                }
            }
        });

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
        comboBox.addActionListener (e -> {
            if(comboBox.getSelectedItem()=="Tarjeta"){
                ppTarjetaBox.setEnabled(true);
                ppNumTarjetaTF.setEditable(true);
                ppExpiracionTf.setEnabled(true);
                ppTitularTF.setEditable(true);
                ppCcvTF.setEditable(true);
            }else{
                ppTarjetaBox.setEnabled(false);
                ppNumTarjetaTF.setEditable(false);
                ppExpiracionTf.setEnabled(false);
                ppTitularTF.setEditable(false);
                ppCcvTF.setEditable(false);
            }
        });

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
                if(((caracter < '0') || (caracter > '9')) && (caracter != '\b' /*corresponde a BACK_SPACE*/) || ppNumTarjetaTF.getText().length()== 19)
                {
                    e.consume();
                }else if(ppNumTarjetaTF.getText().length()== 4 || ppNumTarjetaTF.getText().length()== 9 ||ppNumTarjetaTF.getText().length()== 14){
                    ppNumTarjetaTF.setText(ppNumTarjetaTF.getText()+"-");
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

        JButton ppVolverButton = new JButton("Volver");
        ppVolverButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
        Bottom.add(ppVolverButton);
        ppVolverButton.addActionListener(e -> {

        });

        JButton ppMenuButton = new JButton("Menu");
        ppMenuButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
        Bottom.add(ppMenuButton);
        ppMenuButton.addActionListener(e -> {
            Retorno rtn = new Retorno();
            frmAmetsTravel.setVisible(rtn.runReturn());
        });

        JButton ppSiguienteButton = new JButton("Siguiente");
        ppSiguienteButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
        Bottom.add(ppSiguienteButton);
        ppSiguienteButton.addActionListener(e -> {
            PagoDAO pagoDAO = new PagoDAO();
            Date currentDate = new Date();
            if(comboBox.getSelectedItem()=="Tarjeta" && comboBox.getSelectedIndex()!=-1 && ppTarjetaBox.getSelectedIndex()!=-1 && ppNumTarjetaTF.getText().length()==19 && ppExpiracionTf.getText().length()==5 && ppTitularTF.getText().length()!=0 && ppCcvTF.getText().length()==3){
                if(ppExpiracionTf.getDate()==null || currentDate.after(ppExpiracionTf.getDate())){
                    JOptionPane.showMessageDialog(null, "TARJETA EXPIRADA", "MÉTODO DE PAGO NO ACEPTADO", JOptionPane.WARNING_MESSAGE);
                }else {
                    ticket.txtrCompraRealizada.setText("Compra Realizada\nTipo de pago: Tarjeta");
                    char last1digit = ppNumTarjetaTF.getText().charAt(15);
                    char last2digit = ppNumTarjetaTF.getText().charAt(16);
                    char last3digit = ppNumTarjetaTF.getText().charAt(17);
                    char last4digit = ppNumTarjetaTF.getText().charAt(18);
                    ticket.txtrNumDeTarjeta.setText("Tipo de Tarjeta: " + ppTarjetaBox.getSelectedItem() + "\nNum. de Tarjeta: ****-****-****-" + last1digit+last2digit+last3digit+last4digit + "\nTitular: " + ppTitularTF.getText());
                    ticket.txtrAsfdas.setText("Descripcion: " + desc);
                    pagoDAO.guardarVenta(agentID, desc, String.valueOf(comboBox.getSelectedItem()), precio);
                    frmAmetsTravel.setVisible(false);
                    ticket.frame.setVisible(true);
                }
                //agregar opción para efectivo, se llena solo el campo de tipo de pago y descripcion
            }else if(comboBox.getSelectedItem()=="Efectivo"){
                ticket.txtrCompraRealizada.setText("Compra Realizada\nTipo de pago: Efectivo");
                ticket.txtrAsfdas.setText("Descripcion: " + desc);
                pagoDAO.guardarVenta(agentID, desc, String.valueOf(comboBox.getSelectedItem()), precio);
                frmAmetsTravel.setVisible(false);
                ticket.frame.setVisible(true);
            }else{
                JOptionPane.showMessageDialog(null, "LLENE TODOS LOS ESPACIOS CORRECTAMENTE","DATOS INCOMPLETOS", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}