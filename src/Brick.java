/**
 * Holds the data for each brick in the game
 *
 * @author Canaan Matias
 */
public class Brick {

    /**
     * x coordinate for brick
     */
    private int xCoord;

    /**
     * y coordinate for brick
     */
    private int yCoord;

    /**
     * Width of brick
     */
    private int width;

    /**
     * Height of brick
     */
    private int height;

    /**
     * Number of points the brick is worth
     */
    private int points;

    /**
     * Brick ID
     */
    private int id;


    /**
     * Initializes a Brick object
     *
     * @param xCoord x coordinate for brick
     * @param yCoord y coordinate for brick
     * @param width  width of brick
     * @param height width of brick
     * @param points number of points the brick is worth
     * @param id     ID of brick
     */
    public Brick(int xCoord, int yCoord, int width, int height, int points, int id) {

        this.xCoord = xCoord;
        this.yCoord = yCoord;
        this.width = width;
        this.height = height;
        this.points = points;
        this.id = id;
    }

    /**
     * Retrieves brick's x coord
     *
     * @return x coordinate of brick
     */
    public int getXCoord() {
        return xCoord;
    }

    /**
     * Retrieves brick's y coord
     *
     * @return y coordinate of brick
     */
    public int getYCoord() {
        return yCoord;
    }

    /**
     * Retrieves brick's width
     *
     * @return width of brick
     */
    public int getWidth() {
        return width;
    }

    /**
     * Retrieves brick's height
     *
     * @return height of brick
     */
    public int getHeight() {
        return height;
    }

    /**
     * Retrieves brick's point value
     *
     * @return point value of brick
     */
    public int getPoints() {
        return points;
    }

    /**
     * Retrieves brick's ID
     *
     * @return ID of brick
     */
    public int getId() {
        return id;
    }

    /**
     * Sets ID of brick
     *
     * @param id new ID of the brick
     */
    public void setId(int id) {
        this.id = id;
    }
}
