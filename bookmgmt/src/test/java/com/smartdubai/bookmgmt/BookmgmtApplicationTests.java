package com.smartdubai.bookmgmt;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.smartdubai.bookmgmt.controller.BookMgmtController;

@SpringBootTest
class BookmgmtApplicationTests {

	@Autowired
	private BookMgmtController bookMgmtController;
	
	@Test
	void contextLoads() {
		assertThat(bookMgmtController).isNotNull();
	}

}
