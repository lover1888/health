<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 
<html>
<head>
<title>I am hello ^_^</title>
</head>
<body>
<h1>
<c:out value="JSTL测试">jstl test</c:out>
${name}
</h1>
<h2>
	<c:forEach items="${map}" var="o">
		${o.value}<br>
		
	</c:forEach>
</h2>
</body>
</html>