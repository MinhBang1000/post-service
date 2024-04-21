package ctu.cit.se.post_service.daos.imples;

import ctu.cit.se.post_service.daos.ifaces.IPostDAO;
import ctu.cit.se.post_service.daos.mappers.IMapper;
import ctu.cit.se.post_service.dtos.others.CommandResDTO;
import ctu.cit.se.post_service.dtos.posts.CreatePostDTO;
import ctu.cit.se.post_service.dtos.posts.RetrievePostDTO;
import ctu.cit.se.post_service.dtos.posts.UpdatePostDTO;
import ctu.cit.se.post_service.entities.Post;
import ctu.cit.se.post_service.exceptions.messages.CustomExceptionMessage;
import ctu.cit.se.post_service.repositories.IPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PostDAO implements IPostDAO {
    @Autowired
    private IPostRepository postRepository;
    @Autowired
    private IMapper<CreatePostDTO, Post> createMapper;
    @Autowired
    private IMapper<UpdatePostDTO, Post> updateMapper;
    @Autowired
    private IMapper<Post, RetrievePostDTO> retrieveMapper;

    @Override
    public List<RetrievePostDTO> list() {
        return postRepository.findAll().stream().map((post) -> {
            return retrieveMapper.convert(post);
        }).collect(Collectors.toList());
    }

    @Override
    public CommandResDTO create(CreatePostDTO createPostDTO) {
        var post = createMapper.convert(createPostDTO);
        var createdPost = postRepository.save(post);
        return CommandResDTO.builder()
                .id(createdPost.getId().toString())
                .build();
    }

    @Override
    public CommandResDTO update(UpdatePostDTO updatePostDTO) {
        var post = updateMapper.convert(updatePostDTO);
        var updatedPost = postRepository.save(post);
        return CommandResDTO.builder()
                .id(updatedPost.getId().toString())
                .build();
    }

    @Override
    public RetrievePostDTO retrieve(UUID postId) {
        var post = postRepository.findById(postId).orElseThrow(() -> new IllegalArgumentException(CustomExceptionMessage.POST_NOT_FOUND));
        return retrieveMapper.convert(post);
    }

    @Override
    public void delete(UUID postId) {
        var post = postRepository.findById(postId).orElseThrow(() -> new IllegalArgumentException(CustomExceptionMessage.POST_NOT_FOUND));
        postRepository.delete(post);
    }
}