package cpsc2150.extendedConnectX.models;

import java.util.Arrays;

/**
 * <p>The class makes the game board for connect X and updates it as the game
 * progresses</p>
 *
 * @author Josh Grey
 * @version 1.0
 * @invariant There are no gaps between non-space tokens AND
 *            MIN_ROWS_AND_COLUMNS <= rows <= MAX_ROWS_AND_COLUMNS AND
 *            MIN_ROWS_AND_COLUMNS <= columns <= MAX_ROWS_AND_COLUMNS AND
 *            MIN_NUM_TO_WIN <= numToWin <= MAX_NUM_TO_WIN AND
 *            numToWin <= rows AND numToWin <= columns
 * @correspondence self = board
 */
public class GameBoard extends AbsGameBoard implements IGameBoard{
    private char[][] board;
    private int rows;
    private int columns;
    private int numToWin;

    /**
     * This constructor creates the game board
     * @param r number of rows
     * @param c number of columns
     * @param n number in a row to win
     *
     * @pre r {@code <=} MAX_ROWS_AND_COLUMNS AND r {@code >=} MIN_ROWS_AND_COLUMNS
     *      AND c {@code <=} MAX_ROWS_AND_COLUMNS AND c {@code >=} MIN_ROWS_AND_COLUMNS
     *      n {@code <=} MAX_NUM_TO_WIN AND n {@code >=} MIN_NUM_TO_WIN
     *      AND n {@code <=} r AND n {@code <=} c
     * @post rows = r AND columns = c AND numToWin = n AND board = new char[r][c]
     */
    public GameBoard(int r, int c, int n){
        rows = r;
        columns = c;
        numToWin = n;
        board = new char[r][c];
        for(int i = 0; i < rows; ++i){
           Arrays.fill(board[i], ' ');
       }
    }

    /**
     * This method places a token in the lowest possible row on the
     * @param p game token
     * @param c column selected
     * @pre
     *      c {@code >=} 0 AND c {@code <} 7 AND checkIfFree AND
     *      [p is a valid game token]
     * @post
     *      board[i][c] = p, where i is the lowest possible row that is free AND rows = #rows
     *      AND columns = #columns AND numToWin = #numToWin
     */
    @Override
    public void placeToken(char p, int c){
        for(int i = 0; i < getNumRows(); ++i){
            if(board[i][c] == ' '){
                board[i][c] = p;
                return;
            }
        }
    }
    /**
     * This method returns the character of what is at this specific position
     * on the board
     * @param pos position
     * @return character on board, ' ' if blank
     * @pre
     *          pos.getRow {@code >=} 0 AND pos.getRow {@code <} 9
     *          AND pos.getColumn {@code >=} 0 AND pos.getColumn {@code <} 7
     * @post
     *          whatsAtPos = board[pos.getRow][pos.getColumn]
     */
    @Override
    public char whatsAtPos(BoardPosition pos){
        return board[pos.getRow()][pos.getColumn()];
    }
    @Override
    public int getNumRows() {
        return rows;
    }

    @Override
    public int getNumColumns() {
        return columns;
    }

    @Override
    public int getNumToWin() {
        return numToWin;
    }
}
