import java.io.FileNotFoundException;

/**
 * A clone of the video game Atari Breakout
 * (Partnered with Jack Liu and Michael Ashe, 12/1/2021)
 *
 * @author Canaan Matias
 */
public class Main {

    /*
    --------- MUSIC CREDITS ---------

    * Song Title: The Escapists - Center Perks
    *
    * All credit for this music goes to Mouldy Toof Studios and their publisher Team17
    *
    * Can be found at: https://youtu.be/iVfvlHwEPlw
    *
    */

    /**
     * Starts the program
     *
     * @param args command line arguments
     * @throws InterruptedException  for problems related to Thread.sleep in main game loop
     * @throws FileNotFoundException for problems related to file I/O
     */
    public static void main(String[] args) throws InterruptedException, FileNotFoundException {

        // Create a JPanel
        GamePanel panel = new GamePanel(GameConstants.SCREEN_WIDTH, GameConstants.SCREEN_HEIGHT);

        // Create a JFrame and add the panel to it
        new Game(GameConstants.SCREEN_WIDTH, GameConstants.SCREEN_HEIGHT,
                GameConstants.TITLE, panel);
    }
}
