package me.jeongdo.board.api;

import lombok.AllArgsConstructor;
import lombok.Getter;
import me.jeongdo.board.service.response.ArticleResponse;
import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestClient;

public class ArticleApiTest {
    RestClient restClient = RestClient.create("http://localhost:9090");

    @Test
    void createTest() {
        ArticleResponse articleResponse = create(new ArticleCreateRequest(
                "hi", "my content", 1L, 1L
        ));

        System.out.println("articleResponse = " + articleResponse);
    }

    ArticleResponse create(ArticleCreateRequest request) {
        return restClient.post().uri("/v1/articles")
                .body(request)
                .retrieve()
                .body(ArticleResponse.class);
    }

    @Test
    void readTest() {
        ArticleResponse response = read(142072923464052736L);
        System.out.println("response = " + response);
    }

    ArticleResponse read(Long articleId) {
        return restClient.get().uri("/v1/articles/{articleId}", articleId)
                .retrieve()
                .body(ArticleResponse.class);
    }

    @Test
    void updateTest() {
        update(142072923464052736L);
        ArticleResponse response = read(142072923464052736L);
        System.out.println("response = " + response);
    }

    void update(Long articleId) {
        restClient.put().uri("/v1/articles/{articleId}", articleId)
                .body(new ArticleUpdateRequest("hi 222", "my content 222"))
                .retrieve();
    }


    @Test
    void deleteTest() {
        restClient.delete().uri("/v1/articles/{articleId}", 142072923464052736L)
                .retrieve();
    }


    @Getter
    @AllArgsConstructor
    static class ArticleCreateRequest {
        private String title;
        private String content;
        private Long boardId;
        private Long writerId;
    }

    @Getter
    @AllArgsConstructor
    static class ArticleUpdateRequest {
        private String title;
        private String content;
    }
}
