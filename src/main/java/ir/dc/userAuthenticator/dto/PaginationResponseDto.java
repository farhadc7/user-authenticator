package ir.dc.userAuthenticator.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaginationResponseDto<T> {

    private int page;
    private int pageSize;
    private int totalPage;
    private long totalCount;
    private List<T> items = new ArrayList<>();

    public PaginationResponseDto(Page<T> result) {
        items = result.getContent();
        page = result.getPageable().getPageNumber();
        pageSize = result.getSize();
        totalPage = result.getTotalPages();
        totalCount = result.getTotalElements();
    }

}
