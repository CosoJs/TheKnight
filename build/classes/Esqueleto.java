
import java.awt.Graphics2D;
import javax.swing.ImageIcon;
import java.util.Random;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Coso
 */

public class Esqueleto extends Entidad{
    Cueva cueva;
    ImageIcon personaje;
    int x_inicial; //punto de reaparicion recordar
    //980 -> reaparicion en la derecha
    //-20 -> reaparicion en la izquierda
    int y_inicial = 290;
    int x_auxiliar;
    int y_auxiliar;
    int ancho = 96;
    int alto = 192;
    boolean caminando = true;
    boolean atacando = false;
    int frame = 0;
    int framegolpes = 0;
    int movimientoframe;
    String direccion = "Multimedia/esqueleto/esqueleto.png";
    boolean reaparicionderecha = false;
    boolean reaparicionizquierda = false;
    int xJugador;
    boolean enrango = false;
    Player jugador;
    boolean daño = false;
    boolean dañorecibido = false;
    boolean reiniciomuerte = false;
    
    
    
    public Esqueleto(Cueva cueva, Player jugador){
        this.cueva = cueva;
        vida = 100;
        colisionXCuerpo = (x_inicial);
        colisionYCuerpo = (y_inicial + 80);
        colisionWidth = 110;
        colisionHeight = 110;
        ColisionX = x_inicial + 20;
        ColisionY = y_inicial;
        colisionWidthAtack = 100;
        colisionHeightAtack = 400;
        limiteDerecho = colisionXCuerpo + colisionWidth; // es la x mas su anchura
        limiteIzquierdo = colisionXCuerpo; // es la x
        limiteSuperior = colisionYCuerpo; // es la y
        limiteInferior = colisionYCuerpo + colisionHeight; // es la y mas su altura
        limiteDerechoAtaque = ColisionX + colisionWidthAtack; // es la x mas su anchura
        limiteIzquierdoAtaque = ColisionX; // es la x
        limiteSuperiorAtaque = ColisionY; // es la y
        limiteInferiorAtaque = ColisionY + colisionHeightAtack; // es la y mas su altura
        this.jugador = jugador;
        reaparicion();
        movimientoframe = 0;
    }
    
    private void reaparicion(){
        vida = 100;
        //980 -> reaparicion en la derecha
        //-20 -> reaparicion en la izquierda
        int[] valores = {980, -20, 990, -40, 1000, -50, 1010, -30}; // Array de los dos valores
        Random random = new Random();
        int indiceAleatorio = random.nextInt(valores.length);
        int valorElegido = valores[indiceAleatorio]; 
        if (valorElegido >= 980) {
            x_inicial = valorElegido;
            reaparicionderecha = true;
        }
        if (valorElegido <= -20) {
            x_inicial = valorElegido;
            reaparicionizquierda = true;
        }
    }
    
    public void dibujar(Graphics2D grafico){
        if (reiniciomuerte == false && caminando == false && atacando == false && dañorecibido == false) {
            direccion = "Multimedia/esqueleto/esqueleto.png";
        }
        personaje = new ImageIcon(getClass().getResource(direccion));
        atacar();
        caminar();
        daño();
        danorecibido();
        muerte();
        //grafico.drawRect(colisionXCuerpo, colisionYCuerpo, colisionWidth, colisionHeight);
        //grafico.drawRect(ColisionX, ColisionY, colisionWidthAtack ,colisionHeightAtack);
        grafico.drawImage(personaje.getImage(),x_inicial, y_inicial, ancho, alto, null);
    }
    
    public void caminar(){
        xJugador = jugador.x_inicial;
        /*derecha es negativo
        izquierda es positivo
        si es menor debe ir a la izquierda
        si es mayor debe ir a la derecha
         */
        if (frame >= 6 && dañorecibido == false)
            frame = 0;
        if (reiniciomuerte == false && dañorecibido == false && reaparicionizquierda == true && reaparicionderecha == false && x_inicial < xJugador + 60) { //movimiento a la derecha
            atacando = false;
            enrango = false;
            caminando = true;
            x_inicial +=2;
            movimientoframe++;
            if (movimientoframe % 8 == 0) {
                direccion = "Multimedia/esqueleto/caminar/tile00"+ frame +".png";
                frame++;
            }
            framegolpes = 0;
        }
        else if (reiniciomuerte == false && dañorecibido == false && reaparicionderecha == true && reaparicionizquierda == false && x_inicial > xJugador + 170) {
            atacando = false;
            enrango = false;
            caminando = true;
            x_inicial -=2;
            movimientoframe++;
            if (movimientoframe % 8 == 0) {
                direccion = "Multimedia/esqueleto/caminar/tile00"+ frame +"Flip.png";
                frame++;
            }
            framegolpes = 0;
        }else{
            enrango = true;
            caminando = false;
        }
        colisionXCuerpo = (x_inicial);
        colisionYCuerpo = (y_inicial + 80);
    }
    
    public void atacar(){
        if (framegolpes >= 9 && caminando == false && dañorecibido == false)
            framegolpes = 0;
        if (reiniciomuerte == false && dañorecibido == false && enrango == true && caminando == false) {
            atacando = true;
            movimientoframe++;
            if (reaparicionderecha) {
                ColisionX = x_inicial - 70;
                if (movimientoframe % 10 == 0) {
                direccion = "Multimedia/esqueleto/golpear/tile00" + framegolpes + "Flip.png";
                framegolpes++;
                    if ("Multimedia/esqueleto/golpear/tile003Flip.png".equals(direccion) || "Multimedia/esqueleto/golpear/tile008Flip.png".equals(direccion)) {
                        daño = true;
                        daño();
                    }
                }
                if (x_inicial < xJugador + 60) {
                    reaparicionderecha = false;
                    reaparicionizquierda = true;
                    atacando = false;
                }
            }
            if (reaparicionizquierda ) {
                ColisionX = x_inicial + 20;
                if (movimientoframe % 10 == 0) {
                direccion = "Multimedia/esqueleto/golpear/tile00" + framegolpes + ".png";
                framegolpes++;
                    if ("Multimedia/esqueleto/golpear/tile003.png".equals(direccion) || "Multimedia/esqueleto/golpear/tile008.png".equals(direccion)) {
                        daño = true;
                        daño();
                    }
                }
                if (x_inicial > xJugador + 170) {
                    reaparicionderecha = true;
                    reaparicionizquierda = false;
                    atacando = false;
                }
            }
            daño = false;
            ColisionY = y_inicial;
        }
        else{
            atacando = false;
        }
    }
    
    public void daño(){
        limiteDerecho = colisionXCuerpo + colisionWidth; // es la x mas su anchura
        limiteIzquierdo = colisionXCuerpo; // es la x
        limiteSuperior = colisionYCuerpo; // es la y
        limiteInferior = colisionYCuerpo + colisionHeight; // es la y mas su altura
        limiteDerechoAtaque = ColisionX + colisionWidthAtack; // es la x mas su anchura
        limiteIzquierdoAtaque = ColisionX; // es la x
        limiteSuperiorAtaque = ColisionY; // es la y
        limiteInferiorAtaque = ColisionY + colisionHeightAtack; // es la y mas su altura
        if (reiniciomuerte == false && daño == true && limiteDerechoAtaque >= jugador.limiteIzquierdo && limiteIzquierdoAtaque <= jugador.limiteDerecho && limiteInferiorAtaque >= jugador.limiteSuperior && limiteSuperiorAtaque <= jugador.limiteInferior) {
            jugador.vida -=10;
            System.out.println(jugador.vida);
        }
        if (reiniciomuerte == false && jugador.daño == true && jugador.limiteDerechoAtaque >= limiteIzquierdo && jugador.limiteIzquierdoAtaque <= limiteDerecho && jugador.limiteInferiorAtaque >= limiteSuperior && jugador.limiteSuperiorAtaque <= limiteInferior) {
            if (vida <= 0) {
                jugador.vida += 5;
                reiniciomuerte = true;
            }
            else{
                atacando = false;
                caminando = false;
                jugador.daño = false;
                daño = false;
                vida -= 50;
                dañorecibido = true;
            }
        }
    }
    
    public void danorecibido(){
        if (framegolpes >= 4 && caminando == false && atacando == false)
            framegolpes = 0;
        if (dañorecibido == true && atacando == false && caminando == false) {
            movimientoframe++;
            if (reaparicionderecha) {
                if (movimientoframe % 10 == 0) {
                direccion = "Multimedia/esqueleto/danoRecibido/tile00" + framegolpes + "Flip.png";
                framegolpes++;
                }
                x_inicial += 1;
            }
            if (reaparicionizquierda) {
                ColisionX = x_inicial + 20;
                if (movimientoframe % 10 == 0) {
                direccion = "Multimedia/esqueleto/danoRecibido/tile00" + framegolpes + ".png";
                framegolpes++;
                }
                x_inicial -= 1;
            }
        }
        if (dañorecibido == true && framegolpes >= 4 || "Multimedia/esqueleto/danoRecibido/tile004Flip.png".equals(direccion) || "Multimedia/esqueleto/danoRecibido/tile004.png".equals(direccion)) {
                dañorecibido = false;
        }
    }
    public void muerte(){
        if (framegolpes >= 2 && caminando == false && atacando == false && dañorecibido == false)
            framegolpes = 0;
        if (reiniciomuerte == true && dañorecibido == false && atacando == false && caminando == false) {
            movimientoframe++;
            if (reaparicionderecha) {
                if (movimientoframe % 10 == 0) {
                direccion = "Multimedia/esqueleto/danoRecibido/Muerte" + framegolpes + "Flip.png";
                framegolpes++;
                }
            }
            if (reaparicionizquierda) {
                ColisionX = x_inicial + 20;
                if (movimientoframe % 10 == 0) {
                direccion = "Multimedia/esqueleto/danoRecibido/Muerte" + framegolpes + ".png";
                framegolpes++;
                }
            }
        }
        if (reiniciomuerte == true && framegolpes >= 2 || "Multimedia/esqueleto/danoRecibido/Muerte2Flip.png".equals(direccion) || "Multimedia/esqueleto/danoRecibido/Muerte2.png".equals(direccion)) {
            reaparicion();
            reiniciomuerte = false;
        }
    }
    
}
