package kr.hs.dgsw.web_3_26.Protocol;

import kr.hs.dgsw.web_3_26.Domain.Comment;
import lombok.Data;

@Data
public class CommentUsernameProtocol extends Comment {

    private String userName;

    public CommentUsernameProtocol(Comment c, String userName)
    {
        super(c);
        this.userName = userName;

    }
}
