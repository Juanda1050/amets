package vista;

import controlador.TicketController;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class Ticket {

    public JFrame frame;
    public JPanel panel;
    public JTextArea txtrCompraRealizada,txtrNumDeTarjeta;
    public JTextPane txtrAsfdas;
    public JButton btnNewButton_1, btnNewButton;
    /**
     * Launch the application.
     */

    public void runFrame(Ticket window, int agentID){
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    TicketController tc = new TicketController(window, agentID);
                    window.frame.setVisible(false);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public Ticket() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    public void initialize() {

        frame = new JFrame();
        frame.setResizable(false);
        frame.setBounds(100, 100, 400, 500);

        JPanel bottom = new JPanel();
        bottom.setBorder(new EmptyBorder(0, 20, 20, 20));
        frame.getContentPane().add(bottom, BorderLayout.SOUTH);
        bottom.setLayout(new BorderLayout(0, 0));

        panel = new JPanel();
        frame.getContentPane().add(panel, BorderLayout.CENTER);
        panel.setLayout(new BorderLayout(0, 0));

        JPanel top = new JPanel();
        panel.add(top, BorderLayout.NORTH);
        top.setLayout(new GridLayout(0, 1, 0, 0));

        JLabel lblNewLabel = new JLabel("Amets Travels");
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
        top.add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("Gracias por su compra!");
        lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 18));
        top.add(lblNewLabel_1);

        JPanel mid = new JPanel();
        mid.setBorder(new EmptyBorder(20, 20, 20, 20));
        panel.add(mid, BorderLayout.CENTER);
        mid.setLayout(new GridLayout(3, 1, 0, 0));

        txtrCompraRealizada = new JTextArea();
        txtrCompraRealizada.setEditable(false);
        mid.add(txtrCompraRealizada);

        txtrNumDeTarjeta = new JTextArea();
        txtrNumDeTarjeta.setEditable(false);
        mid.add(txtrNumDeTarjeta);

        txtrAsfdas = new JTextPane();
        txtrAsfdas.setEditable(false);
        mid.add(txtrAsfdas);

        JPanel bottompnt = new JPanel();
        panel.add(bottompnt, BorderLayout.SOUTH);

        JLabel lblNewLabel_2 = new JLabel("");
        lblNewLabel_2.setIcon(new ImageIcon("resources/AMETS TRAVELS 70x70.png"));
        lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
        bottompnt.add(lblNewLabel_2);

        btnNewButton_1 = new JButton("Regresar al Men\u00FA");
        btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
        bottom.add(btnNewButton_1);

        btnNewButton = new JButton("Imprimir Ticket");
        btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
        bottom.add(btnNewButton, BorderLayout.SOUTH);
    }

}