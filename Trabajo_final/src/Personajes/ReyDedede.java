package Personajes;
/**
 * Como en todas las subclases de personaje se define el nombre, sus tres estadísticas y el identifier que sera su posición en la cola para atacar
 * Tambien se define lo que hara la habilidad
 */
public class ReyDedede extends Personaje{
    /**
     * Constructor del personaje definiendo su nombre y sus estadísticas
     */
    public ReyDedede(){
        name="Rey Dedede";
        stats.PV=200;
        stats.PH=100;
        stats.ataque=40;
        identifier=2;
    }

    /**
     * La habilidad de Dedede es realizar un Ataque con el mayor daño posible por eso priemro se realiza la función de ataque y entonces ese daño
     * se multiplica por 6 y se le resta a la vida del objetivo
     * @param objective el que va arecivir el ataque
     * @return devuelve el daño total
     */
    @Override
    public int hability(Personaje objective) {
        int damage=Ataco(objective);
        objective.stats.PV-=damage*6;
        return damage*6;
    }
}
