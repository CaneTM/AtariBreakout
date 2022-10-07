import javax.swing.*;

/**
 * JFrame for displaying the scores
 *
 * @author Canaan Matias
 */
public class Scoreboard extends JFrame {

    /**
     * Title of JFrame
     */
    public static final String TITLE = "Scoreboard";

    /**
     * Width of JFrame
     */
    public static final int SCREEN_WIDTH = 400;

    /**
     * Height of JFrame
     */
    public static final int SCREEN_HEIGHT = 600;


    /**
     * Initializes a JFrame
     *
     * @param panel JPanel to be added to the frame
     */
    public Scoreboard(ScorePanel panel) {

        setTitle(TITLE);
        setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);

        add(panel);

        setVisible(true);
    }
}
