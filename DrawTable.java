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

    private ArrayList<Object> dragged_rectangle = new ArrayList<>();
    private ArrayList<Object> dragged_oval = new ArrayList<>();

    private ArrayList<ArrayList> all_shapes;



    private ArrayList selectedShape = new ArrayList();
    private Point lastMousePos = null;

    public DrawTable(){
        x = 0;
        y = 0;
        addMouseListener(this);
        addMouseMotionListener(this);
        setBackground(Color.GRAY);
        setVisible(true);
        pen_color = new Color(Color.BLACK.getRGB());
        mode = "";
        all_shapes = new ArrayList<>();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        old_x = x;
        old_y = y;
        x = e.getX();
        y = e.getY();
        Graphics g = getGraphics();
        g.setColor(pen_color);

        if (this.mode.equals("pen")){
            ArrayList<Object> tmp = new ArrayList<>();
            tmp.add(new int[] { x, y , old_x, old_y});
            tmp.add(this.pen_color);
            all_shapes.add(tmp);

        }
        else if(this.mode.equals("rectangle")){
            dragged_rectangle.clear();
            end = e.getPoint();
            int x = Math.min(start.x, end.x);
            int y = Math.min(start.y, end.y);
            int width = Math.abs(start.x - end.x);
            int height = Math.abs(start.y - end.y);
            Rectangle rect = new Rectangle(x, y, width, height);
            dragged_rectangle.add(rect);
            dragged_rectangle.add(this.pen_color);

        }
        else if(this.mode.equals("oval")){
            dragged_oval.clear();
            end = e.getPoint();
            int x = Math.min(start.x, end.x);
            int y = Math.min(start.y, end.y);
            int width = Math.abs(start.x - end.x);
            int height = Math.abs(start.y - end.y);
            Ellipse2D.Double oval = new Ellipse2D.Double(x, y, width, height);
            dragged_oval.add(oval);
            dragged_oval.add(this.pen_color);
        }
        else if(this.mode.equals("move")){
            int dx = e.getX() - lastMousePos.x;
            int dy = e.getY() - lastMousePos.y;
            System.out.println("Changed: x:"+dx + ", y:"+dy);
            if(selectedShape.get(0).getClass().equals(Rectangle.class)){
                Rectangle rect = (Rectangle) selectedShape.get(0);
                rect.setLocation(rect.x + dx, rect.y + dy);
            }
            else if(selectedShape.get(0).getClass().equals(Ellipse2D.Double.class)){
                Ellipse2D.Double oval = (Ellipse2D.Double) selectedShape.get(0);
                oval.x = oval.x + dx;
                oval.y = oval.y + dy;
            }
            lastMousePos = e.getPoint();
        }
        repaint();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        x = e.getX();
        y = e.getY();
        start = e.getPoint();
        System.out.println("start_x:"+start.x+", start_y:"+start.y);
        if(mode.equals("pen")){
            ArrayList<Object> tmp = new ArrayList<>();
            tmp.add(new int[] { x, y , x, y});
            tmp.add(this.pen_color);
            all_shapes.add(tmp);
        }
        else if(mode.equals("oval")){
            dragged_oval.clear();
            end = e.getPoint();
            int x = Math.min(start.x, end.x);
            int y = Math.min(start.y, end.y);
            int width = Math.abs(start.x - end.x);
            int height = Math.abs(start.y - end.y);
            Ellipse2D.Double oval = new Ellipse2D.Double(x, y, width, height);
            dragged_oval.add(oval);
            dragged_oval.add(this.pen_color);

        }
        else if(mode.equals("rectangle")){
            dragged_rectangle.clear();
            end = e.getPoint();
            int x = Math.min(start.x, end.x);
            int y = Math.min(start.y, end.y);
            int width = Math.abs(start.x - end.x);
            int height = Math.abs(start.y - end.y);
            Rectangle rect = new Rectangle(x, y, width, height);
            dragged_rectangle.add(rect);
            dragged_rectangle.add(this.pen_color);

        }
        else if(mode.equals("move")){
            for(ArrayList shape_info: all_shapes){
                if(shape_info.get(0).getClass().equals(Rectangle.class)){
                    Rectangle rect = (Rectangle) shape_info.get(0);
                    if(rect.getBounds().contains(e.getPoint())){
                        selectedShape.add(rect);
                        selectedShape.add(shape_info.get(1));
                        all_shapes.remove(shape_info);
                        lastMousePos = e.getPoint();
                        break;
                    }
                }
                else if(shape_info.get(0).getClass().equals(Ellipse2D.Double.class)){
                    Ellipse2D.Double oval = (Ellipse2D.Double) shape_info.get(0);
                    if(oval.getBounds().contains(e.getPoint())){
                        selectedShape.add(oval);
                        selectedShape.add(shape_info.get(1));
                        all_shapes.remove(shape_info);
                        lastMousePos = e.getPoint();
                        break;
                    }
                }
            }
        }
        repaint();
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
            if(selectedShape.get(0).getClass().equals(Rectangle.class)){
                Rectangle rect = (Rectangle) selectedShape.get(0);
                ArrayList<Object> tmp = new ArrayList<>();
                tmp.add(rect);
                tmp.add(selectedShape.get(1));
                all_shapes.add(selectedShape);
            }
            else if(selectedShape.get(0).getClass().equals(Ellipse2D.Double.class)){
                Ellipse2D.Double oval = (Ellipse2D.Double) selectedShape.get(0);
                ArrayList<Object> tmp = new ArrayList<>();
                tmp.add(oval);
                tmp.add(selectedShape.get(1));
                all_shapes.add(selectedShape);
            }
        }
        else if(mode.equals("oval")){
            dragged_oval.clear();
            int x = Math.min(start.x, end.x);
            int y = Math.min(start.y, end.y);
            int width = Math.abs(start.x - end.x);
            int height = Math.abs(start.y - end.y);
            Ellipse2D.Double oval = new Ellipse2D.Double(x, y, width, height);
            dragged_oval.add(oval);
            dragged_oval.add(this.pen_color);
            ArrayList<Object> info = new ArrayList();
            info.add(oval);
            info.add(this.pen_color);

            all_shapes.add(info);
            start = null;
            end = null;
        }
        else if(mode.equals("rectangle")){
            dragged_rectangle.clear();
            int x = Math.min(start.x, end.x);
            int y = Math.min(start.y, end.y);
            int width = Math.abs(start.x - end.x);
            int height = Math.abs(start.y - end.y);
            Rectangle rect = new Rectangle(x, y, width, height);
            dragged_rectangle.add(rect);
            dragged_rectangle.add(this.pen_color);
            ArrayList<Object> info = new ArrayList();
            info.add(rect);
            info.add(this.pen_color);
            all_shapes.add(info);
            start = null;
            end = null;
        }
        repaint();
    }

    @Override
    public void mouseMoved(MouseEvent e) {}
    @Override
    public void mouseClicked(MouseEvent e) {}
    @Override
    public void mouseEntered(MouseEvent e) {}
    @Override
    public void mouseExited(MouseEvent e) {}

    public void paint(Graphics g){
        super.paint(g);
        for(ArrayList shape_info: all_shapes){
            if(shape_info.get(0).getClass().equals(Rectangle.class)){
                g.setColor((Color) shape_info.get(1));
                Rectangle rect = (Rectangle) shape_info.get(0);
                g.fillRect(rect.x, rect.y, rect.width, rect.height);
            }
            else if(shape_info.get(0).getClass().equals(Ellipse2D.Double.class)){
                g.setColor((Color) shape_info.get(1));
                Ellipse2D.Double oval = (Ellipse2D.Double) shape_info.get(0);
                g.fillOval((int) oval.x, (int) oval.y, (int) oval.width, (int) oval.height);
            }
            else{
                System.out.println("yep");
                int[] coordinates = (int[]) shape_info.get(0);
                g.setColor((Color) shape_info.get(1));
                g.drawLine(coordinates[0], coordinates[1], coordinates[2], coordinates[3]);
            }
        }
        if(dragged_rectangle!=null && !dragged_rectangle.isEmpty()){
            Rectangle rect = (Rectangle) dragged_rectangle.get(0);
            g.setColor((Color) dragged_rectangle.get(1));
            g.fillRect(rect.x, rect.y, rect.width, rect.height);
            dragged_rectangle.clear();
        }
        if(dragged_oval != null &&  !dragged_oval.isEmpty()){
            Ellipse2D.Double oval = (Ellipse2D.Double) dragged_oval.get(0);
            g.setColor((Color) dragged_oval.get(1));
            g.fillOval((int) oval.x, (int) oval.y, (int) oval.width, (int) oval.height);
            dragged_oval.clear();
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
