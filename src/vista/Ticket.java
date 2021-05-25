package vista;

import controlador.TicketController;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class Ticket {

    public JFrame ticketFrame;
    public JPanel ticketPanel;
    public JTextArea ticket_compraTA, ticket_pagoTA;
    public JTextPane ticket_descTP;
    public JButton ticket_menuB, ticket_printB;

    public void runFrame(Ticket window, int agentID){
        EventQueue.invokeLater(() -> {
            try {
                TicketController tc = new TicketController(window, agentID);
                window.ticketFrame.setVisible(false);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public Ticket() {
        initialize();
    }

    public void initialize() {

        ticketFrame = new JFrame("Ticket de Venta");
        ticketFrame.setResizable(false);
        ticketFrame.setIconImage(Toolkit.getDefaultToolkit().getImage("resources/amets.jpg"));
        ticketFrame.setBounds(100, 100, 400, 500);

        JPanel ticketBottom = new JPanel();
        ticketBottom.setBorder(new EmptyBorder(0, 20, 20, 20));
        ticketFrame.getContentPane().add(ticketBottom, BorderLayout.SOUTH);
        ticketBottom.setLayout(new BorderLayout(0, 0));

        ticketPanel = new JPanel();
        ticketFrame.getContentPane().add(ticketPanel, BorderLayout.CENTER);
        ticketPanel.setLayout(new BorderLayout(0, 0));

        JPanel ticketTop = new JPanel();
        ticketPanel.add(ticketTop, BorderLayout.NORTH);
        ticketTop.setLayout(new GridLayout(0, 1, 0, 0));

        JLabel ticketL = new JLabel("Amets Travels");
        ticketL.setHorizontalAlignment(SwingConstants.CENTER);
        ticketL.setFont(new Font("Tahoma", Font.PLAIN, 16));
        ticketTop.add(ticketL);

        JLabel ticket_compraL = new JLabel("Gracias por su compra!");
        ticket_compraL.setHorizontalAlignment(SwingConstants.CENTER);
        ticket_compraL.setFont(new Font("Tahoma", Font.BOLD, 18));
        ticketTop.add(ticket_compraL);

        JPanel ticketMid = new JPanel();
        ticketMid.setBorder(new EmptyBorder(20, 20, 20, 20));
        ticketPanel.add(ticketMid, BorderLayout.CENTER);
        ticketMid.setLayout(new GridLayout(3, 1, 0, 0));

        ticket_compraTA = new JTextArea();
        ticket_compraTA.setEditable(false);
        ticketMid.add(ticket_compraTA);

        ticket_pagoTA = new JTextArea();
        ticket_pagoTA.setEditable(false);
        ticketMid.add(ticket_pagoTA);

        ticket_descTP = new JTextPane();
        ticket_descTP.setEditable(false);
        ticketMid.add(ticket_descTP);

        JPanel ticketPanel_B = new JPanel();
        ticketPanel.add(ticketPanel_B, BorderLayout.SOUTH);

        JLabel ticket_logoL = new JLabel("");
        ticket_logoL.setIcon(new ImageIcon("resources/AMETS TRAVELS 70x70.png"));
        ticket_logoL.setHorizontalAlignment(SwingConstants.CENTER);
        ticketPanel_B.add(ticket_logoL);

        ticket_menuB = new JButton("Regresar al Men\u00FA");
        ticket_menuB.setFont(new Font("Tahoma", Font.PLAIN, 14));
        ticketBottom.add(ticket_menuB);

        ticket_printB = new JButton("Imprimir Ticket");
        ticket_printB.setFont(new Font("Tahoma", Font.PLAIN, 14));
        ticketBottom.add(ticket_printB, BorderLayout.SOUTH);
    }

}