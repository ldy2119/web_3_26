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

    private String originFileName;

    private String password;

    @CreationTimestamp
    private LocalDateTime joined;
    @UpdateTimestamp
    private LocalDateTime modified;

    public User(String username, String email, String password, String imagePath, String originFileName)
    {
        this.userName = username;
        this.email = email;
        this.imagePath = imagePath;
        this.originFileName = originFileName;
        this.password = password;
    }

    public User(String username, String email, String password)
    {
        this.userName = username;
        this.email = email;
        this.password = password;
    }

    public User(String email, String password)
    {
        this.email = email;
        this.password = password;
    }
}
