package cpsc2150.extendedConnectX;

import cpsc2150.extendedConnectX.models.GameBoard;
import cpsc2150.extendedConnectX.models.GameBoardMem;
import cpsc2150.extendedConnectX.models.IGameBoard;

import java.util.*;


public class GameScreen {
    public static void main(String[] args) {
        boolean inGame = true;
        char[] players;
        int numPlayers;
        Scanner scanner = new Scanner(System.in);
        char choice;
        int column;
        boolean inBounds;
        char player;
        int r;
        int c;
        int n;
        do {
            IGameBoard gameBoard = null;
            System.out.println("How many players?");
            numPlayers = Integer.parseInt(scanner.nextLine());
            while(numPlayers > gameBoard.MAX_PLAYERS || numPlayers < gameBoard.MIN_PLAYERS){
                if(numPlayers > gameBoard.MAX_PLAYERS){
                    System.out.println("Must be " + gameBoard.MAX_PLAYERS + " players or fewer");
                    System.out.println("How many players?");
                    numPlayers = Integer.parseInt(scanner.nextLine());
                }
                else{
                    System.out.println("Must be at least " + gameBoard.MIN_PLAYERS + " players");
                    System.out.println("How many players?");
                    numPlayers = Integer.parseInt(scanner.nextLine());
                }
            }
            players = new char[numPlayers];
            boolean contains = false;
            for(int i = 0; i < numPlayers; ++i) {
                System.out.println("Enter the character to represent player " + (i + 1));
                player = scanner.nextLine().toUpperCase().charAt(0);
                for(char p : players){
                   if(p == player){
                       contains = true;
                       break;
                   }
                }
                while(contains) {
                    System.out.println(player + " is already taken as player token!");
                    System.out.println("Enter the character to represent player " + (i + 1));
                    player = scanner.nextLine().toUpperCase().charAt(0);
                    contains = false;
                    for(char p : players){
                        if(p == player){
                            contains = true;
                            break;
                        }
                    }
                }
                players[i] = player;
            }
            System.out.println("How many rows should be on the board?");
            r = Integer.parseInt(scanner.nextLine());
            while(r > GameBoard.MAX_ROWS_AND_COLUMNS || r < GameBoard.MIN_ROWS_AND_COLUMNS){
                System.out.println("How many rows should be on the board?");
                r = Integer.parseInt(scanner.nextLine());
            }
            System.out.println("How many columns should be on the board?");
            c = Integer.parseInt(scanner.nextLine());
            while(c > GameBoard.MAX_ROWS_AND_COLUMNS || c < GameBoard.MIN_ROWS_AND_COLUMNS){
                System.out.println("How many columns should be on the board?");
                c = Integer.parseInt(scanner.nextLine());
            }
            System.out.println("How many in a row to win?");
            n = Integer.parseInt(scanner.nextLine());
            while(n < GameBoard.MIN_NUM_TO_WIN || n > GameBoard.MAX_NUM_TO_WIN || n > c || n > r){
                System.out.println("How many in a row to win?");
                n = Integer.parseInt(scanner.nextLine());
            }
            System.out.println("Would you like a Fast Game (F/f) or a Memory Efficient game (M/m)?");
            choice = scanner.nextLine().charAt(0);
            while(choice != 'F' && choice != 'f' && choice != 'm' && choice != 'M'){
                System.out.println("Please enter F or M");
                System.out.println("Would you like a Fast Game (F/f) or a Memory Efficient game (M/m)?");
                choice = scanner.nextLine().charAt(0);
            }
            if(choice == 'F' || choice == 'f'){
                gameBoard = new GameBoard(r, c, n);
            }
            else{
                gameBoard = new GameBoardMem(r, c, n);
            }
            System.out.println(gameBoard);
            int count = 0;
            while (!gameBoard.checkTie()) {
                System.out.println("Player " + players[count % players.length]
                        + ", what column do you want to place your marker in?");
                column = Integer.parseInt(scanner.nextLine());
                inBounds = (column >= 0) && (column < gameBoard.getNumColumns());
                while (!inBounds) {
                    if (column >= gameBoard.getNumColumns()) {
                        System.out.println("Column cannot be greater than " + (gameBoard.getNumColumns() - 1));
                        System.out.println("Player " + players[count % players.length]
                                + ", what column do you want to place your marker in?");
                        column = Integer.parseInt(scanner.nextLine());
                    } else {
                        System.out.println("Column cannot be less than " + 0);
                        System.out.println("Player " + players[count % players.length]
                                + ", what column do you want to place your marker in?");
                        column = Integer.parseInt(scanner.nextLine());
                    }
                    inBounds = column >= 0 && column < gameBoard.getNumColumns();
                }
                while (!gameBoard.checkIfFree(column)) {
                    System.out.println("Column is full");
                    System.out.println("Player " + players[count % players.length]
                            + ", what column do you want to place your marker in?");
                    column = Integer.parseInt(scanner.nextLine());
                    inBounds = column >= 0 && column < gameBoard.getNumColumns();
                    while (!inBounds) {
                        if (column >= gameBoard.getNumColumns()) {
                            System.out.println("Column cannot be greater than " + (gameBoard.getNumColumns() - 1));
                            System.out.println("Player " + players[count % players.length]
                                    + ", what column do you want to place your marker in?");
                            column = Integer.parseInt(scanner.nextLine());
                        } else {
                            System.out.println("Column cannot be less than " + 0);
                            System.out.println("Player " + players[count % players.length]
                                    + ", what column do you want to place your marker in?");
                            column = Integer.parseInt(scanner.nextLine());
                        }
                        inBounds = column >= 0 && column < gameBoard.getNumColumns();
                    }
                }
                gameBoard.placeToken(players[count % players.length], column);
                System.out.println(gameBoard);
                if (gameBoard.checkForWin(column)) {
                    System.out.println("Player " + players[count % players.length] + " Won!");
                    break;
                }
                count += 1;
            }
            System.out.println("Would you like to play again? Y/N");
            choice = scanner.nextLine().charAt(0);
            while (choice != 'y' && choice != 'n' && choice != 'Y' && choice != 'N') {
                System.out.println("Would you like to play again? Y/N");
                choice = scanner.nextLine().charAt(0);
            }
            if (choice == 'n' || choice == 'N') {
                inGame = false;
            }
        }while (inGame);
    }
}
