package models.cartas;

import models.Jugador;
import models.Tablero;

public class CartaMover extends Carta {
    private int nCasilla;

    public CartaMover(int nCasilla, String descrip){
        super(descrip);
        this.nCasilla = nCasilla;
    }

    @Override
    public void hacer(Jugador jugador) {
        jugador.moverA(nCasilla);
    }
}
