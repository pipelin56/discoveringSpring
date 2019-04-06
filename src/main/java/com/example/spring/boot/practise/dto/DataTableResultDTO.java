package com.example.spring.boot.practise.dto;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.domain.Page;

public class DataTableResultDTO<T> implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2797671998107225143L;

	private int draw;
	
	private long recordsTotal;
	
	private long recordsFiltered;
	
	private String error;
	
	private List<T> data;
	
	public DataTableResultDTO(){
		super();
	}
			
	public DataTableResultDTO(Page<T> page, int draw, String error) {
		this.draw = draw;
		this.recordsTotal = page.getTotalElements();
		this.recordsFiltered = page.getTotalElements();
		this.error = error;
		this.data = page.getContent();
	}
	
	public DataTableResultDTO(List<T> list, int draw, String error) {
		this.draw = draw;
		this.recordsTotal = list.size();
		this.recordsFiltered = list.size();
		this.error = error;
		this.data = list;
	}

	public int getDraw() {
		return draw;
	}

	public void setDraw(int draw) {
		this.draw = draw;
	}

	public long getRecordsTotal() {
		return recordsTotal;
	}

	public void setRecordsTotal(long recordsTotal) {
		this.recordsTotal = recordsTotal;
	}

	public long getRecordsFiltered() {
		return recordsFiltered;
	}

	public void setRecordsFiltered(long recordsFiltered) {
		this.recordsFiltered = recordsFiltered;
	}
	
	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}
}
