package circle;

public class Main {
    public static void main(String[] args) {

        javax.swing.SwingUtilities.invokeLater(() -> {
            PointCircleMove app = new PointCircleMove();
            app.setVisible(true);
        });
    }
}