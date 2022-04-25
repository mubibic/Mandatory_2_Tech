import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.*;

class Ball extends JPanel implements Runnable {

    //id to know which balls we compare at collision
    private int id;
    //ArrayList contains every ball's location, each ball "knows" all the locations
    private ArrayList<Position> positionList;
    //Boolean for ball directions
    private boolean directionRight, directionUp;
    //new direction and speed after bounce, generated randomly later
    private int xDx, yDy;
    //Bouncing frame
    private final int MAX_X = 400, MAX_Y = 360;
    //Set up new color after bounce
    private Random r;
    private Color clr = Color.blue;

    //Default ball attributes
    public Ball(int id, ArrayList<Position> positionList) {
        this.id = id;
        this.positionList = positionList;
        directionRight = false;
        directionUp = false;
        xDx = 1;
        yDy = 1;
        setOpaque(false);
        setPreferredSize(new Dimension(MAX_X, MAX_Y));
        r = new Random();
    }

    //Runs program
    public void run() {

        while (true) {
            setFps(15);
            Position position = positionList.get(id);

            moveBall(position);
            ballCollision(position);
            wallCollision(position);

            repaint();
        }
    }

    //Sets up program's speed
    private void setFps(int milliSecond) {
        try {
            Thread.sleep(milliSecond);
        } catch (InterruptedException exception) {
            System.err.println(exception.toString());
        }
    }

    //Checks the direction of the ball, moves it to that way
    private void moveBall(Position position) {
        int x = position.getX();
        int y = position.getY();

        if (directionRight) {
          position.setX(x + xDx);
        } else {
            position.setX(x - xDx);
        }

        if (directionUp) {
            position.setY(y + yDy);
        } else {
            position.setY(y - yDy);
        }
    }

    //When one of the walls is hit, sets new direction
    private void wallCollision(Position position) {
        //If bottom frame hit, direction set to up
        if (position.getY() <= 0) {
            directionUp = true;
            yDy = (int) (Math.random() * 5 + 2);

            //If top frame hit, direction set to down
        } else if (position.getY() >= MAX_Y - 30) {
            yDy = (int) (Math.random() * 5 + 2);
            directionUp = false;
        }

        //If left frame hit, direction set to right
        if (position.getX() <= 0) {
            directionRight = true;
            xDx = (int) (Math.random() * 5 + 2);

            //If left frame hit, direction set to left
        } else if (position.getX() >= MAX_X - 30) {
            directionRight = false;
            xDx = (int) (Math.random() * 5 + 2);
        }
    }

    public void ballCollision(Position position) {
        //Copy list
        List<Position> otherPositions = new ArrayList<>(positionList);
        //Remove ball with the current id from list (not to check collision with itself)
        otherPositions.remove(id);

        //For each loop goes through position of other balls
        for (Position otherPosition : otherPositions
        ) {
            //Calculates area of balls from balls position
            double xDif = position.getX() - otherPosition.getX();
            double yDif = position.getY() - otherPosition.getY();
            double distanceSquared = xDif * xDif + yDif * yDif;
            //Creates a boolean from calculation
            boolean collision = distanceSquared < (15 + 15) * (15 + 15);

            //If collision through balls get a new (more or less random) direction and random speed, new color
            if (collision) {
                yDy = (int) (Math.random() * 5 + 2);
                xDx = (int) (Math.random() * 5 + 2);
                //New color
                set(new Color(r.nextInt(256), r.nextInt(256), r.nextInt(256)));

                //Change new direction to opposite of current direction
                if (!directionUp) {
                    directionUp = true;
                } else {
                    directionUp = false;
                }

                if (!directionRight) {
                    directionRight = true;
                } else {
                    directionRight = false;
                }
            }
        }
    }

    public void paint(Graphics gr) {
        int x = positionList.get(id).getX();
        int y = positionList.get(id).getY();

        super.paint(gr);
        gr.setColor(get());
        gr.fillOval(x, y, 30, 30);
    }

    public void set(Color c) {
        clr = c;
    }

    public Color get() {
        return clr;
    }
}