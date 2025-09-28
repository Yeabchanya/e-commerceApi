package com.yeabchanya.e_commerceApi.Dto.Response;

import com.yeabchanya.e_commerceApi.Dto.Request.PaginationRequest;
import lombok.Data;
import org.springframework.data.domain.Page;

import java.util.List;

@Data
public class PaginationResponse<B> {

    private List<?> list;
    private PaginationRequest paginationRequest;

    public PaginationResponse(Page<?> page) {

        this.list = page.getContent();

        this.paginationRequest = PaginationRequest.builder()
                .empty(page.isEmpty())
                .first(page.isFirst())
                .last(page.isLast())
                .pageSize(page.getPageable().getPageSize())
                .pageNumber(page.getPageable().getPageNumber() + 1)
                .totalPages(page.getTotalPages())
                .totalElements(page.getTotalElements())
                .numberOfElements(page.getNumberOfElements())
                .build();
    }
}