package ctu.cit.se.post_service.controllers.imples;

import ctu.cit.se.post_service.controllers.ifaces.IPostController;
import ctu.cit.se.post_service.daos.ifaces.IPostDAO;
import ctu.cit.se.post_service.daos.ifaces.ITagDAO;
import ctu.cit.se.post_service.dtos.others.CommandResDTO;
import ctu.cit.se.post_service.dtos.posts.CreatePostDTO;
import ctu.cit.se.post_service.dtos.posts.RetrievePostDTO;
import ctu.cit.se.post_service.dtos.posts.UpdatePostDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/posts")
public class PostController implements IPostController {
    @Autowired
    private IPostDAO postDAO;
    @Autowired
    private ITagDAO tagDAO;

    @Override
    @GetMapping
    public ResponseEntity<List<RetrievePostDTO>> list() {
        return ResponseEntity.ok(postDAO.list());
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<RetrievePostDTO> retrieve(@PathVariable String id) {
        return ResponseEntity.ok(postDAO.retrieve(UUID.fromString(id)));
    }

    @Override
    @PostMapping
    public ResponseEntity<CommandResDTO> create(@RequestBody CreatePostDTO createPostDTO) {
        return new ResponseEntity<>(postDAO.create(createPostDTO), HttpStatus.CREATED);
    }

    @Override
    @PatchMapping
    public ResponseEntity<CommandResDTO> update(@RequestBody UpdatePostDTO updatePostDTO) {
        return new ResponseEntity<>(postDAO.update(updatePostDTO), HttpStatus.OK);
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        postDAO.delete(UUID.fromString(id));
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
