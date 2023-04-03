import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Options extends JPanel{

    public Options(Paint paint){
        setLayout(new BorderLayout());
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

        JPanel colorPanel = new JPanel(new GridLayout(1,7));

        colorPanel.add(new ColorPalette(Color.BLUE, paint));
        colorPanel.add(new ColorPalette(Color.RED, paint));
        colorPanel.add(new ColorPalette(Color.GREEN, paint));
        colorPanel.add(new ColorPalette(Color.YELLOW, paint));
        colorPanel.add(new ColorPalette(Color.ORANGE, paint));
        colorPanel.add(new ColorPalette(new Color(123, 35, 123), paint));
        colorPanel.add(new ColorPalette(Color.BLACK, paint));
        add(colorPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);
        add(getBlueLineSeparator(), BorderLayout.SOUTH);
    }
    public JPanel getBlueLineSeparator(){
        JPanel blueLine = new JPanel();
        blueLine.setBackground(Color.BLUE);
        blueLine.setPreferredSize(new Dimension(0, 5));
        return blueLine;
    }

}
