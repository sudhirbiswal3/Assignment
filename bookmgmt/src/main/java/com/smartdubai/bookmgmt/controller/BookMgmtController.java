package com.smartdubai.bookmgmt.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smartdubai.bookmgmt.request.BookRequest;
import com.smartdubai.bookmgmt.response.BookResponse;
import com.smartdubai.bookmgmt.service.BookService;

@RestController
@RequestMapping(path = "/api/bookmgmt/v1")
public class BookMgmtController {
	
	@Autowired
	private BookService bookService;
		
	@PostMapping("/create")
	public ResponseEntity<BookResponse> createBook(@RequestBody BookRequest bookRequest) {
		BookResponse bookResponse = bookService.createBook(bookRequest.getBookRequest());				
		return ResponseEntity.created(bookResponse.getBookResponse().get(0).getUri()).body(bookResponse);
	}
	
	@PutMapping("/update")
	public ResponseEntity<BookResponse> updateBook(@RequestBody BookRequest bookRequest) {
		BookResponse bookResponse = bookService.updateBook(bookRequest.getBookRequest());				
		return ResponseEntity.created(bookResponse.getBookResponse().get(0).getUri()).body(bookResponse);
	}
	
	@GetMapping(value = "/get/{id}")
	public ResponseEntity<BookResponse> getBookById(@PathVariable("id") Long id) {
		BookResponse bookResponse = bookService.findByBookId(id);
		return new ResponseEntity<BookResponse>(bookResponse, HttpStatus.OK);
	}
	
	@GetMapping(value = "/getAll")
	public ResponseEntity<BookResponse> getAllBook() {
		BookResponse bookResponse = bookService.findAllBooks();
		return new ResponseEntity<BookResponse>(bookResponse, HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/delete/{id}")
	public ResponseEntity<BookResponse> deleteBookById(@PathVariable("id") Long id) {
		BookResponse bookResponse = bookService.deleteBookById(id);
		return new ResponseEntity<>(bookResponse, HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/deleteAll")
	public ResponseEntity<BookResponse> deleteAllBook() {
		BookResponse bookResponse = bookService.deleteAllBook();
		return new ResponseEntity<>(bookResponse, HttpStatus.OK);
	}
	
	@PostMapping("/checkout")
	public ResponseEntity<BookResponse> checkoutBook(@RequestBody BookRequest bookRequest) {
		BookResponse bookResponse = bookService.checkoutBook(bookRequest.getBookRequest());				
		return new ResponseEntity<>(bookResponse, HttpStatus.OK);
	}
}
