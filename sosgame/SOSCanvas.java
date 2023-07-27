import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class SOSCanvas extends JPanel {
    private SOS sosReference;
    private int dimension;
    private String letterToBePlayed;
    private int rowIndex;
    private int columnIndex;

    public SOSCanvas(SOS sossy){
        this.sosReference = sossy;
        dimension = sossy.getDimension();
        this.setLayout(new GridLayout(dimension,dimension));
    }

    public SOS getSosReference() {
        return this.sosReference;
    }

    public void setDrawnLetter(String letterToBePlayed, int rowIndex, int columnIndex) {
        this.letterToBePlayed = letterToBePlayed;
        this.rowIndex = rowIndex;
        this.columnIndex = columnIndex;
    }

    //Override the paintComponent method

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        int dimension = sosReference.getDimension();
        for (int i = 1; i <= dimension; i++) {
            for (int j = 1; j <= dimension; j++) {
                g.drawRect(j*70,i*70,70,70);
            }
        }
        for (int i = 1; i <= dimension; i++) {
            for (int j = 1; j <= dimension; j++) {
                g.drawString(String.valueOf(sosReference.getCellContents(i-1,j-1)), j*70 + 35, i*70 + 35);
            }
        }
    }
}
