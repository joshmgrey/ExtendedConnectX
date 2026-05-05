package cpsc2150.extendedConnectX.models;

/**
 * Board is abstractly a 2 dimensional grid of character.
 * Indexing starts at zero
 * This board is bounded by MAX_ROWS_AND_COLUMNS AND MIN_ROWS_AND_COLUMNS
 * @author Josh Grey
 * @version 1.0
 * Initialization ensures:
 *      GameBoard contains only space characters and
 *      is getNumRows x getNumColumns OR
 *      GameBoardMem is an empty map of each player's token and a list of the
 * Constraints: MIN_ROWS_AND_COLUMNS <= getNumRows <= MAX_ROWS_AND_COLUMNS
 *              MIN_ROWS_AND_COLUMNS <= getNumColumns <= MAX_ROWS_AND COLUMNS
 */
public interface IGameBoard {
    int MAX_ROWS_AND_COLUMNS = 100;
    int MIN_ROWS_AND_COLUMNS = 3;
    int MAX_NUM_TO_WIN = 25;
    int MIN_NUM_TO_WIN = 3;
    int MIN_PLAYERS = 2;
    int MAX_PLAYERS = 10;
    /**
     * This method returns the number of rows in the {@code GameBoard}
     * @return Number of rows in the {@code GameBoard}
     * @pre
     *          NONE
     * @post
     *          getNumRows = rows AND self = #self
     */
    int getNumRows();

    /**
     * This method returns the number of columns in the {@code GameBoard}
     * @return Number of columns in the {@code GameBoard}
     * @pre
     *          NONE
     * @post
     *          getNumColumns = columns AND self = #self
     */
    int getNumColumns();

    /**
     * This method return the number of tokens in a row needed to win
     * @return number of tokens in a row needed to win
     * @pre
     *      NONE
     * @post
     *      getNumToWin = numToWin AND self = #self
     */
    int getNumToWin();
    /**
     * This method checks whether the column {@code c} can accept another token
     * @param c column
     * @return True if {@code c} can accept another token, false otherwise
     * @pre
     *          0 <= c < getNumColumns
     * @post
     *          checkIfFree iff board[i][c] = ' ', where i is lowest
     *          row that has a blank space on the floor
     */
    default boolean checkIfFree(int c){
        for(int i = 0; i < getNumRows(); ++i){
            BoardPosition pos = new BoardPosition(i, c);
            if(whatsAtPos(pos) == ' '){
                return true;
            }
        }
        return false;
    }
    /**
     * This method places a token in the lowest possible row on the
     * @param p game token
     * @param c column selected
     * @pre
     *      0 <= c < getNumColumns AND checkIfFree AND
     *      [p is a valid game token]
     * @post
     *      board[i][c] = p, where i is the lowest possible row that is free
     */
    void placeToken(char p, int c);

    /**
     * This method checks whether the last token placed won the game
     * @param c column
     * @return true if token in column c causes a win and
     * @pre
     *      placeToken AND 0 <= c < getNumColumns
     * @post
     *      checkForWin iff (checkHorizWin OR checkDiagWin OR checkVertiWin)
     *
     */
    default boolean checkForWin(int c){
        BoardPosition pos = null;
        int r = 0;
        if(!checkIfFree(c)){
            r = getNumRows() - 1;
        }
        else {
            for (int i = 0; i < getNumRows() - 1; ++i) {
                pos = new BoardPosition(i + 1, c);
                if(whatsAtPos(pos) == ' '){
                    r = i;
                    break;
                }
            }
        }
        pos = new BoardPosition(r, c);
        if(checkHorizWin(pos, whatsAtPos(pos))){
            return true;
        } else if(checkVertWin(pos, whatsAtPos(pos))){
            return true;
        } else if(checkDiagWin(pos, whatsAtPos(pos))){
            return true;
        }
        return false;
    }
    /**
     * This method checks whether the last token placed resulted in a tie.
     * @return True if all columns !checkIfFree and false otherwise
     * @pre
     *          !checkForWin
     * @post
     *          checkTie iff (!checkIfFree for each {@code column})
     */
    default boolean checkTie(){
        for(int i = 0; i < getNumColumns(); ++i){
            if(checkIfFree(i)){
                return false;
            }
        }
        return true;
    }
    /**
     * @param pos position on board
     * @param p game token
     * @return True if previous token played resulted in a win
     *         horizontally and false otherwise
     * @pre
     *          [p is valid game token] AND 0 <= pos.getColumn < getNumColumns
     *          AND 0 <= pos.getRow < getNumRows
     * @post
     *          checkHorizWin iff isPlayerAtPosition for token p
     *          getNumToWin in a row horizontally
     */
    default boolean checkHorizWin(BoardPosition pos, char p){
        int count = 1;
        BoardPosition pos2;
        for(int i = pos.getColumn(); i < getNumColumns(); ++i){
            pos2 = new BoardPosition(pos.getRow(), i);
            if(!pos2.equals(pos)) {
                if(isPlayerAtPos(pos2, p)) {
                    count += 1;
                    if(count == getNumToWin()){
                        return true;
                    }
                } else {
                    break;
                }
            }
        }

        for(int i = pos.getColumn(); i >= 0; --i){
            pos2 = new BoardPosition(pos.getRow(), i);
            if(!pos2.equals(pos)){
                if(isPlayerAtPos(pos2, p)){
                    count += 1;
                    if(count == getNumToWin()){
                        return true;
                    }
                }
                else{
                    break;
                }
            }
        }
        return false;
    }
    /**
     *
     * @param pos position
     * @param p player token
     * @return True if token played resulted in a win vertically and false otherwise
     * @pre
     *          [p is valid game token] AND 0 <= pos.getColumn < getNumColumns AND
     *          0 <= pos.getRow < getNumRows
     * @post
     *          checkVertWin iff isPlayerAtPosition for token p
     *          getNumToWin in a row vertically
     */
    default boolean checkVertWin(BoardPosition pos, char p){
        int count = 1;
        BoardPosition pos2;
        for(int i = pos.getRow(); i < getNumRows(); ++i) {
            pos2 = new BoardPosition(i, pos.getColumn());
            if (!pos2.equals(pos)) {
                if (isPlayerAtPos(pos2, p)) {
                    count += 1;
                    if(count == getNumToWin()){
                        return true;
                    }
                } else {
                    break;
                }
            }
        }
        for(int i = pos.getRow(); i >= 0; --i){
            pos2 = new BoardPosition(i, pos.getColumn());
            if(!pos2.equals(pos)) {
                if (isPlayerAtPos(pos2, p)) {
                    count += 1;
                    if(count == getNumToWin()){
                        return true;
                    }
                } else {
                    break;
                }
            }
        }
        return false;
    }
    /**
     *
     * @param pos position on board of the previously placed token
     * @param p character of the previously placed token
     * @return true if there is a win diagonally and false otherwise
     * @pre
     *          0 <= pos.getRow < getNumRows AND 0 <= pos.getColumn < getNumColumns
     *          AND [p is valid token]
     * @post
     *          checkDiagWin iff isPlayerAtPos getNumToWin in a row diagonally
     */
    default boolean checkDiagWin(BoardPosition pos, char p){
        int count = 1;
        BoardPosition pos2;
        for(int i = pos.getRow(), j = pos.getColumn(); i < getNumRows() && j <getNumColumns(); i++, j++){
            pos2 = new BoardPosition(i, j);
            if(!pos2.equals(pos)){
                if(isPlayerAtPos(pos2, p)){
                    count += 1;
                    if(count == getNumToWin()){
                        return true;
                    }
                }
                else{
                    break;
                }
            }
        }
        for(int i = pos.getRow(), j = pos.getColumn(); i >= 0 && j >= 0; --i, --j){
            pos2 = new BoardPosition(i, j);
            if(!pos2.equals(pos)){
                if(isPlayerAtPos(pos2, p)){
                    count += 1;
                    if(count == getNumToWin()){
                        return true;
                    }
                }
                else {
                    break;
                }
            }
        }
        count = 1;
        for(int i = pos.getRow(), j = pos.getColumn(); i < getNumRows() && j >= 0; i++, j--){
            pos2 = new BoardPosition(i, j);
            if(!pos2.equals(pos)){
                if(isPlayerAtPos(pos2, p)){
                    count += 1;
                    if(count == getNumToWin()){
                        return true;
                    }
                }
                else{
                    break;
                }
            }

        }
        for(int i = pos.getRow(), j = pos.getColumn(); i >= 0 && j < getNumColumns(); i--, j++){
            pos2 = new BoardPosition(i,j);
            if(!pos2.equals(pos)){
                if(isPlayerAtPos(pos2, p)){
                    count += 1;
                    if(count == getNumToWin()){
                        return true;
                    }
                }
                else {
                    break;
                }
            }
        }
        return false;
    }
    /**
     * This method returns the character of what is at this specific position
     * on the board
     * @param pos position
     * @return character on board, ' ' if blank
     * @pre
     *          0 <= pos.getRow < getNumRows AND
     *          0 <= pos.getColumn < getNumColumns
     * @post
     *          whatsAtPos = [character in pos of self]
     */
    char whatsAtPos(BoardPosition pos);
    /**
     *
     * @param pos position
     * @param player current player
     * @return true if player token is at the position and false otherwise
     * @pre
     *          [player is valid] AND 0 <= pos.getColumn < getNumColumns
     *          AND 0 <= pos.getRow < getNumRows
     * @post
     *          isPlayerAtPos iff whatsAtPos(pos) = player
     */
    default boolean isPlayerAtPos(BoardPosition pos, char player){
        return whatsAtPos(pos) == player;
    }
}