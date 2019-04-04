package kr.hs.dgsw.web_3_26.Repository;

import kr.hs.dgsw.web_3_26.Domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long>
{

}
