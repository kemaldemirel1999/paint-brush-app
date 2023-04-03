import javax.swing.*;
import java.awt.*;

public class Paint extends JFrame {


    private boolean is_under_the_blue_line;
    private JPanel optionPanel;

    public Paint(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 1000);
        setLocationRelativeTo(null);
        setVisible(true);
        this.is_under_the_blue_line = false;
        this.optionPanel = new JPanel(new GridLayout(2,0));

        JButton draw_rectangle_button = new JButton("Dikdortgen Ciz");
        JButton draw_oval_button = new JButton("Oval Ciz");
        JButton draw_with_pen_button = new JButton("Kalemle Ciz");
        JButton move_button = new JButton("Tasi");

        JPanel buttonPanel = new JPanel(new GridLayout(1,0));
        buttonPanel.add(draw_rectangle_button);
        buttonPanel.add(draw_oval_button);
        buttonPanel.add(draw_with_pen_button);
        buttonPanel.add(move_button);

        JPanel topPanel = new JPanel(new GridLayout(1,0));
        ColorPalette colorPalettes = new ColorPalette();
        topPanel.add(colorPalettes);

        this.optionPanel.add(topPanel);
        this.optionPanel.add(buttonPanel);
        add(this.optionPanel, BorderLayout.NORTH);
        JPanel blueLinePanel = new JPanel() {
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(Color.BLUE);
                g.drawLine(0, 50, getWidth(), 50);
            }
        };
        blueLinePanel.setPreferredSize(new Dimension(1000, 50));
        add(blueLinePanel);
    }

}
