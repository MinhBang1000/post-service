package ctu.cit.se.post_service.dtos.tags;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RetrieveTagDTO {
    @JsonProperty("tagId")
    private String id;
    @JsonProperty("tagCode")
    private String code;
    @JsonProperty("tagDescription")
    private String description;
    @JsonProperty("tagTitle")
    private String title;
}
