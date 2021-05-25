package vista;

import controlador.MenuController;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class MenuPrincipal {

    public JFrame mPrincipalFrame;
    public JButton mPrincipal_corteB, mPrincipal_exitB, mPrincipal_ventaB;

    public void runFrame(int agentID){
        EventQueue.invokeLater(() -> {
            try {
                MenuPrincipal window = new MenuPrincipal();
                MenuController cm = new MenuController(window, agentID);
                window.mPrincipalFrame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public MenuPrincipal() {
        initialize();
    }

    public void initialize() {

        mPrincipalFrame = new JFrame("Menu Principal");
        mPrincipalFrame.setExtendedState(Frame.MAXIMIZED_BOTH);
        mPrincipalFrame.setBounds(100, 100, 1280, 720);
        mPrincipalFrame.setIconImage(Toolkit.getDefaultToolkit().getImage("resources/amets.jpg"));
        mPrincipalFrame.getContentPane().setLayout(new BorderLayout(0, 0));

        JPanel mPrincipalTop = new JPanel();
        mPrincipalTop.setBorder(new EmptyBorder(20, 20, 20, 20));
        mPrincipalFrame.getContentPane().add(mPrincipalTop, BorderLayout.NORTH);
        mPrincipalTop.setLayout(new BorderLayout(0, 0));

        JLabel mPrincipalL = new JLabel("MENU PRINCIPAL");
        mPrincipalL.setHorizontalAlignment(SwingConstants.CENTER);
        mPrincipalL.setFont(new Font("Tahoma", Font.BOLD, 18));
        mPrincipalTop.add(mPrincipalL, BorderLayout.CENTER);

        JPanel mPrincipalMid = new JPanel();
        mPrincipalMid.setBorder(new EmptyBorder(20, 20, 20, 20));
        mPrincipalFrame.getContentPane().add(mPrincipalMid, BorderLayout.CENTER);
        mPrincipalMid.setLayout(new BorderLayout(0, 0));

        JLabel mPrincipal_logoL = new JLabel("");
        mPrincipal_logoL.setIcon(new ImageIcon("resources/AMETS TRAVELS 300x300.png"));
        mPrincipal_logoL.setHorizontalAlignment(SwingConstants.RIGHT);
        mPrincipalMid.add(mPrincipal_logoL, BorderLayout.EAST);

        JPanel mPrincipalMid_L = new JPanel();
        mPrincipalMid.add(mPrincipalMid_L, BorderLayout.CENTER);
        mPrincipalMid_L.setLayout(new GridLayout(0, 1, 0, 0));

        mPrincipal_ventaB = new JButton("Nueva Venta");
        mPrincipal_ventaB.setFont(new Font("Tahoma", Font.PLAIN, 16));
        mPrincipal_ventaB.setIcon(new ImageIcon("resources/venta.png"));
        mPrincipalMid_L.add(mPrincipal_ventaB);

        mPrincipal_corteB = new JButton("Corte de Caja");
        mPrincipal_corteB.setFont(new Font("Tahoma", Font.PLAIN, 16));
        mPrincipal_corteB.setIcon(new ImageIcon("resources/caja.png"));
        mPrincipalMid_L.add(mPrincipal_corteB);

        JPanel mPrincipalBottom = new JPanel();
        mPrincipalBottom.setBorder(new EmptyBorder(20, 20, 20, 20));
        mPrincipalFrame.getContentPane().add(mPrincipalBottom, BorderLayout.SOUTH);
        mPrincipalBottom.setLayout(new BorderLayout(0, 0));

        mPrincipal_exitB = new JButton("Salir");
        mPrincipal_exitB.setFont(new Font("Tahoma", Font.PLAIN, 16));
        mPrincipal_exitB.setIcon(new ImageIcon("resources/salida.png"));
        mPrincipalBottom.add(mPrincipal_exitB, BorderLayout.EAST);
    }
}