<%@page import="static poly.util.CmmUtil.nvl"%>
<%@page import="poly.dto.BoardDTO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%
    
    	List<BoardDTO> rList = (List<BoardDTO>)request.getAttribute("rList");
    
    %>
<!DOCTYPE html>
<html>
<head>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>


<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>


<div style="margin:auto;width:800px;">
<table border="1" style="width:100%;" id="searchResult">
<thead>
<tr>
	<td>글번호</td>
	<td>제목</td>
	<td>게시일</td>
	<td>게시자</td>
</tr>
</thead>
<tbody>
 <% for(BoardDTO e : rList) {%>
<tr>
	<td><%=nvl(e.getPost_no()) %></td>
	<td><a href="/board/boardDetail.do?no=<%=e.getPost_no()%>"><%=nvl(e.getPost_title()) %></a></td>
	<td><%=nvl(e.getReg_dt()) %></td>
	<td><%=nvl(e.getReg_id())%></td>
	
</tr>
<%} %>
</tbody>
</table>
<div style="width:100%;text-align:right;margin-top:5px;">

<button type=button onclick="location.href='/board/newpost.do'">새 글</button>
</div>
</div>

<br><br>
<!-- ajax 시작 -->
<div style="display: inline-block; margin: 0;">
	<div class="input-group">
		<input type="text" placeholder="이름" name="title" id="title" style="height: 40px !impoertant;"/>
		<input type="button" onClick="JavaScript:search();" value="검색"/>
	</div>
</div>
<!-- ajax 끝-->

<div id="resContainer">

</div>
</body>

<script type= "text/javascript">
function search(){
	//alert("test");
	var title = $("#title").val();
	//alert(title)
		$.ajax({
		url : "/board/searchList.do",
		type : "post",
		data : {
			"title" : title
			},
		success : function(data) {
			console.log(data);
			var resHTML = " ";
			console.log(title);
			console.log(data.length);
			resHTML += '<tr>';
			resHTML += '<td>글번호</td>';
			resHTML += '<td>제목</td>';
			resHTML += '<td>게시일</td>';
			resHTML += '<td>게시자</td>';
			resHTML += '</tr>';
			if(data.length == 0){
				resHTML +=  '<tr>';
				resHTML += '<td>-</td>';
				resHTML += '<td>-</td>';
				resHTML += '<td>-</td>';
				resHTML += '<td>-</td>';
				resHTML +=  '</tr>';
			}else{
				for(var i=0; i< data.length; i++){
					resHTML +=  '<tr>';
					resHTML += '<td>'+data[i].post_no+'</td>';
					resHTML += '<td>'+data[i].post_title+'</td>';
					resHTML += '<td>'+data[i].reg_dt+'</td>';
					resHTML += '<td>'+data[i].reg_id+'</td>';
					resHTML +=  '</tr>';
					console.log(resHTML);
				}
			}
			$("#searchResult").html(resHTML);
			
		}
	})
}

</script>
</html>