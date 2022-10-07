/**
 * Holds the constant values for the game
 *
 * @author Canaan Matias
 */
public class GameConstants {

    // SCREEN ATTRIBUTES
    /**
     * Game title
     */
    public static final String TITLE = "Atari Breakout Clone";

    /**
     * Screen width
     */
    public static final int SCREEN_WIDTH = 800;

    /**
     * Screen height
     */
    public static final int SCREEN_HEIGHT = 600;

    /**
     * Sleep value in milliseconds
     */
    public static final long SLEEP_VAL = 11;


    // TOTAL BRICKS RENDERED
    /**
     * Number of bricks in each row
     */
    public static final int NUM_BRICKS_X = 10;

    /**
     * Number of rows of bricks
     */
    public static final int NUM_BRICKS_Y = 5;


    // PADDLE DIMENSIONS
    /**
     * Width of paddle
     */
    public static final int PADDLE_WIDTH = 120;

    /**
     * Height of paddle
     */
    public static final int PADDLE_HEIGHT = PADDLE_WIDTH / 8;

    /**
     * y coordinate of paddle
     */
    public static final int PADDLE_Y = 500;

    /**
     * Speed of paddle
     */
    public static final int PADDLE_SPEED = 25;


    // BALL DIMENSIONS
    /**
     * Radius of ball
     */
    public static final int BALL_RADIUS = 20;

    /**
     * Arbitrary multiplier for ball collision detection
     */
    public static final double MULTIPLIER = 2.5;


    // POINT SYSTEM
    /**
     * x coordinate for points display
     */
    public static final int SCORE_TEXT_X = 650;

    /**
     * y coordinate for points display
     */
    public static final int SCORE_TEXT_Y = 500;

    /**
     * Font size of points display
     */
    public static final int SCORE_FONT_SIZE = 20;

    /**
     * Number of points each brick is worth
     */
    public static final int BRICK_POINTS = 10;

    /**
     * Total number of points available
     */
    public static final int MAX_POINTS = NUM_BRICKS_X * NUM_BRICKS_Y * BRICK_POINTS;


    // MUSIC

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
     * Filepath of song
     */
    public static final String SONG = "The Escapists - Center Perks.wav";
}
