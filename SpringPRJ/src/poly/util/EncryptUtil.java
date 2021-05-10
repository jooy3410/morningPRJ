package poly.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class EncryptUtil {
	
	/*
	 * 암호화 알고리즘에 추가시킬 암호화 문구
	 * 
	 * 
	 * 일반적인 암호화 알고리즘 SHA-265을 통해서만 암호화 시킬 경우, 암호화 된 값만 보고 일반적인 비밀번호에 대한 값을 쉽게 
	 * 예측이 가능함 따라서, 암호화할때ㅐ 암호화 되는 갑에 추가적인 문자열을 붙여서 함께 암호화를 진행함
	 * 
	 */
	
	final static String addMessage = "PolyDAtaAnalysis"; //임의 값
	
	/*
	 *일반적인 암호화 알고리즘 sha-265를 통해서만 암호화 시킬 경우, 암호화 된 값만 보고 일반적인 비밀번호에 대한 값을 쉽게 예측이 가능함에 따라서,
	 *암호화할때 암호화 되는 값에 추가적인 문자열을 붙여서 함께 암호화를 진행함
	 * 
	 */
	
	
	/*
	 * AES128-CBC 암호화 알고리즘에 사용되는 초기 잭터와 암호화 키
	 */
	
	//초기 백터(16바이트 크기를 가지며, 16바이트 단위로 암호화시, 암호화할 총 길이가 16바이트가 되지 못하면 뒤에 추가하는 바이트)
	
	public static String encHashSHA265(String str) throws Exception{
		
		String res = ""; //암호화 결과값이 저장되는 변수
		String plantText = addMessage + str; // 암호화 시킬 값에 보안강화를 위해 임의값을 추가함
		
		try {
			
			//자바는 기본적으로 표준 암호화 알고리즘을 java.security 패키지를 통해 제공함
			// 여러 해시 알고리즘 중 가장 많이 사용되는 SHA-256f를 지원하고 있음
			
			MessageDigest sh = MessageDigest.getInstance("SHA-256");
			
			sh.update(plantText.getBytes());
			
			byte byteData[] = sh.digest();
			
			StringBuffer sb = new StringBuffer();
			
			for (int i = 0; i < byteData.length; i++) {
				sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
				
			}
			
			res = sb.toString();
			
			// 자바에서 제공하는 알고리즘이 아닌경우, 에러발생
		}	catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			
			res = "";
			
		}
		
		return res;
	}

}
