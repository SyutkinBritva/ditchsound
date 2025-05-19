package ru.ditchsound.catalog.dto.Security;

import lombok.Data;

@Data
public class JwtRequest {

    private String userName;
    private String password;
}
