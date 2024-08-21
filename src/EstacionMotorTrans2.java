
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

public class EstacionMotorTrans2 extends Estaciones2 {

    static private Semaforo station2_1Robots;
    static private Queue<Robot> availableRobots2;
    static private int time2_1;

    public EstacionMotorTrans2(int row, int station, Queue<Robot> availableRobots2) {
        super(row, station, availableRobots);
        if (station2_1Robots == null) {
            EstacionMotorTrans2.availableRobots2 = availableRobots2;
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
        stationReady[ROW][STATION].espera();

        stationRobots[STATION].espera();

        super.robotWork();

        station2_1Robots.espera();
        stationRobots[STATION].libera();

        robotWork();

        station2_1Robots.libera();

        car[ROW][STATION + 1] = car[ROW][STATION];

        stationReady[ROW][STATION + 1].libera();

        isStation2Completed[ROW].set(true);
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
