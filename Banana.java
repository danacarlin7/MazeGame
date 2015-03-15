package mazegame;
/**
 * A class that represents a banana in the maze.
 * This class is responsible for performing the following operations:
 * 1. At creation, it initializes the instance variables used to store the
 *        current location and value of the banana.
 * 2. It returns the value of the banana
 * 3. Returns the symbol associated with that Banana object.
 */
public class Banana extends Sprite{

    protected int value;

    /**
     * @param symbol symbol representation of Banana
     * @param row row location of Banana
     * @param col column location of Banana
     * @param value value of Banana
     */
    public Banana(char symbol, int row, int col, int value) {

        super(symbol, row, col);
        this.value = value;
    }

    /**
     * @return return value of Banana
     */
    public int getValue() {
        return value;
    }

    /**
     * @return String representation of symbol of Banana
     */
    @Override
    public String toString() {
        return String.valueOf(this.getSymbol());
    }
}
