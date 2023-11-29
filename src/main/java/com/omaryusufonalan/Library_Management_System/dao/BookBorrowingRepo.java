package com.omaryusufonalan.Library_Management_System.dao;
import com.omaryusufonalan.Library_Management_System.entity.BookBorrowing;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookBorrowingRepo extends JpaRepository<BookBorrowing, Integer> {
}
