package models;

import java.util.Random;

public class Dice {
    static Random random = new Random();
    public static int dado1, dado2;
    
    public static void lanzar() {
        dado1 = random.nextInt(6) + 1;
        dado2 = random.nextInt(6) + 1;
    }
    
    public static int getResultado() {
        return dado1 + dado2;
    }
    
    public static boolean isDoble() {
        return dado1 == dado2;
    }
}
