package chat.rs.chatenum;

/**
 * @author natalija
 */
public enum ChatMessageState {
    /**
     * Message doesn't contain any offensive content.
     */
    OKAY("OKAY"),
    /**
     * Message contain not appropriate content.
     */
    OFFENSIVE("OFFENSIVE");

    /**
     * String representation of the enum.
     */
    private final String value;

    /**
     * Private constructor.
     *
     * @param value as String.
     */
    ChatMessageState(String value) {
        this.value = value;
    }

    /**
     * Getter.
     *
     * @return String representation of an enum.
     */
    public String getValue() {
        return value;
    }
}
