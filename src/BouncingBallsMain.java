import javax.swing.*;
import java.util.ArrayList;

class BouncingBallsMain extends JFrame {

    public BouncingBallsMain() {
        setResizable(false);
        setSize(400, 400);

        //Creating positions for balls, ArrayList index = ball id
        Position position1 = new Position(1, 1);
        Position position2 = new Position(1, 50);
        Position position3 = new Position(1, 100);
        Position position4 = new Position(1, 150);

        ArrayList<Position> positionList = new ArrayList<>();
        positionList.add(position1);
        positionList.add(position2);
        positionList.add(position3);
        positionList.add(position4);

        Ball ball1 = new Ball(0, positionList);
        Ball ball2 = new Ball(1, positionList);
        Ball ball3 = new Ball(2, positionList);
        Ball ball4 = new Ball(3, positionList);

        ball1.add(ball2);
        ball2.add(ball3);
        ball3.add(ball4);
        //It states how the graphical interface should look and displays the balls
        getContentPane().add(ball1);

        setVisible(true);
        Thread x = new Thread(ball1);
        Thread y = new Thread(ball2);
        Thread z = new Thread(ball3);
        Thread v = new Thread(ball4);

        x.start();
        y.start();
        z.start();
        v.start();
    }

    public static void main(String[] args) {
        new BouncingBallsMain();
    }
}