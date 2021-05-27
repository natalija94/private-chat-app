package chat.rs;

import chat.rs.converter.DiscussionFilterConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.support.FormattingConversionService;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * @author natalija
 */
@Configuration
public class EnumConfiguration extends WebMvcConfigurationSupport {
    @Override
    public FormattingConversionService mvcConversionService() {
        FormattingConversionService f = super.mvcConversionService();
        f.addConverter(new DiscussionFilterConverter());
        return f;
    }
}
