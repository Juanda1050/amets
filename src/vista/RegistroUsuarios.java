package vista;


import com.toedter.calendar.JTextFieldDateEditor;
import controlador.UsuarioController;
import modelo.UsuarioDAO;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.*;
import javax.swing.border.EmptyBorder;


public class RegistroUsuarios {

    public JFrame rUsuariosFrame;
    public JTextField rUsuarios_direccionTF, rUsuarios_telefonoTF, rUsuarios_emailTF, rUsuarios_nombreTF, rUsuarios_apellidoTF;
    public JTextFieldDateEditor rUsuarios_cumpleTF;
    public JButton rUsuarios_nextB, rUsuarios_backB;

    public void runFrame(int agentID){
        EventQueue.invokeLater(() -> {
            try {
                RegistroUsuarios window = new RegistroUsuarios();
                UsuarioDAO dao = new UsuarioDAO();
                UsuarioController c = new UsuarioController(window, dao, agentID);
                window.rUsuariosFrame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public RegistroUsuarios() {
        initialize();
    }

    public void initialize() {
        rUsuariosFrame = new JFrame("Registrar Usuario");
        rUsuariosFrame.setExtendedState(Frame.MAXIMIZED_BOTH);
        rUsuariosFrame.setIconImage(Toolkit.getDefaultToolkit().getImage("resources/amets.jpg"));
        rUsuariosFrame.setBounds(100, 100, 1280, 720);

        JPanel rUsuariosTop = new JPanel();
        rUsuariosTop.setBorder(new EmptyBorder(20, 20, 20, 20));
        rUsuariosFrame.getContentPane().add(rUsuariosTop, BorderLayout.NORTH);
        rUsuariosTop.setLayout(new BorderLayout(0, 0));

        JLabel rUsuariosL = new JLabel("Registro de Usuarios");
        rUsuariosL.setHorizontalAlignment(SwingConstants.CENTER);
        rUsuariosL.setFont(new Font("Tahoma", Font.BOLD, 18));
        rUsuariosTop.add(rUsuariosL, BorderLayout.NORTH);

        JPanel rUsuariosMid = new JPanel();
        rUsuariosFrame.getContentPane().add(rUsuariosMid, BorderLayout.CENTER);
        rUsuariosMid.setBorder(new EmptyBorder(20, 20, 20, 20));
        rUsuariosMid.setLayout(new BorderLayout(0, 0));

        Panel rUsuariosMid_R = new Panel();
        rUsuariosMid.add(rUsuariosMid_R, BorderLayout.CENTER);
        rUsuariosMid_R.setLayout(new GridLayout(0, 2, 0, 30));

        Label rUsuarios_nombreL = new Label("Nombre(s)");
        rUsuariosMid_R.add(rUsuarios_nombreL);
        rUsuarios_nombreL.setFont(new Font("Tahoma", Font.PLAIN, 16));

        rUsuarios_nombreTF = new JTextField();
        rUsuarios_nombreTF.setFont(new Font("Tahoma", Font.PLAIN, 20));
        rUsuarios_nombreTF.setHorizontalAlignment(SwingConstants.LEFT);
        rUsuarios_nombreTF.setColumns(10);
        rUsuarios_nombreTF.setAlignmentX(1.0f);
        rUsuariosMid_R.add(rUsuarios_nombreTF);
        rUsuarios_nombreTF.addKeyListener(new KeyAdapter()
        {
            public void keyTyped(KeyEvent e)
            {
                char caracter = e.getKeyChar();
                if(!(Character.isLetter(caracter)||Character.isWhitespace(caracter)||Character.isISOControl(caracter))||rUsuarios_nombreTF.getText().length()==35)
                {
                    e.consume();
                }
            }
        });

        Label rUsuarios_apellidoL = new Label("Apellidos");
        rUsuariosMid_R.add(rUsuarios_apellidoL);
        rUsuarios_apellidoL.setFont(new Font("Tahoma", Font.PLAIN, 16));

        rUsuarios_apellidoTF = new JTextField();
        rUsuarios_apellidoTF.setFont(new Font("Tahoma", Font.PLAIN, 20));
        rUsuarios_apellidoTF.setHorizontalAlignment(SwingConstants.LEFT);
        rUsuarios_apellidoTF.setColumns(10);
        rUsuarios_apellidoTF.setAlignmentX(1.0f);
        rUsuariosMid_R.add(rUsuarios_apellidoTF);
        rUsuarios_apellidoTF.addKeyListener(new KeyAdapter()
        {
            public void keyTyped(KeyEvent e)
            {
                char caracter = e.getKeyChar();
                if(!(Character.isLetter(caracter)||Character.isWhitespace(caracter)||Character.isISOControl(caracter))||rUsuarios_apellidoTF.getText().length()==45)
                {
                    e.consume();
                }
            }
        });

        Label rUsuarios_cumpleL = new Label("Fecha de Nacimiento (yyyy-MM-dd)");
        rUsuariosMid_R.add(rUsuarios_cumpleL);
        rUsuarios_cumpleL.setFont(new Font("Tahoma", Font.PLAIN, 16));

        rUsuarios_cumpleTF = new JTextFieldDateEditor("yyyy-MM-dd","####-##-##",'_');
        rUsuarios_cumpleTF.setFont(new Font("Tahoma", Font.PLAIN, 20));
        rUsuarios_cumpleTF.setHorizontalAlignment(SwingConstants.LEFT);
        rUsuarios_cumpleTF.setColumns(10);
        rUsuarios_cumpleTF.setAlignmentX(1.0f);
        rUsuariosMid_R.add(rUsuarios_cumpleTF);

        Label rUsuarios_direccionL = new Label("Direccion");
        rUsuariosMid_R.add(rUsuarios_direccionL);
        rUsuarios_direccionL.setFont(new Font("Tahoma", Font.PLAIN, 16));

        rUsuarios_direccionTF = new JTextField();
        rUsuarios_direccionTF.setFont(new Font("Tahoma", Font.PLAIN, 20));
        rUsuarios_direccionTF.setHorizontalAlignment(SwingConstants.LEFT);
        rUsuarios_direccionTF.setAlignmentX(1.0f);
        rUsuarios_direccionTF.setColumns(10);
        rUsuariosMid_R.add(rUsuarios_direccionTF);
        rUsuarios_direccionTF.addKeyListener(new KeyAdapter()
        {
            public void keyTyped(KeyEvent e)
            {
                if(rUsuarios_direccionTF.getText().length()==45)
                {
                    e.consume();
                }
            }
        });

        Label rUsuarios_emailL = new Label("Email");
        rUsuariosMid_R.add(rUsuarios_emailL);
        rUsuarios_emailL.setFont(new Font("Tahoma", Font.PLAIN, 16));

        rUsuarios_emailTF = new JTextField();
        rUsuarios_emailTF.setFont(new Font("Tahoma", Font.PLAIN, 20));
        rUsuarios_emailTF.setHorizontalAlignment(SwingConstants.LEFT);
        rUsuarios_emailTF.setAlignmentX(Component.RIGHT_ALIGNMENT);
        rUsuarios_emailTF.setColumns(10);
        rUsuariosMid_R.add(rUsuarios_emailTF);
        rUsuarios_emailTF.addKeyListener(new KeyAdapter()
        {
            public void keyTyped(KeyEvent e)
            {
                if(rUsuarios_emailTF.getText().length()==45)
                {
                    e.consume();
                }
            }
        });

        Label rUsuarios_telefonoL = new Label("Tel\u00E9fono");
        rUsuariosMid_R.add(rUsuarios_telefonoL);
        rUsuarios_telefonoL.setFont(new Font("Tahoma", Font.PLAIN, 16));

        rUsuarios_telefonoTF = new JTextField();
        rUsuarios_telefonoTF.setFont(new Font("Tahoma", Font.PLAIN, 20));
        rUsuarios_telefonoTF.setHorizontalAlignment(SwingConstants.LEFT);
        rUsuarios_telefonoTF.setAlignmentX(1.0f);
        rUsuarios_telefonoTF.setColumns(10);
        rUsuariosMid_R.add(rUsuarios_telefonoTF);
        rUsuarios_telefonoTF.addKeyListener(new KeyAdapter()
        {
            public void keyTyped(KeyEvent e)
            {
                char caracter = e.getKeyChar();
                if(((caracter < '0') || (caracter > '9')) && (caracter != '\b' /*corresponde a BACK_SPACE*/) || rUsuarios_telefonoTF.getText().length()== 10)
                {
                    e.consume();
                }
            }
        });

        JPanel rUsuariosBottom = new JPanel();
        rUsuariosBottom.setBorder(new EmptyBorder(30, 20, 20, 20));
        rUsuariosFrame.getContentPane().add(rUsuariosBottom, BorderLayout.SOUTH);
        rUsuariosBottom.setLayout(new BorderLayout(0, 0));

        rUsuarios_nextB = new JButton("Siguiente");
        rUsuarios_nextB.setFont(new Font("Tahoma", Font.PLAIN, 16));
        rUsuarios_nextB.setIcon(new ImageIcon("resources/siguiente.png"));
        rUsuariosBottom.add(rUsuarios_nextB, BorderLayout.EAST);

        rUsuarios_backB = new JButton("Volver");
        rUsuarios_backB.setFont(new Font("Tahoma", Font.PLAIN, 16));
        rUsuarios_backB.setIcon(new ImageIcon("resources/left.png"));
        rUsuariosBottom.add(rUsuarios_backB, BorderLayout.WEST);
    }

}