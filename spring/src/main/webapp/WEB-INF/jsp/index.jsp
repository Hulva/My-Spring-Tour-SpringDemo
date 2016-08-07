<%@ page contentType="text/html; charset=UTF-8" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
    <title>Spring Page Redirection</title>
</head>
<body>
<h2>Spring Page Redirection</h2>
<p>点击下面的按钮跳转到新页面</p>
<form:form method="GET" action="/spring/redirect">
<table>
    <tr>
    <td>
    <input type="submit" value="Redirect Page"/>
    </td>
    </tr>
</table>  
</form:form>
<p>点击跳转到普通html页面</p>
<form:form method="GET" action="/spring/staticPage">
<table>
    <tr>
    <td>
    <input type="submit" value="Get HTML Page"/>
    </td>
    </tr>
</table>  
</form:form>
</body>
</html>