package models;

import models.cartas.Carta;

import java.util.ArrayList;
import java.util.Collections;

public class Baraja {
    private ArrayList<Carta> baraja = new ArrayList<>();

    public void tomarCarta(Jugador jugador){
        Carta carta;
        carta = baraja.remove(0);
        ponerAbajo(carta);
        carta.usar(jugador);
    }

    public void ponerAbajo(Carta carta){
        baraja.add(carta);
    }

    public void barajear(){
        Collections.shuffle(baraja);
    }
}
