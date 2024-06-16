package GameManagement;

import CargarImagenes.ImageLoader;
import Interfaz.Window;

import javax.swing.*;
import java.awt.image.BufferedImage;

/**
 * La clase screenManager la uso para un mejor control de la Interfaz o en este caso de la clase Window
 */
public class ScreenManager {
    private Window vent;

    /**
     * El contructor que sirve para indicar a que Window a de aplicar sus funciones
     * @param a
     */
    public ScreenManager(Window a){
        vent=a;
    }

    /**
     * Devuelve la interfaza su estado de inicio
     */
    public void InitState(){
        vent.statusPanel.setVisible(true);
        vent.enemyPanel.setVisible(true);
        vent.gameOverText.setVisible(false);
        vent.buttonsPanel.setVisible(true);
    }

    /**
     * Pone la interfaz en el estado de fin dejuego
     */
    public void GameOverState(){
        BufferedImage deadgoku= ImageLoader.loadImage("/GokuDead.png");
        vent.statusPanel.setVisible(false);
        vent.gameOverText.setVisible(true);
        vent.buttonsPanel.setVisible(false);
        vent.narrationArea.setVisible(false);
        vent.getEnemigo().setIcon(new ImageIcon(deadgoku));
    }
}
