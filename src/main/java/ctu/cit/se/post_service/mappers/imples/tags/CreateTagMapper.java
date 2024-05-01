package ctu.cit.se.post_service.mappers.imples.tags;

import ctu.cit.se.post_service.mappers.IMapper;
import ctu.cit.se.post_service.dtos.tags.CreateTagDTO;
import ctu.cit.se.post_service.entities.Tag;
import org.springframework.stereotype.Component;

@Component
public class CreateTagMapper implements IMapper<CreateTagDTO, Tag> {
    @Override
    public Tag convert(CreateTagDTO source) {
        return Tag.builder()
                .title(source.getTitle())
                .description(source.getDescription())
                .build();
    }
}
