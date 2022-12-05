package models;

public class Taxes extends Casilla {
    private int impuesto;

    public Taxes(int pos, String n, int impuesto){
        super(pos,n);
        this.impuesto = impuesto;
    }

    @Override
    public void hacer(Player jugador) {
        jugador.setDinero(-this.impuesto);
    }
}
