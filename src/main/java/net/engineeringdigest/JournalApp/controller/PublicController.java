package net.engineeringdigest.JournalApp.controller;

import net.engineeringdigest.JournalApp.entity.User;
import net.engineeringdigest.JournalApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController  // this class has already @Restcontroller  but also all its end point and url path will convert into json on what the class return.
@RequestMapping("/public")
public class PublicController { // method

    @Autowired  // ere we areinjecting dependencies using  @Autowired
    private UserService userService;

    @GetMapping("/health-check") // means the function healthcheck() will map on end point
   public String healthcheck(){  //restcontroller has special type so this string is the special type.
        return "ok";
// now the function healthcheck() map on health-check this endpoint.
    }

    @PostMapping("/create-user")  //  @PostMapping beacuse we are adding something
    public void createUser(@RequestBody User user) {
        userService.saveNewEntry(user);
    }
}
// if u run localhost 8080 health-check with get then controler will be on String healthcheck() function.

