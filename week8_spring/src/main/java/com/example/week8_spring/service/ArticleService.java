package com.example.week8_spring.service;

import com.example.week8_spring.domain.Article;
import com.example.week8_spring.domain.Comment;
import com.example.week8_spring.dto.ArticleRequestDto;
import com.example.week8_spring.repository.ArticleRepository;
import com.example.week8_spring.repository.commentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ArticleService {
    private final ArticleRepository articleRepository;
    private final commentRepository commentrepository;
    @Autowired
    public ArticleService(ArticleRepository articleRepository, commentRepository commentrepository) {

        this.articleRepository = articleRepository;
        this.commentrepository = commentrepository;
    }

    public Article createArticle(ArticleRequestDto articleRequestDto){
        Article article = new Article(articleRequestDto);
        articleRepository.save(article);
        return article;
    }

    public List<Article> getArticle(){
        return articleRepository.findAll();
    }

    public Article getArticles(Long id){
        return articleRepository.findById(id).orElseThrow(
                () -> new NullPointerException("해당 아이디가 존재하지 않습니다")
        );
    }

    @Transactional
    public void setArticleComment(ArticleRequestDto articleRequestDto){
        Article article = articleRepository.findById(articleRequestDto.getIdx()).orElseThrow(
                () -> new NullPointerException("해당 아이디가 존재하지 않습니다.")
        );
        Comment comment = new Comment(articleRequestDto, article);
        commentrepository.save(comment);
    }

}
