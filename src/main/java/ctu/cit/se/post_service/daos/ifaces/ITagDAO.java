package ctu.cit.se.post_service.daos.ifaces;

import ctu.cit.se.post_service.daos.IBaseDAO;
import ctu.cit.se.post_service.dtos.others.CommandResDTO;
import ctu.cit.se.post_service.dtos.tags.CreateTagDTO;
import ctu.cit.se.post_service.dtos.tags.RetrieveTagDTO;
import ctu.cit.se.post_service.dtos.tags.UpdateTagDTO;
import ctu.cit.se.post_service.entities.Tag;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public interface ITagDAO extends IBaseDAO<CreateTagDTO, UpdateTagDTO, RetrieveTagDTO, CommandResDTO, UUID> {
    void createInitData(List<Tag> tags);
}
