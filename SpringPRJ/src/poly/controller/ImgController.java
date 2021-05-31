package poly.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import poly.dto.ImgDTO;
import poly.service.IImgService;
import poly.util.CmmUtil;
import poly.util.DateUtil;
import poly.util.FileUtil;

@Controller
public class ImgController {
	private Logger log = Logger.getLogger(getClass());
	
	/*
	 * 비즈니스 로직
	 */
	@Resource(name = "ImgService")
	private IImgService imgService;
	
	// 업로드되는 파일이 저장되는 기본 폴더 설정(자바에서 경로는 /로 표현함)
	final private String FLIE_UPLOAD_SAVE_PATH = "D:/morningPRJ/SpringPRJ/WebContent/resource/images";

	/*
	 * 이미지 파일 업로드 화면 호출
	 */
	@RequestMapping(value = "img/imageFileUpload")
	public String Index() {
		log.info(this.getClass().getName() + "이미지 파일 업로드 화면!!!");

		return "/img/ImageFileUpload";
	}
	
	/*
	 * 파일 업로드 및 DB저장
	 */
	@RequestMapping(value = "img/imgUpload.do")
	public String getReadforImageText(HttpServletRequest request, HttpServletResponse response, ModelMap model, HttpSession session,
			@RequestParam(value = "fileUpload") MultipartFile mf) throws Exception {

		log.info(this.getClass().getName() + "이미지 업로드 저장 시작!!");
		
		//글내용
		String note = CmmUtil.nvl("note");
		//업로드하는 실제 파일명
		//다운로드 기능 구현시, 임의로 정의된 파일명을 원래대로 만들어주기 위한 목적
		String org_file_name = mf.getOriginalFilename();
		
		// 파일 확장자 가져오기
		String ext = org_file_name.substring(org_file_name.lastIndexOf(".") + 1, org_file_name.length()).toLowerCase();
		
		String reg_id = (String)session.getAttribute("SS_EMAIL");
		
		//이미지 파일만 실행되도록 함
		if (ext.equals("jpeg") || ext.equals("jpg") || ext.equals("gif") || ext.equals("png")) {
			
						//웹서버에 저장되는 파일 이름

						// 업로드하는 파일 이름에 한글, 특수 문자들이 저장될 수 있기 때문에 강제로 영어와 숫자로 구성된 파일명으로 변경해서 저장한다.
						// 리눅스나 유닉스 등 운영체제는 다국어 지원에 취약하기 때문이다.
						String save_file_name = DateUtil.getDateTime("24hhmmss") + "." + ext;
						// 웹서버에 업로드한 파일 저장하는 물리적 경로
						String save_file_path = FileUtil.mkdirForDate(FLIE_UPLOAD_SAVE_PATH);
						String full_file_info = save_file_path + "/" + save_file_name;
						//이미지가 저장되는 폴더경로
						String save_folder_name = save_file_path.substring(save_file_path.length()-10);
						
						//정상적으로 값이 생성되었는지 로그 찍어서 확인
						log.info("full_file_info : " + full_file_info);
						log.info("save_file_name : " + save_file_name);
						log.info("save_file_Path : " + save_file_path);
						log.info("save_folder_name : " + save_folder_name);
						log.info("note : " + note);
						
						// 업로드되는 파일을 서버에 저장
						mf.transferTo(new File(full_file_info));

						ImgDTO pDTO = new ImgDTO();
						
						pDTO.setFull_file_info(full_file_info);// 저장경로+파일명
						pDTO.setSave_file_name(save_file_name);// 저장되는 파일명
						pDTO.setSave_file_path(save_file_path);// 저장되는 경로
						pDTO.setOrg_file_name(org_file_name);// 원본 파일명
						pDTO.setExt(ext);// 확장자		
						pDTO.setSave_folder_name(save_folder_name);// 저장되는 폴더경로		
						pDTO.setReg_id(reg_id);//등록자 ID
						pDTO.setNote(note);//등록자 ID
						
						//이미지 저장
						ImgDTO rDTO = imgService.uploadImg(pDTO);
						
						String msg = "";
						String url = "";
						if (rDTO != null) {
							msg = "들어갔습니다.";
							url = "/img/getimg.do";

							model.addAttribute("msg", msg);
							model.addAttribute("url", url);

							
						} else {
							msg = "등록에 실패했습니다.";
							url = "/img/ImageFileUpload.do";
							
							model.addAttribute("msg", msg);
							model.addAttribute("url", url);
					}
					}
					return "/redirect";
				}
	
	
	// 이미지 불러오기
	@RequestMapping(value = "img/getimg.do")
	public String getimg(HttpServletRequest request, ModelMap model) throws Exception {

		log.info(this.getClass().getName() + "이미지 불러오기 start!");

		List<ImgDTO> rList = imgService.getImg();
		
		log.info("rList : null? " + rList==null);
		
		if (rList == null) {
			rList = new ArrayList<>();
		}

		for (ImgDTO rDTO : rList) {
			
			log.info("rDTO :" + rDTO.getSeq());
			log.info("rDTO :" + rDTO.getFull_file_info());
			log.info("rDTO :" + rDTO.getSave_file_name());
			log.info("rDTO :" + rDTO.getSave_file_path());
			log.info("rDTO :" + rDTO.getSave_folder_name());
		}

		model.addAttribute("rList", rList);

		log.info(this.getClass().getName() + ".getimg end!");

		return "/img/getimg";
	}
	
	
	//이미지 게시판 상세페이지 불러오기
	@RequestMapping(value = "img/imgDetail.do", method = RequestMethod.GET)
	public String imgDetail(HttpServletRequest request,HttpServletResponse response, ModelMap model,
			HttpSession session) throws Exception {
		
		log.info(this.getClass().getName() + "imgDetail.do start!");
		
		String seq = request.getParameter("no");
		
		ImgDTO pDTO = new ImgDTO();
		pDTO.setSeq(seq);
		
		ImgDTO rDTO = imgService.getimgDetail(pDTO);
		
		if(rDTO == null) {
			rDTO = new ImgDTO();
					
		}
		
		model.addAttribute("rDTO", rDTO);
		
		log.info(this.getClass().getName() + "imgDetail.do end!!");
		
		return "/img/imgDetail";
		
	}
}
