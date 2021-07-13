package du.board.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import du.board.domain.ReplyVO;
import du.board.service.ReplyService;

@Controller
public class ReplyController {

	@Autowired
	private ReplyService replyService;
	
	@RequestMapping("/replyWrite.do")
	public String replyWrite(HttpSession session, @ModelAttribute ReplyVO reply) {
		replyService.insertReply(session, reply);
		
		return "redirect:/boardInfoPage/"+Long.toString(reply.getBoardIdx())+".do";
	}
}
