package service;

import java.time.LocalDateTime;
import java.util.List;

import model.Cart;
import model.CartView;

public interface CartService {
	
	public List<CartView> getCartView();

	public List<Cart> getCartByUser(String username);
	
	public boolean insertCart(String cartNo,int userId,int productId,int quantity,LocalDateTime creatd);

	public int getCartCount() ;

	boolean deleteCartById(int id);

	boolean deleteCart(int id);

	int getCartLastId();
}
