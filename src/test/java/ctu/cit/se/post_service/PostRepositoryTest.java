package ctu.cit.se.post_service;

import ctu.cit.se.post_service.entities.Post;
import ctu.cit.se.post_service.entities.Tag;
import ctu.cit.se.post_service.repositories.IPostRepository;
import ctu.cit.se.post_service.repositories.ITagRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDateTime;
import java.util.HashSet;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class PostRepositoryTest {
    @Autowired
    private IPostRepository postRepository;
    @Autowired
    private ITagRepository tagRepository;
    @Autowired
    private TestEntityManager em;
    private Post post1;
    private Post post2;
    private Tag tag1;
    private Tag tag2;
    @BeforeEach
    public void setUp() throws Exception {
        post1 = Post.builder()
                .code("")
                .content("This is the first post")
                .createdAt(LocalDateTime.now())
                .creator("Le Minh Bang")
                .build();
        post2 = Post.builder()
                .code("")
                .content("This is the second post")
                .createdAt(LocalDateTime.now())
                .creator("Le Minh Bang")
                .build();
        tag1 = Tag.builder()
                .code("")
                .description("This is the first tag")
                .title("Technology")
                .build();
        tag2 = Tag.builder()
                .code("")
                .description("This is the second tag")
                .title("Technology")
                .build();
        em.persist(post1);
        em.persist(post2);
        em.persist(tag1);
        em.persist(tag2);
    }
    @Test
    public void shouldSave_bothPostAndTag_linkedTogether() {
        post1.addTag(tag1);
        tag1.addPost(post1);
        em.persist(post1);
        var savedPost = postRepository.findById(post1.getId()).orElse(null);
        assertNotNull(savedPost);
        assertTrue(savedPost.getTags().contains(tag1));
        var savedTag = tagRepository.findById(tag1.getId()).orElse(null);
        assertNotNull(savedTag);
        assertTrue(savedTag.getPosts().contains(post1));
    }
    @Test
    public void shouldDelete_allTags_beforeDeletePost() {
        post1.addTag(tag1);
        tag1.addPost(post1);
        post1.addTag(tag2);
        tag2.addPost(post1);
        em.persist(post1);
        var savedPost1 = postRepository.findById(post1.getId()).orElse(null);
        assertNotNull(savedPost1);
        assertTrue(savedPost1.getTags().contains(tag1) && savedPost1.getTags().contains(tag2));
        var tags = new HashSet<>(savedPost1.getTags());
        for (Tag tag : tags) {
            savedPost1.removeTag(tag);
            tag.removePost(savedPost1);
        }
        em.persist(post1);
        var savedPost2 = postRepository.findById(post1.getId()).orElse(null);
        assertNotNull(savedPost2);
        assertTrue(savedPost2.getTags().isEmpty());
    }
}
