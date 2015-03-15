package mazegame;

/**
 * A class that represents a subclass of the Banana Class.
 * This class is responsible for performing the following operations:
 * 1. At creation, it initializes the instance variables used to store the
 *        location and value of the banana.
 * 2. Contains a string method to show the representation of a MobileBanana.
 * 3. Contains a method to move the MobileBanana randomly
 */
public class MobileBanana extends Banana implements Moveable{

    /**
     * @param symbol char representation of MobileBanana
     * @param row row location of MobileBanana
     * @param col column location of MobileBanana
     * @param value how much score is increased when monkey eats MobileBanana
     */
    public MobileBanana(char symbol, int row, int col, int value){

        super(symbol, row, col, value);
        this.value = MazeConstants.MOBILE_BANANA_SCORE;
    }

    /**
     * @param row how much to increase the row location the MobileBanana
     * @param col how much to increase the column location of MobileBanana
     */
    public void move(int row, int col){

        this.row += row;
        this.col += col;
    }

    /**
     * @return String value of MobileBanana symbol.
     */
    @Override
    public String toString() {
        return String.valueOf(this.getSymbol());
    }
}
