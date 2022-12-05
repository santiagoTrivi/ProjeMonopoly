package models.cartas;

import models.Carcel;
import models.Jugador;

public class CartaIrCarcel extends Carta {
    public CartaIrCarcel() {
        super("VE DIRECTAMENTE A LA CARCEL SIN PASAR POR LA CASILLA DE SALIDA Y SIN COBRAR LOS 200$");
    }

    @Override
    public void hacer(Jugador jugador){
        Carcel.enviarACarcel(jugador);
    }
}
