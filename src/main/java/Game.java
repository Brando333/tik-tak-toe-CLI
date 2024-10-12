import java.util.Arrays;
import java.util.Scanner;

public class Game {

    private final Scanner scanner = new Scanner(System.in);
    private Michi michi = new Michi();


    public Game(Michi michi) {
        this.michi = michi;
    }

    public void start() {
        michi.getDraw();
        System.out.println("Enter the cordinates with the format[x y], eg. 1 2");
        while (true) {
            handlePlayerTurn(PlayerFlag.PLAYER_1, "Player 1");
            if (checkForWinner(PlayerFlag.PLAYER_1)) return;

            handlePlayerTurn(PlayerFlag.PLAYER_2, "Player 2");
            if (checkForWinner(PlayerFlag.PLAYER_2)) return;
        }


    }

    private boolean checkForWinner(PlayerFlag player) {
        PlayerFlag playerFlag = michi.checkIfAnyoneWon();
        if (playerFlag == player) {
            System.out.println((player == PlayerFlag.PLAYER_1 ? "Player 1" : "Player 2") + " won the game.");
            return true;
        }
        return false;
    }

    private void handlePlayerTurn(PlayerFlag player, String playerName) {
        System.out.print(playerName + ": ");
        String playerCordinates = scanner.nextLine();
        int[] cordinates;
        if (!isCordinateValid(playerCordinates)) {
            System.out.println("Invalid cordinates, must be space delimited numbers within 0 - 2. eg. 2 2");
            handlePlayerTurn(player, playerName);

        } else {
            cordinates = parseCordinates(playerCordinates);
            int x = cordinates[0];
            int y = cordinates[1];

            try {
                michi.setMove(player, x, y);
            } catch (CellIsNotEmptyException e) {
                System.out.println("That cell is not empty, you moron.");
                handlePlayerTurn(player, playerName);
                return;
            }
            michi.getDraw();
        }
    }

    private boolean isCordinateValid(String playerCordinates) {
        return playerCordinates.matches("[0-2] [0-2]");
    }

    private int[] parseCordinates(String cordinates) {
        return Arrays.stream(cordinates.split(" ")).mapToInt(Integer::parseInt).toArray();
    }

}
