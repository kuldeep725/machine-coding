public class Player {

    private String name;
    private char symbol;
    private int index;

    public static final Player COMPUTER = new Player("Computer", 'O', 1);
    public static final Player X = new Player("", 'X', 0);
    public static final Player O = new Player("", 'O', 1);

    public Player(String name, char symbol, int index) {
        this.name = name;
        this.symbol = symbol;
        this.index = index;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public char getSymbol() {
        return symbol;
    }

    public void setSymbol(char symbol) {
        this.symbol = symbol;
    }
}
