package vista;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import java.awt.BorderLayout;

public class Control {

    private JFrame CaFrame;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Control window = new Control();
                    window.CaFrame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public Control() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        CaFrame= new JFrame();
        CaFrame.setTitle("Amets Travels");
        CaFrame.setBounds(100, 100, 515, 308);
        CaFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        CaFrame.getContentPane().add(panel, BorderLayout.SOUTH);
        panel.setLayout(new BorderLayout(20, 20));

        JLabel CaCargandoL = new JLabel("Cargando... Porfavor espere", SwingConstants.CENTER);
        CaCargandoL.setFont(new Font("Tahoma", Font.PLAIN, 18));
        panel.add(CaCargandoL, BorderLayout.NORTH);

        JLabel CaLogoL = new JLabel(new ImageIcon("resources/logo.png"));
        CaFrame.add(CaLogoL);
    }

}