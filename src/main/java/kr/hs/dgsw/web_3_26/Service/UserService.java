package kr.hs.dgsw.web_3_26.Service;

import kr.hs.dgsw.web_3_26.Domain.User;

import java.util.List;

public interface UserService
{
    User addUser(User user);
    List<User> listUser();
    User findUser(Long userId);
    User updateUser(Long userId, User user);
    boolean deleteUser(Long userId);
}
