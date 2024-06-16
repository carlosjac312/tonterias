package Factory;

import Personajes.MetaKnight;
import Personajes.Personaje;

/**
 * Esta es la factoria de Metaknights
 */
public class MetaknightFactory implements creator{
    /**
     * Esto crea el personaje
     * @return devuelve el personaje creado
     */
    @Override
    public Personaje createPersonaje() {
        return new MetaKnight();
    }
}
