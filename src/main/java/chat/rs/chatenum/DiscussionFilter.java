package chat.rs.chatenum;

/**
 * @author natalija
 */
public enum DiscussionFilter {
    NONE("NONE"),
    APPROPRIATE_CONTENT("APPROPRIATE_CONTENT"),
    OFFENSIVE_CONTENT("OFFENSIVE_CONTENT");

    private final String value;

    private DiscussionFilter(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
