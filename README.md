# 線上購物商城
https://github.com/ejigjo/library_system/blob/main/images/title.png
## 專案名稱:  
### 線上購物系統
![Image](https://github.com/user-attachments/assets/0935fb9b-6510-4c52-af67-4896b444cae3)


## 功能介紹:

- 🔹 使用者登入 / 註冊
- 🔹 商品搜尋
- 🔹 購物車功能
- 🔹 支付假功能
  
![Image](https://github.com/user-attachments/assets/c5822c21-b9ea-4d5c-819a-f0b38cba3702)

## ERD  
![Image](https://github.com/user-attachments/assets/15c8f204-8c4c-4a44-b145-cce7de3f000e)
## 登入UML用例圖  
![Image](https://github.com/user-attachments/assets/11298808-12f5-4ad2-905a-7982ee8f33aa)
## 購物UML用例圖  
![Image](https://github.com/user-attachments/assets/a7738902-a51e-4ea1-bcb7-bea0796b756d)  
##信用卡資訊
卡號:4111 1111 1111 1111
卡片到期日:12/29
CVV:1234

## 專案結構  
shopping_mall
│── src/main/java
│   ├── controller
│   │   ├── LoginUI.java
│   │   ├── MainViewUI.java
│   │   ├── PaymentUI.java
│   │   ├── RegisterUI.java
│   │   ├── TransactionCompleteUI.java
│   │   ├── TransactionHistoryUI.java
│   │
│   ├── dao
│   │   ├── CartDao.java
│   │   ├── OrderItemsDao.java
│   │   ├── OrdersDao.java
│   │   ├── ProductDao.java
│   │   ├── UserDao.java
│   │
│   ├── dao.impl
│   │   ├── CartDaoImpl.java
│   │   ├── OrderDaoImpl.java
│   │   ├── OrderItemsDaoImpl.java
│   │   ├── ProductDaoImpl.java
│   │   ├── UserDaoImpl.java
│   │
│   ├── model
│   │   ├── Cart.java
│   │   ├── CartView.java
│   │   ├── OderView.java  (可能是 OrderView？)
│   │   ├── Order.java
│   │   ├── OrderItem.java
│   │   ├── Product.java
│   │   ├── TempCart.java
│   │   ├── TransactionCompleteView.java
│   │   ├── User.java
│   │
│   ├── service
│   │   ├── CartService.java
│   │   ├── OrderItemsService.java
│   │   ├── OrdersService.java
│   │   ├── ProductService.java
│   │   ├── UserService.java
│   │
│   ├── service.impl
│   │   ├── CartServiceImpl.java
│   │   ├── OrderItemsServiceImpl.java
│   │   ├── OrderServiceImpl.java
│   │   ├── ProductServiceImpl.java
│   │   ├── UserServiceImpl.java
│   │
│   ├── until  (應該是 utils？)
│   │   ├── LoginUntil.java
│   │   ├── MakeSerialNumber.java
│   │   ├── RegisterUntil.java
│   │   ├── SessionManager.java
│   │   ├── SqlUntil.java

