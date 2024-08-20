package ru.johnmur.online_shop.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.johnmur.online_shop.model.User;

import java.util.List;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {
    User findByUsername(String username);

    List<User> findAllByOrderByUsernameAsc();
}
