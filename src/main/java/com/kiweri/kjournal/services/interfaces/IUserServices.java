package com.kiweri.kjournal.services.interfaces;

import com.kiweri.kjournal.controllers.dtos.request.CreateUserRequest;
import com.kiweri.kjournal.controllers.dtos.request.UpdateUserRequest;
import com.kiweri.kjournal.controllers.dtos.response.BaseResponse;
import com.kiweri.kjournal.entities.User;

public interface IUserServices {
    BaseResponse create(CreateUserRequest request);

    BaseResponse update(Long id, UpdateUserRequest request);

    void delete(Long id);

    BaseResponse list();

    BaseResponse get(Long id);

    User findById(Long id);

    User findByEmail(String email);

}
