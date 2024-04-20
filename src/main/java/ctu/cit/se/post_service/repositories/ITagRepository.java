package ctu.cit.se.post_service.repositories;

import ctu.cit.se.post_service.entities.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ITagRepository extends JpaRepository<Tag, UUID> {
    List<Tag> findByCode(String code);
}
