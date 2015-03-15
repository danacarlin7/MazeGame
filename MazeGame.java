package mazegame;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.IOException;
import java.util.Random;
import java.util.List;

/**
 * A class that represents the basic functionality of the maze game.
 * This class is responsible for performing the following operations:
 * 1. At creation, it initializes the instance variables used to store the
 *        current state of the game.
 * 2. When a move is specified, it checks if it is a legal move and makes the
 *        move if it is legal.
 * 3. It reports information about the current state of the game when asked.
 */
public class MazeGame {

    /** A random number generator to move the MobileBananas. */
    private Random random;
    
    /** The maze grid. */
    private Grid<Sprite> maze;
    
    /** The first player. */
    private Monkey player1;
    
    /** The second player. */
    private Monkey player2;

    /** The bananas to eat. */
    private List<Banana> bananas;

    
    /**
     * Creates a new MazeGame that corresponds to the maze in the file
     * named layoutFileName.
     * @param layoutFileName the path to the input maze file
     */
    public MazeGame(String layoutFileName) throws IOException {

        bananas = new ArrayList<Banana>();
        random = new Random();

        int[] dimensions = getDimensions(layoutFileName);

        maze = new ArrayGrid<Sprite>(dimensions[0], dimensions[1]);
               
        Scanner sc = new Scanner(new File(layoutFileName));

        /* INITIALIZE THE GRID HERE */

        String newLine = sc.nextLine();

        //Create new sprites for every character in the file
        //Type chosen depending on character value in file
        for(int row = 0; row < maze.getNumRows();row++ ){
            for(int col = 0; col < maze.getNumCols(); col++){
                char currentSprite = newLine.charAt(col);
                switch(currentSprite){
                    case MazeConstants.WALL:
                        Wall mazeWall = new Wall(MazeConstants.WALL, row, col);
                        maze.setCell(row, col, mazeWall);
                        break;
                    case MazeConstants.BANANA:
                        Banana mazeBanana = new
                                Banana(MazeConstants.BANANA,row , col,
                                MazeConstants.BANANA_SCORE);
                        maze.setCell(row, col, mazeBanana);
                        bananas.add(mazeBanana);
                        break;
                    case MazeConstants.MOBILE_BANANA:
                        MobileBanana mazeMobileBanana = new
                                MobileBanana(MazeConstants.MOBILE_BANANA, row,
                                col, MazeConstants.MOBILE_BANANA_SCORE);
                        maze.setCell(row, col, mazeMobileBanana);
                        bananas.add(mazeMobileBanana);
                        break;
                    case MazeConstants.VACANT:
                        UnvisitedHallway emptyHall = new UnvisitedHallway
                                (MazeConstants.VACANT, row, col);
                        maze.setCell(row, col, emptyHall);
                        break;
                    case MazeConstants.VISITED:
                        VisitedHallway nonEmptyHall = new VisitedHallway
                                (MazeConstants.VISITED, row, col);
                        maze.setCell(row, col, nonEmptyHall);
                        break;
                    case MazeConstants.P1:
                        player1 = new Monkey(MazeConstants.P1, row, col);
                        maze.setCell(row, col, player1);
                        break;
                    case MazeConstants.P2:
                        player2 = new Monkey(MazeConstants.P2, row, col);
                        maze.setCell(row, col, player2);
                        break;
                    default:
                        Wall unknown = new Wall('?', row, col);
                        maze.setCell(row, col, unknown);
                }
            }
            if(sc.hasNext()){
                newLine = sc.nextLine();
            }
        }

        sc.close();
    }
    
    /**
     * Returns the dimensions of the maze in the file named layoutFileName.
     * @param layoutFileName the path of the input maze file
     * @return an array [numRows, numCols], where numRows is the number
     * of rows and numCols is the number of columns in the maze that
     * corresponds to the given input maze file
     * @throws IOException
     */    
    private int[] getDimensions(String layoutFileName) throws IOException {       
        
        Scanner sc = new Scanner(new File(layoutFileName));

        // find the number of columns
        String nextLine = sc.nextLine();
        int numCols = nextLine.length();

        int numRows = 1;

        // find the number of rows
        while (sc.hasNext()) {
            numRows++;
            sc.nextLine();
        }

        sc.close();
        return new int[]{numRows, numCols};
    }

    /**
     * @return the MazeGame maze
     */
    public Grid<Sprite> getMaze() {
        return maze;
    }

    /**
     * @return number of rows in maze
     */
    public int getNumRows(){
        return maze.getNumRows();
    }

    /**
     * @return number of columns in maze
     */
    public int getNumCols(){
        return maze.getNumCols();
    }

    /**
     * @param i row value of cell
     * @param j column value of cell
     * @return the Sprite that is in cell located at row i and column j
     */
    public Sprite get(int i, int j){

         return maze.getCell(i, j);
    }

    /**
     * @param nextMove value entered by user
     *                 Moves are generated by associated value in MazeConstants
     */
    public void move(char nextMove) {

        //Creates a random value for moving mobile banana
        int randomMove = random.nextInt(3);

        //Sorts the input and makes the move accordingly
        if (nextMove == MazeConstants.P1_UP) {
            checkNextMove(player1, MazeConstants.UP, 0);
        } else if (nextMove == MazeConstants.P1_RIGHT) {
            checkNextMove(player1, 0, MazeConstants.RIGHT);
        } else if (nextMove == MazeConstants.P1_LEFT) {
            checkNextMove(player1, 0, MazeConstants.LEFT);
        } else if (nextMove == MazeConstants.P1_DOWN) {
            checkNextMove(player1, MazeConstants.DOWN, 0);
        } else if (nextMove == MazeConstants.P2_UP) {
            checkNextMove(player2, MazeConstants.UP, 0);
        } else if (nextMove == MazeConstants.P2_RIGHT) {
            checkNextMove(player2, 0, MazeConstants.RIGHT);
        } else if (nextMove == MazeConstants.P2_LEFT) {
            checkNextMove(player2, 0, MazeConstants.LEFT);
        } else if (nextMove == MazeConstants.P2_DOWN) {
            checkNextMove(player2, MazeConstants.DOWN, 0);
        } else {
            System.out.println("Unrecognized Move");
        }

        //Moves banana after moving players
        moveBanana(randomMove);
    }

    /**
     * @return true iff player1 and player2 can not make any valid moves
     */
    public boolean isBlocked(){

        return (checkArea(player1) && checkArea(player2));
    }

    /**
     * @return 1,2 or 3 if bananas is empty, which is when the game is over.
     * Return 1 if player1 won, return 2 if player2 won, return 3 if the game is
     * tied. Return 0 if bananas is not empty, meaning no one has won the game.
     */
    public int hasWon(){

        //Checks if bananas is empty
        if(bananas.isEmpty()){

            //Compares score of player1 and player2 to return appropriate winner
            if(player1.getScore() > player2.getScore()){
                return 1;
             }
            else if(player2.getScore() > player1.getScore()){
                return 2;
            }
            else{
                return 3;
                }
        }
        //If no one has won the game
        return 0;
    }

    /**
     * @return string representation of maze
     */
    @Override
    public String toString() {
        return "MazeGame{" +
                "random=" + random +
                ", maze=" + maze +
                ", player1=" + player1 +
                ", player2=" + player2 +
                ", bananas=" + bananas +
                '}';
    }

    /**
     * @return player1
     */
    public Monkey getPlayerOne() {

        return player1;


    }

    /**
     * @return player2
     */
    public Monkey getPlayerTwo() {
        return player2;
    }

    /**
     * @param newRow row the player is attempting to move to
     * @param newCol column the player is attempting to move to
     * @return true iff there not a VisitedHallway, other player or Wall in the location
     * the current player wants to move to.
     */
    public boolean checkValidMove(int newRow, int newCol){


        Sprite newCell = maze.getCell(newRow, newCol);

        return !(newCell instanceof Wall || newCell instanceof VisitedHallway
                || newCell instanceof Monkey);
    }

    /**
     * @param newRow row the banana is attempting to move to
     * @param newCol column the banana is attempting to move to
     * @return true iff there is unvisited hallway in new cell.
     */
    public boolean checkValidBananaMove(int newRow, int newCol){


        Sprite newCell = maze.getCell(newRow, newCol);

        return (newCell instanceof UnvisitedHallway);
    }

    /**
     * @param player the player in the game who is going to eat a banana
     * @param row the row that the player is moving to, also the location of the banana
     * @param col the column that the player is moving to, also the location of the banana
     */
    public void bananaMuncher(Monkey player, int row, int col){

        //Checks that at the location there is a banana
        //Eats banana and removes it from bananas
        if(maze.getCell(row, col) instanceof Banana) {
            player.eatBanana(((Banana) maze.getCell(row, col)).getValue());
            bananas.remove(maze.getCell(row, col));
        }
    }

    /**
     * @param randomMove the randomly generated value that will decide in which direction
     *                   the mobile banana will move. A value of 0 will move the banana up,
     *                   a value of 1 will move the banana right, a value of 2 will move
     *                   the banana down and a value of 3 will move the banana left.
     */

    public void moveBanana(int randomMove) {

        //Loops through the list bananas to find all MobileBananas and move them according to
        //randomMove
        for (int k = 0; k < bananas.size(); k++) {
            if (bananas.get(k).getSymbol() == MazeConstants.MOBILE_BANANA) {
                if (randomMove == 0) {
                    moveMobileBanana((MobileBanana) bananas.get(k), MazeConstants.UP, 0);
                }
                else if (randomMove == 1) {
                    moveMobileBanana((MobileBanana) bananas.get(k), 0, MazeConstants.RIGHT);

                }
                else if (randomMove == 2) {
                    moveMobileBanana((MobileBanana) bananas.get(k), MazeConstants.DOWN, 0);
                }
                else if (randomMove == 3) {
                    moveMobileBanana((MobileBanana) bananas.get(k), 0, MazeConstants.LEFT);
                }
            }

        }
    }
    /**
     * @param player the player who's area we are checking for a valid move
     * @return true iff the player is not blocked
     * a player is blocked if the surrounding cells are Walls, VisitedHallways or another Monkey.
     */

    public boolean checkArea(Monkey player){

        //The cells left, right, up and down from the location of player respectively.

        Sprite lCell = maze.getCell(player.getRow(), player.getCol() + MazeConstants.LEFT);
        Sprite rCell = maze.getCell(player.getRow(), player.getCol() + MazeConstants.RIGHT);
        Sprite uCell = maze.getCell(player.getRow() + MazeConstants.UP, player.getCol());
        Sprite dCell = maze.getCell(player.getRow() + MazeConstants.DOWN, player.getCol());

        //Checks if the cells are Visited Hallways, Walls or another player.
        if (!(lCell instanceof VisitedHallway || lCell instanceof Wall || lCell instanceof Monkey)){
            return false;
        }
        else if(!(rCell instanceof VisitedHallway || rCell instanceof Wall || rCell instanceof Monkey)){
            return false;
        }
        else if(!(uCell instanceof VisitedHallway || uCell instanceof Wall || uCell instanceof Monkey)){
            return false;
        }
        else if(!(dCell instanceof VisitedHallway || dCell instanceof Wall || dCell instanceof Monkey)){
            return false;
        }
        return true;
    }

    /**
     * @param player Monkey who is attempting to make a move
     * @param increaseRow how much to increase the current row location of player
     * @param increaseCol how much to increase the current column location of player
     */
    public void checkNextMove(Monkey player, int increaseRow, int increaseCol){

        //Variables to shorten lines of code
        int newRow = player.getRow() + increaseRow;
        int newCol = player.getCol() + increaseCol;
        VisitedHallway oldSpot = new VisitedHallway(MazeConstants.VISITED,
                player.getRow(), player.getCol());

        //Checks that player is within valid index for row and columns
        if(player.getCol() < maze.getNumCols() || player.getRow()
                < maze.getNumRows() || player.getCol() > 0 || player.getRow()
                > 0){
            if(checkValidMove(newRow, newCol)) {

                //If new cell is banana, eats banana, increases score and removes
                // banana from bananas.
                if(maze.getCell(newRow, newCol) instanceof Banana){
                    maze.setCell(player.getRow(), player.getCol(), oldSpot);
                    player.move(increaseRow, increaseCol);
                    bananaMuncher(player, newRow, newCol);
                    maze.setCell(player.getRow(), player.getCol(), player);
                }
                else{
                    maze.setCell(player.getRow(), player.getCol(), oldSpot);
                    player.move(increaseRow, increaseCol);
                    maze.setCell(player.getRow(), player.getCol(), player);
                }
            }
        }
    }

    /**
     * @param banana the specific MobileBanana we are moving randomly
     * @param increaseRow how much to increase the row of banana
     * @param increaseCol how much to increase the column of banana
     */
    public void moveMobileBanana(MobileBanana banana, int increaseRow, int increaseCol){

        int newRow = banana.getRow() + increaseRow;
        int newCol = banana.getCol() + increaseCol;
        UnvisitedHallway oldSpot = new UnvisitedHallway(MazeConstants.VACANT,
                banana.getRow(), banana.getCol());

        //Checks that banana is within appropriate index range, and that new move is valid
        if(banana.getCol() < maze.getNumCols() || banana.getRow()
                < maze.getNumRows() || banana.getCol() > 0 || banana.getRow()
                > 0){
            if(checkValidBananaMove(newRow, newCol)) {
                maze.setCell(banana.getRow(), banana.getCol(), oldSpot);
                maze.setCell(newRow, newCol, banana);
                banana.move(increaseRow, increaseCol);
            }
        }
    }
}

