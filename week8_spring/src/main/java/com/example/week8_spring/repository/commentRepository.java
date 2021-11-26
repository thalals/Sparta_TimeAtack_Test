package com.example.week8_spring.repository;

import com.example.week8_spring.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface commentRepository extends JpaRepository<Comment, Long> {

}
