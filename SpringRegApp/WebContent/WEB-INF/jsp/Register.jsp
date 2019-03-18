<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="jstl"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Register</h1>
	
	<s:form action="register" modelAttribute="reg">
		Enter name:<s:input path="uname"/><s:errors path="uname"/><br/>
		Enter email:<s:input path="email"/><s:errors path="email"/><br/>
		Enter dob:<s:input path="dob"/><s:errors path="dob"/><br/>
		<input type="submit"/>
		
	</s:form>
	${msg}
</body>
</html>





