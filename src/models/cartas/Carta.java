package models.cartas;

import models.Player;
import models.Mostrar;

public abstract class Carta {
    String description;

    public Carta(String description){
        this.description = description;
    }

    public void usar(Player jugador){
        Mostrar.carta(this.description);
        hacer(jugador);
    }

    public abstract void hacer(Player jugador);
}