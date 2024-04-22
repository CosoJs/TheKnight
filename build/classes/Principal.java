
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

/**
 *
 * @author Coso
 */
public class Principal {

    /**
     * @param args the command line arguments
     */
    @SuppressWarnings("SleepWhileInLoop")
    public static void main(String[] args) {
        // TODO code application logic here
        JFrame ventana = new JFrame("TheKnigh");
        MenuPrincipal menuprincipal = new MenuPrincipal(ventana);
        ventana.setFocusable(true);
        ventana.add(menuprincipal);
        ventana.setSize(1000, 600);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setLocationRelativeTo(null);
        ventana.getContentPane().add(menuprincipal);
        ventana.setVisible(true);
        ventana.setResizable(false);
        while (true) {            
            ventana.repaint();
            try {
                Thread.sleep(10);
            } catch (InterruptedException ex) {
                Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
