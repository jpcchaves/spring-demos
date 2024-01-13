package com.springdemos.springdemos.service.user;

import com.springdemos.springdemos.data.dto.UserCreateDto;
import com.springdemos.springdemos.entity.User;
import com.springdemos.springdemos.repository.UserRepository;
import com.springdemos.springdemos.service.user.contracts.UserService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User createUser(UserCreateDto userCreateDto) throws IOException {
        User user = new User();

        user.setName(userCreateDto.getName());

        List<String> base64List = new ArrayList<>();

        for (MultipartFile file : userCreateDto.getPictures()) {
            String base64String = Base64.getEncoder().encodeToString(file.getBytes());
            base64List.add(base64String);
        }

        user.setProfilePicture(base64List);

        return userRepository.save(user);
    }

    @Override
    public List<User> listUser() {
        return userRepository.findAll();
    }
}
