package com.smartdubai.bookmgmt.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.smartdubai.bookmgmt.dto.BookDTO;
import com.smartdubai.bookmgmt.entity.Book;
import com.smartdubai.bookmgmt.repo.BookRepository;
import com.smartdubai.bookmgmt.request.BookRequest;
import com.smartdubai.bookmgmt.response.BookResponse;
import com.smartdubai.bookmgmt.service.impl.BookServiceImpl;
import com.smartdubai.bookmgmt.utils.BookServiceHelper;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;


@RunWith(SpringRunner.class)
public class BookServiceTest {
	@TestConfiguration
    static class BookServiceImplTestContextConfiguration {
 
        @Bean
        public BookService employeeService() {
            return new BookServiceImpl();
        }
    }

    @Autowired
    private BookService bookService;
    
    @MockBean
    private BookServiceHelper bookServiceHelper;

    @MockBean
    private BookRepository bookRepository;
    
    @Before
    public void setUp() {
    	
    	final Logger logger = (Logger)LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME);
        logger.setLevel(Level.ALL);
        
    	Book book = new Book();
    	book.setId(1l);
    	book.setName("book1");
    	book.setDescription("book description1");
    	book.setAuthor("author1");
    	book.setType("comic");
    	book.setPrice(1000d);
    	book.setIsbn("ISBN0001");
    	
        Optional<Book> bookOptional = Optional.of(book);
        Mockito.when(bookRepository.findById(book.getId())).thenReturn(bookOptional);
        
        BookResponse bookResonse = new BookResponse();
        List<BookDTO> bookDtoList = new ArrayList<>();
        BookDTO bookDto = new BookDTO();
        bookDto.setId(1l);
        bookDto.setName("book1");
        bookDto.setDescription("book description1");
        bookDto.setAuthor("author1");
        bookDto.setType("comic");
        bookDto.setPrice(1000d);
        bookDto.setIsbn("ISBN0001");
        bookDtoList.add(bookDto);
        bookResonse.setBookResponse(bookDtoList);   
        
        
        BookRequest bookRequest = new BookRequest();
        List<BookDTO> bookDtoList1 = new ArrayList<>();
        BookDTO bookDto2 = new BookDTO();
        bookDto2.setId(1l);
        bookDto2.setName("book1");
        bookDto2.setDescription("book description1");
        bookDto2.setAuthor("author1");
        bookDto2.setType("comic");
        bookDto2.setPrice(1000d);
        bookDto2.setIsbn("ISBN0001");
        bookDtoList1.add(bookDto);
        bookRequest.setBookRequest(bookDtoList1);  
        
        Mockito.when(bookServiceHelper.copyBookEntityToResponse(bookOptional)).thenReturn(bookResonse);
        Mockito.when(bookServiceHelper.copyRequestToBookEntity(bookDto2)).thenReturn(book);
        Mockito.when(bookServiceHelper.createBookDto(book.getId())).thenReturn(bookDto2);
        Mockito.when(bookRepository.save(book)).thenReturn(book);
    }
    
    @Test
    public void testCreateBook()
    {
    	List<BookDTO> bookRequest = new ArrayList<>();
    	BookDTO bookDto = new BookDTO();
        bookDto.setId(1l);
        bookDto.setName("book1");
        bookDto.setDescription("book description1");
        bookDto.setAuthor("author1");
        bookDto.setType("comic");
        bookDto.setPrice(1000d);
        bookDto.setIsbn("ISBN0001");
        bookRequest.add(bookDto);
        
    	BookResponse bookResponse = bookService.createBook(bookRequest);		
		assertNotNull("price is not null", bookResponse);
		
    }
    
    @Test
    public void testFindById()
    {
    	BookResponse bookResponse = bookService.findByBookId(1l);
		BookDTO bookDto = bookResponse.getBookResponse().get(0);
		assertNotNull("price is not null", bookDto.getPrice());
		assertEquals(new Long(1), bookDto.getId());
    }
       
}
