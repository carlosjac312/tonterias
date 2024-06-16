package Factory;

import Personajes.Kirby;
import Personajes.Personaje;

/**
 * Esta es la factoria de Metaknights
 */
public class KirbyFactory implements creator {
    /**
     * Esto crea el personaje
     * @return devuelve el personaje creado
     */
    @Override
    public Personaje createPersonaje() {
        return new Kirby();
    }
}
