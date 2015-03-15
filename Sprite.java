package mazegame;

/**
 * A class that represents a single symbol for all items in maze.
 * This class is responsible for performing the following operations:
 * 1. At creation, it initializes the instance variables used to store the
 *        current coordinate location and symbol of the item.
 * 2. Contains methods to get row, column and symbol of the Sprite.
 */
public abstract class Sprite {


    /**symbol representation for Sprite */
    protected char symbol;

    /** row location of Sprite */
    protected int row;

    /** column location of Sprite*/
    protected int col;

    /**
     * @param symbol symbol representation of Sprite
     * @param row row location of Sprite
     * @param col column location of Sprite
     */
    public Sprite(char symbol, int row, int col){

        this.symbol = symbol;
        this.row = row;
        this.col = col;
    }

    /**
     * @return Symbol of Sprite
     */
    public char getSymbol() {
        return symbol;
    }

    /**
     * @return row location of Sprite
     */
    public int getRow() {
        return row;
    }

    /**
     * @return column location of Sprite
     */
    public int getCol() {
        return col;
    }

    /**
     * @return String representation of symbol of Sprite
     */
    @Override
    public abstract String toString();
}
