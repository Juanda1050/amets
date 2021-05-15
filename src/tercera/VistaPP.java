package tercera;

import primera.Retorno;
import segunda.MenuPrincipal;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class VistaPP {

    private JFrame frmAmetsTravel;
    private JTextField ppNumTarjetaTF;
    private JTextField ppExpiracionTf;
    private JTextField ppTitularTF;
    private JTextField ppCcvTF;

    /**
     * Launch the application.
     */
    public void runFrame(){
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    VistaPP window = new VistaPP();
                    window.frmAmetsTravel.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public VistaPP() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frmAmetsTravel = new JFrame();
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

        JComboBox comboBox = new JComboBox();
        comboBox.setFont(new Font("Tahoma", Font.PLAIN, 16));
        comboBox.setModel(new DefaultComboBoxModel(new String[] {"Tipo de Pago"}));
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

        JComboBox ppTarjetaBox = new JComboBox();
        ppTarjetaBox.setModel(new DefaultComboBoxModel(new String[] {"Tarjeta"}));
        ppTarjetaBox.setFont(new Font("Tahoma", Font.PLAIN, 18));
        MidMid.add(ppTarjetaBox);

        JLabel ppnumTarjetaLabel = new JLabel("Numero de tarjeta");
        ppnumTarjetaLabel.setHorizontalAlignment(SwingConstants.CENTER);
        ppnumTarjetaLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
        MidMid.add(ppnumTarjetaLabel);

        ppNumTarjetaTF = new JTextField();
        ppNumTarjetaTF.setFont(new Font("Tahoma", Font.PLAIN, 20));
        MidMid.add(ppNumTarjetaTF);
        ppNumTarjetaTF.setColumns(10);

        JLabel ppExpiracionLabel = new JLabel("Fecha de expiracion");
        ppExpiracionLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
        ppExpiracionLabel.setHorizontalAlignment(SwingConstants.CENTER);
        MidMid.add(ppExpiracionLabel);

        ppExpiracionTf = new JTextField();
        ppExpiracionTf.setFont(new Font("Tahoma", Font.PLAIN, 20));
        MidMid.add(ppExpiracionTf);
        ppExpiracionTf.setColumns(10);

        JLabel ppTitularLabel = new JLabel("Nombre del titular");
        ppTitularLabel.setHorizontalAlignment(SwingConstants.CENTER);
        ppTitularLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
        MidMid.add(ppTitularLabel);

        ppTitularTF = new JTextField();
        ppTitularTF.setFont(new Font("Tahoma", Font.PLAIN, 20));
        MidMid.add(ppTitularTF);
        ppTitularTF.setColumns(10);

        JLabel ppCcvLabel = new JLabel("Codigo de control");
        ppCcvLabel.setHorizontalAlignment(SwingConstants.CENTER);
        ppCcvLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
        MidMid.add(ppCcvLabel);

        ppCcvTF = new JTextField();
        ppCcvTF.setFont(new Font("Tahoma", Font.PLAIN, 20));
        MidMid.add(ppCcvTF);
        ppCcvTF.setColumns(10);

        JPanel Bottom = new JPanel();
        Bottom.setBorder(new EmptyBorder(20, 20, 20, 20));
        frmAmetsTravel.getContentPane().add(Bottom, BorderLayout.SOUTH);
        Bottom.setLayout(new GridLayout(0, 3, 25, 0));

        JButton ppVolverButton = new JButton("Volver");
        ppVolverButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
        Bottom.add(ppVolverButton);
        ppVolverButton.addActionListener(e -> {
            VistaSH shFrame = new VistaSH();
            shFrame.runFrame();
            frmAmetsTravel.setVisible(false);
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
            /* Se genera el ticket */
            Ticket tickt = new Ticket();
            tickt.runFrame();
            frmAmetsTravel.setVisible(false);
        });
    }

}