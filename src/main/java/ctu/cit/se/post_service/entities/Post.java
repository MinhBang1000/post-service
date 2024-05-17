package ctu.cit.se.post_service.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "posts")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(name = "post_code")
    private String code;
    @Column(name = "post_title", columnDefinition = "varchar(250) not null")
    private String title;
    @Column(name = "post_content", columnDefinition = "text")
    private String content;
    @Column(name = "post_avatar", columnDefinition = "text")
    private String avatar;
    @Column(name = "post_created_at")
    private LocalDateTime createdAt;
    @Column(name = "post_creator_name", columnDefinition = "varchar(250) not null")
    private String creator;
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    @ToString.Exclude
    private Set<PostTag> postTags = new HashSet<>();
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Post post = (Post) o;
        return Objects.equals(id, post.id);
    }
    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
