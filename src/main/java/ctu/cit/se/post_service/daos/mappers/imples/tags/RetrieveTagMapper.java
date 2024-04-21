package ctu.cit.se.post_service.daos.mappers.imples.tags;

import ctu.cit.se.post_service.daos.mappers.IMapper;
import ctu.cit.se.post_service.dtos.tags.CreateTagDTO;
import ctu.cit.se.post_service.dtos.tags.RetrieveTagDTO;
import ctu.cit.se.post_service.entities.Tag;
import org.springframework.stereotype.Component;

@Component
public class RetrieveTagMapper implements IMapper<Tag, RetrieveTagDTO> {
    @Override
    public RetrieveTagDTO convert(Tag source) {
        return RetrieveTagDTO.builder()
                .id(source.getId().toString())
                .title(source.getTitle())
                .code(source.getCode())
                .description(source.getDescription())
                .build();
    }
}
