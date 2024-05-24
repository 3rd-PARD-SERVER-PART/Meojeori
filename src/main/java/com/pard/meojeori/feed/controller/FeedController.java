package com.pard.meojeori.feed.controller;

import com.pard.meojeori.feed.dto.FeedDto;
import com.pard.meojeori.feed.service.FeedService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/feed")
public class FeedController {
    private final FeedService feedService;

    @GetMapping("/detail/{id}")
    public FeedDto.Read findById(@PathVariable("id") Long id) {
        return feedService.findById(id);
    }

    @PostMapping("/{feedId}/upvote")
    public void toggleUpvote(@PathVariable Long feedId, @RequestParam UUID userId) {
        feedService.toggleUpvote(feedId, userId);
    }
}
