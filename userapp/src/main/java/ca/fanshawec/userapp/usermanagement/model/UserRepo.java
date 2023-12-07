package ca.fanshawec.userapp.usermanagement.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepo extends JpaRepository<ApplicationUser, Integer> {
    public ApplicationUser findByUserName(String userName);
}
