package com.springdemos.springdemos.service.user.contracts;

import com.springdemos.springdemos.data.dto.UserCreateDto;
import com.springdemos.springdemos.entity.User;

import java.util.List;

public interface UserService {
    User createUser(UserCreateDto userCreateDto) ;
    List<User> listUser();
}
