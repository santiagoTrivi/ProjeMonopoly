package models;

public class Impuesto extends Casilla {
    private int impuesto;

    public Impuesto(int pos, String n, int impuesto){
        super(pos,n);
        this.impuesto = impuesto;
    }

    @Override
    public void hacer(Jugador jugador) {
        jugador.setDinero(-this.impuesto);
    }
}
