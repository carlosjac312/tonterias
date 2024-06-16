package GameManagement;

import Interfaz.Game;
import Interfaz.Window;
import Personajes.Personaje;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * La clase turnManager se encarda de llevar el control de a que personaje le toca realizar su acción
 * Aun no está terminada
 */
public class TurnManager {
    private Game juego;
    public boolean pressed=false;
    private Window ventana;
    /**
     * El contructor de la clase
     * @param a usa este parametro para indicar el Game con el que trabajara
     * @param b usa este parametro para indicar el Window con el que trabajara
     */
    public TurnManager(Game a, Window b){juego=a;ventana=b;}

    /**
     * Esta funcion es creada para indicar que accion tomara el personaje
     * Tiene para elegir entre atacar defender habilidad o revivivir
     * En cada uno de los botones se realizara la funcion indicada y se modificaran las partes de la interfaz que cambien
     * @param your es el personaje que realizara la accion
     * La funcion aun no esta terminada
     */
    public void yourTurn(Personaje your){
        if (your.stats.PV<=0) ventana.narrationArea.setText(your.getname()+" está inconsciente");//Si el personaje esta muerto no hara nada solo esto
        else {
            ventana.atack.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(pressed==false) {
                        pressed=true;
                        int a=your.Ataco(juego.turnos.get(4));
                        ventana.narrationArea.setText(your.getname()+" ha hecho "+a+" daño");
                    }
                }
            });
            ventana.defend.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(pressed==false) {
                        pressed=true;
                        your.Defiendo();
                        ventana.narrationArea.setText(your.getname()+" se pone a la defensiva");
                    }
                }
            });
            ventana.hability.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(pressed==false) {
                        pressed=true;
                        if (your.stats.PH<=0){
                            your.stats.PH+=75;
                            ventana.narrationArea.setText(your.getname()+" no tiene PHs para realizar la habilidad asi que descansa y recupera 75 PHs");
                            ventana.statusPersonajes.get(your.getIdentifier()).setText(juego.turnos.get(your.getIdentifier()).getname()+"\n\nPV: "+juego.turnos.get(your.getIdentifier()).stats.PV+"\nPH: "+juego.turnos.get(your.getIdentifier()).stats.PH);
                        }
                        else {
                            int a=your.hability(juego.turnos.get(4));
                            ventana.narrationArea.setText(your.getname()+" ha usado su habilidad ");
                            your.stats.PH-=25;
                            switch (your.getname()){
                                case "Kirby":
                                    ventana.narrationArea.append("rompiendo la defensa del enemigo");
                                    ventana.statusPersonajes.get(0).setText(juego.turnos.get(0).getname()+"\n\nPV: "+juego.turnos.get(0).stats.PV+"\nPH: "+juego.turnos.get(0).stats.PH);
                                    break;
                                case "Bandana Waddle dee":
                                    ventana.narrationArea.append("quitandole al enemigo "+a+" puntos de habilidad");
                                    ventana.statusPersonajes.get(3).setText(juego.turnos.get(3).getname()+"\n\nPV: "+juego.turnos.get(3).stats.PV+"\nPH: "+juego.turnos.get(3).stats.PH);
                                    break;
                                case "MetaKnight":
                                    ventana.narrationArea.append("golpeando varias veces al enemigo haciendole "+a+" puntos de daño");
                                    ventana.statusPersonajes.get(1).setText(juego.turnos.get(1).getname()+"\n\nPV: "+juego.turnos.get(1).stats.PV+"\nPH: "+juego.turnos.get(1).stats.PH);
                                    break;
                                case "Rey Dedede":
                                    ventana.narrationArea.append("golpeando con mucha fuerza al enemigo haciendole "+a+" puntos de daño");
                                    ventana.statusPersonajes.get(2).setText(juego.turnos.get(2).getname()+"\n\nPV: "+juego.turnos.get(2).stats.PV+"\nPH: "+juego.turnos.get(2).stats.PH);
                                    break;
                            }
                        }
                    }
                }
            });
            ventana.revive.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(pressed==false) {
                        pressed=true;
                        boolean found=false;
                        for (int i=0; i<juego.turnos.size()-1; i++){
                            if (juego.turnos.get(i).stats.PV<=0){
                                juego.turnos.get(i).stats.PV=100;
                                ventana.narrationArea.setText(your.getname()+" ha revivido ha "+juego.turnos.get(i).getname());
                                found=true;
                                ventana.statusPersonajes.get(i).setText(juego.turnos.get(i).getname()+"\n\nPV: "+juego.turnos.get(i).stats.PV+"\nPH: "+juego.turnos.get(i).stats.PH);
                                break;
                            }
                        }
                        if (found==false){
                            your.stats.PV+=50;
                            your.stats.PH+=25;
                            ventana.statusPersonajes.get(your.getIdentifier()).setText(juego.turnos.get(your.getIdentifier()).getname()+"\n\nPV: "+juego.turnos.get(your.getIdentifier()).stats.PV+"\nPH: "+juego.turnos.get(your.getIdentifier()).stats.PH);
                            ventana.narrationArea.setText("No hay nadie inconsciente asi que "+your.getname()+" descansa recuperando 50 PVs y 25 PHs");
                        }
                    }
                }
            });
        }
    }
}
