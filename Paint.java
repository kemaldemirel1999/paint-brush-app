import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Paint extends JFrame {

    private JPanel optionPanel;
    private DrawTable drawTable;

    public Paint(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 1000);
        setLocationRelativeTo(null);
        add(getOptionPanel(), BorderLayout.NORTH);
        drawTable = new DrawTable();
        add(drawTable, BorderLayout.CENTER);
        setVisible(true);
    }
    public JPanel getBlueLineSeparator(){
        JPanel blueLine = new JPanel();
        blueLine.setBackground(Color.BLUE);
        blueLine.setPreferredSize(new Dimension(0, 5));
        return blueLine;
    }

    public JPanel getOptionPanel(){
        this.optionPanel = new JPanel(new BorderLayout());

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


        JPanel buttonPanel = new JPanel(new GridLayout(1,4));
        buttonPanel.add(draw_rectangle_button);
        buttonPanel.add(draw_oval_button);
        buttonPanel.add(draw_with_pen_button);
        buttonPanel.add(move_button);

        JPanel colorPanel = new JPanel(new GridLayout(1,7));

        colorPanel.add(new ColorPalette(Color.BLUE, this));
        colorPanel.add(new ColorPalette(Color.RED, this));
        colorPanel.add(new ColorPalette(Color.GREEN, this));
        colorPanel.add(new ColorPalette(Color.YELLOW, this));
        colorPanel.add(new ColorPalette(Color.ORANGE, this));
        colorPanel.add(new ColorPalette(new Color(123, 35, 123), this));
        colorPanel.add(new ColorPalette(Color.BLACK, this));
        this.optionPanel.add(colorPanel, BorderLayout.NORTH);
        this.optionPanel.add(buttonPanel, BorderLayout.CENTER);
        this.optionPanel.add(getBlueLineSeparator(), BorderLayout.SOUTH);
        return optionPanel;
    }

    public DrawTable getDrawTable() {
        return drawTable;
    }

    public void setDrawTable(DrawTable drawTable) {
        this.drawTable = drawTable;
    }
}
