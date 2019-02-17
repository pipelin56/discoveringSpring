package com.example.spring.boot.practise.util.paginator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;

public class PageRender<T> {
	
	private String url;
	private Page<T> page;
	private int totalPages;
	private int numElemntsPage;
	private int actualPage;
	private List<PageItem> listOfPages;
	
	public PageRender(String url, Page<T> page) {
		super();
		this.url = url;
		this.page = page;
		this.listOfPages = new ArrayList<>();
		
		numElemntsPage = page.getSize();
		totalPages = page.getTotalPages();
		actualPage = page.getNumber()+1; //start with 0
		
		int from;
		int to;
		if(totalPages <= numElemntsPage) {
			from = 1;
			to = totalPages;
		}else {
			if(actualPage <= numElemntsPage/2) {
				from = 1;
				to= numElemntsPage;
			}else if(actualPage >= totalPages - numElemntsPage/2) {
				from = totalPages - numElemntsPage + 1;
				to = numElemntsPage;
			}else {
				from = actualPage - numElemntsPage/2;
				to = numElemntsPage;
			}
		}
		
		for(int i=0; i<to; i++)
			listOfPages.add(new PageItem(from+i, actualPage == from+i));
	}

	public String getUrl() {
		return url;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public int getActualPage() {
		return actualPage;
	}

	public List<PageItem> getListOfPages() {
		return listOfPages;
	}
	
	public boolean isFirst() {
		return this.page.isFirst();
	}
	
	public boolean isLast() {
		return this.page.isLast();
	}
	
	public boolean isHasNext() {
		return this.page.hasNext();
	}
	
	public boolean isHasPrevious() {
		return this.page.hasPrevious();
	}

	public int getNumElemntsPage() {
		return numElemntsPage;
	}
	
	public long getTotalRows() {
		return page.getTotalElements();
	}

}
