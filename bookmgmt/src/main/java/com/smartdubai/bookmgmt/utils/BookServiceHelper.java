package com.smartdubai.bookmgmt.utils;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.smartdubai.bookmgmt.dto.BookDTO;
import com.smartdubai.bookmgmt.entity.Book;
import com.smartdubai.bookmgmt.entity.BookPromotion;
import com.smartdubai.bookmgmt.exception.RecordNotFoundException;
import com.smartdubai.bookmgmt.response.BookResponse;

@Component
public class BookServiceHelper {

	private final  Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	public Book copyRequestToBookEntity(BookDTO bookRequest) {
		BookPromotion bookPromotion = new BookPromotion();
		bookPromotion.setDiscountCalcType("percentage");
		bookPromotion.setEnablePromo("true");
		bookPromotion.setPromoCode(bookRequest.getPromoCode());
		bookPromotion.setPromoDesc(bookRequest.getPromoCode());
		bookPromotion.setType(bookRequest.getType());
		bookPromotion.setDiscount(bookRequest.getDiscount()==null?0:bookRequest.getDiscount());
		Book book = new Book();
		BeanUtils.copyProperties(bookRequest, book);
		book.setBookPromotion(bookPromotion);
		bookPromotion.setBook(book);
		return book;
	}

	public BookResponse copyBookEntityToResponse(Optional<Book> bookr) {
		BookResponse bookResponse = new BookResponse();
		List<BookDTO> list = new ArrayList<>();
		BookDTO bookd = new BookDTO();
		bookd.setUri(getURI(bookr.get().getId()));
		if (!bookr.isPresent()) {
			LOGGER.warn("Book is not abailable please verify >>>> ");
			throw new RecordNotFoundException(CustomMessageConstants.NO_RECOURD_FOUND);
		}
		BeanUtils.copyProperties(bookr.get(), bookd);
		bookResponse.setSuccess(true);
		list.add(bookd);
		bookResponse.setBookResponse(list);
		return bookResponse;
	}

	public BookDTO createBookDto(Long id) {
		BookDTO bookd = new BookDTO();
		bookd.setId(id);
		bookd.setUri(getURI(id));
		return bookd;

	}

	public BookResponse populateCheckoutBookResponse(Double totalPrice) {
		BookResponse bookResponse = new BookResponse();
		BookDTO bookd = new BookDTO();
		bookResponse.setSuccess(true);
		bookd.setTotalPrice(totalPrice);
		List<BookDTO> list = new ArrayList<>();
		list.add(bookd);
		bookResponse.setBookResponse(list);
		return bookResponse;
	}

	public URI getURI(Long id) {
		return ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/bookmgmt/v1/get/{id}")
				.buildAndExpand(id).toUri();
	}
	public BookResponse populateResponse(List<BookDTO> bookDtoList) {
		BookResponse bookResponse = new BookResponse();
		bookResponse.setSuccess(true);
		bookResponse.setBookResponse(bookDtoList);
		return bookResponse;
	}
	
	public BookResponse populateResponseUpdate(List<Book> bookDtoList) {
		BookResponse bookResponse = new BookResponse();
		List<BookDTO> bookList = new ArrayList<>();
		bookDtoList.forEach(a->{
			BookDTO bookd = new BookDTO();
			bookd.setUri(getURI(a.getId()));
			BeanUtils.copyProperties(a, bookd);
			bookList.add(bookd);
			});
		bookResponse.setBookResponse(bookList);
		bookResponse.setSuccess(true);
		return bookResponse;
	}

	public Book setRequestDetailToEntity(Book book, List<BookDTO> bookRequest) {
		BookDTO bkdto = bookRequest.stream().filter(a-> a.getId() ==book.getId()).findAny().get();
		BeanUtils.copyProperties(bkdto, book);
		return book;
	}
}
