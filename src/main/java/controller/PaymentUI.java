package controller;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.List;
import javax.swing.*;

import model.TempCart;
import service.OrdersService;
import service.impl.OrderServiceImpl;
import until.MakeSerialNumber;

public class PaymentUI extends JFrame {
    private static final long serialVersionUID = 1L;

    private JTextField cardNumberField_1, cardNumberField_2, cardNumberField_3, cardNumberField_4;
    private JTextField cardHolderField, expiryMonthField, expiryYearField;
    private JPasswordField cvvField;
    private JButton payButton, backButton;
    
    private OrdersService ordersService = new OrderServiceImpl();
    private List<TempCart> tempCarts;

    public PaymentUI(List<TempCart> tempCarts) {
        this.tempCarts = tempCarts;
        setupUI();
        setupActions();
    }

    /** 設定 UI 介面 */
    private void setupUI() {
        setTitle("信用卡支付");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setLayout(null);

        // 信用卡號
        JLabel label = new JLabel("信用卡號碼:");
        label.setBounds(10, 10, 73, 25);
        getContentPane().add(label);

        cardNumberField_1 = new JTextField(); cardNumberField_1.setBounds(85, 10, 42, 25);
        cardNumberField_2 = new JTextField(); cardNumberField_2.setBounds(135, 10, 42, 25);
        cardNumberField_3 = new JTextField(); cardNumberField_3.setBounds(185, 10, 42, 25);
        cardNumberField_4 = new JTextField(); cardNumberField_4.setBounds(235, 10, 42, 25);
        
        getContentPane().add(cardNumberField_1);
        getContentPane().add(cardNumberField_2);
        getContentPane().add(cardNumberField_3);
        getContentPane().add(cardNumberField_4);

        // 持卡人姓名
        JLabel label_1 = new JLabel("持卡人姓名:");
        label_1.setBounds(10, 50, 80, 25);
        getContentPane().add(label_1);
        
        cardHolderField = new JTextField();
        cardHolderField.setBounds(95, 50, 150, 25);
        getContentPane().add(cardHolderField);

        // 到期日
        JLabel label_2 = new JLabel("有效年限:");
        label_2.setBounds(10, 90, 80, 25);
        getContentPane().add(label_2);
        
        expiryMonthField = new JTextField(); expiryMonthField.setBounds(95, 90, 40, 25);
        expiryYearField = new JTextField(); expiryYearField.setBounds(150, 90, 60, 25);
        
        getContentPane().add(expiryMonthField);
        getContentPane().add(expiryYearField);

        // CVV
        JLabel label_3 = new JLabel("安全碼 (CVV):");
        label_3.setBounds(10, 130, 100, 25);
        getContentPane().add(label_3);
        
        cvvField = new JPasswordField();
        cvvField.setBounds(120, 130, 60, 25);
        getContentPane().add(cvvField);

        // 按鈕
        backButton = new JButton("上一頁");
        backButton.setBounds(80, 200, 80, 30);
        getContentPane().add(backButton);

        payButton = new JButton("付款");
        payButton.setBounds(200, 200, 80, 30);
        getContentPane().add(payButton);

        setVisible(true);
    }

    /** 綁定事件 */
    private void setupActions() {
        payButton.addActionListener(e -> processPayment());
        backButton.addActionListener(e -> dispose());
    }

    /** 處理付款 */
    private void processPayment() {
        String cardNumber = getCardNumber();
        String cardHolder = cardHolderField.getText().trim();
        String expiryMonth = expiryMonthField.getText().trim();
        String expiryYear = expiryYearField.getText().trim();
        String cvv = new String(cvvField.getPassword()).trim();

        if (!isValidCardInfo(cardNumber, cardHolder, expiryMonth, expiryYear, cvv)) {
            return;
        }

        String orderNo = MakeSerialNumber.makeOrderNumber(ordersService.getOrderAll().size());

        if (ordersService.processPayment(tempCarts)) {
            JOptionPane.showMessageDialog(null, "付款成功！");
            dispose();
            new TransactionCompleteUI(orderNo).setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "付款失敗！");
        }
    }

    /** 獲取完整信用卡號 */
    private String getCardNumber() {
        return cardNumberField_1.getText().trim() + 
               cardNumberField_2.getText().trim() + 
               cardNumberField_3.getText().trim() + 
               cardNumberField_4.getText().trim();
    }

    /** 驗證信用卡資訊 */
    private boolean isValidCardInfo(String cardNumber, String cardHolder, String expiryMonth, String expiryYear, String cvv) {
        if (!isValidCreditCardNumber(cardNumber)) {
            JOptionPane.showMessageDialog(null, "無效的信用卡號碼！");
            return false;
        }
        if (cardHolder.isEmpty()) {
            JOptionPane.showMessageDialog(null, "持卡人姓名不能為空！");
            return false;
        }
        if (!isValidExpiryDate(expiryMonth, expiryYear)) {
            JOptionPane.showMessageDialog(null, "無效的到期日！");
            return false;
        }
        if (!cvv.matches("\\d{3,4}")) {
            JOptionPane.showMessageDialog(null, "CVV 必須是 3 或 4 位數！");
            return false;
        }
        return true;
    }

    /** 驗證信用卡號 */
    private boolean isValidCreditCardNumber(String cardNumber) {
        if (!cardNumber.matches("\\d{13,19}")) return false;
        int sum = 0;
        boolean alternate = false;
        for (int i = cardNumber.length() - 1; i >= 0; i--) {
            int digit = Character.getNumericValue(cardNumber.charAt(i));
            if (alternate) {
                digit *= 2;
                if (digit > 9) digit -= 9;
            }
            sum += digit;
            alternate = !alternate;
        }
        return (sum % 10 == 0);
    }

    /** 驗證有效日期 */
    private boolean isValidExpiryDate(String month, String year) {
        if (!month.matches("\\d{1,2}") || !year.matches("\\d{2,4}")) return false;
        int expMonth = Integer.parseInt(month);
        int expYear = Integer.parseInt(year.length() == 2 ? "20" + year : year);
        return !YearMonth.of(expYear, expMonth).isBefore(YearMonth.now());
    }
}
