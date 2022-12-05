package models.cartas;

import models.Player;
import models.Tablero;

public class CartaMover extends Carta {
    private int nCasilla;

    public CartaMover(int nCasilla, String descrip){
        super(descrip);
        this.nCasilla = nCasilla;
    }

    @Override
    public void hacer(Player jugador) {
        jugador.moverA(nCasilla);
    }
}
