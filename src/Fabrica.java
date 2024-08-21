/*
* Acosta Chang Luis Xavier - 21170229
* Proyecto: Fabrica de autos
* Topicos avanzados de programación
* 9:00-10:00
* Dr. Clemente Gerardo Garcia
*/

import java.util.LinkedList;
import java.util.Queue;

import Nisson.Robot;

public class Fabrica {
    private Thread[][] matrizEstaciones;
    private Queue<Robot>[] availableRobots; // Una cola por cada estación
    private Queue<Robot> availableRobots2; // Una cola por cada estación

    static final int ESTACION1R = 5;
    static final int ESTACION2R = 4;
    static final int ESTACION2_1R = 2;
    static final int ESTACION3R = 3;
    static final int ESTACION4R = 3;
    static final int ESTACION5R = 2;
    static final int ESTACION6R = 5;

    static Vista vista;

    @SuppressWarnings("unchecked")
    public Fabrica(Vista vista) {
        Fabrica.vista = vista;

        matrizEstaciones = new Thread[app2.ROWS][app2.STATIONS];

        availableRobots = new Queue[app2.STATIONS];
        availableRobots2 = new LinkedList<>();

        crearRobots();

        for (int i = 0; i < app2.ROWS; i++) {
            for (int j = 0; j < app2.STATIONS; j++) {
                if (j == 1)
                    matrizEstaciones[i][j] = new Thread(new EstacionMotorTrans2(i, j, availableRobots2));
                else
                    matrizEstaciones[i][j] = new Thread(new Estaciones2(i, j, availableRobots));
            }
        }
    }

    private void crearRobots() {
        availableRobots[0] = new LinkedList<Robot>(); // Chamis y cableado
        availableRobots[1] = new LinkedList<>(); // Motor y transmision
        availableRobots[2] = new LinkedList<>(); // Carroceria
        availableRobots[3] = new LinkedList<>(); // Interiores
        availableRobots[4] = new LinkedList<>(); // Llantas
        availableRobots[5] = new LinkedList<>(); // Pruebas

        for (int i = 0; i < ESTACION1R; i++) {
            availableRobots[0].add(new Robot(00 + i, "Robot " + i + " E1", Vista.RUTA + "robot" + i + ".png"));
            availableRobots[5].add(new Robot(55 + i, "Robot " + i + " E6", Vista.RUTA + "robot" + i + ".png"));
        }
        for (int i = 0; i < ESTACION2R; i++) {
            availableRobots[1].add(new Robot(11 + i, "mRobot " + i + " E2", Vista.RUTA + "robot" + i + ".png"));
        }
        for (int i = 0; i < ESTACION5R; i++) {
            availableRobots2.add(new Robot(22 + i, "tRobot " + i + " E2", Vista.RUTA + "robot" + (i + 5) + ".png"));
            availableRobots[4].add(new Robot(44 + i, "Robot " + i + " E5", Vista.RUTA + "robot" + (i + 5) + ".png"));
        }
        for (int i = 0; i < ESTACION3R; i++) {
            availableRobots[2].add(new Robot(21 + i, "Robot " + i + " E3", Vista.RUTA + "robot" + i + ".png"));
            availableRobots[3].add(new Robot(33 + i, "Robot " + i + " E4", Vista.RUTA + "robot" + i + ".png"));
        }
    }

    public Thread[][] getMatrizEstaciones() {
        return matrizEstaciones;
    }
}