package models;

public class Property extends Casilla {
    public final int precio;
    public Player propietario;
    public int valorHipoteca;
    public boolean hipotecada;
    
    public Property(){
        super();
        this.precio = 0;
        this.valorHipoteca = 0;
        this.hipotecada = false;
    }
    
    public Property(int pos, String nombre, int precio, int hipo){
        super(pos, nombre);
        this.precio = precio;
        this.valorHipoteca = hipo;
        this.hipotecada = false;
    }

    public int getPrecio(){
        return this.precio;
    }

    public Player getPropietario() {
        return this.propietario;
    }
    
    public void setPropietario(Player jugador) {
        this.propietario = jugador;
    }
    
    public boolean isHipotecada() {
        return this.hipotecada;
    }

    public int getValorHipoteca() {
        return this.valorHipoteca;
    }
    
    public void comprado(Player actualJugador) {
        setPropietario(actualJugador);
        actualJugador.comprar(this);
    }
    
    public void ofertar(Player actualJugador){
        Mostrar.texto("Te gustaria comprar esta propiedad: " + name + " por " + precio + "$");
        int op = Leer.opcion(2, "1. Si\t\t2. No ");
        if(op == 1){
            comprado(actualJugador);
        }
    }

    @Override
    public void hacer(Player actualJugador) {}
}
