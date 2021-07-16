package du.board.controller;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import du.board.domain.BoardAttFileVO;
import du.board.domain.BoardCriteria;
import du.board.domain.BoardVO;
import du.board.service.BoardService;

@Controller
public class BoardController {

	@Autowired
	private BoardService boardService;
	
	@SuppressWarnings("unused")
	private Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	@RequestMapping(value = "/boardListPage.do", method = RequestMethod.GET)
	public ModelAndView boardListPage(BoardCriteria criteria) throws Exception {
		
		ModelAndView mav = new ModelAndView("board/boardList.jsp");
		
		String title = criteria.getTitle();
		int listCnt = boardService.selectBoardListCnt(title);
		
		criteria.pageInfo(listCnt);		
		mav.addObject("pagination", criteria);
		
		List<BoardVO> boardList = boardService.selectBoardList(criteria);
		mav.addObject("boardList", boardList);
		
		mav.addObject("title", title);
		
		return mav;
	}
	
	@RequestMapping("/boardWritePage.do")
	public String boardWritePage(HttpSession session) {
		return "board/boardWrite.jsp";
	}
	
	@RequestMapping(value ="/boardWrite.do", method = RequestMethod.POST)
	public String boardWrite(HttpSession session, @ModelAttribute BoardVO board) throws Exception{
		boardService.insertBoard(board, session);
				
		return "redirect:/boardListPage.do";
	}
	
	@RequestMapping("/boardInfoPage/{idx}.do")
	public ModelAndView boardInfoPage(@PathVariable("idx") long idx) {
		ModelAndView mav = new ModelAndView("board/boardInfo.jsp");
		
		BoardVO board = boardService.selectBoard(idx);
		mav.addObject("board", board);
		
		return mav;
	}
	
	@RequestMapping("/boardDelete.do")
	public String boardDelete(BoardVO boardVO) throws Exception {
		if(boardVO.hasAttFile()) {
			boardService.deleteBoardAttFile(boardVO.getCriteria());
		}		
		
		boardService.deleteBoard(boardVO.getIdx());
		
		return "redirect:/boardListPage.do";
	}

	@RequestMapping("/boardModifyPage.do")
	public ModelAndView boardModifyPage(long idx) {
		ModelAndView mav = new ModelAndView("board/boardModify.jsp");
		
		BoardVO board = boardService.selectBoard(idx);
		mav.addObject("board", board);
		
		return mav;
	}
	
	@RequestMapping(value = "/boardModify.do", method = RequestMethod.POST)
	public String boardModify(BoardVO board, HttpSession session) throws Exception {
		boardService.updateBoard(board, session);

		return "redirect:/boardInfoPage/" + Long.toString(board.getIdx()) + ".do";		
	}
	
	@RequestMapping(value = "/download/boardAttFile.do", method = RequestMethod.POST)
	public ModelAndView downloadBoardAttFile(BoardAttFileVO criteria, ModelAndView mv) throws Exception {		
		mv.setViewName("downloadView");		
		
		BoardAttFileVO attFileVO = boardService.findBoardAttFile(criteria);
		File file = new File(attFileVO.getFullAttFilePath());
		
		mv.addObject("downloadFile", file);
		mv.addObject("downloadFilename", attFileVO.getOldFilename());
		
		return mv;
	}
	
	
}
