package com.example.kiosk.level5;

import java.util.ArrayList;
import java.util.List;

public class Menu {

    // 속성
    private String name;
    private List<MenuItem> menuItems;

    // 생성자
    public Menu(String name) {
        this.name = name;
        this.menuItems = new ArrayList<>();
    }

    // 메뉴 추가
    public void addMenuItem(MenuItem menuItem) {
        menuItems.add(menuItem);
    }

    // getter
    public String getName() {
        return name;
    }
    public List<MenuItem> getMenuItems() {
        return menuItems;
    }

    // setter
    public void setName(String name) {
        this.name = name;
    }
    public void setMenuItems(List<MenuItem> menuItems) {
        this.menuItems = menuItems;
    }

    // 메뉴 아이템 출력 메서드
    public void showMenuItems() {
        System.out.println("[ " + name.toUpperCase() + " MENU ]");
        for (int i = 0; i < menuItems.size(); i++) {
            System.out.println((i + 1) + ". " + menuItems.get(i));
        }
        System.out.println("0. 뒤로가기");
    }

}
