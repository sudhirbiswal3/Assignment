
package com.smartdubai.bookmgmt.dto;

import java.net.URI;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
	"id",
    "name",
    "description",
    "author",
    "type",
    "price",
    "isbn",
    "promoCode",
    "discount"
})
public class BookDTO{

    @JsonProperty("id")
	private Long id;	
    @JsonProperty("name")
    private String name;
    @JsonProperty("description")
    private String description;
    @JsonProperty("author")
    private String author;
    @JsonProperty("type")
    private String type;
    @JsonProperty("price")
    private Double price;
    @JsonProperty("isbn")
    private String isbn;    
    @JsonProperty("promoCode")
    private String promoCode;      
    @JsonProperty("discount")
    private Integer discount;       
    @JsonProperty("uri")
	private URI uri;	    
    @JsonProperty("totalPrice")
    private Double totalPrice;    
	@JsonProperty("errorMessage")
	private String errorMessage;
	
	@JsonProperty("id")
	public Long getId() {
		return id;
	}
	@JsonProperty("id")
	public void setId(Long id) {
		this.id = id;
	}

	@JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("description")
    public String getDescription() {
        return description;
    }

    @JsonProperty("description")
    public void setDescription(String description) {
        this.description = description;
    }

    @JsonProperty("author")
    public String getAuthor() {
        return author;
    }

    @JsonProperty("author")
    public void setAuthor(String author) {
        this.author = author;
    }

    @JsonProperty("type")
    public String getType() {
        return type;
    }

    @JsonProperty("type")
    public void setType(String type) {
        this.type = type;
    }

    @JsonProperty("price")
    public Double getPrice() {
        return price;
    }

    @JsonProperty("price")
    public void setPrice(Double price) {
        this.price = price;
    }

    @JsonProperty("isbn")
    public String getIsbn() {
        return isbn;
    }

    @JsonProperty("isbn")
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
    @JsonProperty("promoCode")
   	public String getPromoCode() {
   		return promoCode;
   	}

    @JsonProperty("promoCode")
   	public void setPromoCode(String promoCode) {
   		this.promoCode = promoCode;
   	}
    
    @JsonProperty("uri")
	public URI getUri() {
		return uri;
	}
	@JsonProperty("uri")
	public void setUri(URI uri) {
		this.uri = uri;
	}
	@JsonProperty("totalPrice")
	public Double getTotalPrice() {
		return totalPrice;
	}
	@JsonProperty("totalPrice")
	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}
	@JsonProperty("errorMessage")
	public String getErrorMessage() {
		return errorMessage;
	}
	@JsonProperty("errorMessage")
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	@JsonProperty("discount")
	public Integer getDiscount() {
		return discount;
	}
	@JsonProperty("discount")
	public void setDiscount(Integer discount) {
		this.discount = discount;
	}
	
}
