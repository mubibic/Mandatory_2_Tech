import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Array;
import java.util.Random;

class BouncingBalls extends JFrame {

    private Ball balls[] = new Ball[3];


    public BouncingBalls() {

        Random r = new Random();
        setResizable(false);
        setSize(400, 400);

        Ball ball1 = new Ball();
        Ball ball2 = new Ball();
        Ball ball3 = new Ball();


        //getContentPane().add(ball1);
        setVisible(true);
        Thread x = new Thread(ball1);
        Thread y = new Thread(ball2);
        Thread z = new Thread(ball3);

        x.start();
        y.start();
        z.start();

        balls[0] = ball1;
        balls[1] = ball2;
        balls[2] = ball3;

    }

    public static void main(String[] args) {
        new BouncingBalls();
    }

    public void paint(Graphics gr) {

        super.paint(gr);

        for (Ball ball : balls) {
            gr.setColor(ball.getColor());
            gr.fillOval(ball.x, ball.y, 30, 30);
        }

    }
}