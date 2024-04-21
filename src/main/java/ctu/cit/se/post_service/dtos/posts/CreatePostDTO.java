package ctu.cit.se.post_service.dtos.posts;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreatePostDTO {
    @JsonProperty("postTitle")
    private String title;
    @JsonProperty("postContent")
    private String content;
    @JsonProperty("postCreatorId")
    private String creatorId;
    @JsonProperty("postCreatedAt")
    private LocalDateTime createdAt;
    @JsonProperty("postTagIds")
    private List<String> tagIds;
}
