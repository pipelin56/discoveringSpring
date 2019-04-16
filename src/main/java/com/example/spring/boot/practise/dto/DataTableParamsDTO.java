package com.example.spring.boot.practise.dto;

import java.io.Serializable;
import org.springframework.data.domain.PageRequest;

public class DataTableParamsDTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7034248241923727489L;
	
	private Integer iDisplayLength;
	private Integer sEcho;
	private Integer iDisplayStart;
	private String sSearch;
	
	public DataTableParamsDTO() {}

	public Integer getiDisplayLength() {
		return iDisplayLength;
	}

	public void setiDisplayLength(Integer iDisplayLength) {
		this.iDisplayLength = iDisplayLength;
	}

	public Integer getsEcho() {
		return sEcho;
	}

	public void setsEcho(Integer sEcho) {
		this.sEcho = sEcho;
	}

	public Integer getiDisplayStart() {
		return iDisplayStart;
	}

	public void setiDisplayStart(Integer iDisplayStart) {
		this.iDisplayStart = iDisplayStart;
	}
	
	public PageRequest getPageRequest() {
			return  PageRequest.of(this.iDisplayStart/this.iDisplayLength, this.iDisplayLength);
	}

	public String getsSearch() {
		return sSearch;
	}

	public void setsSearch(String sSearch) {
		this.sSearch = sSearch;
	}
	
	

}
