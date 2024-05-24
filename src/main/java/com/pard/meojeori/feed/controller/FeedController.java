package com.pard.meojeori.feed.controller;

import com.pard.meojeori.feed.dto.FeedDto;
import com.pard.meojeori.feed.service.FeedService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/feed")
public class FeedController {
    private final FeedService feedService;

    @GetMapping("/detail/{id}")
    public FeedDto.Read findById(@PathVariable("id") Long id) {
        return feedService.findById(id);
    }



}
