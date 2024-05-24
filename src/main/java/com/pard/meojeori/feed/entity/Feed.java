package com.pard.meojeori.feed.entity;


import com.pard.meojeori.user.entity.User;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.UuidGenerator;
import org.hibernate.type.SqlTypes;

import java.util.UUID;

@Getter
@Entity
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

    @Column(name = "upvote", nullable = false)
    private Long upvote;

    @Column(name = "price",nullable = false)
    private Long price;

    @Lob
    @Column(name = "contents", nullable = false, columnDefinition = "TEXT")
    private String contents;

}
