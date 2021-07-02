package du.user.service;

import du.user.domain.UserVO;

public interface UserService {
	
	public Boolean selectPwd(String id, String password);
	
	public UserVO selectUserInfo(String id);
}
