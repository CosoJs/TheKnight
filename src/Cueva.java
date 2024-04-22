
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Coso
 */
public final class Cueva extends JPanel implements KeyListener{
    int nivel;
    JFrame frame;
    Musica musica = new Musica();
    Fondo fondo = new Fondo(this);
    Player personaje = new Player(this);
    Esqueleto[] esqueletos; //arrays de enemigos esqueletos
    JButton Jugar;
    JButton Salir;
    JLabel gameover = new JLabel();
    JLabel puntuacion;
    
    @SuppressWarnings("LeakingThisInConstructor")
    public Cueva(JFrame frame, int nivel){
        this.nivel = nivel;
        this.frame = frame;
        musica.Cueva();
        setLayout(null);
        setBackground(Color.black);
        frame.addKeyListener(this);
        botones();
        generacion();
        puntuacion = new javax.swing.JLabel();
        puntuacion.setBounds(300, 10, 400, 90);
        puntuacion.setFont(new java.awt.Font("Arial Black", 1, 36)); // NOI18N
        puntuacion.setForeground(new java.awt.Color(51, 102, 255));
    }
    
    private void generacion(){
        esqueletos = new Esqueleto[1 * nivel];
        for (int i = 0; i < esqueletos.length; i++) {
            this.esqueletos[i] = new Esqueleto(this, personaje);
        }
    }
    private void botones() {
        gameover.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Multimedia/cueva/Game_Over_Logo.png")));
        gameover.setSize(382, 176);
        gameover.setLocation( 320 , 100);
        Jugar = new JButton();
        Jugar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Multimedia/cueva/reiniciar.png")));
        Jugar.setBounds(200, 380, 162, 66);
        Jugar.setContentAreaFilled(false); // que sea transparente
        //el boton jugar estara en medio
        Salir = new JButton();
        Salir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Multimedia/cueva/regresar.png")));
        Salir.setBounds(650, 380, 162, 66);
        Salir.setContentAreaFilled(false);
        //Boton para salir del juego
        add(gameover);
        add(Salir);
        add(Jugar);
        gameover.setVisible(false);
        Salir.setVisible(false);
        Jugar.setVisible(false);
        Jugar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Reinicio();
            }
        });
        Salir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        dibujar(g2);
    }
    
    
    private void dibujar(Graphics2D g) {
        puntuacion.setText("Nivel: " + nivel + " Puntos: " + personaje.puntuacion);
        this.add(puntuacion);
        if (personaje.puntuacion >= (nivel * 10)) {
            nivel += 1;
            generacion();
        }
        if (personaje.vida <= 0)
            actionPerformed();
        else{
            fondo.setCuevaFondo();
            fondo.dibujar(g);
            personaje.dibujar(g);
            for (Esqueleto esqueleto : esqueletos) {
                    esqueleto.dibujar(g);
            }
            
        }
    }
    
    
    @Override
    public void keyPressed(KeyEvent e) {
        personaje.keyPressed(e);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        personaje.keyReleased(e);
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }
    
    public void actionPerformed() {
        gameover.setVisible(true);
        Salir.setVisible(true);
        Jugar.setVisible(true);
        puntuacion.setLocation(300, 250);
    }
    
    public void Reinicio(){
        frame.setContentPane(new Cueva(frame, 1));
        frame.revalidate();
        frame.repaint();
    }
    
}

/*
    private void botonPrueba(){
        JButton prueba = new JButton("Coordenadas");
        prueba.setBounds(450, 280, 100, 40);
        add(prueba);
        prueba.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("LimiteDerecho: " + personaje.limiteDerecho); 
                System.out.println("LimiteIzquierdo: " + personaje.limiteIzquierdo);
                System.out.println("LimiteSuperior: " + personaje.limiteSuperior);
                System.out.println("LimiteInferior: " + personaje.limiteInferior);
                System.out.println("LimiteDerecho Esqueleto: " + esqueletos[0].limiteDerechoAtaque);
                System.out.println("LimiteIzquierdo Esqueleto: " + esqueletos[0].limiteIzquierdoAtaque);
                System.out.println("LimiteSuperior Esqueleto: " + esqueletos[0].limiteSuperiorAtaque);
                System.out.println("LimiteInferior Esqueleto: " + esqueletos[0].limiteInferiorAtaque);
            }
        });
    } */
