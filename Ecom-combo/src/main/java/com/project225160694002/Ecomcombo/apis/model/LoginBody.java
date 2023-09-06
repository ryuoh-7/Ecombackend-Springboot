package com.project225160694002.Ecomcombo.apis.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

public class LoginBody {
    @Getter
    @NotBlank
    @NotNull
    private String username;

    @Getter
    @NotBlank
    @NotNull
    private String password;

}
