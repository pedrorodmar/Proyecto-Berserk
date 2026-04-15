package inventory;


// Representa una poción del juego
 
public class Pocion extends Item {

    public Pocion(String nombre, String descripcion, int precio, TipoItem tipo, int curacion, int cantidad) {
        super(nombre, descripcion, precio, tipo, curacion, cantidad);
    }

    
    // Devuelve la cantidad de curación
    
    public int getCuracion() {
        return getValorEfecto();
    }

    
    // Acción al usar la poción
     
    public void usar() {
        System.out.println("Usas " + getNombre() + " y recuperas " + getCuracion() + " de vida");
    }
}
