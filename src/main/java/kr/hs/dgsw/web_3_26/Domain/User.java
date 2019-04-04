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
public class User {

    @Id
    @GeneratedValue
    private Long id;

    private String userName;
    private String email;

    private String imagePath;

    @CreationTimestamp
    private LocalDateTime joined;
    @UpdateTimestamp
    private LocalDateTime modified;

    public User(String username, String email, String imagePath)
    {
        this.userName = username;
        this.email = email;
        this.imagePath = imagePath;
    }
}
