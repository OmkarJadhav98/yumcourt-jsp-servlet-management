package com.yumcourt.service;

import com.yumcourt.model.Menu;
import com.yumcourt.repository.MenuRepository;

import java.util.List;

public class MenuService {
    private static MenuRepository menuRepository;

    public MenuService(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

    public List<Menu> retrieveMenus() {
        return menuRepository.retrieveMenus();
    }

    public Menu getMenuById(long id) {
        return menuRepository.findById(id);
    }

    public void createMenu(Menu menu) {
        menuRepository.createMenu(menu);
    }

    public void updateMenu(Menu menu) {
        menuRepository.updateMenu(menu);
    }

    public void deleteMenu(long id) {
        menuRepository.deleteMenu(id);
    }

    public static Menu findMenuById(long findMenuId){ menuRepository.findById(findMenuId);
        return null;
    }
}
