package chat.rs.util;

import chat.rs.dto.PageInfoDTO;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

/**
 * @author natalija
 */
public class PageableFactory {
    /**
     * Util class. Private constructor.
     */
    private PageableFactory() {
    }

    /**
     * Creates appropriate Pageable ready for filtering the discussion.
     * If appropriate params are not passed it returns a default Pageable.
     *
     * @param pageInfoDTO requested page details.
     * @return
     */
    public static Pageable pageableInstance(PageInfoDTO pageInfoDTO) {
        if (pageInfoDTO != null && pageInfoDTO.getPage() >= 0 && pageInfoDTO.getNumberOfPostsPerPage() > 0) {
            return PageRequest.of(pageInfoDTO.getPage(), pageInfoDTO.getNumberOfPostsPerPage());
        } else {
            return PageRequest.of(Constants.DEFAULT_PAGE_NUMBER, Constants.DEFAULT_NUMBER_OF_MESSAGES_PER_REQUEST);
        }
    }

}
