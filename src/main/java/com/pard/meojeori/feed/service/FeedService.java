package com.pard.meojeori.feed.service;

import com.pard.meojeori.feed.dto.FeedDto;
import com.pard.meojeori.feed.entity.Feed;
import com.pard.meojeori.feed.entity.UpvoteHistory;
import com.pard.meojeori.feed.repo.FeedRepo;

import com.pard.meojeori.user.repo.UserRepo;
import com.pard.meojeori.feed.repo.UpvoteHistoryRepo;
import com.pard.meojeori.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FeedService {
    private final FeedRepo feedRepo;
    private final UserRepo userRepo;

    public void createFeed(FeedDto.CreateFeed dto, UUID userId){ feedRepo.save(Feed.toEntity(
            dto, userRepo.findById(userId).orElseThrow())); }
    private final UpvoteHistoryRepo upvoteHistoryRepo;


    public FeedDto.Read findById(Long id){
        return new FeedDto.Read(feedRepo.findById(id).orElseThrow());
    }

    @Transactional
    public void toggleUpvote(Long feedId, UUID userId){
        Feed feed = feedRepo.findById(feedId)
                .orElseThrow(() -> new RuntimeException("Feed not found"));
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Optional<UpvoteHistory> upvoteHistoryOptional = upvoteHistoryRepo.findByFeedAndUser(feed, user);

        if (upvoteHistoryOptional.isPresent()) {
            UpvoteHistory upvoteHistory = upvoteHistoryOptional.get();
            upvoteHistoryRepo.delete(upvoteHistory);
            feed.decrementUpvote();
        } else {
            UpvoteHistory upvoteHistory = UpvoteHistory.builder()
                    .feed(feed)
                    .user(user)
                    .build();
            upvoteHistoryRepo.save(upvoteHistory);
            feed.incrementUpvote();
        }

        feedRepo.save(feed);
    }

    public List<FeedDto.Read> rankUpvote(){
        return feedRepo.findAllByOrderByUpvoteDesc().stream()
                .map(FeedDto.Read::new)
                .toList();
    }

    public List<FeedDto.Read> rankPrice(){
        return feedRepo.findAllByOrderByPriceAsc().stream()
                .map(FeedDto.Read::new)
                .toList();
    }

    public List<FeedDto.Read> rankLatest(){
        return feedRepo.findAllByOrderByIdDesc().stream()
                .map(FeedDto.Read::new)
                .toList();
    }

    @Transactional
    public void deleteFeed(Long feedId, UUID userId){
        if(compareUserIdAndUserIdInFeedId(feedId, userId)) {
            upvoteHistoryRepo.deleteByFeedId(feedId);
            feedRepo.deleteById(feedId);
        }
    }

    @Transactional
    public void updateFeed(Long feedId, UUID userId, FeedDto.CreateFeed dto){
        if(compareUserIdAndUserIdInFeedId(feedId, userId)){
            Feed feed = feedRepo.findById(feedId).orElseThrow();
            feed.setContents(dto.getContents());
            feed.setTitle(dto.getTitle());
            feed.prePersistAndUpdate();
        }
    }

    private boolean compareUserIdAndUserIdInFeedId(Long feedId, UUID userId){
        Optional<Feed> optionalFeed = feedRepo.findById(feedId);
        if (optionalFeed.isPresent()) {
            Feed feed = optionalFeed.get();
            return feed.getWriter().getId().equals(userId);
        } else return false;
    }
}
