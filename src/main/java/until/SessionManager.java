package until;

import model.User;

public class SessionManager {
	private static SessionManager sessionManager;
	private User loginUser;

	public static SessionManager getSessionManager() {
		if (sessionManager == null) {
			 sessionManager = new SessionManager();
		}

		return sessionManager;
	}
	
	public void setLoginUser(User loginUser) {
		this.loginUser = loginUser;
	}
	
	public User getLoginUser() {
		return loginUser;
	}

}
