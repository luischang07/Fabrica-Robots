
/*
 * Acosta Chang Luis Xavier - 21170229
 * Proyecto: Fabrica de autos
 * Topicos avanzados de programación
 * 9:00-10:00
 * Dr. Clemente Gerardo Garcia
 */
import java.util.Queue;

import Nisson.Robot;
import Rutinas.Semaforo;

public class EstacionMotorTrans extends Estaciones {

    static private Semaforo station2_1Robots;
    static private Queue<Robot> availableRobots2;
    static private int time2_1;

    public EstacionMotorTrans(int row, int station, Queue<Robot> availableRobots2) {
        super(row, station, availableRobots);
        if (station2_1Robots == null) {
            EstacionMotorTrans.availableRobots2 = availableRobots2;
            station2_1Robots = new Semaforo(Fabrica.ESTACION2_1R);
            time2_1 = 4;
        }

    }

    @Override
    protected void robotWork() throws InterruptedException {
        queue.espera();
        Robot robot = availableRobots2.remove();
        queue.libera();

        Fabrica.vista.actualizarLinea(car[ROW][STATION], STATION, ROW, robot.getImg(), robot.getId(), true);
        Thread.sleep(time2_1 * 250);

        queue.espera();
        availableRobots2.add(robot);
        queue.libera();
    }

    @Override
    protected void work() throws InterruptedException {
        if (STATION != 5) {
            if (stationReady[ROW][STATION + 1].get()) {
                return;
            }
        }
        if (!stationReady[ROW][STATION].get())
            return;

        stationRobots[STATION].espera();

        super.robotWork();

        station2_1Robots.espera();
        stationRobots[STATION].libera();

        robotWork();

        station2_1Robots.libera();

        car[ROW][STATION + 1] = car[ROW][STATION];

        stationReady[ROW][STATION + 1].set(true);

        stationReady[ROW][STATION].set(false);
    }

    @Override
    public void run() {
        while (true) {
            try {
                work();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

    }

}
