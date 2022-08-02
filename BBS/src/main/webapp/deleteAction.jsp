<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="bbs.Bbs"%>
<%@ page import="bbs.BbsDAO"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSP 게시판 웹 사이트</title>
</head>
<body>
<%-- 	<%
	BbsDAO bbsDAO = new BbsDAO();
	int result = bbsDAO.write(bbs.getBbsTitle(),userID,bbs.getBbsContent(),strDate);
	if(result == -1){
		PrintWriter script = response.getWriter();
		script.println("<script>");
		script.println("alert('글쓰기에 실패하였습니다.')");
		script.println("history.back()");
		script.println("</script>");
	}
	else{
		
		PrintWriter script = response.getWriter();
		script.println("<script>");
		script.println("location.href = 'main.jsp'");
		script.println("</script>");
	}
	%> --%>
	<button onclick="test()">파라미터값가져오기</button>
	<script>
		function test(){
			alert("Gg");
			 var bbsID = request.getParameterByName("bbsID"); alert(bbsID);
		}
		
	</script>
</body>
</html>