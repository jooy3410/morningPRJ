package poly.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import poly.dto.UserDTO;

import poly.persistance.mapper.IUserMapper;

import poly.service.IUserService;
import poly.util.CmmUtil;

@Service("UserInfoService")
public class UserService implements IUserService{
	
	@Resource(name="UserInfoMapper")
	private IUserMapper userMapper;

	@Override
	public int insertUserInfo(UserDTO pDTO) throws Exception {
		
		//회원가입 성공 : 1, 아이디 중복으로인한 가입 취소 : 2, 기타 에러 발생 : 0
		int res = 0;
		
		//controller에서 값이 정상적으로 못 넘어오는 경우를 대비하기 위해 사용함
		if (pDTO ==null) {
			pDTO = new UserDTO();
//		}
		
		//회원 가입 중복 방지를 위해 DB에서 데이터 조회
//		UserInfoDTO rDTO = userInfoMapper.getUserExists(pDTO);
		
		//mapper에서 값이 정상적으로 못 넘어오는 경우를 대비하기 위해 사용함
//		if (rDTO == null) {
//			rDTO = new UserInfoDTO();
//		}
		
		//중복된 회원정보가 있는 경우, 결과값을 2로 변경하고, 더 이상 작업을진행하지 않음
//		if (CmmUtil.nvl(rDTO.getExists_yn()).equals("Y")) {
//			res = 2;
			
		//회원가입 중복이 아니므로, 회원가입 진행함
			
//		}else {
			
			//회원가입
			int success = userMapper.insertUserInfo(pDTO);
			
			//db에 데이터가 등록 되었다면,
			if (success > 0) {
				res = 1;
				
			}else {
				res = 0;
				
			}
		
		}
		
		return res;
	}
	

}
