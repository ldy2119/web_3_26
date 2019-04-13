package kr.hs.dgsw.web_3_26.Service;

import kr.hs.dgsw.web_3_26.Domain.Comment;
import kr.hs.dgsw.web_3_26.Domain.User;
import kr.hs.dgsw.web_3_26.Protocol.CommentUsernameProtocol;
import kr.hs.dgsw.web_3_26.Repository.CommentRepository;
import kr.hs.dgsw.web_3_26.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private UserRepository userRepository;

    @PostConstruct
    private void init()
    {
        User u = userRepository.save(new User("aaa", "aaa@dgsw", "1234"));
        commentRepository.save(new Comment(u.getId(), "hi there1", null, null));
        commentRepository.save(new Comment(u.getId(), "hi there2", null, null));
        commentRepository.save(new Comment(u.getId(), "hi there3", null, null));
    }

    @Override
    public CommentUsernameProtocol addComment(Comment comment) {

        return userRepository.findById(comment.getUserId()).map(found -> {
            return new CommentUsernameProtocol(commentRepository.save(comment), found.getUserName());
        }).orElse(null);

    }

    @Override
    public List<CommentUsernameProtocol> listAllComments() {
        List<Comment> commentList = commentRepository.findAll();
        List<CommentUsernameProtocol> cupList = new ArrayList<>();

        commentList.forEach(comment -> {
            Optional<User> found = userRepository.findById(comment.getUserId());
            String userName = (found.isPresent()) ? found.get().getUserName() : null;
            cupList.add(new CommentUsernameProtocol(comment, userName) );
        });
        return cupList;
    }

    @Override
    public CommentUsernameProtocol findComment(Long commentId) {
        return commentRepository.findById(commentId).map(found -> {
            return new CommentUsernameProtocol(found, userRepository.findById(found.getUserId()).map(u -> {
                return u.getUserName();
            }).orElse(null));
        }).orElse(null);
    }

    @Override
    public CommentUsernameProtocol updateComment(Comment comment) {

        return commentRepository.findById(comment.getId()).map(found -> {
            found.setContent(comment.getContent());
            found.setImagePath(comment.getImagePath());
            found.setOriginFileName(comment.getOriginFileName());
            User user = userRepository.findById(found.getUserId()).map(f -> f).orElse(null);
            return new CommentUsernameProtocol(commentRepository.save(found), user.getUserName());
        }).orElse(null);
    }

    @Override
    public boolean removeComment(Long commentId) {

        try
        {
            return commentRepository.findById(commentId).map(found -> {
                commentRepository.delete(found);
                return true;
            }).orElse(false);
        }
        catch (Exception e)
        {
            return false;
        }
    }


}
