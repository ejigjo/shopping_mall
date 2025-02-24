package dao.impl;

import java.util.List;

import dao.OrderItemsDao;
import model.Order;
import model.OrderItem;
import until.SqlUntil;

public class OrderItemsDaoImpl implements OrderItemsDao{
	@Override
	public boolean insertOrderItem(String orderItemNo,int orderId,int productId,int quantity,int price) {
		String sql = "INSERT INTO order_items (order_items_no, order_id, product_id, quantity, price) VALUES(?,?,?,?,?)";
		
		return SqlUntil.excuteUpdate(sql, orderItemNo,orderId,productId,quantity,price);
	}
	
	@Override
	public List<OrderItem> getOrderItemAll(){
		String sql = "select * from order_items";
		return SqlUntil.excuteQuery(sql, OrderItem.class);
	}

}
