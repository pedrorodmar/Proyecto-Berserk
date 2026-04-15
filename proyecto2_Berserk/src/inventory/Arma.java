package inventory;


  // Representa un arma del juego
 
public class Arma extends Item {

    public Arma(String nombre, String descripcion, int precio, TipoItem tipo, int danio) {
        super(nombre, descripcion, precio, tipo, danio, 1);
    }

   
    // Devuelve el daño del arma
     
    public int getDanio() {
        return getValorEfecto();
    }

    
     // Acción al usar el arma
     
    public void usar() {
        System.out.println("Has equipado el arma: " + getNombre() + " (Daño: " + getDanio() + ")");
    }
}