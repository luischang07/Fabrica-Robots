
/*
* Acosta Chang Luis Xavier - 21170229
* Proyecto: Fabrica de autos
* Topicos avanzados de programación
* 9:00-10:00
* Dr. Clemente Gerardo Garcia
*/
import javax.swing.*;
//para Diseño
import com.formdev.flatlaf.FlatClientProperties;

import Rutinas.Rutinas;

import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

public class Vista extends JFrame implements ComponentListener {

    private JPanel pnlEstaciones;
    private JLabel[] lblEstaciones;

    private JPanel pnlLineas;
    private JPanel[] pnlLineasCarros;
    private JLabel[] lblLineas;

    private JPanel pnlCarrosRobot;
    private JLabel[][] lblCarrosRobot;
    private JLabel[][] lblnAuto;

    private ImageIcon chasis, carroceria, interiores, llantas, prueba, motor, transmision;

    public Vista() {
        super("Linea de produccion");
        HazInterfaz();
        addComponentListener(this);
    }

    private void HazInterfaz() {
        setMinimumSize(new Dimension(700, 500));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setSize(1350, 800);
        setLocationRelativeTo(null);

        pnlEstaciones = createStyledPanel(new GridLayout());
        add(pnlEstaciones);

        lblEstaciones = new JLabel[] {
                createLabel("Chasis y cableado", SwingConstants.CENTER),
                createLabel("Motor/Transmision", SwingConstants.CENTER),
                createLabel("Carroceria", SwingConstants.CENTER),
                createLabel("Interiores", SwingConstants.CENTER),
                createLabel("Llantas", SwingConstants.CENTER),
                createLabel("Pruebas", SwingConstants.CENTER)
        };

        pnlLineas = createStyledPanel(new GridLayout(app2.ROWS, 1, 1, 10));
        add(pnlLineas);

        lblLineas = new JLabel[app2.ROWS];
        pnlLineasCarros = new JPanel[app2.ROWS];

        for (int i = 0; i < pnlLineasCarros.length; i++) {
            lblLineas[i] = createLabel("Linea " + (i + 1), SwingConstants.CENTER);
            pnlLineasCarros[i] = createStyledPanel(new GridLayout(2, 0));
            pnlLineasCarros[i].add(lblLineas[i]);
            pnlLineas.add(pnlLineasCarros[i]);
        }

        for (JLabel lblEstacion : lblEstaciones) {
            pnlEstaciones.add(lblEstacion);
        }

        pnlCarrosRobot = new JPanel(new GridLayout(app2.ROWS, 18));
        add(pnlCarrosRobot);

        lblCarrosRobot = new JLabel[app2.ROWS][app2.STATIONS * 2];
        lblnAuto = new JLabel[app2.ROWS][app2.STATIONS];

        for (int i = 0; i < lblCarrosRobot.length; i++) {
            for (int j = 0; j < lblCarrosRobot[i].length; j++) {
                lblCarrosRobot[i][j] = new JLabel();
                lblCarrosRobot[i][j].setVisible(false);
                lblCarrosRobot[i][j].setBorder(BorderFactory.createLineBorder(Color.black));

                if (j % 2 == 0) {
                    lblnAuto[i][j / 2] = createLabel("", SwingConstants.CENTER);
                    pnlCarrosRobot.add(lblnAuto[i][j / 2]);
                }
                pnlCarrosRobot.add(lblCarrosRobot[i][j]);
            }
        }
        pnlCarrosRobot.setBounds((int) (pnlLineas.getWidth() + pnlLineas.getX()),
                (int) (pnlEstaciones.getHeight() + pnlEstaciones.getY()), (int) (this.getWidth() * .9),
                (int) (this.getHeight() * .8));

        ajustarImagenes();
        for (int j = 0; j < lblCarrosRobot.length; j++) {
            lblCarrosRobot[j][0].setIcon(chasis);
            lblCarrosRobot[j][4].setIcon(carroceria);
            lblCarrosRobot[j][6].setIcon(interiores);
            lblCarrosRobot[j][8].setIcon(llantas);
            lblCarrosRobot[j][10].setIcon(prueba);
        }
    }

    private JLabel createLabel(String text, int alignment) {
        JLabel label = new JLabel(text);
        label.setHorizontalAlignment(alignment);
        return label;
    }

    private JPanel createStyledPanel(LayoutManager layout) {
        JPanel panel = new JPanel(layout);
        panel.putClientProperty(FlatClientProperties.STYLE, "background:$Menu.background;arc:10");
        return panel;
    }

    public void actualizarLinea(int auto, int estacion, int linea, String robotImg, int robotID,
            boolean esTransmision) {

        lblnAuto[linea][estacion].setText("A: " + auto
                + " R: " + robotID);

        if (estacion == 0) {
            lblCarrosRobot[linea][estacion * 2 + 1].setIcon(AjustarImagen(robotImg));
            lblCarrosRobot[linea][estacion * 2].setVisible(true);
            lblCarrosRobot[linea][estacion * 2 + 1].setVisible(true);
            return;
        }

        ocultar(linea, (estacion - 1));

        // Muestra la imagen en la estación actual
        if (!esTransmision && estacion == 1) {
            lblCarrosRobot[linea][estacion * 2].setIcon(motor);
            lblCarrosRobot[linea][estacion * 2 + 1].setIcon(AjustarImagen(robotImg));

            lblCarrosRobot[linea][estacion * 2].setVisible(true);
            lblCarrosRobot[linea][estacion * 2 + 1].setVisible(true);
        } else if (esTransmision && estacion == 1) {
            lblCarrosRobot[linea][estacion * 2].setIcon(transmision);
            lblCarrosRobot[linea][estacion * 2 + 1].setIcon(AjustarImagen(robotImg));

            lblCarrosRobot[linea][estacion * 2].setVisible(true);
            lblCarrosRobot[linea][estacion * 2 + 1].setVisible(true);
        } else {
            lblCarrosRobot[linea][estacion * 2 + 1].setIcon(AjustarImagen(robotImg));

            lblCarrosRobot[linea][estacion * 2].setVisible(true);
            lblCarrosRobot[linea][estacion * 2 + 1].setVisible(true);
        }
    }

    public void ocultar(int linea, int estacion) {
        lblnAuto[linea][estacion].setText("");
        lblCarrosRobot[linea][estacion * 2].setVisible(false);
        lblCarrosRobot[linea][estacion * 2 + 1].setVisible(false);
    }

    @Override
    public void componentResized(ComponentEvent e) {
        pnlEstaciones.setBounds((int) (this.getWidth() * .10), (int) (this.getHeight() * .01),
                (int) (this.getWidth() * .9), (int) (this.getHeight() * .10));

        pnlLineas.setBounds((int) (this.getWidth() * 0.005), (int) (pnlEstaciones.getHeight() + pnlEstaciones.getY()),
                (int) (pnlEstaciones.getX() - (int) (this.getWidth() * 0.005)), (int) (this.getHeight() * .8));

        pnlCarrosRobot.setBounds((int) (pnlLineas.getWidth() + pnlLineas.getX()),
                (int) (pnlEstaciones.getHeight() + pnlEstaciones.getY()), (int) (this.getWidth() * .9),
                (int) (this.getHeight() * .8));

        ajustarImagenes();
    }

    private ImageIcon AjustarImagen(String robot) {
        int w = pnlCarrosRobot.getWidth() / 18;
        int h = (int) (pnlCarrosRobot.getHeight() / app2.ROWS);

        return Rutinas.AjustarImagen(robot, w, h);
    }

    private void ajustarImagenes() {
        int w = pnlCarrosRobot.getWidth() / 18;
        int h = (int) (pnlCarrosRobot.getHeight() / app2.ROWS);

        chasis = Rutinas.AjustarImagen("chasis.jpg", w, h);
        carroceria = Rutinas.AjustarImagen("carroceria.jpg", w, h);
        interiores = Rutinas.AjustarImagen("interiores.jpg", w, h);
        llantas = Rutinas.AjustarImagen("llantas.jpg", w, h);
        prueba = Rutinas.AjustarImagen("pruebas.jpg", w, h);
        motor = Rutinas.AjustarImagen("motor.jpg", w, h);
        transmision = Rutinas.AjustarImagen("transmision.jpg", w, h);
    }

    @Override
    public void componentMoved(ComponentEvent e) {
    }

    @Override
    public void componentShown(ComponentEvent e) {
    }

    @Override
    public void componentHidden(ComponentEvent e) {
    }

}
