package com.example.kiosk.level6;

import java.util.ArrayList;
import java.util.List;

public class Cart {

    // 속성
    private List<CartItem> cartItems;

    // 생성자
    public Cart() {
        this.cartItems = new ArrayList<>();
    }

    // 장바구니에 메뉴 추가
    public void addItem(MenuItem  menuItem) {
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

    // 장바구니 항목 getter
    public List<CartItem> getCartItems() {
        return cartItems;
    }

    // 장바구니 가격 총합
    public double calculateTotal() {
        double total = 0;
        for (CartItem item : cartItems) {
            total += item.getMenuItem().getPrice() * item.getQuantity();
        }
        return total;
    }

    // 카트 비어있는지 확인
    public boolean isCartEmpty() {
        return cartItems.isEmpty();
    }

}
