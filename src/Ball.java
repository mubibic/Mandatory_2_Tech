import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.*;

class Ball extends JPanel implements Runnable {

    //id variable to know which balls we compare at collision / The ball objects id is equal to the arraylist index
    private int id;
    //ArrayList contains every ball's location, each ball "knows" all the locations
    private ArrayList<Position> positionList;
    //Boolean for ball directions
    private boolean directionRight, directionUp;
    //Random speed (1-7) generated after collision
    private int xDelta, yDelta;
    //Bouncing frame size of Jpanel = Windows frame - header(40px)
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
        xDelta = 1;
        yDelta = 1;
        //Transparency of the ball
        setOpaque(false);
        setPreferredSize(new Dimension(MAX_X, MAX_Y));
        //Variable for color
        r = new Random();
    }

    //Runs program
    public void run() {

        while (true) {
            setFps(15);
            Position position = positionList.get(id);

            moveBall(position);
            wallCollision(position);
            ballCollision(position);
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
            position.setX(x + xDelta);
        } else {
            position.setX(x - xDelta);
        }

        if (directionUp) {
            position.setY(y + yDelta);
        } else {
            position.setY(y - yDelta);
        }
    }

    //When one of the walls is hit, sets new direction and color
    private void wallCollision(Position position) {

        //If bottom frame hit, direction set to up
        if (position.getY() <= 0) {
            directionUp = true;
            yDelta = randomSpeed();
            setNewColor();


            //If top frame hit, direction set to down
        } else if (position.getY() >= MAX_Y - 30) {
            yDelta = randomSpeed();
            directionUp = false;
            setNewColor();
        }

        //If left frame hit, direction set to right
        if (position.getX() <= 0) {
            directionRight = true;
            xDelta = randomSpeed();
            setNewColor();

            //If right frame hit, direction set to left
        } else if (position.getX() >= MAX_X - 30) {
            directionRight = false;
            xDelta = randomSpeed();
            setNewColor();
        }
    }

    public void ballCollision(Position position) {
        //Copy list
        List<Position> otherPositions = new ArrayList<>(positionList);
        //Remove ball with the current id from list (not to check collision with itself)
        otherPositions.remove(id);

        //For each loop goes through position of other balls
        for (Position otherPosition : otherPositions) {
            boolean collision = isColliding(position, otherPosition);

            //If collision is true the ball gets new random direction, speed and new color
            if (collision) {
                yDelta = randomSpeed();
                xDelta = randomSpeed();
                //New color
                setNewColor();

                //Change new direction to opposite of current direction
                directionRight = !directionRight;
                directionUp = !directionUp;
            }
        }
    }

    //Returns boolean true if two balls collide
    private boolean isColliding(Position position, Position otherPosition) {
        //Calculates area of balls from balls position
        double xDif = position.getX() - otherPosition.getX();
        double yDif = position.getY() - otherPosition.getY();
        double distanceSquared = xDif * xDif + yDif * yDif;
        //Creates a boolean from calculation
        boolean collision = distanceSquared < (15 + 15) * (15 + 15);
        return collision;
    }

    //Creates colored ball for graphical display
    public void paint(Graphics gr) {
        int x = positionList.get(id).getX();
        int y = positionList.get(id).getY();

        super.paint(gr);
        gr.setColor(clr);
        gr.fillOval(x, y, 30, 30);
    }

    //Method for creating random color
    private void setNewColor() {
        clr = new Color(r.nextInt(256), r.nextInt(256), r.nextInt(256));
    }

    //Generates a random integer speed
    public int randomSpeed(){
    return (int) (Math.random() * 6 + 1);}

}