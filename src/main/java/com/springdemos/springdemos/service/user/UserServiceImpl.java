package com.springdemos.springdemos.service.user;

import com.springdemos.springdemos.data.dto.UserCreateDto;
import com.springdemos.springdemos.entity.User;
import com.springdemos.springdemos.repository.UserRepository;
import com.springdemos.springdemos.service.user.contracts.UserService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

@Service
public class UserServiceImpl implements UserService {
    private static final List<String> ALLOWED_EXTENSIONS = Arrays.asList("png", "jpeg", "jpg", "webp", "jfif");
    private static final long MAX_FILE_SIZE_BYTES = 5 * 1024 * 1024;
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
    public User getById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
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

    public String encodeMultipartFileWithPrefix(MultipartFile file) {
        try {
            if (file.isEmpty()) {
                throw new IllegalArgumentException("File is empty");
            }

            if (file.getSize() > MAX_FILE_SIZE_BYTES) {
                throw new IllegalArgumentException("File size exceeds the allowed limit");
            }

            String originalFilename = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
            String fileExtension = getFileExtension(originalFilename);
            if (!ALLOWED_EXTENSIONS.contains(fileExtension.toLowerCase())) {
                throw new IllegalArgumentException("Invalid file type. Allowed types are: " + ALLOWED_EXTENSIONS);
            }

            byte[] fileContent = file.getBytes();
            String base64Encoded = Base64.getEncoder().encodeToString(fileContent);
            return "data:" + file.getContentType() + ";base64," + base64Encoded;
        } catch (IOException | IllegalArgumentException e) {
            e.printStackTrace();
            return null;
        }
    }

    private String getFileExtension(String fileName) {
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }
}
