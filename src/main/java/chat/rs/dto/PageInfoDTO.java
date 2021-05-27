package chat.rs.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author natalija
 */
@Data
public class PageInfoDTO implements Serializable {
    /**
     * No args constructor.
     */
    public PageInfoDTO() {
    }

    /**
     * Parametrized constructor.
     *
     * @param page
     * @param numberOfPostsPerPage
     */
    public PageInfoDTO(int page, int numberOfPostsPerPage) {
        this.page = page;
        this.numberOfPostsPerPage = numberOfPostsPerPage;
    }

    /**
     * Property for requested page number
     */
    private int page;

    /**
     * Property for requested number of posts per page.
     */
    private int numberOfPostsPerPage;
}
