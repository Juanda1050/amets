package segunda;

import Controlador.ControladorMenu;
import tercera.VistaCC;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MenuPrincipal {

    public JFrame mpFrame;
    public JButton mpCajaBtn, mpSalirBtn, mpVentaBtn;

    public void runFrame(int agentID){
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    MenuPrincipal window = new MenuPrincipal();
                    ControladorMenu cm = new ControladorMenu(window, agentID);
                    window.mpFrame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public MenuPrincipal() {
        initialize();
    }

    public void initialize() {

        mpFrame = new JFrame();
        mpFrame.setTitle("Amets Travels");
        mpFrame.setExtendedState(Frame.MAXIMIZED_BOTH);
        mpFrame.setBounds(100, 100, 1280, 720);
        mpFrame.getContentPane().setLayout(new BorderLayout(0, 0));

        JPanel mpTop = new JPanel();
        mpTop.setBorder(new EmptyBorder(20, 20, 20, 20));
        mpFrame.getContentPane().add(mpTop, BorderLayout.NORTH);
        mpTop.setLayout(new BorderLayout(0, 0));

        JLabel mpLabel = new JLabel("Men\u00FA Principal");
        mpLabel.setHorizontalAlignment(SwingConstants.CENTER);
        mpLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
        mpTop.add(mpLabel, BorderLayout.CENTER);

        JPanel mpMid = new JPanel();
        mpMid.setBorder(new EmptyBorder(20, 20, 20, 20));
        mpFrame.getContentPane().add(mpMid, BorderLayout.CENTER);
        mpMid.setLayout(new BorderLayout(0, 0));

        JLabel mpLogoLbl = new JLabel("");
        mpLogoLbl.setIcon(new ImageIcon("resources/AMETS TRAVELS 300x300.png"));
        mpLogoLbl.setHorizontalAlignment(SwingConstants.RIGHT);
        mpMid.add(mpLogoLbl, BorderLayout.EAST);

        JPanel mpMidWest = new JPanel();
        mpMid.add(mpMidWest, BorderLayout.CENTER);

        mpVentaBtn = new JButton("Nueva Venta");
        mpMidWest.setLayout(new GridLayout(0, 1, 0, 0));
        mpVentaBtn.setFont(new Font("Tahoma", Font.PLAIN, 16));
        mpMidWest.add(mpVentaBtn);

        mpCajaBtn = new JButton("Corte de Caja");
        mpCajaBtn.setFont(new Font("Tahoma", Font.PLAIN, 16));
        mpMidWest.add(mpCajaBtn);

        JPanel mpBottom = new JPanel();
        mpBottom.setBorder(new EmptyBorder(20, 20, 20, 20));
        mpFrame.getContentPane().add(mpBottom, BorderLayout.SOUTH);
        mpBottom.setLayout(new BorderLayout(0, 0));

        mpSalirBtn = new JButton("Salir");
        mpSalirBtn.setFont(new Font("Tahoma", Font.PLAIN, 16));
        mpBottom.add(mpSalirBtn, BorderLayout.EAST);
    }
}