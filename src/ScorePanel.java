import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.Scanner;

/**
 * JPanel containing the scoreboard
 *
 * @author Canaan Matias
 */
public class ScorePanel extends JPanel {

    /**
     * Font size of heading
     */
    private final int headingSize = 30;

    /**
     * Font size of entry
     */
    private final int entrySize = 15;


    /**
     * Initializes a JPanel
     */
    public ScorePanel() {
        setBackground(Color.black);
    }


    /**
     * Displays the scores
     *
     * @throws FileNotFoundException for issues relating to file I/O
     */
    public void displayScores() throws FileNotFoundException {

        BoxLayout scoreLayout = new BoxLayout(this, BoxLayout.Y_AXIS);
        setLayout(scoreLayout);

        FileInputStream fis = new FileInputStream("scores.txt");
        Scanner in = new Scanner(fis);

        JLabel heading = new JLabel("SCORES:");
        heading.setAlignmentX(Component.CENTER_ALIGNMENT);
        heading.setForeground(Color.white);
        heading.setFont(new Font("Verdana", Font.PLAIN, headingSize));
        add(heading);

        while (in.hasNextLine()) {

            String line = in.nextLine();

            JLabel emptyLabel = new JLabel(" ");
            add(emptyLabel);

            JLabel label = new JLabel(line);
            label.setAlignmentX(Component.CENTER_ALIGNMENT);
            label.setForeground(Color.green);
            label.setFont(new Font("Verdana", Font.PLAIN, entrySize));
            add(label);
        }
    }


    /**
     * Adds a new score entry
     *
     * @param playerName name of player
     * @param points     the score that the player has attained
     * @throws FileNotFoundException for issues relating to file I/O
     */
    public void addEntry(String playerName, int points) throws FileNotFoundException {

        FileOutputStream fos = new FileOutputStream("scores.txt", true);
        PrintStream out = new PrintStream(fos);

        out.println(playerName + ": " + points);
    }
}
