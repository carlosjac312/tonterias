package Personajes;
/**
 * Como en todas las subclases de personaje se define el nombre, sus tres estadísticas y el identifier que sera su posición en la cola para atacar
 * Tambien se define lo que hara la habilidad
 */
public class BandanaWadledee extends Personaje{
    /**
     * Constructor del personaje definiendo su nombre y sus estadísticas
     */
    public BandanaWadledee(){
        name="Bandana Waddle dee";
        stats.PV=200;
        stats.PH=100;
        stats.ataque=40;
        identifier=3;
    }

    /**
     * La habilidad de Bandana consiste en robarle PH al objetivo para que asi este no pueda realizar su habilidad
     * @param objective Personaje al que le quitara los PH
     * @return devuelve la cantidad de PH que roba
     */
    @Override
    public int hability(Personaje objective) {
        objective.stats.PH-=25;
        return 25;
    }
}
