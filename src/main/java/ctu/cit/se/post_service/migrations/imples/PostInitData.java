package ctu.cit.se.post_service.migrations.imples;

import ctu.cit.se.post_service.entities.Post;
import ctu.cit.se.post_service.migrations.IBaseInitData;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PostInitData implements IBaseInitData<Post> {
    @Override
    public List<Post> getInitData() {
        // Create post for new deployment here
        return List.of();
    }
}
