package com.springdemos.springdemos.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.id.IdentifierGenerator;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private UUID uuid = UUID.randomUUID();

    @ElementCollection
    @Column(columnDefinition = "TEXT")
    private List<String> profilePicture;
    private String name;


    public User() {
    }

    public User(Long id, UUID uuid, List<String> profilePicture, String name) {
        this.id = id;
        this.uuid = uuid;
        this.profilePicture = profilePicture;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public List<String> getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(List<String> profilePicture) {
        this.profilePicture = profilePicture;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
