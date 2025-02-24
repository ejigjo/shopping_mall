package dao.impl;

import java.util.List;

import dao.ProductDao;
import model.Product;
import until.SqlUntil;

public class ProductDaoImpl implements ProductDao {
	@Override
	public List<Product> selectProductByName(String name) {
		String sql ="SELECT * FROM product WHERE name LIKE ?";
		return SqlUntil.excuteQuery(sql, Product.class, "%" + name + "%");
	}

	@Override
	public List<Product> getProducts() {
		String sql = "SELECT * FROM product";
		return SqlUntil.excuteQuery(sql, Product.class);
	}

	@Override
	public boolean updateProduct(int id, int stock) {
		String sql = "UPDATE product SET stock = ? WHERE id = ?";
		return SqlUntil.excuteUpdate(sql, stock, id);
	}

	@Override
	public Product getProduct(int id) {
		String sql = "SELECT * FROM product WHERE id = ?";
		return SqlUntil.excuteSingleQuery(sql, Product.class, id);
	}

}
