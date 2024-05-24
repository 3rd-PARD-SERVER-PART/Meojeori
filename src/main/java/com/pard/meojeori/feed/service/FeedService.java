package com.pard.meojeori.feed.service;

import com.pard.meojeori.feed.dto.FeedDto;
import com.pard.meojeori.feed.entity.Feed;
import com.pard.meojeori.feed.repo.FeedRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FeedService {
    private final FeedRepo feedRepo;

    public void createFeed(FeedDto.Create dto){ feedRepo.save(Feed.toEntity(dto)); }
}
