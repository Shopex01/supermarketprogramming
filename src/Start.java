import java.awt.*;
import GUI.GUI;

public class Start {
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                GUI frame = new GUI();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
