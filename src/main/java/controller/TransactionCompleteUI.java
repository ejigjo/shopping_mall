package controller;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import model.TransactionCompleteView;
import service.OrdersService;
import service.impl.OrderServiceImpl;
import until.SessionManager;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.List;

public class TransactionCompleteUI extends JFrame {
	private static final long serialVersionUID = 1L;
	private OrdersService ordersService = new OrderServiceImpl();

	public TransactionCompleteUI(String orderNo) {
		setTitle("交易完成");
		setSize(400, 400);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());

		// 訂單明細標題
		JLabel titleLabel = new JLabel("交易成功！您的訂單明細如下：", SwingConstants.CENTER);
		titleLabel.setFont(new Font("標楷體", Font.BOLD, 16));
		getContentPane().add(titleLabel, BorderLayout.NORTH);
		// 訂單明細列表
		DefaultListModel<String> listModel = new DefaultListModel<>();
		int totalPrice = 0;
		List<TransactionCompleteView> tList = ordersService.getOrderViewById(orderNo);
		for (TransactionCompleteView detail : tList) {
			totalPrice = totalPrice + detail.getPrice() * detail.getQuantity();
			listModel.addElement(detail.getPro_name() + " " + detail.getBrand() + " x " + detail.getQuantity() +" 單價: " + detail.getPrice());
		}
		JList<String> orderList = new JList<>(listModel);
		getContentPane().add(new JScrollPane(orderList), BorderLayout.CENTER);

		JPanel infoPanel = new JPanel(new GridLayout(4, 1));
		infoPanel.add(new JLabel("訂單編號: " + orderNo));
		infoPanel.add(new JLabel("訂購人: " + SessionManager.getSessionManager().getLoginUser().getName()));
		infoPanel.add(new JLabel("總金額: $" + totalPrice));
		add(infoPanel, BorderLayout.NORTH);
		// 確認按鈕
		JButton closeButton = new JButton("確認");
		closeButton.addActionListener(e ->{
			dispose();
			new MainViewUI().setVisible(true);
			});
		JPanel buttonPanel = new JPanel();
		buttonPanel.add(closeButton);
		getContentPane().add(buttonPanel, BorderLayout.SOUTH);

		setVisible(true);
	}
}