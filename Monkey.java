package mazegame;

/**
 * A class that represents the players of the game as Monkeys.
 * This class is responsible for performing the following operations:
 * 1. At creation, it initializes the instance variables used to store the
 *        the players score, location and symbolic representation.
 * 2. This class contains methods to retrieve the score, number of moves by the
 *         player.
 * 3. Contains methods to move the player and eat Banana objects to increase score.
 */
public class Monkey extends Sprite implements Moveable{

    private int score;
    private int numMoves;

    /**
     * @param symbol representation of Monkey
     * @param row row location of Monkey
     * @param col column location of monkey
     */
    public Monkey(char symbol, int row, int col){

        super(symbol, row, col);
        this.score = 0;
    }

    /**
     * @return string value of symbol of Monkey
     */
    @Override
    public String toString() {
        return String.valueOf(this.symbol);
    }

    /**
     * @param score Increases Monkey score by score
     */
    public void eatBanana(int score){

        this.score += score;
    }

    /**
     * @return score of Monkey
     */
    public int getScore() {
        return score;
    }

    /**
     * @return number of valid moves performed by Monkey
     */
    public int getNumMoves() {
        return numMoves;
    }

    /**
     * @param row how much to change Monkeys.row by
     * @param col how much to change Monkey.col by
     */
    public void move(int row, int col){

        this.row += row;
        this.col += col;
        this.numMoves ++;
    }
}
