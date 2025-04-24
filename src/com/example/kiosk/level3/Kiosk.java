package com.example.kiosk.level3;

import java.util.List;
import java.util.Scanner;

public class Kiosk {

    // 속성
    private List<MenuItem> menuItems;
    private Scanner scanner;

    // 생성자
    public Kiosk(List<MenuItem> menuItems) {
        this.menuItems = menuItems;
        this.scanner = new Scanner(System.in);
    }

    // 키오스크 시작 메서드
    public void start() {
        boolean running = true;
        while (running) {
            System.out.println("[ SHAKESHACK MENU ]");

            for (int i = 0; i < menuItems.size(); i++) {
                MenuItem item = menuItems.get(i);
                System.out.println((i + 1) + ". " + item.getName() + " | W " + item.getPrice() + " | " + item.getDescription());
            }
            System.out.println("0. 종료      | 종료");
            System.out.print("메뉴를 선택하세요: ");
            int choice = scanner.nextInt();

            if (choice == 0) {
                running = false;
            } else if (choice >= 1 && choice <= menuItems.size()) {
                System.out.println("선택한 메뉴: " + choice + ". " + menuItems.get(choice-1).getName());
                System.out.println("---------------------------");
            } else {
                System.out.println("메뉴의 숫자를 입력해주세요.");
            }
        }
    }

    public void close() {
        scanner.close();
        System.out.println("키오스크를 종료합니다.");
    }

}
