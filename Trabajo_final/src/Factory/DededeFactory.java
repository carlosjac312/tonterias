package Factory;

import Personajes.Personaje;
import Personajes.ReyDedede;

/**
 * Esta es la factoria de Metaknights
 */
public class DededeFactory implements creator{
    /**
     * Esto crea el personaje
     * @return devuelve el personaje creado
     */
    @Override
    public Personaje createPersonaje() {
        return new ReyDedede();
    }
}
