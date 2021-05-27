package chat.rs.chatenum;

/**
 * @author natalija
 */
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
