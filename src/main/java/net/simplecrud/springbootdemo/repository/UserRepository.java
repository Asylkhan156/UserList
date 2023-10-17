package net.simplecrud.springbootdemo.repository;

import net.simplecrud.springbootdemo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    public List<User> findByFirstName(String firstName);
}
