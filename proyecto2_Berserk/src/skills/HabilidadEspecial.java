package skills;

// HabilidadEspecial permite pasar un coste de energía personalizado.
public class HabilidadEspecial extends Habilidades {
    public HabilidadEspecial(String nombre, String descripcion, double mult, int coste) {
        super(nombre, descripcion, mult, coste);
    }
}


//La Pasiva se guarda en la lista pero tiene lógica interna distinta.
public class HabilidadPasiva extends Habilidades {
 public HabilidadPasiva(String nombre, String descripcion) {
     super(nombre, descripcion, 1.0, 0);
 }
}