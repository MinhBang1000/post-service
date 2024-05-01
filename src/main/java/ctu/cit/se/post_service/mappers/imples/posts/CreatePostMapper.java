package ctu.cit.se.post_service.mappers.imples.posts;

import ctu.cit.se.post_service.mappers.IMapper;
import ctu.cit.se.post_service.dtos.posts.CreatePostDTO;
import ctu.cit.se.post_service.entities.Post;
import ctu.cit.se.post_service.entities.Tag;
import ctu.cit.se.post_service.exceptions.messages.CustomExceptionMessage;
import ctu.cit.se.post_service.repositories.ITagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Component
public class CreatePostMapper implements IMapper<CreatePostDTO, Post> {
    @Autowired
    private ITagRepository tagRepository;
    @Override
    public Post convert(CreatePostDTO source) {
        return Post.builder()
                .title(source.getTitle())
                .createdAt(source.getCreatedAt())
                .creator(source.getCreator())
                .content(source.getContent())
                .build();
    }
}
