package du.board.service;

import javax.servlet.http.HttpSession;

import du.board.domain.ReplyVO;

public interface ReplyService {

	public void insertReply(HttpSession session, ReplyVO reply);
	
}
