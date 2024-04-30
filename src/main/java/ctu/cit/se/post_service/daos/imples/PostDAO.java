package ctu.cit.se.post_service.daos.imples;

import ctu.cit.se.post_service.daos.ifaces.IPostDAO;
import ctu.cit.se.post_service.dtos.tags.RetrieveTagDTO;
import ctu.cit.se.post_service.entities.PostTag;
import ctu.cit.se.post_service.mappers.IMapper;
import ctu.cit.se.post_service.dtos.others.CommandResDTO;
import ctu.cit.se.post_service.dtos.posts.CreatePostDTO;
import ctu.cit.se.post_service.dtos.posts.RetrievePostDTO;
import ctu.cit.se.post_service.dtos.posts.UpdatePostDTO;
import ctu.cit.se.post_service.entities.Post;
import ctu.cit.se.post_service.exceptions.messages.CustomExceptionMessage;
import ctu.cit.se.post_service.mappers.imples.tags.RetrieveTagMapper;
import ctu.cit.se.post_service.repositories.IPostRepository;
import ctu.cit.se.post_service.repositories.IPostTagRepository;
import ctu.cit.se.post_service.repositories.ITagRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PostDAO implements IPostDAO {
    @Autowired
    private IPostRepository postRepository;
    @Autowired
    private ITagRepository tagRepository;
    @Autowired
    private IPostTagRepository postTagRepository;
    @Autowired
    private IMapper<CreatePostDTO, Post> createMapper;
    @Autowired
    private IMapper<UpdatePostDTO, Post> updateMapper;
    @Autowired
    private IMapper<Post, RetrievePostDTO> retrieveMapper;
    @Autowired
    private RetrieveTagMapper retrieveTagMapper;

    private List<RetrieveTagDTO> getAllTags(Post post) {
        return post.getPostTags().stream().map(postTag -> retrieveTagMapper.convert(postTag.getTag())).toList();
    }

    @Override
    public List<RetrievePostDTO> list() {
        return postRepository.findAll().stream().map((post) -> {
            var retrievePostDTO = retrieveMapper.convert(post);
            retrievePostDTO.setTags(getAllTags(post));
            return retrievePostDTO;
        }).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public CommandResDTO create(CreatePostDTO createPostDTO) {
        var post = createMapper.convert(createPostDTO);
        var createdPost = postRepository.save(post);
        for (String tagId : createPostDTO.getTagIds()) {
            var tag = tagRepository.findById(UUID.fromString(tagId)).orElse(null);
            if (Objects.nonNull(tag)) {
                var postTag = PostTag.builder().post(createdPost).tag(tag).build();
                postTagRepository.save(postTag);
            }
        }
        return CommandResDTO.builder()
                .id(createdPost.getId().toString())
                .build();
    }

    @Override
    @Transactional
    public CommandResDTO update(UpdatePostDTO updatePostDTO) {
        var post = updateMapper.convert(updatePostDTO);
        var updatedPost = postRepository.save(post);
        postTagRepository.deleteAllByPost(updatedPost);
        postTagRepository.flush();
        for (String tagId : updatePostDTO.getTagIds()) {
            var tag = tagRepository.findById(UUID.fromString(tagId)).orElse(null);
            if (Objects.nonNull(tag)) {
                var postTag = PostTag.builder().post(updatedPost).tag(tag).build();
                postTagRepository.save(postTag);
            }
        }
        return CommandResDTO.builder()
                .id(updatedPost.getId().toString())
                .build();
    }

    @Override
    public RetrievePostDTO retrieve(UUID postId) {
        var post = postRepository.findById(postId).orElseThrow(() -> new IllegalArgumentException(CustomExceptionMessage.POST_NOT_FOUND));
        var retrievePostDTO = retrieveMapper.convert(post);
        retrievePostDTO.setTags(getAllTags(post));
        return retrievePostDTO;
    }

    @Override
    public void delete(UUID postId) {
        var post = postRepository.findById(postId).orElseThrow(() -> new IllegalArgumentException(CustomExceptionMessage.POST_NOT_FOUND));
        postRepository.deleteById(postId);
    }
}
