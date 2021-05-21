package segunda;

import modelo.PagoDAO;
import modelo.SelecPaqDAO;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class SeleccionarPaquete extends RegistroUsuarios{

    private JFrame spFrame;
    public JTextPane spDescripcionTF;
    private JTextField spOrigenFT;
    private JTextField spHotelTF;
    private JTextField spPlazasTF;
    private JTextField spAerolineaTF;
    protected JTextPane spDireccionTF;
    private JTextField spPrecioTF;
    private JTextField spTipodeVueloTF;
    private JTextField spRegimenTF;
    private JComboBox spPaqueteCB;
    private JComboBox spDestinoCB;
    private JTextField spHoraDespegueSpn;
    private JTextField spHoraLlegadaSpn;
    private JTextField spHoradeAterrizajeSpn;
    private JTextField spHoraSalidaSpn;
    Ticket ticket = new Ticket();

    public void initialize(int agentID) {

        SelecPaqDAO spDAO = new SelecPaqDAO();

        spFrame = new JFrame();
        spFrame.setVisible(true);
        spFrame.setBounds(100, 100, 1280, 720);
        spFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        spFrame.getContentPane().setLayout(new BorderLayout(0, 0));

        JPanel spTop = new JPanel();
        spTop.setBorder(new EmptyBorder(20, 20, 20, 20));
        spFrame.getContentPane().add(spTop, BorderLayout.NORTH);
        spTop.setLayout(new BorderLayout(0, 0));

        JLabel spTitulo = new JLabel("Seleccionar Paquete");
        spTitulo.setFont(new Font("Tahoma", Font.BOLD, 18));
        spTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        spTop.add(spTitulo);

        JPanel spMid = new JPanel();
        spMid.setBorder(new EmptyBorder(20, 20, 20, 20));
        spFrame.getContentPane().add(spMid, BorderLayout.CENTER);
        spMid.setLayout(new GridLayout(0, 6, 0, 30));

        spPaqueteCB = new JComboBox();
        spPaqueteCB.setEnabled(false);

        JButton btnSiguiente = new JButton("Siguiente");
        btnSiguiente.setEnabled(false);

        spDestinoCB = new JComboBox<String>(spDAO.listarDestinos().toArray(new String[0]));
        spDestinoCB.setSelectedIndex(-1);
        spDestinoCB.setFont(new Font("Tahoma", Font.PLAIN, 16));
        spMid.add(spDestinoCB);

        JSeparator separator_1 = new JSeparator();
        separator_1.setForeground(Color.WHITE);
        separator_1.setVisible(false);
        spMid.add(separator_1);

        spDestinoCB.addActionListener (e -> {
            if(spDestinoCB.getSelectedIndex()!=-1){
                spPaqueteCB.setEnabled(true);
                btnSiguiente.setEnabled(true);
            }
            spPaqueteCB.removeAllItems();
            spDAO.listarPaquetes(spDestinoCB.getSelectedIndex()+1);
            for(int i=0;i<spDAO.getListaPaquete().size();i++){
                spPaqueteCB.addItem(spDAO.getListaPaquete().get(i));
            }
        });

        spPaqueteCB.setFont(new Font("Tahoma", Font.PLAIN, 16));
        spMid.add(spPaqueteCB);
        spPaqueteCB.addActionListener (e -> {
            String paqueteCBindex = spPaqueteCB.getSelectedItem().toString();
            spDescripcionTF.setText(spDAO.getData(paqueteCBindex).get(0));
            spPrecioTF.setText(spDAO.getData(paqueteCBindex).get(1));
            spPlazasTF.setText(spDAO.getData(paqueteCBindex).get(2));
            spHotelTF.setText(spDAO.getData(paqueteCBindex).get(3));
            spDireccionTF.setText(spDAO.getData(paqueteCBindex).get(4));
            spRegimenTF.setText(spDAO.getData(paqueteCBindex).get(5));
            spHoraLlegadaSpn.setText(spDAO.getData(paqueteCBindex).get(6));
            spHoraSalidaSpn.setText(spDAO.getData(paqueteCBindex).get(7));
            spOrigenFT.setText(spDAO.getData(paqueteCBindex).get(8));
            spHoraDespegueSpn.setText(spDAO.getData(paqueteCBindex).get(9));
            spHoradeAterrizajeSpn.setText(spDAO.getData(paqueteCBindex).get(10));
            spAerolineaTF.setText(spDAO.getData(paqueteCBindex).get(11));
            spTipodeVueloTF.setText(spDAO.getData(paqueteCBindex).get(12));
        });

        JSeparator separator_2 = new JSeparator();
        separator_2.setForeground(Color.WHITE);
        separator_2.setVisible(false);
        spMid.add(separator_2);

        JSeparator separator_3 = new JSeparator();
        separator_3.setForeground(Color.WHITE);
        separator_3.setVisible(false);
        spMid.add(separator_3);

        JSeparator separator_4 = new JSeparator();
        separator_4.setForeground(Color.WHITE);
        separator_4.setVisible(false);
        spMid.add(separator_4);

        JLabel spDescripcionLbl = new JLabel("Descripci\u00F3n");
        spDescripcionLbl.setHorizontalAlignment(SwingConstants.CENTER);
        spDescripcionLbl.setFont(new Font("Tahoma", Font.PLAIN, 14));
        spMid.add(spDescripcionLbl);

        spDescripcionTF = new JTextPane();
        spDescripcionTF.setEditable(false);
        spDescripcionTF.setFont(new Font("Tahoma", Font.PLAIN, 14));
        spMid.add(spDescripcionTF);

        JLabel spOrigenLbl = new JLabel("Origen");
        spOrigenLbl.setHorizontalAlignment(SwingConstants.CENTER);
        spOrigenLbl.setFont(new Font("Tahoma", Font.PLAIN, 14));
        spMid.add(spOrigenLbl);

        spOrigenFT = new JTextField();
        spOrigenFT.setEditable(false);
        spOrigenFT.setFont(new Font("Tahoma", Font.PLAIN, 14));
        spOrigenFT.setColumns(10);
        spMid.add(spOrigenFT);

        JLabel spHotelLbl = new JLabel("Hotel");
        spHotelLbl.setHorizontalAlignment(SwingConstants.CENTER);
        spHotelLbl.setFont(new Font("Tahoma", Font.PLAIN, 14));
        spMid.add(spHotelLbl);

        spHotelTF = new JTextField();
        spHotelTF.setEditable(false);
        spHotelTF.setFont(new Font("Tahoma", Font.PLAIN, 14));
        spHotelTF.setColumns(10);
        spMid.add(spHotelTF);

        JLabel spPlazasLbl = new JLabel("Plazas");
        spPlazasLbl.setHorizontalAlignment(SwingConstants.CENTER);
        spPlazasLbl.setFont(new Font("Tahoma", Font.PLAIN, 14));
        spMid.add(spPlazasLbl);

        spPlazasTF = new JTextField();
        spPlazasTF.setEditable(false);
        spPlazasTF.setFont(new Font("Tahoma", Font.PLAIN, 14));
        spPlazasTF.setColumns(10);
        spMid.add(spPlazasTF);

        JLabel spAerolineaLbl = new JLabel("Aerol\u00EDnea");
        spAerolineaLbl.setHorizontalAlignment(SwingConstants.CENTER);
        spAerolineaLbl.setFont(new Font("Tahoma", Font.PLAIN, 14));
        spMid.add(spAerolineaLbl);

        spAerolineaTF = new JTextField();
        spAerolineaTF.setEditable(false);
        spAerolineaTF.setFont(new Font("Tahoma", Font.PLAIN, 14));
        spAerolineaTF.setColumns(10);
        spMid.add(spAerolineaTF);

        JLabel spDireccionLbl = new JLabel("Direcci\u00F3n");
        spDireccionLbl.setHorizontalAlignment(SwingConstants.CENTER);
        spDireccionLbl.setFont(new Font("Tahoma", Font.PLAIN, 14));
        spMid.add(spDireccionLbl);

        spDireccionTF = new JTextPane();
        spDireccionTF.setEditable(false);
        spDireccionTF.setFont(new Font("Tahoma", Font.PLAIN, 14));
        spMid.add(spDireccionTF);

        JLabel spPrecioLbl = new JLabel("Precio");
        spPrecioLbl.setHorizontalAlignment(SwingConstants.CENTER);
        spPrecioLbl.setFont(new Font("Tahoma", Font.PLAIN, 14));
        spMid.add(spPrecioLbl);

        spPrecioTF = new JTextField();
        spPrecioTF.setEditable(false);
        spPrecioTF.setFont(new Font("Tahoma", Font.PLAIN, 14));
        spPrecioTF.setColumns(10);
        spMid.add(spPrecioTF);

        JLabel spTipodeVueloLbl = new JLabel("Tipo de Vuelo");
        spTipodeVueloLbl.setHorizontalAlignment(SwingConstants.CENTER);
        spTipodeVueloLbl.setFont(new Font("Tahoma", Font.PLAIN, 14));
        spMid.add(spTipodeVueloLbl);

        spTipodeVueloTF = new JTextField();
        spTipodeVueloTF.setEditable(false);
        spTipodeVueloTF.setFont(new Font("Tahoma", Font.PLAIN, 14));
        spTipodeVueloTF.setColumns(10);
        spMid.add(spTipodeVueloTF);

        JLabel spRegimenLbl = new JLabel("Regimen");
        spRegimenLbl.setHorizontalAlignment(SwingConstants.CENTER);
        spRegimenLbl.setFont(new Font("Tahoma", Font.PLAIN, 14));
        spMid.add(spRegimenLbl);

        spRegimenTF = new JTextField();
        spRegimenTF.setEditable(false);
        spRegimenTF.setFont(new Font("Tahoma", Font.PLAIN, 14));
        spRegimenTF.setColumns(10);
        spMid.add(spRegimenTF);

        JSeparator separator = new JSeparator();
        separator.setForeground(Color.WHITE);
        separator.setVisible(false);
        spMid.add(separator);

        JSeparator separator2 = new JSeparator();
        separator2.setForeground(Color.WHITE);
        separator2.setOrientation(SwingConstants.VERTICAL);
        separator2.setVisible(false);
        spMid.add(separator2);

        JLabel spHoraDespegueLbl = new JLabel("Hora de Despegue");
        spHoraDespegueLbl.setHorizontalAlignment(SwingConstants.CENTER);
        spHoraDespegueLbl.setFont(new Font("Tahoma", Font.PLAIN, 14));
        spMid.add(spHoraDespegueLbl);

        spHoraDespegueSpn = new JTextField();
        spHoraDespegueSpn.setEditable(false);
        spHoraDespegueSpn.setFont(new Font("Tahoma", Font.PLAIN, 14));
        spMid.add(spHoraDespegueSpn);

        JLabel spHoraLlegadaLbl = new JLabel("Hora de Llegada");
        spHoraLlegadaLbl.setHorizontalAlignment(SwingConstants.CENTER);
        spHoraLlegadaLbl.setFont(new Font("Tahoma", Font.PLAIN, 14));
        spMid.add(spHoraLlegadaLbl);

        spHoraLlegadaSpn = new JTextField();
        spHoraLlegadaSpn.setEditable(false);
        spHoraLlegadaSpn.setFont(new Font("Tahoma", Font.PLAIN, 14));
        spMid.add(spHoraLlegadaSpn);

        JSeparator separator3 = new JSeparator();
        separator3.setForeground(Color.WHITE);
        separator3.setVisible(false);
        spMid.add(separator3);

        JSeparator separator4 = new JSeparator();
        separator4.setForeground(Color.WHITE);
        separator4.setVisible(false);
        spMid.add(separator4);

        JLabel spHoradeAterrizajeLbl = new JLabel("Hora de Aterrizaje");
        spHoradeAterrizajeLbl.setHorizontalAlignment(SwingConstants.CENTER);
        spHoradeAterrizajeLbl.setFont(new Font("Tahoma", Font.PLAIN, 14));
        spMid.add(spHoradeAterrizajeLbl);

        spHoradeAterrizajeSpn = new JTextField();
        spHoradeAterrizajeSpn.setEditable(false);
        spHoradeAterrizajeSpn.setFont(new Font("Tahoma", Font.PLAIN, 14));
        spMid.add(spHoradeAterrizajeSpn);

        JLabel spHoraSalidaLbl = new JLabel("Hora de Salida");
        spHoraSalidaLbl.setHorizontalAlignment(SwingConstants.CENTER);
        spHoraSalidaLbl.setFont(new Font("Tahoma", Font.PLAIN, 14));
        spMid.add(spHoraSalidaLbl);

        spHoraSalidaSpn = new JTextField();
        spHoraSalidaSpn.setEditable(false);
        spHoraSalidaSpn.setFont(new Font("Tahoma", Font.PLAIN, 14));
        spMid.add(spHoraSalidaSpn);

        JPanel spBottom = new JPanel();
        spBottom.setBorder(new EmptyBorder(20, 20, 20, 20));
        spFrame.getContentPane().add(spBottom, BorderLayout.SOUTH);
        spBottom.setLayout(new GridLayout(0, 3, 20, 0));

        JButton spVolverBtn = new JButton("Volver");
        spVolverBtn.setFont(new Font("Tahoma", Font.PLAIN, 14));
        spBottom.add(spVolverBtn);

        JButton spMenuBtn = new JButton("Men\u00FA");
        spMenuBtn.setFont(new Font("Tahoma", Font.PLAIN, 14));
        spBottom.add(spMenuBtn);

        btnSiguiente.setFont(new Font("Tahoma", Font.PLAIN, 14));
        spBottom.add(btnSiguiente);
        btnSiguiente.addActionListener(e -> {
            /* Se genera el ticket */
            ticket.runFrame();
            VistaPP pp = new VistaPP();
            pp.initialize(spDescripcionTF.getText(),Double.parseDouble(spPrecioTF.getText()),agentID);
            spFrame.setVisible(false);
        });
    }
}
