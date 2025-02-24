package service.impl;

import java.time.LocalDateTime;
import java.util.List;

import dao.CartDao;
import dao.impl.CartDaoImpl;
import model.Cart;
import model.CartView;
import service.CartService;
import until.SqlUntil;

public class CartServiceImpl implements CartService {

	private CartDao cartDao = new CartDaoImpl();
	@Override
	public boolean deleteCart(int id) {
		return cartDao.deleteCart(id);
	}

	@Override
	public List<CartView> getCartView() {
		return cartDao.getCartView();
	}

	@Override
	public int getCartCount() {
		return cartDao.getCartCount();
	}

	@Override
	public List<Cart> getCartByUser(String username) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public int getCartLastId() {
		return cartDao.getCartLastId();
	}

	@Override
	public boolean insertCart(String cartNo, int userId, int productId, int quantity, LocalDateTime creatd) {
		return cartDao.insertCart(cartNo, userId, productId, quantity, creatd);
	}

	@Override
	public boolean deleteCartById(int id) {
		return cartDao.deleteCartById(id);
	}

}
