import javax.management.relation.RelationNotFoundException;
import java.util.Random;
import java.util.Scanner;

public class Game {

    private char[][] board;
    private static final Scanner sc = new Scanner(System.in);
    private Player[] players;
    private boolean isMultiplayer ;

    public Game(int len, int numOfPlayers) throws Exception {
        this(len, numOfPlayers, false);
    }

    public Game(int len, int numOfPlayers, boolean isMultiplayer) throws Exception {

        if(len <= 0 || len >= 100 || numOfPlayers <= 1 || numOfPlayers >= 26) {
            throw new Exception("Wrong arguments");
        }
        this.board = new char[len][len];
        this.players = new Player[numOfPlayers];
        this.isMultiplayer = isMultiplayer;
        initialize();
    }

    public void initialize() {
        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board.length; j++) {
                board[i][j] = '-';
            }
        }

        if(!isMultiplayer) {
            players[0] = Player.X;
            players[1] = Player.COMPUTER;
            return;
        }

        if(players.length == 2) {
            players[0] = Player.X;
            players[1] = Player.O;
        } else {
            for(int i = 0; i < players.length; i++)
                players[i] = new Player(null, (char) ('a'+i), i);
        }
    }

    public void run() {

        System.out.println("Enter usernames");
        for(int i = 0; i < players.length; i++) {
            if(players[i] == Player.COMPUTER)
                continue;

            System.out.print(players[i].getSymbol() + " - ");
            String name = sc.nextLine();
            if(name.isBlank()) {
                i--;
                System.out.println("Empty username not allowed.");
            }

            players[i].setName(name);
        }

        Player currPlayer = players[0];
        Player winner = null;
        int currRow;
        int currCol;
        Random rand = new Random();
        displayBoard();

        while(!hasOver(board)) {

            if(currPlayer == Player.COMPUTER) {
                System.out.print(currPlayer.getName() + " turn (row col) - ");
                currRow = rand.nextInt(board.length);
                currCol = rand.nextInt(board.length);

                while(!isValid(currRow, currCol)) {
                    currRow = rand.nextInt(board.length);
                    currCol = rand.nextInt(board.length);
                }

                System.out.println(currRow + " " + currCol);

            } else {
                System.out.print(currPlayer.getName() + " turn (row col) - ");
                currRow = sc.nextInt() - 1;
                currCol = sc.nextInt() - 1;
            }

            if(!isValid(currRow, currCol)) {
                System.out.println("Invalid move.\n");
                continue;
            }

            board[currRow][currCol] = currPlayer.getSymbol();

            displayBoard();

            if(hasWon(currPlayer)) {
                winner = currPlayer;
                break;
            }
            currPlayer = nextPlayer(currPlayer);

        }

        if(winner == null) {
            System.out.println("It's draw.");
        } else {
            System.out.println(winner.getName() + " won (" + winner.getSymbol() + ")");
        }

    }

    private boolean hasWon(Player currPlayer) {
        char symbol = currPlayer.getSymbol();
        return checkRows(symbol) || checkColumns(symbol) || checkDiagonals(symbol);
    }

    private boolean checkDiagonals(char symbol) {
        boolean isFound = true;
        for(int i = 1; i < board.length; i++) {
            if(board[i][i] != symbol || board[i][i] != board[i-1][i-1]) {
                isFound = false;
                break;
            }
        }

        if(isFound) return isFound;

        isFound = true;
        for(int i = 1; i < board.length; i++) {
            if(board[i][board.length-i-1] != symbol || board[i][board.length-i-1] != board[i-1][board.length-i]) {
                isFound = false;
                break;
            }
        }

        return isFound;
    }

    private boolean checkColumns(char symbol) {
        for(int j = 0; j < board.length; j++) {
            boolean isFound = true;
            for(int i = 1; i < board.length; i++) {
                if (board[i][j] != symbol || board[i][j] != board[i-1][j]) {
                    isFound = false;
                    break;
                }
            }
            if(isFound)
                return true;
        }
        return false;
    }

    private boolean checkRows(char symbol) {
        for(int i = 0; i < board.length; i++) {
            boolean isFound = true;
            for(int j = 1; j < board.length; j++) {
                if (board[i][j] != symbol || board[i][j] != board[i][j - 1]) {
                    isFound = false;
                    break;
                }
            }
            if(isFound)
                return true;
        }
        return false;
    }

    private Player nextPlayer(Player currPlayer) {
        return players[(currPlayer.getIndex() + 1) % players.length];
    }

    private boolean isValid(int currRow, int currCol) {
        return currRow >= 0 && currCol >= 0 && currRow < board.length && currCol < board.length && board[currRow][currCol] == '-';
    }

    private boolean hasOver(char[][] board) {
        for(char[] chars: board) {
            for(int j = 0; j < board.length; j++) {
                if(chars[j] == '-')
                    return false;
            }
        }

        return true;
    }

    public void displayBoard() {
        for (char[] chars : board) {
            for (int j = 0; j < board.length; j++) {
                System.out.print(chars[j]);
            }
            System.out.println();
        }
    }

}
