import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OptionsPanel extends JPanel{

    /*
        this JPanel includes the colors, buttons and blue line separator.
     */
    public OptionsPanel(Paint paint){
        setLayout(new BorderLayout());
        // Buttons are created.
        JButton draw_rectangle_button = new JButton("Dikdortgen Ciz");
        JButton draw_oval_button = new JButton("Oval Ciz");
        JButton draw_with_pen_button = new JButton("Kalemle Ciz");
        JButton move_button = new JButton("Tasi");

        draw_rectangle_button.setPreferredSize(new Dimension(150,50));
        draw_oval_button.setPreferredSize(new Dimension(150,50));
        draw_with_pen_button.setPreferredSize(new Dimension(150,50));
        move_button.setPreferredSize(new Dimension(150,50));

        draw_rectangle_button.setFont(new Font("Arial", Font.PLAIN, 14));
        draw_oval_button.setFont(new Font("Arial", Font.PLAIN, 14));
        draw_with_pen_button.setFont(new Font("Arial", Font.PLAIN, 14));
        move_button.setFont(new Font("Arial", Font.PLAIN, 14));

        // When associated button is clicked, the mode is changed to it.
        draw_rectangle_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                paint.getDrawTable().setMode("rectangle");
            }
        });
        draw_oval_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                paint.getDrawTable().setMode("oval");
            }
        });
        draw_with_pen_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                paint.getDrawTable().setMode("pen");
            }
        });
        move_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                paint.getDrawTable().setMode("move");
            }
        });

        JPanel buttonPanel = new JPanel(new GridLayout(1,4));
        buttonPanel.add(draw_rectangle_button);
        buttonPanel.add(draw_oval_button);
        buttonPanel.add(draw_with_pen_button);
        buttonPanel.add(move_button);

        // colorPanel includes all the color palette's that you can choose.
        JPanel colorPanel = new JPanel(new GridLayout(1,7));

        // Color palette is not button. It is written by me as a JPanel
        colorPanel.add(new ColorPalette(Color.BLUE, paint));
        colorPanel.add(new ColorPalette(Color.RED, paint));
        colorPanel.add(new ColorPalette(Color.GREEN, paint));
        colorPanel.add(new ColorPalette(Color.YELLOW, paint));
        colorPanel.add(new ColorPalette(Color.ORANGE, paint));
        colorPanel.add(new ColorPalette(new Color(123, 35, 123), paint));
        colorPanel.add(new ColorPalette(Color.BLACK, paint));
        add(colorPanel, BorderLayout.NORTH);    // color panel is added to north of the optionPanel
        add(buttonPanel, BorderLayout.CENTER);  // buttonPanel is added to center of the optionPanel
        add(getBlueLineSeparator(), BorderLayout.SOUTH); // blueLineSeparator is added to south of the optionPanel
    }

    // This JPanel represents the blue line in the paint-brush-app.
    public JPanel getBlueLineSeparator(){
        JPanel blueLine = new JPanel();
        blueLine.setBackground(Color.BLUE);
        blueLine.setPreferredSize(new Dimension(0, 5));
        return blueLine;
    }

}
