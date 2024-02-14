package circle;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class PointCircleMove extends JFrame {
    // розміри вікна
    private static final int WIDTH = 500;
    private static final int HEIGHT = 400;

    // координати центру, початкові значення радіусу, кута й швидкості
    private int centerX = WIDTH / 2;
    private int centerY = HEIGHT / 2;
    private int radius = 100;
    private double angle = 0;
    private double angularSpeed = 0.05;

    // конструктор
    public PointCircleMove() {
        setTitle("Рух по колу");
        setSize(WIDTH, HEIGHT);
        setResizable(false);
        setBackground(Color.LIGHT_GRAY);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // створення панелі
        JPanel panel = new JPanel() {
            @Override
            // малювання компонента точки
            protected void paintComponent(Graphics g) {
                super.paintComponent(g); // очищаємо вікно
                Graphics2D g2d = (Graphics2D) g;
                g2d.setColor(Color.BLUE);
                // вираховуємо координати
                int x = (int) (centerX + radius * Math.cos(angle));
                int y = (int) (centerY + radius * Math.sin(angle));
                g2d.fillOval(x - 7, y - 7, 14, 14);
            }
        };
        panel.setBackground(Color.LIGHT_GRAY);

        // додавання обробника подій при натиску на кнопки миші
        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                // ліва - пришвидшує, права - сповільнює
                if (e.getButton() == MouseEvent.BUTTON1) {
                    angularSpeed += 0.01;
                } else if (e.getButton() == MouseEvent.BUTTON3) {
                    angularSpeed -= 0.01;
                    if (angularSpeed < 0) {
                        angularSpeed = 0;
                    }
                }
            }
        });

        // виконання події е кожні 16 мілісекунд
        Timer timer = new Timer(16, e -> {
            angle += angularSpeed;
            panel.repaint();
        });
        timer.start();

        // додавання панелі на вікно
        add(panel);
    }

}