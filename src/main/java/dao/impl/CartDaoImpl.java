package dao.impl;

import java.time.LocalDateTime;
import java.util.List;

import dao.CartDao;
import model.Cart;
import model.CartView;
import until.SessionManager;
import until.SqlUntil;

public class CartDaoImpl implements CartDao {
	
	@Override
	public int getCartLastId() {
		String sql = "SELECT MAX(id) FROM cart";
		return SqlUntil.excuteSingleQuery(sql, Integer.class);
	}
	
	@Override
	public boolean deleteCart(int id) {
		String sql = "DELETE FROM cart WHERE id = ?";
		return SqlUntil.excuteUpdate(sql, id);
	}
	
	@Override
	public List<CartView> getCartView(){
		String sql = "SELECT * FROM cart_view WHERE user_id = ?";
		return SqlUntil.excuteQuery(sql, CartView.class,SessionManager.getSessionManager().getLoginUser().getId());
	}
	
	@Override
	public List<Cart> getCartByUser(String username) {
		String sql = "SELECT * FROM cart cart.id LEFT JOIN  ";
		return null;
	}

	@Override
	public boolean insertCart(String cartNo, int userId, int productId, int quantity, LocalDateTime creatd) {
		String sql = "INSERT INTO cart(cart_no,user_id,product_id,quantity,created_at) VALUES(?,?,?,?,?)";
		return SqlUntil.excuteUpdate(sql, cartNo, userId, productId, quantity, creatd);
	}

	@Override
	public int getCartCount() {
		String sql = "SELECT * FROM cart";
		return SqlUntil.excuteQuery(sql, Cart.class).size();

	}
	@Override
	public boolean deleteCartById(int id) {
		String sql = "DELETE FROM cart WHERE id = ?";
		return SqlUntil.excuteUpdate(sql, id);
	}

}
