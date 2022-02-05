import java.util.Scanner;

public class Driver {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int defaultN = 3;
        int defaultNumOfPlayers = 2;
        Game game;

        while (true) {

            int n = defaultN;
            int numOfPlayers = defaultNumOfPlayers;

            System.out.println("=========TIC-TAC-TOE==========\n");
            System.out.print("Length of board (default: 3) - ");
            String response = sc.nextLine();
            if (!response.isBlank()) {
                try {
                    n = Integer.parseInt(response);
                } catch (NumberFormatException e) {
                    System.out.println("Improper length for board");
                    continue;
                }
            }

            boolean isMultiplayer = false;
            System.out.print("Do you want to play multiplayer (default: n) (y/n) - ");
            response = sc.nextLine();

            if (response.trim().equalsIgnoreCase("y")) {
                isMultiplayer = true;
            }
            try {

                if (isMultiplayer) {
                    System.out.print("Number of players (default: 2) - ");
                    response = sc.nextLine();

                    if (!response.isBlank()) {
                        try {
                            numOfPlayers = Integer.parseInt(response);
                        } catch (NumberFormatException e) {
                            System.out.println("Improper number of players");
                            continue;
                        }
                    }
                }

                game = new Game(n, numOfPlayers, isMultiplayer);
                game.run();

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

            System.out.print("Do you want to play again (y/n) - ");
            response = sc.nextLine();
            if (!response.trim().equalsIgnoreCase("y")) {
                break;
            }
        }
    }
}
