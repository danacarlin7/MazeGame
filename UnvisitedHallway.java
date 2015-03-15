package mazegame;

/**
 * A class that represents the UnvisitedHallways in maze.
 * This class is responsible for performing the following operations:
 * 1. At creation, it initializes the instance variables used to store the
 *        current location and symbol of the Sprite.
 * 2. Contains a method to return string value of symbol.
 */
public class UnvisitedHallway extends Sprite{

    /**
     * @param symbol symbolic representation of UnvisitedHallway
     * @param row row location of UnvisitiedHallway
     * @param col column location of UnvisitiedHallway
     */
    public UnvisitedHallway(char symbol, int row, int col){

        super(symbol, row, col);
    }

    /**
     * @return string representation of symbol of UnvisitedHallway
     */
    @Override
    public String toString() {
        return String.valueOf(this.getSymbol());
    }
}
