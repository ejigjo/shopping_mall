package dao;

import java.util.List;

import model.Order;
import model.OrderItem;

public interface OrderItemsDao {

	boolean insertOrderItem(String orderItemNo, int orderId, int productId, int quantity, int price);

	List<OrderItem> getOrderItemAll();

}
