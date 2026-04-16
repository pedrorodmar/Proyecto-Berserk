package characters;

import java.util.ArrayList;
import java.util.List;

import inventory.Inventario;

public abstract class Personajes {

    // Atributos de los Personajes
    
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
    private List<Habilidad> habilidades;
    private Inventario inventario;
    private EstadoPersonaje estadoActual;
    private boolean vivo;

    // Constructor
    public Personajes(String name, int vidaMaxima, int danioBase, int defensa, int velocidad, int energiaMaxima) {

        this.name = name;
        this.vidaMaxima = vidaMaxima;
        this.vidaActual = vidaMaxima;            // comienza con la vida al máximo
        this.danioBase = danioBase;
        this.defensa = defensa;
        this.velocidad = velocidad;
        this.energiaMaxima = energiaMaxima;
        this.energiaActual = energiaMaxima;      // comienza con la energía al máximo

        this.nivel = 1;
        this.experiencia = 0;
        this.oro = 0;
        this.vivo = true;

        this.estadoActual = EstadoPersonaje.VULNERABLE;

        // inicializa habilidades e inventario
        this.inventario = new Inventario();
        this.habilidades = new ArrayList<>();

        inicializarHabilidades();
    }

    // Métodos abstractos
    protected abstract void inicializarHabilidades();
    protected abstract void aplicarBonusDeSubidaNivel();

    // ================= VIDA =================

    public void curar(int cantidad) {

        if (cantidad <= 0) {
            return;
        }

        // nos aseguramos que la vida actual nunca supere la vida máxima del personaje evitando crear método extra
        vidaActual = Math.min(vidaActual + cantidad, vidaMaxima);
    }

    public void recibirDanio(int danio) {

        int danioReal = calcularDanioReal(danio);            // calculamos el daño real teniendo en cuenta defensa

        danioReal = aplicarModificadorEstado(danioReal);     // comprobamos si el estado modifica el daño (ej: vulnerable)

        vidaActual = Math.max(0, vidaActual - danioReal);    // evitamos que la vida sea negativa

        if (vidaActual == 0) {
            this.vivo = false;                               // comprobamos si el personaje ha muerto
        }

        System.out.println(name + " recibe " + danioReal + " puntos de daño");

        if (!this.vivo) {
            System.out.println(name + " ha sido derrotado...");
        }
    }

    private int calcularDanioReal(int danio) {

        // evitamos que el daño sea 0 → mínimo 1
        return Math.max(1, danio - this.defensa);
    }

    private int aplicarModificadorEstado(int danio) {

        // si está vulnerable recibe más daño
        if (this.estadoActual == EstadoPersonaje.VULNERABLE) {
            return (int) (danio * 1.5);      // casteamos a int tras multiplicar
        }

        return danio;
    }

    // ================= ENERGÍA =================

    public boolean gastarEnergia(int coste) {

        // comprobamos que tenga suficiente energía para usar la habilidad
        if (energiaActual < coste) {
            System.out.println(name + " no tiene suficiente energía.");
            return false;
        }

        energiaActual -= coste;      // restamos energía
        return true;
    }

    public void recuperarEnergia(int cantidad) {

        // controlamos que no supere el máximo ni sea negativa
        if (cantidad <= 0) {
            return;
        }

        energiaActual = Math.min(energiaActual + cantidad, energiaMaxima);
    }

    // ================= EXPERIENCIA =================

    public void ganarExperiencia(int xp) {

        if (xp <= 0) {
            return;
        }

        this.experiencia += xp;
        System.out.println(name + " gana " + xp + " puntos de experiencia.");

        // comprobamos si sube de nivel
        if (experiencia >= calcularXpNecesaria()) {
            subirNivel();
        }
    }

    private int calcularXpNecesaria() {

        // fórmula simple de subida de nivel
        return nivel * 100;
    }

    private void subirNivel() {

        this.nivel++;
        this.experiencia = 0;

        restaurarVidaCompleta();
        restaurarEnergiaCompleta();

        aplicarBonusDeSubidaNivel();

        mostrarMensajeSubidaNivel();
    }

    private void restaurarVidaCompleta() {

        this.vidaActual = this.vidaMaxima;     // restaura vida al máximo
    }

    private void restaurarEnergiaCompleta() {

        this.energiaActual = this.energiaMaxima;  // restaura energía al máximo
    }

    private void mostrarMensajeSubidaNivel() {

        System.out.println("-- " + name + " ha subido de nivel! \nSu nivel actual es de " + nivel + "! --");
    }

    // ================= ORO =================

    public void ganarOro(int cantidad) {

        if (cantidad <= 0) {
            return;
        }

        this.oro += cantidad;
        System.out.println(name + " gana " + cantidad + " puntos de oro.");
    }

 // ================= GETTERS =================

    public String getName() {
        return name;
    }

    public int getVidaActual() {
        return vidaActual;
    }

    public int getVidaMaxima() {
        return vidaMaxima;
    }

    public int getDanioBase() {
        return danioBase;
    }

    public int getDefensa() {
        return defensa;
    }

    public int getVelocidad() {
        return velocidad;
    }

    public int getEnergiaActual() {
        return energiaActual;
    }

    public int getEnergiaMaxima() {
        return energiaMaxima;
    }

    public int getNivel() {
        return nivel;
    }

    public int getExperiencia() {
        return experiencia;
    }

    public int getOro() {
        return oro;
    }

    public List<Habilidad> getHabilidades() {
        return habilidades;
    }

    public Inventario getInventario() {
        return inventario;
    }

    public EstadoPersonaje getEstadoActual() {
        return estadoActual;
    }

    public boolean isVivo() {
        return vivo;
    }


    // ================= SETTERS =================

    public void setName(String name) {
        this.name = name;
    }

    public void setDanioBase(int danioBase) {
        this.danioBase = danioBase;
    }

    public void setDefensa(int defensa) {
        this.defensa = defensa;
    }

    public void setVelocidad(int velocidad) {
        this.velocidad = velocidad;
    }

    public void setOro(int oro) {
        this.oro = oro;
    }

    public void setHabilidades(List<Habilidad> habilidades) {
        this.habilidades = habilidades;
    }

    public void setInventario(Inventario inventario) {
        this.inventario = inventario;
    }

    public void setEstadoActual(EstadoPersonaje estadoActual) {
        this.estadoActual = estadoActual;
    }

    // ================= TO STRING =================

    @Override
    public String toString() {

        return name + " | HP: " + vidaActual + "/" + vidaMaxima
                + " | STMN: " + energiaActual + "/" + energiaMaxima
                + " | ATK: " + danioBase
                + " | DEF: " + defensa
                + " | VEL: " + velocidad;
    }
     
}