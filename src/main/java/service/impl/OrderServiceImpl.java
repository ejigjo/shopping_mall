package service.impl;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

import dao.CartDao;
import dao.OrderItemsDao;
import dao.OrdersDao;
import dao.ProductDao;
import dao.impl.CartDaoImpl;
import dao.impl.OrderDaoImpl;
import dao.impl.OrderItemsDaoImpl;
import dao.impl.ProductDaoImpl;
import model.Order;
import model.TempCart;
import model.TransactionCompleteView;
import service.CartService;
import service.OrdersService;
import until.MakeSerialNumber;
import until.SessionManager;
import until.SqlUntil;

public class OrderServiceImpl implements OrdersService {

	private OrdersDao ordersDao = new OrderDaoImpl();

	private OrderItemsDao orderItemsDao = new OrderItemsDaoImpl();

	private ProductDao productDao = new ProductDaoImpl();

	private CartDao cartDao = new CartDaoImpl();

	@Override
	public boolean processPayment(List<TempCart> tempCarts) {
		try {
			SqlUntil.beginTransaction();
			int totalPrice = tempCarts.stream().mapToInt(TempCart::getPrice).sum();
			String orderNo = MakeSerialNumber.makeOrderNumber(ordersDao.getOrderAll().size());
			
			int orderId = ordersDao.insertOrderGetId(orderNo, SessionManager.getSessionManager().getLoginUser().getId(),
					totalPrice, "已付款", LocalDateTime.now());
			if(orderId <=0) {
				throw new SQLException("新增Order失敗，無法獲取有效的訂單 ID");
			}
			
			for (TempCart c : tempCarts) {
				String orderItemNo = MakeSerialNumber.makeOrderItemNumber(orderItemsDao.getOrderItemAll().size());
				int totalSinglePrice = c.getPrice() * c.getQuantity();
				if(!orderItemsDao.insertOrderItem(orderItemNo, orderId, c.getProductId(), c.getQuantity(),
						totalSinglePrice)) {
					throw new SQLException("新增orderItems失敗");
				}
				
				int proStock = productDao.getProduct(c.getProductId()).getStock();
				if(!productDao.updateProduct(c.getProductId(), proStock - c.getQuantity())) {
					throw new SQLException("更新product失敗");
				}
				
				if(!cartDao.deleteCartById(c.getCartId())) {
					throw new SQLException("刪除cart失敗");
				}
				SqlUntil.commitTransaction();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			SqlUntil.rollbackTransaction();
			return false;
		} finally {
			SqlUntil.closeConnection();
		}
		return true;
	}

	@Override
	public List<Order> getOrderAll() {
		return ordersDao.getOrderAll();
	}

	@Override
	public int insertOrderGetId(String orderNo, int loginUser, int totalPrice, String status, LocalDateTime creatd) {
		return ordersDao.insertOrderGetId(orderNo, loginUser, totalPrice, status, creatd);
	}

	@Override
	public List<TransactionCompleteView> getOrderViewById(String orderNO) {
		return ordersDao.getOrderViewById(orderNO);
	}

	@Override
	public List<Order> getOrderbyId(int userId) {
		return ordersDao.getOrderbyId(userId);
	}

}
