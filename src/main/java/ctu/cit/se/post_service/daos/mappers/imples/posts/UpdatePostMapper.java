package ctu.cit.se.post_service.daos.mappers.imples.posts;

import ctu.cit.se.post_service.daos.mappers.IMapper;
import ctu.cit.se.post_service.dtos.posts.UpdatePostDTO;
import ctu.cit.se.post_service.dtos.tags.UpdateTagDTO;
import ctu.cit.se.post_service.entities.Post;
import ctu.cit.se.post_service.entities.Tag;
import ctu.cit.se.post_service.exceptions.messages.CustomExceptionMessage;
import ctu.cit.se.post_service.repositories.IPostRepository;
import ctu.cit.se.post_service.repositories.ITagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class UpdatePostMapper implements IMapper<UpdatePostDTO, Post> {
    @Autowired
    private IPostRepository postRepository;
    @Autowired
    private ITagRepository tagRepository;
    @Override
    public Post convert(UpdatePostDTO source) {
        var post = postRepository.findById(UUID.fromString(source.getId())).orElseThrow(() -> new IllegalArgumentException(CustomExceptionMessage.POST_NOT_FOUND));
        var tagIds = source.getTagIds();
        Set<Tag> tags = new HashSet<>();
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
                .id(post.getId())
                .title(Objects.isNull(source.getTitle()) ? post.getTitle() : source.getTitle())
                .content(Objects.isNull(source.getContent()) ? post.getContent() : source.getContent())
                .code(post.getCode())
                .creatorId(post.getCreatorId())
                .createdAt(post.getCreatedAt())
                .tags(tags)
                .build();
    }
}
