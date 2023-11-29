package com.omaryusufonalan.Library_Management_System.dao;

import com.omaryusufonalan.Library_Management_System.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface BookRepo extends JpaRepository<Book, Integer> {
    @Query(value = "SELECT * FROM books WHERE book_author_id = ?1", nativeQuery = true)
    List<Book> findByAuthorIdNative(int authorId);

    @Query(value = "SELECT books2categories_category_id FROM books2categories WHERE books2categories_book_id = :bookId", nativeQuery = true)
    List<Integer> findByBookIdNative(@Param("bookId") int bookId);

    @Query(value = "INSERT INTO books2categories(books2categories_book_id, books2categories_category_id) VALUES (:bookId, :categoryId)", nativeQuery = true)
    @Modifying
    @Transactional
    void insertIntoBooksToCategories(@Param("bookId") int bookId, @Param("categoryId") int categoryId);
}
