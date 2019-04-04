package kr.hs.dgsw.web_3_26.Service;

import kr.hs.dgsw.web_3_26.Domain.User;
import kr.hs.dgsw.web_3_26.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User addUser(User user) {
        Optional<User> found = this.userRepository.findByEmail(user.getEmail());
        if(found.isPresent())
        {
            return null;
        }
        else
        {
            return userRepository.save(user);
        }
    }

    @Override
    public List<User> listUser() {
        return userRepository.findAll();
    }

    @Override
    public User findUser(Long userId) {
        return userRepository.findById(userId).orElse(null);
    }

    @Override
    public User updateUser(Long userId, User user) {
        return userRepository.findById(userId).map(found -> {
            found.setUserName(user.getUserName());
            found.setEmail(user.getEmail());
            found.setImagePath(user.getImagePath());
            return userRepository.save(found);
        }).orElse(null);
    }

    @Override
    public boolean deleteUser(Long userId) {
        return userRepository.findById(userId).map(found ->{
            userRepository.delete(found);
            return true;
        }).orElse(false);
    }
}
