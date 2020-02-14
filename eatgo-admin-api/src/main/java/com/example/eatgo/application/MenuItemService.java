package com.example.eatgo.application;

import com.example.eatgo.domain.MenuItem;
import com.example.eatgo.domain.MenuItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MenuItemService {

    private MenuItemRepository menuItemRepository;

    @Autowired
    public MenuItemService(MenuItemRepository menuItemRepository) {
        this.menuItemRepository = menuItemRepository;
    }

    public List<MenuItem> getMenuItems(Long restaurantId) {
        List<MenuItem> menuItems = menuItemRepository.findByRestaurantId(restaurantId);

        return menuItems;
    }

    public void bulkUpdate(Long restaurantId, List<MenuItem> menuItems) {
/*        menuItems.stream()
                .filter(menuItem -> !menuItem.isDestroy())
                .forEach(menuItem -> {
                    menuItem.setRestaurantId(restaurantId);
                    menuItemRepository.save(menuItem);
                });
        menuItems.stream()
                .filter(menuItem -> menuItem.isDestroy())
                .forEach(menuItem -> {
                    menuItemRepository.deleteById((menuItem.getId()));
                });*/
        for(MenuItem menuItem:menuItems){
            update(restaurantId, menuItem);
        }

    }

    private void update(Long restaurantId, MenuItem menuItem) {
        if(menuItem.isDestroy()){
            menuItemRepository.deleteById((menuItem.getId()));
            return;
        }
        menuItem.setRestaurantId(restaurantId);
        menuItemRepository.save(menuItem);
    }

}
