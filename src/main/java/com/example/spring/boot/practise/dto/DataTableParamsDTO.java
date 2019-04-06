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
		//if(iDisplayLength < 0 || iDisplayLength > 25 || iDisplayStart < 0 )
		//	return PageRequest.of(Constants.DEFAULT_NUM_PAGE, Constantes.DEFAULT_NUM_ROWS);
		//else
			return  PageRequest.of(this.iDisplayStart/this.iDisplayLength, this.iDisplayLength);
	}
	
	

}
