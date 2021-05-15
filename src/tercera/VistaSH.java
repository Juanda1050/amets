package tercera;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import com.toedter.calendar.JDateChooser;
import primera.Retorno;
import segunda.MenuPrincipal;
import segunda.SeleccionarVuelos;

public class VistaSH extends JFrame{

    private JFrame frmAmetsTravels;
    private JTextField shDireccionTF;
    private JTextField shClasifTF;
    private JTextField shHuespedesTF;
    private JTextField shRegimenTF;
    private JTextField shDispoTF;

    /**
     * Launch the application.
     */
    public void runFrame(){
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    VistaSH window = new VistaSH();
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
    public VistaSH() {
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
        Top.setBorder(new EmptyBorder(20, 20, 20, 0));
        frmAmetsTravels.getContentPane().add(Top, BorderLayout.NORTH);
        Top.setLayout(new BorderLayout(0, 0));

        JLabel shLabel = new JLabel("Seleccione un Hotel");
        shLabel.setHorizontalAlignment(SwingConstants.CENTER);
        shLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
        Top.add(shLabel, BorderLayout.CENTER);

        JPanel TopSouth = new JPanel();
        TopSouth.setBorder(new EmptyBorder(20, 20, 20, 20));
        Top.add(TopSouth, BorderLayout.SOUTH);
        TopSouth.setLayout(new GridLayout(0, 2, 0, 0));

        JComboBox comboBox = new JComboBox();
        comboBox.setModel(new DefaultComboBoxModel(new String[] {"Hotel"}));
        comboBox.setFont(new Font("Tahoma", Font.PLAIN, 16));
        TopSouth.add(comboBox);

        JPanel Mid = new JPanel();
        Mid.setBorder(new EmptyBorder(20, 20, 20, 20));
        frmAmetsTravels.getContentPane().add(Mid, BorderLayout.CENTER);
        Mid.setLayout(new GridLayout(0, 2, 25, 15));

        JLabel shDireccion = new JLabel("Direcci\u00F3n");
        shDireccion.setHorizontalAlignment(SwingConstants.CENTER);
        shDireccion.setFont(new Font("Tahoma", Font.PLAIN, 18));
        Mid.add(shDireccion);

        shDireccionTF = new JTextField();
        shDireccionTF.setHorizontalAlignment(SwingConstants.CENTER);
        shDireccionTF.setEditable(false);
        shDireccionTF.setFont(new Font("Tahoma", Font.PLAIN, 20));
        Mid.add(shDireccionTF);
        shDireccionTF.setColumns(10);

        JLabel shClasif = new JLabel("Clasificaci\u00F3n");
        shClasif.setHorizontalAlignment(SwingConstants.CENTER);
        shClasif.setFont(new Font("Tahoma", Font.PLAIN, 18));
        Mid.add(shClasif);

        shClasifTF = new JTextField();
        shClasifTF.setEditable(false);
        shClasifTF.setFont(new Font("Tahoma", Font.PLAIN, 20));
        Mid.add(shClasifTF);
        shClasifTF.setColumns(10);

        JLabel shHuespedes = new JLabel("Hu\u00E9spedes");
        shHuespedes.setHorizontalAlignment(SwingConstants.CENTER);
        shHuespedes.setFont(new Font("Tahoma", Font.PLAIN, 18));
        Mid.add(shHuespedes);

        shHuespedesTF = new JTextField();
        shHuespedesTF.setEditable(false);
        shHuespedesTF.setFont(new Font("Tahoma", Font.PLAIN, 20));
        Mid.add(shHuespedesTF);
        shHuespedesTF.setColumns(10);

        JLabel shRegimen = new JLabel("R\u00E9gimen");
        shRegimen.setHorizontalAlignment(SwingConstants.CENTER);
        shRegimen.setFont(new Font("Tahoma", Font.PLAIN, 18));
        Mid.add(shRegimen);

        shRegimenTF = new JTextField();
        shRegimenTF.setEditable(false);
        shRegimenTF.setFont(new Font("Tahoma", Font.PLAIN, 20));
        Mid.add(shRegimenTF);
        shRegimenTF.setColumns(10);

        JLabel shDispo = new JLabel("Disponibilidad");
        shDispo.setHorizontalAlignment(SwingConstants.CENTER);
        shDispo.setFont(new Font("Tahoma", Font.PLAIN, 18));
        Mid.add(shDispo);

        shDispoTF = new JTextField();
        shDispoTF.setEditable(false);
        shDispoTF.setFont(new Font("Tahoma", Font.PLAIN, 20));
        Mid.add(shDispoTF);
        shDispoTF.setColumns(10);

        JPanel Bottom = new JPanel();
        Bottom.setBorder(new EmptyBorder(40, 40, 40, 40));
        frmAmetsTravels.getContentPane().add(Bottom, BorderLayout.SOUTH);
        Bottom.setLayout(new BorderLayout(25, 0));

        JPanel BottomTop = new JPanel();
        BottomTop.setBorder(new EmptyBorder(20, 20, 20, 20));
        Bottom.add(BottomTop, BorderLayout.NORTH);
        BottomTop.setLayout(new GridLayout(0, 4, 25, 0));

        JLabel lblNewLabel = new JLabel("Fecha de llegada");
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
        BottomTop.add(lblNewLabel);

        JDateChooser dateChooser = new JDateChooser();
        BottomTop.add(dateChooser);

        JLabel lblNewLabel_1 = new JLabel("Fecha de salida");
        lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
        BottomTop.add(lblNewLabel_1);

        JDateChooser dateChooser_1 = new JDateChooser();
        BottomTop.add(dateChooser_1);

        JPanel BottomSouth = new JPanel();
        BottomSouth.setBorder(new EmptyBorder(20, 20, 20, 20));
        Bottom.add(BottomSouth, BorderLayout.SOUTH);
        BottomSouth.setLayout(new GridLayout(0, 3, 25, 0));

        JButton btnNewButton = new JButton("Volver");
        btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
        BottomSouth.add(btnNewButton);
        btnNewButton.addActionListener(e -> {
            SeleccionarVuelos svFrame = new SeleccionarVuelos();
            svFrame.runFrame();
            frmAmetsTravels.setVisible(false);
        });

        JButton btnNewButton_1 = new JButton("Men\u00FA");
        btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
        BottomSouth.add(btnNewButton_1);
        btnNewButton_1.addActionListener(e -> {
            Retorno rtn = new Retorno();
            frmAmetsTravels.setVisible(rtn.runReturn());
        });

        JButton btnNewButton_2 = new JButton("Siguiente");
        btnNewButton_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
        BottomSouth.add(btnNewButton_2);
        btnNewButton_2.addActionListener(e -> {
            VistaPP ppFrame = new VistaPP();
            ppFrame.runFrame();
            frmAmetsTravels.setVisible(false);
        });
    }
}