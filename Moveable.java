package mazegame;

/**
 * An interface that is implemented by all moving objects in the game.
 * This interface is responsible for defining the following operations:
 * 1. Contains method for objects to move around the grid by row and col values.
 */
public interface Moveable {

    public void move(int row, int col);

}
