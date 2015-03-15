package mazegame;

/**
 * An interface that represents the basic functionality of all Grids.
 * This class is responsible for performing the following operations:
 * 1. This interface includes methods to set and get cells
 * 2. Methods to get the number of columns and rows
 * 3. Equality and String method specific to Grid objects
 */
public interface Grid <T> {

    public void setCell(int row, int col, T item);

    public T getCell(int row, int col);

    public int getNumRows();

    public int getNumCols();

    public boolean equals(Grid<T> other);

    public String toString();
}
