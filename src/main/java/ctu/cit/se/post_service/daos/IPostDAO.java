package ctu.cit.se.post_service.daos;

import ctu.cit.se.post_service.dtos.others.CommandResDTO;
import ctu.cit.se.post_service.dtos.posts.CreatePostDTO;
import ctu.cit.se.post_service.dtos.posts.RetrievePostDTO;
import ctu.cit.se.post_service.dtos.posts.UpdatePostDTO;
import ctu.cit.se.post_service.dtos.tags.RetrieveTagDTO;

import java.util.List;
import java.util.UUID;

public interface IPostDAO {
    List<RetrieveTagDTO> list();
    CommandResDTO create(CreatePostDTO createPostDTO);
    CommandResDTO update(UpdatePostDTO updatePostDTO);
    RetrievePostDTO retrieve(UUID postId);
    void delete(UUID postId);
}
