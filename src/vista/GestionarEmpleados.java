package vista;

import controlador.EmpleadoController;
import modelo.EmpleadoDAO;

import java.awt.*;

import javax.swing.*;

import javax.swing.border.EmptyBorder;

import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import java.awt.event.*;

public class GestionarEmpleados {

    private JFrame gEmpleadoFrame;
    public JTextField gEmpleado_idTF, gEmpleado_nombreTF, gEmpleado_apellidoTF, gEmpleado_contraTF;
    public JComboBox gEmpleado_turnoCB, gEmpleado_puestoCB;
    public JButton gEmpleado_addB , gEmpleado_saveB, gEmpleado_editB, gEmpleado_deleteB;
    public JTable gEmpleadoTable;
    private int limiteNombre = 35, limiteApellido = 45, limiteContra = 15;

    public void runFrame(){
        EventQueue.invokeLater(() -> {
            try {
                GestionarEmpleados window = new GestionarEmpleados();
                EmpleadoDAO empleadoDAO = new EmpleadoDAO();
                EmpleadoController controladorEmpleado = new EmpleadoController(window, empleadoDAO);

                window.gEmpleadoFrame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public GestionarEmpleados() {
        initialize();
    }

    private void initialize() {
        //Inicia el frame
        gEmpleadoFrame = new JFrame("Gestionar Empleados");
        gEmpleadoFrame.setIconImage(Toolkit.getDefaultToolkit().getImage("resources/amets.jpg"));
        gEmpleadoFrame.setBounds(100, 100, 1280, 720);
        gEmpleadoFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gEmpleadoFrame.setExtendedState(Frame.MAXIMIZED_BOTH);
        gEmpleadoFrame.getContentPane().setLayout(new BorderLayout(0, 0));
        gEmpleadoFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                int result = JOptionPane.showConfirmDialog(gEmpleadoFrame, "¿Desea cerrar el programa?", "Salir del programa", JOptionPane.YES_NO_OPTION);
                if (result == JOptionPane.YES_OPTION) {
                    gEmpleadoFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                } else if (result == JOptionPane.NO_OPTION) {
                    gEmpleadoFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                }
            }
        });

        //Panel Norte del Frame
        JPanel gEmpleadoTop = new JPanel();
        gEmpleadoTop.setBorder(new EmptyBorder(20, 20, 20, 20));
        gEmpleadoFrame.getContentPane().add(gEmpleadoTop, BorderLayout.NORTH);
        gEmpleadoTop.setLayout(new BorderLayout(0, 0));

        //Label para identificar el nuevo elemento
        JLabel gEmpleado_addL = new JLabel("Nuevo Empleado");
        gEmpleado_addL.setFont(new Font("Tahoma", Font.BOLD, 18));
        gEmpleadoTop.add(gEmpleado_addL, BorderLayout.WEST);

        //Label principal
        JLabel gEmpleadoL = new JLabel("Empleados");
        gEmpleadoL.setHorizontalAlignment(SwingConstants.CENTER);
        gEmpleadoL.setFont(new Font("Tahoma", Font.BOLD, 18));
        gEmpleadoTop.add(gEmpleadoL, BorderLayout.CENTER);

        //Panel izquierdo del Frame
        JPanel gEmpleadoLeft = new JPanel();
        gEmpleadoLeft.setBorder(new EmptyBorder(20, 20, 20, 20));
        gEmpleadoFrame.getContentPane().add(gEmpleadoLeft, BorderLayout.WEST);
        gEmpleadoLeft.setLayout(new GridLayout(0, 2, 10, 50));

        //Label ID
        JLabel gEmpleado_idL = new JLabel("ID Empleado");
        gEmpleado_idL.setFont(new Font("Tahoma", Font.PLAIN, 16));
        gEmpleadoLeft.add(gEmpleado_idL);

        //TextField ID
        gEmpleado_idTF = new JTextField();
        gEmpleado_idTF.setFont(new Font("Tahoma", Font.PLAIN, 16));
        gEmpleado_idTF.setEditable(false);
        gEmpleadoLeft.add(gEmpleado_idTF);
        gEmpleado_idTF.setColumns(10);

        //Label nombre
        JLabel gEmpleado_nombreL = new JLabel("Nombre(s)");
        gEmpleado_nombreL.setFont(new Font("Tahoma", Font.PLAIN, 16));
        gEmpleadoLeft.add(gEmpleado_nombreL);

        //TextField de nombre con validacion
        gEmpleado_nombreTF = new JTextField();
        gEmpleado_nombreTF.setFont(new Font("Tahoma", Font.PLAIN, 16));
        gEmpleadoLeft.add(gEmpleado_nombreTF);
        gEmpleado_nombreTF.setColumns(10);
        gEmpleado_nombreTF.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char ch = e.getKeyChar();
                if (Character.isLetter(ch) || Character.isISOControl(ch) ||  Character.isSpaceChar(ch)) {
                }
                else {
                    e.consume();
                    JOptionPane.showMessageDialog(null, "Solo admite letras");
                }
                if(gEmpleado_apellidoTF.getText().length() >= limiteNombre){
                    e.consume();
                    Toolkit.getDefaultToolkit().beep();
                }
            }
        });

        //Label apellido
        JLabel gEmpleado_apellidoL = new JLabel("Apellidos");
        gEmpleado_apellidoL.setFont(new Font("Tahoma", Font.PLAIN, 16));
        gEmpleadoLeft.add(gEmpleado_apellidoL);

        //TextField de apellido con validacion
        gEmpleado_apellidoTF = new JTextField();
        gEmpleado_apellidoTF.setFont(new Font("Tahoma", Font.PLAIN, 16));
        gEmpleadoLeft.add(gEmpleado_apellidoTF);
        gEmpleado_apellidoTF.setColumns(10);
        gEmpleado_apellidoTF.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char ch = e.getKeyChar();
                if (Character.isLetter(ch) || Character.isISOControl(ch) ||  Character.isSpaceChar(ch)) {
                }
                else {
                    e.consume();
                    JOptionPane.showMessageDialog(null, "Escriba solo letras");
                }
                if(gEmpleado_apellidoTF.getText().length() >= limiteApellido){
                    e.consume();
                    Toolkit.getDefaultToolkit().beep();
                }
            }
        });

        //Label de contraseña
        JLabel gEmpleado_contraL = new JLabel("Contraseña");
        gEmpleado_contraL.setBackground(new Color(240, 240, 240));
        gEmpleado_contraL.setFont(new Font("Tahoma", Font.PLAIN, 16));
        gEmpleadoLeft.add(gEmpleado_contraL);

        //TextField de contraseña con validacion
        gEmpleado_contraTF = new JTextField();
        gEmpleado_contraTF.setFont(new Font("Tahoma", Font.PLAIN, 16));
        gEmpleadoLeft.add(gEmpleado_contraTF);
        gEmpleado_contraTF.setColumns(10);
        gEmpleado_contraTF.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if(gEmpleado_contraTF.getText().length() >= limiteContra){
                    e.consume();
                    Toolkit.getDefaultToolkit().beep();
                }
            }
        });

        //Label de turno
        JLabel gEmpleado_turnoL = new JLabel("Turno");
        gEmpleado_turnoL.setFont(new Font("Tahoma", Font.PLAIN, 16));
        gEmpleadoLeft.add(gEmpleado_turnoL);

        //ComboBox para seleccionar el turno de trabajo
        gEmpleado_turnoCB = new JComboBox();
        gEmpleado_turnoCB.setModel(new DefaultComboBoxModel(new String[] {"Seleccione turno de trabajo", "1", "2"}));
        gEmpleado_turnoCB.setFont(new Font("Tahoma", Font.PLAIN, 16));
        gEmpleadoLeft.add(gEmpleado_turnoCB);

        //Label de Puesto
        JLabel gEmpleado_puestoL = new JLabel("Puesto");
        gEmpleado_puestoL.setFont(new Font("Tahoma", Font.PLAIN, 16));
        gEmpleadoLeft.add(gEmpleado_puestoL);

        //ComboBox para seleccionar el puesto de trabajo
        gEmpleado_puestoCB = new JComboBox();
        gEmpleado_puestoCB.setModel(new DefaultComboBoxModel(new String[] {"Seleccione puesto de trabajo", "1", "2"}));
        gEmpleado_puestoCB.setFont(new Font("Tahoma", Font.PLAIN, 16));
        gEmpleadoLeft.add(gEmpleado_puestoCB);

        //Boton nuevo
        gEmpleado_addB = new JButton("Agregar");
        gEmpleado_addB.setFont(new Font("Tahoma", Font.PLAIN, 16));
        gEmpleado_addB.setIcon(new ImageIcon("resources/add.png"));
        gEmpleadoLeft.add(gEmpleado_addB);

        //Boton guardar
        gEmpleado_saveB = new JButton("Guardar");
        gEmpleado_saveB.setFont(new Font("Tahoma", Font.PLAIN, 16));
        gEmpleado_saveB.setIcon(new ImageIcon("resources/save.png"));
        gEmpleadoLeft.add(gEmpleado_saveB);

        //Panel Central del Frame
        JPanel gEmpleadoMid = new JPanel();
        gEmpleadoMid.setBorder(new EmptyBorder(20, 20, 40, 20));
        gEmpleadoFrame.getContentPane().add(gEmpleadoMid, BorderLayout.CENTER);
        gEmpleadoMid.setLayout(new BorderLayout(0, 10));

        //Contenedor de Jtable
        JScrollPane gEmpleadoSP = new JScrollPane();
        gEmpleadoMid.add(gEmpleadoSP);

        //Creacion de tabla en Panel Central
        gEmpleadoTable = new JTable();
        gEmpleadoTable = new JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                return false;
            }
        };
        DefaultTableModel tModel = new DefaultTableModel();
        gEmpleadoTable.setModel(tModel);
        tModel.addColumn("ID Empleado");
        tModel.addColumn("Nombre(s)");
        tModel.addColumn("Apellidos");
        tModel.addColumn("Contraseña");
        tModel.addColumn("Turno");
        tModel.addColumn("Puesto");
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );
        gEmpleadoTable.getColumnModel().getColumn(0).setCellRenderer( centerRenderer );
        gEmpleadoTable.setRowHeight(50);
        gEmpleadoTable.setFont(new Font("Tahoma", Font.PLAIN, 16));
        for(int i = 0; i < gEmpleadoTable.getModel().getColumnCount(); i++){
            gEmpleadoTable.getColumnModel().getColumn(i).setCellRenderer( centerRenderer );
        }
        gEmpleadoSP.setViewportView(gEmpleadoTable);

        //Generando estilo de JTable en el encabezado
        JTableHeader tHeader = gEmpleadoTable.getTableHeader();
        tHeader.setPreferredSize(new Dimension(0, 25));
        tHeader.setBackground(Color.decode("#094293"));
        tHeader.setForeground(Color.white);
        tHeader.setReorderingAllowed(false); //Evita que se muevan las columnas
        tHeader.setFont(new Font("Tahome", Font.BOLD, 16));
        gEmpleadoTable.setFont(new Font("Tahome", Font.PLAIN, 16));
        gEmpleadoSP.setViewportView(gEmpleadoTable);

        //Panel Sur de Panel Central del Frame
        JPanel gEmpleadoMid_bottom = new JPanel();
        gEmpleadoMid_bottom.setBorder(new EmptyBorder(0, 0, 20, 20));
        gEmpleadoMid.add(gEmpleadoMid_bottom, BorderLayout.SOUTH);
        gEmpleadoMid_bottom.setLayout(new GridLayout(0, 6, 20, 0));

        //Boton editar
        gEmpleado_editB = new JButton("Editar");
        gEmpleado_editB.setFont(new Font("Tahoma", Font.PLAIN, 16));
        gEmpleado_editB.setIcon(new ImageIcon("resources/edit.png"));
        gEmpleadoMid_bottom.add(gEmpleado_editB);

        //Boton eliminar
        gEmpleado_deleteB = new JButton("Eliminar");
        gEmpleado_deleteB.setFont(new Font("Tahoma", Font.PLAIN, 16));
        gEmpleado_deleteB.setIcon(new ImageIcon("resources/delete.png"));
        gEmpleadoMid_bottom.add(gEmpleado_deleteB);

        //Panel Sur del Frame
        JPanel gPromoBottom = new JPanel();
        gPromoBottom.setBorder(new EmptyBorder(20, 20, 20, 20));
        gEmpleadoFrame.getContentPane().add(gPromoBottom, BorderLayout.SOUTH);
        gPromoBottom.setLayout(new BorderLayout(0, 0));

        //Boton para regresar al Menu Administrador
        JButton gEmpleado_backB = new JButton("VOLVER");
        gEmpleado_backB.setFont(new Font("Tahoma", Font.PLAIN, 16));
        gEmpleado_backB.setIcon(new ImageIcon("resources/left.png"));
        gPromoBottom.add(gEmpleado_backB, BorderLayout.EAST);
        gEmpleado_backB.addActionListener(e -> {
            VistaMA maFrame = new VistaMA();
            maFrame.runFrame();
            gEmpleadoFrame.setVisible(false);
        });
    }

}
