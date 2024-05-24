package com.pard.meojeori.feed.controller;

import com.pard.meojeori.feed.dto.FeedDto;
import com.pard.meojeori.feed.service.FeedService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/feed")
public class FeedController {
    private final FeedService feedService;

    @PostMapping("/make")
    @Operation(summary = "게시물 입력", description = "글 작성 기능 (카테고리 선정, 제목, 가격, 세부내용)")
    public String createFeed(@RequestBody FeedDto.CreateFeed  dto, @RequestParam UUID userId){
        feedService.createFeed(dto, userId);
        return "게시물 추가 되었습니다.";
    }
    @GetMapping("/detail/{id}")
    public FeedDto.Read findById(@PathVariable("id") Long id) {
        return feedService.findById(id);
    }

    @PostMapping("/{feedId}/upvote")
    public void toggleUpvote(@PathVariable Long feedId, @RequestParam UUID userId) {
        feedService.toggleUpvote(feedId, userId);
    }
}
