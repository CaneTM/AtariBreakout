import javax.sound.sampled.*;
import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;

/**
 * A JFrame that contains the game panel
 *
 * @author Canaan Matias
 */
public class Game extends JFrame {


    /**
     * Initializes a JFrame
     *
     * @param width  screen width
     * @param height screen height
     * @param title  title of game
     * @param panel  panel to be added to the frame
     * @throws InterruptedException  for problems related to Thread.sleep in main game loop
     * @throws FileNotFoundException for problems related to file I/O
     */
    public Game(int width, int height, String title, GamePanel panel)
            throws InterruptedException, FileNotFoundException {

        setTitle(title);

        setSize(width, height);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        // Make window appear in middle of screen
        setLocationRelativeTo(null);

        add(panel);
        setVisible(true);

        playMusic(GameConstants.SONG);
        panel.run();
    }


    /**
     * Plays the game music
     *
     * @param songTitle filepath of song to be played
     */
    private void playMusic(String songTitle) {

        try {
            // Create AudioInputStream from given sound file
            File audioFile = new File(songTitle);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);

            // Acquire audio format and create a DataLine.Info object
            AudioFormat format = audioStream.getFormat();
            DataLine.Info info = new DataLine.Info(Clip.class, format);

            // Create Clip object from info
            Clip audioClip = (Clip) AudioSystem.getLine(info);

            // Play audio and loop
            audioClip.open(audioStream);
            audioClip.start();
            audioClip.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (Exception e) {
            System.out.println("Trouble playing music");
        }
    }
}
