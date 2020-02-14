package com.example.eatgo.interfaces;

import com.example.eatgo.application.UserService;
import com.example.eatgo.domain.User;
import org.apache.coyote.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    //1. User list
    @GetMapping("users")
    public List<User> list() {

        List<User> users = userService.getUsers();


        return users;
    }

    //2. User create -> 회원가입
    @PostMapping("users")
    public ResponseEntity<?> create(
            @RequestBody User resource) throws URISyntaxException {
        User user = userService.addUser(resource.getEmail(), resource.getName(), resource.getLevel());

        URI location = new URI("/users/"+user.getId());
        return ResponseEntity.created(location).body("{}");
    }

    //3. User update
    @PatchMapping("/users/{id}")
    public String update(@PathVariable("id") Long id,
                         @RequestBody User resource) {
        String email = resource.getEmail();
        String name = resource.getName();
        Long level = resource.getLevel();

        userService.updateUser(id, email, name, level);

        return "{}";
    }

    //4. User delete -> level: 0 => 아무것도 못 함.
    @DeleteMapping("/users/{id}")
    public String delete(@PathVariable("id") Long id) {
        userService.deactiveUser(id);

        return "{}";
    }

}
