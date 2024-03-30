package com.kiweri.kjournal.controllers.dtos.request;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CreateUserRequest {
    private String email;
    private String password;

}
