package com.smartdubai.bookmgmt.service;

import java.util.List;

import com.smartdubai.bookmgmt.dto.BookDTO;
import com.smartdubai.bookmgmt.response.BookResponse;

public interface BookService{
	
	BookResponse createBook(List<BookDTO> bookRequest);

	BookResponse findByBookId(Long id);

	BookResponse findAllBooks();

	BookResponse deleteBookById(Long id);

	BookResponse checkoutBook(List<BookDTO> bookRequest);

	BookResponse deleteAllBook();

	BookResponse updateBook(List<BookDTO> bookRequest);
	
}
