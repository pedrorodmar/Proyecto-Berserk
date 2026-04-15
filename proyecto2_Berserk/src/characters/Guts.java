package characters;

import skills.Ataque;
import skills.HabilidadEspecial;

public class Guts extends Personajes {

    public Guts() {
        super("Guts", 120, 30, 20, 10, 50);
    }

    @Override
    protected void inicializarHabilidades() {
    	// 1. Habilidad Pasiva
        this.getHabilidades().add(new HabilidadPasiva("Marca del Sacrificio", 
            "Pasiva: Si la vida de Guts baja del 30%, entra en estado 'Berserker' y hace un 50% más de daño."));
            
        // 2. Ataque Básico (Coste: 0)
        this.getHabilidades().add(new Ataque("Tajo Matadragones", 
            "Un barrido horizontal muy pesado.", 1.2));
            
        // 3. Habilidad Especial (Coste: 25)
        this.getHabilidades().add(new HabilidadEspecial("Corte Ciclón", 
            "Gira sobre sí mismo dañando con gran fuerza.", 1.8, 25));
            
        // 4. Habilidad Especial Definitiva (Coste: 45)
        this.getHabilidades().add(new HabilidadEspecial("Cañón de Brazo", 
            "Dispara el cañón oculto en su prótesis a quemarropa. Daño masivo.", 3.5, 45));
    }

    @Override
    protected void aplicarBonusDeSubidaNivel() {
        // Guts prioriza Vida, Ataque y Defensa
        this.setVidaMaxima(this.getVidaMaxima() + 20);
        this.setDanioBase(this.getDanioBase() + 6);
        this.setDefensa(this.getDefensa() + 5);
        this.setEnergiaMaxima(this.getEnergiaMaxima() + 5);
    }
}