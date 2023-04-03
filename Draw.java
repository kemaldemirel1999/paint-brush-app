import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public class Draw extends JComponent {

    private int x;
    private int y;
    private int old_x;
    private int old_y;
    private Graphics2D g2;

    public Draw(){
        setDoubleBuffered(false);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                old_x = x;
                old_y = y;
            }
        });
        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                x = e.getX();
                y = e.getY();
                if(g2!= null){
                    g2.drawLine(old_x, old_y, x, y);
                    repaint();
                    old_x = x;
                    old_y = y;
                }
            }
        });
    }
    public void paintComponent(Graphics g){

    }
    public void mousePressed(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
}
