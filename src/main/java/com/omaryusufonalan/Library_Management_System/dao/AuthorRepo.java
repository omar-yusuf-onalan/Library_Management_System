package com.omaryusufonalan.Library_Management_System.dao;

import com.omaryusufonalan.Library_Management_System.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AuthorRepo extends JpaRepository<Author, Integer> {

}
