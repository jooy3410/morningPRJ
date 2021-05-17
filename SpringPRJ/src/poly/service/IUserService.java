package poly.service;

import poly.dto.UserDTO;

public interface IUserService {

	//회원가입하기(회원정보 등록하기)
	int insertUserInfo(UserDTO pDTO) throws Exception;

}
