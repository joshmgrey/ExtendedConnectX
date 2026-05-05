package cpsc2150.extendedConnectX.models;

/**
 * <p>The class shows a specific position on the board from the GameBoard
 * class</p>
 * <p>This class is used to check several method within the GameBoard class,
 * such as check if a player has a won diagonally, horizontally, or vertically.
 * It can also check what character is at a specific position on the board. It
 * can even check to see if the player of certain game token played at a specific
 * position on the board.</p>
 * @author Josh Grey
 * @version 1.0
 * @invariant column {@code >=} 0 AND column {@code <} 7 AND row {@code >=} 0
 *            AND row {@code <} 9
 */
public class BoardPosition {
    private int row;
    private int column;

    /**
     * This constructor initializes the row {@code r} and column {@code c}
     * at a specific position on the game board
     * @param r row
     * @param c column
     * @pre
     *          r {@code >=} 0 AND r {@code <} 9 AND c {@code >=} 0
     *          AND c {@code <} 7
     * @post
     *          column = c AND row = r AND numToWin = n AND
     */
    public BoardPosition(int r, int c){
        row = r;
        column = c;
    }

    /**
     * This method returns the column
     * @return the column
     * @pre
     *          NONE
     * @post
     *          getColumn = column AND column = #column
     */
    public int getColumn(){
        return column;
    }

    /**
     * This method returns the row
     * @return the row
     * @pre
     *          NONE
     * @post
     *          getRow = row AND row = #row
     */
    public int getRow(){
        return row;
    }

    /**
     * This method overrides the default implementation of {@code equals}
     * to compare two positions on the board.
     * @param obj position on board being compared
     * @return True if {@code this.row} = {@code pos.row} AND
     *         {@code this.column} = {@code pos.column} and false otherwise
     * @pre
     *          this.getClass() = obj.getClass() AND obj != null
     * @post
     *          BoardPosition pos = (BoardPosition) obj AND {@code equals} iff
     *          ((this.row = pos.row) AND (this.column = pos.column))
     */
    @Override
    public boolean equals(Object obj) {
        BoardPosition position = (BoardPosition) obj;
        return position.column == column && position.row == row;
    }

    /**
     * This method overrides the default implementation of {@code toString}
     * to provide a string representation of the object
     * @return a string representation of the position on the board
     * @pre
     *          NONE
     * @post
     *          toString = "[row],[column]" AND row = #row AND column = #column
     */
    @Override
    public String toString(){
        return row + "," + column;
    }
}
