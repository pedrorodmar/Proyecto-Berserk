package characters;

import skills.Ataque;
import skills.HabilidadEspecial;

public class Casca extends Personajes {

    public Casca() {
        super("Casca", 100, 20, 15, 20, 60);
    }

    @Override
    protected void inicializarHabilidades() {
        // 1. Habilidad Pasiva
        this.getHabilidades().add(new HabilidadPasiva("Supervivencia Instintiva", 
            "Pasiva: Al recibir un golpe letal, sobrevive con 1 HP (Solo funciona una vez por combate)."));
            
        // 2. Ataque Básico (Coste: 0)
        this.getHabilidades().add(new Ataque("Golpe Táctico", 
            "Corte preciso buscando los puntos débiles de la armadura.", 1.0));
            
        // 3. Habilidad Especial (Coste: 15)
        this.getHabilidades().add(new HabilidadEspecial("Patada Desequilibrante", 
            "Golpea al enemigo dejándolo 'Vulnerable' para el siguiente turno.", 1.3, 15));
            
        // 4. Habilidad Especial Definitiva (Coste: 35)
        this.getHabilidades().add(new HabilidadEspecial("Carga de la Vanguardia", 
            "Ataque coordinado con una ferocidad implacable.", 2.4, 35));
    }

    @Override
    protected void aplicarBonusDeSubidaNivel() {
        // Casca es la más equilibrada en todas las áreas
        this.setVidaMaxima(this.getVidaMaxima() + 15);
        this.setDanioBase(this.getDanioBase() + 3);
        this.setDefensa(this.getDefensa() + 3);
        this.setVelocidad(this.getVelocidad() + 3);
        this.setEnergiaMaxima(this.getEnergiaMaxima() + 8);
    }
}