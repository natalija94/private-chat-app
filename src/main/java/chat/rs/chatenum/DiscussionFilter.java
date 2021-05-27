package chat.rs.chatenum;

/**
 * @author natalija
 */
public enum DiscussionFilter {
    /**
     * No filter is applied.
     */
    NONE("NONE"),
    /**
     * Filter only appropriate content.
     */
    APPROPRIATE_CONTENT("APPROPRIATE_CONTENT"),
    /**
     * Filter only offensive content.
     */
    OFFENSIVE_CONTENT("OFFENSIVE_CONTENT");

    /**
     * String representation of the enum.
     */
    private final String value;

    /**
     * Private constructor.
     *
     * @param value as String.
     */
    DiscussionFilter(String value) {
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
