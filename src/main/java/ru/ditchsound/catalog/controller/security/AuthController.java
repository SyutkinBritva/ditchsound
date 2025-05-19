package ru.ditchsound.catalog.controller.security;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.ditchsound.catalog.dto.Security.JwtRequest;
import ru.ditchsound.catalog.dto.Security.JwtResponse;
import ru.ditchsound.catalog.dto.Security.RegDto;
import ru.ditchsound.catalog.dto.Security.UserDto;
import ru.ditchsound.catalog.service.security.AuthService;

@RestController
@RequiredArgsConstructor
@Tag(name = "Authentication", description = "Auth API")
public class AuthController {
    private final AuthService authService;

    @Operation(summary = "Authenticate user and get JWT token")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "JWT token returned",
                    content = @Content(schema = @Schema(implementation = JwtResponse.class))),
            @ApiResponse(responseCode = "401", description = "Invalid credentials")
    })
        @PostMapping("/auth")
    public JwtResponse createAuthToken(@RequestBody JwtRequest authRequest) {
        return authService.createAuthToken(authRequest);
    }

    @Operation(summary = "Register new user")
    @PostMapping("/registration")
    public UserDto createNewUser(@RequestBody RegDto registrationUserDto) {
        return authService.createNewUser(registrationUserDto);
    }
}
