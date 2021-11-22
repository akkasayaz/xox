package tictactoe;
import java.sql.Array;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Scanner c = new Scanner(System.in);
        char[][] board = {{'_','_','_'},{'_','_','_'},{'_','_','_'}};
        while (getState(board) == "Game not finished"){
            displayBoard(board);
            int[] move = getMove(c);
            makeMove(board, move[0],move[1],turn(board),c);

        }
        displayBoard(board);
        System.out.println(getState(board));
        /*String cells = c.next();
        char[][] board  = boardData(cells);
        displayBoard(board);
        System.out.println(getState(board));
        char plays;
        //plays = turn(board);
        plays = 'X';
        int[] move = getMove(c);
        board = makeMove(board,move[0],move[1],plays,c);
        displayBoard(board);*/


    }

    public static char[][] boardData(String input) {
        char[][] board = new char[3][3];
        int i = 0;
        for (int r = 0; r < board.length; r++) {
            for (int c = 0; c < board[r].length; c++) {
                board[r][c] = input.charAt(i++);
            }
        }
        return board;
    }


    public static void displayBoard(char[][] board) {
        System.out.println("---------");
        for (char[] row:board) {
            System.out.print("| ");
            for (char col:row) {
                System.out.print(col + " ");
            }
            System.out.println("|");
        }
        System.out.println("---------");
    }


    public static String getState(char[][] board) {
        int totalX = 0;
        int totalO = 0;
        for (char[] row : board) {
            for (char symbol:row) {
                if (symbol == 'X') {
                    totalX++;
                } else if (symbol == 'O') {
                    totalO++;
                }
            }

        }
        int difference = totalO - totalX;
        if (difference < -1 || difference > 1) {
            return "Impossible";
        }
        boolean xWon = isSymbolWon(board, 'X');
        boolean oWon = isSymbolWon(board, 'O');
        if (xWon && oWon) {
            return "Impossible";
        }
        if (xWon) {
            return "X wins";
        }
        if (oWon) {
            return "O wins";
        }
        if (totalO + totalX == 9) {
            return "Draw";
        }
        return "Game not finished";
    }
    public static boolean isSymbolWon(char[][] board, char symbol) {
        return board[0][0] == symbol && board[0][1] == symbol && board[0][2] == symbol
                || board[1][0] == symbol && board[1][1] == symbol && board[1][2] == symbol
                || board[2][0] == symbol && board[2][1] == symbol && board[2][2] == symbol
                || board[0][0] == symbol && board[1][0] == symbol && board[2][0] == symbol
                || board[0][1] == symbol && board[1][1] == symbol && board[2][1] == symbol
                || board[0][2] == symbol && board[1][2] == symbol && board[2][2] == symbol
                || board[0][0] == symbol && board[1][1] == symbol && board[2][2] == symbol
                || board[0][2] == symbol && board[1][1] == symbol && board[2][0] == symbol;
    }
    public static char turn(char[][] board){
        int totalX = 0;
        int totalO = 0;
        for (char[] row : board) {
            for (char symbol:row) {
                if (symbol == 'X') {
                    totalX++;
                } else if (symbol == 'O') {
                    totalO++;
                }
            }

        }
        if (totalO >= totalX){
            return 'X';
        }else
            return 'O';
    }
    public static char[][] makeMove(char[][] board, int x, int y, char symbol, Scanner c) {
        if (board[x - 1][y - 1] == '_') {
            board[x - 1][y - 1] = symbol;

        } else {
            System.out.println("This cell is occupied! Choose another one!");
            int[] newMove = getMove(c);
            return makeMove(board, newMove[0], newMove[1], symbol, c);
        }
        return board;
    }

    public static int[] getMove(Scanner c){
        String exp = c.next();
        String exp2 = c.next();
        if (exp.matches("\\d")){
            if (exp.matches("[1-3]") && exp2.matches("[1-3]")){
                int x = Integer.parseInt(exp);
                int y = Integer.parseInt(exp2);
                int[] a = {x,y};
                return a;
            } else{
                System.out.println("Coordinates should be from 1 to 3!");
                return getMove(c);
            }

        } else {
            System.out.println("You should enter numbers!");
            return getMove(c);
        }
    }
}