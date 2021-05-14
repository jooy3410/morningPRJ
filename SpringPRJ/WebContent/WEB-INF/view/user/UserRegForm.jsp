<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
	<script type="text/javascript">
	//회원가입 정보의 유효성 체크하기
	function doRegUserCheck(f){
		
		if (f.email.value==""){
			alert("이메일을 입력해주세요. ");
			f.email.focus();
			return false;
		}
		
		if (f.password.value==""){
			alert("비밀번호를 입력해주세요. ");
			f.password.focus();
			return false;
		}
		
		if (f.password2.value==""){
			alert("비밀번호확인을 입력해주세요. ");
			f.password.focus();
			return false;
		}
		
		if (f.user_name.value==""){
			alert("사용할 이름을 입력해주세요. ")
			f.user_name.focus();
			return false;
		}
	}
</script>

</head>
<body>

<h1>회원가입화면</h1>
<form name="f" method="post" action="/user/insertUserInfo.do" onsubmit="return doRegUserCheck(this);">
 <div class="signup-content">
                    <form method="POST" id="signup-form" class="signup-form">
                        <h2 class="form-title">회원가입</h2>
                        
						<div class="form-group">
                            <input type="email" class="form-input" name="email"  placeholder="Email"/>
                        </div>
                           
                        <div class="form-group">
                            <input type="password" class="form-input" name="password" placeholder="비밀번호"/>
                            <span toggle="#password" class="zmdi zmdi-eye field-icon toggle-password"></span>
                        </div>
                        
                       
                           
                        <div class="form-group">
                           <input type="password" class="form-input" name="password2" placeholder="비밀번호 확인"/>
                             <span toggle="#password" class="zmdi zmdi-eye field-icon toggle-password"></span>
                        </div>
                        
                         
                        <div class="form-group">
                            <input type="text" class="form-input" name="user_name" placeholder="사용할 이름"/>
                        </div>
                         

                           
                        <div class="form-group">
                            <input type="submit" name="submit" class="form-submit" value="가입하기"/>
                            
                            
                            
                        </div>
                    </form>
                    
                </div>

</body>
</html>