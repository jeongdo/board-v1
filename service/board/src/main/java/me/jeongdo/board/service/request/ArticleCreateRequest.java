package me.jeongdo.board.service.request;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ArticleCreateRequest {
    private String title;
    private String content;
    private Long boardId;
    private Long writerId;
}
