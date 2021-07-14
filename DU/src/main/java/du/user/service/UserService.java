package du.user.service;

import javax.servlet.http.HttpServletRequest;

import du.user.domain.UserVO;

public interface UserService {
	
	public Boolean selectPwd(String id, String password);
	
	public UserVO selectUserInfo(String id);
	
	public Boolean loginProcess(HttpServletRequest request, UserVO user);
}
