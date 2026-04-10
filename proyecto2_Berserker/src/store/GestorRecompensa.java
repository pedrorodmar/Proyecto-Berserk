package store;

import java.util.Random;

import characters.Personajes;
import inventory.Item;

public class GestorRecompensa {

	private int xpBase = 50;
	private int oroBase = 30;
	private int probOro = 60;
	private int probItem = 30;
	private Random rand;
	
	public GestorRecompensa() {
		
	}
	
	public void generarRecompensa(Personajes personaje, int nivel) {
		
	}
	
	public void generarRecompensaRandom(Personajes personaje) {
		
	}
	
	private int calcularXP(int nivel) {
		
		return nivel;
	}
	
	private int calcularOro(int oro) {
		
		return oro;
	}
	
	private void entregarXP(Personajes personaje, int nivel) {
		
	}
	
	private void entregarOro(Personajes personaje, int oro) {
		
	}
	
	private String decidirTipoRecompensa() {
		
		return null;
	}
	
	private Item generarItemAleatorio(Item item) {
		
		return item;
	}
	
	private void entregarItem(Personajes personaje, int cantidad) {
		
	}
	
	private int aplicarMultiplicadorJefe(int cantidad) {
		
		return cantidad;
	}
	
	private void mostrarMensajeRecompensa(int cantidad) {
		
	}
	
	
}
