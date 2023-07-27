import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class SOSGUIPanel extends JPanel {
    private SOSCanvas sosCanvas;
    private JComboBox letterComboBox;
    private JPanel upperPanel, belowPanel;
    private String player1, player2;
    private JLabel player1Label, player2Label, player1ScoreLabel, player2ScoreLabel, slashLabel;
    public SOSGUIPanel(SOSCanvas sosCanvas, String player1, String player2) {
        setLayout(new BorderLayout());
        this.sosCanvas = sosCanvas;
        CoordinateListener coordsListener = new CoordinateListener();
        sosCanvas.addMouseListener(coordsListener);
        this.player1 = player1;
        this.player2 = player2;

        //Upper Panel
        this.upperPanel = new JPanel();
        this.player1Label = new JLabel(player1);
        this.player1Label.setForeground(Color.green);
        this.player1ScoreLabel = new JLabel("0");
        this.player2Label = new JLabel(player2);
        this.player2ScoreLabel = new JLabel("0");
        this.slashLabel = new JLabel("-");
        upperPanel.add(player1Label);
        upperPanel.add(player1ScoreLabel);
        upperPanel.add(slashLabel);
        upperPanel.add(player2ScoreLabel);
        upperPanel.add(player2Label);

        //Below Panel
        this.belowPanel = new JPanel();
        this.letterComboBox = new JComboBox();
        this.letterComboBox.addItem("s");
        this.letterComboBox.addItem("o");
        this.belowPanel.add(letterComboBox);

        //Adding all components
        add(this.upperPanel, BorderLayout.NORTH);
        add(this.belowPanel, BorderLayout.SOUTH);
        add(this.sosCanvas, BorderLayout.CENTER);
    }

    private class CoordinateListener extends MouseAdapter {

        private int x;
        private int y;

        @Override
        public void mouseClicked(MouseEvent e) {
            this.x = e.getX();
            this.y = e.getY();
            int rowIndex = 0;
            int columnIndex = 0;

            //Determining which cell is clicked
            for (int i = 0; i <= 5; i++) {
                if (this.y > i * 70 && this.y < (i + 1) * 70) {
                    rowIndex = i;
                    break;
                }
            }
            for (int j = 0; j <= 5; j++) {
                    if (this.x > j * 70 && this.x < (j + 1) * 70) {
                        columnIndex = j;
                        break;
                    }
            }
            //Playing and printing the board (optional)
            char playLetter = ((String)letterComboBox.getSelectedItem()).charAt(0);
            String playLetterStringVersion = (String)letterComboBox.getSelectedItem();

            sosCanvas.getSosReference().play(playLetter, rowIndex,columnIndex);
            sosCanvas.getSosReference().printBoard();

            //Determining who's turn is it
            if (sosCanvas.getSosReference().getTurn() == 1) {
                player1Label.setForeground(Color.green);
                player2Label.setForeground(Color.gray);
            } else {
                player2Label.setForeground(Color.green);
                player1Label.setForeground(Color.gray);
            }
            player1ScoreLabel.setText(""+sosCanvas.getSosReference().getPlayerScore1());
            player2ScoreLabel.setText(""+sosCanvas.getSosReference().getPlayerScore2());
            repaint();
            if (sosCanvas.getSosReference().isGameOver() && sosCanvas.getSosReference().getPlayerScore1() > sosCanvas.getSosReference().getPlayerScore2()) {
                JOptionPane.showConfirmDialog(null, "The game is finished. The winner is " + player1, "SOS Game", JOptionPane.WARNING_MESSAGE);
            } else if (sosCanvas.getSosReference().isGameOver() && sosCanvas.getSosReference().getPlayerScore1() < sosCanvas.getSosReference().getPlayerScore2()) {
                JOptionPane.showConfirmDialog(null, "The game is finished. The winner is " + player2, "SOS Game", JOptionPane.WARNING_MESSAGE);
            } else if (sosCanvas.getSosReference().isGameOver() && sosCanvas.getSosReference().getPlayerScore1() == sosCanvas.getSosReference().getPlayerScore2()) {
                JOptionPane.showConfirmDialog(null, "The game is finished. It's a draw. ", "SOS Game", JOptionPane.WARNING_MESSAGE);
            }
        }
    }
}

