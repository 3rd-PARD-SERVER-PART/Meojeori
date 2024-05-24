package com.pard.meojeori.feed.repo;

import com.pard.meojeori.feed.entity.Feed;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface FeedRepo extends JpaRepository<Feed, Long> {

}
