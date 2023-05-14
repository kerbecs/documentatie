import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.LinkedList;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class SnakeGame extends JPanel implements KeyListener, Runnable {
    private static final long serialVersionUID = 1L;
    private static final int WIDTH = 300;
    private static final int HEIGHT = 300;
    private static final int SIZE = 10;
    private static final int SPEED = 100;

    private LinkedList<Point> snake;
    private Point fruit;
    private int direction;
    private boolean running;
    private Random random;

    public SnakeGame() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        addKeyListener(this);
        setFocusable(true);

        snake = new LinkedList<>();
        random = new Random();
        direction = KeyEvent.VK_RIGHT;
        running = true;
        fruit = new Point(random.nextInt(WIDTH / SIZE) * SIZE, random.nextInt(HEIGHT / SIZE) * SIZE);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, WIDTH, HEIGHT);

        g.setColor(Color.WHITE);
        for (Point p : snake) {
            g.fillRect(p.x, p.y, SIZE, SIZE);
        }

        g.setColor(Color.RED);
        g.fillOval(fruit.x, fruit.y, SIZE, SIZE);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_LEFT && direction != KeyEvent.VK_RIGHT) {
            direction = key;
        } else if (key == KeyEvent.VK_RIGHT && direction != KeyEvent.VK_LEFT) {
            direction = key;
        } else if (key == KeyEvent.VK_UP && direction != KeyEvent.VK_DOWN) {
            direction = key;
        } else if (key == KeyEvent.VK_DOWN && direction != KeyEvent.VK_UP) {
            direction = key;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void run() {
        while (running) {
            Point head = snake.peekFirst();
            Point newPoint = new Point(head.x, head.y);

            if (direction == KeyEvent.VK_LEFT) {
                newPoint.x -= SIZE;
            } else if (direction == KeyEvent.VK_RIGHT) {
                newPoint.x += SIZE;
            } else if (direction == KeyEvent.VK_UP) {
                newPoint.y -= SIZE;
            } else if (direction == KeyEvent.VK_DOWN) {
                newPoint.y += SIZE;
            }

            // Check if the snake has collided with the fruit
            if (newPoint.x == fruit.x && newPoint.y == fruit.y) {
                snake.add(newPoint);
                fruit = new Point(random.nextInt(WIDTH / SIZE) * SIZE, random.nextInt(HEIGHT / SIZE) * SIZE);
            } else {
                snake.removeLast();
                snake.addFirst(newPoint);
            }

            // Check if the snake has collided with itself or the walls
            if (newPoint.x < 0 || newPoint.x >= WIDTH || newPoint.y < 0 || newPoint.y >= HEIGHT || snake.indexOf(newPoint) != snake.size() - 1) {
                running = false;
            }

            repaint();

            try {
                Thread.sleep(SPEED);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Snake Game");
        SnakeGame game = new SnakeGame();
        frame.add(game);
        frame.pack();
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        new Thread(game).start();
    }
}

class Point {
    int x, y;
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
