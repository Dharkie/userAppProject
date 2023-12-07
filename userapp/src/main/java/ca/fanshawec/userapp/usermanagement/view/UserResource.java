package ca.fanshawec.userapp.usermanagement.view;

import ca.fanshawec.userapp.usermanagement.model.ApplicationUser;
import ca.fanshawec.userapp.usermanagement.controller.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class UserResource {

    @Autowired
    private UserService userService;

@GetMapping("/name")
public String getPrint(){
    return "hellofrancis";
}


    @GetMapping("users")
    public List<ApplicationUser> getAll() {
        return userService.findAll();
    }

    @PostMapping("login")
    public ResponseEntity<Void> login(@RequestBody LoginRequest loginRequest) {
        boolean loginStatus = userService.login(loginRequest);
        if(loginStatus) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
    @PutMapping("/{userName}")
    public void updateUserPassword(@PathVariable String userName, @RequestBody ApplicationUser user)
    {
        userService.updateUserPassword(userName, user);
    }

    @PostMapping("signup")
    public void signUp(@RequestBody ApplicationUser applicationUser) {
        userService.add(applicationUser);
    }
    @DeleteMapping("/{userName}")
    public void deleteById(@PathVariable String userName)
    {
        userService.deleteById(userName);
    }
    @GetMapping("/{username}")
    public ResponseEntity<String> getBioByUsername(@PathVariable String username)
    {
        String userProfile = userService.getBioByUsername(username);
        if(userProfile!=null)
        {
            return ResponseEntity.ok(userProfile);
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }
}
