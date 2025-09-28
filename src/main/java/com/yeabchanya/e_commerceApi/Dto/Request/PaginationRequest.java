package com.yeabchanya.e_commerceApi.Dto.Request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PaginationRequest {

	private int pageSize;
	private int pageNumber;
	private int totalPages;
	private Long totalElements;
	private int numberOfElements;

	private boolean last;
	private boolean first;
	private boolean empty;

}