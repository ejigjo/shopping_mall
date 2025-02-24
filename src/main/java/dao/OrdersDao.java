package dao;

import java.time.LocalDateTime;
import java.util.List;

import model.Order;
import model.TransactionCompleteView;

public interface OrdersDao {

	List<Order> getOrderAll();

	int insertOrderGetId(String orderNo, int loginUser, int totalPrice, String status, LocalDateTime creatd);

	List<TransactionCompleteView> getOrderViewById(String orderNO);

	List<Order> getOrderbyId(int userId);

}
