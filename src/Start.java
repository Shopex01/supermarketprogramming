import java.awt.*;
import GUI.Frontend;

public class Start {
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                Frontend frame = new Frontend();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
