package com.example.kiosk.level7;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;

public class Kiosk {

    // 속성
    private List<Menu> menus;
    private Scanner scanner;
    private Cart cart;

    // 생성자
    public Kiosk() {
        this.menus = new ArrayList<>();
        this.scanner = new Scanner(System.in);
        this.cart = new Cart();
    }

    // getter
    public List<Menu> getMenus() {
        return menus;
    }
    public Scanner getScanner() {
        return scanner;
    }

    // setter
    public void setMenus(List<Menu> menus) {
        this.menus = menus;
    }
    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
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

    // 안전하게 choice(정수) 받기
    private int getValidInput() {
        int choice = -1;
        boolean validInput = false;

        while (!validInput) {
            try {
                choice = scanner.nextInt();
                validInput = true;
            } catch (InputMismatchException e) {
                System.out.println("숫자만 입력해주세요.");
                scanner.nextLine();
                System.out.print("다시 입력해주세요: ");
            }
        }

        scanner.nextLine();
        return choice;
    }

    // 카테고리 메뉴 출력
    private boolean homeMenu() {
        System.out.println("[ MAIN MENU ]");
        for (int i = 0; i < menus.size(); i++) {
            System.out.println((i + 1) + ". " + menus.get(i).getName());
        }
        System.out.println("0. 종료      | 종료");

        if (!cart.isCartEmpty()) {
            System.out.println("\n[ ORDER MENU ]");
            System.out.println("4. Orders");
            System.out.println("5. Cancel");
        }

        // 사용자 입력받기
        System.out.print("메뉴를 선택하세요: ");
        int choice = getValidInput();
        if (choice == 0) {
            return false;
        } else if (choice >= 1 && choice <= menus.size()) {
            // 선택된 메뉴 표시
            Menu selectedMenu = menus.get(choice - 1);
            mainMenu(selectedMenu);
        } else if (choice == 4 && !cart.isCartEmpty()) {
            order();
        } else if (choice == 5 && !cart.isCartEmpty()) {
            cancelOrder();
        } else {
            System.out.println("메뉴의 숫자를 입력해주세요.");
        }
        return true;
    }
    
    // 메뉴 출력
    private void mainMenu(Menu menu) {
        System.out.println("[ " + menu.getName().toUpperCase() +" MENU ]");
        List<MenuItem> menuItems = menu.getMenuItems();

        // 스트림을 사용하여 메뉴 아이템 출력
        IntStream.range(0, menuItems.size())
                        .forEach(i -> {
                            MenuItem item = menuItems.get(i);
                            String formatName = formatName(item.getName());
                            System.out.println((i + 1) + ". " + formatName + " | W " + item.getPrice() + " | " + item.getDescription());
                        });


        System.out.println("0. 뒤로가기");
        System.out.print("메뉴를 선택하세요: ");

        // 사용자 입력받기
        int choice = getValidInput();
        if (choice == 0) {
            // 뒤로 가기
        } else if (choice >= 1 && choice <= menuItems.size()) {
            System.out.println("선택한 메뉴: " + choice + ". " + menuItems.get(choice-1).getName());

            // 장바구니 추가 여부 확인
            addCartItemPropt(menuItems.get(choice-1));
        } else {
            System.out.println("메뉴의 숫자를 입력해주세요.");
        }
    }

    // 장바구니 추가 여부
    private void addCartItemPropt(MenuItem menuItem) {
        System.out.println("[ " + menuItem.getName() + " | W " + menuItem.getPrice() + " | " + menuItem.getDescription() + " ]");
        System.out.println("위 메뉴를 장바구니에 추가 하시겠습니까?");
        System.out.println("1. 확인      2. 취소");

        int choice = getValidInput();
        if (choice == 1) {
            cart.addItem(menuItem);
            System.out.println(menuItem.getName() + " 이(가) 장바구니에 추가되었습니다.");
            System.out.println("--------------------------------");
        } else if (choice == 2) {
            System.out.println(menuItem.getName() + " 이(가) 취소되었습니다.");
            System.out.println("--------------------------------");
        } else {
            System.out.println("잘못된 선택입니다. 장바구니 추가가 취소되었습니다.");
            System.out.println("--------------------------------");
        }
    }

    // 주문
    private void order() {
        System.out.println("아래와 같이 주문하시겠습니까?");
        System.out.println("[ Orders ]");

        // 스트림 사용하여 장바구니 출력
        cart.getCartItems().stream()
                .forEach(item -> {
                    MenuItem menuItem = item.getMenuItem();
                    String formatName = formatName(menuItem.getName());
                    System.out.println(formatName + " | W " + menuItem.getPrice() + " | " + menuItem.getDescription() + " | " + item.getQuantity() + " 개");
                });

        double total = cart.calculateTotal();

        System.out.println("[ Total ]");
        System.out.printf("W %.2f\n", total);

        System.out.println("1. 주문       2. 메뉴판");
        int orderChoice = getValidInput();

        if (orderChoice == 1) {
            UserType.discountInfo();
            int userTypeChoice = getValidInput();
            UserType userType = UserType.selectUserType(userTypeChoice);

            // 할인 적용된 총액
            double discoutedTotal = cart.calculateDiscountTotal(userType);
            System.out.println("주문이 완료되었습니다.");
            System.out.println("금액은 W " + discoutedTotal + " 입니다.");
            System.out.println("--------------------------------");

            cart.clearCart();
        } else if (orderChoice == 2) {
            System.out.println("메뉴판으로 돌아갑니다.");
            System.out.println("--------------------------------");
        } else {
            System.out.println("잘못된 입력입니다. 메뉴판으로 돌아갑니다.");
            System.out.println("--------------------------------");
        }

//        if (choice == 1) {
//            System.out.println("주문이 완료되었습니다.");
//            System.out.println("금액은 W " + total + " 입니다.");
//            System.out.println("--------------------------------");
//            cart.clearCart();
//        } else if (choice == 2){
//            System.out.println("--------------------------------");
//        } else {
//            System.out.println("메뉴의 숫자를 입력해주세요.");
//        }
    }

    // 주문 취소
    private void cancelOrder() {
        if (cart.isCartEmpty()) {
            System.out.println("취소할 주문이 없습니다.");
            return;
        }

        System.out.println("진행중인 주문을 취소하시겠습니까?");
        System.out.println("1. 확인      2. 취소");

        int choice = getValidInput();
        if (choice == 1) {
            cart.clearCart();
            System.out.println("진행중인 주문이 취소되었습니다.");
            System.out.println("--------------------------------");
        } else if (choice == 2) {
            System.out.println("주문 취소가 취소되었습니다.");
            System.out.println("--------------------------------");
        } else {
            System.out.println("잘못된 선택입니다.");
            System.out.println("--------------------------------");
        }
    }

    private String formatName(String name) {
        int length = 16;
        if (name.length() >= length) {
            return name;
        }

        for (int i = name.length(); i < length; i++) {
            name += " ";
        }
        return name;
    }

    // 자원 해제 메서드
    public void close() {
        scanner.close();
        System.out.println("키오스크를 종료합니다.");
    }

}
