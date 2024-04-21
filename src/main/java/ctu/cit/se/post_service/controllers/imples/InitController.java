package ctu.cit.se.post_service.controllers.imples;

import ctu.cit.se.post_service.daos.ifaces.ITagDAO;
import ctu.cit.se.post_service.entities.Tag;
import ctu.cit.se.post_service.migrations.IBaseInitData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/init-data")
public class InitController {
    @Autowired
    private ITagDAO tagDAO;
    @Autowired
    private IBaseInitData<Tag> tagIBaseInitData;
    @PostMapping
    public ResponseEntity<Void> create() {
        var tagList = tagIBaseInitData.getInitData();
        Set<Tag> tags = new HashSet<>(tagList);
        tagDAO.createInitData(tags);
        return ResponseEntity.ok(null);
    }
}
