package ctu.cit.se.post_service.mappers.imples.tags;

import ctu.cit.se.post_service.mappers.IMapper;
import ctu.cit.se.post_service.dtos.tags.UpdateTagDTO;
import ctu.cit.se.post_service.entities.Tag;
import ctu.cit.se.post_service.exceptions.messages.CustomExceptionMessage;
import ctu.cit.se.post_service.repositories.ITagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.UUID;

@Component
public class UpdateTagMapper implements IMapper<UpdateTagDTO, Tag> {
    @Autowired
    private ITagRepository tagRepository;

    @Override
    public Tag convert(UpdateTagDTO source) {
        var tag = tagRepository.findById(UUID.fromString(source.getId())).orElseThrow(() -> new IllegalArgumentException(CustomExceptionMessage.TAG_NOT_FOUND));
        return Tag.builder()
                .id(tag.getId())
                .title(Objects.isNull(source.getTitle()) ? tag.getTitle() : source.getTitle())
                .code(tag.getCode())
                .description(Objects.isNull(source.getDescription()) ? tag.getDescription() : source.getDescription())
                .build();
    }
}
