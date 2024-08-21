
/*
* Acosta Chang Luis Xavier - 21170229
* Proyecto: Fabrica de autos
* Topicos avanzados de programación
* 9:00-10:00
* Dr. Clemente Gerardo Garcia
*/
import java.awt.*;

class NissonLayout implements LayoutManager {

    int noLineas;

    public NissonLayout(int noLineas) {
        this.noLineas = noLineas;
    }

    @Override
    public void addLayoutComponent(String name, Component comp) {

    }

    @Override
    public void removeLayoutComponent(Component comp) {

    }

    @Override
    public void layoutContainer(Container miContenedor) {
        int nComponentes = miContenedor.getComponentCount();

        int x = 0;
        int y = 0;

        for (int i = 0; i < nComponentes; i++) {
            Component componente = miContenedor.getComponent(i);

            // Calcular el ancho y alto del componente basado en el tamaño del contenedor
            int anchoComponente = (int) (miContenedor.getWidth() / 18);
            int altoComponente = (int) (miContenedor.getHeight() / noLineas);

            // Ajustar la posición x para cada cuarto componente
            if (i % 4 == 0 && i > 0) {
                x += (int) (miContenedor.getWidth() * .01);
            }

            // Establecer la posición y tamaño del componente
            componente.setBounds(x, y, anchoComponente, altoComponente);

            // Avanzar la posición x para el próximo componente
            x += anchoComponente;

            // Reiniciar x y avanzar y para cada 18 componentes, excepto cada 72 componentes
            if (i % 18 == 0 && i > 0 && i % 72 != 0) {
                x = (int) (miContenedor.getWidth() * .01);
                y += altoComponente;
            }
        }
    }

    @Override
    public Dimension preferredLayoutSize(Container parent) {
        return null;
    }

    @Override
    public Dimension minimumLayoutSize(Container parent) {
        return null;
    }
}
