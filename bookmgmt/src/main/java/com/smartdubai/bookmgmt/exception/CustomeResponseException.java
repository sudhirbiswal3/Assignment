package com.smartdubai.bookmgmt.exception;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.smartdubai.bookmgmt.dto.BookDTO;
import com.smartdubai.bookmgmt.response.BookResponse;

@ControllerAdvice
@RestController
public class CustomeResponseException  extends ResponseEntityExceptionHandler{
	
	@ExceptionHandler(RecordNotFoundException.class)
	public final ResponseEntity<BookResponse> handleAllExceptions(Exception ex, WebRequest request) {
		BookResponse bookResponse = populateErrorDetails(ex);
		return new ResponseEntity<BookResponse>(bookResponse, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(IllegalArgumentException.class)
	public final ResponseEntity<BookResponse> handleInvalidRequest(Exception ex, WebRequest request) {
		BookResponse bookResponse = populateErrorDetails(ex);
		return new ResponseEntity<BookResponse>(bookResponse, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<BookResponse> handleUnexpectedError(Exception ex, WebRequest request) {
		BookResponse bookResponse = populateErrorDetails(ex);
		return new ResponseEntity<BookResponse>(bookResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	private BookResponse populateErrorDetails(Exception ex) {
		BookResponse bookResponse = new BookResponse();		
		bookResponse.setSuccess(false);	
		List<BookDTO> list = new ArrayList<>();
		BookDTO bookDto = new BookDTO();
		bookDto.setErrorMessage(ex.getMessage());
		list.add(bookDto);
		bookResponse.setBookResponse(list);
		return bookResponse;
	}
}
