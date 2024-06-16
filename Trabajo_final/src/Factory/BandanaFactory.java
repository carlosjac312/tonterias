package Factory;

import Personajes.BandanaWadledee;
import Personajes.Personaje;

/**
 * Esta es la factoria de Metaknights
 */
public class BandanaFactory implements creator{
    /**
     * Esto crea el personaje
     * @return devuelve el personaje creado
     */
    @Override
    public Personaje createPersonaje() {
        return new BandanaWadledee();
    }
}
