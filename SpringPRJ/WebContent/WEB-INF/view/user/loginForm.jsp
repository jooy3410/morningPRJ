<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form name="f" method="post" action="/user/getUserLoginCheck.do" >

<h1>미라클모닝</h1>
	
	<div>
		<input type="text" id="email" class="form-input" name="email"
		placeholder="이메일" />
	</div>
	
	<div>
		<input type="password" id="password" class="form-input" name="password"
		placeholder="비밀번호" /> 
	</div>
	
	<div class="form-group">
		<input type="submit" class="form-submit" value="LOGIN" />
	</div>
			
					
</form>

</body>
</html>