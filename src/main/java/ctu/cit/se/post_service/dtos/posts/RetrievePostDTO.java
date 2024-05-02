package ctu.cit.se.post_service.dtos.posts;

import com.fasterxml.jackson.annotation.JsonProperty;
import ctu.cit.se.post_service.dtos.tags.RetrieveTagDTO;
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
public class RetrievePostDTO {
    @JsonProperty("postId")
    private String id;
    @JsonProperty("postCode")
    private String code;
    @JsonProperty("postTitle")
    private String title;
    @JsonProperty("postContent")
    private String content;
    @JsonProperty("postAvatar")
    private String avatar;
    @JsonProperty("postCreatedAt")
    private LocalDateTime createAt;
    @JsonProperty("postCreatorName")
    private String creator;
    @JsonProperty("postTags")
    private List<RetrieveTagDTO> tags;
}
