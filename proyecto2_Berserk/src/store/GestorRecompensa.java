package store;

import java.util.Random;

import characters.Personajes;
import inventory.Item;

public class GestorRecompensa {

	private int xpBase = 50;
	private int oroBase = 30;
	private int probOro = 60; 		//60% de probabilidad
	private int probItem = 30;		//30% de prob
	private Random rand;			
	
	public GestorRecompensa() {
		this.rand = new Random();
	}
	
	public void generarRecompensa(Personajes personaje, int nivel) {
		int xp  = calcularXp(nivelEnemigo);
        entregarXp(personaje, xp);

        String tipoRecompensa = decidirTipoRecompensa();
        procesarTipoRecompensa(tipoRecompensa, personaje, nivelEnemigo);
	}
	
	
	private int calcularXP(int nivelE) {
		
		return xpBase * nivelE;
	}
	
	private int calcularOro(int oroE) {
		
		return oroBase * oroE + rand.nextInt(20);
	}
	
	private void entregarXP(Personajes personaje, int xp) {
		personaje.ganarExperiencia(xp);
	}
	
	private void entregarOro(Personajes personaje, int oro) {
		personaje.ganarOro(oro);
	}
	
	private String decidirTipoRecompensa() {
        int tirada = random.nextInt(100);   // número entre 0 y 99
        if (tirada < probOro) {
        	return "ORO";
        }
        if (tirada < probItem + probOro) {
        	return "ITEM";
        }
        return "NADA";                      // 10% de no obtener recompensa extra
	}
	
	private void procesarTipoRecompensa(String tipo, Personaje personaje, int nivel) {
        switch (tipo) {
            case "ORO":
                int oro = calcularOro(nivel);
                entregarOro(personaje, oro);
                mostrarMensajeRecompensa("ORO", 0, oro, null);
                break;
            case "ITEM":
                Item item = generarItemAleatorio();
                entregarItem(personaje, item);
                mostrarMensajeRecompensa("ITEM", 0, 0, item.getNombre());
                break;
            case "NADA":
                System.out.println("El enemigo no llevaba nada de valor.");
                break;
        }
    }
	
	private Item generarItemAleatorio(Item item) {
		
		List<Item> posiblesDrops = new ArrayList<>();
        posiblesDrops.add(new Pocion("Poción pequeña",  20,  0,  0));
        posiblesDrops.add(new Pocion("Poción de maná",   0, 30,  0));
        posiblesDrops.add(new Arma("Espada corta",      15,  0));
        int indice = rand.nextInt(posiblesDrops.size());
        return posiblesDrops.get(indice);
	}
	
	private void mostrarMensajeRecompensa(String tipo, int xp, int oro, String nombreItem) {
        System.out.println("\n── RECOMPENSA ──────────────────");
        if (xp  > 0) {
        	System.out.println("  XP obtenida  : " + xp);
        }
        if (oro > 0) {
        	System.out.println("  Oro obtenido : " + oro);
        }
        if (nombreItem != null) {
        	System.out.println("  Item obtenido: " + nombreItem);
        }
        System.out.println("────────────────────────────────");
    }
	
}
