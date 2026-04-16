package store;

import java.util.List;
import java.util.Random;
import java.util.Scanner;

import characters.Personajes;
import inventory.Item;
import inventory.Item.TipoItem;

public class Tienda {

	private static final int Max_Items_Catalogos = 10;
	
	private List<Item> catalogo;
	private boolean itemGratisDisponible;
	private Item itemRecompensa;
	private List<String> mensajesBienvenida;
	private Random rand;
	private Scanner scanner;
	
	public Tienda() {
		this.catalogo = new ArrayList<>();
		this.itemGratisDisponible = true;
		this.mensajesBienvenida = new ArrayList<>();
		inicializarCatalogo();
		inicializarMensajes();
		this.itemGratisDisponible = generarItemRecompensa();
	}
	
	public void mostrarCatalogo() {
		
		System.out.println("\n── CATÁLOGO ─────────────────────────────────────────");
        mostrarItemGratis();
        mostrarStockDisponible();
        System.out.println("─────────────────────────────────────────────────────");
		
		
        
	}
	
	public void comprarItem(Personajes personaje) {
		if(!itemEstaEnCatalago(item)) {
			return;
		}
		if(!personajePuedePermitirse(item, personaje)) {
			return;
		}
		cobrarOro(item, personaje);
		entregarItem(item, personaje);
		mostrarMensajeCompra(item);
    }
	
    public void reclamarItemGratis(Personaje personaje) {
        if (!itemGratisDisponible) {
            System.out.println("Ya has reclamado el item gratuito de hoy.");
            return;
        }
        entregarItem(itemGratisDelDia, personaje);
        marcarItemGratisComoReclamado();
        System.out.println("Has reclamado: " + itemGratisDelDia.getNombre() + " gratuitamente!");
    }

	public void inicializarCatalogo() {
		// Consumibles
        catalogo.add(new Item("Poción menor",       "Recupera 30 de vida",             20,  TipoItem.Curacion_pequeña,    30));
        catalogo.add(new Item("Poción mayor",       "Recupera 80 de vida",             50,  TipoItem.Curacion_grande,     80));
        catalogo.add(new Item("Antídoto de bruja",  "Cura todos los estados",          35,  TipoItem.Cura_totalEstado,      0));
        //catalogo.add(new Item("Polvo de Behelit",   "Sube 10 puntos a una stat 1 combate", 45, TipoItem.POLVO_STAT,       10));

        // Armaduras
        catalogo.add(new Item("Armadura ligera",    "+5 de defensa",                   60,  TipoItem.Armadura_pequeña,       5));
        catalogo.add(new Item("Armadura de acero",  "+12 de defensa",                 120,  TipoItem.Armadura_media,    12));
        catalogo.add(new Item("Armadura de Guts",   "+20 de defensa",                 220,  TipoItem.Armadura_alta,       20));
        catalogo.add(new Item("Armadura del Berserker", "+35 de defensa, maldita",    400,  TipoItem.Armadura_definitva, 35));

        // Armas
        catalogo.add(new Item("Espada corta",       "+5 de ataque",                    50,  TipoItem.Arma_espada_media,    5));
        catalogo.add(new Item("Espada larga",       "+12 de ataque",                  110,  TipoItem.Arma_espada_alta,   12));
        catalogo.add(new Item("Espada encantada",   "+20 de ataque, ignora algo de defensa", 250, TipoItem.Arma_espada_definitiva, 20));
        //catalogo.add(new Item("Espada maldita",     "+35 de ataque, única por personaje",    450, TipoItem.ARMA_ESPADA_MALDITA,   35));

        // Especial
        catalogo.add(new Item("Caballo salvaje",    "Permite esquivar un enemigo",    150,  TipoItem.Caballo_salvaje,      0));
        
    }

    private void inicializarMensajes() {
    	
    	mensajesBienvenida.add("Bienvenido, viajero. Tengo lo que necesitas... por un precio.");
        mensajesBienvenida.add("Otro aventurero en busca de poder. Adelante, echa un vistazo.");
        mensajesBienvenida.add("Mi mercancía es la mejor del reino. No encontrarás mejor oferta.");
	}
	
	public void mostrarMensajeBienvenida() {
		
        int indice = rand.nextInt(mensajesBienvenida.size());
        System.out.println("\n╔══════════════════════════════════════╗");
        System.out.println("║           TIENDA DEL CAMINO          ║");
        System.out.println("╚══════════════════════════════════════╝");
        System.out.println(mensajesBienvenida.get(indice));
        
	}
	
	private boolean jugadorPuedePermitirse(Item item, Personajes personaje) {
		
		if(personaje.getOro() >= item.getPrecio()) {
			return true;
		}else {
			System.out.println("No tienes suficiente oro. Necesitas: " + item.getPrecio() + " oro.");
		}
	}
	
	private void cobrarOro(Item item, Personajes personaje) {
		
        personaje.perderOro(item.getPrecio());
	}
	 
	private void entregarItem(Item item, Personajes personaje) {
		personaje.getInventario().agregar(item);
	}
	
	private boolean itemEstaEnCatalago(Item item) {
		
		if(catalogo.contains(item)) {
			return true;
		}else {
			System.out.println("Ese item no está disponible en la tienda.");
		}
	}
	
	private Item generarItemRecompensa() {
		
		List<Item> posiblesGratis = new ArrayList<>();
        posiblesGratis.add(new Pocion("Poción pequeña",  20, 0));
        posiblesGratis.add(new Pocion("Poción de maná",   0, 30));
        int indice = new Random().nextInt(posiblesGratis.size());
        return posiblesGratis.get(indice);
	}
	
	private void marcarItemRecompensaComoReclamado() {
	        this.itemGratisDisponible = false;
	}
	
	private void mostrarItemGratis() {
		
		String estado = itemGratisDisponible ? "[Dispobile]" : "[Ya Reclamado]";
		System.out.println("Item gratis - " + estado + " -: " + itemRecompensa.getNombre());
	}
	
	private void mostrarStockDisponible() {
        System.out.println("\n--- Catálogo ---");
        for (Item item : catalogo) {
            mostrarPrecio(item);
        }
    }

    private void mostrarPrecio(Item item) {
        System.out.println("  " + item.getNombre() + " ........... " + item.getPrecio() + " oro");
    }

    private void mostrarMensajeCompra(Item item) {
        System.out.println("Has comprado: " + item.getNombre() + " por " + item.getPrecio() + " oro.");
    }
	
	
	
	
}
