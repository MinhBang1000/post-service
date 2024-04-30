package ctu.cit.se.post_service.repositories;

import ctu.cit.se.post_service.entities.Post;
import ctu.cit.se.post_service.entities.PostTag;
import ctu.cit.se.post_service.entities.Tag;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;
import java.util.Objects;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class PostTagRepositoryTest {
    @Autowired
    private IPostTagRepository postTagRepository;
    @Autowired
    private IPostRepository postRepository;
    @Autowired
    private ITagRepository tagRepository;

//    @Test
//    public void shouldCreate_postTag() {
//        var tag = tagRepository.save(Tag.builder().code("").description("").title("Tag 1").build());
//        var post = postRepository.save(Post.builder().title("Post 1").content("Post 1").createdAt(LocalDateTime.now()).creator("Le Minh Bang").build());
//        var postTag = postTagRepository.save(PostTag.builder().tag(tag).post(post).build());
//        assertTrue(Objects.nonNull(postTag.getId()));
//    }
}
