
/*
* Acosta Chang Luis Xavier - 21170229
* Proyecto: Fabrica de autos
* Topicos avanzados de programación
* 9:00-10:00
* Dr. Clemente Gerardo Garcia
*/
import Rutinas.*;

import java.util.Queue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

import Nisson.*;

public class Estaciones2 implements Runnable {

    static final int CARS = 100;

    static protected Semaforo[][] stationReady;

    protected final int ROW, STATION;
    static protected Queue<Robot>[] availableRobots;
    static protected Semaforo[] stationRobots;

    static protected Semaforo queue;
    static protected Semaforo carSem;
    static protected AtomicBoolean[] isStation2Completed;

    static protected AtomicInteger carInProduction;
    static protected AtomicInteger ProcessedCars;
    static protected int[][] car;

    static protected int[] StationTime;

    public Estaciones2(int ROW, int station, Queue<Robot>[] availableRobots) {
        this.ROW = ROW;
        this.STATION = station;

        if (stationReady == null) {
            Estaciones2.availableRobots = availableRobots;
            init();
            carInProduction = new AtomicInteger(0);
            ProcessedCars = new AtomicInteger(0);
        }
    }

    private void init() {
        carSem = new Semaforo(1);
        queue = new Semaforo(1);
        isStation2Completed = new AtomicBoolean[app2.ROWS];
        for (int i = 0; i < app2.ROWS; i++) {
            isStation2Completed[i] = new AtomicBoolean(true);
        }
        car = new int[app2.ROWS][app2.STATIONS];
        StationTime = new int[] { 20, 6, 10, 5, 5, 10 }; // Tiempos de cada estación en segundos

        stationReady = new Semaforo[app2.ROWS][app2.STATIONS];

        for (int i = 0; i < app2.ROWS; i++) {
            stationReady[i][0] = new Semaforo(1);
            car[i][0] = -1;
        }

        for (int i = 0; i < app2.ROWS; i++) {
            for (int j = 1; j < app2.STATIONS; j++) {
                car[i][j] = -1;
                stationReady[i][j] = new Semaforo(0);
            }
        }

        stationRobots = new Semaforo[app2.STATIONS];

        stationRobots[0] = new Semaforo(Fabrica.ESTACION1R);
        stationRobots[1] = new Semaforo(Fabrica.ESTACION2R);
        stationRobots[2] = new Semaforo(Fabrica.ESTACION3R);
        stationRobots[3] = new Semaforo(Fabrica.ESTACION4R);
        stationRobots[4] = new Semaforo(Fabrica.ESTACION5R);
        stationRobots[5] = new Semaforo(Fabrica.ESTACION6R);
    }

    protected void robotWork() throws InterruptedException {
        queue.espera();
        Robot robot = availableRobots[STATION].remove();
        queue.libera();

        Fabrica.vista.actualizarLinea(car[ROW][STATION], STATION, ROW, robot.getImg(), robot.getId(), false);
        Thread.sleep(StationTime[STATION] * 250);

        queue.espera();
        availableRobots[STATION].add(robot);
        queue.libera();
    }

    protected void work() throws InterruptedException {
        if (STATION == 0 && !isStation2Completed[ROW].get())
            return;

        if (STATION != 0)
            stationReady[ROW][STATION].espera();

        stationRobots[STATION].espera();

        if (STATION == 0) {
            ProcessedCars.getAndIncrement();
            carSem.espera();
            car[ROW][STATION] = carInProduction.getAndIncrement();
            carSem.libera();
        }

        robotWork();

        stationRobots[STATION].libera();

        if (STATION == 5) {
            // System.out.println("Carro " + car[ROW][STATION] + " terminado");
            Fabrica.vista.ocultar(ROW, STATION);
        } else {
            car[ROW][STATION + 1] = car[ROW][STATION];

            isStation2Completed[ROW].set(false);

            stationReady[ROW][STATION + 1].libera();
        }
    }

    @Override
    public void run() {
        while (true) {
            try {
                if (STATION == 0) {
                    if (ProcessedCars.get() > CARS) {
                        System.out.println("Producción terminada");
                        return;
                    }
                }

                work();

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

}
