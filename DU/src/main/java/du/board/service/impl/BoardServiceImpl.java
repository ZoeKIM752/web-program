package du.board.service.impl;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import du.board.dao.BoardDAO;
import du.board.domain.BoardAttFileVO;
import du.board.domain.BoardCriteria;
import du.board.domain.BoardVO;
import du.board.service.BoardService;
import du.common.FileUploadUtil;
import du.common.Pagination;
import du.user.domain.UserVO;

@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
	private BoardDAO boardDAO;
	
	@SuppressWarnings("unused")
	private Logger logger = LoggerFactory.getLogger(BoardServiceImpl.class);
	
	@Override
	public List<BoardVO> selectBoardList(Pagination pagination, String title) {
		
		HashMap<String, Object> map = new HashMap<>();
		
		map.put("startList", pagination.getStartList());
		map.put("listSize", pagination.getListSize());
		map.put("title", title);
		
		return boardDAO.selectBoardList(map);
	}
	
	@Override
	public List<BoardVO> selectBoardList(BoardCriteria criteria) {
		return boardDAO.selectBoardListByCri(criteria);
	}

	@Override
	public int selectBoardListCnt(String title) {
		return boardDAO.selectBoardListCnt(title);
	}

	@Override
	public BoardVO selectBoard(long idx) {
		return boardDAO.selectBoard(idx);
	}

	@Override
	public void insertBoard(BoardVO boardVO, HttpSession session) throws Exception{
		UserVO user = (UserVO) session.getAttribute("USER");
		
		if(user == null) {
			return;
		}
		
		boardVO.setWriterId(user.getUserId());		
		boardDAO.insertBoard(boardVO);
		
		insertBoardAttFile(boardVO);
	}

	@Override
	public void deleteBoard(long idx) {
		boardDAO.deleteBoard(idx);
	}

	@Override
	public void updateBoard(BoardVO boardVO, HttpSession session) throws Exception {
		UserVO user = (UserVO) session.getAttribute("USER");
		
		if(user == null) {
			return;
		}
		
		boardVO.setWriterId(user.getUserId());	
		
		try {
			boardDAO.updateBoard(boardVO);
			
			updateBoardAttFile(boardVO);
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}

	@Override
	public BoardAttFileVO findBoardAttFile(BoardAttFileVO criteria) {
		return boardDAO.selectBoardAttFile(criteria);
	}

	@Override
	public void deleteBoardAttFile(BoardAttFileVO criteria) throws Exception {		
		BoardAttFileVO attFileVO = boardDAO.selectBoardAttFile(criteria);
		String fullAttFilePath = attFileVO.getFullAttFilePath();
		
		File file = new File(fullAttFilePath);
		if(file.exists() && !file.isDirectory()) {
			file.delete();
		}
	}
	
	private void updateBoardAttFile(BoardVO boardVO) throws Exception {
		String handleType = boardVO.getHandleType();
		BoardAttFileVO criteria = boardVO.getCriteria();
		boolean hasAttFile = boardVO.hasAttFile();
		
		switch(handleType) {
		case "fix":
			return;			
		case "del":
			if(hasAttFile) {
				deleteBoardAttFile(criteria);
				boardDAO.deleteBoardAttFile(criteria);		
			}
			break;
		case "chg":
			if(boardVO.hasAttFile()) {
				deleteBoardAttFile(criteria);
				boardDAO.deleteBoardAttFile(criteria);		
			}
			insertBoardAttFile(boardVO);
			break;
		default:
			
		}			
	}
	
	private void insertBoardAttFile(BoardVO boardVO) throws Exception{
		if(!boardVO.isExistAttFile()) {
			return;
		}
		
		BoardAttFileVO attFileVO = new BoardAttFileVO(boardVO);
		try {
			FileUploadUtil.uploadBoardAttFileVO(attFileVO);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		boardDAO.insertBoardAttFile(attFileVO);
	}
}
