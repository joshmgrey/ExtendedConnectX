package cpsc2150.extendedConnectX.models;

public abstract class AbsGameBoard implements IGameBoard{
    /**
     * This method overrides the default implementation of {@code toString}
     * to provide a string representation of the object
     * @return a string representation of the board
     * @pre
     *          NONE
     * @post
     *          toString = board.toString(), where board is an instance of StringBuilder
     */
    @Override
    public String toString() {
        StringBuilder board = new StringBuilder("|");
        BoardPosition pos;
        for(int i = 0; i < getNumColumns(); ++i){
            board.append(String.format("%2d", i)).append('|');
        }
        board.append('\n');
        for(int i = getNumRows() - 1; i >= 0; i--){
            board.append('|');
            for(int j = 0; j < getNumColumns(); j++){
                pos = new BoardPosition(i, j);
                board.append(String.format("%-2c", whatsAtPos(pos))).append('|');
            }
            board.append('\n');
        }
        return board.toString();
    }
}
