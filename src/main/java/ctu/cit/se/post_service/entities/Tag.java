package ctu.cit.se.post_service.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "tags")
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(name = "tag_title", columnDefinition = "varchar(250) not null")
    private String title;
    @Column(name = "tag_code")
    private String code;
    @Column(name = "tag_description", columnDefinition = "varchar(250) not null")
    private String description;
    @OneToMany(mappedBy = "tag", cascade = CascadeType.ALL)
    @ToString.Exclude
    Set<PostTag> postTags = new HashSet<>();
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tag tag = (Tag) o;
        return Objects.equals(id, tag.id);
    }
    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
