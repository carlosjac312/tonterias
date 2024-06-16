package Personajes;
/**
 * Como en todas las subclases de personaje se define el nombre, sus tres estadísticas y el identifier que sera su posición en la cola para atacar
 * Tambien se define lo que hara la habilidad
 */
public class MetaKnight extends Personaje{
    /**
     * Constructor del personaje definiendo su nombre y sus estadísticas
     */
    public MetaKnight(){
        name="MetaKnight";
        stats.PV=200;
        stats.PH=100;
        stats.ataque=40;
        identifier=1;
    }

    /**
     *La habilidad de Metanight genera un numero aleatorio entre 2 y 8 este numero sera el numero de veces que atacara al objetivo consecutivamente en ese turno
     * @param objective al que le va a atacar
     * @return devuelve el daño total que hara
     */
    @Override
    public int hability(Personaje objective) {
        int hits = (int)(Math.random()*8+2);
        int daño=0;
        for (int i=0; i<hits; i++){
            daño+=Ataco(objective);
        }
        return daño;
    }
}
