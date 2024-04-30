package ctu.cit.se.post_service.daos.imples;

import ctu.cit.se.post_service.daos.ifaces.ITagDAO;
import ctu.cit.se.post_service.mappers.IMapper;
import ctu.cit.se.post_service.dtos.others.CommandResDTO;
import ctu.cit.se.post_service.dtos.tags.CreateTagDTO;
import ctu.cit.se.post_service.dtos.tags.RetrieveTagDTO;
import ctu.cit.se.post_service.dtos.tags.UpdateTagDTO;
import ctu.cit.se.post_service.entities.Tag;
import ctu.cit.se.post_service.exceptions.messages.CustomExceptionMessage;
import ctu.cit.se.post_service.repositories.ITagRepository;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class TagDAO implements ITagDAO {
    @Autowired
    private ITagRepository tagRepository;
    @Autowired
    private IMapper<CreateTagDTO, Tag> createMapper;
    @Autowired
    private IMapper<UpdateTagDTO, Tag> updateMapper;
    @Autowired
    private IMapper<Tag, RetrieveTagDTO> retrieveMapper;
    @Autowired
    private EntityManager entityManager;

    @Override
    public List<RetrieveTagDTO> list() {
        return tagRepository.findAll().stream().map((tag) -> {
            return retrieveMapper.convert(tag);
        }).collect(Collectors.toList());
    }

    @Override
    public CommandResDTO create(CreateTagDTO createTagDTO) {
        var tag = createMapper.convert(createTagDTO);
        var createdTag = tagRepository.save(tag);
        return CommandResDTO.builder()
                .id(createdTag.getId().toString())
                .build();
    }

    @Override
    public CommandResDTO update(UpdateTagDTO updateTagDTO) {
        var tag = updateMapper.convert(updateTagDTO);
        var updatedTag = tagRepository.save(tag);
        return CommandResDTO.builder()
                .id(updatedTag.getId().toString())
                .build();
    }

    @Override
    public RetrieveTagDTO retrieve(UUID tagId) {
        var tag = tagRepository.findById(tagId).orElseThrow(() -> new IllegalArgumentException(CustomExceptionMessage.TAG_NOT_FOUND));
        return retrieveMapper.convert(tag);
    }

    @Override
    @Transactional
    public void delete(UUID tagId) {
        var tag = tagRepository.findById(tagId).orElseThrow(() -> new IllegalArgumentException(CustomExceptionMessage.TAG_NOT_FOUND));

        entityManager.remove(tag);
    }

    @Override
    public void createInitData(Set<Tag> tags) {
        tagRepository.saveAll(tags);
    }
}
