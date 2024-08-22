package ru.johnmur.online_shop.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.johnmur.online_shop.DTO.UserBalanceRequestDTO;
import ru.johnmur.online_shop.DTO.UserDTO;
import ru.johnmur.online_shop.DTO.UserRegistrationDTO;
import ru.johnmur.online_shop.repos.UserRepo;
import ru.johnmur.online_shop.model.User;
import ru.johnmur.online_shop.service.mapper.UserMapper;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepo userRepo;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepo userRepo,
                       UserMapper userMapper,
                       PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
        this.userMapper = userMapper;
    }

    public List<UserDTO> findAll() {
        return userRepo.findAll().stream()
                .map(userMapper::toUserDTO)
                .collect(Collectors.toList());
    }

    public List<UserDTO> findAllSortedByUsername() {
        return userRepo.findAllByOrderByUsernameAsc().stream()
                .map(userMapper::toUserDTO)
                .collect(Collectors.toList());
    }

    public Optional<UserDTO> findById(Long id) {
        return userRepo.findById(id)
                .map(userMapper::toUserDTO);
    }

    public Optional<UserDTO> findByUsername(String username) {
        User user = userRepo.findByUsername(username);
        if (user != null) {
            return Optional.of(userMapper.toUserDTO(user));
        }
        return Optional.empty();
    }

    public BigDecimal updateUserBalance(UserBalanceRequestDTO userBalanceRequestDTO,
                                        BigDecimal amount) {
        User user = userRepo.findById(userBalanceRequestDTO.getId())
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        BigDecimal newBalance = amount.add(userBalanceRequestDTO.getAmount());
        user.setBalance(newBalance);
        userRepo.save(user);
        return newBalance;
    }

    public boolean registerUser(UserRegistrationDTO userRegistrationDTO) {
        if (userRepo.findByUsername(userRegistrationDTO.getUsername()) != null) {
            return false;
        }

        User user = new User();
        user.setUsername(userRegistrationDTO.getUsername());
        user.setPassword(passwordEncoder.encode(userRegistrationDTO.getPassword()));
        user.setBalance(BigDecimal.ZERO);
        user.setGender(userRegistrationDTO.getGender());
        userRepo.save(user);

        return true;
    }

    public UserDTO createUser(UserRegistrationDTO userRegistrationDTO) {
        User user = new User();

        user.setUsername(userRegistrationDTO.getUsername());
        user.setPassword(passwordEncoder.encode(userRegistrationDTO.getPassword()));
        user.setGender(userRegistrationDTO.getGender());
        user.setBalance(BigDecimal.ZERO);
        user.setRole(userRegistrationDTO.getRole());
        userRepo.save(user);

        return new UserDTO(
                user.getId(),
                user.getUsername(),
                user.getGender(), user.getBalance(),
                user.getRole()
        );
    }

    public void deleteUser(Long id) {
        userRepo.deleteById(id);
    }
}
