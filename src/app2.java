
// Para Diseño
import javax.swing.UIManager;
import java.awt.Font;

import com.formdev.flatlaf.FlatLightLaf;
import com.formdev.flatlaf.fonts.roboto.FlatRobotoFont;

import Rutinas.Rutinas;

public class app2 {
    static final int ROWS = Rutinas.nextInt(15, 15);
    static final int STATIONS = 6;

    public static void main(String[] args) {
        // para Diseño
        // -----------------------------------------------------------------------
        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
        } catch (Exception ex) {
        }
        FlatRobotoFont.install();
        UIManager.put("defaultFont", new Font(FlatRobotoFont.FAMILY, Font.BOLD, 14));
        FlatLightLaf.setup();
        // ------------------------------------------------------------------------------------

        Vista vista = new Vista();
        vista.setVisible(true);

        Fabrica fabrica = new Fabrica(vista);

        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < STATIONS; j++) {
                fabrica.getMatrizEstaciones()[i][j].start();
            }
        }

    }

}
