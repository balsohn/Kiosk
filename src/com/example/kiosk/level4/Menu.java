package com.example.kiosk.level4;

import java.util.ArrayList;
import java.util.List;

public class Menu {

    // 속성
    String name;
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

    // 메뉴 이름 getter
    public String getName() {
        return name;
    }

    // 메뉴 getter
    public List<MenuItem> getMenuItems() {
        return menuItems;
    }

    // 메뉴 아이템 출력 메서드ㅏ
    public void showMenuItems() {
        System.out.println("[ " + name.toUpperCase() + " MENU ]");
        for (int i = 0; i < menuItems.size(); i++) {
            System.out.println((i + 1) + ". " + menuItems.get(i));
        }
        System.out.println("0. 뒤로가기");
    }

}
