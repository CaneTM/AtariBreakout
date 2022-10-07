import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Handles actions performed on the keyboard
 *
 * @author Canaan Matias
 */
public class KeyActionListener implements KeyListener {

    /**
     * JPanel that the action listener listens to actions from
     */
    private GamePanel panel;


    /**
     * Initializes a KeyActionListener
     *
     * @param panel JPanel that the action listener listens to actions from
     */
    public KeyActionListener(GamePanel panel) {
        this.panel = panel;
    }


    /**
     * Called when a key is typed
     *
     * @param e key event
     */
    @Override
    public void keyTyped(KeyEvent e) {

    }


    /**
     * Called when a key is pressed
     *
     * @param e key event
     */
    @Override
    public void keyPressed(KeyEvent e) {

        if (e.getKeyChar() == 'a' || e.getKeyChar() == 'A') {
            panel.moveLeft();
        } else if (e.getKeyChar() == 'd' || e.getKeyChar() == 'D') {
            panel.moveRight();
        } else if (e.getKeyChar() == ' ') {
            panel.setSpacePressed(true);
        }
    }


    /**
     * Called when a key is released
     *
     * @param e key event
     */
    @Override
    public void keyReleased(KeyEvent e) {

    }
}
