package Personajes;
/**
 * Como en todas las subclases de personaje se define el nombre, sus tres estadísticas y el identifier que sera su posición en la cola para atacar
 * Tambien se define lo que hara la habilidad
 */
public class Kirby extends Personaje{
    /**
     * Constructor del personaje definiendo su nombre y sus estadísticas
     */
    public Kirby(){
        name="Kirby";
        stats.PV=200;
        stats.PH=100;
        stats.ataque=40;
        identifier=0;
    }

    /**
     * La habilidad de kirby hace que la defensa del objetivo siempre se desactive para que así todos le puedan hacer mas daño
     * @param objective
     * @return nada ya que no hace falta que devuelva
     */
    @Override
    public int hability(Personaje objective) {
        objective.defend=false;
        return 0;
    }
}
