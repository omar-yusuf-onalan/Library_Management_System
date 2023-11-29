package com.omaryusufonalan.Library_Management_System.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "books")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id", columnDefinition = "serial")
    private int id;

    @Column(name = "book_name", nullable = false)
    private String name;

    @Column(name = "book_publication_year", nullable = false)
    private int publicationYear;

    @Column(name = "book_stock", nullable = false)
    private int stock;

    @JsonIgnore
    @OneToMany(mappedBy = "book", cascade = CascadeType.REMOVE)
    private List<BookBorrowing> bookBorrowingList;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "book_author_id", referencedColumnName = "author_id")
    private Author author;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "book_publisher_id", referencedColumnName = "publisher_id")
    private Publisher publisher;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "books2categories",
            joinColumns = {@JoinColumn(name = "books2categories_book_id")},
            inverseJoinColumns = {@JoinColumn(name = "books2categories_category_id")}
    )
    private List<Category> categoryList;
}
