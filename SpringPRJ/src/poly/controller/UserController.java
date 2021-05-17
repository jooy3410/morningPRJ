package poly.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import poly.dto.UserDTO;

import poly.service.IUserService;
import poly.util.CmmUtil;
import poly.util.EncryptUtil;

@Controller
public class UserController {
	private Logger log = Logger.getLogger(this.getClass());
	
	
	/*
	 * 비즈니스 로직(중요 로직을 수행하기 위해 사용되는 서비스를 메모리에 적재(싱글톤 패턴 전용됨)
	 */
	@Resource(name = "UserService")
	private IUserService userService;
	
	/**
	 * 회원가입화면으로 이동
	 */
	@RequestMapping(value="user/userRegForm")
	public String userReForm() {
		log.info(this.getClass().getName() + ".user/userRegForm ok");
		
		
		return "/user/UserRegForm";
		
		
	}
	
	/**
	 * 회원가입 로직 처리
	 */
	@RequestMapping(value="user/insertUserInfo")
	public String insertUserInfo(HttpServletRequest request, HttpServletResponse response,
			ModelMap model) throws Exception {
		
		log.info(getClass().getName() + ".insertUserInfo start!");
		
		//회원가입 결과에 대한 메시지 전달할 변수
		String msg = "";
		
		//웹(회원정보 입력화면)에서 받는 정보를 저장할 변수
		UserDTO pDTO = null;
		
		try {
			/*
			 * #############################################################
			 * 웹(회원정보 입력화면)에서 받는 정보를 String 변수에 저장 시작!!
			 * 
			 * 무조건 웹으로 받은 정보는 DTO에 저장하기 위해 임시로 String변수에 저장함
			 * #############################################################
			 */
			
			String email = CmmUtil.nvl(request.getParameter("email")); //아이디
			String password = CmmUtil.nvl(request.getParameter("password"));//비밀번호
			String user_name = CmmUtil.nvl(request.getParameter("user_name"));//닉네임
			/*
			 * ################################################################
			 * 웹(회원정보 입력화면)에서 받는 정보를 String 변수에 저장끝!
			 * 
			 * 무조건 웹으로 받은 정보는 DTO에 저장하기 위해 임시로 String 변수에 저장
			 * ################################################################
			 */
			
			/*
			 * ################################################################
			 * 반드시, 갑슬 받았으면, 꼭 로그를 찍어서 값이 제대로 들어오는지 파악해야함
			 * 반드시 작성할것
			 * ################################################################
			 *
			 */
			log.info("email : " + email);
			log.info("password : " + password);
			log.info("user_name : " + user_name);
			
			/*
			 * ############################################################
			 * 웹(회원정보 입력화면)에서 받는 정보를 DTO에 저장하기 시작!!
			 * 무조건 웹으로 받은 정보는 DTO에 저장해야 한다고 이해하기 권함
			 * ############################################################
			 */
			
			//웹(회원정보 입력화면)에서 받는 정보를 저장할 변수를 메모리에 올리기
			pDTO = new UserDTO();
			
		
			pDTO.setPassword(EncryptUtil.encHashSHA256(password));
			pDTO.setEmail(EncryptUtil.encAES128CBC(email));
			pDTO.setUser_name(user_name);
			
			
			/*
			 * 회원가입
			 */
			int res = userService.insertUserInfo(pDTO);
			
			if (res == 1) {
				msg = "회원가입되었습니다.";
				
				
			//추후 회원가입 입력화면에서 ajax를 활용해서 아이디 중복,  이메일 중복 체크하길바람
				
//			}else if (res == 2) {
//				msg = "이미 가입된 이메일 주소입니다.";
			
			}else {
				msg = "오류로 인해 회원가입이 실패하였습니다.";
				
			}
			
		}catch(Exception e) {
			//저장이 실패되면 사용자에게 보여줄 메시지
			msg = "실패하였습니다. : "+ e.toString();
			log.info(e.toString());
			e.printStackTrace();
			
		}finally {
			log.info(this.getClass().getName() + ".insertUserInfo end!");
			
			//회원가입 여부 결과 메시지 전달하기
			model.addAttribute("msg", msg);
			
			//회원가입 여부 결과 메시지 전달하기
			model.addAttribute("pDTO", pDTO);
			
			//변수 초기화 (메모리 효율화 시키기 위해 사용함)
			pDTO = null;
			
		}
		
		
				return "/user/Msg";
		
				
	}
	
	
	
	
	

}
