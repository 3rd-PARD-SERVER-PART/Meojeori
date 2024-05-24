package com.pard.meojeori.feed.repo;

import com.pard.meojeori.feed.entity.Feed;
import com.pard.meojeori.feed.entity.UpvoteHistory;
import com.pard.meojeori.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UpvoteHistoryRepo extends JpaRepository<UpvoteHistory,Long> {
    Optional<UpvoteHistory> findByFeedAndUser(Feed feed, User user);
}
