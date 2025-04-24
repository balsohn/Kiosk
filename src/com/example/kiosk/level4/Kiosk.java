package com.example.kiosk.level4;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Kiosk {

    // 속성
    private List<Menu> menus;
    private Scanner scanner;

    // 생성자
    public Kiosk() {
        this.menus = new ArrayList<>();
        this.scanner = new Scanner(System.in);
    }

    // Menu 추가 메서드
    public void addMenu(Menu menu) {
        menus.add(menu);
    }

    // 키오스크 시작 메서드
    public void start() {
        boolean running = true;
        while (running) {
            // 카테고리 메뉴 출력
            running = homeMenu();
        }
    }

    // 카테고리 메뉴 출력
    public boolean homeMenu() {
        System.out.println("[ MAIN MENU ]");
        for (int i = 0; i < menus.size(); i++) {
            System.out.println((i + 1) + ". " + menus.get(i).getName());
        }
        System.out.println("0. 종료      | 종료");

        // 사용자 입력받기
        System.out.print("메뉴를 선택하세요: ");
        int choice = scanner.nextInt();
        if (choice == 0) {
            return false;
        } else if (choice >= 1 && choice <= menus.size()) {
            // 선택된 메뉴 표시
            Menu selectedMenu = menus.get(choice - 1);
            mainMenu(selectedMenu);
        } else {
            System.out.println("메뉴의 숫자를 입력해주세요.");
        }
        return true;
    }
    
    // 메뉴 출력
    public void mainMenu(Menu menu) {
        System.out.println("[ " + menu.getName().toUpperCase() +" MENU ]");
        List<MenuItem> menuItems = menu.getMenuItems();

        for (int i = 0; i < menuItems.size(); i++) {
            MenuItem item = menuItems.get(i);
            String formatName = formatName(item.getName());
            System.out.println((i + 1) + ". " + formatName + " | W " + item.getPrice() + " | " + item.getDescription());
        }
        System.out.println("0. 뒤로가기");
        System.out.print("메뉴를 선택하세요: ");

        // 사용자 입력받기
        int choice = scanner.nextInt();
        if (choice == 0) {
            // 뒤로 가기
        } else if (choice >= 1 && choice <= menuItems.size()) {
            System.out.println("선택한 메뉴: " + choice + ". " + menuItems.get(choice-1).getName());
            System.out.println("---------------------------");
        } else {
            System.out.println("메뉴의 숫자를 입력해주세요.");
        }
    }

    public String formatName(String name) {
        int length = 16;
        if (name.length() >= length) {
            return name;
        }

        for (int i = name.length(); i < length; i++) {
            name += " ";
        }
        return name;
    }

    public void close() {
        scanner.close();
        System.out.println("키오스크를 종료합니다.");
    }

}
