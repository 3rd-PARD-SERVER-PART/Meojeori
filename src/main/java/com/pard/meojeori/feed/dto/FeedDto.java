package com.pard.meojeori.feed.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.pard.meojeori.feed.entity.Feed;
import com.pard.meojeori.user.entity.User;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;


public class FeedDto {
    @Getter
    @Setter
    public static class CreateNewFeed{
        private UUID userId;
        private String title;
        private Long price;
        private String contents;
    }

    @Getter
    @Setter
    public static class CreateFeed{
        private User writer;
        private String title;
        private Long upvote;
        private Long price;
        private String contents;
    }

    @Getter
    @Setter
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Read{
        private Long id;
        private User writer;
        private String title;
        private Long upvote;
        private Long price;
        private String contents;
        private LocalDateTime createdAt;

        public Read(Feed feed){
            this.id = feed.getId();
            this.writer = feed.getWriter();
            this.title = feed.getTitle();
            this.upvote = feed.getUpvote();
            this.price = feed.getPrice();
            this.contents = feed.getContents();
            this.createdAt = feed.getCreatedAt();
        }
    }
}
