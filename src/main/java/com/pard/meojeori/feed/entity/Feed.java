package com.pard.meojeori.feed.entity;


import com.pard.meojeori.feed.dto.FeedDto;
import com.pard.meojeori.user.entity.User;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.UuidGenerator;
import org.hibernate.type.SqlTypes;

import java.time.LocalDateTime;
import java.util.UUID;


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

    @Column(name = "title",  nullable = false)
    private String title;

    @Column(name = "like", nullable = false)
    private Long like;

    @Column(name = "price",nullable = false)
    private Long price;

    @Lob
    @Column(name = "contents", nullable = false, columnDefinition = "TEXT")
    private String contents;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    public static Feed toEntity(FeedDto.Create dto){
        return Feed.builder()
                .like(dto.getLike())
                .contents(dto.getContents())
                .price(dto.getPrice())
                .title(dto.getTitle())
                .build();
    }
}
