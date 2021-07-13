package du.board.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import du.board.domain.ReplyVO;

public interface ReplyService {
	
	public List<ReplyVO> selectReplyList(long boardIdx);

	public void insertReply(HttpSession session, ReplyVO reply);

	public void deleteReply(long idx);

	public void deleteAllReply(long boardIdx);

	public void updateReply(ReplyVO reply);
	
}
