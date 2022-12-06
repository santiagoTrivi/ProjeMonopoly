package models;

import java.util.ArrayList;

public class Monopolio {
    
    public static final String[] FICHAS = new String[] {"PLANCHA", "CARRO", "ZAPATO", "CARRETILLA"};
    
    public static ArrayList<Player> crearJugadores(int nJugadores){
        ArrayList<Player> jugadores = new ArrayList<>();
        Player nuevo = new Player();
        String[] disponibles = FICHAS;
        for (int i = 1; i <= nJugadores; i++) {
            boolean fracaso = true;
            do {
                Mostrar.crearJuador(i, disponibles);
                int op = Leer.opcion(4, "Elija una ficha: ");
                if (op != -1) {
                    if (disponibles[op - 1] != null ) {
                        nuevo = new Player(FICHAS[op - 1]);
                        disponibles[op - 1] = null;
                        jugadores.add(nuevo);
                        fracaso = false;
                    }
                }
            } while (fracaso);
        }
        return jugadores;
    }
    
    public static void turno(Player actualJugador){
        int nDobles = 0;
        Mostrar.texto(actualJugador.getNombre() + " es tu turno!");
        if (actualJugador.enCarcel) {
            Jail.carcelTurno(actualJugador);
        } 
        if (!actualJugador.enCarcel) {
            int op;
            do {
                Mostrar.menuUsuario();
                op = Leer.opcion(5, "Escoja una opcion: ");
                switch (op) {
                    case 1:
                        Mostrar.texto("Posicion: " + Tablero.getActualCasilla(actualJugador).getName());
                        do{
                            nDobles++;
                            Dice.lanzar();
                            Mostrar.texto("Obtuviste al lanzar los dados: " + Dice.dado1 + " + " + Dice.dado2 + " = " + Dice.getResultado());
                            actualJugador.mover(Dice.getResultado());
                            if (nDobles == 3){
                                Jail.sendtojail(actualJugador);
                            }
                        } while ((nDobles < 3) && (Dice.isDoble()));
                        break;
                    case 2:
                        Mostrar.texto(actualJugador.toString());
                        break;
                    case 3:
                        ArrayList<Propertycolor> pHabitables = actualJugador.getPropiedadesHabitables();
                        if (pHabitables.isEmpty()) {
                            Mostrar.texto("No posees ninguna propiedad donde puedas construir casa");
                        } else {
                            Mostrar.colorPropiedad(pHabitables);
                            int opc = Leer.opcion(pHabitables.size(), "Escoje una propiedad: ");
                            Propertycolor cp = pHabitables.get(opc - 1);
                            cp.buyHouse();
                        }
                        break;
                    case 4:
                        ArrayList<Property> pSinCasa = actualJugador.getPropiedadesSinCasas();
                        if (pSinCasa.isEmpty()) {
                            Mostrar.texto("No posees ninguna propiedad que puedas hipotecar");
                        } else {
                            Mostrar.propiedad(pSinCasa);
                            int opc = Leer.opcion(pSinCasa.size(), "Escoje una propiedad: ");
                            Property p = pSinCasa.get(opc - 1);
                            actualJugador.hipotecar(p);
                        }
                        break;
                }
            } while (op != 1);
        }
            
            
            
        }

    public static boolean getTermino() {
        boolean[] termino = new boolean[] {false, false, false, false};
        int enBancarrota = 0;
        ArrayList<Player> jugadores;
        jugadores = Tablero.getJugadores();
        for (int i = 0; i < jugadores.size(); i++) {
            termino[i] = jugadores.get(i).isBancarota();   
        }
        for (int i = 0; i < termino.length; i++) {
           if (termino[i]) {
               enBancarrota++;
           }  
        }
        return enBancarrota >= 3;
    }
}
