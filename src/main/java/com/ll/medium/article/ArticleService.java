package com.ll.medium.article;


import com.ll.medium.global.DataNotFoundException;
import com.ll.medium.article.entity.Article;
import com.ll.medium.article.entity.ArticleForm;
import com.ll.medium.member.entity.Member;
import com.ll.medium.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;
    private final MemberService memberService;

    public List<Article> getAllArticles(){
        return articleRepository.findAll();
    }

    public Page<Article> getAllArticlesByUsername(int page, Member member){
        Pageable pageable = PageRequest.of(page,30);
        return articleRepository.findAllByWriter(pageable,member);
    }

    public Page<Article> getPageList(int page) {
        Pageable pageable = PageRequest.of(page, 30);
        return articleRepository.findAll(pageable);
    }
    public Page<Article> getPublishedPageList(int page){
        Pageable pageable = PageRequest.of(page, 30);
        return articleRepository.findAllByIsPublished(pageable, true);
    }

    public void createArticle(ArticleForm articleForm){
        Article article = Article.builder()
                .title(articleForm.getTitle())
                .body(articleForm.getBody())
                .writer(articleForm.getWriter())
                .isPublished(articleForm.isPublish())
                .dateTime(LocalDateTime.now())
                .build();

        articleRepository.save(article);
    }

    public Article getArticleById(Integer id){
        Optional<Article> article = articleRepository.getArticleById(id);

        if(!article.isPresent()){
            throw new DataNotFoundException("해당하는 게시글이 없습니다.");
        }
        return article.get();
    }

    public void modify(Article article, ArticleForm articleForm) {
        article = article.toBuilder()
                .title(articleForm.getTitle())
                .body(articleForm.getBody())
                .dateTime(LocalDateTime.now())
                .build();

        articleRepository.save(article);
    }

    public void deleteArticle(Article article) {
        articleRepository.delete(article);
    }

    public List<Article> getAllArticlesByWriter(Member writer) {
        // null 이면??
        return articleRepository.findAllByWriter(writer);
    }

    public void addHit(Article article) {
        articleRepository.save(article.toBuilder().hit(article.getHit()+1).build());
    }
    public void addLike(Article article, Member member){
        article.getLike().add(member);
        articleRepository.save(article);
    }
    public void subLike(Article article, Member member){
        article.getLike().remove(member);
        articleRepository.save(article);
    }

}
