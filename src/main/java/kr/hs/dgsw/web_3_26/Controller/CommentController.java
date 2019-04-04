package kr.hs.dgsw.web_3_26.Controller;

import kr.hs.dgsw.web_3_26.Domain.Comment;
import kr.hs.dgsw.web_3_26.Domain.User;
import kr.hs.dgsw.web_3_26.Protocol.CommentUsernameProtocol;
import kr.hs.dgsw.web_3_26.Repository.UserRepository;
import kr.hs.dgsw.web_3_26.Service.CommentService;
import kr.hs.dgsw.web_3_26.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CommentController
{
    @Autowired
    private CommentService commentService;

    @PostMapping("/add")
    public CommentUsernameProtocol Add(@RequestBody Comment comment)
    {
        return commentService.addComment(comment);
    }

    @GetMapping("/list")
    public List<CommentUsernameProtocol> listComment()
    {
        return commentService.listAllComments();
    }

    @GetMapping("/list/{id}")
    public CommentUsernameProtocol findComment(@PathVariable Long id)
    {
        return commentService.findComment(id);
    }

    @PutMapping("/update")
    public Comment updateComment(@RequestBody Comment comment)
    {
        return commentService.updateComment(comment);
    }

    @DeleteMapping("/delete/{id}")
    public boolean deleteComment(@PathVariable long id)
    {
        return commentService.removeComment(id);
    }
}
