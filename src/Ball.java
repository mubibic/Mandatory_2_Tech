import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.*;

class Ball extends JPanel implements Runnable {

    private int id;
    private ArrayList<Position> positionList;

    private boolean xUp, yUp;

    //current position of ball
    // private int x, y;

    //new direction and speed after bounce
    private int xDx, yDy;
    //Bouncing frame
    private final int MAX_X = 400, MAX_Y = 360;

    private Random r;
    private Color clr = Color.blue;

    public Ball(int id, ArrayList<Position> positionList) {
        this.id = id;
        this.positionList = positionList;

        xUp = false;
        yUp = false;
        xDx = 1;
        yDy = 1;

        setOpaque(false);
        setPreferredSize(new Dimension(MAX_X, MAX_Y));
        r = new Random();
    }

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

    private void setFps(int milliSecond) {
        try {
            Thread.sleep(milliSecond);
        } catch (InterruptedException exception) {
            System.err.println(exception.toString());
        }
    }

    private void wallCollision(Position position) {
        //If bottom frame reached, direction set to up
        if (position.getY() <= 0) {
            yUp = true;
            yDy = (int) (Math.random() * 5 + 2);


        } else if (position.getY() >= MAX_Y - 30) {
            yDy = (int) (Math.random() * 5 + 2);
            yUp = false;

        }

        if (position.getX() <= 0) {
            xUp = true;
            xDx = (int) (Math.random() * 5 + 2);


        } else if (position.getX() >= MAX_X - 30) {
            xUp = false;
            xDx = (int) (Math.random() * 5 + 2);
        }
    }

    private void moveBall(Position position) {
        int x = position.getX();
        int y = position.getY();
        //New random (xDx) generated
        if (xUp) {
            //Set x to x+=xdx
            position.setX(x + xDx);
        } else {
            position.setX(x - xDx);
        }

        if (yUp) {
            position.setY(y + yDy);
        } else {
            position.setY(y - yDy);
        }
    }

    public void ballCollision(Position position) {
        //Copy list
        List<Position> otherPositions = new ArrayList<>(positionList);
        otherPositions.remove(id);

        for (Position otherPosition : otherPositions
        ) {
            double xDif = position.getX() - otherPosition.getX();
            double yDif = position.getY() - otherPosition.getY();
            double distanceSquared = xDif * xDif + yDif * yDif;

            boolean collision = distanceSquared < (15 + 15) * (15 + 15);

            if (collision) {
                yDy = (int) (Math.random() * 5 + 2);
                xDx = (int) (Math.random() * 5 + 2);
                set(new Color(r.nextInt(256), r.nextInt(256), r.nextInt(256)));

                if (!yUp) {
                    yUp = true;
                } else {
                    yUp = false;
                }

                if (!xUp) {
                    xUp = true;
                } else {
                    xUp = false;
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