package com.kiweri.kjournal.controllers.dtos.response;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class GetUserResponse {

    private Long id;

    private String email;

    private String subscription;
}
