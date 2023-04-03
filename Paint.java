import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Paint extends JFrame {

    private Options optionsPanel;
    private DrawTable drawTable;

    public Paint(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 1000);
        setLocationRelativeTo(null);
        this.optionsPanel = new Options(this);
        add(optionsPanel, BorderLayout.NORTH);
        this.drawTable = new DrawTable();
        add(drawTable, BorderLayout.CENTER);
        setVisible(true);
    }

    public DrawTable getDrawTable() {
        return drawTable;
    }

    public void setDrawTable(DrawTable drawTable) {
        this.drawTable = drawTable;
    }
}
