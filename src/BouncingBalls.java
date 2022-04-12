import javax.swing.*;
import java.util.ArrayList;
import java.util.Random;

class BouncingBalls extends JFrame {

    public BouncingBalls() {

        Random r = new Random();
        setResizable(false);
        setSize(400, 400);

        //Id not needed, ArrayList index = ball id
        Position ballPosition1 = new Position(1,1);
        Position ballPosition2 = new Position(1,1);
        Position ballPosition3 = new Position(1,1);

        ArrayList<Position> positionList = new ArrayList<>();
        positionList.add(ballPosition1);
        positionList.add(ballPosition2);
        positionList.add(ballPosition3);

        Ball ball1 = new Ball(0,positionList);
        Ball ball2 = new Ball(1,positionList);
        Ball ball3 = new Ball(2,positionList);

        ball1.add(ball2);
        ball2.add(ball3);
        getContentPane().add(ball1);

        setVisible(true);
        Thread x = new Thread(ball1);
        Thread y = new Thread(ball2);
        Thread z = new Thread(ball3);

        x.start();
        y.start();
        z.start();
    }

    public static void main(String[] args) {
        new BouncingBalls();
    }
}