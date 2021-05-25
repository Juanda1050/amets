package vista;

import com.toedter.calendar.JTextFieldDateEditor;
import controlador.HotelesController;
import modelo.HotelesDAO;

import java.awt.*;

import javax.swing.*;

import javax.swing.border.EmptyBorder;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class GestionarHoteles {
        private JFrame gHotelFrame;
        public JTextField gHotel_idTF, gHotel_nombreTF, gHotel_ubicacionTF, gHotel_clasifTF, gHotel_genteTF;
        public JButton gHotel_addB, gHotel_saveB, gHotel_editB, gHotel_deleteB, gHotel_hotelB;
        public JTable gHotelTable;
        public JComboBox gHotel_destinoCB, gHotel_dispoCB, gHotel_regimenCB;
        public JTextFieldDateEditor  gHotel_entradaDC, gHotel_salidaDC;
        private int limiteNombre = 50, limiteUbi = 100, limiteClasif = 11, limiteGente = 1;

        public void runFrame(){
                EventQueue.invokeLater(() -> {
                        try {
                                GestionarHoteles window = new GestionarHoteles();
                                HotelesDAO hotelesDAO = new HotelesDAO();
                                HotelesController controller = new HotelesController(window, hotelesDAO);
                                window.gHotelFrame.setVisible(true);
                        } catch (Exception e) {
                                e.printStackTrace();
                        }
                });
        }

        public GestionarHoteles() {
                initialize();
        }

        private void initialize() {
                gHotelFrame = new JFrame("Gestionar Hoteles");
                gHotelFrame.setExtendedState(Frame.MAXIMIZED_BOTH);
                gHotelFrame.setIconImage(Toolkit.getDefaultToolkit().getImage("resources/amets.jpg"));
                gHotelFrame.setBounds(100, 100, 1280, 720);
                gHotelFrame.addWindowListener(new WindowAdapter() {
                        public void windowClosing(WindowEvent we) {
                                int result = JOptionPane.showConfirmDialog(gHotelFrame, "¿Desea cerrar el programa?", "Salir del programa", JOptionPane.YES_NO_OPTION);
                                if (result == JOptionPane.YES_OPTION)
                                {
                                        gHotelFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                                }
                                else if (result == JOptionPane.NO_OPTION)
                                {
                                        gHotelFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                                }
                        }
                });

                JPanel gHotelTop = new JPanel();
                gHotelTop.setBorder(new EmptyBorder(20, 20, 20, 20));
                gHotelFrame.getContentPane().add(gHotelTop, BorderLayout.NORTH);
                gHotelTop.setLayout(new BorderLayout(0, 0));

                JLabel gHotel_addL = new JLabel("Nuevo Hotel");
                gHotel_addL.setHorizontalAlignment(SwingConstants.CENTER);
                gHotel_addL.setFont(new Font("Tahoma", Font.BOLD, 18));
                gHotelTop.add(gHotel_addL, BorderLayout.WEST);

                JLabel gHotelL = new JLabel("Hoteles");
                gHotelL.setHorizontalAlignment(SwingConstants.CENTER);
                gHotelL.setFont(new Font("Tahoma", Font.BOLD, 18));
                gHotelTop.add(gHotelL, BorderLayout.CENTER);

                JPanel gHotelLeft = new JPanel();
                gHotelLeft.setBorder(new EmptyBorder(20, 20, 20, 20));
                gHotelFrame.getContentPane().add(gHotelLeft, BorderLayout.WEST);
                gHotelLeft.setLayout(new GridLayout(0, 2, 15, 10));

                JLabel gHotel_idL = new JLabel("ID Hotel");
                gHotel_idL.setFont(new Font("Tahoma", Font.PLAIN, 16));
                gHotelLeft.add(gHotel_idL);

                gHotel_idTF = new JTextField();
                gHotel_idTF.setFont(new Font("Tahoma", Font.PLAIN, 16));
                gHotel_idTF.setColumns(10);
                gHotel_idTF.setEditable(false);
                gHotelLeft.add(gHotel_idTF);

                JLabel gHotel_destinoL = new JLabel("Destino");
                gHotel_destinoL.setFont(new Font("Tahoma", Font.PLAIN, 16));
                gHotelLeft.add(gHotel_destinoL);
                gHotelLeft.add(gHotel_destinoL);

                gHotel_destinoCB = new JComboBox();
                gHotel_destinoCB.setFont(new Font("Tahoma", Font.PLAIN, 16));
                gHotel_destinoCB.setModel(new DefaultComboBoxModel(new String[] {"Seleccione destino"}));
                gHotel_destinoCB.setToolTipText("");
                gHotelLeft.add(gHotel_destinoCB);

                JLabel gHotel_nombreL = new JLabel("Nombre");
                gHotel_nombreL.setFont(new Font("Tahoma", Font.PLAIN, 16));
                gHotelLeft.add(gHotel_nombreL);

                gHotel_nombreTF = new JTextField();
                gHotel_nombreTF.setFont(new Font("Tahoma", Font.PLAIN, 16));
                gHotel_nombreTF.setColumns(10);
                gHotelLeft.add(gHotel_nombreTF);
                gHotel_nombreTF.addKeyListener(new KeyAdapter() {
                        @Override
                        public void keyTyped(KeyEvent e) {
                                char ch = e.getKeyChar();
                                if(Character.isLetter(ch) || Character.isISOControl(ch) ||  Character.isSpaceChar(ch)){
                                }else{
                                        e.consume();
                                        JOptionPane.showMessageDialog(null, "Solo admite letras");
                                }
                                if (gHotel_nombreTF.getText().length() >= limiteNombre){
                                        e.consume();
                                        Toolkit.getDefaultToolkit().beep();
                                }
                        }
                });

                JLabel gHotel_ubicacionL = new JLabel("Ubicacion");
                gHotel_ubicacionL.setFont(new Font("Tahoma", Font.PLAIN, 16));
                gHotelLeft.add(gHotel_ubicacionL);

                gHotel_ubicacionTF = new JTextField();
                gHotel_ubicacionTF.setFont(new Font("Tahoma", Font.PLAIN, 16));
                gHotel_ubicacionTF.setColumns(10);
                gHotelLeft.add(gHotel_ubicacionTF);
                gHotel_ubicacionTF.addKeyListener(new KeyAdapter() {
                        @Override
                        public void keyTyped(KeyEvent e) {
                                if (gHotel_ubicacionTF.getText().length() >= limiteUbi){
                                        e.consume();
                                        Toolkit.getDefaultToolkit().beep();
                                }
                        }
                });

                JLabel gHotel_clasifL = new JLabel("Clasificacion");
                gHotel_clasifL.setFont(new Font("Tahoma", Font.PLAIN, 16));
                gHotelLeft.add(gHotel_clasifL);

                gHotel_clasifTF = new JTextField();
                gHotel_clasifTF.setFont(new Font("Tahoma", Font.PLAIN, 16));
                gHotel_clasifTF.setColumns(10);
                gHotel_clasifTF.addKeyListener(new KeyAdapter() {
                        @Override
                        public void keyTyped(KeyEvent e) {
                                char ch = e.getKeyChar();
                                if(Character.isLetter(ch) || Character.isDigit(ch) || Character.isISOControl(ch) || Character.isSpaceChar(ch)){
                                }
                                else{
                                        e.consume();
                                        JOptionPane.showMessageDialog(null, "Solo admite numeros y letras");
                                }
                                if(gHotel_clasifTF.getText().length() >= limiteClasif){
                                        e.consume();
                                        Toolkit.getDefaultToolkit().beep();
                                }
                        }
                });
                gHotelLeft.add(gHotel_clasifTF);

                JLabel gHotel_genteL = new JLabel("Huespedes");
                gHotel_genteL.setFont(new Font("Tahoma", Font.PLAIN, 16));
                gHotelLeft.add(gHotel_genteL);

                gHotel_genteTF = new JTextField();
                gHotel_genteTF.setFont(new Font("Tahoma", Font.PLAIN, 16));
                gHotel_genteTF.setColumns(10);
                gHotel_genteTF.addKeyListener(new KeyAdapter() {
                        @Override
                        public void keyTyped(KeyEvent e) {
                                char ch = e.getKeyChar();
                                if(Character.isDigit(ch) || Character.isISOControl(ch)){
                                }
                                else {
                                        e.consume();
                                        JOptionPane.showMessageDialog(null, "Solo admite números");
                                }
                                if (gHotel_genteTF.getText().length() >= limiteGente){
                                        e.consume();
                                        Toolkit.getDefaultToolkit().beep();
                                }
                        }
                });
                gHotelLeft.add(gHotel_genteTF);

                JLabel gHotel_regimenL = new JLabel("Regimen");
                gHotel_regimenL.setFont(new Font("Tahoma", Font.PLAIN, 16));
                gHotelLeft.add(gHotel_regimenL);

                gHotel_regimenCB = new JComboBox();
                gHotel_regimenCB.setFont(new Font("Tahoma", Font.PLAIN, 16));
                gHotel_regimenCB.setModel(new DefaultComboBoxModel(new String []{"Seleccione un régimen", "1", "2"}));
                gHotelLeft.add(gHotel_regimenCB);
                gHotelLeft.add(gHotel_regimenCB);

                JLabel gHotel_dispoL = new JLabel("Disponibilidad");
                gHotel_dispoL.setFont(new Font("Tahoma", Font.PLAIN, 16));
                gHotelLeft.add(gHotel_dispoL);

                gHotel_dispoCB = new JComboBox();
                gHotel_dispoCB.setFont(new Font("Tahoma", Font.PLAIN, 16));
                gHotel_dispoCB.setModel(new DefaultComboBoxModel(new String []{"Seleccione la disponibilidad", "0", "1"}));
                gHotelLeft.add(gHotel_dispoCB);

                JLabel gHotel_llegadaL = new JLabel("Fecha de llegada");
                gHotel_llegadaL.setFont(new Font("Tahoma", Font.PLAIN, 16));
                gHotelLeft.add(gHotel_llegadaL);

                gHotel_entradaDC = new JTextFieldDateEditor("yyyy-MM-dd HH:mm", "####-##-## ##:##", '_');
                gHotelLeft.add(gHotel_entradaDC);

                JLabel gHotel_salidaL = new JLabel("Fecha de llegada");
                gHotel_salidaL.setFont(new Font("Tahoma", Font.PLAIN, 16));
                gHotelLeft.add(gHotel_salidaL);

                gHotel_salidaDC = new JTextFieldDateEditor("yyyy-MM-dd HH:mm", "####-##-## ##:##", '_');
                gHotelLeft.add(gHotel_salidaDC);

                gHotel_addB = new JButton("Nuevo");
                gHotel_addB.setFont(new Font("Tahoma", Font.PLAIN, 16));
                gHotel_addB.setIcon(new ImageIcon("resources/add.png"));
                gHotelLeft.add(gHotel_addB);

                gHotel_saveB = new JButton("Guardar");
                gHotel_saveB.setFont(new Font("Tahoma", Font.PLAIN, 16));
                gHotel_saveB.setIcon(new ImageIcon("resources/save.png"));
                gHotelLeft.add(gHotel_saveB);

                JPanel gHotelMid = new JPanel();
                gHotelMid.setBorder(new EmptyBorder(20, 20, 40, 20));
                gHotelFrame.getContentPane().add(gHotelMid, BorderLayout.CENTER);
                gHotelMid.setLayout(new BorderLayout(0, 10));

                JScrollPane gHotelSP = new JScrollPane();
                gHotelSP.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
                gHotelMid.add(gHotelSP, BorderLayout.CENTER);

                gHotelTable = new JTable();
                gHotelTable = new JTable(){
                        public boolean isCellEditable(int rowIndex, int colIndex){
                                return false;
                        }
                };
                DefaultTableModel tModel = new DefaultTableModel();
                gHotelTable.setModel(tModel);
                tModel.addColumn("ID Hotel");
                tModel.addColumn("Destino");
                tModel.addColumn("Nombre");
                tModel.addColumn("Ubicacion");
                tModel.addColumn("Clasificacion");
                tModel.addColumn("Huespedes");
                tModel.addColumn("Regimen");
                tModel.addColumn("Disponibilidad");
                tModel.addColumn("Check-in");
                tModel.addColumn("Check-out");
                DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
                centerRenderer.setHorizontalAlignment( JLabel.CENTER );
                for(int i = 0; i < gHotelTable.getModel().getColumnCount(); i++){
                        gHotelTable.getColumnModel().getColumn(i).setCellRenderer( centerRenderer );
                }
                gHotelTable.setRowHeight(50);

                //Generando estilo de JTable
                JTableHeader tHeader = gHotelTable.getTableHeader();
                tHeader.setPreferredSize(new Dimension(0, 25));
                tHeader.setBackground(Color.decode("#094293"));
                tHeader.setForeground(Color.white);
                tHeader.setReorderingAllowed(false);
                tHeader.setFont(new Font("Tahome", Font.BOLD, 16));
                gHotelTable.setFont(new Font("Tahome", Font.PLAIN, 14));
                gHotelSP.setViewportView(gHotelTable);

                JPanel gHotelMid_B = new JPanel();
                gHotelMid_B.setBorder(new EmptyBorder(0, 0, 20, 20));
                gHotelMid.add(gHotelMid_B, BorderLayout.SOUTH);
                gHotelMid_B.setLayout(new GridLayout(0, 6, 20, 0));

                gHotel_editB = new JButton("Editar");
                gHotel_editB.setVerticalAlignment(SwingConstants.TOP);
                gHotel_editB.setFont(new Font("Tahoma", Font.PLAIN, 16));
                gHotel_editB.setIcon(new ImageIcon("resources/edit.png"));
                gHotelMid_B.add(gHotel_editB);

                gHotel_deleteB = new JButton("Eliminar");
                gHotel_deleteB.setVerticalAlignment(SwingConstants.TOP);
                gHotel_deleteB.setFont(new Font("Tahoma", Font.PLAIN, 16));
                gHotel_deleteB.setIcon(new ImageIcon("resources/delete.png"));
                gHotelMid_B.add(gHotel_deleteB);

                gHotel_hotelB = new JButton("Detalles");
                gHotel_hotelB.setVerticalAlignment(SwingConstants.TOP);
                gHotel_hotelB.setFont(new Font("Tahoma", Font.PLAIN, 16));
                gHotel_hotelB.setIcon(new ImageIcon("resources/details.png"));
                gHotelMid_B.add(gHotel_hotelB);
                gHotel_hotelB.addActionListener(e -> {
                        DetalleHotel dhFrame = new DetalleHotel();
                        dhFrame.runFrame();
                        gHotelFrame.setVisible(false);
                });

                JPanel gHotelBottom = new JPanel();
                gHotelBottom.setBorder(new EmptyBorder(20, 20, 20, 20));
                gHotelFrame.getContentPane().add(gHotelBottom, BorderLayout.SOUTH);
                gHotelBottom.setLayout(new BorderLayout(0, 0));

                JButton gHotel_backB = new JButton("VOLVER");
                gHotel_backB.setFont(new Font("Tahoma", Font.PLAIN, 16));
                gHotel_backB.setIcon(new ImageIcon("resources/left.png"));
                gHotelBottom.add(gHotel_backB, BorderLayout.EAST);
                gHotel_backB.addActionListener(e -> {
                        MenuAdministrador maFrame = new MenuAdministrador();
                        maFrame.runFrame();
                        gHotelFrame.setVisible(false);
                });
        }

}
