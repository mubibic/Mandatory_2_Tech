import java.awt.*;
import java.util.Random;

//Runnable is needed to run the balls on multiple threads
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

    //Constructor requesting starting coordinates
    public Ball(int x, int y) {
        //Boolean if we increase or decrease the x/y coordinate value of the ball
        xIncrease = false;
        yIncrease = false;
        //Delta refers to the balls speed
        xDelta = 5;
        yDelta = 5;
        this.x = x;
        this.y = y;
        //Instantiating variable for random color
        r = new Random();
    }

    //This method is called when the thread starts (thread.start method calls it); Run(method is a must because of using Runnable interface
    public void run() {
        while (true) {
            try {
                Thread.sleep(20);
            } catch (InterruptedException exception) {
                System.err.println(exception.toString());
            }
            //Continuously calculate the new coordinates based on Delta(speed) values
            moveBallOneStep();

            //Checking if ball colliding with the wall/so it can not leave the frame
            if (y <= 0) {
                yIncrease = true;
                //It is the randomizer for the speed of the ball after colliding
                //yDelta = (int) (Math.random() * 5 + 2);
                setColor(new Color(r.nextInt(256), r.nextInt(256), r.nextInt(256)));

                //+30 is the diameter of the ball as we don't use it's center but the top left point of it
            } else if (y + 30 >= MAX_Y) {
                //yDelta = (int) (Math.random() * 5 + 2);
                yIncrease = false;
                setColor(new Color(r.nextInt(256), r.nextInt(256), r.nextInt(256)));

            }

            if (x <= 0) {
                xIncrease = true;
                //xDelta = (int) (Math.random() * 5 + 2);
                setColor(new Color(r.nextInt(256), r.nextInt(256), r.nextInt(256)));

            } else if (x + 30 >= MAX_X) {
                xIncrease = false;
                //xDelta = (int) (Math.random() * 5 + 2);
                setColor(new Color(r.nextInt(256), r.nextInt(256), r.nextInt(256)));

            }
        }
    }

    //Checks if the balls centers(diameter of the ball is 30) collide with each other
    public boolean isHitboxHit(Ball ball) {
        //Returns true if the two balls center points are closer than 30 -> they collided
        return (calculateDistanceBetweenPoints(this.x + 15, this.y + 15, ball.x + 15, ball.y + 15) < 30);
    }

    //Mathematical calculation for the distance btw the balls coordinates for collision detection
    private double calculateDistanceBetweenPoints(double x1, double y1, double x2, double y2) {
        return Math.sqrt((y2 - y1) * (y2 - y1) + (x2 - x1) * (x2 - x1));
    }

    //Change balls direction after collision
    public void invertDirection() {
        xIncrease = !xIncrease;
        yIncrease = !yIncrease;
        moveBallOneStep();
    }

    //Calculate the new coordinates based on Delta(speed) values
    private void moveBallOneStep() {
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
    }

    public void setColor(Color c) {
        clr = c;
    }

    public Color getColor() {
        return clr;
    }
}