package com.smartdubai.bookmgmt.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.smartdubai.bookmgmt.entity.Book;

@Repository
public interface BookRepository extends JpaRepository<Book,Long>{

}
