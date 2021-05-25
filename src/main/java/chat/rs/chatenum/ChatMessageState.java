package chat.rs.chatenum;

public enum ChatMessageState {
    OKAY("OKAY"),
    OFFENSIVE("OFFENSIVE");

    private String value;

    private ChatMessageState(String value) {
    this.value = value;
    }

    public String getValue() {
        return value;
    }
}
