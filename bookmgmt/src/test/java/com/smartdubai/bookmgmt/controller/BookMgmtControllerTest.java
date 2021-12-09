package com.smartdubai.bookmgmt.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.smartdubai.bookmgmt.dto.BookDTO;
import com.smartdubai.bookmgmt.response.BookResponse;
import com.smartdubai.bookmgmt.service.BookService;

@RunWith(SpringRunner.class)
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@WebMvcTest(BookMgmtController.class)
public class BookMgmtControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private BookService bookService;

	private List<BookDTO> bookRequest;

	private List<BookDTO> bookResponse;

	private BookResponse bookResObj;

	private BookDTO bookDTO;

	@Before
	public void setUp() {
		bookRequest = new ArrayList<>();
		bookDTO = new BookDTO();
		bookDTO.setName("test9");
		bookDTO.setDescription("test description9");
		bookDTO.setAuthor("author9");
		bookDTO.setType("Comic");
		bookDTO.setPrice(1000d);
		bookDTO.setIsbn("xyzf");
		bookRequest.add(bookDTO);

		bookResponse = new ArrayList<>();
		bookDTO = new BookDTO();
		bookDTO.setId(1L);
		try {
			bookDTO.setUri(new URI("http://localhost:8090/api/bookmgmt/v1/get/1"));
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		bookResponse.add(bookDTO);
		
		bookResObj = new BookResponse();
		bookResObj.setSuccess(true);
		bookResObj.setBookResponse(bookResponse);
		when(bookService.createBook(bookRequest)).thenReturn(bookResObj);
	}

	@Test
	public void createBookTest() throws Exception {		
		this.mockMvc.perform(post("/api/bookmgmt/v1/create").content(asJsonString(bookRequest))
				.contentType(MediaType.APPLICATION_JSON)).andDo(print())
				.andExpect(status().is5xxServerError());
	}

	@Test
	public void getAllBookTest() throws Exception {
		when(bookService.findAllBooks()).thenReturn(new BookResponse());
		this.mockMvc.perform(get("/api/bookmgmt/v1/getAll")).andDo(print()).andExpect(status().isOk());
	}

	private static String asJsonString(final Object bookRequest) {
		try {
			String bookJson = new ObjectMapper().writeValueAsString(bookRequest);
			String str = "{\"bookRequest\":";
			String request = str + bookJson + "}";
			return request;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	private static String asJsonStringResponse(final Object obj) {
		try {
			String bookJson = new ObjectMapper().writeValueAsString(obj);
			System.out.println(bookJson);
			return bookJson;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
