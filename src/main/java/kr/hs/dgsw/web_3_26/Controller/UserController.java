package kr.hs.dgsw.web_3_26.Controller;

import kr.hs.dgsw.web_3_26.Domain.User;
import kr.hs.dgsw.web_3_26.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController
{
    @Autowired
    private UserService userService;

    @PostMapping("/adduser")
    public User addUser(@RequestBody User user)
    {
        return this.userService.addUser(user);
    }

    @GetMapping("/listuser")
    public List<User> list()
    {
        return userService.listUser();
    }

    @GetMapping("/listuser/{userId}")
    public User findUser(@PathVariable Long userId)
    {
        return userService.findUser(userId);
    }

    @PutMapping("/updateuser/{userId}")
    public User updateUser(@PathVariable long userId, @RequestBody User user)
    {
        return userService.updateUser(userId, user);
    }

    @DeleteMapping("/deleteuser/{userId}")
    public boolean deleteUser(@PathVariable long userId)
    {
        return userService.deleteUser(userId);
    }
}
