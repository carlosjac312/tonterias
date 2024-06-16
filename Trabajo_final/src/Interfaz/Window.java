package Interfaz;

import CargarImagenes.ImageLoader;
import GameManagement.ScreenManager;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * La clase Window genera la interfaz principal que se usara en el juego
 */
public class Window {
    private JFrame ventana;
    public JPanel enemyPanel, buttonsPanel, statusPanel;
    public JButton atack, defend, hability, revive;
    public JTextArea stKirby, stMetal, stDedede, stBandana, narrationArea, gameOverText;
    public ArrayList<JTextArea> statusPersonajes;
    private Font standarLetras = new Font("Times new Roman", Font.PLAIN, 26);
    private Font bigLetras = new Font("Times new Roman", Font.PLAIN, 80);
    private ScreenManager restart = new ScreenManager(this);
    private JLabel enemigo;

    /**
     * El constructor de la clase que genera la interfaz entera
     * Añadir que me estoy intentando recrear una  Interfaz parecida  la de lejuego Eartbound aqui dejo un enlace con un ejemplo https://youtu.be/nAmBIljqgR8
     */
    public Window(){
        ventana =new JFrame();
        ventana.setTitle("RPG Combat");//Titulo de la ventana
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//Para cerrar la ventana
        ventana.getContentPane().setBackground(Color.BLACK);
        ventana.setBounds(170,50, 1280,720);
        ventana.setLayout(null);

        enemyPanel = createPanel(0,120,1280,380);
        enemyPanel.setBackground(Color.YELLOW);
        ventana.add(enemyPanel);

        BufferedImage goku= ImageLoader.loadImage("/GDripgoodsize.png");
        enemigo=new JLabel(new ImageIcon(goku));
        enemigo.setLocation(180,0);
        enemyPanel.add(enemigo);

        buttonsPanel =createPanel(0,0,1280,120);
        buttonsPanel.setLayout(new GridLayout(1,5));//Organiza el Panel en este caso se han puesto 1 fila y 4 columnas
        ventana.add(buttonsPanel);

        atack = createButton("Atacar");
        buttonsPanel.add(atack);

        defend = createButton("Defenderse");
        buttonsPanel.add(defend);

        hability = createButton("Habilidad");
        buttonsPanel.add(hability);

        revive = createButton("Revivir");
        buttonsPanel.add(revive);



        narrationArea = createTextarea();
        narrationArea.setBounds(0,0,380,20);
        enemyPanel.add(narrationArea);

        statusPanel =createPanel(0,500,1280,220);
        statusPanel.setLayout(new GridLayout(1,4));
        ventana.add(statusPanel);

        stKirby = createTextarea();
        statusPanel.add(stKirby);

        stMetal = createTextarea();
        statusPanel.add(stMetal);

        stDedede = createTextarea();
        statusPanel.add(stDedede);

        stBandana = createTextarea();
        statusPanel.add(stBandana);

        statusadder();

        gameOverText = new JTextArea("Game Over");
        gameOverText.setSize(1280,270);
        gameOverText.setBackground(Color.black);
        gameOverText.setForeground(Color.WHITE);
        gameOverText.setFont(bigLetras);
        enemyPanel.add(gameOverText);

        ventana.setVisible(true);
    }

    /**
     * Esta función crea un panel
     * @param posx este parametro es para saber su posición en el eje de las x
     * @param posy este parametro es para saber su posición en el eje de las y
     *             Los parametros ancho y largo es solo para ajustar el tamaño del Panel
     * @param ancho
     * @param largo
     * @return por ultimo devuelve el Panel creado
     */
    public JPanel createPanel(int posx, int posy, int ancho, int largo){
        JPanel a=new JPanel();
        a.setBackground(Color.black);
        a.setBounds(posx,posy,ancho,largo);
        return a;
    }

    /**
     * Esta función crea un boton
     * @param nombre Esto es solo para poner el texto que tendra el boton al paracer por pantalla
     * @return devuelve el boton creado
     */
    public JButton createButton(String nombre){
        JButton a=new JButton(nombre);
        a.setBackground(Color.black);
        a.setForeground(Color.white);
        a.setFont(standarLetras);
        a.setFocusPainted(false);
        return a;
    }

    /**
     * Igual que las demas funciones create esta cre una TextArea
     * @return devuelve siempre la TextArea creada
     */
    public JTextArea createTextarea(){
        JTextArea a=new JTextArea();
        a.setBackground(Color.black);
        a.setForeground(Color.WHITE);
        a.setFont(standarLetras);
        a.setLineWrap(true);
        return a;
    }

    /**
     * statusadder guarda todas las TextArea que muestran el estado de cadas personaje para que luego en la Clase game dentro de run cuando sea el turno
     * de un personaje en especifico si TextArea se ponga de color rojo
     */
    public void statusadder(){
        statusPersonajes=new ArrayList<JTextArea>();
        statusPersonajes.add(stKirby);
        statusPersonajes.add(stDedede);
        statusPersonajes.add(stMetal);
        statusPersonajes.add(stBandana);
    }
    public JFrame getVentana() {
        return ventana;
    }
    public JLabel getEnemigo(){return enemigo;}
}
