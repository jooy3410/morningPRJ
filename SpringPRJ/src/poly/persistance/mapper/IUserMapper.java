package poly.persistance.mapper;

import config.Mapper;
import poly.dto.UserDTO;

@Mapper("UserMapper")
public interface IUserMapper {

	//회원가입하기(회원정보 등록하기)
	int insertUserInfo(UserDTO pDTO) throws Exception;

	

}
