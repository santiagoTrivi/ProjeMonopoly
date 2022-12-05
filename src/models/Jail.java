package models;

import java.util.ArrayList;

public class Jail {
    
    public static void sendtojail(Player jugador) {
        jugador.enCarcel = true;
        Mostrar.texto("Ahora estás en la cárcel durante los próximos turnos");
        jugador.moverA(10);
    }

    public static void carcelTurno(Player actualJugador) {
        actualJugador.turnosCarcel++;
        Mostrar.texto("Turnos en carcel: " + actualJugador.turnosCarcel);
        int comparador;
        do {
            Mostrar.menuCarcel();
            comparador = Leer.opcion(5, "Escoja una opcion");
            if(comparador == 1){
                if (actualJugador.turnosCarcel <= 3) { 
                        Dice.lanzar();
                        Mostrar.texto("Obtuviste al lanzar los dados: " + Dice.dado1 + " " + Dice.dado2 + "= " + Dice.getResultado());
                        if (Dice.isDoble()) {
                            actualJugador.enCarcel = false;
                            Mostrar.texto("Saliste de la carcel, por doble");
                            actualJugador.mover(Dice.getResultado());                            
                        } else {
                            if (actualJugador.turnosCarcel < 3) {
                                Mostrar.texto("Se cobraron 50$ y sales de la carcel");
                                actualJugador.enCarcel = false;
                                actualJugador.setDinero(-50);
                                actualJugador.mover(Dice.getResultado());
                            } else {
                                Mostrar.texto("Sigues en la carcel"); 
                            }   
                        }
                    }
            } else if(comparador == 2){
                actualJugador.setDinero(-50);
                Monopolio.turno(actualJugador);
            } else if(comparador == 3){
                if (actualJugador.cartasSalirCarcel > 0) {
                    actualJugador.enCarcel = false;
                    actualJugador.cartasSalirCarcel--;
                    Mostrar.texto("Saliste de la carcel, por tarjeta");
                } else {
                    Mostrar.texto("No posees tarjeta SALIR DE LA CARCEL GRATIS");
                }
            } else if(comparador == 4){
                actualJugador.toString();
            } else if(comparador == 5){
                ArrayList<Propertycolor> pHabitables = actualJugador.getPropiedadesHabitables();
                if (pHabitables.isEmpty()) {
                    Mostrar.texto("No posees ninguna propiedad donde puedas construir casa");
                } else {
                    Mostrar.colorPropiedad(pHabitables);
                    int opc = Leer.opcion(pHabitables.size(), "Escoje una propiedad: ");
                    Propertycolor cp = pHabitables.get(opc - 1);
                    cp.buyHouse();
                }
            }else if(comparador == 6){
                ArrayList<Property> pSinCasa = actualJugador.getPropiedadesSinCasas();
                if (pSinCasa.isEmpty()) {
                    Mostrar.texto("No posees ninguna propiedad que puedas hipotecar");
                } else {
                    Mostrar.propiedad(pSinCasa);
                    int opc = Leer.opcion(pSinCasa.size(), "Escoje una propiedad: ");
                    Property p = pSinCasa.get(opc - 1);
                    actualJugador.hipotecar(p);
                }
            }else{
                Mostrar.error("Opcion no valida");
            }
        } while (comparador != 1);
    }
}
