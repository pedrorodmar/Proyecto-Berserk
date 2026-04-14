package characters;

import java.util.ArrayList;

import inventory.Inventario;
import sun.management.BaseOperatingSystemImpl;

public abstract class Personajes {
	
	//Atributos de los Personajes
	
	private String name;
	private int vidaActual;
	private int vidaMaxima;
	private int danioBase;
	private int defensa;
	private int velocidad;
	private int energiaActual;
	private int energiaMaxima;
	private int nivel;
	private int experiencia;
	private int oro;
	private  List<Habilidad> habilidades;
	private Inventario inventario;
	private EstadoPersonaje estadoActual;
	private boolean vivo;
	
	//Costructor
	public Personajes(String name, int vidaMaxima, int danioBase, int defensa, int velocidad, int energiaMaxima) {
		
		this.name = name;
		this.vidaActual = vidaMaxima;			//comienza con la vida al máximo
		this.vidaMaxima = vidaMaxima;
		this.danioBase = danioBase;
		this.defensa = defensa;
		this.energiaActual = energiaMaxima;		//comienza con la energía al máximo
		this.energiaMaxima = energiaMaxima;
		
		this.nivel = 1;
		this.experiencia = 0;
		this.oro = 0;
		this.vivo = true;
		
		//inicaliza habilidades e inventario
		
		this.inventario = new Inventario();
		this.habilidades = new ArrayList<>();
		
		inicializarHabilidades();
	
	}
	
	//Getters y Setters
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getVidaActual() {
		return vidaActual;
	}

	public void setVidaActual(int vidaActual) {
		this.vidaActual = vidaActual;
	}

	public int getVidaMaxima() {
		return vidaMaxima;
	}

	public void setVidaMaxima(int vidaMaxima) {
		this.vidaMaxima = vidaMaxima;
	}

	public int getDanioBase() {
		return danioBase;
	}

	public void setDanioBase(int danioBase) {
		this.danioBase = danioBase;
	}

	public int getDefensa() {
		return defensa;
	}

	public void setDefensa(int defensa) {
		this.defensa = defensa;
	}

	public int getVelocidad() {
		return velocidad;
	}

	public void setVelocidad(int velocidad) {
		this.velocidad = velocidad;
	}

	public int getEnergiaActual() {
		return energiaActual;
	}

	public void setEnergiaActual(int energiaActual) {
		this.energiaActual = energiaActual;
	}

	public int getEnergiaMaxima() {
		return energiaMaxima;
	}

	public void setEnergiaMaxima(int energiaMaxima) {
		this.energiaMaxima = energiaMaxima;
	}

	public int getNivel() {
		return nivel;
	}

	public void setNivel(int nivel) {
		this.nivel = nivel;
	}

	public int getExperiencia() {
		return experiencia;
	}

	public void setExperiencia(int experiencia) {
		this.experiencia = experiencia;
	}

	public int getOro() {
		return oro;
	}

	public void setOro(int oro) {
		this.oro = oro;
	}

	
	//Métodos Vida
		
	public void curar(int cantidad) {
		
		if(cantidad <= 0) {
			return;
		}
		vidaActual = Math.min(vidaActual + cantidad, vidaMaxima);	//con esto nos aseguramos que la vida Actual nunca supere la vida máxima del personaje avitando así crear un
	}																//método extra para controlar la vida
	
	public void restarVida(int cantidad) {
		
		vidaActual = Math.max(0, vidaActual - cantidad);			//resta la vida actual por daño recibida, cantidad, y controlamos que la vida nunca sea negativa con Math.min
	}
	
	public void actualizarEstadoVida() {							//comprueba que el personaje ha muerto o no
		if(vidaActual == 0) {
			this.vivo = false;
		}
	}
	
	public void mostrarMensajeDanio(int danioReal) {				//simplemente muestra el mensaje del daño recibido recibiendo el daño
		
		System.out.println(name + " ha recibido " + danioReal + " puntos de daño!");
		if(!this.vivo) {
			System.out.println(name + " ha sido derrotado... .. .");	//en caso de haber sido derrotado con ese ataque mostrará este mensaje
		}
	}
	
	public void recibirDanio1(int danio) {
		
		int danioReal = calcularDanioReal(danio);			//le pasamos a una variable el daño calculado
		
		danioReal = aplicarModificadorEstado(danioReal);	//comprobamos que sea el daño total que tiene que hacer por si está en un estado vulnerable que no sea el normal
		restarVida(danioReal);								//restamo la vida correspondiente
		actualizarEstadoVida();								//actualizamos el estado de vida del personaje
		mostrarMensajeDanio(danioReal);
	}
	
	private int calcularDanioReal(int danio) {
		
		return Math.max(1, danio - this.defensa);			//math.max para evitar que el daño que recibe nunca sea 0, mínimo 1
	}
	
	private int aplicarModificadorEstado(int danioReal) {
		
		int danioTotal;
		if(this.estadoActual == EstadoPersonaje.VULNERABLE) {	//en caso de que esté vulnerable el ataque hace 1.5 de daño más por lo que cambiamos el daño real
			danioTotal = (int)(danioReal * 1.5);				//el (int) nos asegura que danioReal sea un int y no un double después de multiplicarlo por 1.5
		}
		
		return danioTotal;
	}
	

	//Métodos de energía
	
	public void recuperarEnergia(int cantidad) {		//método donde se recuperará energia llamando a el otor método que controla que la energia no pase de la energia máxima 
													   	// ni sea 0
		if(cantidad <= 0) {
			return;
		}
		energiaActual = Math.min(energiaActual + cantidad, energiaMaxima);
	}
	
	public void restarEnergia(int coste) {
		
		energiaActual = Math.max(0, energiaActual - coste);
	}
	
	public boolean tieneEnergiaParaHabilidad(int coste) {		//en este método se comprueba antes de lanzar la habilidad que verdaderamente puede lanzarla, evitando así
																//que se lance una habilidad de mayor coste de casteo que la energia que le queda
		return energiaActual >= coste;
	}
	
	public boolean gastarEnergia(int coste) {
		
		if(!tieneEnergiaParaHabilidad(coste)) {									//comprueba que si se puede o no castear la habilidad seleccionada
			System.out.println(name + " no tiene suficiente energía.");
			return false;
		}
		restarEnergia(coste);
		return true;
	}
	
	//Métodos de experiencia
	
	public void ganarExperiencia(int xp) {
		
		if(xp <= 0) {
			return;
		}
		this.experiencia += xp;
		System.out.println(name + " gana " + xp + " puntos de experiencia.");
		comprobarSubidaNivel();
	}
	
	private boolean tieneSuficienteExperiencia() {
		
		return this.experiencia >= calcularXpNecesaria();
	}
	
	private int calcularXpNecesaria() {
		
		if(tieneSuficienteExperiencia()) {
			subirNivel();
		}
	}
	
	public void subirNivel() {
		
		this.nivel++;
		this.experiencia = 0;
		restaurarVidaCompleta(); 
		restaurarEnergiaCompleta();
		aplicarBonusDeSubidaNivel();
		mostrarMensajeSubidaNivel();
	}
	
	protected abstract void aplicarBonusDeSubidaNivel();

	private void restaurarVidaCompleta() {
		
		this.vidaActual = this.vidaMaxima;
	}
	
	private void restaurarEnergiaCompleta() {
		
		this.energiaActual = this.energiaMaxima;
	}
	
	private void mostrarMensajeSubidaNivel() {
		
		System.out.println("-- " + name + " ha subido de nivel! \nSu nivel actual es de " + nivel +"! --");
	}
	
	
	//Métodos de Oro
	
public void ganarOro(int oro) {
		
		if(oro <= 0) {
			return;
		}
		this.oro += oro;
		System.out.println(name + " gana " + oro + " puntos de oro.");
	}
	
	
    //Métodos de Impresión
        
	@Override
    public String toString(){
        return name + " | HP: "+ vidaActual + "/" + vidaMaxima
                +" | STMN: " + energiaActual + "/" + energiaMaxima
                +" | ATK: " + danioBase
                +" | DEF: " + defensa
                +" | VEL: " + velocidad;
    }
}
