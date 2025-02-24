package controller;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridLayout;

import javax.swing.DefaultCellEditor;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import model.CartView;
import model.Product;
import model.TempCart;
import model.User;
import service.CartService;
import service.ProductService;
import service.impl.CartServiceImpl;
import service.impl.ProductServiceImpl;
import until.MakeSerialNumber;
import until.SessionManager;

import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class MainViewUI extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton deleteCartButton = new JButton("刪除");
	private JButton buyButton = new JButton("確認購買");
	private JButton transInforButton = new JButton("交易紀錄");
	private JButton searchButton = new JButton("搜尋");
	private JButton addToCartButton = new JButton("加入購物車");
	private JTextField searchField;
	private JTable productTable;
	private DefaultTableModel productTableModel;
	private JTable cartTable;
	private DefaultTableModel cartTableModel;
	private JComboBox<Integer> quantityComboBox;
	private ProductService productService = new ProductServiceImpl();
	private CartService cartService = new CartServiceImpl();
	private List<Product> productList = productService.getProducts();

	public MainViewUI() {
		userUI();
		action();

	}

	private void action() {
		deleteCartButton.addActionListener(e -> {
			int selectedRow = cartTable.getSelectedRow();
			if (selectedRow != -1) {
				for (int i = 0; i < cartTable.getRowCount(); i++) {
					boolean selected = (boolean) cartTable.getValueAt(i, 0);
					if (selected) {
						int cartId = (int) cartTable.getValueAt(i, 6);
						cartService.deleteCartById(cartId);
					}
				}
				refreshCart();
				JOptionPane.showMessageDialog(null, "刪除成功！");
			} else {
				JOptionPane.showMessageDialog(null, "請選擇商品！");
			}
		});

		buyButton.addActionListener(e -> {
			List<TempCart> tempCarts = new ArrayList<>();
			int selectedRow = cartTable.getSelectedRow();
			if (selectedRow != -1) {
				for (int i = 0; i < cartTable.getRowCount(); i++) {
					boolean selected = (boolean) cartTable.getValueAt(i, 0);
					if (selected) {
						TempCart tempCart = new TempCart();
						tempCart.setPrice((int) cartTable.getValueAt(i, 3));
						tempCart.setQuantity((int) cartTable.getValueAt(i, 4));
						tempCart.setProductId((int) cartTable.getValueAt(i, 5));
						tempCart.setCartId((int) cartTable.getValueAt(i, 6));
						tempCarts.add(tempCart);
					}

				}
			} else {
				JOptionPane.showMessageDialog(null, "請選擇商品！");
				return;
			}
			dispose();
			new PaymentUI(tempCarts).setVisible(true);

		});

		searchButton.addActionListener(e -> {
			String searchStr = searchField.getText().toString().trim();
			productList = productService.selectProductByName(searchStr);
			showProductList(productList);
		});

		addToCartButton.addActionListener(e -> addToCart());

		transInforButton.addActionListener(e -> {
			new TransactionHistoryUI().setVisible(true);
			dispose();
		});
	}

	private void addToCart() {
		int selectedRow = productTable.getSelectedRow();
		if (selectedRow != -1) {
			int proId = (int) productTableModel.getValueAt(selectedRow, 5);
			int quantity = (int) quantityComboBox.getSelectedItem();
			User loginUser = SessionManager.getSessionManager().getLoginUser();
			String cartNo = MakeSerialNumber.makeCartNumber(cartService.getCartLastId());
			int stock = (int) productTableModel.getValueAt(selectedRow, 4);
			if (quantity > stock) {
				JOptionPane.showMessageDialog(null, "庫存不足喔!請重新選擇");
				return;
			}
			cartService.insertCart(cartNo, loginUser.getId(), proId, quantity, LocalDateTime.now());
			refreshCart();
		} else {
			JOptionPane.showMessageDialog(null, "請選擇商品！");
			return;
		}
	}

	private void refreshCart() {
		cartTableModel.setRowCount(0);
		showCartView();
	}

	private void showCartView() {
		List<CartView> cartViews = cartService.getCartView();
		if (cartViews.size() > 0) {
			for (CartView c : cartViews) {
				int price = c.getPrice() * c.getQuantity();
				cartTableModel.addRow(new Object[] { false, c.getName(), c.getBrand(), price, c.getQuantity(),
						c.getProduct_id(), c.getCart_id() });
			}
		}
	}

	private void showProductList(List<Product> productList) {
		// 商品列表
		String[] columns = { "商品名稱", "商品種類", "商品品牌", "價格", "庫存", "商品ID" };
		Object[][] data = new Object[productList.size()][6];
		for (int i = 0; i < productList.size(); i++) {
			data[i][0] = productList.get(i).getName();
			data[i][1] = productList.get(i).getCategory();
			data[i][2] = productList.get(i).getBrand();
			data[i][3] = productList.get(i).getPrice();
			data[i][4] = productList.get(i).getStock();
			data[i][5] = productList.get(i).getId();

		}
		if (productTableModel == null) {
			productTableModel = new DefaultTableModel(data, columns) {
				private static final long serialVersionUID = -3557581980252605017L;

				@Override
				public boolean isCellEditable(int row, int column) {
					return false;
				}
			};

			productTable = new JTable(productTableModel);

			productTable.getColumnModel().getColumn(1).setMaxWidth(70);
			productTable.getColumnModel().getColumn(2).setMaxWidth(70);
			productTable.getColumnModel().getColumn(3).setMaxWidth(70);
			productTable.getColumnModel().getColumn(4).setMaxWidth(50);
			// 將ID欄位隱藏
			productTable.getColumnModel().getColumn(5).setMinWidth(0);
			productTable.getColumnModel().getColumn(5).setMaxWidth(0);
			productTable.getColumnModel().getColumn(5).setPreferredWidth(0);

			getContentPane().add(new JScrollPane(productTable), BorderLayout.CENTER);
		} else {
			// **清空舊數據，然後重新加載新數據**
			productTableModel.setRowCount(0);
			for (Object[] row : data) {
				productTableModel.addRow(row);
			}
			productTableModel.fireTableDataChanged(); // 通知 UI 更新
		}
	}

	private void userUI() {
		this.setTitle("電商平台");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(750, 600);
		getContentPane().setLayout(new BorderLayout());

		// 搜尋區域
		JPanel searchPanel = new JPanel();
		searchPanel.add(new JLabel("搜尋: "));
		searchField = new JTextField(20);

		searchPanel.add(searchField);
		searchPanel.add(searchButton);
		getContentPane().add(searchPanel, BorderLayout.NORTH);

		JPanel quantityPanel = new JPanel();
		quantityPanel.add(new JLabel("選擇數量: "));
		quantityComboBox = new JComboBox<>(new Integer[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 });
		quantityPanel.add(quantityComboBox);

		quantityPanel.add(deleteCartButton);

		quantityPanel.add(buyButton);
		getContentPane().add(quantityPanel, BorderLayout.SOUTH);

		quantityPanel.add(transInforButton);

		showProductList(productList);

		// 購物車區域
		JPanel cartPanel = new JPanel(new BorderLayout());
		cartPanel.add(new JLabel("購物車"), BorderLayout.NORTH);

		String[] cartColumns = { "選擇", "商品名稱", "品牌", "價格", "數量", "商品id", "購物車id" };
		cartTableModel = new DefaultTableModel(cartColumns, 0) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return column == 0; // 只允許選擇框可編輯
			}
		};
		cartTable = new JTable(cartTableModel);
		cartTable.getColumnModel().getColumn(0).setCellEditor(new DefaultCellEditor(new JCheckBox()));
		cartTable.getColumnModel().getColumn(0).setCellRenderer(cartTable.getDefaultRenderer(Boolean.class));
		cartTable.getColumnModel().getColumn(0).setMaxWidth(40);
		cartTable.getColumnModel().getColumn(1).setMaxWidth(180);
		cartTable.getColumnModel().getColumn(2).setMaxWidth(50);
		cartTable.getColumnModel().getColumn(3).setMaxWidth(50);
		cartTable.getColumnModel().getColumn(4).setMaxWidth(40);
		cartTable.getColumnModel().getColumn(5).setMinWidth(0);
		cartTable.getColumnModel().getColumn(5).setMaxWidth(0);
		cartTable.getColumnModel().getColumn(5).setPreferredWidth(0);
		cartTable.getColumnModel().getColumn(6).setMinWidth(0);
		cartTable.getColumnModel().getColumn(6).setMaxWidth(0);
		cartTable.getColumnModel().getColumn(6).setPreferredWidth(0);
		cartPanel.add(new JScrollPane(cartTable), BorderLayout.CENTER);
		cartPanel.setPreferredSize(new Dimension(320, 500));

		cartPanel.add(addToCartButton, BorderLayout.SOUTH);

		getContentPane().add(cartPanel, BorderLayout.EAST);

		showCartView();

		this.setVisible(true);

	}

}
