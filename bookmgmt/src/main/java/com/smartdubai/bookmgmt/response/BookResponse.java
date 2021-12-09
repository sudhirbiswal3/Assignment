package com.smartdubai.bookmgmt.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.smartdubai.bookmgmt.dto.BookDTO;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "isSuccess","bookResponse"})
public class BookResponse {
	
	@JsonProperty("isSuccess")
	private boolean isSuccess;
	
	@JsonProperty("bookResponse")
	private List<BookDTO> bookResponse;
		
	@JsonProperty("bookResponse")
	public List<BookDTO> getBookResponse() {
		return bookResponse;
	}
	@JsonProperty("bookResponse")
	public void setBookResponse(List<BookDTO> bookResponse) {
		this.bookResponse = bookResponse;
	}
	@JsonProperty("isSuccess")
	public boolean isSuccess() {
		return isSuccess;
	}
	@JsonProperty("isSuccess")
	public void setSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}	
	
}
