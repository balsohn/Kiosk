package com.example.kiosk.level7;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Cart {

    // 속성
    private List<CartItem> cartItems;

    // 생성자
    public Cart() {
        this.cartItems = new ArrayList<>();
    }

    // 장바구니에 메뉴 추가
    public void addItem(MenuItem menuItem) {
        // 이미 장바구니에 있는지 확인
        for (CartItem item : cartItems) {
            if(item.getMenuItem().getName().equals(menuItem.getName())) {
                item.addQuantity();
                return;
            }
        }
        // 없으면
        cartItems.add(new CartItem(menuItem));
    }

    // 장바구니 비우기
    public void clearCart() {
        cartItems.clear();
    }

    // 특정 메뉴 제거
    public void removeItem(String menuName) {
        cartItems = cartItems.stream()
                .filter(item -> !item.getMenuItem().getName().equals(menuName))
                .collect(Collectors.toList());
    }

    // 할인율 적용
    public double calculateDiscountTotal(UserType userType) {
        double total = calculateTotal();
        return total * (1 - userType.getDiscountRate());
    }

    // 장바구니 항목 getter
    public List<CartItem> getCartItems() {
        return cartItems;
    }

    // 장바구니 가격 총합
    public double calculateTotal() {
        return cartItems.stream()
                .mapToDouble(item -> item.getMenuItem().getPrice() * item.getQuantity())
                .sum();
    }

    // 카트 비어있는지 확인
    public boolean isCartEmpty() {
        return cartItems.isEmpty();
    }

}
