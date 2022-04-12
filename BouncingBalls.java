import javax.swing.*;
import java.awt.*;

class BouncingBalls extends Canvas {

    private Ball balls[] = new Ball[4];

    public BouncingBalls() {

        Ball ball1 = new Ball(0, 0);
        Ball ball2 = new Ball(100,200);
        Ball ball3 = new Ball(150, 50);
        Ball ball4 = new Ball(50, 150);

        //Creating an own thread for each ball -> built in method from Runnable interface
        Thread x = new Thread(ball1);
        Thread y = new Thread(ball2);
        Thread z = new Thread(ball3);
        Thread v = new Thread(ball4);

        //Start() method calls Run()method
        x.start();
        y.start();
        z.start();
        v.start();

        balls[0] = ball1;
        balls[1] = ball2;
        balls[2] = ball3;
        balls[3] = ball4;
    }

    public static void main(String[] args) {
        //Calling the constructor of start()method and creates the threads
        BouncingBalls bb = new BouncingBalls();
        //Jframe is for graphical display/ This creating the window
        JFrame f = new JFrame();
        f.add(bb);
        f.setResizable(false);
        f.setSize(400, 400);
        f.setVisible(true);

        //Refreshing loop for graphical display
        while (true) {
            try {
                Thread.sleep(20);
            } catch (InterruptedException exception) {
                System.err.println(exception.toString());
            }
            //The logic for checking if the balls are colliding / Comparing all balls with all other balls
            for (int i = 0; i < bb.balls.length; i++) {
                for (int j = i + 1; j < bb.balls.length; j++) {
                    if (bb.balls[i].isHitboxHit(bb.balls[j])) {
                        bb.balls[i].invertDirection();
                        bb.balls[j].invertDirection();
                    }
                }
            }
            // It is called every 20 millisecs which calls paint method in the background
            bb.repaint();
        }
    }

    //This method draws the balls themselves
    public void paint(Graphics gr) {
        for (Ball ball : balls) {
            gr.setColor(ball.getColor());
            gr.fillOval(ball.x, ball.y, 30, 30);
        }
    }
}