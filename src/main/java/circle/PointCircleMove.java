package circle;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class PointCircleMove extends JFrame {
    // розміри вікна
    private static final int WIDTH = 500;
    private static final int HEIGHT = 400;

    // координати центру кола, по якому рухатиметься точка
    private int centerX = WIDTH / 2;
    private int centerY = HEIGHT / 2;
    private int radius = 100;  // початкове значення радіусу
    private double angle = 0;  // початкове значення кута
    private double angularSpeed = 0.05;  // початкова кутова швидкість

    // конструктор
    public PointCircleMove() {
        setTitle("Рух по колу");
        setSize(WIDTH, HEIGHT);
        setResizable(false);
        setBackground(Color.LIGHT_GRAY);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // створюємо панель
        JPanel panel = new JPanel() {
            // перевизначаємо метод paintComponent для малювання компонента точки
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g); // очищаємо вікно
                Graphics2D g2d = (Graphics2D) g;
                g2d.setColor(Color.BLUE);
                // вираховуємо x та y точки використовуючи формули полярної системи координат
                int x = (int) (centerX + radius * Math.cos(angle));
                int y = (int) (centerY + radius * Math.sin(angle));

                /* малюємо заповнене коло (точку) розміром 14х14 пікселів
                та з координатами (x-7, y-7), віднімаючи половину розміру точки,
                щоб вирівняти точно по центру лінії руху.
                */
                g2d.fillOval(x - 7, y - 7, 14, 14);
            }
        };
        panel.setBackground(Color.LIGHT_GRAY);

        // додаємо обробник подій при натиску на кнопки миші
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

        /* створюємо таймер, що оновлює положення точки на колі кожні 16 мілісекунд та
        викликаємо метод repaint(), щоб панель перемалювалась. */
        Timer timer = new Timer(16, e -> {
            angle += angularSpeed;
            panel.repaint();
        });
        timer.start();

        // додаємо панель на вікно
        add(panel);
    }

}
