import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ColorPalette extends JPanel {

    private Color color; // Color of the palette.

    // paint object and color is given as parameter.
    // Color is assigned to colorPalette's color.
    // paint objects is going to be used for changing color of rectangle, ellipse, and pen.
    public ColorPalette(Color color, Paint paint) {
        super();
        this.color = color;
        setPreferredSize(new Dimension(50, 70));

        // When color palette is clicked. Current color is changed to it.
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                paint.getDrawTable().setPen_color(color);
            }
        });
    }

    // This is used for creating rectangle color palette areas.
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(color);
        g.fillRect(5, 5, getWidth() - 10, getHeight() - 10);
    }

}



