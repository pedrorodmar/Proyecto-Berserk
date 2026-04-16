package skills;

public class Ataque extends Habilidades {
    public Ataque(String nombre, String descripcion, double mult) {
        // Un ataque básico siempre gasta 0 de energía
        super(nombre, descripcion, mult, 0); 
    }
}

