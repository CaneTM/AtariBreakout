import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;

/**
 * A JPanel that controls the game's components
 *
 * @author Canaan Matias
 */
public class GamePanel extends JPanel {

    /**
     * Contains the bricks in the game
     */
    private Brick[][] bricks = new Brick[GameConstants.NUM_BRICKS_Y][GameConstants.NUM_BRICKS_X];

    /**
     * x coordinate for paddle
     */
    private int paddleX;

    /**
     * x coordinate for ball
     */
    private int ballX;

    /**
     * y coordinate for ball
     */
    private int ballY;

    /**
     * Velocity of ball in x direction
     */
    private int ballSpeedX;

    /**
     * Velocity of ball in y direction
     */
    private int ballSpeedY;

    /**
     * Player's score
     */
    private int points;

    /**
     * Colors for each row of bricks
     */
    private final Color[] colors = {Color.red, Color.orange, Color.yellow, Color.green, Color.blue};

    /**
     * Used to determine whether space bar has been pressed
     */
    private boolean isSpacePressed = false;

    /**
     * Initiates or terminates the game loop
     */
    private boolean isRunning = true;


    /**
     * Initializes the game
     *
     * @param width  screen width
     * @param height screen height
     */
    public GamePanel(int width, int height) {
        this.paddleX = (width - GameConstants.PADDLE_WIDTH) / 2;

        this.ballX = width / 2 - 10;
        this.ballY = GameConstants.PADDLE_Y - GameConstants.BALL_RADIUS - 10;
        this.ballSpeedX = 2;
        this.ballSpeedY = -ballSpeedX;

        this.points = 0;

        int brickWidth = width / GameConstants.NUM_BRICKS_X;
        int brickHeight = brickWidth / 3;

        int xCoord = 0;

        for (int i = 0; i < bricks.length; i++) {
            for (int j = 0; j < bricks[i].length; j++) {

                bricks[i][j] = new Brick(xCoord, brickHeight,
                        brickWidth, brickHeight, GameConstants.BRICK_POINTS, i);
            }
        }

        setSize(width, height);
        setBackground(Color.black);

        KeyActionListener kal = new KeyActionListener(this);
        addKeyListener(kal);
        setFocusable(true);
    }


    /**
     * Renders the bricks onto the screen
     *
     * @param g handles the graphics
     */
    private void drawBricks(Graphics g) {
        int brickWidth = GameConstants.SCREEN_WIDTH / GameConstants.NUM_BRICKS_X;
        int brickHeight = brickWidth / 3;

        int xCoord = 0;
        int yCoord = brickHeight;

        for (int i = 0; i < bricks.length; i++) {
            for (int j = 0; j < bricks[i].length; j++) {

                // Render all non-null bricks
                if (bricks[i][j] != null) {
                    Color brickColor = bricks[i][j].getId() == -1 ? Color.black : colors[i];

                    // Store brick data in Brick object
                    bricks[i][j] = new Brick(xCoord, yCoord,
                            brickWidth, brickHeight,
                            GameConstants.BRICK_POINTS, bricks[i][j].getId());

                    // Graphical representation of the data
                    g.setColor(brickColor);
                    g.fillRect(bricks[i][j].getXCoord(), bricks[i][j].getYCoord(),
                            bricks[i][j].getWidth(), bricks[i][j].getHeight());

                    // Make Brick object null if hit
                    if (bricks[i][j].getId() == -1) {
                        points += bricks[i][j].getPoints();
                        bricks[i][j] = null;
                    }
                }
                xCoord += brickWidth;
            }
            xCoord = 0;
            yCoord += brickHeight;
        }
    }


    /**
     * Renders the paddle onto the screen
     *
     * @param g handles the graphics
     */
    private void drawPaddle(Graphics g) {
        g.setColor(Color.white);
        g.fillRect(paddleX, GameConstants.PADDLE_Y,
                GameConstants.PADDLE_WIDTH, GameConstants.PADDLE_HEIGHT);
    }


    /**
     * Moves the paddle left
     */
    public void moveLeft() {
        if (paddleX > 0) {
            paddleX += -GameConstants.PADDLE_SPEED;

            if (!isSpacePressed)
                ballX += -GameConstants.PADDLE_SPEED;
        }
    }


    /**
     * Moves the paddle right
     */
    public void moveRight() {
        if (paddleX + GameConstants.PADDLE_WIDTH < GameConstants.SCREEN_WIDTH) {
            paddleX += GameConstants.PADDLE_SPEED;

            if (!isSpacePressed)
                ballX += GameConstants.PADDLE_SPEED;
        }
    }


    /**
     * Renders the ball onto the screen
     *
     * @param g handles the graphics
     */
    private void drawBall(Graphics g) {
        g.setColor(Color.white);
        g.fillOval(ballX, ballY, GameConstants.BALL_RADIUS, GameConstants.BALL_RADIUS);
    }


    /**
     * Manages the physics, collision detection, and movement of the ball
     */
    private void moveBall() {
        ballX += ballSpeedX;
        ballY += ballSpeedY;

        // hits left or right walls
        if (ballX < 0 || ballX + GameConstants.BALL_RADIUS > GameConstants.SCREEN_WIDTH)
            ballSpeedX = -ballSpeedX;
        // hits screen top
        if (ballY < 0)
            ballSpeedY = -ballSpeedY;
        // hits screen bottom
        if (ballY + (GameConstants.BALL_RADIUS *
                GameConstants.MULTIPLIER) > GameConstants.SCREEN_HEIGHT) {
            isRunning = false;
        }
        // hits top of paddle
        if (ballY + GameConstants.BALL_RADIUS > GameConstants.PADDLE_Y) {
            if (ballX >= paddleX &&
                    ballX + GameConstants.BALL_RADIUS <= paddleX + GameConstants.PADDLE_WIDTH) {
                ballSpeedY = -ballSpeedY;
            }
        }

        // Iterate over each brick for collision detection
        for (int i = 0; i < bricks.length; i++) {
            for (int j = 0; j < bricks[i].length; j++) {

                // A Brick object with an id of -1 indicates it has been hit
                if (bricks[i][j] != null) {
                    Brick currentBrick = bricks[i][j];

                    // hits bottom of brick
                    if (ballY <= currentBrick.getYCoord() + currentBrick.getHeight() &&
                            ballY >= currentBrick.getYCoord()) {
                        if (ballX >= currentBrick.getXCoord() &&
                                ballX <= currentBrick.getXCoord() + currentBrick.getWidth()) {

                            ballSpeedY = -ballSpeedY;
                            currentBrick.setId(-1);
                        }
                    }
                    // hits top of brick
                    else if (ballY + GameConstants.BALL_RADIUS >= currentBrick.getYCoord() &&
                            ballY <= currentBrick.getYCoord() + currentBrick.getHeight()) {
                        if (ballX >= currentBrick.getXCoord() &&
                                ballX + GameConstants.BALL_RADIUS <= currentBrick.getXCoord() +
                                        currentBrick.getWidth()) {

                            ballSpeedY = -ballSpeedY;
                            currentBrick.setId(-1);
                        }
                    }
                    // hits right side of brick
                    else if (ballX <= currentBrick.getXCoord() + currentBrick.getWidth() &&
                            ballX >= currentBrick.getXCoord()) {
                        if (ballY + GameConstants.BALL_RADIUS <= currentBrick.getYCoord() +
                                currentBrick.getHeight() &&
                                ballY + GameConstants.BALL_RADIUS >= currentBrick.getYCoord()) {

                            ballSpeedX = -ballSpeedX;
                            currentBrick.setId(-1);
                        }
                    }
                    // hits left side of brick
                    else if (ballX >= currentBrick.getXCoord() &&
                            ballX <= currentBrick.getXCoord() + currentBrick.getWidth()) {
                        if (ballY + GameConstants.BALL_RADIUS <= currentBrick.getYCoord() +
                                currentBrick.getHeight() &&
                                ballY + GameConstants.BALL_RADIUS >= currentBrick.getYCoord()) {

                            ballSpeedX = -ballSpeedX;
                            currentBrick.setId(-1);
                        }
                    }
                }
            }
        }
    }


    /**
     * Renders the score onto the screen
     *
     * @param g handles the graphics
     */
    private void drawScore(Graphics g) {
        g.setFont(new Font("Verdana", Font.PLAIN, GameConstants.SCORE_FONT_SIZE));
        g.drawString("POINTS: " + points, GameConstants.SCORE_TEXT_X, GameConstants.SCORE_TEXT_Y);
    }


    /**
     * Handles rendering of components
     *
     * @param g handles the graphics
     */
    @Override
    public void paintComponent(Graphics g) {

        super.paintComponent(g);

        drawBricks(g);
        drawPaddle(g);
        drawBall(g);
        drawScore(g);
    }


    /**
     * Main game loop
     *
     * @throws InterruptedException  for problems related to Thread.sleep
     * @throws FileNotFoundException for problems related to file I/O
     */
    public void run() throws InterruptedException, FileNotFoundException {

        while (isRunning) {

            if (isSpacePressed)
                moveBall();

            Thread.sleep(GameConstants.SLEEP_VAL);
            repaint();

            if (points == GameConstants.MAX_POINTS)
                break;
        }
        handleGameOver();
    }


    /**
     * Handles the game over sequence
     *
     * @throws FileNotFoundException for problems related to file I/O
     */
    private void handleGameOver() throws FileNotFoundException {

        String gameOver = "Game Over!";
        String youWin = "You Win!";
        String message = points == GameConstants.MAX_POINTS ? youWin : gameOver;
        message += " Your score: " + points + "\nEnter your name below:";
        String playerName = JOptionPane.showInputDialog(message);

        // Create a separate JPanel to display scores
        ScorePanel panel = new ScorePanel();
        panel.addEntry(playerName, points);
        panel.displayScores();

        // Add the JPanel to a new JFrame
        new Scoreboard(panel);
    }


    /**
     * Sets isSpacePressed to a boolean value
     *
     * @param spacePressed the value that isSpacePressed will store
     */
    public void setSpacePressed(boolean spacePressed) {
        isSpacePressed = spacePressed;
    }
}
