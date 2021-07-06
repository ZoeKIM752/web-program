package du.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BoardController {
	
	@RequestMapping("/boardListPage.do")
	public String boardListPage() {
		return "board/boardList.jsp";
	}
	
}
