package chat.rs.converter;

import chat.rs.chatenum.DiscussionFilter;
import org.springframework.core.convert.converter.Converter;

public class DiscussionFilterConverter implements Converter<String, DiscussionFilter> {

    @Override
    public DiscussionFilter convert(String source) {
        try {
            return DiscussionFilter.valueOf(source);
        } catch (Exception e) {
            return DiscussionFilter.NONE;
        }
    }
}
