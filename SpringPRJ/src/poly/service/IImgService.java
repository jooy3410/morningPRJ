package poly.service;

import java.util.List;

import poly.dto.ImgDTO;

public interface IImgService {

	//이미지 업로드
	ImgDTO uploadImg(ImgDTO pDTO)throws Exception;

	//이미지 불러오기
	List<ImgDTO> getImg() throws Exception;

	//이미지 상세페이지 불러오기
	ImgDTO getimgDetail(ImgDTO pDTO) throws Exception;
	




	

}
