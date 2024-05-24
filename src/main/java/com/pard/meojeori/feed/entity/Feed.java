package com.pard.meojeori.feed.entity;


import com.pard.meojeori.user.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.UuidGenerator;
import org.hibernate.type.SqlTypes;

import java.util.UUID;

@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Feed")
public class Feed {
    @Id
    @GeneratedValue
    @UuidGenerator
    @JdbcTypeCode(SqlTypes.BINARY)
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @ManyToOne
    @Column(name = "writer_id", nullable = false)
    private User writer;

    @Column(name = "title",  nullable = false)
    private String title;

    @Column(name = "title", nullable = false)
    private Long like;

    @Column(name = "title",nullable = false)
    private Long price;

    @Column(name = "title", nullable = false)
    private String contents;

}
