/*
* Acosta Chang Luis Xavier - 21170229
* Proyecto: Fabrica de autos
* Topicos avanzados de programación
* 9:00-10:00
* Dr. Clemente Gerardo Garcia
*/
package Nisson;

public class Robot {

    private int id;
    private String name;
    private String img;

    public Robot(int id, String name, String img) {
        this.id = id;
        this.name = name;
        this.img = img;
    }

    public String work() {
        return name + " is working";
    }

    public void display() {
        System.out.println("Robot ID: " + id);
        System.out.println("Robot Name: " + name);
        System.out.println("Robot Image: " + img);
    }

    public String getName() {
        return name;
    }

    public String getImg() {
        return img;
    }

    public int getId() {
        return id;
    }
}
