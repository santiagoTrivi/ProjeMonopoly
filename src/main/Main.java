package main;

import models.*;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<Jugador> jugadores;
        boolean salir = false;
        int op = 0;
        
        do{
            Mostrar.menuPrincipal();
            op = Leer.opcion(3, "Elija la opcion a realizar: ");
            
            if(op == 1){
                jugadores = Monopolio.crearJugadores(4);
                Tablero.setJugadores(jugadores);
                Tablero.iniciar();
                do {
                    for (int i = 0; i < jugadores.size(); i++) {
                        Jugador actualJugador = jugadores.get(i);
                        Monopolio.turno(actualJugador);
                    }
                } while (!Monopolio.getTermino());
            } else if(op == 2){
                Mostrar.reglasBasicas();
            } else if(op == 3){
                salir = true;
                Mostrar.salida();
            }
            
            
            /*
             
            switch (op) {
                case 1:
                    jugadores = Monopolio.crearJugadores(4);
                    Tablero.setJugadores(jugadores);
                    Tablero.iniciar();
                    do {
                        for (int i = 0; i < jugadores.size(); i++) {
                            Jugador actualJugador = jugadores.get(i);
                            Monopolio.turno(actualJugador);
                        }
                    } while (!Monopolio.getTermino());
                    break;
                case 2:
                    Mostrar.reglasBasicas();
                    break;
                case 3:
                    salir = true;
                    Mostrar.salida();
                    break;
                default:
            }
    */
        }while(!salir); 
    }
} 

        

    

