package chat.rs.converter;

import chat.rs.chatenum.DiscussionFilter;
import org.springframework.core.convert.converter.Converter;

/**
 * @author natalija
 */
public class DiscussionFilterConverter implements Converter<String, DiscussionFilter> {
    /**
     * Custom DiscussionFilter. Converts String into appropriate DiscussionFilter.
     * @param source value to be converted.
     * @return converted value.
     */
    @Override
    public DiscussionFilter convert(String source) {
        try {
            return DiscussionFilter.valueOf(source);
        } catch (Exception e) {
            return DiscussionFilter.NONE;
        }
    }
}
