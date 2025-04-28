package com.example.kiosk.level7;

public class CartItem {

    // 속성
    private MenuItem menuItem;
    private int quantity;

    // 생성자
    public CartItem(MenuItem menuItem) {
        this.menuItem = menuItem;
        this.quantity = 1;
    }

    // 수량 증가
    public void addQuantity() {
        quantity++;
    }

    // getter
    public MenuItem getMenuItem() {
        return menuItem;
    }
    public int getQuantity() {
        return quantity;
    }

    // setter
    public void setMenuItem(MenuItem menuItem) {
        this.menuItem = menuItem;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

}
