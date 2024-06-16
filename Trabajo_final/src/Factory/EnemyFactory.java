package Factory;

import Personajes.Enemy;
import Personajes.Personaje;

/**
 * Esta es la factoria de Metaknights
 */
public class EnemyFactory implements creator{
    /**
     * Esto crea el personaje
     * @return devuelve el personaje creado
     */
    @Override
    public Personaje createPersonaje() {
        return new Enemy();
    }
}
