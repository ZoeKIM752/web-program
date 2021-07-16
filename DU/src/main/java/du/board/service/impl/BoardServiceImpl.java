package du.board.service.impl;

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
	public void insertBoard(BoardVO boardVO, HttpSession session) {
		UserVO user = (UserVO) session.getAttribute("USER");
		
		if(user == null) {
			return;
		}
		
		boardVO.setWriterId(user.getUserId());		
		boardDAO.insertBoard(boardVO);
		
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

	@Override
	public void deleteBoard(long idx) {
		boardDAO.deleteBoard(idx);
	}

	@Override
	public void updateBoard(BoardVO board) {
		boardDAO.updateBoard(board);
	}
/*
	이 주석을 삭제하지 않는 이유는 강의시간에 FileUploadUtil 클래스를 만드는 것이 
	수업 범위를 넘어서지 않는지 의심스럽기 때문임...
	private void uploadBoardAttFile(BoardAttFileVO attFileVO) throws IllegalStateException, IOException {
		String fileStorePath = propertiesService.getString("fileStorePath");
		String dailyPath = LocalDate.now().toString();
		String filePath = fileStorePath + File.separator + dailyPath;
		
		File directory = new File(filePath);
		if(!directory.exists()) {
			directory.mkdir();
		}	
		attFileVO.setFilePath(filePath);
		
		MultipartFile multipartFile = attFileVO.getAttFile();
		String originalFilename = multipartFile.getOriginalFilename();
		attFileVO.setOldFilename(originalFilename);
		
		int pos = originalFilename.lastIndexOf(".");		
		String ext = originalFilename.substring(pos);
		String newFilenameBody = Generators.timeBasedGenerator().generate().toString();		
		String newFilename = newFilenameBody + ext;	
		attFileVO.setNewFilename(newFilename);
		
		File newFile = new File(filePath + File.separator + newFilename);
		multipartFile.transferTo(newFile);		
	}
*/

	@Override
	public BoardAttFileVO findBoardAttFile(BoardAttFileVO criteria) {
		return boardDAO.selectBoardAttFile(criteria);
	}
}
