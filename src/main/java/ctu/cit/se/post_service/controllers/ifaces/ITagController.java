package ctu.cit.se.post_service.controllers.ifaces;

import ctu.cit.se.post_service.controllers.IBaseController;
import ctu.cit.se.post_service.dtos.others.CommandResDTO;
import ctu.cit.se.post_service.dtos.tags.CreateTagDTO;
import ctu.cit.se.post_service.dtos.tags.RetrieveTagDTO;
import ctu.cit.se.post_service.dtos.tags.UpdateTagDTO;

public interface ITagController extends IBaseController<CreateTagDTO, UpdateTagDTO, RetrieveTagDTO, CommandResDTO> {
}
