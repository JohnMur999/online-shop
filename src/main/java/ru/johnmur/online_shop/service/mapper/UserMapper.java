package ru.johnmur.online_shop.service.mapper;

import org.springframework.stereotype.Component;
import ru.johnmur.online_shop.DTO.UserDTO;
import ru.johnmur.online_shop.model.User;

@Component
public class UserMapper {
    public UserDTO toUserDTO(User user) {
        return new UserDTO(
                user.getId(),
                user.getUsername(),
                user.getGender(),
                user.getBalance(),
                user.getRole()
        );
    }

    public User toEntity(UserDTO userDTO) {
        User user = new User();
        user.setId(userDTO.getUserID());
        user.setUsername(userDTO.getUsername());
        user.setGender(userDTO.getGender());
        user.setBalance(userDTO.getBalance());
        user.setRole(userDTO.getRole());

        return user;
    }
}
