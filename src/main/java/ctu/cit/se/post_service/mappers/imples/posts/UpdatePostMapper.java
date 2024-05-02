package ctu.cit.se.post_service.mappers.imples.posts;

import ctu.cit.se.post_service.mappers.IMapper;
import ctu.cit.se.post_service.dtos.posts.UpdatePostDTO;
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

@Component
public class UpdatePostMapper implements IMapper<UpdatePostDTO, Post> {
    @Autowired
    private IPostRepository postRepository;
    @Autowired
    private ITagRepository tagRepository;
    @Override
    public Post convert(UpdatePostDTO source) {
        var post = postRepository.findById(UUID.fromString(source.getId())).orElseThrow(() -> new IllegalArgumentException(CustomExceptionMessage.POST_NOT_FOUND));
        return Post.builder()
                .id(post.getId())
                .title(Objects.isNull(source.getTitle()) ? post.getTitle() : source.getTitle())
                .content(Objects.isNull(source.getContent()) ? post.getContent() : source.getContent())
                .code(post.getCode())
                .avatar(Objects.isNull(source.getAvatar()) ? post.getAvatar() : source.getAvatar())
                .creator(Objects.isNull(source.getCreator()) ? post.getCreator() : source.getCreator())
                .createdAt(post.getCreatedAt())
                .build();
    }
}
