package Interfaz;

import GameManagement.EnemyActions;
import GameManagement.ScreenManager;
import GameManagement.TurnManager;
import Personajes.Personaje;
import Factory.PersonajeFactory;
import java.awt.*;
import java.util.ArrayList;

/**
 * La funcion Game es la que controlara todo el juego
 */
public class Game implements Runnable{
    private Thread gameThread, actionThread;
    public Window ventana=new Window();
    public ScreenManager manage;
    public TurnManager order;
    public EnemyActions enemturn;
    public ArrayList<Personaje> turnos;
    private PersonajeFactory factory;
    public int poscharacter;

    /**
     * Constructor de Game en el que se inicializan todos los parametros
     * Inicializa el poscharacter a 0 para empezar con el turno del primer personaje de la cola y el resto simplemente los inicializa
     * indicando la Window y game que usaran
     */
    public Game(){
        poscharacter =0;
        factory =new PersonajeFactory();
        order = new TurnManager(this,ventana);
        manage=new ScreenManager(ventana);
        enemturn=new EnemyActions(this,ventana);
    }

    /**
     * La función gameOver servira para saber si el personaje pierde o gana y para que el loop no para mientras ninguna de las
     * dos opciones anteriores no haya ocurrido
     * @return devuelve si es fin del juego o no
     */
    public boolean gameOver(){
        if (turnos.get(0).stats.PV<0 && turnos.get(1).stats.PV<0 && turnos.get(2).stats.PV<0 && turnos.get(3).stats.PV<0){
            ventana.gameOverText.append("\nHas perdido");
            return true;
        } else if (turnos.get(4).stats.PV<0) {
            ventana.gameOverText.append("\nHas ganado");
            return true;
        } else return false;
    }

    /**
     * La función init inicia prepara el codigo para comenzar el Thread y tambien construye el vector de personajes y el de los cuadros de textos de los estados de cada personaje
     */
    private void init(){
        System.out.println("AAAAAAAAAAAAAAAA");
        factory.PersonajesAdder();
        manage.InitState();
        turnos= factory.party;
        ventana.stKirby.setText(turnos.get(0).getname()+"\n\nPV: "+turnos.get(0).stats.PV+"\nPH: "+turnos.get(0).stats.PH);
        ventana.stDedede.setText(turnos.get(1).getname()+"\n\nPV: "+turnos.get(1).stats.PV+"\nPH: "+turnos.get(1).stats.PH);
        ventana.stMetal.setText(turnos.get(2).getname()+"\n\nPV: "+turnos.get(2).stats.PV+"\nPH: "+turnos.get(2).stats.PH);
        ventana.stBandana.setText(turnos.get(3).getname()+"\n\nPV: "+turnos.get(3).stats.PV+"\nPH: "+turnos.get(3).stats.PH);
        ventana.narrationArea.setText("Turno de "+turnos.get(0).getname());
    }

    /**
     * startGameThread Inicia el Thread inicianizandolo y llamando a la funcion init y start
     */
    public void startGameThread(){
        init();
        gameThread=new Thread(this);
        gameThread.start();
        actionThread=new Thread(this);
    }

    /**
     * La funcion run es el loop constante del codigo la cual dentro de ella tiene el funcionamiento de este
     * Tambien contiene las variables drawInterval, nextDrawtime y remainingTime las cuales mediante la funcion sleep le aplican un retardo al Thread
     */
    @Override
    public void run() {
        double drawInterval = 1000000000/60; //1000000000/60
        double nextDrawTime = System.nanoTime() + drawInterval;
        while (gameThread != null){
            long currentTime = System.nanoTime();
            update();
            order.yourTurn(turnos.get(poscharacter));
            ventana.statusPersonajes.get(poscharacter).setBackground(Color.RED);
            if (turnos.get(poscharacter).stats.PV<=0) ventana.narrationArea.setText(turnos.get(poscharacter).getname()+" está inconsciente");
            if(order.pressed==true){
                if (poscharacter ==3){
                    ventana.statusPersonajes.get(poscharacter).setBackground(Color.BLACK);
                    poscharacter =0;
                    order.pressed=false;
                    enemturn.Act();
                    ventana.narrationArea.append("\nTurno de "+turnos.get(poscharacter).getname());
                }
                else{
                    ventana.statusPersonajes.get(poscharacter).setBackground(Color.BLACK);
                    poscharacter++;
                    ventana.narrationArea.append("\nTurno de "+turnos.get(poscharacter).getname());
                    order.pressed=false;
                }
            }
            if (gameOver()){
                manage.GameOverState();
                gameThread.stop();
            }
            ventana.getVentana().repaint();

            try {
                double remainingTime=nextDrawTime-System.nanoTime();
                remainingTime = remainingTime/1000000;

                if(remainingTime<0){
                    remainingTime=0;
                }

                Thread.sleep((long) remainingTime);
                nextDrawTime += drawInterval;

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * update y paintComponent actualizan la interfaz por cada vuelta de la funcion run
     */
    public void update(){

    }
    public void paintComponent(Graphics g){
        ventana.getVentana().paintComponents(g);
    }
}
