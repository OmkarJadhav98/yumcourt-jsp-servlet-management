package com.yumcourt.controller;

import com.yumcourt.model.Menu;
import com.yumcourt.model.Restaurant;
import com.yumcourt.repository.ContactRepository;
import com.yumcourt.repository.MenuRepository;
import com.yumcourt.repository.RestaurantRepository;
import com.yumcourt.service.MenuService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import java.io.IOException;
import java.util.List;

@WebServlet("/menus/*")
public class MenuController extends HttpServlet {
    private MenuService menuService;

    @Override
    public void init() throws ServletException {
        this.menuService = new MenuService(new MenuRepository());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathInfo = req.getPathInfo();
        if (pathInfo == null || pathInfo.equals("/")) {
            List<Menu> menus = menuService.retrieveMenus();
            resp.setContentType("application/json");
            resp.getWriter().write(menus.toString());
        } else {
            try {
                long id = Long.parseLong(pathInfo.substring(1));
                Menu menu = menuService.getMenuById(id);
                if (menu != null) {
                    resp.setContentType("application/json");
                    resp.getWriter().write(menu.toJson());
                } else {
                    resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Menu not found");
                }
            } catch (NumberFormatException e) {
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid menu ID");
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String description = req.getParameter("description");
        double price = Double.parseDouble(req.getParameter("price"));
        long restaurantId = Long.parseLong(req.getParameter("restaurantId"));
        boolean availability = Boolean.parseBoolean(req.getParameter("availability"));

        Restaurant restaurant = new RestaurantRepository(new ContactRepository()).findById(restaurantId);
        if (restaurant == null) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid restaurant ID");
            return;
        }

        Menu menu = new Menu(0, name, description, price, restaurant, availability);
        menuService.createMenu(menu);
        resp.setStatus(HttpServletResponse.SC_CREATED);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            long id = Long.parseLong(req.getParameter("id"));
            String name = req.getParameter("name");
            String description = req.getParameter("description");
            double price = Double.parseDouble(req.getParameter("price"));
            long restaurantId = Long.parseLong(req.getParameter("restaurantId"));
            boolean availability = Boolean.parseBoolean(req.getParameter("availability"));

            Restaurant restaurant = new RestaurantRepository(new ContactRepository()).findById(restaurantId);
            if (restaurant == null) {
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid restaurant ID");
                return;
            }

            Menu menu = new Menu(id, name, description, price, restaurant, availability);
            menuService.updateMenu(menu);
            resp.setStatus(HttpServletResponse.SC_OK);
        } catch (NumberFormatException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid input");
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            long id = Long.parseLong(req.getPathInfo().substring(1));
            menuService.deleteMenu(id);
            resp.setStatus(HttpServletResponse.SC_NO_CONTENT);
        } catch (NumberFormatException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid menu ID");
        }
    }
}
