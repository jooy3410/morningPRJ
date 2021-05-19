<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="poly.util.CmmUtil" %>
<%@ page import="poly.dto.UserDTO" %>
<%
//Controller로 부터 전달받은 데이터
String msg = CmmUtil.nvl((String)request.getAttribute("msg"));

//Controller로부터 전달받은 웹(회원정보입력화면)으로부터 입력받은 데이터(회원아이다, 이름, 이메일, 주소 등)
UserDTO pDTO = (UserDTO)request.getAttribute("pDTO");

if (pDTO==null){
	pDTO = new UserDTO();
	
}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	alert("<%=msg%>");
</script>
</head>
<body>

<%=CmmUtil.nvl(pDTO.getEmail()) %>님의 회원가입을 축하드립니다.

</body>
</html>