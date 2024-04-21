package ctu.cit.se.post_service.controllers.imples;

import ctu.cit.se.post_service.controllers.ifaces.ITagController;
import ctu.cit.se.post_service.daos.ifaces.ITagDAO;
import ctu.cit.se.post_service.dtos.others.CommandResDTO;
import ctu.cit.se.post_service.dtos.tags.CreateTagDTO;
import ctu.cit.se.post_service.dtos.tags.RetrieveTagDTO;
import ctu.cit.se.post_service.dtos.tags.UpdateTagDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/tags")
public class TagController implements ITagController {
    @Autowired
    private ITagDAO tagDAO;
    @Override
    @GetMapping
    public ResponseEntity<List<RetrieveTagDTO>> list() {
        return ResponseEntity.ok(tagDAO.list());
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<RetrieveTagDTO> retrieve(@PathVariable String id) {
        return ResponseEntity.ok(tagDAO.retrieve(UUID.fromString(id)));
    }
    @Override
    @PostMapping
    public ResponseEntity<CommandResDTO> create(@RequestBody CreateTagDTO createTagDTO) {
        return new ResponseEntity<>(tagDAO.create(createTagDTO), HttpStatus.CREATED);
    }
    @Override
    @PatchMapping
    public ResponseEntity<CommandResDTO> update(@RequestBody UpdateTagDTO updateTagDTO) {
        return new ResponseEntity<>(tagDAO.update(updateTagDTO), HttpStatus.OK);
    }
    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        tagDAO.delete(UUID.fromString(id));
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
