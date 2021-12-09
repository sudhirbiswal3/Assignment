package com.smartdubai.bookmgmt.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.smartdubai.bookmgmt.dto.BookDTO;
import com.smartdubai.bookmgmt.entity.Book;
import com.smartdubai.bookmgmt.exception.RecordNotFoundException;
import com.smartdubai.bookmgmt.repo.BookRepository;
import com.smartdubai.bookmgmt.response.BookResponse;
import com.smartdubai.bookmgmt.service.BookService;
import com.smartdubai.bookmgmt.utils.BookServiceHelper;
import com.smartdubai.bookmgmt.utils.CustomMessageConstants;

@Service
@Transactional
public class BookServiceImpl implements BookService {

	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private BookRepository bookRepository;
	
	@Autowired
	private BookServiceHelper bookServiceHelper;
	
	@Override
	public BookResponse createBook(List<BookDTO> bookRequest) {	
		List<Book> bookList = bookRequest.stream().map(a -> bookServiceHelper.copyRequestToBookEntity(a)).collect(Collectors.toList());
		List<Book> bookResultList = bookRepository.saveAllAndFlush(bookList);
		List<BookDTO> bookDtoList = bookResultList.stream().map(b -> bookServiceHelper.createBookDto(b.getId())).collect(Collectors.toList());			
		return bookServiceHelper.populateResponse(bookDtoList);
	}	

	@Override
	public BookResponse findByBookId(Long id) {
		Optional<Book> bookr = bookRepository.findById(id);
		return bookServiceHelper.copyBookEntityToResponse(bookr);
	}

	@Override
	public BookResponse findAllBooks() {
		List<Book> bookList = bookRepository.findAll();		
		List<BookDTO> bookDtoList = bookList.stream().collect(Collectors.mapping(a-> {
			BookDTO bookDto = new BookDTO();
			bookDto.setId(a.getId());
			bookDto.setUri(bookServiceHelper.getURI(a.getId()));
			bookDto.setName(a.getName());
			bookDto.setAuthor(a.getAuthor());
			bookDto.setType(a.getType());
			bookDto.setPrice(a.getPrice());
			bookDto.setIsbn(a.getIsbn());
			bookDto.setPromoCode(a.getPromoCode());
			bookDto.setDiscount(a.getDiscount());
			return bookDto;
			
		}, Collectors.toList()));		
		
		return bookServiceHelper.populateResponse(bookDtoList);
	}

	@Override
	public BookResponse deleteBookById(Long id) {
		try {
			bookRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			LOGGER.warn("Error during delete book >>>> ", e.getMessage());
			throw new RecordNotFoundException(CustomMessageConstants.NO_RECOURD_FOUND);
		}		
		List<BookDTO> list = new ArrayList<>();
		list.add(bookServiceHelper.createBookDto(id));
		return bookServiceHelper.populateResponse(list);
	}

	@Override
	public BookResponse checkoutBook(List<BookDTO> bookRequest) {
		
		List<Long> idList = bookRequest.stream().map(a -> a.getId()).collect(Collectors.toList());
		List<Book> resultList = bookRepository.findAllById(idList);	
		Double totalPrice = resultList.stream().mapToDouble(s ->{ Double price = s.getPrice(); Integer discount = s.getBookPromotion().getDiscount();
		if(s.getPromoCode()!=null) {
			Double result = price - (price * discount/100);
			LOGGER.info(" id "+ s.getId() + "  " + result);
			return result;	
		}
		return price;	
		}).sum();		
		return bookServiceHelper.populateCheckoutBookResponse(totalPrice);
	}

	@Override
	public BookResponse deleteAllBook() {
		bookRepository.deleteAll();
		BookDTO bookDto = new BookDTO();
		List<BookDTO> list = new ArrayList<>();	
		list.add(bookDto);
		return bookServiceHelper.populateResponse(list);
	}

	@Override
	public BookResponse updateBook(List<BookDTO> bookRequest) {;
		List<Long> ids = bookRequest.stream().map(a->a.getId()).collect(Collectors.toList());
		List<Book> bookListDb = bookRepository.findAllById(ids);
		List<Book> bookList = bookListDb.stream().map(a -> bookServiceHelper.setRequestDetailToEntity(a,bookRequest)).collect(Collectors.toList());		
		List<Book> bookResultList = bookRepository.saveAllAndFlush(bookList);
		return bookServiceHelper.populateResponseUpdate(bookResultList);
	}	
}
