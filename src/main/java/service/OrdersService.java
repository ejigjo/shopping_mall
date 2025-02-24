package service;

import java.time.LocalDateTime;
import java.util.List;

import model.Order;
import model.TempCart;
import model.TransactionCompleteView;

public interface OrdersService {

	List<Order> getOrderAll();

	int insertOrderGetId(String orderNo, int loginUser, int totalPrice, String status, LocalDateTime creatd);

	List<TransactionCompleteView> getOrderViewById(String orderNO);

	List<Order> getOrderbyId(int userId);

	public boolean processPayment(List<TempCart> tempCarts);
}
