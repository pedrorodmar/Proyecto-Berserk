package characters;

import skills.Ataque;
import skills.HabilidadEspecial;
import skills.HabilidadPasiva; 

public class Griffith extends Personajes {

    // CONSTRUCTOR 
    public Griffith() {
        super("Griffith", 90, 25, 15, 30, 70);
    }

    // --- GETTERS Y SETTERS ---
    // Se utilizan los heredados de la clase Personajes.

    // MÉTODOS VARIOS 
    
    @Override
    protected void inicializarHabilidades() {
    	
    	// Aquí es donde llenamos la caja vacía de habilidades y metemos las d Griffith.
    	
        this.getHabilidades().add(new HabilidadPasiva("Aura del Halcón", "20% de probabilidad de esquivar."));
        this.getHabilidades().add(new Ataque("Estocada Fina", "Ataque rápido de esgrima.", 1.1));
        this.getHabilidades().add(new HabilidadEspecial("Vuelo del Halcón", "Ráfaga de estocadas.", 1.8, 20));
        this.getHabilidades().add(new HabilidadEspecial("Juicio Divino", "Golpe certero definitivo.", 3.0, 45));
    }

    @Override
    protected void aplicarBonusDeSubidaNivel() {
        
        //Al ser un personaje ágil, gana mucha velocidad y energía.
         
        this.setVidaMaxima(this.getVidaMaxima() + 10);
        this.setDanioBase(this.getDanioBase() + 4);
        this.setVelocidad(this.getVelocidad() + 6);
        this.setEnergiaMaxima(this.getEnergiaMaxima() + 10);
    }
}