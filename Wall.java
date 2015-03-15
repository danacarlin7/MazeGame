package mazegame;

/**
 * A class that represents the Wall in maze.
 * This class is responsible for performing the following operations:
 * 1. At creation, it initializes the instance variables used to store the
 *        current location and symbol of the Sprite.
 * 2. Contains a method to return string value of symbol.
 */
public class Wall extends Sprite{

    /**
     * @param symbol symbolic representation of Wall
     * @param row row location of Wall
     * @param col column location of Wall
     */
    public Wall(char symbol, int row, int col){

        super(symbol, row, col);
    }

    /**
     * @return string representation of symbol of Wall
     */
    @Override
    public String toString() {
        return String.valueOf(this.getSymbol());
    }
}
