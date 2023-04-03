import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class DrawTable extends JPanel implements MouseMotionListener, MouseListener {

    private int x;
    private int y;
    private int old_x;
    private int old_y;
    private Color pen_color;
    private String mode;

    public DrawTable(){
        x = 0;
        y = 0;
        addMouseListener(this);
        addMouseMotionListener(this);
        setBackground(Color.GRAY);
        setVisible(true);
        pen_color = new Color(Color.BLACK.getRGB());
        mode = "pen";
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        old_x = x;
        old_y = y;
        x = e.getX();
        y = e.getY();
        System.out.println("x:"+x+", y:"+y);
        Graphics g = getGraphics();
        g.setColor(pen_color);
        if (this.mode.equals("pen")){
            g.drawLine(old_x, old_y, x, y);
        }
    }
    @Override
    public void mouseMoved(MouseEvent e) {}
    @Override
    public void mouseClicked(MouseEvent e) {}
    @Override
    public void mousePressed(MouseEvent e) {
        x = e.getX();
        y = e.getY();
        int start_x = x;
        int start_y = y;
        System.out.println("start_x:"+start_x+", start_y:"+start_y);
    }
    @Override
    public void mouseReleased(MouseEvent e) {
        x = e.getX();
        y = e.getY();
        int end_x = x;
        int end_y = y;
        System.out.println("end_x:"+end_x+", end_y:"+end_y);
    }
    @Override
    public void mouseEntered(MouseEvent e) {}
    @Override
    public void mouseExited(MouseEvent e) {}

    public void paint(Graphics g){
        System.out.println("Paint called");
        super.paint(g);
    }

    public Color getPen_color() {
        return pen_color;
    }

    public void setPen_color(Color pen_color) {
        this.pen_color = pen_color;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }
}
