package service.impl;

import java.util.List;

import dao.OrderItemsDao;
import dao.impl.OrderItemsDaoImpl;
import model.OrderItem;
import service.OrderItemsService;
import until.SqlUntil;

public class OrderItemsServiceImpl implements OrderItemsService{
	private OrderItemsDao orderItemsDao = new OrderItemsDaoImpl();
	
	@Override
	public boolean insertOrderItems(String orderItemsNo,int orderId,int productId,int quantity,int price) {
		return orderItemsDao.insertOrderItem(orderItemsNo, orderId, productId, quantity, price);
	}
	@Override
	public List<OrderItem> getOrderItemAll(){
		return orderItemsDao.getOrderItemAll();
	}
}
