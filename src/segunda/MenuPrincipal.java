package segunda;

import tercera.VistaCC;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MenuPrincipal extends Credenciales{

    private JFrame mpFrame;

    public void initializeMenu(int agentID) {

        mpFrame = new JFrame();
        mpFrame.setVisible(true);
        mpFrame.setTitle("Amets Travels");
        mpFrame.setExtendedState(Frame.MAXIMIZED_BOTH);
        mpFrame.setBounds(100, 100, 1280, 720);
        mpFrame.getContentPane().setLayout(new BorderLayout(0, 0));
        mpFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                int result = JOptionPane.showConfirmDialog(mpFrame, "¿Desea cerrar el programa?", "Salir del programa", JOptionPane.YES_NO_OPTION);
                if (result == JOptionPane.YES_OPTION)
                {
                    mpFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                }
                else if (result == JOptionPane.NO_OPTION)
                {
                    mpFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                }
            }
        });

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

        JButton mpVentaBtn = new JButton("Nueva Venta");
        mpMidWest.setLayout(new GridLayout(0, 1, 0, 0));
        mpVentaBtn.setFont(new Font("Tahoma", Font.PLAIN, 16));
        mpMidWest.add(mpVentaBtn);
        mpVentaBtn.addActionListener(e -> {
            RegistroUsuarios ru = new RegistroUsuarios();
            ru.initializeRU(agentID);
            mpFrame.setVisible(false);
        });

        JButton mpCajaBtn = new JButton("Corte de Caja");
        mpCajaBtn.setFont(new Font("Tahoma", Font.PLAIN, 16));
        mpMidWest.add(mpCajaBtn);
        mpCajaBtn.addActionListener(e -> {
            VistaCC ccFrame = new VistaCC();
            ccFrame.runFrame(agentID);
            mpFrame.setVisible(false);
        });

        JPanel mpBottom = new JPanel();
        mpBottom.setBorder(new EmptyBorder(20, 20, 20, 20));
        mpFrame.getContentPane().add(mpBottom, BorderLayout.SOUTH);
        mpBottom.setLayout(new BorderLayout(0, 0));

        JButton mpSalirBtn = new JButton("Salir");
        mpSalirBtn.setFont(new Font("Tahoma", Font.PLAIN, 16));
        mpBottom.add(mpSalirBtn, BorderLayout.EAST);
        mpSalirBtn.addActionListener(e -> {
            Credenciales credentials = new Credenciales();
            credentials.initializeCred();
            mpFrame.setVisible(false);
        });
    }
}