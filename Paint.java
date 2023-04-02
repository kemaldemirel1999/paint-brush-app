import javax.swing.*;
import java.awt.*;

public class Paint extends JFrame {

    private JButton draw_rectangle_button;
    private JButton draw_oval_button;
    private JButton draw_with_pen_button;
    private JButton move_button;


    private boolean is_under_the_blue_line;
    private JPanel optionPanel;

    public Paint(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 1000);
        setLocationRelativeTo(null);
        setVisible(true);
        this.is_under_the_blue_line = false;
        draw_rectangle_button = new JButton("Dikdortgen Ciz");
        draw_oval_button = new JButton("Oval Ciz");
        draw_with_pen_button = new JButton("Kalemle Ciz");
        move_button = new JButton("Tasi");

        optionPanel = new JPanel();
        optionPanel.add(draw_rectangle_button);
        optionPanel.add(draw_oval_button);
        optionPanel.add(draw_with_pen_button);
        optionPanel.add(move_button);
        optionPanel.setVisible(true);
        add(optionPanel, BorderLayout.NORTH);




    }

    public void paintComponent(Graphics g){
        super.paintComponents(g);
        Color[] colors = {Color.BLUE, Color.RED, Color.GREEN, Color.YELLOW, Color.ORANGE, new Color(123, 35, 123), Color.BLACK};
        int x = 50;
        int y = 50;
        int width = 50;
        int height = 50;
        for (int i = 0; i < 7; i++) {
            g.setColor(colors[i]);
            g.fillRect(x, y, width, height);
            x += 60;
        }
    }

}
