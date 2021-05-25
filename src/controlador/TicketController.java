package controlador;

import vista.Retorno;
import vista.Ticket;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;

public class TicketController implements ActionListener, WindowListener
{

    Ticket vista;
    int agentID;

    public TicketController(Ticket v, int agentID){
        this.vista = v;
        this.agentID = agentID;
        vista.ticket_printB.addActionListener(this);
        vista.ticket_menuB.addActionListener(this);
        vista.ticketFrame.addWindowListener(this);
    }

    public void Print(){

        PrinterJob job = PrinterJob.getPrinterJob();
        job.setJobName("Print Data");

        job.setPrintable(new Printable() {

            public int print(Graphics pg, PageFormat pf, int pageNum){
                pf.setOrientation(PageFormat.LANDSCAPE);
                if(pageNum>0){
                    return Printable.NO_SUCH_PAGE;
                }

                Graphics2D g2 = (Graphics2D)pg;
                g2.translate(pf.getImageableX(), pf.getImageableY());
                g2.scale(1,1);

                vista.ticketPanel.paint(g2);
                return Printable.PAGE_EXISTS;
            }
        });

        boolean ok = job.printDialog();
        if(ok){
            try{
                job.print();
            }
            catch (PrinterException ex){}
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == vista.ticket_menuB) {
            Retorno rtn = new Retorno();
            vista.ticketFrame.setVisible(rtn.runReturn(agentID));
        }

        if (e.getSource() == vista.ticket_printB) {
            Print();
        }
    }

    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {

        if (e.getSource() == vista.ticketFrame) {
            int result = JOptionPane.showConfirmDialog(vista.ticketFrame, "¿Desea cerrar el programa?", "Salir del programa", JOptionPane.YES_NO_OPTION);
            if (result == JOptionPane.YES_OPTION)
            {
                vista.ticketFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            }
            else if (result == JOptionPane.NO_OPTION)
            {
                vista.ticketFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            }
        }
    }

    @Override
    public void windowClosed(WindowEvent e) {

    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }
}
