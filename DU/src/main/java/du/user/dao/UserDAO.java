package du.user.dao;

import du.user.domain.UserVO;

public interface UserDAO {
	public String selectPwd(String id);
	
	public UserVO selectUserInfo(String id);
}
