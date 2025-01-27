package me.jeongdo.board.controller;

import lombok.RequiredArgsConstructor;
import me.jeongdo.board.service.ArticleService;
import me.jeongdo.board.service.request.ArticleCreateRequest;
import me.jeongdo.board.service.request.ArticleUpdateRequest;
import me.jeongdo.board.service.response.ArticleResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleService articleService;

    @GetMapping("/v1/articles/{articleId}")
    public ArticleResponse read(@PathVariable Long articleId) {
        return articleService.read(articleId);
    }

    @PostMapping("/v1/articles")
    public ArticleResponse create(@RequestBody ArticleCreateRequest request) {
        System.out.println("request = " + request);
        ArticleResponse articleResponse = articleService.create(request);
        System.out.println("articleResponse = " + articleResponse);
        return articleResponse;
    }

    @PutMapping("/v1/articles/{articleId}")
    public ArticleResponse update(@PathVariable Long articleId, @RequestBody ArticleUpdateRequest request) {
        return articleService.update(articleId, request);
    }

    @DeleteMapping("/v1/articles/{articleId}")
    public void delete(@PathVariable Long articleId) {
        articleService.delete(articleId);
    }

}
