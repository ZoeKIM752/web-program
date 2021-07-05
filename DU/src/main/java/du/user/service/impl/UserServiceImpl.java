package du.user.service.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import du.user.dao.UserDAO;
import du.user.domain.UserVO;
import du.user.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDAO userDAO;
	
	@Override
	public Boolean selectPwd(String id, String password) {
		if(password.equals(userDAO.selectPwd(id))){
			return true;
		} else {
			return false;
		}
	}

	@Override
	public UserVO selectUserInfo(String id) {
		return userDAO.selectUserInfo(id);
	}

	@Override
	public Boolean loginProcess(HttpServletRequest request, UserVO user) {
		if(selectPwd(user.getUserId(), user.getPwd())){
			getSession(request, user);
			return true;
		}
		return false;
	}
	
	public boolean getSession(HttpServletRequest request, UserVO user) {
		 
	    // 해당 사용자의 로그인 정보를 가져온다.
	    UserVO userInfo = selectUserInfo(user.getUserId());
	    
	    if (userInfo != null) {
	                
	        // 세션을 가져온다. (가져올 세션이 없다면 생성한다.)
	        HttpSession httpSession = request.getSession(true);
	        
	        // "USER"로 sessionVO를 세션에 바인딩한다.
	        httpSession.setAttribute("USER", userInfo);
	    } else {
	        return false;
	    }
	    return true;
	}
}
