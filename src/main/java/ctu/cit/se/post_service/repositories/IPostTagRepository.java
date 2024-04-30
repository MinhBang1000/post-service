package ctu.cit.se.post_service.repositories;

import ctu.cit.se.post_service.entities.Post;
import ctu.cit.se.post_service.entities.PostTag;
import ctu.cit.se.post_service.entities.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IPostTagRepository extends JpaRepository<PostTag, UUID> {
    void deleteAllByPost(Post post);
    void deleteAllByTag(Tag tag);
}
