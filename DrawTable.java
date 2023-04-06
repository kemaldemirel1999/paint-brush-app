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
    private Point start, end;
    private ArrayList<Object> dragged_rectangle;
    private ArrayList<Object> dragged_oval;
    private ArrayList<ArrayList> all_shapes;
    private ArrayList<Object> moving_shape;
    private Point lastMousePos;

    public DrawTable(){
        this.x = 0;
        this.y = 0;
        addMouseListener(this);
        addMouseMotionListener(this);
        setBackground(Color.GRAY);
        setVisible(true);
        this.pen_color = new Color(Color.BLACK.getRGB());
        this.mode = "";
        this.lastMousePos = null;
        this.all_shapes = new ArrayList<>();
        this.dragged_rectangle = new ArrayList<>();
        this.dragged_oval = new ArrayList<>();
        this.moving_shape = new ArrayList<>();
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
            if(moving_shape.get(0).getClass().equals(Rectangle.class)){
                Rectangle rect = (Rectangle) moving_shape.get(0);
                rect.x = rect.x + dx;
                rect.y = rect.y + dy;;
                moving_shape.remove(0);
                moving_shape.add(0,rect);
            }
            else if(moving_shape.get(0).getClass().equals(Ellipse2D.Double.class)){
                Ellipse2D.Double oval = (Ellipse2D.Double) moving_shape.get(0);
                oval.x = oval.x + dx;
                oval.y = oval.y + dy;
                moving_shape.remove(0);
                moving_shape.add(0,oval);
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
            for(int i=all_shapes.size()-1; i>=0; i--){
                ArrayList<Object> shape_info = all_shapes.get(i);
                if(shape_info.get(0).getClass().equals(Rectangle.class)){
                    Rectangle rect = (Rectangle) shape_info.get(0);
                    if(rect.getBounds().contains(e.getPoint())){
                        all_shapes.remove(i);
                        moving_shape.add(rect);
                        moving_shape.add(shape_info.get(1));
                        break;
                    }
                }
                else if(shape_info.get(0).getClass().equals(Ellipse2D.Double.class)){
                    Ellipse2D.Double oval = (Ellipse2D.Double) shape_info.get(0);
                    if(oval.getBounds().contains(e.getPoint())){
                        all_shapes.remove(i);
                        moving_shape.add(oval);
                        moving_shape.add(shape_info.get(1));
                        break;
                    }
                }
            }
            lastMousePos = e.getPoint();
        }
        repaint();
    }
    @Override
    public void mouseReleased(MouseEvent e) {
        x = e.getX();
        y = e.getY();
        end = e.getPoint();
        Graphics g = getGraphics();
        g.setColor(this.pen_color);

        if(mode.equals("move")){
            if(moving_shape.get(0).getClass().equals(Rectangle.class)){
                Rectangle rect = (Rectangle) moving_shape.get(0);
                Color color = (Color) moving_shape.get(1);
                ArrayList<Object> tmp = new ArrayList<>();
                tmp.add(rect);
                tmp.add(color);
                all_shapes.add(tmp);
                moving_shape.clear();
            }
            else if(moving_shape.get(0).getClass().equals(Ellipse2D.Double.class)){
                Ellipse2D.Double oval = (Ellipse2D.Double) moving_shape.get(0);
                Color color = (Color) moving_shape.get(1);
                ArrayList<Object> tmp = new ArrayList<>();
                tmp.add(oval);
                tmp.add(color);
                all_shapes.add(tmp);
                moving_shape.clear();
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
        if(moving_shape != null && !moving_shape.isEmpty()){
            if(moving_shape.get(0).getClass().equals(Rectangle.class)){
                Rectangle rect = (Rectangle) moving_shape.get(0);
                g.setColor((Color) moving_shape.get(1));
                g.fillRect((int) rect.x, (int) rect.y, (int) rect.width, (int) rect.height);
            }
            else if(moving_shape.get(0).getClass().equals(Ellipse2D.Double.class)){
                Ellipse2D.Double oval = (Ellipse2D.Double) moving_shape.get(0);
                g.setColor((Color) moving_shape.get(1));
                g.fillOval((int) oval.x, (int) oval.y, (int) oval.width, (int) oval.height);
            }
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
