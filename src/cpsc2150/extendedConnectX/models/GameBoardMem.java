package cpsc2150.extendedConnectX.models;

import java.util.*;

/**
 * This class is a more memory efficient version of game board.
 * @author Josh Grey
 * @version 1.0
 * @invariant MIN_ROWS_AND_COLUMNS <= rows <= MAX_ROWS_AND_COLUMNS AND
 *            MIN_ROWS_AND_COLUMNS <= columns <= MAX_ROWS_AND_COLUMNS AND
 *            MIN_NUM_TO_WIN <= numToWin <= MAX_NUM_TO_WIN AND
 *            numToWin <= rows AND numToWin <= columns
 * @correspondence self = board
 */
public class GameBoardMem extends AbsGameBoard implements IGameBoard  {
    private Map<Character, List<BoardPosition>> board;
    private int rows;
    private int columns;
    private int numToWin;

    /**
     * This Constructor creates an empty map with each player token and initializes
     * the number of rows, columns, tokens in a row to win
     * @param r number of rows
     * @param c number of columns
     * @param n number needed to win
     * @pre r {@code <=} MAX_ROWS_AND_COLUMNS AND r {@code >=} MIN_ROWS_AND_COLUMNS
     *      AND c {@code <=} MAX_ROWS_AND_COLUMNS AND c {@code >=} MIN_ROWS_AND_COLUMNS
     *      n {@code <=} MAX_NUM_TO_WIN AND n {@code >=} MIN_NUM_TO_WIN AND
     *      n {@code <=} r AND n {@code <=} c
     * @post rows = r AND columns = c AND numToWin = n AND board = HashMap<>()
     */
    public GameBoardMem(int r, int c, int n){
        board = new HashMap<>();
        rows = r;
        columns = c;
        numToWin = n;
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

    @Override
    public void placeToken(char p, int c) {
        if(!board.containsKey(p)){
            board.put(p, new ArrayList<>());
        }
        BoardPosition pos;
        for(int i = 0; i < getNumRows(); i++){
            pos = new BoardPosition(i, c);
            if(whatsAtPos(pos) == ' '){
                board.get(p).add(pos);
                return;
            }
        }
    }

    @Override
    public char whatsAtPos(BoardPosition pos) {
        char key = ' ';
        for(Map.Entry<Character, List<BoardPosition>> player : board.entrySet()){
            if(isPlayerAtPos(pos, player.getKey())){
                key = player.getKey();
                break;
            }
        }
        return key;
    }
    @Override
    public boolean isPlayerAtPos(BoardPosition pos, char player){
        return board.get(player).contains(pos);
    }
}
