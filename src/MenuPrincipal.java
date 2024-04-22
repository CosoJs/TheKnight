
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;



/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Coso
 */
public class MenuPrincipal extends JPanel implements ActionListener{
    JButton Jugar;
    JButton Salir;
    JButton Musica;
    JFrame frame;
    Musica musica = new Musica();
    
    public MenuPrincipal(JFrame frame){
        this.frame = frame;
        iniciarComponentes();
    }

    private void iniciarComponentes() {
        musica.MenuPrincipal();
        botones();//primero los botones para que se carguen su aspecto
        panel();
    }

    private void panel() {
        setLayout(null);
        setBackground(Color.red);
        add(Musica);
        add(Salir);
        add(Jugar);
    }

    private void botones() {
        Jugar = new JButton("JUGAR");
        Jugar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Multimedia/Menu principal/jugar.png")));
        Jugar.setBounds(400, 280, 204, 83);
        Jugar.setContentAreaFilled(false);
        Jugar.setBorder(null);
        Jugar.addActionListener(this);
        //el boton jugar estara en medio
        Salir = new JButton("SALIR");
        Salir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Multimedia/Menu principal/salir.png")));
        Salir.setBounds(400, 380, 204, 83);
        Salir.setContentAreaFilled(false);
        Salir.setBorder(null);
        Salir.addActionListener(this);
        //Boton para salir del juego
        Musica = new JButton();
        Musica.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Multimedia/Menu principal/musica.png")));
        Musica.setBounds(0, 450, 96, 116);
        Musica.setContentAreaFilled(false);
        Musica.setBorder(null);
        Musica.addActionListener(this);
        
    }
    
    //acciones

    @Override
    public void actionPerformed(ActionEvent e){
        if (e.getSource() == Jugar) {
            frame.setContentPane(new Cueva(frame, 1));
            frame.revalidate();
            musica.Reinicio();
            frame.repaint();
        }
        else if (e.getSource() == Salir) {
            System.exit(0);
        }
        else if (e.getSource() == Musica) {
            musica.PararOcontinuar();
        }
    }

}
