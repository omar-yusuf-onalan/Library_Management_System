package com.omaryusufonalan.Library_Management_System.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "categories")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id", columnDefinition = "serial")
    private int id;

    @Column(name = "category_name", nullable = false)
    private String name;

    @Column(name = "category_description")
    private String description;

    @JsonIgnore
    @ManyToMany(mappedBy = "categoryList", cascade = CascadeType.REMOVE)
    private List<Book> bookList;
}
