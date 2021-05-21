package vista;

import java.awt.*;
import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import javax.swing.border.EmptyBorder;

public class Ticket {

    protected JFrame frame;
    private JPanel panel;
    protected JTextArea txtrCompraRealizada,txtrNumDeTarjeta;
    protected JTextPane txtrAsfdas;
    /**
     * Launch the application.
     */

    public void runFrame(){
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Ticket window = new Ticket();
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
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                int result = JOptionPane.showConfirmDialog(frame, "¿Desea cerrar el programa?", "Salir del programa", JOptionPane.YES_NO_OPTION);
                if (result == JOptionPane.YES_OPTION)
                {
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                }
                else if (result == JOptionPane.NO_OPTION)
                {
                    frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                }
            }
        });

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

        JButton btnNewButton_1 = new JButton("Regresar al Men\u00FA");
        btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
        bottom.add(btnNewButton_1);
        btnNewButton_1.addActionListener(e -> {
            Retorno rtn = new Retorno();
            frame.setVisible(rtn.runReturn());
        });

        JButton btnNewButton = new JButton("Imprimir Ticket");
        btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
        bottom.add(btnNewButton, BorderLayout.SOUTH);
        btnNewButton.addActionListener(e -> {
            Print();
        });
    }

    public void Print(){

        PrinterJob job = PrinterJob.getPrinterJob();
        job.setJobName("Print Data");

        job.setPrintable(new Printable() {

            public int print(Graphics pg, PageFormat pf, int pageNum){
                pf.setOrientation(PageFormat.LANDSCAPE);
                if(pageNum>0){
                    return Printable.NO_SUCH_PAGE;
                }

                Graphics2D g2 = (Graphics2D)pg;
                g2.translate(pf.getImageableX(), pf.getImageableY());
                g2.scale(1,1);

                panel.paint(g2);
                return Printable.PAGE_EXISTS;


            }
        });

        boolean ok = job.printDialog();
        if(ok){
            try{
                job.print();
            }
            catch (PrinterException ex){}
        }
    }

}