package ctu.cit.se.post_service.daos.mappers.imples.posts;

import ctu.cit.se.post_service.daos.mappers.IMapper;
import ctu.cit.se.post_service.dtos.posts.CreatePostDTO;
import ctu.cit.se.post_service.dtos.tags.CreateTagDTO;
import ctu.cit.se.post_service.entities.Post;
import ctu.cit.se.post_service.entities.Tag;
import ctu.cit.se.post_service.exceptions.messages.CustomExceptionMessage;
import ctu.cit.se.post_service.repositories.ITagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Component
public class CreatePostMapper implements IMapper<CreatePostDTO, Post> {
    @Autowired
    private ITagRepository tagRepository;
    @Override
    public Post convert(CreatePostDTO source) {
        var tagIds = source.getTagIds();
        Set<Tag> tags  = new HashSet<>();
        if (tagIds != null && !tagIds.isEmpty()) {
            tagIds.forEach((tagId) -> {
                try {
                    var tag = tagRepository.findById(UUID.fromString(tagId)).get();
                    tags.add(tag);
                }catch (Exception e) {
                    throw new IllegalArgumentException(CustomExceptionMessage.TAG_NOT_FOUND);
                }
            });
        }
        return Post.builder()
                .title(source.getTitle())
                .createdAt(source.getCreatedAt())
                .creatorId(UUID.fromString(source.getCreatorId()))
                .content(source.getContent())
                .tags(tags)
                .build();
    }
}