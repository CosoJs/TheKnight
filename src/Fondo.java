
import java.awt.Graphics2D;
import javax.swing.ImageIcon;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Coso
 */
public class Fondo {
    ImageIcon imagenFondo1;
    ImageIcon imagenFondo2;
    ImageIcon imagenFondo3;
    Cueva cueva;
    public Fondo(Cueva cueva){
        this.cueva = cueva;
    }
    
    public void setCuevaFondo(){
        imagenFondo1 = new ImageIcon(getClass().getResource("Multimedia/cueva/Fondo1.png"));
        imagenFondo2 = new ImageIcon(getClass().getResource("Multimedia/cueva/Fondo2.png"));
        imagenFondo3 = new ImageIcon(getClass().getResource("Multimedia/cueva/piso.png"));
    }
    
    public void dibujar(Graphics2D g){
        g.drawImage(imagenFondo1.getImage(),-10, 0, imagenFondo1.getIconWidth(), imagenFondo1.getIconHeight(), null);
        g.drawImage(imagenFondo2.getImage(),990, 0, imagenFondo2.getIconWidth(), imagenFondo2.getIconHeight(), null);
        g.drawImage(imagenFondo3.getImage(),0, 70, imagenFondo3.getIconWidth(), imagenFondo3.getIconHeight(), null);
    }
    
}
