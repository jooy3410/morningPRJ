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

<table border="1">

<col width="150px">
<col width="150px">
<col width="150px">
<col width="150px">
<tr>
	<td>아이디</td>
	<td><input type="text" name="email" style="width:150px"/></td>
</tr>

<tr>
	<td>비밀번호</td>
	<td><input type=password" name="password" style="width:150px"/></td>
</tr>
<tr>
	<td>비밀번호 확인</td>
	<td><input type=password2" name="password2" style="width:150px"/></td>
</tr>

<tr>
	<td>사용할 이름</td>
	<td><input type="text" name="user_name" style="width:150px"/></td>
</tr>

</table>
<input type="submit" value="회원가입" />
</form>


</body>
</html>