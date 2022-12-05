package models.cartas;

import models.Jail;
import models.Player;

public class CartaIrCarcel extends Carta {
    public CartaIrCarcel() {
        super("VE DIRECTAMENTE A LA CARCEL SIN PASAR POR LA CASILLA DE SALIDA Y SIN COBRAR LOS 200$");
    }

    @Override
    public void hacer(Player jugador){
        Jail.sendtojail(jugador);
    }
}
