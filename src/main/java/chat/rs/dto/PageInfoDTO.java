package chat.rs.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class PageInfoDTO implements Serializable {
    public PageInfoDTO() {
    }

    public PageInfoDTO(int page, int numberOfPostsPerPage) {
        this.page = page;
        this.numberOfPostsPerPage = numberOfPostsPerPage;
    }

    private int page;
    private int numberOfPostsPerPage;
}
