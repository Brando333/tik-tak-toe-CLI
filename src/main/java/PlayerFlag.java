public enum PlayerFlag {

    PLAYER_1(1),
    PLAYER_2(-1),
    NO_WINNER(0);

    private final int value;

    PlayerFlag(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
