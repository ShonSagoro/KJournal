package com.kiweri.kjournal.controllers;

import com.kiweri.kjournal.controllers.dtos.request.CreateUserRequest;
import com.kiweri.kjournal.controllers.dtos.request.UpdateUserRequest;
import com.kiweri.kjournal.controllers.dtos.response.BaseResponse;
import com.kiweri.kjournal.services.interfaces.IUserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private IUserServices service;

    @GetMapping("/{id}")
    public ResponseEntity<BaseResponse> get(@PathVariable Long id) {
        return service.get(id).apply();
    }

    @GetMapping
    public ResponseEntity<BaseResponse> getAll() {
        return service.list().apply();
    }

    @PostMapping("/register")
    public ResponseEntity<BaseResponse> create(@RequestBody CreateUserRequest request) {
        return service.create(request).apply();
    }

    @PutMapping("/{id}")
    public ResponseEntity<BaseResponse> update(@RequestBody UpdateUserRequest request,
                                               @PathVariable Long id) {

        return service.update(id, request).apply();
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

}
