import javax.swing.*;
import java.awt.*;

public class Main{
    public static void main(String[] args) {
        //Setting the Frame
        JFrame frame = new JFrame("SOS Frame");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(500,600);
        SOS sosSample = new SOS(3);
        SOSCanvas sosCanvas = new SOSCanvas(sosSample);
        SOSGUIPanel gamePanel = new SOSGUIPanel(sosCanvas, "Player 1", "Player 2");
        frame.add(gamePanel);
        frame.setVisible(true);
    }
}