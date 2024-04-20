package ctu.cit.se.post_service.dtos.tags;

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
public class CreateTagDTO {
    @JsonProperty("tagTitle")
    private String title;
    @JsonProperty("tagDescription")
    private String description;
    @JsonProperty("tagCode")
    private String code;
}
