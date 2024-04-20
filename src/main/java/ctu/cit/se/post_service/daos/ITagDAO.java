package ctu.cit.se.post_service.daos;

import ctu.cit.se.post_service.dtos.others.CommandResDTO;
import ctu.cit.se.post_service.dtos.tags.CreateTagDTO;
import ctu.cit.se.post_service.dtos.tags.RetrieveTagDTO;
import ctu.cit.se.post_service.dtos.tags.UpdateTagDTO;

import java.util.List;
import java.util.UUID;

public interface ITagDAO {
    List<RetrieveTagDTO> list();
    CommandResDTO create(CreateTagDTO createTagDTO);
    CommandResDTO update(UpdateTagDTO updateTagDTO);
    RetrieveTagDTO retrieve(UUID tagId);
    void delete(UUID tagId);
}
