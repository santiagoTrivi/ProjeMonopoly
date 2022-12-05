package models.cartas;

import models.Jugador;

public class CartaSalirCarcel extends Carta {
    public CartaSalirCarcel() {
        super("SALIR DE LA CARCEL GRATIS");
    }
    
    @Override
    public void hacer(Jugador jugador) {
        jugador.cartasSalirCarcel++;
    }
}
