package com.example.week7_spring.Controller;

import com.example.week7_spring.Repository.WordRepository;
import com.example.week7_spring.domain.Word;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class WordController {

    private final WordRepository wordrepository;

    @GetMapping("/save")
    public Word createWord(@RequestParam String content){

        Word new_word = new Word(content);
        return wordrepository.save(new_word);
    }
}
