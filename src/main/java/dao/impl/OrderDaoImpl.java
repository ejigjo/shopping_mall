package dao.impl;

import java.time.LocalDateTime;
import java.util.List;

import dao.OrdersDao;
import model.Order;
import model.TransactionCompleteView;
import until.SqlUntil;

public class OrderDaoImpl implements OrdersDao{
	
	@Override
	public int insertOrderGetId(String orderNo,int loginUser,int totalPrice ,String status,LocalDateTime creatd){
	String Sql = "INSERT INTO orders(order_no, user_id, total_price, status, created_at) VALUES(?,?,?,?,?)";
		return SqlUntil.excuteUpdateReturnId(Sql, orderNo,loginUser,totalPrice,status,creatd);
	}
	@Override
	public List<Order> getOrderAll(){
		String sql = "SELECT * FROM orders";
		return SqlUntil.excuteQuery(sql, Order.class);
	}
	
	@Override
	public List<Order> getOrderbyId(int userId){
		String sql = "SELECT * FROM orders WHERE user_id = ?";
		return SqlUntil.excuteQuery(sql, Order.class,userId);
	}
	
	@Override
	public List<TransactionCompleteView> getOrderViewById(String orderNO){
		String sql = "SELECT * FROM order_item_view WHERE order_no = ?";
		return SqlUntil.excuteQuery(sql, TransactionCompleteView.class, orderNO);
	}
	
}
