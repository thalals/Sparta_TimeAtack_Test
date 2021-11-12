package com.example.week7_spring.Repository;

import com.example.week7_spring.domain.Word;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WordRepository extends JpaRepository<Word, Long> {
}
