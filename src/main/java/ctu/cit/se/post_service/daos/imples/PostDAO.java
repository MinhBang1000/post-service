package ctu.cit.se.post_service.daos.imples;

import ctu.cit.se.post_service.daos.IPostDAO;
import ctu.cit.se.post_service.dtos.others.CommandResDTO;
import ctu.cit.se.post_service.dtos.posts.CreatePostDTO;
import ctu.cit.se.post_service.dtos.posts.RetrievePostDTO;
import ctu.cit.se.post_service.dtos.posts.UpdatePostDTO;
import ctu.cit.se.post_service.dtos.tags.RetrieveTagDTO;
import ctu.cit.se.post_service.repositories.IPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
@Service
public class PostDAO implements IPostDAO {
    @Autowired
    private IPostRepository postRepository;

    @Override
    public List<RetrieveTagDTO> list() {
        return List.of();
    }

    @Override
    public CommandResDTO create(CreatePostDTO createPostDTO) {
        return null;
    }

    @Override
    public CommandResDTO update(UpdatePostDTO updatePostDTO) {
        return null;
    }

    @Override
    public RetrievePostDTO retrieve(UUID postId) {
        return null;
    }

    @Override
    public void delete(UUID postId) {

    }
}
