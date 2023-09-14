package com.springdemos.springdemos.service.impl;

import com.springdemos.springdemos.entity.MenuItem;
import com.springdemos.springdemos.repository.MenuItemRepository;
import com.springdemos.springdemos.service.usecases.MenuItemService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MenuItemServiceImpl implements MenuItemService {

    private final MenuItemRepository menuItemRepository;

    public MenuItemServiceImpl(MenuItemRepository menuItemRepository) {
        this.menuItemRepository = menuItemRepository;
    }

    @Override
    public List<MenuItem> getMenuItems() {
        List<MenuItem> items = menuItemRepository.findAll();

        return groupMenuItems(items);
    }

    public List<MenuItem> groupMenuItems(List<MenuItem> menuItems) {
        Map<Long, List<MenuItem>> parentChildrenMap = new HashMap<>();

        for (MenuItem item : menuItems) {
            Long parentId = item.getParentId();
            parentChildrenMap.putIfAbsent(parentId, new ArrayList<>());
            parentChildrenMap.get(parentId).add(item);
        }

        return buildMenuTree(null, parentChildrenMap);
    }

    private List<MenuItem> buildMenuTree(
            Long parentId,
            Map<Long, List<MenuItem>> parentChildrenMap) {
        List<MenuItem> menuTree = new ArrayList<>();

        List<MenuItem> children = parentChildrenMap.get(parentId);
        if (children != null) {
            for (MenuItem child : children) {
                List<MenuItem> subtree = buildMenuTree(child.getId(), parentChildrenMap);
                child.setSubItems(subtree);
                menuTree.add(child);
            }
        }

        return menuTree;
    }
}
