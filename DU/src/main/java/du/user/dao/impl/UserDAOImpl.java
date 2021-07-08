package du.user.dao.impl;

import org.springframework.stereotype.Repository;

import du.user.dao.UserDAO;
import du.user.domain.UserVO;
import egovframework.rte.psl.dataaccess.EgovAbstractMapper;

@Repository
public class UserDAOImpl extends EgovAbstractMapper implements UserDAO {
	@Override
	public String selectPwd(String id) {
		return selectOne("User.selectPwd", id);
	}
	
	@Override
	public UserVO selectUserInfo(String id) {
		return selectOne("User.selectUserInfo", id);
	}
}
