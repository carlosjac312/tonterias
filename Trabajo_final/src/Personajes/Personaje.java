package Personajes;

import DatosPersonajes.Estadisticas;

import java.util.ArrayList;

/**
 * Esta es la superclase para los personajes del juego por ahora solo dos acciones están implementadas que son las de Ataco y defiendo
 */
public abstract class Personaje implements Habilidad {
    protected int identifier;
    public Estadisticas stats=new Estadisticas();
    /**
     * Estadisticas es una clase que almacena la vida el daño del personaje y sus puntos de habilidad
     */
    public ArrayList<String> Habilidades=new ArrayList<String>();//Aun no he implementado las habilidades

    protected String name;
    public String getname(){
        return name;
    }

    public int getIdentifier() {
        return identifier;
    }
    protected boolean defend;

    /**
     * La función ataco le calcula el daño que recivira el objetivo sacando un numero aleatorio entre 0 y el atque del personaje-1
     * y luego se lo resta a sus PV
     * Aqui se usa el boolean defend que si esta en true el daño se didvide en 2
     * @param objetivo Define quien es el objetivo del ataque
     * @return El return está por estética por si añado el mensaje de has hecho x daño
     */
    public int Ataco(Personaje objetivo) {
        int daño= (int) (Math.random()*stats.ataque);
        if (objetivo.defend==true){
            daño/=2;
            objetivo.defend=false;
        }
        objetivo.stats.PV-=daño;
        return daño;
    }
    /**
     * Al usar la acción Defiendo se pone el parametro defend en true y ahora el personaje que lo utilizo recibira menos daño
     * del próximo ataque
     */
    public void Defiendo() {
        defend=true;
    }
}
