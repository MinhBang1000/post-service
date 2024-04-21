package ctu.cit.se.post_service.controllers.ifaces;

import ctu.cit.se.post_service.controllers.IBaseController;
import ctu.cit.se.post_service.dtos.others.CommandResDTO;
import ctu.cit.se.post_service.dtos.posts.CreatePostDTO;
import ctu.cit.se.post_service.dtos.posts.RetrievePostDTO;
import ctu.cit.se.post_service.dtos.posts.UpdatePostDTO;

public interface IPostController extends IBaseController<CreatePostDTO, UpdatePostDTO, RetrievePostDTO, CommandResDTO> {
}
