/*
* Acosta Chang Luis Xavier - 21170229
* Proyecto: Fabrica de autos
* Topicos avanzados de programación
* 9:00-10:00
* Dr. Clemente Gerardo Garcia
*/

package Nisson;

public class myQueue {
    private int max;
    private int front;
    private int rear;
    private int nItems;
    private Robot[] robots;

    public myQueue(int s) {
        max = s;
        robots = new Robot[max];
        front = 0;
        rear = -1;
        nItems = 0;
    }

    public void push(Robot j) {
        if (rear == max - 1) {
            rear = -1;
        }
        robots[++rear] = j;
        nItems++;
    }

    public Robot pop() {
        Robot temp = robots[front++];
        if (front == max) {
            front = 0;
        }
        nItems--;
        return temp;
    }

    public Robot peekFront() {
        return robots[front];
    }

    public boolean isEmpty() {
        return (nItems == 0);
    }

    public boolean isFull() {
        return (nItems == max);
    }

    public int size() {
        return nItems;
    }
}
