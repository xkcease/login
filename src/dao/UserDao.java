package dao;

import org.springframework.stereotype.Repository;

import po.User;

@Repository
public interface UserDao {
	public abstract int insertUser(User user);
	public abstract User findUserByName(String username);
	public abstract User findUserByPhone(String phone);
	public abstract int updateUserForPassword(User user);
}
