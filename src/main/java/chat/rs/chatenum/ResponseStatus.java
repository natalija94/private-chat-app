package chat.rs.chatenum;

/**
 * @author natalija
 */
public enum ResponseStatus {
    /**
     * Error occurred.
     */
    ERROR("ERROR"),
    /**
     * Action success.
     */
    SUCCESS("SUCCESS");

    /**
     * String representation of an enum.
     */
    private String value;

    /**
     * Private constructor.
     *
     * @param value as String.
     */
    private ResponseStatus(String value) {
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
