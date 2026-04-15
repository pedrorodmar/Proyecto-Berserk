package inventory;


 // Representa una armadura del juego
 
public class Armadura extends Item {

    public Armadura(String nombre, String descripcion, int precio, TipoItem tipo, int defensa) {
        super(nombre, descripcion, precio, tipo, defensa, 1);
    }

    
    // Devuelve la defensa
     
    public int getDefensa() {
        return getValorEfecto();
    }

    
    // Acción al usar la armadura
     
    public void usar() {
        System.out.println("Has equipado la armadura: " + getNombre() + " (Defensa: " + getDefensa() + ")");
    }
}