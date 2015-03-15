package mazegame;

/**
 * A class that represents a grid in a 2D array.
 * This class is responsible for performing the following operations:
 * 1. At creation, it initializes a 2D array with a size specified by numRows and numCols.
 * 2. It prints out the 2D array in the form of a maze grid.
 * 3. It changes the cells specified at row and col.
 */

public class ArrayGrid<T>  implements Grid<T> {

    private int numRows;
    private int numCols;
    private T[][] gridHolder;


    /**
     * @param numRows number of rows in the Array
     * @param numCols number of columns in the Array
     */
    public ArrayGrid(int numRows, int numCols) {

        gridHolder = (T[][]) new Object[numRows][numCols];
        this.numRows = numRows;
        this.numCols = numCols;

    }

    /**
     * @param row row location of cell you are changing
     * @param col column location of cell you are changing
     * @param item Object that will be at location row, col
     */
    @Override
    public void setCell(int row, int col, T item) {
        gridHolder[row][col] = item;

    }

    /**
     * @param row row location of cell
     * @param col column location of cell
     * @return cell at location (row, col) in gridHolder
     */
    @Override
    public T getCell(int row, int col) {
        return gridHolder[row][col];
    }

    /**
     * @return number of rows
     */
    @Override
    public int getNumRows() {
        return this.numRows;
    }

    /**
     * @return number of columns
     */
    @Override
    public int getNumCols() {
        return this.numCols;
    }

    /**
     * @param other a second Grid<T> object
     * @return true iff the string representation of both grids are identical
     */
    @Override
    public boolean equals(Grid<T> other) {

        return this.toString().equals(other.toString());
    }

    /**
     * @return string representation of ArrayGrid
     */
    public String toString() {
        String result = "";

        for (int i = 0; i < this.numRows; i++) {
            for (int j = 0; j < this.numCols; j++) {
                result += gridHolder[i][j];
            }

            result += "\n";
        }

        return result;
    }
}
