package vista;

import controlador.VentaController;
import modelo.VentaDAO;
import tercera.VistaMA;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.awt.event.ActionEvent;
import java.net.URLEncoder;

public class GestionarVentas {

    private JFrame gVentasFrame;

    /**
     * Launch the application.
     */
    public JTable gVentasTable;
    public void runFrame(){
        EventQueue.invokeLater(() -> {
            try {
                GestionarVentas window = new GestionarVentas();
                VentaDAO dao = new VentaDAO();
                VentaController c = new VentaController(window, dao);
                window.gVentasFrame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * Create the application.
     * @wbp.parser.entryPoint
     */
    public GestionarVentas() {
        initialize();
    }


    private void initialize() {
        //Iniciando JFrame
        gVentasFrame = new JFrame();
        gVentasFrame.setMinimumSize(new Dimension(1280, 720));
        gVentasFrame.setExtendedState(Frame.MAXIMIZED_BOTH);
        gVentasFrame.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\DELL\\OneDrive\\Documentos\\Tercer Semestre\\POO\\agenciaAmets\\resources\\amets.jpg"));
        gVentasFrame.setTitle("Gestionar Ventas");
        gVentasFrame.setBounds(100, 100, 1280, 720);
        gVentasFrame.setLocationRelativeTo(null);
        gVentasFrame.getContentPane().setLayout(new BorderLayout(0, 0));
        gVentasFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                int result = JOptionPane.showConfirmDialog(gVentasFrame, "¿Desea cerrar el programa?", "Salir del programa", JOptionPane.YES_NO_OPTION);
                if (result == JOptionPane.YES_OPTION)
                {
                    gVentasFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                }
                else if (result == JOptionPane.NO_OPTION)
                {
                    gVentasFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                }
            }
        });

        //JPanel North
        JPanel gVentasTop = new JPanel();
        gVentasTop.setBorder(new EmptyBorder(20, 20, 20, 20));
        gVentasFrame.getContentPane().add(gVentasTop, BorderLayout.NORTH);
        gVentasTop.setLayout(new BorderLayout(0, 0));

        //JLabel Principal "Ventas"
        JLabel gVentasLabel = new JLabel("Ventas");
        gVentasLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
        gVentasLabel.setHorizontalAlignment(SwingConstants.CENTER);
        gVentasLabel.setVerticalTextPosition(SwingConstants.CENTER);
        gVentasTop.add(gVentasLabel);

        //JPanel Center
        JPanel gVentasMid = new JPanel();
        gVentasMid.setBorder(new EmptyBorder(10, 5, 10, 5));
        gVentasFrame.getContentPane().add(gVentasMid, BorderLayout.CENTER);
        gVentasMid.setLayout(new BorderLayout(0, 0));

        //JScrollPane con una JTable dentro
        JScrollPane gVentasSP = new JScrollPane();
        gVentasSP.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        gVentasMid.add(gVentasSP, BorderLayout.CENTER);

        gVentasTable = new JTable();
        gVentasTable.setModel(new DefaultTableModel(
                new Object[][] {
                },
                new String[] {
                        "ID Venta", "Usuario", "Vendedor", "Cantidad", "Descripcion", "Fecha", "Tipo de Pago", "Subtotal", "IVA", "Total"
                }
        ));

        //Generando estilo de JTable
        JTableHeader tHeader = gVentasTable.getTableHeader();
        tHeader.setPreferredSize(new Dimension(0, 25));
        tHeader.setBackground(Color.decode("#094293"));
        tHeader.setForeground(Color.white);
        tHeader.setFont(new Font("Tahome", Font.BOLD, 16));
        gVentasTable.setFont(new Font("Tahome", Font.PLAIN, 14));
        gVentasSP.setViewportView(gVentasTable);

        //JPanel South
        JPanel gVentasBottom = new JPanel();
        gVentasBottom.setBorder(new EmptyBorder(20, 20, 20, 20));
        gVentasFrame.getContentPane().add(gVentasBottom, BorderLayout.SOUTH);
        gVentasBottom.setLayout(new BorderLayout(0, 0));

        //JButton de "Volver"
        JButton gVentas_volverButton = new JButton("VOLVER");
        gVentas_volverButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
        gVentas_volverButton.setIcon(new ImageIcon("C:\\Users\\DELL\\OneDrive\\Documentos\\Tercer Semestre\\POO\\AmetsTravels\\resources\\left.png"));
        gVentas_volverButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
        gVentas_volverButton.setFocusable(false);
        gVentas_volverButton.setBackground(Color.WHITE);
        gVentasBottom.add(gVentas_volverButton, BorderLayout.EAST);
        gVentas_volverButton.addActionListener(e -> {
            VistaMA maFrame = new VistaMA();
            maFrame.runFrame();
            gVentasFrame.setVisible(false);
        });
    }

    public static String getImgurContent(String clientID) throws Exception {
        URL url;
        url = new URL("https://api.imgur.com/3/image");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        String data = URLEncoder.encode("image", "UTF-8") + "="
                + URLEncoder.encode("https://i.imgur.com/t4N0JQj.jpg", "UTF-8");

        conn.setDoOutput(true);
        conn.setDoInput(true);
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Authorization", "Client-ID " + clientID);
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type",
                "application/x-www-form-urlencoded");

        conn.connect();
        StringBuilder stb = new StringBuilder();
        OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
        wr.write(data);
        wr.flush();

        // Get the response
        BufferedReader rd = new BufferedReader(
                new InputStreamReader(conn.getInputStream()));
        String line;
        while ((line = rd.readLine()) != null) {
            stb.append(line).append("\n");
        }
        wr.close();
        rd.close();

        return stb.toString();
    }
}
