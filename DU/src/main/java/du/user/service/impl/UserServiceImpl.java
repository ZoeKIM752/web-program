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
	public String selectPwd(String id) {
		return userDAO.selectPwd(id);
	}

	@Override
	public UserVO selectUserInfo(String id) {
		return userDAO.selectUserInfo(id);
	}

	@Override
	public Boolean loginProcess(HttpServletRequest request, UserVO user) {
		if(user.getPwd().equals(selectPwd(user.getUserId()))){
			setSession(request, user);
			return true;
		}
		return false;
	}
	
	public void setSession(HttpServletRequest request, UserVO user) {
		// 해당 사용자의 로그인 정보를 가져온다.
	    UserVO userInfo = selectUserInfo(user.getUserId());
	    
	    if (userInfo != null) {
	                
	        // 세션을 가져온다. (가져올 세션이 없다면 생성한다.)
	        HttpSession httpSession = request.getSession(true);
	        
	        // "USER"로 sessionVO를 세션에 바인딩한다.
	        httpSession.setAttribute("USER", userInfo);
	    } 
	}

	@Override
	public void updateUser(UserVO user) {
		if(user.getPwd().equals("")) {
			user.setPwd(selectPwd(user.getUserId()));
		}
		
		userDAO.updateUser(user);
	}

	@Override
	public void insertUser(UserVO user) {
		userDAO.insertUser(user);
	}

	@Override
	public void deleteUser(HttpSession session) {
		UserVO user = (UserVO) session.getAttribute("USER");
		userDAO.deleteUser(user.getUserId());
	}
}
