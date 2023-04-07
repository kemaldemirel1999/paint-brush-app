import javax.swing.*;
import java.awt.*;

public class Paint extends JFrame {

    private Options optionsPanel;
    private DrawTable drawTable;

    /*
        option panel: buttons and colors
        drawTable: the area that you can draw objects and writing with pen.
        Objects(rectangle and ellipse) can be moved inside drawTable.
     */
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
