package GameManagement;

import Interfaz.Game;
import Interfaz.Window;

/**
 * La clase Enemy Actions es la que decide que acción realizara el enemigo mediante el uso de generar numeros aleatorios
 */
public class EnemyActions {
    private Game juego;
    private Window ventana;

    /**
     * Constructor de la clase
     * @param a indica que Game que usara
     * @param b indica que Game que Window usara
     */
    public EnemyActions(Game a,Window b){juego=a;ventana=b;}

    /**
     * Aqui se genera el numero aleatorio el cual mediante un switch se elige que acción realizara y otro numero aleatorio para elegir a quien ataca
     * Tambien hay que decir que la final de cada caso s eactualizan las textArea
     */
    public void Act(){
        int choice = (int)(Math.random()*4+1);
        int objective = (int)(Math.random()*4);
        if (juego.turnos.get(4).stats.PH==0 && choice==3) choice=4; //Este if es por si se intenta realizar la habilidad sin PHs pues que directamente haga la cuarta accion que es recuperar PH
        switch (choice){
            case 1://El caso 1 es el de ataque normal. El while está para que el enemigo no ataque el personajes ya muertos
                while (juego.turnos.get(objective).stats.PV<=0) objective = (int)(Math.random()*4);
                int damage=juego.turnos.get(4).Ataco(juego.turnos.get(objective));
                ventana.statusPersonajes.get(objective).setText(juego.turnos.get(objective).getname()+"\n\nPV: "+juego.turnos.get(objective).stats.PV+"\nPH: "+juego.turnos.get(objective).stats.PH);
                ventana.narrationArea.append("\nEl enemigo ha hecho "+damage+" de daño ha "+juego.turnos.get(objective).getname());
                break;
            case 2://El caso 2 el enemigo activa su funcion de Defiendo
                juego.turnos.get(4).Defiendo();
                ventana.narrationArea.append("\nEl enemigo se pone a la defensiva");
                break;
            case 3://El caso 3 realiza la habilidad del enemigo
                juego.turnos.get(4).hability(juego.turnos.get(objective));
                ventana.statusPersonajes.get(objective).setText(juego.turnos.get(objective).getname()+"\n\nPV: "+juego.turnos.get(objective).stats.PV+"\nPH: "+juego.turnos.get(objective).stats.PH);
                ventana.narrationArea.append("\nEl enemigo le ha quitado la mitad de su vida a "+juego.turnos.get(objective).getname());
                break;
            case 4://El caso 4 el enemigo recupera 75 PH
                juego.turnos.get(4).stats.PH+=50;
                ventana.narrationArea.append("\nEl enemigo medita recuperando 50 PHs");
                break;
        }
    }
}
