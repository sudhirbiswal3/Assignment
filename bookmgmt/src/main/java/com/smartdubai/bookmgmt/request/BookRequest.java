
package com.smartdubai.bookmgmt.request;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.smartdubai.bookmgmt.dto.BookDTO;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"bookRequest"})
public class BookRequest {

    @JsonProperty("bookRequest")
    private List<BookDTO> bookRequest;
      
    public List<BookDTO> getBookRequest() {
        return bookRequest;
    }

    public void setBookRequest(List<BookDTO> bookRequest) {
        this.bookRequest = bookRequest;
    }   
}
