package Personajes;

/**
 * Como en todas las subclases de personaje se define el nombre, sus tres estadísticas y el identifier que sera su posición en la cola para atacar
 * Tambien se define lo que hara la habilidad
 */
public class Enemy extends Personaje{
    /**
     * Constructor del personaje definiendo su nombre y sus estadísticas
     */
    public Enemy(){
        name="Goku malvado";
        stats.PV=1000;
        stats.PH=100;
        stats.ataque=400;
        identifier=4;
    }

    /**
     * La habilidad del enemigo le quita la mitad de los PVs actuales a su objetivo
     * @param objective Personaje al que le quitara los PVs
     * @return PVs restantes del objetivo
     */
    @Override
    public int hability(Personaje objective) {
        objective.stats.PV/=2;
        return objective.stats.PV;
    }
}
