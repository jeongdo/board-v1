package me.jeongdo.board.service;

import kuke.board.common.snowflake.Snowflake;
import lombok.RequiredArgsConstructor;
import me.jeongdo.board.entity.Article;
import me.jeongdo.board.repository.ArticleRepository;
import me.jeongdo.board.service.request.ArticleCreateRequest;
import me.jeongdo.board.service.request.ArticleUpdateRequest;
import me.jeongdo.board.service.response.ArticleResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ArticleService {

    private final Snowflake snowflake = new Snowflake();
    private final ArticleRepository articleRepository;

    @Transactional
    public ArticleResponse create(ArticleCreateRequest request) {
        Article article = articleRepository.save(
            Article.create(snowflake.nextId()
                    , request.getTitle()
                    , request.getContent()
                    , request.getBoardId()
                    , request.getWriterId()
            )
        );
        return ArticleResponse.from(article);
    }

    @Transactional
    public ArticleResponse update(Long articleId, ArticleUpdateRequest request) {
        Article article = articleRepository.findById(articleId).orElseThrow();
        article.update(request.getTitle(), request.getContent());
        return ArticleResponse.from(article);
    }

    public ArticleResponse read(Long articleId) {
        Article article = articleRepository.findById(articleId).orElseThrow();
        return ArticleResponse.from(article);
    }

    @Transactional
    public void delete(Long articleId) {
        articleRepository.deleteById(articleId);
    }

}
