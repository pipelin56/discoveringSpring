package com.example.spring.boot.practise.util.paginator;

public class PageItem {
	private Integer numberOfPage;
	private Boolean isActual;

	public PageItem(Integer numberOfPage, Boolean isActual) {
		super();
		this.numberOfPage = numberOfPage;
		this.isActual = isActual;
	}

	public Integer getNumberOfPage() {
		return numberOfPage;
	}

	public Boolean getIsActual() {
		return isActual;
	}

}
