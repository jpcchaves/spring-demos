package com.springdemos.springdemos.service.impl;

import com.springdemos.springdemos.entity.MenuItem;
import com.springdemos.springdemos.repository.MenuItemRepository;
import com.springdemos.springdemos.service.usecases.MenuItemService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MenuItemServiceImpl implements MenuItemService {

    private final MenuItemRepository menuItemRepository;

    public MenuItemServiceImpl(MenuItemRepository menuItemRepository) {
        this.menuItemRepository = menuItemRepository;
    }

    public static List<MenuItem> buildMenuTree(List<MenuItem> flatMenuItems) {
        List<MenuItem> menuTree = new ArrayList<>();

        for (MenuItem menuItem : flatMenuItems) {
            if (menuItem.getParent() == null) {
                menuTree.add(menuItem);
            } else {
                MenuItem parent = findMenuItemById(menuTree, menuItem.getParent().getId());
                if (parent != null) {
                    parent.getSubItems().add(menuItem);
                }
            }
        }

        return menuTree;
    }

    private static MenuItem findMenuItemById(
            List<MenuItem> menuItems,
            Long id) {
        for (MenuItem menuItem : menuItems) {
            if (menuItem.getId().equals(id)) {
                return menuItem;
            }
        }
        return null;
    }

    @Override
    public List<MenuItem> getMenuItems() {
        List<MenuItem> items = menuItemRepository.findAll();

        return buildMenuTree(items);
    }
}
