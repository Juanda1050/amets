package segunda;


import Controlador.ControladorRU;
import com.toedter.calendar.JTextFieldDateEditor;
import modelo.UsuarioDAO;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.*;
import javax.swing.border.EmptyBorder;


public class RegistroUsuarios {

    public JFrame ruFrame;
    public JTextField ruDireccionTF;
    public JTextField ruTelefonoTF;
    public JTextField ruEmailTF;
    public JTextField ruNombreTF;
    public JTextField ruApellidoTF;
    public JTextFieldDateEditor ruNacimientoTF;
    public JButton ruSiguienteBtn;
    public JButton ruVolverBtn;

    /**
     * Launch the application.
     */
    public void runFrame(int agentID){
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    RegistroUsuarios window = new RegistroUsuarios();
                    UsuarioDAO dao = new UsuarioDAO();
                    ControladorRU c = new ControladorRU(window, dao, agentID);
                    window.ruFrame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public RegistroUsuarios() {
        initialize();
    }


    /**
     * Initialize the contents of the frame.
     */
    public void initialize() {

        ruFrame = new JFrame();
        ruFrame.setExtendedState(Frame.MAXIMIZED_BOTH);
        ruFrame.setTitle("Amets Travel");
        ruFrame.setBounds(100, 100, 1280, 720);

        JPanel ruTop = new JPanel();
        ruTop.setBorder(new EmptyBorder(20, 20, 20, 20));
        ruFrame.getContentPane().add(ruTop, BorderLayout.NORTH);
        ruTop.setLayout(new BorderLayout(0, 0));

        JLabel ruLabel = new JLabel("Registro de Usuarios");
        ruLabel.setHorizontalAlignment(SwingConstants.CENTER);
        ruLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
        ruTop.add(ruLabel, BorderLayout.NORTH);

        JPanel ruMid = new JPanel();
        ruFrame.getContentPane().add(ruMid, BorderLayout.CENTER);
        ruMid.setBorder(new EmptyBorder(20, 20, 20, 20));
        ruMid.setLayout(new BorderLayout(0, 0));

        Panel ruMidEast = new Panel();
        ruMid.add(ruMidEast, BorderLayout.CENTER);
        ruMidEast.setLayout(new GridLayout(0, 2, 0, 30));

        Label ruNombreLbl = new Label("Nombre(s)");
        ruMidEast.add(ruNombreLbl);
        ruNombreLbl.setFont(new Font("Tahoma", Font.PLAIN, 16));

        ruNombreTF = new JTextField();
        ruNombreTF.setFont(new Font("Tahoma", Font.PLAIN, 20));
        ruNombreTF.setHorizontalAlignment(SwingConstants.LEFT);
        ruNombreTF.setColumns(10);
        ruNombreTF.setAlignmentX(1.0f);
        ruMidEast.add(ruNombreTF);
        ruNombreTF.addKeyListener(new KeyAdapter()
        {
            public void keyTyped(KeyEvent e)
            {
                char caracter = e.getKeyChar();
                if(!(Character.isLetter(caracter)||Character.isWhitespace(caracter)||Character.isISOControl(caracter))||ruNombreTF.getText().length()==35)
                {
                    e.consume();
                }
            }
        });

        Label ruApellidoLbl = new Label("Apellidos");
        ruMidEast.add(ruApellidoLbl);
        ruApellidoLbl.setFont(new Font("Tahoma", Font.PLAIN, 16));

        ruApellidoTF = new JTextField();
        ruApellidoTF.setFont(new Font("Tahoma", Font.PLAIN, 20));
        ruApellidoTF.setHorizontalAlignment(SwingConstants.LEFT);
        ruApellidoTF.setColumns(10);
        ruApellidoTF.setAlignmentX(1.0f);
        ruMidEast.add(ruApellidoTF);
        ruApellidoTF.addKeyListener(new KeyAdapter()
        {
            public void keyTyped(KeyEvent e)
            {
                char caracter = e.getKeyChar();
                if(!(Character.isLetter(caracter)||Character.isWhitespace(caracter)||Character.isISOControl(caracter))||ruApellidoTF.getText().length()==45)
                {
                    e.consume();
                }
            }
        });

        Label ruNacimientoLbl = new Label("Fecha de Nacimiento (yyyy-MM-dd)");
        ruMidEast.add(ruNacimientoLbl);
        ruNacimientoLbl.setFont(new Font("Tahoma", Font.PLAIN, 16));

        ruNacimientoTF = new JTextFieldDateEditor("yyyy-MM-dd","####-##-##",'_');
        ruNacimientoTF.setFont(new Font("Tahoma", Font.PLAIN, 20));
        ruNacimientoTF.setHorizontalAlignment(SwingConstants.LEFT);
        ruNacimientoTF.setColumns(10);
        ruNacimientoTF.setAlignmentX(1.0f);
        ruMidEast.add(ruNacimientoTF);

        Label ruDireccionLbl = new Label("Direcci\u00F3n");
        ruMidEast.add(ruDireccionLbl);
        ruDireccionLbl.setFont(new Font("Tahoma", Font.PLAIN, 16));

        ruDireccionTF = new JTextField();
        ruDireccionTF.setFont(new Font("Tahoma", Font.PLAIN, 20));
        ruDireccionTF.setHorizontalAlignment(SwingConstants.LEFT);
        ruDireccionTF.setAlignmentX(1.0f);
        ruDireccionTF.setColumns(10);
        ruMidEast.add(ruDireccionTF);
        ruDireccionTF.addKeyListener(new KeyAdapter()
        {
            public void keyTyped(KeyEvent e)
            {
                if(ruDireccionTF.getText().length()==45)
                {
                    e.consume();
                }
            }
        });

        Label ruEmailLbl = new Label("Email");
        ruMidEast.add(ruEmailLbl);
        ruEmailLbl.setFont(new Font("Tahoma", Font.PLAIN, 16));

        ruEmailTF = new JTextField();
        ruEmailTF.setFont(new Font("Tahoma", Font.PLAIN, 20));
        ruEmailTF.setHorizontalAlignment(SwingConstants.LEFT);
        ruEmailTF.setAlignmentX(Component.RIGHT_ALIGNMENT);
        ruEmailTF.setColumns(10);
        ruMidEast.add(ruEmailTF);
        ruEmailTF.addKeyListener(new KeyAdapter()
        {
            public void keyTyped(KeyEvent e)
            {
                if(ruEmailTF.getText().length()==45)
                {
                    e.consume();
                }
            }
        });

        Label ruTelefonoLbl = new Label("Tel\u00E9fono");
        ruMidEast.add(ruTelefonoLbl);
        ruTelefonoLbl.setFont(new Font("Tahoma", Font.PLAIN, 16));

        ruTelefonoTF = new JTextField();
        ruTelefonoTF.setFont(new Font("Tahoma", Font.PLAIN, 20));
        ruTelefonoTF.setHorizontalAlignment(SwingConstants.LEFT);
        ruTelefonoTF.setAlignmentX(1.0f);
        ruTelefonoTF.setColumns(10);
        ruMidEast.add(ruTelefonoTF);
        ruTelefonoTF.addKeyListener(new KeyAdapter()
        {
            public void keyTyped(KeyEvent e)
            {
                char caracter = e.getKeyChar();
                if(((caracter < '0') || (caracter > '9')) && (caracter != '\b' /*corresponde a BACK_SPACE*/) || ruTelefonoTF.getText().length()== 10)
                {
                    e.consume();
                }
            }
        });

        JPanel ruBottom = new JPanel();
        ruBottom.setBorder(new EmptyBorder(30, 20, 20, 20));
        ruFrame.getContentPane().add(ruBottom, BorderLayout.SOUTH);
        ruBottom.setLayout(new BorderLayout(0, 0));

        ruSiguienteBtn = new JButton("Siguiente");
        ruSiguienteBtn.setFont(new Font("Tahoma", Font.PLAIN, 16));
        ruBottom.add(ruSiguienteBtn, BorderLayout.EAST);

        ruVolverBtn = new JButton("Volver");
        ruVolverBtn.setFont(new Font("Tahoma", Font.PLAIN, 16));
        ruBottom.add(ruVolverBtn, BorderLayout.WEST);
    }

}