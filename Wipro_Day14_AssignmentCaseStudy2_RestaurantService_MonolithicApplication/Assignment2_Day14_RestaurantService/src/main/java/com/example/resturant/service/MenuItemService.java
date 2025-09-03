package com.example.resturant.service;


import com.example.resturant.entity.MenuItem;
import com.example.resturant.entity.Restaurant;
import com.example.resturant.repository.MenuItemRepository;
import com.example.resturant.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuItemService {

    @Autowired
    private MenuItemRepository menuItemRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;

    public MenuItem addMenuItem(Long restaurantId, MenuItem menuItem) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new RuntimeException("Restaurant not found"));
        menuItem.setRestaurant(restaurant);
        return menuItemRepository.save(menuItem);
    }

    public List<MenuItem> getMenuItemsByRestaurant(Long restaurantId) {
        return menuItemRepository.findByRestaurantId(restaurantId);
    }

    public MenuItem updateMenuItem(Long id, MenuItem updatedItem) {
        MenuItem menuItem = menuItemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Menu item not found"));

        menuItem.setName(updatedItem.getName());
        menuItem.setDescription(updatedItem.getDescription());
        menuItem.setPrice(updatedItem.getPrice());
        return menuItemRepository.save(menuItem);
    }

    public void deleteMenuItem(Long id) {
        menuItemRepository.deleteById(id);
    }
}