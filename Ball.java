import java.awt.*;
import java.util.Random;
import javax.swing.*;


class Ball extends JPanel implements Runnable
{
    private boolean xUp, yUp, bouncing;
    private int x, y, xDx, yDy;
    private final int MAX_X = 400, MAX_Y = 400;
    private Random r;
    private Color clr = Color.blue;


    public Ball()
    {
        xUp = false;
        yUp = false;
        xDx = 1;
        yDy = 1;
        bouncing = true;



        setOpaque(false);
        setPreferredSize(new Dimension( MAX_X, MAX_Y));
        r = new Random();
    }
    public void run()
    {
        while ( true ) {

            try {
                Thread.sleep(10);
            } catch (InterruptedException exception) {
                System.err.println(exception.toString());
            }

            if (xUp) {
                x += xDx;
            } else {
                x -= xDx;
            }


            if (yUp){
                y += yDy;
            }
            else{
                y -= yDy;
            }


            if ( y <= 0 ) {
                yUp = true;
                yDy = ( int ) ( Math.random() * 5 + 2 );
                set(new Color(r.nextInt(256), r.nextInt(256),r.nextInt(256)));

            }

            else if ( y >= MAX_Y - 30 ) {
                yDy = ( int ) ( Math.random() * 5 + 2 );
                yUp = false;
                set(new Color(r.nextInt(256), r.nextInt(256),r.nextInt(256)));

            }

            if ( x <= 0 ) {
                xUp = true;
                xDx = ( int ) ( Math.random() * 5 + 2 );
                set(new Color(r.nextInt(256), r.nextInt(256),r.nextInt(256)));

            }

            else if ( x >= MAX_X - 30 ) {
                xUp = false;
                xDx = ( int ) ( Math.random() * 5 + 2 );
                set(new Color(r.nextInt(256), r.nextInt(256),r.nextInt(256)));

            }

            repaint();

        }

    }


    public void paint(Graphics gr)
    {

        super.paint(gr);
        if ( bouncing ) {

            gr.setColor(get());
            gr.fillOval( x, y, 30, 30 );
        }

    }

    public void set(Color c){
        clr=c;
    }

    public Color get() {
        return clr;
    }
}