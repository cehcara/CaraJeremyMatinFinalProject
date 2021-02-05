package com.example.task10.Controllers;

import com.example.task10.Models.User;
import com.example.task10.Models.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
public class UserController {
    @Autowired
    UserServiceImpl userService;

    @RequestMapping(value = "/login")
    public ResponseEntity<Collection<User>> login() {
        return new ResponseEntity<>(userService.getUsers(), HttpStatus.OK);
    }

    @RequestMapping(value = "/register")
    public ResponseEntity<String> register(@RequestBody User user) {
        userService.createUser(user);
        return new ResponseEntity<>("User is created successfully", HttpStatus.CREATED);
    }

    @RequestMapping(value = "/users/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> delete(@PathVariable("id") String id) {
        userService.deleteUser(id);
        return new ResponseEntity<>("User is deleted successfully", HttpStatus.OK);
    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.PUT)
    public ResponseEntity<String>
    updateUser(@PathVariable("id") String id, @RequestBody User user) {
        userService.updateUser(id, user);
        return new ResponseEntity<>("User is updated successfully", HttpStatus.OK);
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public ResponseEntity<Collection<User>>
    getEveryUsers() {
        return new ResponseEntity<Collection<User>>(userService.getUsers(), HttpStatus.OK);
    }

    @PostMapping(value="post")
    @ResponseBody
    public void test(@RequestBody User user){
        System.out.println(user.getName());
        System.out.println(user.getEmail());
    }

    @GetMapping(value="list")
    @ResponseBody
    public User list(){
        //from the database.
        User u = new User();
        u.setName("Bryan");
        u.setEmail("ytbryan@hey.com");
        return u;
    }
}
