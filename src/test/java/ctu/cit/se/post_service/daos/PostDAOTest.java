package ctu.cit.se.post_service.daos;

import ctu.cit.se.post_service.daos.imples.PostDAO;
import ctu.cit.se.post_service.dtos.others.CommandResDTO;
import ctu.cit.se.post_service.dtos.posts.CreatePostDTO;
import ctu.cit.se.post_service.dtos.posts.RetrievePostDTO;
import ctu.cit.se.post_service.dtos.posts.UpdatePostDTO;
import ctu.cit.se.post_service.entities.Post;
import ctu.cit.se.post_service.entities.Tag;
import ctu.cit.se.post_service.repositories.IPostRepository;
import static org.junit.jupiter.api.Assertions.*;

import ctu.cit.se.post_service.repositories.ITagRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@SpringBootTest
public class PostDAOTest {
    @Autowired
    private PostDAO postDAO;

    @Autowired
    private IPostRepository postRepository;
    @Autowired
    private ITagRepository tagRepository;
    private CreatePostDTO createPostDTO;

    private Set<Tag> createTagExamples() {
        Tag tag1 = Tag.builder()
                .title("Tag 1")
                .description("Tag Description 1")
                .code("Tag 1")
                .posts(new HashSet<>())
                .build();
        Tag tag2 = Tag.builder()
                .title("Tag 2")
                .description("Tag Description 2")
                .code("Tag 2")
                .posts(new HashSet<>())
                .build();
        Tag tag3 = Tag.builder()
                .title("Tag 3")
                .description("Tag Description 3")
                .code("Tag 3")
                .posts(new HashSet<>())
                .build();
        tagRepository.save(tag1);
        tagRepository.save(tag2);
        tagRepository.save(tag3);
        return Set.of(tag1, tag2, tag3);
    }

    private CreatePostDTO createCRUDPostDTO() {
        return CreatePostDTO.builder()
                .title("Post 1")
                .content("Post 1")
                .creator("Le Minh Bang")
                .createdAt(LocalDateTime.now())
                .build();

    }

    @BeforeEach
    public void setUp() throws Exception {
        createPostDTO = createCRUDPostDTO();
        Set<Tag> tags = createTagExamples();
        List<String> tagIds = new ArrayList<>(tags.stream().map(tag -> tag.getId().toString()).collect(Collectors.toSet()));
        createPostDTO.setTagIds(tagIds);
    }

    @Test
    public void shouldCreate_postAndTag_linkedTogether() {
        CommandResDTO commandResDTO = postDAO.create(createPostDTO);
        UUID createdPostId = UUID.fromString(commandResDTO.getId());
        Post createPost = postRepository.findById(createdPostId).orElse(null);
        assertNotNull(createPost);
    }
}
