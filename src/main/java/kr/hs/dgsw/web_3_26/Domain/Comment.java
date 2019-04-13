package kr.hs.dgsw.web_3_26.Domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
public class Comment {

    @Id
    @GeneratedValue
    private Long id;

    private Long userId;
    private String content;

    @CreationTimestamp
    private LocalDateTime created;

    @UpdateTimestamp
    private LocalDateTime modified;

    private String imagePath;

    private String originFileName;

    public Comment(Comment c)
    {
        this.id = c.getId();
        this.userId = c.getUserId();
        this.content = c.getContent();
        this.imagePath = c.getImagePath();
        this.originFileName = c.getOriginFileName();
        this.created = c.created;
        this.modified = c.modified;
    }

    public Comment(Long userId, String content, String imagePath, String originFileName)
    {
        this.userId = userId;
        this.content = content;
        this.originFileName = originFileName;
        this.imagePath = imagePath;
    }
}
