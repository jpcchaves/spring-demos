package com.springdemos.springdemos.data.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public class UserCreateDto {
   @NotEmpty
    private String name;

    @NotNull
    @NotEmpty
    @Size(max = 10485760, message = "Each file size must not exceed 10MB")
    private List<MultipartFile> pictures;

    public UserCreateDto() {
    }

    public UserCreateDto(String name, List<MultipartFile> pictures) {
        this.name = name;
        this.pictures = pictures;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<MultipartFile> getPictures() {
        return pictures;
    }

    public void setPictures(List<MultipartFile> pictures) {
        this.pictures = pictures;
    }
}
