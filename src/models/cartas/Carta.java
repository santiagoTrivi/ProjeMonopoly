package models.cartas;

import models.Jugador;
import models.Mostrar;

public abstract class Carta {
    String description;

    public Carta(String description){
        this.description = description;
    }

    public void usar(Jugador jugador){
        Mostrar.carta(this.description);
        hacer(jugador);
    }

    public abstract void hacer(Jugador jugador);
}