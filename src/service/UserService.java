package service;

import po.DataJson;
import po.User;

public interface UserService {
	public abstract boolean register(User user, DataJson dataJson);
	public abstract DataJson login(User user);
	public abstract boolean forgetPassword(User user, DataJson dataJson);
	public abstract boolean updateUserForPassword(User user);
}
