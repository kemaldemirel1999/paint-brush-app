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
        this.optionPanel = new JPanel(new BorderLayout());

        JButton draw_rectangle_button = new JButton("Dikdortgen Ciz");
        JButton draw_oval_button = new JButton("Oval Ciz");
        JButton draw_with_pen_button = new JButton("Kalemle Ciz");
        JButton move_button = new JButton("Tasi");

        draw_rectangle_button.setPreferredSize(new Dimension(200,50));
        draw_oval_button.setPreferredSize(new Dimension(200,50));
        draw_with_pen_button.setPreferredSize(new Dimension(200,50));
        move_button.setPreferredSize(new Dimension(200,50));

        draw_rectangle_button.setFont(new Font("Arial", Font.PLAIN, 14));
        draw_oval_button.setFont(new Font("Arial", Font.PLAIN, 14));
        draw_with_pen_button.setFont(new Font("Arial", Font.PLAIN, 14));
        move_button.setFont(new Font("Arial", Font.PLAIN, 14));


        JPanel buttonPanel = new JPanel(new GridLayout(1,4));
        buttonPanel.add(draw_rectangle_button);
        buttonPanel.add(draw_oval_button);
        buttonPanel.add(draw_with_pen_button);
        buttonPanel.add(move_button);

        JPanel colorPanel = new JPanel(new GridLayout(1,7));
        colorPanel.add(new ColorPalette(Color.BLUE));
        colorPanel.add(new ColorPalette(Color.RED));
        colorPanel.add(new ColorPalette(Color.GREEN));
        colorPanel.add(new ColorPalette(Color.YELLOW));
        colorPanel.add(new ColorPalette(Color.ORANGE));
        colorPanel.add(new ColorPalette(Color.MAGENTA));
        colorPanel.add(new ColorPalette(Color.BLACK));

        this.optionPanel.add(colorPanel, BorderLayout.NORTH);
        this.optionPanel.add(buttonPanel, BorderLayout.SOUTH);
        add(this.optionPanel, BorderLayout.NORTH);

    }

}
