package ru.ditchsound.catalog.service.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ditchsound.catalog.dto.Security.RegDto;
import ru.ditchsound.catalog.mappers.RegMapper;
import ru.ditchsound.catalog.model.Role;
import ru.ditchsound.catalog.model.User;
import ru.ditchsound.catalog.repository.UserRepository;
import ru.ditchsound.catalog.service.RoleService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final RoleService roleService;
    private final RegMapper regMapper;
    private final PasswordEncoder passwordEncoder;

    public Optional<User> getUserByName(String userName) {
        return userRepository.findByUserName(userName);
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        User user = getUserByName(username).orElseThrow(() -> new UsernameNotFoundException(username));
        return new org.springframework.security.core.userdetails.User(
                user.getUserName(),
                user.getPassword(),
                user.getRoles().stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList())
        );
    }

    @Transactional
    public User createNewUser(RegDto regDto){
        if (userRepository.existsByUserName(regDto.getUserName())) {
            throw new IllegalArgumentException("Username already exists");
        }
        if (userRepository.existsByEmail(regDto.getEmail())) {
            throw new IllegalArgumentException("Email already registered");
        }

        User user = regMapper.toEntity(regDto);
        user.setPassword(passwordEncoder.encode(regDto.getPassword()));

        Role userRole = roleService.getRoleByName("ROLE_USER");
        user.setRoles(List.of(userRole));

        return userRepository.save(user);
    }

    @Transactional
    public User createAdminUser(RegDto regDto) {
        User user = createNewUser(regDto); // Базовая регистрация
        Role adminRole = roleService.getRoleByName("ROLE_ADMIN");
        user.getRoles().add(adminRole);
        return userRepository.save(user);
    }

}
