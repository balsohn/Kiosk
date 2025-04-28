package com.example.kiosk.level7;

public enum UserType {
    VETERAN("국가유공자", 0.1),    // 국가유공자
    SOLDIER("군인", 0.05),         // 군인
    STUDENT("학생", 0.03),          // 학생
    GENERAL("일반인", 0.0);           // 일반인

    private String description;
    private double discountRate;

    UserType(String description, double discountRate) {
        this.description = description;
        this.discountRate = discountRate;
    }

    // getter
    public double getDiscountRate() {
        return discountRate;
    }

    // 할인 출력 메서드
    public static void discountInfo() {
        System.out.println("할인 정보를 선택해주세요.");
        int index = 1;
        for (UserType type : values()) {
            System.out.printf("%d. %s : %.0f%%\n", index++, type.description, type.discountRate * 100);
        }
    }

    public static UserType selectUserType(int choice) {
        return values()[choice - 1];
    }
}
