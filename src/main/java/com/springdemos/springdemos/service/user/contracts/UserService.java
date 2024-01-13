package com.springdemos.springdemos.service.user.contracts;

import com.springdemos.springdemos.data.dto.UserCreateDto;
import com.springdemos.springdemos.entity.User;

import java.io.IOException;
import java.util.List;

public interface UserService {
    User createUser(UserCreateDto userCreateDto) throws IOException;
    List<User> listUser();
}
