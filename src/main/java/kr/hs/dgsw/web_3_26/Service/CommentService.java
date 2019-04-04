package kr.hs.dgsw.web_3_26.Service;

import kr.hs.dgsw.web_3_26.Domain.Comment;
import kr.hs.dgsw.web_3_26.Domain.User;
import kr.hs.dgsw.web_3_26.Protocol.CommentUsernameProtocol;

import java.util.List;

public interface CommentService {
    CommentUsernameProtocol addComment(Comment comment);
    List<CommentUsernameProtocol> listAllComments();
    CommentUsernameProtocol findComment(Long commentId);
    Comment updateComment(Comment comment);
    boolean removeComment(Long commentId);
}
