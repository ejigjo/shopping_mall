package service;

import java.util.List;

import model.OrderItem;

public interface OrderItemsService {

	boolean insertOrderItems(String orderItemsNo, int orderId, int productId, int quantity, int price);

	List<OrderItem> getOrderItemAll();

}
