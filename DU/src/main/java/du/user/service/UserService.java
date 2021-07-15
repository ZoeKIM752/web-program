package du.user.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import du.user.domain.UserVO;

public interface UserService {
	
	public String selectPwd(String id);
	
	public UserVO selectUserInfo(String id);
	
	public Boolean loginProcess(HttpServletRequest request, UserVO user);
	
	public void updateUser(UserVO user);
	
	public void insertUser(UserVO user);
	
	public void deleteUser(HttpSession session);
	
}
