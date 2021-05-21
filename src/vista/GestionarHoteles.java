package vista;

import java.awt.*;

import javax.swing.*;

import javax.swing.border.EmptyBorder;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Date;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class GestionarHoteles {

        private JFrame gHotelFrame;
        public JTextField gHotel_idTF, gHotel_nombreTF, gHotel_ubicacionTF, gHotel_clasifTF, gHotel_genteTF;
        private JTextField textField_1;
        private JTextField textField_2;
        private JTextField textField_3;
        private JTextField gHotel_precioTF;
        public JButton gHotel_addB, gHotel_saveB, gHotel_editB, gHotel_deleteB;
        public JTable gHotelTable;

        /**
         * Launch the application.
         */
        public void runFrame(){
                EventQueue.invokeLater(() -> {
                        try {
                                GestionarHoteles window = new GestionarHoteles();
                                window.gHotelFrame.setVisible(true);
                        } catch (Exception e) {
                                e.printStackTrace();
                        }
                });
        }

        /**
         * Create the application.
         */
        public GestionarHoteles() {
                initialize();
        }

        /**
         * Initialize the contents of the frame.
         */
        private void initialize() {
                gHotelFrame = new JFrame();
                gHotelFrame.setExtendedState(Frame.MAXIMIZED_BOTH);
                gHotelFrame.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\DELL\\OneDrive\\Documentos\\Tercer Semestre\\POO\\AmetsTravels\\resources\\amets.jpg"));
                gHotelFrame.setTitle("Gestionar Hoteles");
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

                JLabel gHotel_idL = new JLabel("ID Hotel");
                gHotel_idL.setFont(new Font("Tahoma", Font.PLAIN, 16));

                gHotel_idTF = new JTextField();
                gHotel_idTF.setFont(new Font("Tahoma", Font.PLAIN, 20));
                gHotel_idTF.setColumns(10);
                gHotel_idTF.setEditable(false);

                JLabel gHotel_destinoL = new JLabel("Destino");
                gHotel_destinoL.setFont(new Font("Tahoma", Font.PLAIN, 16));

                JComboBox gHotel_destinoCB = new JComboBox();
                gHotel_destinoCB.setModel(new DefaultComboBoxModel(new String[] {"Seleccione un destino"}));
                gHotel_destinoCB.setFont(new Font("Tahoma", Font.PLAIN, 16));

                JLabel gHotel_nombreL = new JLabel("Nombre");
                gHotel_nombreL.setFont(new Font("Tahoma", Font.PLAIN, 16));

                gHotel_nombreTF = new JTextField();
                gHotel_nombreTF.setFont(new Font("Tahoma", Font.PLAIN, 20));
                gHotel_nombreTF.setColumns(10);

                JLabel gHotel_ubicacionL = new JLabel("Ubicacion");
                gHotel_ubicacionL.setFont(new Font("Tahoma", Font.PLAIN, 16));

                gHotel_ubicacionTF = new JTextField();
                gHotel_ubicacionTF.setFont(new Font("Tahoma", Font.PLAIN, 20));
                gHotel_ubicacionTF.setColumns(10);

                JLabel gHotel_clasifL = new JLabel("Clasificacion");
                gHotel_clasifL.setFont(new Font("Tahoma", Font.PLAIN, 16));

                gHotel_clasifTF = new JTextField();
                gHotel_clasifTF.setFont(new Font("Tahoma", Font.PLAIN, 20));
                gHotel_clasifTF.setColumns(10);

                JLabel gHotel_genteL = new JLabel("Huespedes");
                gHotel_genteL.setFont(new Font("Tahoma", Font.PLAIN, 16));

                gHotel_genteTF = new JTextField();
                gHotel_genteTF.setFont(new Font("Tahoma", Font.PLAIN, 20));
                gHotel_genteTF.setColumns(10);

                JLabel gHotel_regimenL = new JLabel("Regimen");
                gHotel_regimenL.setFont(new Font("Tahoma", Font.PLAIN, 16));

                JComboBox gHotel_regimenCB = new JComboBox();
                gHotel_regimenCB.setModel(new DefaultComboBoxModel(new String[] {"Seleccione un regimen"}));
                gHotel_regimenCB.setFont(new Font("Tahoma", Font.PLAIN, 16));

                JLabel gHotel_dispoL = new JLabel("Disponibilidad");
                gHotel_dispoL.setFont(new Font("Tahoma", Font.PLAIN, 16));

                JComboBox gHotel_dispoCB = new JComboBox();
                gHotel_dispoCB.setModel(new DefaultComboBoxModel(new String[] {"Seleccione la disponibilidad"}));
                gHotel_dispoCB.setFont(new Font("Tahoma", Font.PLAIN, 16));

                JLabel gHotel_entradaL = new JLabel("Check-in");
                gHotel_entradaL.setFont(new Font("Tahoma", Font.PLAIN, 16));

                Date date = new Date();
                JSpinner gHotel_entradaJS = new JSpinner( new SpinnerDateModel());
                JSpinner.DateEditor gVuelos_entradaE = new JSpinner.DateEditor(gHotel_entradaJS, "yyyy-MM-dd HH:mm:ss");
                gHotel_entradaJS.setEditor(gVuelos_entradaE);
                gHotel_entradaJS.setValue(date);

                JLabel gHotel_salidaL = new JLabel("Check-out");
                gHotel_salidaL.setFont(new Font("Tahoma", Font.PLAIN, 16));

                JSpinner gHotel_salidaJS = new JSpinner( new SpinnerDateModel());
                JSpinner.DateEditor gVuelos_salidaE = new JSpinner.DateEditor(gHotel_salidaJS, "yyyy-MM-dd HH:mm:ss");
                gHotel_salidaJS.setEditor(gVuelos_salidaE);
                gHotel_salidaJS.setValue(date);

                gHotelLeft.setLayout(new GridLayout(0, 2, 15, 10));
                gHotelLeft.add(gHotel_idL);
                gHotelLeft.add(gHotel_idTF);
                gHotelLeft.add(gHotel_destinoL);
                gHotelLeft.add(gHotel_destinoCB);
                gHotelLeft.add(gHotel_nombreL);
                gHotelLeft.add(gHotel_nombreTF);
                gHotelLeft.add(gHotel_ubicacionL);
                gHotelLeft.add(gHotel_ubicacionTF);
                gHotelLeft.add(gHotel_clasifL);
                gHotelLeft.add(gHotel_clasifTF);
                gHotelLeft.add(gHotel_genteL);
                gHotelLeft.add(gHotel_genteTF);
                gHotelLeft.add(gHotel_regimenL);
                gHotelLeft.add(gHotel_regimenCB);
                gHotelLeft.add(gHotel_dispoL);
                gHotelLeft.add(gHotel_dispoCB);
                gHotelLeft.add(gHotel_entradaL);
                gHotelLeft.add(gHotel_entradaJS);
                gHotelLeft.add(gHotel_salidaL);
                gHotelLeft.add(gHotel_salidaJS);

                gHotel_addB = new JButton("Nuevo");
                gHotel_addB.setFont(new Font("Tahoma", Font.PLAIN, 16));
                gHotelLeft.add(gHotel_addB);

                gHotel_saveB = new JButton("Guardar");
                gHotel_saveB.setFont(new Font("Tahoma", Font.PLAIN, 16));
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
                gHotelTable.getColumnModel().getColumn(0).setCellRenderer( centerRenderer );
                gHotelTable.setRowHeight(50);

                //Generando estilo de JTable
                JTableHeader tHeader = gHotelTable.getTableHeader();
                tHeader.setPreferredSize(new Dimension(0, 25));
                tHeader.setBackground(Color.decode("#094293"));
                tHeader.setForeground(Color.white);
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
                gHotelMid_B.add(gHotel_editB);

                gHotel_deleteB = new JButton("Eliminar");
                gHotel_deleteB.setVerticalAlignment(SwingConstants.TOP);
                gHotel_deleteB.setFont(new Font("Tahoma", Font.PLAIN, 16));
                gHotelMid_B.add(gHotel_deleteB);

                JButton gHotel_hotelB = new JButton("Detalles");
                gHotel_hotelB.setVerticalAlignment(SwingConstants.TOP);
                gHotel_hotelB.setFont(new Font("Tahoma", Font.PLAIN, 16));
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

                JButton btnNewButton = new JButton("VOLVER");
                btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
                btnNewButton.setIcon(new ImageIcon("C:\\Users\\DELL\\OneDrive\\Documentos\\Tercer Semestre\\POO\\AmetsTravels\\resources\\left.png"));
                gHotelBottom.add(btnNewButton, BorderLayout.EAST);
                btnNewButton.addActionListener(e -> {
                        VistaMA maFrame = new VistaMA();
                        maFrame.runFrame();
                        gHotelFrame.setVisible(false);
                });
        }

}
