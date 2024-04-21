package ctu.cit.se.post_service.daos.ifaces;

import ctu.cit.se.post_service.daos.IBaseDAO;
import ctu.cit.se.post_service.dtos.others.CommandResDTO;
import ctu.cit.se.post_service.dtos.posts.CreatePostDTO;
import ctu.cit.se.post_service.dtos.posts.RetrievePostDTO;
import ctu.cit.se.post_service.dtos.posts.UpdatePostDTO;

import java.util.UUID;

public interface IPostDAO extends IBaseDAO<CreatePostDTO, UpdatePostDTO, RetrievePostDTO, CommandResDTO, UUID> {

}
