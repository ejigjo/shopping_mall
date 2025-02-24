package controller;

import javax.swing.*;

import model.Order;
import service.OrdersService;
import service.impl.OrderServiceImpl;
import until.SessionManager;
import java.util.List;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TransactionHistoryUI extends JFrame {
	private static final long serialVersionUID = 1L;
	private JTable transactionTable;
	private OrdersService ordersService = new OrderServiceImpl();

	public TransactionHistoryUI() {
		setTitle("交易紀錄");
		setSize(600, 400);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());

		// 訂單標題
		JLabel titleLabel = new JLabel("交易紀錄", SwingConstants.CENTER);
		titleLabel.setFont(new Font("標楷體", Font.BOLD, 18));
		getContentPane().add(titleLabel, BorderLayout.NORTH);
		List<Order> orderList = ordersService.getOrderbyId(SessionManager.getSessionManager().getLoginUser().getId());
		// 訂單表格
		String[] columnNames = { "訂單編號", "日期", "總金額", "狀態" ,"orderId"};
		Object[][] data = new Object[orderList.size()][5];
		for (int i = 0; i < orderList.size(); i++) {
	        Order order = orderList.get(i);
            data[i][0] = order.getOrderNo();
            data[i][1] = order.getCreatedAt();
            data[i][2] = "$" + order.getTotalPrice();
            data[i][3] = "已付款";
            data[i][4] = order.getOrderNo();
		}

		transactionTable = new JTable(data, columnNames);
		getContentPane().add(new JScrollPane(transactionTable), BorderLayout.CENTER);
		transactionTable.getColumnModel().getColumn(4).setMinWidth(0);
		transactionTable.getColumnModel().getColumn(4).setMaxWidth(0);
		transactionTable.getColumnModel().getColumn(4).setPreferredWidth(0);
		// 顯示明細按鈕
		JButton showDetailsButton = new JButton("顯示明細");
		showDetailsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedRow = transactionTable.getSelectedRow();
				if (selectedRow != -1) {
					String orderNumber = (String) transactionTable.getValueAt(selectedRow, 4);
					 new TransactionCompleteUI(orderNumber).setVisible(true);
				} else {
					JOptionPane.showMessageDialog(null, "請選擇一筆訂單！");
				}
			}
		});
		JPanel buttonPanel = new JPanel();
		buttonPanel.add(showDetailsButton);
		getContentPane().add(buttonPanel, BorderLayout.SOUTH);

		setVisible(true);
	}
}