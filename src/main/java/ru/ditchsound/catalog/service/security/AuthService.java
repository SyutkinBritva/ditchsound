package ru.ditchsound.catalog.service.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import ru.ditchsound.catalog.dto.Security.JwtRequest;
import ru.ditchsound.catalog.dto.Security.JwtResponse;
import ru.ditchsound.catalog.dto.Security.RegDto;
import ru.ditchsound.catalog.dto.Security.UserDto;
import ru.ditchsound.catalog.exception.PasswordMismatchException;
import ru.ditchsound.catalog.exception.UsernameAlreadyExistsException;
import ru.ditchsound.catalog.model.User;
import ru.ditchsound.catalog.utils.JwtTokenUtils;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserService userService;
    private final JwtTokenUtils jwtTokenUtils;
    private final AuthenticationManager authenticationManager;

    public JwtResponse createAuthToken(JwtRequest authRequest) {
        // Если учетные данные неверны, исключение перейдет в GlobalExceptionHandler
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authRequest.getUserName(), authRequest.getPassword()
                )
        );
        // Загрузка деталей пользователя
        UserDetails userDetails = userService.loadUserByUsername(authRequest.getUserName());
        // Генерация токена
        String token = jwtTokenUtils.generateToken(userDetails);
        return new JwtResponse(token);
    }

    /**
     * Регистрация нового пользователя.
     * @param registrationUserDto - DTO с данными для регистрации
     * @return UserDto с данными зарегистрированного пользователя
     * @throws PasswordMismatchException если пароли не совпадают
     * @throws UsernameAlreadyExistsException если имя пользователя уже занято
     */
    public UserDto createNewUser(RegDto registrationUserDto) {
        // Проверка совпадения паролей
        if (!registrationUserDto.getPassword().equals(registrationUserDto.getConfirmPassword())) {
            throw new PasswordMismatchException("Пароли не совпадают");
        }
        // Проверка наличия пользователя с таким именем
        if (userService.getUserByName(registrationUserDto.getUserName()).isPresent()) {
            throw new UsernameAlreadyExistsException(
                    String.format("Пользователь '%s' уже существует", registrationUserDto.getUserName())
            );
        }
        // Создание пользователя
        User user = userService.createNewUser(registrationUserDto);
        return new UserDto(user.getId(), user.getUserName(), user.getEmail());
    }
}
