package store;

import java.util.List;
import java.util.Random;
import java.util.Scanner;

import characters.Personajes;
import inventory.Item;
import inventory.Item.TipoItem;

public class Tienda {


	private List<Item> catalogo;
	private boolean itemGratisDisponible;
	private Item itemRecompensa;
	private List<String> mensajesBienvenida;
	private Random rand;
	private Scanner scanner;
	
	public Tienda() {
		
	}
	
	public void mostrarCatalogo() {
		
		System.out.println("\n── CATÁLOGO ─────────────────────────────────────────");
        for (int i = 0; i < catalogo.size(); i++) {
            System.out.printf("[%2d] %s%n", i + 1, catalogo.get(i).toString());
        }
        if (itemGratisDisponible) {
            System.out.println("\n[*] Tienes un objeto GRATIS disponible. Escribe 0 para reclamarlo.");
        }
        System.out.println("[X]  Salir de la tienda");
        System.out.println("─────────────────────────────────────────────────────");
		
		
        
	}
	
	public void comprarItem(Personajes personaje) {
		mostrarMensajeBienvenida();

        boolean enTienda = true;

        while (enTienda) {
            mostrarCatalogo();
            System.out.printf("Oro disponible: %d%n", personaje.getOro());
            System.out.print("Elige opción: ");
            String entrada = scanner.nextLine().trim();

            if (entrada.equalsIgnoreCase("X")) {
                System.out.println("\"Que la espada te acompañe.\"");
                enTienda = false;

            } else if (entrada.equals("0") && itemGratisDisponible) {
                reclamarItem(personaje);

            } else {
                    int opcion = Integer.parseInt(entrada) - 1;

                    if (opcion < 0 || opcion >= catalogo.size()) {
                        System.out.println("Opción no válida. Elige un número de la lista.");
                        continue;
                    }

                    Item seleccionado = catalogo.get(opcion);

                    if (!jugadorPuedePermitirse(seleccionado, personaje)) {
                        System.out.printf("No tienes suficiente oro. Necesitas %d y tienes %d.%n",
                                seleccionado.getPrecio(), personaje.getOro());
                        continue;
                    }

                    cobrarOro(seleccionado, personaje);
                    entregarItem(seleccionado, personaje);

                
                }
            }
        }
	
	   public void reclamarItem(Personajes personaje) {
		   
	        if (!itemGratisDisponible) {
	            System.out.println("Ya reclamaste tu objeto gratuito.");
	            return;
	        }

	        System.out.println("\nTu objeto gratuito de hoy es: " + itemRecompensa.getNombre());
	        System.out.println(itemRecompensa.getDescripcion());
	        entregarItem(itemRecompensa, personaje);
	        marcarItemRecompensa();
	        
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
    	
        mensajesBienvenida.add("\"Bienvenido, espadachín. Aquí encontrarás lo que necesitas... si tienes oro.\"");
        mensajesBienvenida.add("\"Los monstruos del eclipse no esperan. Compra rápido.\"");
        mensajesBienvenida.add("\"He visto caer a mejores guerreros. Quizás esto te ayude a sobrevivir.\"");
        mensajesBienvenida.add("\"El destino está escrito en tu marca. Pero el equipo correcto puede retrasar lo inevitable.\"");
	}
	
	public void mostrarMensajeBienvenida() {
		
        int indice = rand.nextInt(mensajesBienvenida.size());
        System.out.println("\n╔══════════════════════════════════════╗");
        System.out.println("║           TIENDA DEL CAMINO          ║");
        System.out.println("╚══════════════════════════════════════╝");
        System.out.println(mensajesBienvenida.get(indice));
        
	}
	
	private boolean jugadorPuedePermitirse(Item item, Personajes personaje) {
		return personaje.getOro() >= item.getPrecio();
		
	}
	
	private void cobrarOro(Item item, Personajes personaje) {
		
		personaje.setOro(personaje.getOro() - item.getPrecio());
        System.out.printf("Se han descontado " + item.getPrecio() + " de oro. Oro restante: " + personaje.getOro());
        
	}
	
	private void aplicarEfectoEquipo(Item item, Personajes personaje) {
		
	}
	 
	private void entregarItem(Item item, Personajes personaje) {
		
	}
	
	private boolean itemEstaEnCatalago(Item item) {
		
	}
	
	private Item generarItemRecompensa() {
		
	}
	
	private void marcarItemRecompensa() {
	        this.itemGratisDisponible = false;
	}
	
	
	
	
	
	
}
