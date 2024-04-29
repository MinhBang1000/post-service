package ctu.cit.se.post_service.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.*;

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
    @Column(name = "post_title")
    private String title;
    @Column(name = "post_content")
    private String content;
    @Column(name = "post_created_at")
    private LocalDateTime createdAt;
    @Column(name = "post_creator_name")
    private String creator;
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinTable(name = "post_tags",
            joinColumns = @JoinColumn(name = "post_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id", referencedColumnName = "id")
    )
    private Set<Tag> tags = new HashSet<>();

    public void addTag(Tag tag) {
        if (Objects.isNull(tags)){
            tags = new HashSet<>();
        }
        tags.add(tag);
    }

    public void removeTag(Tag tag) {
        if (Objects.nonNull(tags)) {
            tags.remove(tag);
        }
    }

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

    @Override
    public String toString() {
        return "Post{" +
                "creator='" + creator + '\'' +
                ", createdAt=" + createdAt +
                ", content='" + content + '\'' +
                ", title='" + title + '\'' +
                ", code='" + code + '\'' +
                ", id=" + id +
                '}';
    }
}
