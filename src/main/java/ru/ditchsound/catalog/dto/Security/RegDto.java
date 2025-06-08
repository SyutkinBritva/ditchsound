package ru.ditchsound.catalog.dto.Security;

import lombok.Data;

@Data
public class RegDto {

    private String userName;

    private String email;

    private String password;

    private String confirmPassword;
}
