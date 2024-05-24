package com.pard.meojeori.feed.entity;


import com.pard.meojeori.feed.dto.FeedDto;
import com.pard.meojeori.user.entity.User;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;


@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "Feeds")
public class Feed {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "writer_id", nullable = false)
    private User writer;

    @Setter
    @Column(name = "title",  nullable = false)
    private String title;

    @Column(name = "upvote", nullable = false)
    private Long upvote;

    @Column(name = "price",nullable = false)
    private Long price;

    @Lob
    @Setter
    @Column(name = "contents", nullable = false, columnDefinition = "TEXT")
    private String contents;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;


    @Column(name = "update_at")
    private LocalDateTime updateAt;

    @PrePersist
    @PreUpdate
    public void prePersistAndUpdate() {
        this.updateAt = LocalDateTime.now();
    }

    public void incrementUpvote() {
        this.upvote++;
    }

    public void decrementUpvote() {
        this.upvote--;
    }

    public static Feed toEntity(FeedDto.CreateFeed dto, User writer){
        return Feed.builder()
                .upvote(dto.getUpvote())
                .writer(writer)
                .contents(dto.getContents())
                .price(dto.getPrice())
                .title(dto.getTitle())
                .build();
    }
}
