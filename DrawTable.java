import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;

public class DrawTable extends JPanel implements MouseMotionListener, MouseListener {

    private int x;
    private int y;
    private int old_x;
    private int old_y;
    private Color pen_color;
    private String mode;

    //private int first_x, first_y, last_x, last_y;
    private Point start, end;

    private ArrayList<Rectangle> tmp_rectangles = new ArrayList<>();
    private ArrayList<ArrayList> rectangles = new ArrayList<>();

    private ArrayList<Ellipse2D.Double> tmp_ovals = new ArrayList<>();
    private ArrayList<ArrayList> ovals = new ArrayList<>();


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
        else if(this.mode.equals("rectangle")){
            tmp_rectangles.clear();
            end = e.getPoint();
            int x = Math.min(start.x, end.x);
            int y = Math.min(start.y, end.y);
            int width = Math.abs(start.x - end.x);
            int height = Math.abs(start.y - end.y);
            Rectangle rect = new Rectangle(x, y, width, height);
            tmp_rectangles.add(rect);
        }
        else if(this.mode.equals("oval")){
            tmp_ovals.clear();
            end = e.getPoint();
            int x = Math.min(start.x, end.x);
            int y = Math.min(start.y, end.y);
            int width = Math.abs(start.x - end.x);
            int height = Math.abs(start.y - end.y);
            Ellipse2D.Double oval = new Ellipse2D.Double(x, y, width, height);
            tmp_ovals.add(oval);
        }
        repaint();
    }
    @Override
    public void mouseMoved(MouseEvent e) {}
    @Override
    public void mouseClicked(MouseEvent e) {}
    @Override
    public void mousePressed(MouseEvent e) {
        x = e.getX();
        y = e.getY();
        start = e.getPoint();
        System.out.println("start_x:"+start.x+", start_y:"+start.y);
        if(mode.equals("move")){

        }
        else if(mode.equals("oval")){

        }
        else if(mode.equals("rectangle")){

        }
    }
    @Override
    public void mouseReleased(MouseEvent e) {
        x = e.getX();
        y = e.getY();
        end = e.getPoint();
        System.out.println("end_x:"+end.x+", end_y:"+end.y);
        Graphics g = getGraphics();
        g.setColor(this.pen_color);
        if(mode.equals("move")){

        }
        else if(mode.equals("oval")){
            tmp_ovals.clear();
            int x = Math.min(start.x, end.x);
            int y = Math.min(start.y, end.y);
            int width = Math.abs(start.x - end.x);
            int height = Math.abs(start.y - end.y);
            Ellipse2D.Double oval = new Ellipse2D.Double(x, y, width, height);
            tmp_ovals.add(oval);
            ArrayList<Object> info = new ArrayList();
            info.add(oval);
            info.add(this.pen_color);
            ovals.add(info);
            start = null;
            end = null;
        }
        else if(mode.equals("rectangle")){
            tmp_rectangles.clear();
            int x = Math.min(start.x, end.x);
            int y = Math.min(start.y, end.y);
            int width = Math.abs(start.x - end.x);
            int height = Math.abs(start.y - end.y);
            Rectangle rect = new Rectangle(x, y, width, height);
            tmp_rectangles.add(rect);
            ArrayList<Object> info = new ArrayList();
            info.add(rect);
            info.add(this.pen_color);
            rectangles.add(info);
            start = null;
            end = null;
        }
        repaint();
    }
    @Override
    public void mouseEntered(MouseEvent e) {}
    @Override
    public void mouseExited(MouseEvent e) {}

    public void paint(Graphics g){
        super.paint(g);
        for (Rectangle rect : tmp_rectangles) {
            g.setColor(this.pen_color);
            g.fillRect(rect.x, rect.y, rect.width, rect.height);
        }
        for (ArrayList rectangle_info : rectangles) {
            g.setColor((Color) rectangle_info.get(1));
            Rectangle rect = (Rectangle) rectangle_info.get(0);
            g.fillRect(rect.x, rect.y, rect.width, rect.height);
        }
        for (Ellipse2D.Double oval : tmp_ovals) {
            g.setColor(this.pen_color);
            g.fillOval((int) oval.x, (int) oval.y, (int) oval.width, (int) oval.height);
        }
        for (ArrayList oval_info : ovals) {
            g.setColor((Color) oval_info.get(1));
            Ellipse2D.Double oval = (Ellipse2D.Double) oval_info.get(0);
            g.fillOval((int) oval.x, (int) oval.y, (int) oval.width, (int) oval.height);
        }
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
