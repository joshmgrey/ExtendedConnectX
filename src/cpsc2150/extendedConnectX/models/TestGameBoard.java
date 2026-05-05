package cpsc2150.extendedConnectX.models;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;
public class TestGameBoard {
    private IGameBoard IGameFactory(int r, int c, int n){
        return new GameBoard(r, c, n);
    }
    private String expectedString(char[][] gameBoardExpected){
        StringBuilder boardExpected = new StringBuilder("|");
        for(int i = 0; i < gameBoardExpected[0].length; ++i){
            boardExpected.append(String.format("%2d", i)).append('|');
        }
        boardExpected.append('\n');
        for(int i = gameBoardExpected.length - 1; i >= 0; --i){
            boardExpected.append('|');
            for(int j = 0; j < gameBoardExpected[i].length; ++j){
                boardExpected.append(String.format("%-2c", gameBoardExpected[i][j])).append('|');
            }
            boardExpected.append('\n');
        }
        return boardExpected.toString();
    }
    @Test
    public void testConstructor_5_rows_5_columns_4_number_to_win(){
        int r = 5;
        int c = 5;
        int n = 4;
        char[][] boardExpected = new char[r][c];
        for(int i = 0; i < r; ++i){
            Arrays.fill(boardExpected[i], ' ');
        }
        IGameBoard gb = IGameFactory(r,c,n);
        String expected = expectedString(boardExpected);
        assertEquals(expected, gb.toString());
        assertEquals(r, gb.getNumRows());
        assertEquals(c, gb.getNumColumns());
        assertEquals(n, gb.getNumToWin());
    }
    @Test
    public void testConstructor_6_rows_6_columns_5_number_to_win(){
        int r = 6;
        int c = 6;
        int n = 5;
        char[][] boardExpected = new char[r][c];
        for(int i = 0; i < r; ++i){
            Arrays.fill(boardExpected[i], ' ');
        }
        IGameBoard gb = IGameFactory(r,c,n);
        String expected = expectedString(boardExpected);
        assertEquals(expected, gb.toString());
        assertEquals(r, gb.getNumRows());
        assertEquals(c, gb.getNumColumns());
        assertEquals(n, gb.getNumToWin());
    }
    @Test
    public void testConstructor_9_rows_7_columns_5_number_to_win(){
        int r = 9;
        int c = 7;
        int n = 6;
        char[][] gameBoardExpected = new char[r][c];
        for(int i = 0; i < r; ++i){
            Arrays.fill(gameBoardExpected[i], ' ');
        }
        IGameBoard gb = IGameFactory(r,c,n);
        String expected = expectedString(gameBoardExpected);
        assertEquals(expected,gb.toString());
        assertEquals(r, gb.getNumRows());
        assertEquals(c, gb.getNumColumns());
        assertEquals(n, gb.getNumToWin());
    }
    @Test
    public void testCheckIfFree_col_1_yes(){
        char[][] boardExpected = new char[4][4];
        for(int i = 0; i < boardExpected.length; ++i){
            Arrays.fill(boardExpected[i], ' ');
        }
        IGameBoard gb = IGameFactory(4,4,3);
        gb.placeToken('X', 0);
        boardExpected[0][0] = 'X';
        gb.placeToken('O', 1);
        boardExpected[0][1] = 'O';
        gb.placeToken('X',2);
        boardExpected[0][2] = 'X';
        gb.placeToken('O', 3);
        boardExpected[0][3] = 'O';
        gb.placeToken('X', 1);
        boardExpected[1][1] = 'X';
        gb.placeToken('O', 0);
        boardExpected[1][0] = 'O';
        String expected = expectedString(boardExpected);
        assertTrue(gb.checkIfFree(1));
        assertEquals(expected, gb.toString());
    }
    @Test
    public void testCheckIfFree_col_0_no(){
        char[][] boardExpected = new char[4][4];
        for(int i = 0; i < boardExpected.length; ++i){
            Arrays.fill(boardExpected[i], ' ');
        }
        IGameBoard gb = IGameFactory(4,4,3);
        gb.placeToken('X', 0);
        boardExpected[0][0] = 'X';
        gb.placeToken('O', 0);
        boardExpected[1][0] = 'O';
        gb.placeToken('X',0);
        boardExpected[2][0] = 'X';
        gb.placeToken('O', 0);
        boardExpected[3][0] = 'O';
        assertFalse(gb.checkIfFree(0));
        String expected = expectedString(boardExpected);
        assertEquals(expected,gb.toString());
    }
    @Test
    public void testCheckIfFree_col_2_yes_for_the_win(){
        char[][] boardExpected = new char[4][4];
        for(int i = 0; i < boardExpected.length; ++i){
            Arrays.fill(boardExpected[i], ' ');
        }
        IGameBoard gb = IGameFactory(4,4,3);
        gb.placeToken('X', 0);
        boardExpected[0][0] = 'X';
        gb.placeToken('O',1);
        boardExpected[0][1] = 'O';
        gb.placeToken('X',2);
        boardExpected[0][2] = 'X';
        gb.placeToken('O', 3);
        boardExpected[0][3] = 'O';
        gb.placeToken('X', 0);
        boardExpected[1][0] = 'X';
        gb.placeToken('O', 0);
        boardExpected[2][0] = 'O';
        gb.placeToken('X', 1);
        boardExpected[1][1] = 'X';
        gb.placeToken('O', 2);
        boardExpected[1][2] = 'O';
        gb.placeToken('X', 1);
        boardExpected[2][1] = 'X';
        gb.placeToken('O', 1);
        boardExpected[3][1] = 'O';
        assertTrue(gb.checkIfFree(2));
        String expected = expectedString(boardExpected);
        assertEquals(expected,gb.toString());
    }
    @Test
    public void testCheckHorizWin_win_last_marker_right(){
        char[][] boardExpected = new char[4][4];
        for(int i = 0; i < boardExpected.length; ++i){
            Arrays.fill(boardExpected[i], ' ');
        }
        IGameBoard gb = IGameFactory(4,4,3);
        gb.placeToken('X', 0);
        boardExpected[0][0] = 'X';
        gb.placeToken('O', 1);
        boardExpected[0][1] = 'O';
        gb.placeToken('X', 2);
        boardExpected[0][2] = 'X';
        gb.placeToken('O', 3);
        boardExpected[0][3] = 'O';
        gb.placeToken('X', 0);
        boardExpected[1][0] = 'X';
        gb.placeToken('O',0);
        boardExpected[2][0] = 'O';
        gb.placeToken('X',1);
        boardExpected[1][1] = 'X';
        gb.placeToken('O',1);
        boardExpected[2][1] = 'O';
        gb.placeToken('X',2);
        boardExpected[1][2] = 'X';
        String expected = expectedString(boardExpected);
        assertEquals(expected,gb.toString());
        BoardPosition pos = new BoardPosition(1,2);
        assertTrue(gb.checkHorizWin(pos, 'X'));
    }
    @Test
    public void testCheckForHorizWin_win_last_marker_middle(){
        char[][] boardExpected = new char[4][4];
        for(int i = 0; i < boardExpected.length; ++i){
            Arrays.fill(boardExpected[i], ' ');
        }
        IGameBoard gb = IGameFactory(4,4,3);
        gb.placeToken('X', 3);
        boardExpected[0][3] = 'X';
        gb.placeToken('O', 1);
        boardExpected[0][1] = 'O';
        gb.placeToken('X',3);
        boardExpected[1][3] = 'X';
        gb.placeToken('O', 3);
        boardExpected[2][3] = 'O';
        gb.placeToken('X', 1);
        boardExpected[1][1] = 'X';
        gb.placeToken('O', 2);
        boardExpected[0][2] = 'O';
        gb.placeToken('X', 2);
        boardExpected[1][2] = 'X';
        BoardPosition pos = new BoardPosition(1,2);
        String expected = expectedString(boardExpected);
        assertEquals(expected,gb.toString());
        assertTrue(gb.checkHorizWin(pos, 'X'));
    }
    @Test
    public void testCheckForHorizWin_win_last_marker_left(){
        char[][] boardExpected = new char[4][4];
        for(int i = 0; i < boardExpected.length; ++i){
            Arrays.fill(boardExpected[i], ' ');
        }
        IGameBoard gb = IGameFactory(4,4,3);
        gb.placeToken('X', 3);
        boardExpected[0][3] = 'X';
        gb.placeToken('O', 2);
        boardExpected[0][2] = 'O';
        gb.placeToken('X', 3);
        boardExpected[1][3] = 'X';
        gb.placeToken('O', 3);
        boardExpected[2][3] = 'O';
        gb.placeToken('X',2);
        boardExpected[1][2] = 'X';
        gb.placeToken('O', 1);
        boardExpected[0][1] = 'O';
        gb.placeToken('X' , 1);
        boardExpected[1][1] = 'X';
        BoardPosition pos = new BoardPosition(1, 1);
        String expected = expectedString(boardExpected);
        assertEquals(expected, gb.toString());
        assertTrue(gb.checkHorizWin(pos, 'X'));
    }
    @Test
    public void testCheckForHorizWin_no_win(){
        char[][] boardExpected = new char[4][4];
        for(int i = 0; i < boardExpected.length; ++i){
            Arrays.fill(boardExpected[i], ' ');
        }
        IGameBoard gb = IGameFactory(4,4,3);
        gb.placeToken('X', 0);
        boardExpected[0][0] = 'X';
        gb.placeToken('O', 1);
        boardExpected[0][1] = 'O';
        gb.placeToken('X', 2);
        boardExpected[0][2] = 'X';
        gb.placeToken('O', 3);
        boardExpected[0][3] = 'O';
        gb.placeToken('X', 0);
        boardExpected[1][0] = 'X';
        gb.placeToken('O', 0);
        boardExpected[2][0] = 'O';
        gb.placeToken('X', 1);
        boardExpected[1][1] = 'X';
        gb.placeToken('O', 2);
        boardExpected[1][2] = 'O';
        gb.placeToken('X',3);
        boardExpected[1][3] = 'X';
        gb.placeToken('O',1);
        boardExpected[2][1] = 'O';
        gb.placeToken('X', 2);
        boardExpected[2][2] = 'X';
        BoardPosition pos = new BoardPosition(2,2);
        String expected = expectedString(boardExpected);
        assertEquals(expected,gb.toString());
        assertFalse(gb.checkHorizWin(pos, 'X'));
    }
    @Test
    public void testCheckForVertWin_win_all_markers_col_0(){
        char[][] boardExpected = new char[4][4];
        for(int i = 0; i < boardExpected.length; ++i){
            Arrays.fill(boardExpected[i], ' ');
        }
        IGameBoard gb = IGameFactory(4,4,3);
        gb.placeToken('X', 0);
        boardExpected[0][0] = 'X';
        gb.placeToken('O',1);
        boardExpected[0][1] = 'O';
        gb.placeToken('X', 0);
        boardExpected[1][0] = 'X';
        gb.placeToken('O', 1);
        boardExpected[1][1] = 'O';
        gb.placeToken('X', 0);
        boardExpected[2][0] = 'X';
        BoardPosition pos = new BoardPosition(2,0);
        String expected = expectedString(boardExpected);
        assertEquals(expected, gb.toString());
        assertTrue(gb.checkVertWin(pos, 'X'));
    }
    @Test
    public void testCheckVertWin_win_all_markers_col_1(){
        char[][] boardExpected = new char[4][4];
        for(int i = 0; i < boardExpected.length; ++i){
            Arrays.fill(boardExpected[i], ' ');
        }
        IGameBoard gb = IGameFactory(4,4,3);
        gb.placeToken('X', 1);
        boardExpected[0][1] = 'X';
        gb.placeToken('O',2);
        boardExpected[0][2] = 'O';
        gb.placeToken('X', 1);
        boardExpected[1][1] = 'X';
        gb.placeToken('O', 2);
        boardExpected[1][2] = 'O';
        gb.placeToken('X', 1);
        boardExpected[2][1] = 'X';
        BoardPosition pos = new BoardPosition(2,1);
        String expected = expectedString(boardExpected);
        assertEquals(expected,gb.toString());
        assertTrue(gb.checkVertWin(pos, 'X'));
    }
    @Test
    public void testCheckVertWin_win_all_all_markers_col_2(){
        char[][] boardExpected = new char[4][4];
        for(int i = 0; i < boardExpected.length; ++i){
            Arrays.fill(boardExpected[i], ' ');
        }
        IGameBoard gb = IGameFactory(4,4,3);
        gb.placeToken('X', 2);
        boardExpected[0][2] = 'X';
        gb.placeToken('O', 1);
        boardExpected[0][1] = 'O';
        gb.placeToken('X', 2);
        boardExpected[1][2] = 'X';
        gb.placeToken('O', 1);
        boardExpected[1][1] = 'O';
        gb.placeToken('X', 2);
        boardExpected[2][2] = 'X';
        BoardPosition pos = new BoardPosition(2, 2);
        String expected = expectedString(boardExpected);
        assertEquals(expected,gb.toString());
        assertTrue(gb.checkVertWin(pos, 'X'));
    }
    @Test
    public void testCheckVertWin_no_win(){
        char[][] boardExpected = new char[4][4];
        for(int i = 0; i < boardExpected.length; ++i){
            Arrays.fill(boardExpected[i], ' ');
        }
        IGameBoard gb = IGameFactory(4,4,3);
        gb.placeToken('X', 3);
        boardExpected[0][3] = 'X';
        gb.placeToken('O', 2);
        boardExpected[0][2] = 'O';
        gb.placeToken('X', 3);
        boardExpected[1][3] = 'X';
        gb.placeToken('O', 3);
        boardExpected[2][3] = 'O';
        gb.placeToken('X', 2);
        boardExpected[1][2] ='X';
        gb.placeToken('O',1);
        boardExpected[0][1] = 'O';
        gb.placeToken('X',1);
        boardExpected[1][1] = 'X';
        BoardPosition pos = new BoardPosition(1, 1);
        String expected = expectedString(boardExpected);
        assertEquals(expected,gb.toString());
        assertFalse(gb.checkVertWin(pos, 'X'));
    }
    @Test
    public void testCheckDiagWin_win_right_left_diagonal(){
        char[][] boardExpected = new char[4][4];
        for(int i = 0; i < boardExpected.length; ++i){
            Arrays.fill(boardExpected[i], ' ');
        }
        IGameBoard gb = IGameFactory(4,4,3);
        gb.placeToken('X', 0);
        boardExpected[0][0] = 'X';
        gb.placeToken('O', 1);
        boardExpected[0][1] = 'O';
        gb.placeToken('X', 1);
        boardExpected[1][1] = 'X';
        gb.placeToken('O', 2);
        boardExpected[0][2] = 'O';
        gb.placeToken('X', 3);
        boardExpected[0][3] = 'X';
        gb.placeToken('O',2);
        boardExpected[1][2] = 'O';
        gb.placeToken('X', 2);
        boardExpected[2][2] = 'X';
        BoardPosition pos = new BoardPosition(2,2);
        String expected = expectedString(boardExpected);
        assertEquals(gb.toString(), expected);
        assertTrue(gb.checkDiagWin(pos, 'X'));
    }
    @Test
    public void testCheckDiagWin_win_middle_left_diagonal(){
        char[][] boardExpected = new char[4][4];
        for(int i = 0; i < boardExpected.length; ++i){
            Arrays.fill(boardExpected[i], ' ');
        }
        IGameBoard gb = IGameFactory(4,4,3);
        gb.placeToken('X', 0);
        boardExpected[0][0] = 'X';
        gb.placeToken('O', 2);
        boardExpected[0][2] = 'O';
        gb.placeToken('X',3);
        boardExpected[0][3] = 'X';
        gb.placeToken('O', 2);
        boardExpected[1][2] = 'O';
        gb.placeToken('X', 2);
        boardExpected[2][2] = 'X';
        gb.placeToken('O',1);
        boardExpected[0][1] = 'O';
        gb.placeToken('X',1);
        boardExpected[1][1] = 'X';
        BoardPosition pos = new BoardPosition(1,1);
        String expected = expectedString(boardExpected);
        assertEquals(expected,gb.toString());
        assertTrue(gb.checkDiagWin(pos, 'X'));
    }
    @Test
    public void testCheckDiagWin_win_left_left_diagonal(){
        char[][] boardExpected = new char[4][4];
        for(int i = 0; i < boardExpected.length; ++i){
            Arrays.fill(boardExpected[i], ' ');
        }
        IGameBoard gb = IGameFactory(4,4,3);
        gb.placeToken('X', 2);
        boardExpected[0][2] = 'X';
        gb.placeToken('O', 2);
        boardExpected[1][2] = 'O';
        gb.placeToken('X', 2);
        boardExpected[2][2] = 'X';
        gb.placeToken('O', 2);
        boardExpected[3][2] = 'O';
        gb.placeToken('X', 3);
        boardExpected[0][3] = 'X';
        gb.placeToken('O', 1);
        boardExpected[0][1] = 'O';
        gb.placeToken('X',1);
        boardExpected[1][1] = 'X';
        gb.placeToken('O', 3);
        boardExpected[1][3] = 'O';
        gb.placeToken('X',0);
        boardExpected[0][0] = 'X';
        BoardPosition pos = new BoardPosition(0, 0);
        String expected = expectedString(boardExpected);
        assertEquals(expected,gb.toString());
        assertTrue(gb.checkDiagWin(pos, 'X'));
    }
    @Test
    public void testCheckDiagWin_win_left_right_diagonal(){
        char[][] boardExpected = new char[4][4];
        for(int i = 0; i < boardExpected.length; ++i){
            Arrays.fill(boardExpected[i], ' ');
        }
        IGameBoard gb = IGameFactory(4,4,3);
        gb.placeToken('X', 3);
        boardExpected[0][3] = 'X';
        gb.placeToken('O', 2);
        boardExpected[0][2] = 'O';
        gb.placeToken('X',2);
        boardExpected[1][2] = 'X';
        gb.placeToken('O', 1);
        boardExpected[0][1] = 'O';
        gb.placeToken('X',0);
        boardExpected[0][0] = 'X';
        gb.placeToken('O', 1);
        boardExpected[1][1] = 'O';
        gb.placeToken('X', 1);
        boardExpected[2][1] = 'X';
        BoardPosition pos = new BoardPosition(2, 1);
        String expected = expectedString(boardExpected);
        assertEquals(expected,gb.toString());
        assertTrue(gb.checkDiagWin(pos, 'X'));
    }
    @Test
    public void testCheckDiagWin_win_middle_right_diagonal(){
        char[][] boardExpected = new char[4][4];
        for(int i = 0; i < boardExpected.length; ++i){
            Arrays.fill(boardExpected[i], ' ');
        }
        IGameBoard gb = IGameFactory(4,4,3);
        gb.placeToken('X', 0);
        boardExpected[0][0] = 'X';
        gb.placeToken('O', 1);
        boardExpected[0][1] = 'O';
        gb.placeToken('X', 0);
        boardExpected[1][0] = 'X';
        gb.placeToken('O', 1);
        boardExpected[1][1] = 'O';
        gb.placeToken('X', 1);
        boardExpected[2][1] = 'X';
        gb.placeToken('O', 0);
        boardExpected[2][0] = 'O';
        gb.placeToken('X', 3);
        boardExpected[0][3] = 'X';
        gb.placeToken('O', 2);
        boardExpected[0][2] = 'O';
        BoardPosition pos = new BoardPosition(0,2);
        String expected = expectedString(boardExpected);
        assertEquals(expected,gb.toString());
        assertTrue(gb.checkDiagWin(pos, 'O'));
    }
    @Test
    public void testCheckDiagWin_win_right_right_diagonal(){
        char[][] boardExpected = new char[4][4];
        for(int i = 0; i < boardExpected.length; ++i){
            Arrays.fill(boardExpected[i], ' ');
        }
        IGameBoard gb = IGameFactory(4,4,3);
        gb.placeToken('X', 1);
        boardExpected[0][1] = 'X';
        gb.placeToken('O', 1);
        boardExpected[1][1] = 'O';
        gb.placeToken('X', 1);
        boardExpected[2][1] = 'X';
        gb.placeToken('O', 2);
        boardExpected[0][2] = 'O';
        gb.placeToken('X', 2);
        boardExpected[1][2] = 'X';
        gb.placeToken('O', 0);
        boardExpected[0][0] = 'O';
        gb.placeToken('X', 3);
        boardExpected[0][3] = 'X';
        String expected = expectedString(boardExpected);
        BoardPosition pos = new BoardPosition(0,3);
        assertEquals(expected,gb.toString());
        assertTrue(gb.checkDiagWin(pos, 'X'));
    }
    @Test
    public void testCheckDiagWin_no_win(){
        char[][] boardExpected = new char[4][4];
        for(int i = 0; i < boardExpected.length; ++i){
            Arrays.fill(boardExpected[i], ' ');
        }
        IGameBoard gb = IGameFactory(4,4,3);
        gb.placeToken('X',0);
        boardExpected[0][0] = 'X';
        gb.placeToken('O', 1);
        boardExpected[0][1] = 'O';
        gb.placeToken('X', 1);
        boardExpected[1][1] = 'X';
        gb.placeToken('O',2);
        boardExpected[0][2] = 'O';
        gb.placeToken('X',2);
        boardExpected[1][2] = 'X';
        gb.placeToken('O',2);
        boardExpected[2][2] = 'O';
        gb.placeToken('X',3);
        boardExpected[0][3] = 'X';
        gb.placeToken('O',1);
        boardExpected[2][1] = 'O';
        gb.placeToken('X',3);
        boardExpected[1][3] = 'X';
        String expected = expectedString(boardExpected);
        BoardPosition pos = new BoardPosition(1,3);
        assertEquals(expected,gb.toString());
        assertFalse(gb.checkDiagWin(pos, 'X'));
    }
    @Test
    public void testCheckTie_yes(){
        char[][] boardExpected = new char[4][4];
        for(int i = 0; i < boardExpected.length; ++i){
            Arrays.fill(boardExpected[i], ' ');
        }
        IGameBoard gb = IGameFactory(4,4,3);
        gb.placeToken('X',0);
        boardExpected[0][0] = 'X';
        gb.placeToken('O',0);
        boardExpected[1][0] = 'O';
        gb.placeToken('X', 0);
        boardExpected[2][0] = 'X';
        gb.placeToken('O', 0);
        boardExpected[3][0] = 'O';
        gb.placeToken('X', 1);
        boardExpected[0][1] = 'X';
        gb.placeToken('O', 1);
        boardExpected[1][1] = 'O';
        gb.placeToken('X',1);
        boardExpected[2][1] = 'X';
        gb.placeToken('O',2);
        boardExpected[0][2] = 'O';
        gb.placeToken('X', 2);
        boardExpected[1][2] = 'X';
        gb.placeToken('O',2);
        boardExpected[2][2] = 'O';
        gb.placeToken('X',1);
        boardExpected[3][1] = 'X';
        gb.placeToken('O',2);
        boardExpected[3][2] = 'O';
        gb.placeToken('X',3);
        boardExpected[0][3] = 'X';
        gb.placeToken('O', 3);
        boardExpected[1][3] = 'O';
        gb.placeToken('X', 3);
        boardExpected[2][3] = 'X';
        gb.placeToken('O',3);
        boardExpected[3][3] = 'O';
        String expected = expectedString(boardExpected);
        assertEquals(expected,gb.toString());
        assertTrue(gb.checkTie());
    }
    @Test
    public void testCheckTie_no_only_1_col_filled(){
        char[][] boardExpected = new char[4][4];
        for(int i = 0; i < boardExpected.length; ++i){
            Arrays.fill(boardExpected[i], ' ');
        }
        IGameBoard gb = IGameFactory(4,4,3);
        gb.placeToken('X',0);
        boardExpected[0][0] = 'X';
        gb.placeToken('O', 0);
        boardExpected[1][0] = 'O';
        gb.placeToken('X',0);
        boardExpected[2][0] = 'X';
        gb.placeToken('O',0);
        boardExpected[3][0] = 'O';
        String expected = expectedString(boardExpected);
        assertEquals(expected,gb.toString());
        assertFalse(gb.checkTie());
    }
    @Test
    public void testCheckTie_no_only_2_col_filled(){
        char[][] boardExpected = new char[4][4];
        for(int i = 0; i < boardExpected.length; ++i){
            Arrays.fill(boardExpected[i], ' ');
        }
        IGameBoard gb = IGameFactory(4,4,3);
        gb.placeToken('X',0);
        boardExpected[0][0] = 'X';
        gb.placeToken('O', 0);
        boardExpected[1][0] = 'O';
        gb.placeToken('X',0);
        boardExpected[2][0] = 'X';
        gb.placeToken('O', 0);
        boardExpected[3][0] = 'O';
        gb.placeToken('X', 1);
        boardExpected[0][1] = 'X';
        gb.placeToken('O',1);
        boardExpected[1][1] = 'O';
        gb.placeToken('X',1);
        boardExpected[2][1] = 'X';
        gb.placeToken('O',1);
        boardExpected[3][1] = 'O';
        String expected = expectedString(boardExpected);
        assertEquals(expected,gb.toString());
        assertFalse(gb.checkTie());
    }
    @Test
    public void testCheckTie_no_only_1_col_free(){
        char[][] boardExpected = new char[4][4];
        for(int i = 0; i < boardExpected.length; ++i){
            Arrays.fill(boardExpected[i], ' ');
        }
        IGameBoard gb = IGameFactory(4,4,3);
        gb.placeToken('X',0);
        boardExpected[0][0] = 'X';
        gb.placeToken('O', 0);
        boardExpected[1][0] = 'O';
        gb.placeToken('X',0);
        boardExpected[2][0] = 'X';
        gb.placeToken('O',0);
        boardExpected[3][0] = 'O';
        gb.placeToken('X',1);
        boardExpected[0][1] = 'X';
        gb.placeToken('O',1);
        boardExpected[1][1] = 'O';
        gb.placeToken('X',1);
        boardExpected[2][1] = 'X';
        gb.placeToken('O',2);
        boardExpected[0][2] = 'O';
        gb.placeToken('X',2);
        boardExpected[1][2] = 'X';
        gb.placeToken('O',2);
        boardExpected[2][2] = 'O';
        gb.placeToken('X',1);
        boardExpected[3][1] = 'X';
        gb.placeToken('O',2);
        boardExpected[3][2] = 'O';
        String expected = expectedString(boardExpected);
        assertEquals(expected,gb.toString());
        assertFalse(gb.checkTie());
    }
    @Test
    public void testWhatsAtPos_row_0_col_0_not_space(){
        char[][] boardExpected = new char[4][4];
        for(int i = 0; i < boardExpected.length; ++i){
            Arrays.fill(boardExpected[i], ' ');
        }
        IGameBoard gb = IGameFactory(4,4,3);
        gb.placeToken('X',0);
        boardExpected[0][0] = 'X';
        gb.placeToken('O',1);
        boardExpected[0][1] = 'O';
        gb.placeToken('X',2);
        boardExpected[0][2] = 'X';
        gb.placeToken('O',3);
        boardExpected[0][3] = 'O';
        String expected = expectedString(boardExpected);
        assertEquals(gb.toString(), expected);
        BoardPosition pos = new BoardPosition(0,0);
        assertEquals('X',gb.whatsAtPos(pos));
    }
    @Test
    public void testWhatsAtPos_row_max_col_max(){
        char[][] boardExpected = new char[4][4];
        for(int i = 0; i < boardExpected.length; ++i){
            Arrays.fill(boardExpected[i], ' ');
        }
        IGameBoard gb = IGameFactory(4,4,3);
        gb.placeToken('X',3);
        boardExpected[0][3] = 'X';
        gb.placeToken('O',2);
        boardExpected[0][2] = 'O';
        gb.placeToken('X',1);
        boardExpected[0][1] = 'X';
        gb.placeToken('O', 0);
        boardExpected[0][0] = 'O';
        gb.placeToken('X',3);
        boardExpected[1][3] = 'X';
        gb.placeToken('O',3);
        boardExpected[2][3] = 'O';
        gb.placeToken('X',3);
        boardExpected[3][3] = 'X';
        String expected = expectedString(boardExpected);
        assertEquals(expected,gb.toString());
        BoardPosition pos = new BoardPosition(3,3);
        assertEquals('X', gb.whatsAtPos(pos));
    }
    @Test
    public void testWhatsAtPos_row_min_col_max(){
        char[][] boardExpected = new char[4][4];
        for(int i = 0; i < boardExpected.length; ++i){
            Arrays.fill(boardExpected[i], ' ');
        }
        IGameBoard gb = IGameFactory(4,4,3);
        gb.placeToken('X',3);
        boardExpected[0][3] = 'X';
        gb.placeToken('O',2);
        boardExpected[0][2] = 'O';
        gb.placeToken('X',1);
        boardExpected[0][1] = 'X';
        gb.placeToken('O',0);
        boardExpected[0][0] = 'O';
        String expected = expectedString(boardExpected);
        assertEquals(expected,gb.toString());
        BoardPosition pos = new BoardPosition(0,3);
        assertEquals('X',gb.whatsAtPos(pos));
    }
    @Test
    public void testWhatsAtPos_row_max_col_min(){
        char[][] boardExpected = new char[4][4];
        for(int i = 0; i < boardExpected.length; ++i){
            Arrays.fill(boardExpected[i], ' ');
        }
        IGameBoard gb = IGameFactory(4,4,3);
        gb.placeToken('X', 0);
        boardExpected[0][0] = 'X';
        gb.placeToken('O',1);
        boardExpected[0][1] = 'O';
        gb.placeToken('X',2);
        boardExpected[0][2] = 'X';
        gb.placeToken('O',3);
        boardExpected[0][3] = 'O';
        gb.placeToken('X',0);
        boardExpected[1][0] ='X';
        gb.placeToken('O',0);
        boardExpected[2][0] = 'O';
        gb.placeToken('X', 0);
        boardExpected[3][0] = 'X';
        String expected = expectedString(boardExpected);
        assertEquals(expected, gb.toString());
        BoardPosition pos = new BoardPosition(3,0);
        assertEquals('X', gb.whatsAtPos(pos));
    }
    @Test
    public void testWhatsAtPos_blank_space(){
        char[][] boardExpected = new char[4][4];
        for(int i = 0; i < boardExpected.length; ++i){
            Arrays.fill(boardExpected[i], ' ');
        }
        IGameBoard gb = IGameFactory(4,4,3);
        gb.placeToken('X',0);
        boardExpected[0][0] = 'X';
        gb.placeToken('O',1);
        boardExpected[0][1] = 'O';
        gb.placeToken('X',2);
        boardExpected[0][2] = 'X';
        gb.placeToken('O', 3);
        boardExpected[0][3] = 'O';
        String expected = expectedString(boardExpected);
        assertEquals(expected, gb.toString());
        BoardPosition pos = new BoardPosition(1,1);
        assertEquals(' ', gb.whatsAtPos(pos));
    }
    @Test
    public void testIsPlayerAtPos_yes_1(){
        char[][] boardExpected = new char[4][4];
        for(int i = 0; i < boardExpected.length; ++i){
            Arrays.fill(boardExpected[i], ' ');
        }
        IGameBoard gb = IGameFactory(4,4,3);
        gb.placeToken('X',0);
        boardExpected[0][0] = 'X';
        gb.placeToken('O', 1);
        boardExpected[0][1] = 'O';
        gb.placeToken('X',2);
        boardExpected[0][2] = 'X';
        gb.placeToken('O', 3);
        boardExpected[0][3] = 'O';
        String expected = expectedString(boardExpected);
        assertEquals(expected,gb.toString());
        BoardPosition pos = new BoardPosition(0,0);
        assertTrue(gb.isPlayerAtPos(pos, 'X'));
    }
    @Test
    public void testIsPlayerAtPos_yes_2(){
        char[][] boardExpected = new char[4][4];
        for(int i = 0; i < boardExpected.length; ++i){
            Arrays.fill(boardExpected[i], ' ');
        }
        IGameBoard gb = IGameFactory(4,4,3);
        gb.placeToken('X',3);
        boardExpected[0][3] = 'X';
        gb.placeToken('O',2);
        boardExpected[0][2] = 'O';
        gb.placeToken('X',1);
        boardExpected[0][1] = 'X';
        gb.placeToken('O',0);
        boardExpected[0][0] = 'O';
        String expected = expectedString(boardExpected);
        assertEquals(expected, gb.toString());
        BoardPosition pos = new BoardPosition(0,3);
        assertTrue(gb.isPlayerAtPos(pos, 'X'));
    }
    @Test
    public void testIsPlayerAtPos_no_1(){
        char[][] boardExpected = new char[4][4];
        for(int i = 0; i < boardExpected.length; ++i){
            Arrays.fill(boardExpected[i], ' ');
        }
        IGameBoard gb = IGameFactory(4,4,3);
        gb.placeToken('X',0);
        boardExpected[0][0] = 'X';
        gb.placeToken('O',0);
        boardExpected[1][0] = 'O';
        gb.placeToken('X',0);
        boardExpected[2][0] = 'X';
        gb.placeToken('O',0);
        boardExpected[3][0] = 'O';
        String expected = expectedString(boardExpected);
        assertEquals(expected,gb.toString());
        BoardPosition pos = new BoardPosition(3,0);
        assertFalse(gb.isPlayerAtPos(pos, 'X'));
    }
    @Test
    public void testIsPlayerAtPos_no_2(){
        char[][] boardExpected = new char[4][4];
        for(int i = 0; i < boardExpected.length; ++i){
            Arrays.fill(boardExpected[i], ' ');
        }
        IGameBoard gb = IGameFactory(4,4,3);
        gb.placeToken('X',3);
        boardExpected[0][3] = 'X';
        gb.placeToken('O',3);
        boardExpected[1][3] = 'O';
        gb.placeToken('X',3);
        boardExpected[2][3] = 'X';
        gb.placeToken('O',3);
        boardExpected[3][3] = 'O';
        String expected = expectedString(boardExpected);
        assertEquals(expected, gb.toString());
        BoardPosition pos = new BoardPosition(3,3);
        assertFalse(gb.isPlayerAtPos(pos,'X'));
    }
    @Test
    public void testIsPlayerAtPos_no_3(){
        char[][] boardExpected = new char[4][4];
        for(int i = 0; i < boardExpected.length; ++i){
            Arrays.fill(boardExpected[i], ' ');
        }
        IGameBoard gb = IGameFactory(4,4,3);
        gb.placeToken('X',0);
        boardExpected[0][0] = 'X';
        gb.placeToken('O',1);
        boardExpected[0][1] = 'O';
        gb.placeToken('X',2);
        boardExpected[0][2] = 'X';
        gb.placeToken('O',3);
        boardExpected[0][3] = 'O';
        String expected = expectedString(boardExpected);
        assertEquals(expected, gb.toString());
        BoardPosition pos = new BoardPosition(1,1);
        assertFalse(gb.isPlayerAtPos(pos, 'X'));
    }
    @Test
    public void testPlaceToken_col_empty(){
        char[][] boardExpected = new char[4][4];
        for(int i = 0; i < boardExpected.length; ++i){
            Arrays.fill(boardExpected[i], ' ');
        }
        IGameBoard gb = IGameFactory(4,4,3);
        gb.placeToken('X',1);
        boardExpected[0][1] = 'X';
        gb.placeToken('O', 2);
        boardExpected[0][2] = 'O';
        gb.placeToken('X',0);
        boardExpected[0][0] = 'X';
        String expected = expectedString(boardExpected);
        assertEquals(expected, gb.toString());
    }
    @Test
    public void testPlaceToken_col_not_empty_1(){
        char[][] boardExpected = new char[4][4];
        for(int i = 0; i < boardExpected.length; ++i){
            Arrays.fill(boardExpected[i], ' ');
        }
        IGameBoard gb = IGameFactory(4,4,3);
        gb.placeToken('X',1);
        boardExpected[0][1] = 'X';
        gb.placeToken('O',2);
        boardExpected[0][2] = 'O';
        gb.placeToken('X',0);
        boardExpected[0][0] = 'X';
        gb.placeToken('O',1);
        boardExpected[1][1] = 'O';
        gb.placeToken('X',2);
        boardExpected[1][2] = 'X';
        String expected = expectedString(boardExpected);
        assertEquals(expected,gb.toString());
    }
    @Test
    public void testPlaceToken_col_not_empty_2(){
        char[][] boardExpected = new char[4][4];
        for(int i = 0; i < boardExpected.length; ++i){
            Arrays.fill(boardExpected[i], ' ');
        }
        IGameBoard gb = IGameFactory(4,4,3);
        gb.placeToken('X',1);
        boardExpected[0][1] = 'X';
        gb.placeToken('O',2);
        boardExpected[0][2] = 'O';
        gb.placeToken('X',0);
        boardExpected[0][0] = 'X';
        gb.placeToken('O',1);
        boardExpected[1][1] = 'O';
        gb.placeToken('X',2);
        boardExpected[1][2] = 'X';
        gb.placeToken('O',0);
        boardExpected[1][0] = 'O';
        gb.placeToken('X',1);
        boardExpected[2][1] = 'X';
        String expected = expectedString(boardExpected);
        assertEquals(expected,gb.toString());
    }
    @Test
    public void testPlaceToken_col_not_empty_3(){
        char[][] boardExpected = new char[4][4];
        for(int i = 0; i < boardExpected.length; ++i){
            Arrays.fill(boardExpected[i], ' ');
        }
        IGameBoard gb = IGameFactory(4,4,3);
        gb.placeToken('X',1);
        boardExpected[0][1] = 'X';
        gb.placeToken('O',2);
        boardExpected[0][2] = 'O';
        gb.placeToken('X',0);
        boardExpected[0][0] = 'X';
        gb.placeToken('O',1);
        boardExpected[1][1] = 'O';
        gb.placeToken('X',2);
        boardExpected[1][2] = 'X';
        gb.placeToken('O',0);
        boardExpected[1][0] = 'O';
        gb.placeToken('X',1);
        boardExpected[2][1] = 'X';
        gb.placeToken('O',0);
        boardExpected[2][0] = 'O';
        gb.placeToken('X',0);
        boardExpected[3][0] = 'X';
        String expected = expectedString(boardExpected);
        assertEquals(expected,gb.toString());
    }
    @Test
    public void testPlaceToken_col_empty_row_full(){
        char[][] boardExpected = new char[4][4];
        for(int i = 0; i < boardExpected.length; ++i){
            Arrays.fill(boardExpected[i], ' ');
        }
        IGameBoard gb = IGameFactory(4,4,3);
        gb.placeToken('X',1);
        boardExpected[0][1] = 'X';
        gb.placeToken('O',2);
        boardExpected[0][2] = 'O';
        gb.placeToken('X',0);
        boardExpected[0][0] = 'X';
        gb.placeToken('O',1);
        boardExpected[1][1] = 'O';
        gb.placeToken('X',2);
        boardExpected[1][2] = 'X';
        gb.placeToken('O',0);
        boardExpected[1][0] = 'O';
        gb.placeToken('X',1);
        boardExpected[2][1] = 'X';
        gb.placeToken('O',0);
        boardExpected[2][0] = 'O';
        gb.placeToken('X',0);
        boardExpected[3][0] = 'X';
        gb.placeToken('O',2);
        boardExpected[2][2] = 'O';
        gb.placeToken('X',3);
        boardExpected[0][3] = 'X';
        String expected = expectedString(boardExpected);
        assertEquals(expected,gb.toString());
    }
}