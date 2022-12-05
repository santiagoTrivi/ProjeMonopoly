package models.cartas;

import models.Player;

public class CartaSalirCarcel extends Carta {
    public CartaSalirCarcel() {
        super("SALIR DE LA CARCEL GRATIS");
    }
    
    @Override
    public void hacer(Player jugador) {
        jugador.cartasSalirCarcel++;
    }
}
