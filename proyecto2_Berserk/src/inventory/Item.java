package inventory;

import jdk.internal.misc.FileSystemOption;
import jdk.internal.org.jline.terminal.TerminalBuilder.SystemOutput;

public abstract class Item {

	public enum TipoItem{
		
		Curacion_pequeña,
		Curacion_grande,
		Cura_totalEstado,
		Armadura_pequeña,
		Armadura_media,
		Armadura_alta,
		Armadura_definitva,
		Arma_espada_media,
		Arma_espada_alta,
		Arma_espada_definitiva,
		Caballo_salvaje,
		Oro,
		Poción;
	}
	
	private String nombre;
	private String descripcion;
	private int precio;
	private TipoItem tipo;
	private int valorEfecto;
	private int cantidad;
	
	public Item(String nombre, String descripcion, int precio, TipoItem tipo, int valorEfecto, int cantidad) {
		
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.precio = precio;
		this.tipo = tipo;
		this.valorEfecto = valorEfecto;
		this.cantidad = cantidad;
		
	}

	public String getNombre() {
		return nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public int getPrecio() {
		return precio;
	}

	public TipoItem getTipo() {
		return tipo;
	}

	public int getValorEfecto() {
		return valorEfecto;
	}

	public int getCantidad() {
		return cantidad;
	}
	
	public void incrementarCantidad() {
		this.cantidad++;
	}
	
	public void decrementarCantidad() {
		if(this.cantidad > 0) {
			cantidad--;
		}
	}
	
	
	public boolean esConsumible() {
		return tipo == TipoItem.Curacion_pequeña ||
				tipo == TipoItem.Curacion_grande ||
				tipo == TipoItem.Cura_totalEstado ||
				tipo == TipoItem.Poción;
	}
	
	public boolean esArmadura() {
		return tipo == TipoItem.Armadura_pequeña ||
				tipo == TipoItem.Armadura_media ||
				tipo == TipoItem.Armadura_alta ||
				tipo == TipoItem.Armadura_definitva ;
	}
	
	public boolean esArma() {
		return tipo == TipoItem.Arma_espada_media ||
				tipo == TipoItem.Arma_espada_alta ||
				tipo == TipoItem.Arma_espada_definitiva;
	}
	
	public abstract void usar();
	
	@Override
	
	public String toString() {
		return nombre + " | Precio: " + precio + " | Efecto: " + valorEfecto + " | " + descripcion;
	}
	
	
	
	
	
	
}
