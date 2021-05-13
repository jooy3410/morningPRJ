package poly.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import poly.dto.BoardDTO;
import poly.service.IBoardService;
import poly.util.CmmUtil;

@Controller
public class BoardController {

	@Resource(name = "BoardService")
	IBoardService boardService;

	private Logger log = Logger.getLogger(this.getClass());

	@RequestMapping(value = "board/boardList")
	public String boardList(ModelMap model) {
		log.info("boardList 시작!");
		List<BoardDTO> rList = boardService.getBoardList();

		if (rList == null) {
			rList = new ArrayList<>();
		}
		model.addAttribute("rList", rList);
		for (BoardDTO e : rList) {
				log.info("ename : " + e.getPost_no());
				log.info("empno : " + e.getPost_title());
			}
		
		log.info("BoardList시작");
		return "/board/boardList";
	}
	
	@RequestMapping(value="board/newpost")
	public String newpost() {
		log.info("newpost 시작!");
		
		return "/board/newpost";
	}
	
	@RequestMapping(value="board/dopost.do")
	public String dopost(HttpServletRequest request, ModelMap model) {
		
				
		String post_title = request.getParameter("post_title");
		String post_content = request.getParameter("post_content");
		String post_id = "jooyoung";
		BoardDTO pDTO = new BoardDTO();
		
		pDTO.setReg_id(post_id);
		pDTO.setPost_title(post_title);
		pDTO.setPost_content(post_content);
		int result = boardService.insertPost(pDTO);
		
		String msg = "";
		String url = "/board/boardList.do";
		if(result<1) {
			//실패
			msg = "게시글 등록에 실패했습니다.";
		}else {
			//성공
			msg = "등록에 성공했습니다.~~";
		}
		
		model.addAttribute("msg", msg);
		model.addAttribute("url", url);
		return "/redirect";
		
	}
	
	@RequestMapping("board/boardDetail")
	public String boardDetail(HttpServletRequest request, ModelMap model) {
		String post_no = request.getParameter("no");
		
		BoardDTO pDTO = new BoardDTO();
		pDTO.setPost_no(post_no);
		
		
		
		BoardDTO rDTO = boardService.getPostDetail(pDTO);
		log.info("title : " + rDTO.getPost_title());
		log.info("content : " + rDTO.getPost_content());
		
		model.addAttribute("rDTO", rDTO);
		
		return "/board/boardDetail";
	}
	
	@RequestMapping("board/deletePost")
	public String deletePost(HttpServletRequest request, ModelMap model) {
		String post_no = request.getParameter("no");
		
		BoardDTO pDTO = new BoardDTO();
		pDTO.setPost_no(post_no);
		
		int result = boardService.deletePost(pDTO);
		
		String msg = "";
		String url = "/board/boardList.do";
		if(result<1) {
			//실패
			msg = "게시글 삭제 했습니다.";
		}else {
			//성공
			msg = "삭제를 성공했습니다.~~";
		}
		
		model.addAttribute("msg", msg);
		model.addAttribute("url", url);
		
		return "/redirect";
		
	}
	
	@RequestMapping(value="board/editPost")
	public String editPost(HttpServletRequest request, ModelMap model) {
		
	
		log.info("editPost start");
		String post_no = request.getParameter("no");
		
		BoardDTO pDTO = new BoardDTO();
		pDTO.setPost_no(post_no);
		
		//게시글에 대한 자세한 정보를 불러오는 함수
		//게시글 보기 기능에서 이미 만들었으므로 재활용해준다.
		BoardDTO rDTO = boardService.getPostDetail(pDTO);
		model.addAttribute("rDTO", rDTO);
		
		return"/board/editPost";
				
		
		
	}
	
	@RequestMapping("board/doEditPost")
	public String doEditPost(HttpServletRequest request, ModelMap model) {
	
		String post_title =request.getParameter("post_title");
		String post_content =request.getParameter("post_content");
		String post_no =request.getParameter("post_no");
		
		log.info(post_title);
		log.info(post_content);
		log.info(post_no);
		BoardDTO pDTO = new BoardDTO();
		
		pDTO.setPost_title(post_title);
		pDTO.setPost_content(post_content);
		pDTO.setPost_no(post_no);
		
	
		int result = boardService.updatePost(pDTO);
		

		String msg = "";
		String url = "/board/boardDetail.do?no=" + post_no;
		if(result<1) {
			//실패
			msg = "게시글 수정 실패 했습니다.";
		}else {
			//성공
			msg = "게시글 수정 성공 했습니다.~~";
		}
		
		model.addAttribute("msg", msg);
		model.addAttribute("url", url);
		
		return "/redirect";
		
		
		
	}
	@RequestMapping(value="/board/searchList")
	public @ResponseBody List<BoardDTO> boardSearchList(HttpServletRequest request)throws Exception{
		log.info("searchList start !");
		
		String title = CmmUtil.nvl(request.getParameter("title"));
		log.info("title : " + title);
		
		BoardDTO bDTO = new BoardDTO();
		bDTO.setPost_title(title);
		
		List<BoardDTO> uList = boardService.getsearchList(bDTO);
		log.info("bList size : " + uList.size());
		
		log.info(this.getClass() + "/board/searchList");
		return uList;
		
	}
	
		
	}


