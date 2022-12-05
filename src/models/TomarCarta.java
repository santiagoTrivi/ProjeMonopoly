package models;

public class TomarCarta extends Casilla {
    private Baraja baraja;
    
    public TomarCarta() {
        super();
    }

    public TomarCarta(int pos, String nombre, Baraja baraja){
        super(pos, nombre);
        this.baraja = baraja;
    }
    
    @Override
    public void hacer(Jugador actualJugador) {
        baraja.tomarCarta(actualJugador);
    }
}
