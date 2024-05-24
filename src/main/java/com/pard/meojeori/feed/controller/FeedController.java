package com.pard.meojeori.feed.controller;

import com.pard.meojeori.feed.dto.FeedDto;
import com.pard.meojeori.feed.entity.Feed;
import com.pard.meojeori.feed.service.FeedService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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
    @Operation(summary = "게시물 찾기", description = "글 세부 내용 확인하는 기능")
    public FeedDto.Read findById(@PathVariable("id") Long id) {
        return feedService.findById(id);
    }

    @PostMapping("/upvote")
    @Operation(summary = "좋아요 누르기 기능", description = "글이 마음에 든다면 좋아요를 눌러주세요")
    public void toggleUpvote(@RequestParam Long feedId, @RequestParam UUID userId) {
        feedService.toggleUpvote(feedId, userId);
    }

    @GetMapping("/rank/upvote")
    @Operation(summary = "낭만지수 높은 순 (upvote 내림차순)", description = "낭만지수에 따라서 높은 순으로 랭킹을 나타냄.")
    public List<FeedDto.Read> rankUpvote(){
        return feedService.rankUpvote();
    }

    @GetMapping("/rank/price")
    @Operation(summary = "가격 낮은 순 (price 오름차순)", description = "가격에 따라서 앚은 순으로 랭킹을 나타냄.")
    public List<FeedDto.Read> rankPrice(){
        return feedService.rankPrice();
    }

    @GetMapping("/rank/latest")
    @Operation(summary = "최신 순 (id 내림차순)", description = "가장 먼저 만든 순서에 따라서 게시물을 보여줌")
    public List<FeedDto.Read> rankLatest(){
        return feedService.rankLatest();
    }

    @DeleteMapping("/delete")
    @Operation(summary = "해당 게시물 삭제", description = "해당 유저가 작성한 게시물을 삭제하는 것을 보야줌")
    public String deleteFeed(@RequestParam Long id){
        feedService.deleteFeed(id);
        return "삭제하셨습니다.";
    }
}
