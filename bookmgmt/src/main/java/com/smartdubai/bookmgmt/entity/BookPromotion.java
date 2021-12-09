package com.smartdubai.bookmgmt.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "book_promo_master")
public class BookPromotion {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;	
	private Long bookId;
	private String type;	
	private String promoCode;
	private String promoDesc;
	private Integer discount;
	private String discountCalcType;
	private String enablePromo;	
	
	@OneToOne(mappedBy = "bookPromotion")
	private Book book;
		
	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}		
	public String getType() {
		return type;
	}	
	public Long getBookId() {
		return bookId;
	}
	public void setBookId(Long bookId) {
		this.bookId = bookId;
	}
	public void setType(String type) {
		this.type = type;
	}	
	public String getPromoCode() {
		return promoCode;
	}
	public void setPromoCode(String promoCode) {
		this.promoCode = promoCode;
	}
	public String getPromoDesc() {
		return promoDesc;
	}
	public void setPromoDesc(String promoDesc) {
		this.promoDesc = promoDesc;
	}	
	public Integer getDiscount() {
		return discount;
	}
	public void setDiscount(Integer discount) {
		this.discount = discount;
	}
	public String getDiscountCalcType() {
		return discountCalcType;
	}
	public void setDiscountCalcType(String discountCalcType) {
		this.discountCalcType = discountCalcType;
	}
	public String getEnablePromo() {
		return enablePromo;
	}
	public void setEnablePromo(String enablePromo) {
		this.enablePromo = enablePromo;
	}	
	
}
