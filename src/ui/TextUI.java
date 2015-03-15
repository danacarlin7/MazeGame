package ui;

import mazegame.Grid;
import mazegame.MazeGame;
import java.util.*;


public class TextUI implements UI{

    private MazeGame game;

    private String nextMove;

    public TextUI(MazeGame game) {

        this.game = game;

    }

    public void displayWinner() {

        int won = game.hasWon();
        String message;

        if (game.isBlocked()) { // no winners
            message = "Game over! Both players are stuck.";
        } else {
            if (won == 0) { // game is still on
                return;
            } else if (won == 1) {
                message = "Congratulations Player 1! You won the maze in " +
                        game.getPlayerOne().getNumMoves() + " moves.";
            } else if (won == 2) {
                message = "Congratulations Player 2! You won the maze in " +
                        game.getPlayerTwo().getNumMoves() + " moves.";
            } else { // it's a tie
                message = "It's a tie!";
            }
        }

        System.out.println(message);
    }


    public void launchGame(){

        System.out.println(game.getMaze());
        while(game.isBlocked() == false) {
            Scanner newMove = new Scanner(System.in);
            System.out.print("Please enter next move: ");
            nextMove = newMove.next();
            game.move(nextMove.charAt(0));
            System.out.print(game.getMaze());
        }
    }

}
