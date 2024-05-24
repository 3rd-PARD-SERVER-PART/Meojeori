package com.pard.meojeori.feed.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

public class UpvoteHistoryDto {
    @Schema(example = "{\"userId\": {UUID}, \"feedId\": {long}")
    @Getter
    @Setter
    public static class CreateUpvoteHistory {
        private UUID userId;
        private long feedId;
    }

    @Getter
    @Setter
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Read {
        private long id;
        private UUID userId;
        private Long feedId;
    }
}
