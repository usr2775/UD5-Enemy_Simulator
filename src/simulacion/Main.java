package simulacion;

import simulacion.abstractas.*;
import simulacion.mobs.hostiles.*;
import simulacion.mobs.pacificos.*;
import simulacion.personajes.*;

import java.util.*;


import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        System.out.println("Bienvenido al juego");

        // Pregunta al usuario qué arma llevará el personaje en esta ronda
        System.out.println("¿Qué arma llevará el personaje en esta ronda?");
        System.out.println("1. Ninguna");
        System.out.println("2. Espada de madera");
        System.out.println("3. Espada de hierro");
        System.out.println("4. Espada de diamante");

        Scanner scanner = new Scanner(System.in);
        int opcionArma = scanner.nextInt();
        int fuerza = 1;

        switch (opcionArma) {
            case 2:
                fuerza = 2;
                break;
            case 3:
                fuerza = 3;
                break;
            case 4:
                fuerza = 5;
                break;
        }

        System.out.println("¿Qué armadura llevará el personaje en esta ronda?");
        System.out.println("1. Ninguna");
        System.out.println("2. De cuero");
        System.out.println("3. De hierro");
        System.out.println("4. De diamante");

        int opcionArmadura = scanner.nextInt();
        int defensa = 0; 

        switch (opcionArmadura) {
            case 2:
                defensa = 1;
                break;
            case 3:
                defensa = 2;
                break;
            case 4:
                defensa = 3;
                break;
        }

        Personaje personaje = new Personaje(defensa, fuerza);

        MobHostil[] enemigos = {new Zombie(), new Enderman(), new DragonChiwi()};
        Random random = new Random();
        int turno = 1;
        boolean combateActivo = true;

        while (combateActivo) {
            System.out.println("Turno " + turno + ":");

            int enemigoIndex = random.nextInt(enemigos.length);
            MobHostil enemigo = enemigos[enemigoIndex];
            enemigo.recibirAtaque(personaje.atacar());

            if (enemigo.getSalud() <= 0) {
                System.out.println("Has derrotado al enemigo.");
                combateActivo = false;
                break;
            }

            int accionEnemigo = random.nextInt(2);
            if (accionEnemigo == 0) {
                personaje.recibirAtaque(enemigo.atacar());
            } else {
                enemigo.moverse();
            }

            if (personaje.getSalud() <= 0) {
                System.out.println("Has sido derrotado por el enemigo.");
                combateActivo = false;
            }

            turno++;
        }
    }
}

