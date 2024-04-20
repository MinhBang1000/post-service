package ctu.cit.se.post_service.daos.mappers.imples.posts;

import ctu.cit.se.post_service.daos.mappers.IMapper;
import ctu.cit.se.post_service.dtos.posts.RetrievePostDTO;
import ctu.cit.se.post_service.dtos.tags.RetrieveTagDTO;
import ctu.cit.se.post_service.entities.Post;
import ctu.cit.se.post_service.entities.Tag;
import ctu.cit.se.post_service.repositories.IPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class RetrievePostMapper implements IMapper<Post, RetrievePostDTO> {
    @Autowired
    private IPostRepository postRepository;
    @Autowired
    private IMapper<Tag, RetrieveTagDTO> tagMapper;
    @Override
    public RetrievePostDTO convert(Post source) {
        var tags = source.getTags();
        var retrieveTagDTOS = tags.stream().map(tag -> tagMapper.convert(tag)).collect(Collectors.toList());
        return RetrievePostDTO.builder()
                .id(source.getId().toString())
                .title(source.getTitle())
                .code(source.getCode())
                .createAt(source.getCreatedAt())
                .creatorId(source.getCreatorId().toString())
                .tags(retrieveTagDTOS)
                .build();
    }
}
