package service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.UserDao;
import po.DataJson;
import po.User;
import service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDao userDao;

	public UserDao getUserdao() {
		return userDao;
	}

	public void setUserdao(UserDao userdao) {
		this.userDao = userdao;
	}


	@Override
	public boolean register(User user, DataJson dataJson) {
		int state = 0;
		
		if(userDao.findUserByName(user.getUsername()) != null) {			// 用户名重复检测 			
			dataJson.setState(false);
			dataJson.setErrorIndex(0);
			dataJson.setErrorText("该用户名已存在");
			return false;
		}
		
		if(userDao.findUserByPhone(user.getPhone()) != null){				// 手机号重复检测
			dataJson.setState(false);
			dataJson.setErrorIndex(3);
			dataJson.setErrorText("该手机号已注册");
			return false;
		}
		
		try {
			state = userDao.insertUser(user);
		} catch(Exception e){
			state = 0;
		}
		
		if(state != 0) {			// 成功
			System.out.println("insert " + state + " data");
			return true;
		}
		else {					// 失败
			System.out.println("insert fail");
			return false;
		}
	}

	
	@Override
	public DataJson login(User user) {
		User userObj = null;
		DataJson dataJson = new DataJson();		
		
		userObj = userDao.findUserByName(user.getUsername());
		
		if(userObj != null) {			// 用户存在
			if(userObj.getPassword().equals(user.getPassword())) {		// 密码正确
				dataJson.setState(true);
				System.out.println(userObj);
				System.out.println("password match");
			}
			else {
				dataJson.setState(false);
				dataJson.setErrorIndex(1);
				dataJson.setErrorText("密码错误");
				System.out.println(dataJson);
			}
		}
		else {
			dataJson.setState(false);
			dataJson.setErrorIndex(0);
			dataJson.setErrorText("用户名错误");
			System.out.println(dataJson);
		
		}
		return dataJson;
	}

	
	@Override
	public boolean forgetPassword(User user, DataJson dataJson) {
		User userObj = null;
		User userTemp = null;
		int state = 0;
		
		userObj = userDao.findUserByName(user.getUsername());
		
		if(userObj != null) {					// 用户存在
			
			userTemp = userDao.findUserByPhone(user.getPhone());
			if(userTemp != null) {								// 手机号匹配检验
				if(!userObj.getPhone().equals(userTemp.getPhone())) {
					dataJson.setState(false);
					dataJson.setErrorIndex(1);
					dataJson.setErrorText("手机号错误");
					System.out.println(dataJson);
					return false;
				}
			}
			else {
				dataJson.setState(false);
				dataJson.setErrorIndex(1);
				dataJson.setErrorText("手机号未注册");
				System.out.println(dataJson);
				return false;
			}
			
			try {
				userObj.setPassword(user.getPassword());		
				state = userDao.updateUserForPassword(userObj);		// 重置密码
			} catch(Exception e){
				state = -1;
			}
		}
		else {
			dataJson.setState(false);
			dataJson.setErrorIndex(0);
			dataJson.setErrorText("用户名错误");
			System.out.println(dataJson);
			return false;
		}
		
		System.out.println(state);
		if(state != -1) {			// 成功
			System.out.println("update " + state + " data");
			return true;
		}
		else {					// 失败
			System.out.println("update fail");
			return false;
		}
	}

	
	@Override
	public boolean updateUserForPassword(User user) {
		return false;
	}
	
	
}
