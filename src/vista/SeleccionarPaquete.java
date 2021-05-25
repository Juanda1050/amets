package vista;

import controlador.SelecPaqController;
import modelo.SelecPaqDAO;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class SeleccionarPaquete {

    public JFrame sPaqueteFrame;
    public JTextPane sPaquete_descTP, sPaquete_direccionTP;
    public JTextField sPaquete_origenTF, sPaquete_hotelTF, sPaquete_genteTF, sPaquete_aerolineaTF, sPaquete_precioTF, sPaquete_claseTF, sPaquete_regimenTF;
    public JComboBox<String> sPaquete_paqueteCB, sPaquete_destinoCB;
    public JTextField sPaquete_despegueTF, sPaquete_llegadaTF, sPaquete_aterrizajeTF, sPaquete_salidaTF;
    public JButton sPaquete_backB, sPaquete_menuB, sPaquete_nextB;

    public void runFrame(int agentID, int userID){
        EventQueue.invokeLater(() -> {
            try {
                SeleccionarPaquete window = new SeleccionarPaquete();
                SelecPaqDAO selecPaqDAO = new SelecPaqDAO();
                SelecPaqController spc = new SelecPaqController(window, selecPaqDAO, agentID, userID);
                window.sPaqueteFrame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public SeleccionarPaquete() {
        initialize();
    }

    public void initialize() {

        SelecPaqDAO spDAO = new SelecPaqDAO();
        sPaqueteFrame = new JFrame("Seleccionar Paquete");
        sPaqueteFrame.setBounds(100, 100, 1280, 720);
        sPaqueteFrame.setExtendedState(Frame.MAXIMIZED_BOTH);
        sPaqueteFrame.setIconImage(Toolkit.getDefaultToolkit().getImage("resources/amets.jpg"));
        sPaqueteFrame.getContentPane().setLayout(new BorderLayout(0, 0));

        JPanel sPaqueteTop = new JPanel();
        sPaqueteTop.setBorder(new EmptyBorder(20, 20, 20, 20));
        sPaqueteFrame.getContentPane().add(sPaqueteTop, BorderLayout.NORTH);
        sPaqueteTop.setLayout(new BorderLayout(0, 0));

        JLabel sPaqueteL = new JLabel("Seleccionar Paquete");
        sPaqueteL.setFont(new Font("Tahoma", Font.BOLD, 18));
        sPaqueteL.setHorizontalAlignment(SwingConstants.CENTER);
        sPaqueteTop.add(sPaqueteL);

        JPanel sPaqueteMid = new JPanel();
        sPaqueteMid.setBorder(new EmptyBorder(20, 20, 20, 20));
        sPaqueteFrame.getContentPane().add(sPaqueteMid, BorderLayout.CENTER);
        sPaqueteMid.setLayout(new GridLayout(0, 6, 0, 30));

        sPaquete_destinoCB = new JComboBox<>(spDAO.listarDestinos().toArray(new String[0]));
        sPaquete_destinoCB.setSelectedIndex(-1);
        sPaquete_destinoCB.setFont(new Font("Tahoma", Font.PLAIN, 16));
        sPaqueteMid.add(sPaquete_destinoCB);

        JSeparator ssPaquete_S1 = new JSeparator();
        ssPaquete_S1.setForeground(Color.WHITE);
        ssPaquete_S1.setVisible(false);
        sPaqueteMid.add(ssPaquete_S1);

        sPaquete_paqueteCB = new JComboBox<>();
        sPaquete_paqueteCB.setEnabled(false);
        sPaquete_paqueteCB.setFont(new Font("Tahoma", Font.PLAIN, 16));
        sPaqueteMid.add(sPaquete_paqueteCB);

        JSeparator sPaquete_S2 = new JSeparator();
        sPaquete_S2.setForeground(Color.WHITE);
        sPaquete_S2.setVisible(false);
        sPaqueteMid.add(sPaquete_S2);

        JSeparator sPaquete_S3 = new JSeparator();
        sPaquete_S3.setForeground(Color.WHITE);
        sPaquete_S3.setVisible(false);
        sPaqueteMid.add(sPaquete_S3);

        JSeparator sPaquete_S4 = new JSeparator();
        sPaquete_S4.setForeground(Color.WHITE);
        sPaquete_S4.setVisible(false);
        sPaqueteMid.add(sPaquete_S4);

        JLabel sPaquete_descL = new JLabel("Descripcion");
        sPaquete_descL.setHorizontalAlignment(SwingConstants.CENTER);
        sPaquete_descL.setFont(new Font("Tahoma", Font.PLAIN, 14));
        sPaqueteMid.add(sPaquete_descL);

        sPaquete_descTP = new JTextPane();
        sPaquete_descTP.setEditable(false);
        sPaquete_descTP.setFont(new Font("Tahoma", Font.PLAIN, 14));
        sPaqueteMid.add(sPaquete_descTP);

        JLabel sPaquete_origenL = new JLabel("Origen");
        sPaquete_origenL.setHorizontalAlignment(SwingConstants.CENTER);
        sPaquete_origenL.setFont(new Font("Tahoma", Font.PLAIN, 14));
        sPaqueteMid.add(sPaquete_origenL);

        sPaquete_origenTF = new JTextField();
        sPaquete_origenTF.setEditable(false);
        sPaquete_origenTF.setFont(new Font("Tahoma", Font.PLAIN, 14));
        sPaquete_origenTF.setColumns(10);
        sPaqueteMid.add(sPaquete_origenTF);

        JLabel sPaquete_hotelL = new JLabel("Hotel");
        sPaquete_hotelL.setHorizontalAlignment(SwingConstants.CENTER);
        sPaquete_hotelL.setFont(new Font("Tahoma", Font.PLAIN, 14));
        sPaqueteMid.add(sPaquete_hotelL);

        sPaquete_hotelTF = new JTextField();
        sPaquete_hotelTF.setEditable(false);
        sPaquete_hotelTF.setFont(new Font("Tahoma", Font.PLAIN, 14));
        sPaquete_hotelTF.setColumns(10);
        sPaqueteMid.add(sPaquete_hotelTF);

        JLabel sPaquete_genteL = new JLabel("Plazas");
        sPaquete_genteL.setHorizontalAlignment(SwingConstants.CENTER);
        sPaquete_genteL.setFont(new Font("Tahoma", Font.PLAIN, 14));
        sPaqueteMid.add(sPaquete_genteL);

        sPaquete_genteTF = new JTextField();
        sPaquete_genteTF.setEditable(false);
        sPaquete_genteTF.setFont(new Font("Tahoma", Font.PLAIN, 14));
        sPaquete_genteTF.setColumns(10);
        sPaqueteMid.add(sPaquete_genteTF);

        JLabel sPaquete_aerolineaL = new JLabel("Aerolinea");
        sPaquete_aerolineaL.setHorizontalAlignment(SwingConstants.CENTER);
        sPaquete_aerolineaL.setFont(new Font("Tahoma", Font.PLAIN, 14));
        sPaqueteMid.add(sPaquete_aerolineaL);

        sPaquete_aerolineaTF = new JTextField();
        sPaquete_aerolineaTF.setEditable(false);
        sPaquete_aerolineaTF.setFont(new Font("Tahoma", Font.PLAIN, 14));
        sPaquete_aerolineaTF.setColumns(10);
        sPaqueteMid.add(sPaquete_aerolineaTF);

        JLabel sPaquete_direccionL = new JLabel("Direccion");
        sPaquete_direccionL.setHorizontalAlignment(SwingConstants.CENTER);
        sPaquete_direccionL.setFont(new Font("Tahoma", Font.PLAIN, 14));
        sPaqueteMid.add(sPaquete_direccionL);

        sPaquete_direccionTP = new JTextPane();
        sPaquete_direccionTP.setEditable(false);
        sPaquete_direccionTP.setFont(new Font("Tahoma", Font.PLAIN, 14));
        sPaqueteMid.add(sPaquete_direccionTP);

        JLabel sPaquete_precioL = new JLabel("Precio");
        sPaquete_precioL.setHorizontalAlignment(SwingConstants.CENTER);
        sPaquete_precioL.setFont(new Font("Tahoma", Font.PLAIN, 14));
        sPaqueteMid.add(sPaquete_precioL);

        sPaquete_precioTF = new JTextField();
        sPaquete_precioTF.setEditable(false);
        sPaquete_precioTF.setFont(new Font("Tahoma", Font.PLAIN, 14));
        sPaquete_precioTF.setColumns(10);
        sPaqueteMid.add(sPaquete_precioTF);

        JLabel sPaquete_claseL = new JLabel("Tipo de Vuelo");
        sPaquete_claseL.setHorizontalAlignment(SwingConstants.CENTER);
        sPaquete_claseL.setFont(new Font("Tahoma", Font.PLAIN, 14));
        sPaqueteMid.add(sPaquete_claseL);

        sPaquete_claseTF = new JTextField();
        sPaquete_claseTF.setEditable(false);
        sPaquete_claseTF.setFont(new Font("Tahoma", Font.PLAIN, 14));
        sPaquete_claseTF.setColumns(10);
        sPaqueteMid.add(sPaquete_claseTF);

        JLabel sPaquete_regimenL = new JLabel("Regimen");
        sPaquete_regimenL.setHorizontalAlignment(SwingConstants.CENTER);
        sPaquete_regimenL.setFont(new Font("Tahoma", Font.PLAIN, 14));
        sPaqueteMid.add(sPaquete_regimenL);

        sPaquete_regimenTF = new JTextField();
        sPaquete_regimenTF.setEditable(false);
        sPaquete_regimenTF.setFont(new Font("Tahoma", Font.PLAIN, 14));
        sPaquete_regimenTF.setColumns(10);
        sPaqueteMid.add(sPaquete_regimenTF);

        JSeparator sPaquete_SP4 = new JSeparator();
        sPaquete_SP4.setForeground(Color.WHITE);
        sPaquete_SP4.setVisible(false);
        sPaqueteMid.add(sPaquete_SP4);

        JSeparator sPaquete_SP5 = new JSeparator();
        sPaquete_SP5.setForeground(Color.WHITE);
        sPaquete_SP5.setOrientation(SwingConstants.VERTICAL);
        sPaquete_SP5.setVisible(false);
        sPaqueteMid.add(sPaquete_SP5);

        JLabel sPaquete_despegueL = new JLabel("Hora de Despegue");
        sPaquete_despegueL.setHorizontalAlignment(SwingConstants.CENTER);
        sPaquete_despegueL.setFont(new Font("Tahoma", Font.PLAIN, 14));
        sPaqueteMid.add(sPaquete_despegueL);

        sPaquete_despegueTF = new JTextField();
        sPaquete_despegueTF.setEditable(false);
        sPaquete_despegueTF.setFont(new Font("Tahoma", Font.PLAIN, 14));
        sPaqueteMid.add(sPaquete_despegueTF);

        JLabel sPaquete_llegadaL = new JLabel("Hora de Llegada");
        sPaquete_llegadaL.setHorizontalAlignment(SwingConstants.CENTER);
        sPaquete_llegadaL.setFont(new Font("Tahoma", Font.PLAIN, 14));
        sPaqueteMid.add(sPaquete_llegadaL);

        sPaquete_llegadaTF = new JTextField();
        sPaquete_llegadaTF.setEditable(false);
        sPaquete_llegadaTF.setFont(new Font("Tahoma", Font.PLAIN, 14));
        sPaqueteMid.add(sPaquete_llegadaTF);

        JSeparator sPaquete_SP6 = new JSeparator();
        sPaquete_SP6.setForeground(Color.WHITE);
        sPaquete_SP6.setVisible(false);
        sPaqueteMid.add(sPaquete_SP6);

        JSeparator sPaquete_SP7 = new JSeparator();
        sPaquete_SP7.setForeground(Color.WHITE);
        sPaquete_SP7.setVisible(false);
        sPaqueteMid.add(sPaquete_SP7);

        JLabel sPaquete_aterrizajeL = new JLabel("Hora de Aterrizaje");
        sPaquete_aterrizajeL.setHorizontalAlignment(SwingConstants.CENTER);
        sPaquete_aterrizajeL.setFont(new Font("Tahoma", Font.PLAIN, 14));
        sPaqueteMid.add(sPaquete_aterrizajeL);

        sPaquete_aterrizajeTF = new JTextField();
        sPaquete_aterrizajeTF.setEditable(false);
        sPaquete_aterrizajeTF.setFont(new Font("Tahoma", Font.PLAIN, 14));
        sPaqueteMid.add(sPaquete_aterrizajeTF);

        JLabel sPaquete_salidaL = new JLabel("Hora de Salida");
        sPaquete_salidaL.setHorizontalAlignment(SwingConstants.CENTER);
        sPaquete_salidaL.setFont(new Font("Tahoma", Font.PLAIN, 14));
        sPaqueteMid.add(sPaquete_salidaL);

        sPaquete_salidaTF = new JTextField();
        sPaquete_salidaTF.setEditable(false);
        sPaquete_salidaTF.setFont(new Font("Tahoma", Font.PLAIN, 14));
        sPaqueteMid.add(sPaquete_salidaTF);

        JPanel sPaqueteBottom = new JPanel();
        sPaqueteBottom.setBorder(new EmptyBorder(20, 20, 20, 20));
        sPaqueteFrame.getContentPane().add(sPaqueteBottom, BorderLayout.SOUTH);
        sPaqueteBottom.setLayout(new GridLayout(0, 3, 20, 0));

        sPaquete_backB = new JButton("Volver");
        sPaquete_backB.setFont(new Font("Tahoma", Font.PLAIN, 16));
        sPaquete_backB.setIcon(new ImageIcon("resources/left.png"));
        sPaqueteBottom.add(sPaquete_backB);

        sPaquete_menuB = new JButton("Menu");
        sPaquete_menuB.setFont(new Font("Tahoma", Font.PLAIN, 16));
        sPaquete_menuB.setIcon(new ImageIcon("resources/home.png"));
        sPaqueteBottom.add(sPaquete_menuB);

        sPaquete_nextB = new JButton("Siguiente");
        sPaquete_nextB.setEnabled(false);
        sPaquete_nextB.setFont(new Font("Tahoma", Font.PLAIN, 16));
        sPaquete_nextB.setIcon(new ImageIcon("resources/siguiente.png"));
        sPaqueteBottom.add(sPaquete_nextB);
    }
}