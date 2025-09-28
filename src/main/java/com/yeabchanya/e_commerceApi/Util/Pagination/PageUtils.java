package com.yeabchanya.e_commerceApi.Util.Pagination;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Map;

public interface PageUtils {

	int DEFAULT_PAGE_LIMIT = 2;
	int DEFAULT_PAGE_NUMBER = 1;
	String PAGE_LIMIT = "_limit";
	String PAGE_NUMBER = "_page";

	private static Pageable getPageable(int pageNumber, int pageSize) {

		if (pageNumber < DEFAULT_PAGE_NUMBER) {
			pageNumber = DEFAULT_PAGE_NUMBER;
		}

		if (pageSize < DEFAULT_PAGE_LIMIT) {
			pageSize = DEFAULT_PAGE_LIMIT;
		}

		return PageRequest.of(pageNumber - 1, pageSize);
	}

	public static Pageable fromParams(Map<String, String> params) {

		int pageLimit = PageUtils.DEFAULT_PAGE_LIMIT;
		if (params.containsKey(PageUtils.PAGE_LIMIT)) {
			pageLimit = Integer.parseInt(params.get(PageUtils.PAGE_LIMIT));
		}

		int pageNumber = PageUtils.DEFAULT_PAGE_NUMBER;
		if (params.containsKey(PageUtils.PAGE_NUMBER)) {
			pageNumber = Integer.parseInt(params.get(PageUtils.PAGE_NUMBER));
		}

		return PageUtils.getPageable(pageNumber, pageLimit);

	}

}