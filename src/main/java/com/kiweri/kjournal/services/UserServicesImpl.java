package com.kiweri.kjournal.services;

import com.kiweri.kjournal.controllers.dtos.request.CreateUserRequest;
import com.kiweri.kjournal.controllers.dtos.request.UpdateUserRequest;
import com.kiweri.kjournal.controllers.dtos.response.BaseResponse;
import com.kiweri.kjournal.controllers.dtos.response.GetUserResponse;
import com.kiweri.kjournal.entities.User;
import com.kiweri.kjournal.entities.enums.converters.SubscriptionTypeConverter;
import com.kiweri.kjournal.repositories.IUserRepository;
import com.kiweri.kjournal.services.interfaces.IUserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServicesImpl implements IUserServices {
    @Autowired
    private IUserRepository repository;

    @Autowired
    private SubscriptionTypeConverter subscriptionTypeConverter;

    @Override
    public BaseResponse get(Long id) {
        User user = repository.findById(id)
                .orElseThrow(RuntimeException::new);
        return BaseResponse.builder()
                .data(from(user))
                .message("user for: " + id)
                .success(true)
                .httpStatus(HttpStatus.FOUND).build();
    }

    @Override
    public BaseResponse list() {
        List<GetUserResponse> responses = repository
                .findAll()
                .stream()
                .map(this::from).toList();
        return BaseResponse.builder()
                .data(responses)
                .message("find all users")
                .success(true)
                .httpStatus(HttpStatus.FOUND).build();
    }

    @Override
    public BaseResponse create(CreateUserRequest request) {
        Optional<User> possibleCopy = repository.findByEmail(request.getEmail());

        if (possibleCopy.isPresent()) {
            throw new RuntimeException("the user exist");
        }

        User user = repository.save(from(request));
        return BaseResponse.builder()
                .data(from(user))
                .message("The user was created")
                .success(true)
                .httpStatus(HttpStatus.CREATED).build();
    }

    @Override
    public BaseResponse update(Long id, UpdateUserRequest request) {
        User usr = repository.findById(id).orElseThrow(RuntimeException::new);
        User upUser = repository.save(update(usr, request));
        return BaseResponse.builder()
                .data(from(upUser))
                .message("user updated")
                .success(true)
                .httpStatus(HttpStatus.ACCEPTED).build();
    }

    @Override
    public User findByEmail(String email) {
        return repository.findByEmail(email)
                .orElseThrow(RuntimeException::new);
    }


    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public User findById(Long id) {
        return repository.findById(id)
                .orElseThrow(RuntimeException::new);
    }

    private GetUserResponse from(User user) {
        GetUserResponse response = new GetUserResponse();
        response.setId(user.getId());
        response.setEmail(user.getEmail());
        response.setSubscription(subscriptionTypeConverter.convertToDatabaseColumn(user.getSubscriptionType()));
        return response;

    }

    private User from(CreateUserRequest request) {
        User user = new User();
        user.setSubscriptionType(subscriptionTypeConverter.convertToEntityAttribute("FREE"));
        user.setEmail(request.getEmail());
        user.setPassword(new BCryptPasswordEncoder().encode(request.getPassword()));
        return user;
    }

    private User update(User user, UpdateUserRequest update) {
        user.setSubscriptionType(subscriptionTypeConverter.convertToEntityAttribute(update.getSubscription()));
        user.setEmail(update.getEmail());
        return user;
    }
}
