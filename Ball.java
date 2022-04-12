import java.awt.*;
import java.util.Random;
import javax.swing.*;


class Ball implements Runnable {
    //Direction of x and y coordinates
    private boolean xIncrease, yIncrease;
    //Top left coordinate point of the ball
    public int x, y;
    //The amount/speed we increase the coordinates
    private int xDelta, yDelta;
    //Size of the frame
    private final int MAX_X = 400, MAX_Y = 360;
    //Random variable for color change when colliding
    private Random r;
    public Color clr = Color.blue;


    public Ball() {
        xIncrease = false;
        yIncrease = false;
        xDelta = 1;
        yDelta = 1;


        r = new Random();
    }

    public void run() {
        while (true) {

            try {
                Thread.sleep(10);
            } catch (InterruptedException exception) {
                System.err.println(exception.toString());
            }

            if (xIncrease) {
                x += xDelta;
            } else {
                x -= xDelta;
            }


            if (yIncrease) {
                y += yDelta;
            } else {
                y -= yDelta;
            }


            if (y <= 0) {
                yIncrease = true;
                yDelta = (int) (Math.random() * 5 + 2);
                set(new Color(r.nextInt(256), r.nextInt(256), r.nextInt(256)));

            } else if (y >= MAX_Y - 30) {
                yDelta = (int) (Math.random() * 5 + 2);
                yIncrease = false;
                set(new Color(r.nextInt(256), r.nextInt(256), r.nextInt(256)));

            }

            if (x <= 0) {
                xIncrease = true;
                xDelta = (int) (Math.random() * 5 + 2);
                set(new Color(r.nextInt(256), r.nextInt(256), r.nextInt(256)));

            } else if (x >= MAX_X - 30) {
                xIncrease = false;
                xDelta = (int) (Math.random() * 5 + 2);
                set(new Color(r.nextInt(256), r.nextInt(256), r.nextInt(256)));

            }
        }
    }

    public void set(Color c) {
        clr = c;
    }

    public Color getColor() {
        return clr;
    }
}