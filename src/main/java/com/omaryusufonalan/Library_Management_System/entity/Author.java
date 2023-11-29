package com.omaryusufonalan.Library_Management_System.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "authors")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "author_id", columnDefinition = "serial")
    private int id;

    @Column(name = "author_name", nullable = false)
    private String name;

    @Temporal(TemporalType.DATE)
    @Column(name = "author_birthdate", nullable = false)
    private LocalDate birthdate;

    @Column(name = "author_country", length = 30, nullable = false)
    private String country;

    @JsonIgnore
    @OneToMany(mappedBy = "author", cascade = CascadeType.REMOVE)
    private List<Book> bookList;
}
