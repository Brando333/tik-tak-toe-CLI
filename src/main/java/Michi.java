public class Michi {

    private final int[][] grid = new int[3][3];


    public Michi() {

    }

    public void setMove(PlayerFlag playerFlag, int x, int y) throws CellIsNotEmptyException {
        if (isEmpty(x, y))
            grid[x][y] = playerFlag.getValue();
        else throw new CellIsNotEmptyException("La celda no está vacia");
    }


    public PlayerFlag checkIfAnyoneWon() {
        int[] rowSum = new int[3];
        int[] colSum = new int[3];
        int firstDiagonalSum = 0;
        int secondDiagonalSum = 0;


        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                rowSum[i] += grid[i][j];
                colSum[j] += grid[i][j];

                if (i == j) {
                    firstDiagonalSum += grid[i][j];
                }
                if (i == 2 - j) {
                    secondDiagonalSum += grid[i][j];
                }
            }
        }

        if (firstDiagonalSum == 3 || secondDiagonalSum == 3) {
            return PlayerFlag.PLAYER_1;
        } else if (firstDiagonalSum == -3 || secondDiagonalSum == -3) {
            return PlayerFlag.PLAYER_2;
        }

        for (int i = 0; i < 3; i++) {
            if (rowSum[i] == 3 || colSum[i] == 3) {
                return PlayerFlag.PLAYER_1;
            } else if (rowSum[i] == -3 || colSum[i] == -3) {
                return PlayerFlag.PLAYER_2;
            }
        }
        return PlayerFlag.NO_WINNER;

    }

    private boolean isEmpty(int x, int y) {
        return grid[x][y] == 0;
    }

    public void getDraw() {
        for (int i = 0; i < 3; i++) {
            StringBuilder sb = new StringBuilder();
            for (int[] ints : grid) {
                if (ints[i] == 0) {
                    sb.append("| |");
                } else if (ints[i] == PlayerFlag.PLAYER_1.getValue()) {
                    sb.append("|X|");
                } else if (ints[i] == PlayerFlag.PLAYER_2.getValue()) {
                    sb.append("|O|");
                }
            }
            System.out.println(sb.toString().replace("||", "|"));
        }
    }
}
