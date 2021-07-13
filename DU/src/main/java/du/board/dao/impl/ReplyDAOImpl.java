package du.board.dao.impl;

import org.springframework.stereotype.Repository;

import du.board.dao.ReplyDAO;
import du.board.domain.ReplyVO;
import egovframework.rte.psl.dataaccess.EgovAbstractMapper;

@Repository
public class ReplyDAOImpl extends EgovAbstractMapper implements ReplyDAO {

	@Override
	public void insertReply(ReplyVO reply) {
		insert("Reply.insertReply", reply);
	}

}
