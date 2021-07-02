package du.user.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import du.user.dao.UserDAO;
import du.user.domain.UserVO;
import du.user.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDAO userMapper;
	
	@Override
	public Boolean selectPwd(String id, String password) {
		if(password.equals(userMapper.selectPwd(id))){
			return true;
		} else {
			return false;
		}
	}

	@Override
	public UserVO selectUserInfo(String id) {
		return userMapper.selectUserInfo(id);
	}
	
}
