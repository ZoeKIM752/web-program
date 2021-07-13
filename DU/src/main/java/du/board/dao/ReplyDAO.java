package du.board.dao;

import java.util.List;

import du.board.domain.ReplyVO;

public interface ReplyDAO {
	
	public List<ReplyVO> selectReplyList(long boardIdx);

	public void insertReply(ReplyVO reply);

	public void deleteReply(long idx);

	public void updateReply(ReplyVO reply);

}
