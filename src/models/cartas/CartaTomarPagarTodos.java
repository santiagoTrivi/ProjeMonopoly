package models.cartas;

import java.util.ArrayList;
import models.Player;

public class CartaTomarPagarTodos extends CartaTomarPagar {
    private ArrayList<Player> jugadores;
    private int cant;

    public CartaTomarPagarTodos(ArrayList<Player> jugadores, int cant, String descrip){
        super(cant, crearMsj(cant, descrip));
        this.cant = cant;
        this.jugadores = jugadores;
    }

    protected static String crearMsj(int cant, String descrip) {
        return CartaTomarPagar.crearTomarPagar(cant, descrip) + ((cant > 0) ? " DE TODOS LOS JUGADORES" : " A TODOS LOS JUGADORES");
    }

    @Override
    public void hacer(Player jugador) {
        jugador.setDinero(cant * jugadores.size());
        for(Player j : jugadores){
            j.setDinero(cant);
        }
    }
}
