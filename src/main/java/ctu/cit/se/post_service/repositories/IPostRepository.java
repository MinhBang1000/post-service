package ctu.cit.se.post_service.repositories;

import ctu.cit.se.post_service.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface IPostRepository extends JpaRepository<Post, UUID> {
    List<Post> findByCode(String code);
}
