
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.ImageIcon;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Coso
 */
public class Player extends Entidad implements KeyListener{
    Cueva cueva;
    ImageIcon personaje;
    String direccion = "";
    boolean derecha = false;
    boolean izquierda = false;
    Graphics2D grafico;
    int x_inicial = 400;
    int y_inicial = 250;
    int x_auxiliar;
    int y_auxiliar;
    int personajeframe = 0;
    int movimientoFrame = 0;
    boolean miraDerecha = true;
    boolean miraIzquierda = false;
    boolean golpe = true;
    boolean golpeando = false;
    boolean salto = false;
    boolean sube = false;
    boolean baja = false;
    int ancho = 330;
    int alto = 240;
    boolean daño = false;
    ImageIcon barraVida;
    ImageIcon vidarestante;
    int vidaenBarra;
    int puntuacion = 0;
    
    public Player(Cueva cueva){
        this.cueva = cueva;
        vida = 100;
        personaje = new ImageIcon(getClass().getResource("Multimedia/personaje/personaje.png"));
        colisionXCuerpo = (x_inicial + 100);
        colisionYCuerpo = (y_inicial + 100);
        colisionWidth = 110;
        colisionHeight = 110;
        ColisionX = x_inicial + 20;
        ColisionY = y_inicial;
        colisionWidthAtack = 120;
        colisionHeightAtack = 200;
        limiteDerecho = colisionXCuerpo + colisionWidth; // es la x mas su anchura
        limiteIzquierdo = colisionXCuerpo; // es la x
        limiteSuperior = colisionYCuerpo; // es la y
        limiteInferior = colisionYCuerpo + colisionHeight; // es la y mas su altura
        limiteDerechoAtaque = ColisionX + colisionWidthAtack; // es la x mas su anchura
        limiteIzquierdoAtaque = ColisionX; // es la x
        limiteSuperiorAtaque = ColisionY; // es la y
        limiteInferiorAtaque = ColisionY + colisionHeightAtack; // es la y mas su altura
        vidaenBarra = vida * 3; 
    }
    
    @Override
    public void keyPressed(KeyEvent e){
        switch(e.getKeyCode()){
            case (KeyEvent.VK_E):
                if (golpe == true && salto == false){
                    golpe = false;
                    golpeando = true;
                }
                break;
            case (KeyEvent.VK_D):
                miraIzquierda = false;
                miraDerecha = true;
                derecha = true;
                izquierda = false;
                break;
            case (KeyEvent.VK_A):
                miraDerecha = false;
                miraIzquierda = true;
                derecha = false;
                izquierda = true;
                break;
            case (KeyEvent.VK_SPACE):
                if (golpeando == false) {
                    salto = true;
                }
                break;
            default:
                derecha = false;
                izquierda = false;
        }
    }

    @Override
    public void keyReleased(KeyEvent e){
        switch(e.getKeyCode()){
            case (KeyEvent.VK_D):
                derecha = false;
                movimientoFrame = 0;
                break;
            case (KeyEvent.VK_A):
                izquierda = false;
                movimientoFrame = 0;
                break;
        }
    }

    @Override
    public void keyTyped(KeyEvent e){
        
    }
    public void vida(){
        barraVida = new ImageIcon(getClass().getResource("Multimedia/personaje/barravida.png"));
        vidarestante = new ImageIcon(getClass().getResource("Multimedia/personaje/vida.png"));
        if (vidaenBarra > (vida * 3)) {
            vidaenBarra -= 1;
        }
        if (vidaenBarra < (vida * 3)) {
            vidaenBarra += 1;
        }
        if (vida > 100) {
            vida = 100;
        }
        if (vida <= 0) {
            vida = 0;
        }
    }
    
    public void dibujar(Graphics2D grafico){
        if (derecha == false && izquierda == false && golpeando == false && salto == false && miraIzquierda == false) { //reiniciar el personaje 
            direccion = "Multimedia/personaje/personaje.png";
        }
        if (derecha == false && izquierda == false && golpeando == false && salto == false && miraDerecha == false) {
            direccion = "Multimedia/personaje/personajeFlip.png";
        }
        personaje = new ImageIcon(getClass().getResource(direccion));
        salto();
        golpe();
        movimiento();
        vida();
        //grafico.drawRect(colisionXCuerpo, colisionYCuerpo, colisionWidth, colisionHeight);
        //grafico.drawRect(ColisionX, ColisionY, colisionWidthAtack ,colisionHeightAtack);
        grafico.drawImage(personaje.getImage(),x_inicial, y_inicial, ancho, alto, null);
        grafico.drawImage(vidarestante.getImage(), -20, 0, vidaenBarra, vidarestante.getIconHeight(), null);
        grafico.drawImage(barraVida.getImage(),-20, 0, barraVida.getIconWidth(), barraVida.getIconHeight(), null);
    }
    //colisiones
    
    //acciones
    public void golpe() {
        if (personajeframe >= 7 && salto == false){
            personajeframe = 0;
            golpe = true;
            golpeando = false;
        }
        if (golpeando == true && salto == false) {
            movimientoFrame++;
            if (movimientoFrame % 6 == 0) {
                if (miraDerecha == true) {
                    direccion = "Multimedia/personaje/ataque1/tile00"+ personajeframe + ".png";
                    ColisionX = x_inicial + 160;
                }
                if (miraIzquierda == true) {
                    direccion = "Multimedia/personaje/ataque1/tileFlip00"+ personajeframe + ".png";
                    ColisionX = x_inicial + 40;
                }
                if ("Multimedia/personaje/ataque1/tile004.png".equals(direccion) || "Multimedia/personaje/ataque1/tileFlip004.png".equals(direccion)) {
                    daño = true;
                }
                if ("Multimedia/personaje/ataque1/tile006.png".equals(direccion) || "Multimedia/personaje/ataque1/tileFlip006.png".equals(direccion)) {
                    daño = false;
                }
                personajeframe++;
            }
        }
    }

    public void movimiento() {
        ColisionY = y_inicial;
        if (golpeando == true)
            return;
        if (personajeframe >= 5)
            personajeframe = 0;
        if (derecha == true && x_inicial < 750) {
            if (movimientoFrame % 10 == 0 && salto == false || movimientoFrame == 0) {
                direccion = "Multimedia/personaje/caminar/tile00"+ personajeframe +".png";
                personajeframe++;   
            }
            ColisionX = x_inicial + 160;
            x_inicial += 4;
            movimientoFrame++;
        }
        if (izquierda == true && x_inicial > - 90) {
            if (movimientoFrame % 10 == 0 && salto == false || movimientoFrame == 0) {
                direccion = "Multimedia/personaje/caminar/tileFlip00"+ personajeframe +".png";
                personajeframe++;
            }
            ColisionX = x_inicial + 20;
            x_inicial -= 4;
            movimientoFrame++;
        }
        colisionXCuerpo = (x_inicial + 100);
        colisionYCuerpo = (y_inicial + 100);
        limiteDerecho = colisionXCuerpo + colisionWidth; // es la x mas su anchura
        limiteIzquierdo = colisionXCuerpo; // es la x
        limiteSuperior = colisionYCuerpo; // es la y
        limiteInferior = colisionYCuerpo + colisionHeight; // es la y mas su altura
        limiteDerechoAtaque = ColisionX + colisionWidthAtack; // es la x mas su anchura
        limiteIzquierdoAtaque = ColisionX; // es la x
        limiteSuperiorAtaque = ColisionY; // es la y
        limiteInferiorAtaque = ColisionY + colisionHeightAtack; // es la y mas su altura
    }

    public void salto() {
        if (personajeframe >= 3 && golpeando == false)
            personajeframe = 0;
        //84 altura maxima en y
        //250 altura minima en y
        if (salto == true && golpeando == false) {
            if (y_inicial >= 250) { //si esta en el suelo
                sube = true;
                y_auxiliar =-8;
                baja = false;
            }
            if (y_inicial <= 84) { //si llego al limite del salto
                baja = true;
                y_auxiliar = 8;
                sube = false;
            }
            if (sube == true) {
                y_inicial +=y_auxiliar;
                if (miraDerecha == true) {
                    direccion = "Multimedia/personaje/salto/tile00"+ personajeframe +".png";
                }
                if (miraIzquierda == true) {
                    direccion = "Multimedia/personaje/salto/tileFlip00"+ personajeframe +".png";
                }
                personajeframe++;
            }
            if (baja == true) {
                y_inicial+=y_auxiliar;
                if (miraDerecha == true) {
                    direccion = "Multimedia/personaje/salto/bajar/tile00"+ personajeframe +".png";
                }
                if (miraIzquierda == true) {
                    direccion = "Multimedia/personaje/salto/bajar/tileFlip00"+ personajeframe +".png";
                }
                personajeframe++;
                if (y_inicial == 250) { //si llego al suelo
                    salto = false;
                }
            }
        }
    }
    
    
    
    
    
}
