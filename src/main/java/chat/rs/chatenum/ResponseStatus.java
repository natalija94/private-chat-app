package chat.rs.chatenum;

/**
 * @author natalija
 */
public enum ResponseStatus {
    ERROR("ERROR"),
    SUCCESS("SUCCESS");

    private String value;

    private ResponseStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
