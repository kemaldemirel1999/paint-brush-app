import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ColorPalette extends JPanel {

    private Color color;

    public ColorPalette(Color color) {
        super();
        this.color = color;
        setPreferredSize(new Dimension(50, 70));
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                String colorName;
                if (Color.BLUE.equals(color)) {
                    colorName = "Mavi";
                }
                else if (Color.RED.equals(color)) {
                    colorName = "Kirmizi";
                }
                else if (Color.GREEN.equals(color)) {
                    colorName = "Yesil";
                }
                else if (Color.YELLOW.equals(color)) {
                    colorName = "Sari";
                }
                else if (Color.ORANGE.equals(color)) {
                    colorName = "Turuncu";
                }
                else if (Color.BLACK.equals(color)) {
                    colorName = "Siyah";
                }
                else{
                    colorName = "Mor";
                }
                System.out.println("Yeni Secilen Renk: " + colorName);
            }
        });
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(color);
        g.fillRect(5, 5, getWidth() - 10, getHeight() - 10);
    }

}



