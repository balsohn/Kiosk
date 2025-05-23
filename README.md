# 🍔 쉐이크쉑 키오스크 프로젝트

## 📋 프로젝트 소개

자바를 활용하여 쉐이크쉑 햄버거 키오스크 시스템을 개발한 프로젝트입니다. 단순한 메뉴 선택부터 시작해 객체지향 설계 원칙을 적용하고, 캡슐화, 클래스 간 협력 등 객체지향 프로그래밍의 핵심 개념을 적용해보았습니다.

이 프로젝트는 단계적으로 기능을 확장하며 자바의 다양한 기능과 객체지향 설계 원칙을 학습하기 위한 목적으로 진행되었습니다.

## 🛠️ 개발 과정 및 고민

### 첫 시작: 기본 키오스크 구현
처음에는 단순히 메뉴를 표시하고 사용자 입력을 처리하는 기본적인 키오스크 기능을 구현했습니다. 모든 코드가 하나의 클래스 파일에 있었고, 구조화되지 않았지만 기본 기능은 동작했습니다.

```java
// 처음 구현했던 단순한 방식
Scanner scanner = new Scanner(System.in);
System.out.println("[ SHAKESHACK MENU ]");
System.out.println("1. ShackBurger | W 6.9 | 토마토, 양상추, 쉑소스가 토핑된 치즈버거");
// ... 메뉴 출력 코드
```

### 객체지향 설계 적용
코드가 길어지고 복잡해지면서 유지보수의 어려움을 느꼈습니다. 이를 개선하기 위해 `MenuItem` 클래스를 만들어 메뉴 정보를 관리하게 했습니다.

```java
// MenuItem 클래스를 통해 메뉴 정보 캡슐화
public class MenuItem {
    private String name;
    private double price;
    private String description;
    
    // 생성자, getter, setter 등
}
```

### 구조 개선과 책임 분리
메뉴 표시와 입력 처리 등 모든 로직이 Main 클래스에 있는 것이 비효율적이라 생각했습니다. 키오스크 동작을 담당하는 `Kiosk` 클래스를 만들어 관심사를 분리했습니다.

```java
// Kiosk 클래스를 통해 키오스크 동작 로직 분리
public class Kiosk {
    private List<MenuItem> menuItems;
    
    public void start() {
        // 키오스크 실행 로직
    }
    
    // 다른 메서드들...
}
```

### 계층적 메뉴 구조 도입
실제 키오스크처럼 카테고리별 메뉴 구조가 필요하다고 생각했습니다. `Menu` 클래스를 추가해 '버거', '음료', '디저트' 등의 카테고리를 만들고, 각 카테고리 안에 메뉴 항목을 구성했습니다.

```java
// Menu 클래스를 통한 카테고리 구현
public class Menu {
    private String name;  // 카테고리 이름 (예: "Burgers")
    private List<MenuItem> menuItems;
    
    // 메서드들...
}
```

### 캡슐화 원칙 적용
프로그램이 커지면서 데이터 보호와 일관성 유지가 중요해졌습니다. 모든 클래스의 필드를 private으로 선언하고, getter/setter 메서드를 통해서만 접근하도록 캡슐화 원칙을 적용했습니다.

메뉴 이름을 일정한 길이로 맞추기 위한 formatName 메서드도 구현했는데, 이는 출력을 깔끔하게 하는 데 큰 도움이 되었습니다.

```java
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
```

### 장바구니 기능 구현
실제 키오스크처럼 메뉴를 선택하고 장바구니에 담는 기능이 필요하다고 생각했습니다. `Cart` 클래스를 추가하여 사용자가 선택한 메뉴 항목을 관리하고, 주문 처리와 취소 기능을 구현했습니다.

```java
// Cart 클래스를 통한 장바구니 관리
public class Cart {
    private List<CartItem> items;

    public void addItem(MenuItem menuItem) {
        // 장바구니에 아이템 추가 로직
    }

    public double calculateTotal() {
        // 총액 계산 로직
    }

    public void clearCart() {
        // 장바구니 비우기
    }
}
```

### Enum을 활용한 사용자 유형별 할인율 관리
사용자 유형(국가유공자, 군인, 학생, 일반인)에 따라 다른 할인율을 적용하기 위해 Enum을 활용했습니다. 이를 통해 추가적인 사용자 유형이 생겨도 쉽게 확장할 수 있는 구조를 만들었습니다.

```java
// UserType Enum을 통한 할인율 관리
public enum UserType {
    VETERAN("국가유공자", 0.1),    // 국가유공자 10% 할인
    SOLDIER("군인", 0.05),         // 군인 5% 할인
    STUDENT("학생", 0.03),         // 학생 3% 할인
    GENERAL("일반인", 0.0);        // 일반인 0% 할인

    private String description;
    private double discountRate;

    UserType(String description, double discountRate) {
        this.description = description;
        this.discountRate = discountRate;
    }

    // 할인 정보 출력 및 사용자 유형 선택 메서드
    public static void discountInfo() {
        System.out.println("할인 정보를 선택해주세요.");
        int index = 1;
        for (UserType type : values()) {
            System.out.printf("%d. %s : %.0f%%\n", index++, type.description, type.discountRate * 100);
        }
    }
}
```

### 람다와 스트림을 활용한 코드 개선
장바구니 관리, 메뉴 출력, 가격 계산 등의 기능에 람다와 스트림을 적용하여 코드의 가독성과 효율성을 높였습니다.

```java
// 스트림을 활용한 장바구니 가격 계산
public double calculateTotal() {
    return cartItems.stream()
            .mapToDouble(item -> item.getMenuItem().getPrice() * item.getQuantity())
            .sum();
}

// 스트림을 활용한 특정 메뉴 제거
public void removeItem(String menuName) {
    cartItems = cartItems.stream()
            .filter(item -> !item.getMenuItem().getName().equals(menuName))
            .collect(Collectors.toList());
}

// 스트림을 활용한 메뉴 출력
IntStream.range(0, menuItems.size())
    .forEach(i -> {
        MenuItem item = menuItems.get(i);
        String formatName = formatName(item.getName());
        System.out.println((i + 1) + ". " + formatName + " | W " + item.getPrice() + " | " + item.getDescription());
    });
```

## 💡 주요 기능

### 메뉴 관리 시스템
- 버거, 음료, 디저트 등 다양한 카테고리의 메뉴 관리
- 각 메뉴 항목의 이름, 가격, 설명 정보 저장 및 출력
- 계층적 메뉴 구조를 통한 직관적인 탐색

### 사용자 인터페이스
- 메인 메뉴와 카테고리별 하위 메뉴 제공
- 메뉴 선택 및 뒤로가기 기능
- 출력 포맷팅을 통한 정돈된 UI

### 객체지향 설계
- 클래스 간 책임 분리로 코드 관리 용이
- 캡슐화를 통한 데이터 보호
- 확장성을 고려한 구조 설계

### 장바구니 및 주문 시스템
- 선택한 메뉴를 장바구니에 추가하는 기능
- 장바구니 내역 확인 및 관리
- 주문 처리 및 결제 기능
- 장바구니 비우기 및 주문 취소 기능

### 사용자 유형별 할인 시스템
- Enum을 활용한 사용자 유형 관리(국가유공자, 군인, 학생, 일반인)
- 각 사용자 유형별 다른 할인율 적용
- 주문 시 사용자 유형 선택 및 할인된 가격 계산

### 함수형 프로그래밍 적용
- 람다와 스트림을 활용한 간결한 코드 작성
- 장바구니 항목 필터링 및 가격 계산에 스트림 API 활용
- 메뉴 출력 및 포맷팅에 함수형 프로그래밍 적용

## 🚀 시연 예시

### 기본 메뉴 탐색
```
[ MAIN MENU ]
1. Burgers
2. Drinks
3. Desserts
0. 종료 | 종료
메뉴를 선택하세요: 1

[ BURGERS MENU ]
1. ShackBurger    | W 6.9 | 토마토, 양상추, 쉑소스가 토핑된 치즈버거
2. SmokeShack     | W 8.9 | 베이컨, 체리 페퍼에 쉑소스가 토핑된 치즈버거
3. Cheeseburger   | W 6.9 | 포테이토 번과 비프패티, 치즈가 토핑된 치즈버거
4. Hamburger      | W 5.4 | 비프패티를 기반으로 야채가 들어간 기본버거
0. 뒤로가기
메뉴를 선택하세요: 2
```

### 장바구니 추가 및 주문
```
선택한 메뉴: SmokeShack | W 8.9 | 베이컨, 체리 페퍼에 쉑소스가 토핑된 치즈버거

"SmokeShack | W 8.9 | 베이컨, 체리 페퍼에 쉑소스가 토핑된 치즈버거"
위 메뉴를 장바구니에 추가하시겠습니까?
1. 확인        2. 취소
1

SmokeShack 이 장바구니에 추가되었습니다.

아래 메뉴판을 보시고 메뉴를 골라 입력해주세요.

[ MAIN MENU ]
1. Burgers
2. Drinks
3. Desserts
0. 종료      | 종료

[ ORDER MENU ]
4. Orders       | 장바구니를 확인 후 주문합니다.
5. Cancel       | 진행중인 주문을 취소합니다.
4

아래와 같이 주문 하시겠습니까?

[ Orders ]
SmokeShack | W 8.9 | 베이컨, 체리 페퍼에 쉑소스가 토핑된 치즈버거

[ Total ]
W 8.9

1. 주문      2. 메뉴판
1

할인 정보를 선택해주세요.
1. 국가유공자 : 10%
2. 군인     :  5%
3. 학생     :  3%
4. 일반     :  0%
3

주문이 완료되었습니다. 금액은 W 8.633 입니다.
```

## 🔧 문제 해결 과정

### 메뉴 출력 정렬 문제
메뉴 항목의 이름 길이가 다를 경우 출력이 잘 정렬되지 않는 문제가 있었습니다. 이를 해결하기 위해 formatName 메서드를 구현했습니다. 이 메서드는 이름이 지정된 길이보다 짧으면 공백을 추가해 일정한 길이가 되도록 했습니다.

### 사용자 입력 예외 처리
처음에는 사용자가 메뉴 번호가 아닌 다른 입력을 했을 때 프로그램이 충돌하는 문제가 있었습니다. 이를 해결하기 위해 입력값 검증 로직을 추가했습니다.

```java
if (choice >= 1 && choice <= menus.size()) {
    // 선택된 메뉴 처리
} else {
    System.out.println("메뉴의 숫자를 입력해주세요.");
}
```

### 장바구니 상태 관리 문제
장바구니에 상품이 있는 경우에만 주문 메뉴를 표시해야 했습니다. 이를 위해 장바구니의 상태를 확인하는 로직을 추가했고, 장바구니가 비어있을 때 주문 기능을 사용하려 하면 예외 처리를 구현했습니다.

```java
if (cart.isEmpty() && (choice == 4 || choice == 5)) {
    System.out.println("장바구니가 비어 있습니다. 메뉴를 먼저 추가해주세요.");
    continue;
}
```

또한 주문 완료 후 장바구니를 비우는 기능도 구현했습니다.

### 할인율 적용 관리 문제
처음에는 할인율을 하드코딩했으나, 확장성과 유지보수를 고려하여 Enum으로 리팩토링했습니다. 이를 통해 사용자 유형을 추가하거나 할인율을 변경할 때 코드 전체를 수정하지 않고도 Enum만 수정하면 되도록 구현했습니다.

### 메서드 체이닝과 스트림을 활용한 코드 가독성 개선
장바구니 항목 계산이나 필터링 로직이 복잡해지면서 for 루프와 if 문이 중첩되어 코드 가독성이 떨어지는 문제가 있었습니다. 이를 스트림과 람다 표현식을 활용하여 간결하고 읽기 쉬운 코드로 개선했습니다.

## 📂 프로젝트 구조

프로젝트는 기능별로 패키지를 나누어 단계별로 진행했습니다:

```
src/com/example/kiosk/
├── level1/ - 기본 콘솔 메뉴 구현
├── level2/ - MenuItem 클래스 도입
├── level3/ - Kiosk 클래스로 책임 분리
├── level4/ - 계층적 메뉴 구조 추가
├── level5/ - 캡슐화 강화 및 코드 구조 개선
├── level6/ - 장바구니 및 주문 시스템 구현
└── level7/ - Enum, 람다 & 스트림을 활용한 주문 및 장바구니 관리
```

## 🔄 개발 레벨 변화

- **Level 1**: 단순 콘솔 출력과 사용자 입력만 처리
- **Level 2**: MenuItem 클래스로 메뉴 정보 캡슐화
- **Level 3**: Kiosk 클래스로 키오스크 관련 로직 분리
- **Level 4**: 카테고리 별 메뉴 구조 구현
- **Level 5**: 접근 제어자와 getter/setter 적용해 캡슐화 강화
- **Level 6**: 장바구니 기능 추가 및 주문 프로세스 구현
- **Level 7**: Enum을 활용한 사용자 유형별 할인율 관리, 람다와 스트림을 활용한 코드 개선

## 💻 실행 방법

1. 프로젝트를 클론합니다.
2. IDE에서 프로젝트를 엽니다.
3. `src/com/example/kiosk/level7/Main.java` 파일을 실행합니다.
4. 콘솔 창에서 키오스크를 조작합니다.

