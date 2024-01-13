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
    public User createUser(UserCreateDto userCreateDto) {
        User user = new User();

        user.setName(userCreateDto.getName());

        List<String> base64List = new ArrayList<>();

        for (MultipartFile file : userCreateDto.getPictures()) {
            String base64String = encodeMultipartFileWithPrefix(file);
            base64List.add(base64String);
        }

        user.setProfilePicture(base64List);

        return userRepository.save(user);
    }

    @Override
    public List<User> listUser() {
        return userRepository.findAll();
    }

    @Override
    public User updateUser(Long id, UserCreateDto requestDto) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));

        user.setName(requestDto.getName());

        List<String> base64List = new ArrayList<>();

        for (MultipartFile file : requestDto.getPictures()) {
            String base64String = encodeMultipartFileWithPrefix(file);
            base64List.add(base64String);
        }

        user.setProfilePicture(base64List);

        return userRepository.save(user);
    }

    private String encodeMultipartFileWithPrefix(MultipartFile file) {
        try {
            byte[] fileContent = file.getBytes();
            String base64Encoded = Base64.getEncoder().encodeToString(fileContent);
            return "data:" + file.getContentType() + ";base64," + base64Encoded;
        } catch (Exception e) {
            return null;
        }
    }
}
