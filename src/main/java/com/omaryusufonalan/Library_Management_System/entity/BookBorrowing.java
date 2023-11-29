package com.omaryusufonalan.Library_Management_System.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "borrowing")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookBorrowing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "borrowing_id", columnDefinition = "serial")
    private int id;

    @Column(name = "borrower_name", nullable = false)
    private String borrowerName;

    @Temporal(TemporalType.DATE)
    @Column(name = "borrowing_date", nullable = false)
    private LocalDate borrowingDate;

    @Temporal(TemporalType.DATE)
    @Column(name = "return_date", nullable = false)
    private LocalDate returnDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "borrowing_book_id", referencedColumnName = "book_id")
    private Book book;
}
