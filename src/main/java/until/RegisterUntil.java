package until;

import java.util.regex.Pattern;

import javax.swing.JOptionPane;

import model.User;
import service.UserService;
import service.impl.UserServiceImpl;

public class RegisterUntil {
	private static UserService userService = new UserServiceImpl();

	public static boolean registerAction(String username, String password, String email, String name, String phone,
			String address) {
		if (username.isEmpty() || password.isEmpty() || email.isEmpty() || name.isEmpty() || phone.isEmpty()
				|| address.isEmpty()) {
			JOptionPane.showMessageDialog(null, "使用者資料不能為空", "錯誤", JOptionPane.ERROR_MESSAGE, null);
			return false;
		} else if (checkUsername(username)) {
			JOptionPane.showMessageDialog(null, "使用者已註冊", "錯誤", JOptionPane.ERROR_MESSAGE, null);
			return false;
		} else if (!checkPhone(phone)) {
			JOptionPane.showMessageDialog(null, "不符合電話格式", "錯誤", JOptionPane.ERROR_MESSAGE, null);
			return false;
		} else if (!checkEmail(email)) {
			JOptionPane.showMessageDialog(null, "不符合EMAIL格式", "錯誤", JOptionPane.ERROR_MESSAGE, null);
			return false;
		}

		if (registerSuccess(username, password, email, name, phone, address)) {
			JOptionPane.showMessageDialog(null, "註冊成功", "資訊", JOptionPane.INFORMATION_MESSAGE, null);
			return true;
		} else {
			JOptionPane.showMessageDialog(null, "註冊失敗", "註冊失敗請聯繫系統管理員", JOptionPane.ERROR_MESSAGE, null);
			return false;
		}

	}

	private static boolean registerSuccess(String username, String password, String email, String name, String phone,
			String address) {
		User user = new User();
		user.setEmail(email);
		user.setAddress(address);
		user.setName(name);
		user.setPhone(phone);
		user.setPassword(password);
		user.setUsername(username);
		return userService.registerUser(user);
	}

	private static boolean checkEmail(String email) {
		String regex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
		return Pattern.matches(regex, email);
	}

	private static boolean checkPhone(String phone) {
		String regex = "^09\\d{8}$";
		return Pattern.matches(regex, phone);
	}

	private static boolean checkUsername(String username) {
		User user = userService.getUserByUsername(username);
		if (user.getUsername() == null || user.getUsername().isEmpty()) {
			return false;
		}

		return true;
	}
}
