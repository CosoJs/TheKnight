/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import java.io.*;
import javax.sound.sampled.*;
/**
 *
 * @author Coso
 */
public class Musica {
    private String Musica = "";
    private Clip clip;
    private boolean Reproducir;
    private File musicPath = new File(Musica);
    public Musica (){
        this.Reproducir = true;
    }
    
    
    public void MenuPrincipal(){
        Musica = "src/Multimedia/Musica/MenuPrincipal.wav";
        try{
            musicPath = new File(Musica);
            if (musicPath.exists()) {
                AudioInputStream introducirMusica = AudioSystem.getAudioInputStream(musicPath);
                clip = AudioSystem.getClip();
                clip.open(introducirMusica);
                clip.addLineListener(new LineListener(){
                @Override
                public void update(LineEvent event){
                    if (event.getType() == LineEvent.Type.STOP && (Reproducir == true)) {
                        clip.setFramePosition(0);
                        clip.start();
                    }
                }
            });
                clip.start();
            }
            else
                System.out.println("No file found / Archivo no encontrado");
        }catch(IOException | LineUnavailableException | UnsupportedAudioFileException e){
            System.out.println(e);
        }
    }
    
    public void Cueva(){
        Musica = "src/Multimedia/Musica/MyVeryOwnDeadShip.wav";
        try{
            musicPath = new File (Musica);
            if (musicPath.exists()) {
                AudioInputStream introducirMusica = AudioSystem.getAudioInputStream(musicPath);
                clip = AudioSystem.getClip();
                clip.open(introducirMusica);
                clip.addLineListener(new LineListener(){
                    @Override
                    public void update(LineEvent event){
                        if (event.getType() == LineEvent.Type.STOP && (Reproducir == true)) {
                            clip.setFramePosition(0);
                            clip.start();
                        }
                    }
                });
                clip.start();
            }
        }
        catch(IOException | LineUnavailableException | UnsupportedAudioFileException e){
            System.out.println(e);
        }
    }
    
    public void PararOcontinuar(){
        Reproducir = !Reproducir;
        if (Reproducir == false) {
            clip.stop();
        }
        else{
            clip.start();
        }           
    }
    public void Reinicio(){
        clip.close();
    }
}
