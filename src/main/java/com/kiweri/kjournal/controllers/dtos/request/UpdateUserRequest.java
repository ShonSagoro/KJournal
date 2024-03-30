package com.kiweri.kjournal.controllers.dtos.request;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UpdateUserRequest{
    private String email;
    private String subscription;

}

