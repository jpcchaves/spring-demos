package com.springdemos.springdemos.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "menu_items")
public class MenuItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Long parentId;

    private List<MenuItem> subItems;

    public MenuItem() {
    }

    public MenuItem(
            Long id,
            String name,
            Long parentId,
            List<MenuItem> subItems) {
        this.id = id;
        this.name = name;
        this.parentId = parentId;
        this.subItems = subItems;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public List<MenuItem> getSubItems() {
        return subItems;
    }

    public void setSubItems(List<MenuItem> subItems) {
        this.subItems = subItems;
    }
}
