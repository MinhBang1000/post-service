package ctu.cit.se.post_service.migrations.imples;

import ctu.cit.se.post_service.entities.Tag;
import ctu.cit.se.post_service.migrations.IBaseInitData;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TagInitData implements IBaseInitData<Tag> {

    @Override
    public List<Tag> getInitData() {
        return List.of(
                Tag.builder()
                        .title("Nông nghiệp")
                        .code("post-service-tag-agriculture")
                        .build(),
                Tag.builder()
                        .title("Khoa học và công nghệ")
                        .code("post-service-tag-technology")
                        .build(),
                Tag.builder()
                        .title("Thủy sản")
                        .code("post-service-tag-aquaculture")
                        .build(),
                Tag.builder()
                        .title("Kinh tế")
                        .code("post-service-tag-economy")
                        .build()
        );
    }
}
