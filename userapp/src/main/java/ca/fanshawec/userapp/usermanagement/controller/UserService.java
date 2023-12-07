package ca.fanshawec.userapp.usermanagement.controller;

import ca.fanshawec.userapp.usermanagement.view.LoginRequest;
import ca.fanshawec.userapp.usermanagement.model.UserRepo;
import ca.fanshawec.userapp.usermanagement.model.ApplicationUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    public void add(ApplicationUser user) {
        userRepo.save(user);
    }

    public ApplicationUser find(String userName) {
        List<ApplicationUser> users = findAll();
        for (ApplicationUser applicationUser : users) {
            if (applicationUser.getUserName().equals(userName)) {
                return applicationUser;
            }
        }
        return null;
    }

    public List<ApplicationUser> findAll() {
        return userRepo.findAll();
    }

    public boolean login(LoginRequest loginRequest) {
        ApplicationUser user = this.find(loginRequest.getUserName());
        if (user != null && user.getPassword().equals(loginRequest.getPassword())) {
            return true;
        }
        return false;
    }

    public void updateUserPassword(String userName, ApplicationUser user) {
        ApplicationUser existingUser = userRepo.findByUserName(userName);

        if (existingUser != null) {
            // Compare passwords using equals method
            if (Objects.equals(user.getPassword(), user.getConfirmPassword())) {
                existingUser.setPassword(user.getPassword());
                existingUser.setConfirmPassword(user.getConfirmPassword());
                userRepo.save(existingUser);
            } else {
                throw new IllegalStateException("Passwords don't match");
            }
        } else {
            throw new IllegalStateException("User does not exist");
        }
    }
    //deleteUser
    public void deleteById(String userName) {
        ApplicationUser killUser = userRepo.findByUserName(userName);
        userRepo.delete(killUser);

    }

    public String getBioByUsername(String userName) {
        ApplicationUser userBio = userRepo.findByUserName(userName);
        if (userBio != null) {
            return userBio.getShortBio();
        } else {
            throw new IllegalStateException("User does not exist");
        }
    }
}
