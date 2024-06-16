package Factory;

import Personajes.*;

import java.util.ArrayList;

/**
 *El patrón que estoy implementando en el codigo es el factory
 * Esta clase define a los personajes y la utilizo en la clase BattelLoop
 */
public class PersonajeFactory {
    /**
     *  createPersonaje crea a el personaje indicado usando el parametro de choice
     * @param choice El parametro choice es utilizado como indicador de que tipo de personaje se quiere crear
     * @return La funcion createPersonaje devuelve dependiendo del parametro introducido uno de los personajes disponibles
     */
    public ArrayList<Personaje> party;
    KirbyFactory ckirby;
    MetaknightFactory cmetal;
    DededeFactory cdedede;
    BandanaFactory cbandana;
    EnemyFactory cenemy;
    /**
     * La función PersonajeAdder la cree para que creara el ArrayList de los personajes directamente
     * @return Devuelve el ArrayList ya creado
     */
    public void PersonajesAdder(){
        party=new ArrayList<Personaje>();
        ckirby=new KirbyFactory();
        cmetal=new MetaknightFactory();
        cdedede=new DededeFactory();
        cbandana=new BandanaFactory();
        cenemy=new EnemyFactory();
        party.add(ckirby.createPersonaje());
        party.add(cmetal.createPersonaje());
        party.add(cdedede.createPersonaje());
        party.add(cbandana.createPersonaje());
        party.add(cenemy.createPersonaje());
    }
}
